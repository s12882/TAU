package pl.edu.pjatk.tau.lab4;

import java.util.ArrayList;


public class AlarmRingImpl implements AlarmRing  {
	
	Time czas;
	private boolean State = true;
	public String previousRing;
	private Time time;
	
	ArrayList<Time> timesToRing = new ArrayList<>();
	
	
	public AlarmRingImpl(){
		
	}
	
	public AlarmRingImpl(Time time){
		this.time = time;
	}
	
    public AlarmRingImpl(ArrayList<Time> time){
		this.timesToRing = time;
	}
	
	public boolean shouldRing() {
		
		String currentTime = this.time.getTime();
		System.out.println(time.getTime());
		
		if(timesToRing.isEmpty()){
			throw new RuntimeException("No alarms set");
		}
		
		for(Time time : timesToRing)
			{			
				if(time.getTime().equals(currentTime)){
				
					if(previousRing != currentTime){
						previousRing = currentTime;
						return true;
					}else{
						continue;
					}
				}			
			}
		previousRing = null;
	return false;							
	}
	
	public void addAlarmTime(Time time){
		if(time.getHour()>24 || time.getHour() < 0 || time.getMinute() > 60 || time.getMinute() < 0 ){
			throw new RuntimeException("Time out of range");
		}
		this.timesToRing.add(time);	
	}
	
	public void clearAlramTime(Time time){
		this.timesToRing.remove(time);
	}



}
