package pl.edu.pjatk.tau.lab4;

import org.junit.runner.RunWith;
import static org.junit.Assert.*;

import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

import junit.framework.TestCase;

@RunWith(MockitoJUnitRunner.class)
public class AlarmRingTest extends TestCase {

	
	Time time = mock(Time.class);
	
	AlarmRing alarm = spy(new AlarmRingImpl());
		
	@Test
    public void ShouldRingTest(){
        when(time.getTime()).thenReturn("9:00").thenReturn("10:00");
        AlarmRing c = new AlarmRingImpl();
        c.addAlarmTime(time);
        assertTrue(c.shouldRing());
        verify(time,times(2)).getTime();
    }
	
	
}
