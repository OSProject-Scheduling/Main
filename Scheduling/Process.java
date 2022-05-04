package Scheduling;

public class Process {
	public String Name;
	public double ArrivalTime;
	public double BurstTime;
	public double WaitingTime;
	public double TurnaroundTime = 0;
	public double NormalizedTime = 0;
	public double StaticBurstTime;
	public double ResponseRatio = -1;
	public int Row;
	public String Priority;
	
	public Process(String Name, int ArrivalTime, int StaticBurstTime, int Row) {
		this.ArrivalTime = ArrivalTime;
		this.StaticBurstTime = StaticBurstTime;
		this.BurstTime = StaticBurstTime;
		this.Name = Name;
		this.Row = Row;
	}
	
	public Process(String Priority, String Name, int ArrivalTime, int StaticBurstTime, int Row) {
		this.ArrivalTime = ArrivalTime;
		this.StaticBurstTime = StaticBurstTime;
		this.BurstTime = StaticBurstTime;
		this.Priority = Priority;
		this.Name = Name;
		this.Row = Row;
	}
}
