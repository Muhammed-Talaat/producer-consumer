import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class Simulation {
	private SimulationState currentState;
	private List<SimulationState>checkPoints;
	public Simulation(
			List<shape> Queues, List<shape> Machine, HashMap<Integer,
			List<Integer>> Input_Q,HashMap<Integer, List<Integer>> Output_Q, 
			HashMap<Integer, List<Integer>> Input_M,HashMap<Integer, List<Integer>> Output_M,
			List<Product> lst) {
		// TODO Auto-generated constructor stub
		currentState=new SimulationState(
				Queues,Machine,
				Input_Q,Output_Q,
				Input_M,Output_M
				,lst);
		checkPoints=new ArrayList<SimulationState>();
	}
	public void save() {
		List<shape>queues=currentState.getQueues();
		List<shape>machines=currentState.getMachine();
		HashMap<Integer,List<Integer>>inputQueue=currentState.getInput_Q();
		HashMap<Integer,List<Integer>>outputQueue=currentState.getOutput_Q();
		HashMap<Integer,List<Integer>>inputMachine=currentState.getInput_M();
		HashMap<Integer,List<Integer>>outputMachine=currentState.getOutput_M();
		List<Product>products=currentState.getLst();
		SimulationState savedState=new SimulationState(
				queues,machines,
				inputQueue,outputQueue,
				inputMachine,outputMachine,
				products);
		checkPoints.add(savedState);
	}
	public SimulationState restore() {
		List<shape>queues=checkPoints.get(checkPoints.size()-1).getQueues();
		List<shape>machines=checkPoints.get(checkPoints.size()-1).getMachine();
		HashMap<Integer,List<Integer>>inputQueue=checkPoints.get(checkPoints.size()-1).getInput_Q();
		HashMap<Integer,List<Integer>>outputQueue=checkPoints.get(checkPoints.size()-1).getOutput_Q();
		HashMap<Integer,List<Integer>>inputMachine=checkPoints.get(checkPoints.size()-1).getInput_M();
		HashMap<Integer,List<Integer>>outputMachine=checkPoints.get(checkPoints.size()-1).getOutput_M();
		List<Product>products=checkPoints.get(checkPoints.size()-1).getLst();
		SimulationState restoredState=new SimulationState(
				queues,machines,
				inputQueue,outputQueue,
				inputMachine,outputMachine,
				products);
		currentState=restoredState;
		return currentState;
	}
}