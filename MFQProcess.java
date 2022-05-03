
public class MFQProcess extends Process{
	String PriorityRedayQueue;
	public MFQProcess(String PriorityRedayQueue, String Name,int ArrivalTime, int StaticBurstTime, int Row) {
		super(Name, ArrivalTime, StaticBurstTime, Row);
		this.PriorityRedayQueue = PriorityRedayQueue;
	}
}
