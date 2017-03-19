package pl.edu.pjwstk.lab3;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public interface AlarmRing {
	
	public boolean shouldRing(int Hour, int Minute);
	public void addAlarmTime(int Hour, int Minute);
	public void clearAlramTime(int Hour, int Minute);

}
