package Scheduling;

public class MFQProcess extends Process{
	public String PriorityRedayQueue;
	public MFQProcess(String PriorityRedayQueue, String Name,int ArrivalTime, int StaticBurstTime, int Row) {
		super(Name, ArrivalTime, StaticBurstTime, Row);
		this.PriorityRedayQueue = PriorityRedayQueue;
	}
}
