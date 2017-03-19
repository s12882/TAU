package pl.edu.pjwstk.lab3;

public interface AlarmRing {
	
	public boolean shouldRing(int Time);
	public void addAlarmTime(int Time);
	public void clearAlramTime(int Time);

}
