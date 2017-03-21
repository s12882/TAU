package pl.edu.pjwstk.lab3;

import java.util.ArrayList;

public class Time implements ITime {
	
	public int HourToRing;
	public int MinuteToRing;
	
	public Time(){
		
	}
	
	public Time(int HourToRing, int MinuteToRing){
		this.HourToRing = HourToRing;
		this.MinuteToRing= MinuteToRing;	
	}
		
	public int getHour(){
		return HourToRing;
	}
	
	public int getMinute(){
		return MinuteToRing;
	}

	@Override
	public Time getTime() {
		return this;
	}

}
