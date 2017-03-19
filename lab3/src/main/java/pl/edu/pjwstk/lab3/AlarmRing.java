package pl.edu.pjwstk.lab3;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public interface AlarmRing {
	
	public boolean shouldRing(Time currentTime);
	public void addAlarmTime(Time time);
	public void clearAlramTime(Time time);

}
