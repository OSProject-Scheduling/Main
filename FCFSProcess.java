
public class FCFSProcess {
	String Name;
	double ArrivalTime;
	double BurstTime;
	double WaitingTime;
	double TurnaroundTime = 0;
	double NormalizedTime = 0;
	double StaticBurstTime;
	double ResponseRatio = -1;
	int Quanturm;
	public FCFSProcess(String Name, int ArrivalTime, int StaticBurstTime, int Quanturm) {
		this.ArrivalTime = ArrivalTime;
		this.StaticBurstTime = StaticBurstTime;
		this.BurstTime = StaticBurstTime;
		this.Name = Name;
		this.Quanturm = Quanturm;
	}
}
