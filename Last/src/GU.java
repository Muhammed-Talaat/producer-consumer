
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class GU extends JFrame {
 
	/**
	 *
	 */	
	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
	static List<shape> Queues=new ArrayList<shape>();
	static List<shape> Machine=new ArrayList<shape>();
	private static HashMap<Integer,List<Integer>> Input_Q=new HashMap<Integer,List<Integer>>();
	private static HashMap<Integer,List<Integer>> Output_Q=new HashMap<Integer,List<Integer>>();
	private static HashMap<Integer,List<Integer>> Input_M=new HashMap<Integer,List<Integer>>();
	private static HashMap<Integer,List<Integer>> Output_M=new HashMap<Integer,List<Integer>>();
	private static List<Product> prdct = new ArrayList<Product>(); 
	private static List<Machine<Product>> mchne=new ArrayList<Machine<Product>>();
	private static List<Queue<Product>> que=new ArrayList<Queue<Product>>();
	private JTextField x_text;
	private JTextField y_text;
	private JTextField textField_2;
	private JTextField textField_3;
    private int DimX=1490;
    private int DimY=600;
    boolean start=true;
    private int maxStartNum=8;
    private int maxNum=16;
    private int maxPrdctNum=30;
    private int maxQueSize=50;
    private static JButton Start;
    private static JButton btnNewButton_4;
    private static JButton btnNewButton_3;
    private static JButton btnNewButton_2;
    private static JButton btnNewButton_1;
    private static JButton btnNewButton;
    private static Simulation simulation;
    private Color[] colCollection=new Color[] {
    		Color.BLUE,Color.RED,Color.GRAY,Color.cyan,
    		Color.ORANGE,Color.MAGENTA,Color.WHITE,Color.pink,Color.DARK_GRAY
    };
    private JTextField startNum;
    private JTextField Machin;
    private JTextField product;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GU frame = new GU();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GU() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(20, 20, DimX, DimY);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.white);
		setContentPane(contentPane);
		setTitle("My Application");
		getContentPane().setLayout(null);
	    btnNewButton = new JButton("Add Q's");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setFocusable(false);
		btnNewButton.setEnabled(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				L:{
				int x;int y;
				try {
				x=Integer.parseInt(x_text.getText());
				y=Integer.parseInt(y_text.getText());
				}
				catch(Exception et){			
					JOptionPane.showMessageDialog(null,"enter Numeric values");
					break L;
				}
				if(validate(x,y)&&!start) {
				rectangle rect=new rectangle(x,y,20,50,"Q"+Queues.size(),Color.yellow,0);
				Queues.add(rect);
				Input_Q.put(Queues.size()-1,new ArrayList<Integer>());
				Output_Q.put(Queues.size()-1,new ArrayList<Integer>());
				rect.draw(contentPane);
				que.add(new Queue(maxQueSize,que.size()));
				}
				else {
					JOptionPane.showMessageDialog(null,"click start or enter valid input");
				}
			}
			x_text.setText("");
			y_text.setText("");
			}
		});
		contentPane.setLayout(null);
		btnNewButton.setBounds(12, 15, 86, 32);
		getContentPane().add(btnNewButton);
		
		x_text = new JTextField();
		x_text.setBounds(232, 17, 48, 28);
		getContentPane().add(x_text);
		x_text.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("X :");
		lblNewLabel.setBounds(210, 22, 17, 16);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Y :");
		lblNewLabel_1.setBounds(290, 22, 25, 16);
		getContentPane().add(lblNewLabel_1);
		
		y_text = new JTextField();
		y_text.setBounds(310, 17, 48, 28);
		getContentPane().add(y_text);
		y_text.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(370, 13, 7, 36);
		getContentPane().add(separator);
		
	    btnNewButton_1 = new JButton("Add M's");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1.setBounds(110, 15, 89, 32);
		getContentPane().add(btnNewButton_1);
		btnNewButton_1.setFocusable(false);
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				L:{
				int x;int y;
				try {
				x=Integer.parseInt(x_text.getText());
				y=Integer.parseInt(y_text.getText());
				}
				catch(Exception et){
					JOptionPane.showMessageDialog(null,"enter Numeric values");
					break L;
				}
				if(validate(x,y)&&!start) {
				circle cir=new circle(x,y,36,"M"+Machine.size(),Color.green);
				Machine.add(cir);
				Input_M.put(Machine.size()-1,new ArrayList<Integer>());
				Output_M.put(Machine.size()-1,new ArrayList<Integer>());
				cir.draw(contentPane);
				mchne.add(new Machine(new ArrayList<Observer>(),mchne.size()));
				}
				else JOptionPane.showMessageDialog(null,"click start or enter valid input");
			}
			x_text.setText("");
			y_text.setText("");
			}
		});
		
		
		
	    btnNewButton_2 = new JButton("Connect");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_2.setBounds(383, 14, 93, 32);
		getContentPane().add(btnNewButton_2);
		btnNewButton_2.setFocusable(false);
		btnNewButton_2.setEnabled(false);

		
		JLabel lblNewLabel_2 = new JLabel("Que Num:");
		lblNewLabel_2.setBounds(485, 21, 67, 16);
		getContentPane().add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(552, 16, 48, 28);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Mach Num:");
		lblNewLabel_3.setBounds(609, 22, 67, 16);
		getContentPane().add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		textField_3.setBounds(681, 17, 48, 28);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 60, DimX-36, 2);
		getContentPane().add(separator_1);
		
		JRadioButton rdbtn = new JRadioButton("Qs to Ms");
		rdbtn.setBounds(739, 10, 78, 23);
		contentPane.add(rdbtn);
		rdbtn.setFocusable(false);
		rdbtn.setBackground(Color.white);
		
		
		JRadioButton rdbtn1 = new JRadioButton("Ms to Qs");
		rdbtn1.setBounds(739, 30, 80, 25);
		contentPane.add(rdbtn1);
		rdbtn1.setFocusable(false);
		rdbtn1.setBackground(Color.white);
		rdbtn.setSelected(true);
		
		
		
	    Start = new JButton("Start");
		Start.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Start.setBounds(836, 16, 73, 31);
		Start.setFocusable(false);
		contentPane.add(Start);
		
		startNum = new JTextField();
		startNum.setBounds(978, 15, 48, 28);
		contentPane.add(startNum);
		startNum.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Q's num:");
		lblNewLabel_4.setBounds(919, 21, 56, 16);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("M's num:");
		lblNewLabel_5.setBounds(1036, 21, 56, 16);
		contentPane.add(lblNewLabel_5);
		
		Machin = new JTextField();
		Machin.setBounds(1095, 15, 48, 29);
		contentPane.add(Machin);
		Machin.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Product's");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_6.setBounds(1150, 14, 55, 16);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("num:");
		lblNewLabel_7.setBounds(1160, 28, 40, 16);
		contentPane.add(lblNewLabel_7);
		
		product = new JTextField();
		product.setBounds(1209, 15, 48, 29);
		contentPane.add(product);
		product.setColumns(10);
		
	    btnNewButton_4 = new JButton("replay");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/////////////////////////////
				/////////////////////////////
				// snapshot
				clear();
				SimulationState temp = simulation.restore();
				Queues = temp.getQueues();
				Machine = temp.getMachine();
				Input_Q = temp.getInput_Q();
				Output_Q = temp.getOutput_Q();
				Input_M = temp.getInput_M();
				Output_M = temp.getOutput_M();
				nodeQueue=getNodeQueue();
				load(Queues,Machine,Input_Q,Output_Q,Input_M,Output_M,temp.getLst());
			    Queues.forEach( shape -> ((rectangle)shape).setNumOfProducts(0));
				Queues.forEach( shape -> shape.draw(contentPane));
				Machine.forEach( shape -> shape.draw(contentPane));
				invoke();
			}
		});
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_4.setEnabled(false);
		btnNewButton_4.setBounds(1373, 13, 83, 32);
		contentPane.add(btnNewButton_4);
		btnNewButton_4.setFocusable(false);
		
	    btnNewButton_3 = new JButton("Invoke");
		btnNewButton_3.setEnabled(false); 
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validStartState()) {
					invoke();
				}
				else {
					JOptionPane.showMessageDialog(null,"press start or enter valid starting state");
				}
			}

		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_3.setBounds(1280, 13, 80, 32);
		contentPane.add(btnNewButton_3);
		btnNewButton_3.setFocusable(false);
		
		
		Start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				L:{
				int numQ;
				int numM;
				int prdctNum;
				try {
					numQ=Integer.parseInt(startNum.getText());
					numM=Integer.parseInt(Machin.getText());
					prdctNum=Integer.parseInt(product.getText());
				}
				catch(Exception et){
					JOptionPane.showMessageDialog(null,"enter Numeric values");
					break L;
				}
				if( start && numQ>0 && numQ <= maxStartNum && numM>0 && numM <= maxStartNum && prdctNum > 0 && prdctNum <= maxPrdctNum ) {
					start=false;
					shuffle(numQ,numM,createRandomProducts(prdctNum));
					btnNewButton_3.setEnabled(true);
					btnNewButton_1.setEnabled(true);
					btnNewButton_2.setEnabled(true);
					btnNewButton.setEnabled(true);
				}
				else {
					JOptionPane.showMessageDialog(null,"click start Once or69nter vali39input \n"
							+ "maximum Q's or M's Num: "+maxStartNum+" maximum product's Num: "+maxPrdctNum+""
									+ "\nMinumum must be positive Number");
				}
			}
			startNum.setText("");
			Machin.setText("");
			product.setText("");
			}
		});
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(1268, 11, 1, 41);
		contentPane.add(separator_3);
		
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(823, 12, 2, 36);
		contentPane.add(separator_2);
		
		rdbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtn1.setSelected(false);
			}
		});
		
		rdbtn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtn.setSelected(false);
			}
		});
		
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				L:{
				int queu;int machine;
				try {
				queu=Integer.parseInt(textField_2.getText());
				machine=Integer.parseInt(textField_3.getText());
				}
				catch(Exception et){
					JOptionPane.showMessageDialog(null,"enter Numeric values");
					break L;
				}
				if( !start && queu >= 0 && queu < Queues.size() && machine >= 0 && machine < Machine.size() ) {
				   if(rdbtn1.isSelected()) {
					   if(Output_M.get(machine).size()<1) {
						   if(Output_M.get(machine).contains(queu)||Input_M.get(machine).contains(queu) || queu==0)
							   JOptionPane.showMessageDialog(null,"enter valid connection!");
						   else {
					         connect(Machine.get(machine),Queues.get(queu));
					         Output_M.get(machine).add(queu); 
					         Input_Q.get(queu).add(machine);
					         mchne.get(machine).addOutput(que.get(queu));}
					   }
					   else {
						   JOptionPane.showMessageDialog(null,"each machine must have 1 and only 1 output");
					   }
					   // queue reminder
				   }
				   else {
					   if(Input_Q.get(queu).contains(machine)||Output_Q.get(queu).contains(machine)) {
						   JOptionPane.showMessageDialog(null,"enter valid connection!");
					   }
					   else {
					   connect(Queues.get(queu),Machine.get(machine));
					   Output_Q.get(queu).add(machine);   
					   Input_M.get(machine).add(queu);
					   mchne.get(machine).addInput(que.get(queu));
					   }
				   }
				}else JOptionPane.showMessageDialog(null,"press start or enter valid connectios number");
			}
			textField_2.setText("");
			textField_3.setText("");
			}
		});
		setVisible(true);
	}
	
	public boolean validate(int x,int y) {
		for( shape sh : Queues ) {
			if( Math.abs(x-sh.getX())<63 && Math.abs(y-sh.getY()) < 63 ) {
				return false;
			}
		}
        for( shape sh : Machine ) {
        	if( Math.abs(x-sh.getX())<63 && Math.abs(y-sh.getY()) < 63 ) {
				return false;
			}
		}
		return x > 20 && x < (DimX-63) && y > 90 && y < (DimY-63) ;
	}
	
	private void connect(shape shape, shape shape2) {
	Point []p=getConnectPoints(shape,shape2);
	Graphics g=contentPane.getGraphics();
	g.setColor(Color.black);
	g.drawLine(p[0].x,p[0].y,p[1].x,p[1].y);
	}

	public Point[] getConnectPoints(shape shape, shape shape2) {
		// TODO Auto-generated method stub
		Point Center1=new Point();
		Center1.setLocation(shape.getX()+shape.getWidth()/2,
				shape.getY()+shape.getHeight()/2);
		Point Center2=new Point();
		Center2.setLocation(shape2.getX()+shape2.getWidth()/2,
				shape2.getY()+shape2.getHeight()/2);
		Point interSect[]=new Point[]{intersect(shape,Center1,Center2),intersect(shape2,Center2,Center1)};
		drawArrowHead(interSect[1],interSect[0],contentPane);
		return interSect;
	}
	
	public void invoke() {
		btnNewButton_3.setEnabled(false); 
		btnNewButton_1.setEnabled(false);
		btnNewButton_2.setEnabled(false);
		btnNewButton.setEnabled(false);
		btnNewButton_4.setEnabled(false);
		Start.setEnabled(false);
		start=true;
		nodeQueue=getNodeQueue();
		simulation=new Simulation(Queues, Machine, Input_Q, Output_Q, Input_M, Output_M, prdct);
		runSimulation();
	}
	
	public Point intersect(shape shape,Point shapeCenter, Point anotherCenter) {
		double slope=(double)shape.getHeight()/shape.getWidth();int x=0;int y=0;
		double tan = (shapeCenter.y-anotherCenter.y) / (anotherCenter.x-shapeCenter.x+0.1);
		if(slope*Math.abs(anotherCenter.x-shapeCenter.x)<=Math.abs(anotherCenter.y-shapeCenter.y)) {
			boolean temp = (anotherCenter.y<shapeCenter.y);
			y = shape.getY() + (temp ? 0:shape.getHeight());
			x= shapeCenter.x + ( temp ? (int)((shape.getHeight()/2)/tan):
				-(int)((shape.getHeight()/2)/tan));
		}
		else {
			boolean temp = (anotherCenter.x<shapeCenter.x);
			x=shape.getX() + (temp?0:shape.getWidth());
			y=shapeCenter.y + (temp ? (int)(tan*(shape.getWidth()/2)):
				-(int)(tan*(shape.getWidth()/2)));
		}
		return new Point(x,y);
	}
	

	public void drawArrowHead(Point end,Point start,JPanel jp) {
		Graphics g=jp.getGraphics();
		g.setColor(Color.black);
		Polygon arrowHead = new Polygon();  
		double angle = Math.atan2(-1*(start.y-end.y),start.x-end.x)*180/Math.PI;
		arrowHead.addPoint(end.x,end.y);
		if(angle<180 && angle>0) {
			if(angle<30) {
				arrowHead.addPoint(end.x+8,end.y-5);
				arrowHead.addPoint(end.x+8,end.y+5);
			}
			else if(angle<60) {
				arrowHead.addPoint(end.x,end.y-8);
				arrowHead.addPoint(end.x+8,end.y);
			}
			else if(angle<120) {
				arrowHead.addPoint(end.x+5,end.y-8);
				arrowHead.addPoint(end.x-5,end.y-8);
			}
			else if(angle<150){
				arrowHead.addPoint(end.x,end.y-8);
				arrowHead.addPoint(end.x-8,end.y);
			}
			else {
				arrowHead.addPoint(end.x-8,end.y-5);
				arrowHead.addPoint(end.x-8,end.y+5);
			}
		}
		else {
			if(angle>-30) {
				arrowHead.addPoint(end.x+8,end.y-5);
				arrowHead.addPoint(end.x+8,end.y+5);
			}
			else if(angle>-60) {
				arrowHead.addPoint(end.x+8,end.y);
				arrowHead.addPoint(end.x,end.y+8);
			}
			else if(angle>-120) {
				arrowHead.addPoint(end.x-5,end.y+8);
				arrowHead.addPoint(end.x+5,end.y+8);
			}
			else if(angle>-150){
				arrowHead.addPoint(end.x-8,end.y);
				arrowHead.addPoint(end.x,end.y+8);
			}
			else {
				arrowHead.addPoint(end.x-8,end.y-5);
				arrowHead.addPoint(end.x-8,end.y+5);
			}
		}
	    Graphics2D gr = (Graphics2D) g.create(); 
	    gr.fill(arrowHead);
	    gr.dispose();
	}
	
	private void clear() {
		Queues=new ArrayList<shape>();
		Machine=new ArrayList<shape>();
		Input_Q=new HashMap<Integer,List<Integer>>();
		Output_Q=new HashMap<Integer,List<Integer>>();
		Input_M=new HashMap<Integer,List<Integer>>();
		Output_M=new HashMap<Integer,List<Integer>>();
		mchne=new ArrayList<Machine<Product>>();
		que=new ArrayList<Queue<Product>>();
		prdct = new ArrayList<Product>(); 
		nodeQueue=new HashSet<Integer>();
		numOfStableProducts=0;
		Graphics g=this.contentPane.getGraphics();
		g.setColor(Color.white);
		g.fillRect(63,63,DimX,DimY);
	}
	
	public void start() {
		btnNewButton_3.setEnabled(false); 
		btnNewButton_1.setEnabled(false);
		btnNewButton_2.setEnabled(false);
		btnNewButton.setEnabled(false);
		btnNewButton_4.setEnabled(false);
		Start.setEnabled(false);
		start=true;
		nodeQueue=getNodeQueue();
		simulation=new Simulation(Queues, Machine, Input_Q, Output_Q, Input_M, Output_M, prdct);
		runSimulation();
	}
	
	private void shuffle(int numQ, int numM, List<Product> lst) {
		// TODO Auto-generated method stub
		clear();
		for(int i=0;i<numQ;i++) {
			int x=DimX-100-i*170;
			int y=(int)(DimY/3.1);
			y += (i%2==0) ? 0:80;
			rectangle rect=new rectangle(x,y,20,50,"Q"+Queues.size(),Color.yellow,0);
			Queues.add(rect);
			Input_Q.put(Queues.size()-1,new ArrayList<Integer>());
			Output_Q.put(Queues.size()-1,new ArrayList<Integer>());
		}
		for(int i=0;i<numM;i++) {
			int x=DimX-100-i*170;
			int y=(int)(DimY/1.55);
			y += (i%2==0) ? 0:80;
			circle cir=new circle(x,y,36,"M"+Machine.size(),Color.green);
			Machine.add(cir);
			Input_M.put(Machine.size()-1,new ArrayList<Integer>());
			Output_M.put(Machine.size()-1,new ArrayList<Integer>());
		}
		nodeQueue=getNodeQueue();
		load(Queues,Machine,Input_Q,Output_Q,Input_M,Output_M,lst);
	}

	public void load(List<shape> Queues, List<shape> Machine, HashMap<Integer, List<Integer>> Input_Q,
			HashMap<Integer, List<Integer>> Output_Q, HashMap<Integer, List<Integer>> Input_M,
			HashMap<Integer, List<Integer>> Output_M, List<Product> lst) {
		this.prdct=lst;
		Queues.forEach( shape -> shape.draw(this.contentPane));
		Machine.forEach( shape -> shape.draw(this.contentPane));
		for(int i=0;i<Queues.size()-1;i++) {
			List<Integer> connects=Output_Q.get(i);
			for(Integer e:connects)connect(Queues.get(i),Machine.get(e));
			que.add(new Queue(maxQueSize,i));
		}
		que.add(new Queue(maxQueSize,Queues.size()-1));
		for(int i=0;i<Machine.size();i++) {
			List<Integer> connects=Output_M.get(i);
			List<Integer> connectsInput=Input_M.get(i);
			for(Integer e:connects) {
				connect(Machine.get(i),Queues.get(e));}
			List<Observer> temp=new ArrayList<Observer>();
			connectsInput.forEach( num -> temp.add(que.get(num)));
			if(connects.size()>0) {temp.add(que.get(connects.get(0)));
			mchne.add(new Machine(temp,i));}
			else {mchne.add(new Machine(temp,i));}
		}
		
	}
	
	private List<Product> createRandomProducts(int maxNum){
		List<Product> temp_lst=new ArrayList<Product>();
		Random rnd=new Random();
		for(int i=0;i<maxNum;i++) {
			int index=rnd.nextInt(colCollection.length);
			temp_lst.add(new Product(this.colCollection[index],i));
		}
		return temp_lst;
	}
	

	
	private void runSimulation() {
		// TODO Auto-generated method stub
		simulation.save();
		int tempQueue=0;
		Thread t1=new Thread(new Runnable() {
			@SuppressWarnings("unchecked")
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Random rnd=new Random();
				for(int i=0;i<prdct.size();i++) {
					int temp=rnd.nextInt(6);
					try {
						Thread.sleep(temp*1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
					}
					que.get(tempQueue).producer(prdct.get(i));
				}
			}
		});
		Thread[] threads=new Thread[mchne.size()];
		for(int i=0;i<threads.length;i++)
			threads[i]= mchne.get(i);
		t1.start();
		for(Thread th : threads) 
			th.start();
	}
	
	private static HashSet<Integer> nodeQueue=new HashSet<Integer>();
	private static int numOfStableProducts=0;
	public static void notifyGUI(boolean isInput,int id,Product prdct) {
		if(isInput)changeColor("M"+id,prdct.getColor());
		else changeColor("M"+id,Color.green);
	}
	
	public static void notifyGUI(int id,int num) {
		rectangle temp=(rectangle) Queues.get(id);
		temp.setNumOfProducts(num);
		temp.draw(contentPane);
		if(nodeQueue.contains(id))numOfStableProducts++;
		if(numOfStableProducts==prdct.size())end();
	}
	
	private static void end() {
		Start.setEnabled(true);
		btnNewButton_4.setEnabled(true);
	}
	
	private static void changeColor(String content,Color newColor) {
		shape temp = null;
			for(shape s: 
				(content.contains("M")?Machine:Queues))
				if(content.compareToIgnoreCase(s.getContent())==0)temp=s;
		if(temp !=null) {temp.setColor(newColor);temp.draw(contentPane);
		}
	}
	private boolean validStartState() {
		// TODO Auto-generated method stub
		List<List<Integer>> holder=new ArrayList<List<Integer>>();
		for(int i=0;i<this.Input_M.size();i++) {holder.add(this.Input_M.get(i));}
		holder.addAll(this.Output_M.values());
		for(List<Integer> in:holder)if(in.size()<1)return false;
		holder=new ArrayList<List<Integer>>();
		for(int i=1;i<Input_Q.size();i++)holder.add(this.Input_Q.get(i));
		for(List<Integer> in:holder)if(in.size()<1)return false;
		
		
		return true;
	}
	
	private HashSet<Integer> getNodeQueue() {
		// TODO Auto-generated method stub
		HashSet<Integer> tmp=new HashSet<Integer>();
		for(int i=0;i<Queues.size();i++) {
			if(Output_Q.get(i).size()==0)tmp.add(i);
		}
		return tmp;
	}
}


