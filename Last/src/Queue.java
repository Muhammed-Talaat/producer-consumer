import java.util.LinkedList;
public class Queue<E> implements Observer{
	private int size;
	private int id;
	private volatile java.util.Queue<E>queue;
	public Queue(int size,int id) {
		this.size=size;
		this.id=id;
		queue=new LinkedList<E>();
	}
	public java.util.Queue<E> getQueue(){
		return this.queue;
	}
	public int getId() {
		return this.id;
	}
	public synchronized void producer(E product) {
		try {
			while(queue.size()==size);
			if(queue.size()==0) {
				queue.add(product);
				GU.notifyGUI(id, queue.size());
				notifyAll();
			}
			else
				{
				queue.add(product);
				GU.notifyGUI(id, queue.size());}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public synchronized E consumer() {
		try {
			while(queue.size()==0)
				wait();
			if(queue.size()==size) {
				E product=queue.remove();
				GU.notifyGUI(id, queue.size());
				notifyAll();
				return product;
			}
			else
				{GU.notifyGUI(id, queue.size()-1);
				return queue.remove();}
		} catch (Exception e) {
			// TODO: handle exception
			GU.notifyGUI(id, queue.size()-1);
			return queue.remove();
		}
	}
	public int size() {
		return this.queue.size();
	}
	public void print() {
		System.out.println(queue);
	}
	@Override
	public synchronized void update(Object product) {
		// TODO Auto-generated method stub
		GU.notifyGUI(id, queue.size());
	}
}
