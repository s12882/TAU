package pl.edu.pjwstk.lab3;

import java.util.ArrayList;

public class AlarmRingImpl implements AlarmRing  {
	
	public ArrayList<Integer> TimesToRing;
	private boolean State = true;
	
	public AlarmRingImpl(){
		
	}
	
    public AlarmRingImpl(ArrayList<Integer> TimesToRing ){
		this.TimesToRing = TimesToRing;
	}
	
	public boolean shouldRing(int currentTime) {
			
			for(int time : TimesToRing)
			{
				if(time == currentTime){
					 
					if(State == true){
						State = false;
						return true;
					}else{
						return false;
					}
				}
			}
			State = true;
	return false;
		
								
	}
	
	public void addAlarmTime(int Time){
		this.TimesToRing.add(Time);
	}
	
	public void clearAlramTime(int Time){
		this.TimesToRing.remove(Time);
	}

}
