package pl.edu.pjatk.tau.lab4;

public interface AlarmRing {
	
	public boolean shouldRing();
	public void addAlarmTime(Time time);
	public void clearAlramTime(Time time);

}
