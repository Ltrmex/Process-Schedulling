package gmit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Runner {
	
	static Scanner input = new Scanner(System.in);	//import scanner
	
	public static void main(String[] args) {
		//Variables
		int size, choice = 0;
		
		//User Input
		System.out.print("Number of processes you would like to enter: ");
		size = input.nextInt();
		
		//Create array of processes
		ArrayList<Process> processes = new ArrayList<Process>();
		//Process[] processes = new Process[size];
		
		//Initialize Array of Objects
		for(int i = 0; i < size; i++)
			processes.add(new Process("",0,0,0,0,0));
		
		//Name & Burst Input
		for(Process p : processes) {
			System.out.print("\n\tEnter Process Name: ");
			p.setProcessName(input.next());
			
			System.out.print("\tEnter Burst Time for Process " + p.getProcessName() + ": ");
			p.setBurstTime(input.nextInt());
			
			p.setRemainingTime(p.getBurstTime());
		}//for
		
		ArrayList<Process> backup = new ArrayList<Process>();
		
		for (int i = 0 ; i<processes.size();i++)
		    backup.add(processes.get(i));
		
		System.out.println();
		
		do {
			//Print menu
			menu();
			
			//User Input
			System.out.print("\nEnter choice: ");
			choice = input.nextInt();
			
			switch(choice){
				case 1:
					//Round Robin
					roundRobin(processes);
					break;
				case 2:
					//First Come First Served
					fcfs(processes);
					break;
				case 3:
					//Shortest Job First
					Collections.sort(processes, new Process.burstComparator());	//sort
					fcfs(processes);
					break;
				default: 
					System.out.println("Such option doesn't exist");
			}//switch
			
			//Calculate and Print Average Wait Time
			calculateAverage(processes);
			
			processes.clear();
			
			for (int i = 0 ; i<backup.size();i++)
			    processes.add(backup.get(i));
			
		}while(choice != 4);

	}//main

	private static void menu() {
		System.out.println("1 - Round Robin");
		System.out.println("2 - First Come First Served");
		System.out.println("3 - Shortest Job First");
		System.out.println("4 - Exit");
	}
	
	private static void displayAll(ArrayList<Process> processes){
		//Output
		System.out.println(" Process Name \t   Start Time \t Remaining Time    Wait Time    Burst Time");
		System.out.println("===========================================================================");
	
		for(Process p : processes)
			System.out.println(p.toString());
	}
	
	private static void fcfs(ArrayList<Process> processes) {
		//First Come First Served
		int startTime = 0;
		
		for(int i = 0; i < processes.size(); i++) {
			if(i == 0) {
				processes.get(i).setWaitTime(0);
				processes.get(i).setStartTime(0);
			}
			else {
				startTime += processes.get(i-1).getBurstTime();
				processes.get(i).setWaitTime(startTime);
				processes.get(i).setStartTime(startTime);
				processes.get(i).setRemainingTime(0);
			}
		}//for
		
		displayAll(processes);
	}
	
	private static void calculateAverage(ArrayList<Process> processes) {
		double sum = 0;
		int count = 0;
		
		for(Process p : processes) {
			++count;
			sum += p.getStartTime();
		}
		
		System.out.println("\nAverage Wait Time is: " + sum/count + "\n");
	}
	
	private static void roundRobin(ArrayList<Process> processes) {
		System.out.print("Enter quantum: ");
		int quantum = input.nextInt();
		int current = 0;
		int sum = 0;
		
		System.out.println(" Process Name \t   Start Time \t Remaining Time    Wait Time    Burst Time");
		System.out.println("===========================================================================");
	
		for(Process process : processes)
			sum += process.getRemainingTime();
		
		while(sum > 0) {
			for(Process process : processes) { 
				
				if(process.getRemainingTime() > 0) {			
					int duration = process.getRemainingTime() < quantum ? process.getRemainingTime() : quantum;
					
					process.setDurationTime(process.getDurationTime() + duration);
					process.setWaitTime(current); //set process wait time to current time
					process.setRemainingTime(process.getBurstTime() - duration);
					process.setStartTime(process.getWaitTime());
	
					current += duration;
					
					sum -= process.getRemainingTime();
					
					if(sum - process.getBurstTime() <= 0)
						process.setWaitTime(process.getWaitTime() - quantum);
					
					if(process.getBurstTime() - process.getDurationTime() == 0)
						process.setRemainingTime(0);
					
				}		
				else{continue;}
				
				System.out.println(process.toString());
			}
			
		}

	}

}//Runner