interface shape{
	public void setColor(Color color);
	public void draw(JPanel g);
	public void delete(JPanel g);
	public void setContent(String con);
	public String getContent();
	public int getX();
	public int getY();
	public int getWidth();
	public int getHeight();
}

class circle implements shape{
	private int x;
	private int y;
	private Color color;
	private int radius;
	private String content;
	circle(int x,int y,int radius,String content,Color color){
		this.x=x;
		this.y=y;
		this.color=color;
		this.radius=radius;
		this.content=content;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color=color;
	}
	public void draw(JPanel g) {
		Graphics gr=g.getGraphics();
		gr.setColor(Color.black);
		gr.drawOval(x-1, y-1, radius+1, radius+1);
		gr.setColor(color);
		gr.fillOval(x, y, radius, radius);
		gr.setColor(Color.black);
		gr.drawString(content,x+10,y+20); 
	}
	public void delete(JPanel g) {
		this.color=Color.white;
		draw(g);
	}
	@Override
	public void setContent(String con) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String getContent() {
		// TODO Auto-generated method stub
		return content;
	}
	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return radius;
	}
	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return radius;
	}
}


class rectangle implements shape{
	private int x;
	private int y;
	private Color color;
	private int height;
	private int width;
	private String content;
	private int numOfProducts;
	rectangle(int x,int y,int height,int width,String content,Color color,int numOfProducts){
		this.x=x;
		this.y=y;
		this.color=color;
		this.height=height;
		this.width=width;
		this.content=content;
		this.numOfProducts=numOfProducts;
	}
	public void setNumOfProducts(int numOfProducts) {
		this.numOfProducts=numOfProducts;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getHeight() {
		return height;
	}
	public int getWidth() {
		return width;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color=color;
	}
	public void setContent(String con) {
		this.content=con;
	}
	public String getContent() {
		return content;
	}
	public void draw(JPanel g) {
		Graphics gr=g.getGraphics();
		gr.setColor(Color.black);
		gr.drawRect(x-1, y-1, width+1, height+1);
		gr.setColor(color);
		gr.fillRect(x, y, width, height);
		gr.setColor(Color.black);
		gr.drawString(content,x+15,y+15);
		gr.setColor(Color.white);
		gr.fillRect(x+10,y-20, 26, 10);
		gr.setColor(Color.black);
		gr.drawString(numOfProducts+"",x+10,y-10); 
	}
	public void delete(JPanel g){
		this.color=Color.white;content="";
		draw(g);
	}
}



