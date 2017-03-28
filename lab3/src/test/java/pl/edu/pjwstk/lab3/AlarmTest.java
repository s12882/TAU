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
	    public void AlarmShouldRingTest(){
	    	
	    	Time timeToRing = mock(Time.class);
	    	timeToRing.setTime("10:00");
	    
	        expect(time.getTime())
	        	.andReturn("10:00")
	        	.andReturn("10:00")
	        	.andReturn("10:00")
	        	.andReturn("11:00")
	        	.andReturn("10:00");
	              
	        replay(time);

	        assertEquals(alarm.shouldRing(), false);
	    	alarm.addAlarmTime(timeToRing);
	        assertEquals(alarm.shouldRing(), true);
	        assertEquals(alarm.shouldRing(), false);
	        assertEquals(alarm.shouldRing(), false);
	        assertEquals(alarm.shouldRing(), true);
	        
	        verify(time);
	    }    
	
}
