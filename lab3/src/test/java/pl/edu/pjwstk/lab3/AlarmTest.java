package pl.edu.pjwstk.lab3;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import static org.easymock.EasyMock.*;

import org.easymock.EasyMock;
import org.easymock.EasyMockRule;
import org.easymock.EasyMockRunner;
import org.easymock.TestSubject;
import org.easymock.Mock;
import org.easymock.MockType;
import org.junit.runner.RunWith;

public class AlarmTest {
	
	    @TestSubject
	    public AlarmRing alarm = new AlarmRingImpl();

	    @Mock
	    ITime time;
	    
	    
	    @Test (expected = RuntimeException.class)
	    public void AlarmshouldRingTest(){
	    	
	    	Time time = EasyMock.createMock(Time.class);
	    	Time time1 = new Time(9, 30);
	    	Time time2 = new Time(10, 00);
	    	
	        expect(time.getTime()).
	                andReturn(time1).times(4).
	                andReturn(time2);
	        replay(time);

	        assertEquals(alarm.shouldRing(time1), false);
	        alarm.addAlarmTime(time1);
	        assertEquals(alarm.shouldRing(time1), true);
	        assertEquals(alarm.shouldRing(time1), false);
	        assertEquals(alarm.shouldRing(time1), false);

	        alarm.addAlarmTime(time2);

	        assertEquals(alarm.shouldRing(time2), true);
	        verify(time);
	    }    
	    	
	
	@Test (expected = RuntimeException.class)
	public void AlarmShouldNotRingTest() {
		
		Time time = EasyMock.createMock(Time.class);
    	Time time1 = new Time(9, 30);
    	Time time2 = new Time(10, 00);
    	
        expect(time.getTime()).
                andReturn(time2).andReturn(time2);
        replay(time);

       
        alarm.addAlarmTime(time1);
        assertEquals(alarm.shouldRing(time2), false);

        alarm.addAlarmTime(time2);

        assertEquals(alarm.shouldRing(time2), true);
        verify(time);
	}
	
}
