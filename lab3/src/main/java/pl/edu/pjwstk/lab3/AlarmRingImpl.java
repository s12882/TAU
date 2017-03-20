package pl.edu.pjwstk.lab3;

import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class AlarmRingImpl implements AlarmRing  {
	
	private boolean State = true;
	public ArrayList<Time> timesToRing;
	public Time previousRing;
	
	
	public AlarmRingImpl(){
		
	}
	
    public AlarmRingImpl(ArrayList<Time> time){
		this.timesToRing = time;
	}
	
	public boolean shouldRing(Time currentTime) {
		

		for(Time time : timesToRing)
			{
				if(time == currentTime){
					
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
