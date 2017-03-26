package pl.edu.pjatk.tau.lab4;

import java.util.ArrayList;


public class AlarmRingImpl implements AlarmRing  {
	
	Time czas;
	private boolean State = true;
	public String previousRing;
	
	ArrayList<Time> timesToRing = new ArrayList<>();
	
	
	public AlarmRingImpl(){
		
	}
	
    public AlarmRingImpl(ArrayList<Time> time){
		this.timesToRing = time;
	}
	
	public boolean shouldRing() {
		
		String currentTime = czas.getTime();
		
		if(timesToRing.isEmpty()){
			throw new RuntimeException("No alarms set");
		}
		
		for(Time time : timesToRing)
			{
				if(time.getTime() == currentTime){
					
						if(previousRing != currentTime){
							previousRing = currentTime;
							return true;
						}else{
							return false;
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
