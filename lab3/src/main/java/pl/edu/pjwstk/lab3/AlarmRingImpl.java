package pl.edu.pjwstk.lab3;

import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class AlarmRingImpl implements AlarmRing  {
	
	public ArrayList<Integer> HourToRing;
	public ArrayList<Integer> MinuteToRing;
	private boolean State = true;
	
	
	public AlarmRingImpl(){
		
	}
	
    public AlarmRingImpl(ArrayList<Integer> HourToRing, ArrayList<Integer> MinuteToRing ){
		this.HourToRing = HourToRing;
		this.MinuteToRing = MinuteToRing;
	}
	
	public boolean shouldRing(int currentHour, int currentMinute) {
		
		if(currentHour>23 || currentMinute>60 || currentMinute<0 || currentHour<0){
			throw new RuntimeException("Out of range");
		}
			
			for(int hour : HourToRing)
			{
				if(hour == currentHour){
					
					for(int minute : MinuteToRing){
						
						if(minute == currentMinute){
				 
							if(State == true){
								State = false;
								return true;
							}else{
								return false;
							}
						}
					}
				}
			}
			State = true;
	return false;							
	}
	
	public void addAlarmTime(int Hour, int Minute){
		if(Hour>23 || Minute>60 || Minute<0 || Hour<0){
			throw new RuntimeException("Out of range");
		}
		this.HourToRing.add(Hour);
		this.MinuteToRing.add(Minute);
	}
	
	public void clearAlramTime(int Hour, int Minute){
		if(Hour>23 || Minute>60 || Minute<0 || Hour<0){
			throw new RuntimeException("Out of range");
		}
		this.HourToRing.remove(Hour);
		this.MinuteToRing.remove(Minute);
	}

}
