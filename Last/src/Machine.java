import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
public class Machine<E> extends Thread implements Subject {
	private boolean isTaskCompleted;
	private List<Observer>observers;
	private int id;
	public Machine(List<Observer>observers,int id) {
		// TODO Auto-generated constructor stub
		this.observers=observers;
		this.id=id;
	}
	public int getID() {return this.id;}
	public synchronized void addInput(Observer ob) {
		if(this.observers.size()>0) 
			this.observers.add(this.observers.size()-1, ob);
		else {
			notifyAll();
			this.observers.add(ob);
		}
	}
	
	public void addOutput(Observer ob) {
		this.observers.add(ob);
	}
	public void processing() {
		LocalDateTime time=LocalDateTime.of(LocalDate.now(),LocalTime.ofSecondOfDay(new Random().nextInt(7)+3));
		Timer timer=new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				isTaskCompleted=true;
				timer.cancel();
			}
		}, time.getSecond()*1000);
	}
	@SuppressWarnings("unchecked")
	public synchronized void handle() {
		Random rn=new Random();
		for(;;) {
		try {
			Thread.sleep(rn.nextInt(3000)+1200);
			while(this.observers.size()==0)Thread.sleep(10);
			Queue<E>output=(Queue<E>) this.observers.get(observers.size()-1);
			for(int i=0;i<this.observers.size()-1;i++) {
				Queue<E>input=(Queue<E>) this.observers.get(i);
				if(input.size()!=0) {
					processing();
					E product=input.consumer();
					GU.notifyGUI(true,this.id,(Product) product);
					GU.notifyGUI(input.getId(),input.getQueue().size());
					while(!isTaskCompleted)
						Thread.sleep(0);
					GU.notifyGUI(false,this.id,null);
					output.producer(product);
					notifyObservers(input,product);
					output.print();
					this.isTaskCompleted=false;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}}
	}
	@Override
	public void notifyObservers(Observer observer,Object product) {
		// TODO Auto-generated method stub
		observer.update(product);
	}
	
	@Override
	public void run() {
		handle();
	}
}



