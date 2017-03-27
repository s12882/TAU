package pl.edu.pjatk.tau.lab4;

import org.junit.runner.RunWith;


import static org.junit.Assert.*;

import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;


import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

import junit.framework.TestCase;

@RunWith(MockitoJUnitRunner.class)
public class AlarmRingTest extends TestCase {

	
	Time time = mock(Time.class);
	
	AlarmRing alarm = spy(new AlarmRingImpl());
		
	@Test
    public void AlarmWorksTest(){
		alarm = new AlarmRingImpl(new TimeImpl("9:00"));	
        when(time.getTime()).thenReturn("9:00");

        alarm.addAlarmTime(time);
        assertTrue(alarm.shouldRing());
        assertFalse(alarm.shouldRing());
    }
	
	     
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
