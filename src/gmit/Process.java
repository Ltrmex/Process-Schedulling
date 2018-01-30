package gmit;

import java.util.Comparator;

public class Process {
	private String processName;
	private int startTime;
	private int remainingTime;
	private int waitTime;
	private int burstTime;
	private int durationTime;

	public Process(String processName, int startTime, int remainingTime, int waitTime, int burstTime, int durationTime) {
		super();
		this.processName = processName;
		this.startTime = startTime;
		this.remainingTime = remainingTime;
		this.waitTime = waitTime;
		this.burstTime = burstTime;
		this.durationTime = durationTime;
	}

	public int getDurationTime() {
		return durationTime;
	}

	public void setDurationTime(int durationTime) {
		this.durationTime = durationTime;
	}

	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	
	public int getStartTime() {
		return startTime;
	}
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	
	public int getRemainingTime() {
		return remainingTime;
	}
	public void setRemainingTime(int remainingTime) {
		this.remainingTime = remainingTime;
	}
	
	public int getWaitTime() {
		return waitTime;
	}
	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}
	
	public int getBurstTime() {
		return burstTime;
	}

	public void setBurstTime(int burstTime) {
		this.burstTime = burstTime;
	}

	public static class burstComparator implements Comparator<Process> {

		public int compare(Process p1, Process p2){
			int burstTime1 = p1.getBurstTime();
			int burstTime2 = p2.getBurstTime();
			
			if (burstTime1 == burstTime2)
				return 0;
			else if (burstTime1 > burstTime2)
				return 1;
			else
				return -1;
			}

	}

	public String toString() {
		return "\t" + processName + "\t\t" + startTime + "\t\t" + remainingTime + "\t\t" + waitTime + "\t     " + burstTime;
	}
		
}//Process
