
public class MFQProcess extends Process{
	String PriorityRedayQueue;
	public MFQProcess(String PriorityRedayQueue, String Name,int ArrivalTime, int StaticBurstTime) {
		super(Name, ArrivalTime, StaticBurstTime);
		this.PriorityRedayQueue = PriorityRedayQueue;
	}
}
