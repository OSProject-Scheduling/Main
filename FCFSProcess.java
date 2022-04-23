public class FCFSProcess {
	String Name;
	int ArrivalTime;
	int BurstTime;
	int WaitingTime;
	int TurnaroundTime;
	int NormalizedTime;
	
	public FCFSProcess(String Name, int ArrivalTime, int BurstTIme,
			int WaitingTime, int TurnaroundTime, int NormalizedTime) {
		this.ArrivalTime = ArrivalTime;
		this.BurstTime = BurstTIme;
		this.Name = Name;
		this.WaitingTime = WaitingTime;
		this.TurnaroundTime = TurnaroundTime;
		this.NormalizedTime = NormalizedTime;
	}
}
