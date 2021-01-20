
import java.util.HashMap;
import java.util.List;
public class SimulationState {
	private List<shape> Queues;
	private List<shape> Machine;
	private HashMap<Integer, List<Integer>> Input_Q;
	private HashMap<Integer, List<Integer>> Output_Q;
	private HashMap<Integer, List<Integer>> Input_M;
	private HashMap<Integer, List<Integer>> Output_M;
	private List<Product> lst;
	public SimulationState(
			List<shape> Queues, List<shape> Machine, HashMap<Integer,
			List<Integer>> Input_Q,HashMap<Integer, List<Integer>> Output_Q, 
			HashMap<Integer, List<Integer>> Input_M,HashMap<Integer, List<Integer>> Output_M,
			List<Product> lst) {
		// TODO Auto-generated constructor stub
		this.Queues=Queues;this.Machine=Machine;
		this.Input_Q=Input_Q;this.Output_Q=Output_Q;
		this.Input_M=Input_M;this.Output_M=Output_M;
		this.lst=lst;
	}
	public List<shape> getQueues() {
		return Queues;
	}
	public List<shape> getMachine() {
		return Machine;
	}
	public HashMap<Integer, List<Integer>> getInput_Q() {
		return Input_Q;
	}
	public HashMap<Integer, List<Integer>> getOutput_Q() {
		return Output_Q;
	}
	public HashMap<Integer, List<Integer>> getInput_M() {
		return Input_M;
	}
	public HashMap<Integer, List<Integer>> getOutput_M() {
		return Output_M;
	}
	public List<Product> getLst() {
		return lst;
	}
}
