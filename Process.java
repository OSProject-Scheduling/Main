public class Process {
	String Name;
	double ArrivalTime;
	double BurstTime;
	double WaitingTime;
	double TurnaroundTime = 0;
	double NormalizedTime = 0;
	double StaticBurstTime;
	double ResponseRatio = -1;
	int Row;
	public Process(String Name, int ArrivalTime, int StaticBurstTime, int Row) {
		this.ArrivalTime = ArrivalTime;
		this.StaticBurstTime = StaticBurstTime;
		this.BurstTime = StaticBurstTime;
		this.Name = Name;
		this.Row = Row;
	}
}
