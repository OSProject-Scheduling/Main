import java.util.Timer;
import java.util.TimerTask;
import java.util.LinkedList;

public abstract class Algorithm {
	abstract void schedulling();
	
	protected GhanttChartPanel ghanttchartPanel;
	protected LinkedList<Process> AlgorithmList = new LinkedList<>();	
	protected LinkedList<MFQProcess> HighAlgorithmList = new LinkedList<>();
	protected LinkedList<MFQProcess> MiddleAlgorithmList = new LinkedList<>();
	protected LinkedList<MFQProcess> LowAlgorithmList = new LinkedList<>();
	
	protected LinkedList<MFQProcess> HighReadyQueue = new LinkedList<>();
	protected LinkedList<MFQProcess> MiddleReadyQueue = new LinkedList<>();
	protected LinkedList<MFQProcess> LowReadyQueue = new LinkedList<>();
	protected LinkedList<Process> ReadyQueue = new LinkedList<>();
	
	protected Process PresentProcess = null;
	protected ProjectManager manager;
	protected int time = 0;
	protected int CoreWork = 1;
	
	public Algorithm(ProjectManager manager) {
		this.AlgorithmList = manager.addPanel.AlgorithmList;
		this.HighAlgorithmList = manager.addPanel.MFQHighAlgorithmList;
		this.MiddleAlgorithmList = manager.addPanel.MFQMiddleAlgorithmList;
		this.LowAlgorithmList = manager.addPanel.MFQLowAlorithmList;
		
		this.ghanttchartPanel = manager.GhanttChart;
		this.manager = manager;
		start();
	}
	
	private void start() {
		Timer timer = new Timer(); 																				// timer와 timertask를 사용해 카운트를 구현시켰습니다
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
					schedulling();
					if(AlgorithmList.isEmpty() && ReadyQueue.isEmpty() && PresentProcess == null) timer.cancel(); 
					time++; 																					// time변수를 증가시켜줘 초를 표현
					
				}
			};
			timer.schedule(task, 1000,1000); 																	// 1초마다 실행
	}
	
	
	protected void Core() { // 예정
		// -> CoreWork 이거 변경해주는거 
		// 상황에 맞게 CoreWork 변경
	}
}
