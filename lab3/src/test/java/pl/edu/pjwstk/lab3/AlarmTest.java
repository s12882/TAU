package pl.edu.pjwstk.lab3;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import static org.easymock.EasyMock.*;

import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.easymock.TestSubject;
import org.easymock.Mock;
import org.junit.runner.RunWith;

public class AlarmTest {
	
	
	@Before
	public void setUp() {
	    AlarmRing alarmRing = createMock(AlarmRing.class); 
	}

	@Test
	public void AlarmShouldRingTest() {
		AlarmRing alarmRing = EasyMock.createMock(AlarmRing.class);
		
		alarmRing.addAlarmTime(10, 25);
		expect(alarmRing.shouldRing(10, 25)).andReturn(true);
		
		EasyMock.replay(alarmRing);
		alarmRing.addAlarmTime(10, 25);
		assertTrue(alarmRing.shouldRing(10, 25));
		verify(alarmRing);
	}
	
	
	@Test
	public void AlarmShouldNotRingTest() {
		AlarmRing alarmRing = EasyMock.createMock(AlarmRing.class);
		
		alarmRing.addAlarmTime(10, 25);
		expect(alarmRing.shouldRing(12, 0)).andReturn(false);
		
		EasyMock.replay(alarmRing);
		alarmRing.addAlarmTime(10, 25);
		assertFalse(alarmRing.shouldRing(12, 0));
		verify(alarmRing);
	}
	
	@Test
	public void AlarmShouldNotRingTwiceTest() {
		AlarmRing alarmRing = EasyMock.createMock(AlarmRing.class);
		
		alarmRing.addAlarmTime(10, 25);
		expect(alarmRing.shouldRing(10, 25)).andReturn(true);
		expect(alarmRing.shouldRing(10, 25)).andReturn(false);
		
		EasyMock.replay(alarmRing);
		alarmRing.addAlarmTime(10, 25);
		assertTrue(alarmRing.shouldRing(10, 25));
		assertFalse(alarmRing.shouldRing(10, 25));
		verify(alarmRing);
	}
	
	@Test
	public void AlarmShouldRingTwiceTest() {
		AlarmRing alarmRing = EasyMock.createMock(AlarmRing.class);
		
		alarmRing.addAlarmTime(10, 25);
		expect(alarmRing.shouldRing(10, 25)).andReturn(true);
		expect(alarmRing.shouldRing(11, 40)).andReturn(false);
		expect(alarmRing.shouldRing(10, 25)).andReturn(true);
		
		EasyMock.replay(alarmRing);
		alarmRing.addAlarmTime(10, 25);
		assertTrue(alarmRing.shouldRing(10, 25));
		assertFalse(alarmRing.shouldRing(11, 40));
		assertTrue(alarmRing.shouldRing(10, 25));
		verify(alarmRing);
	}
	
	@Test
	public void AlarmShouldRingTwiceJUnitTest() {
		ArrayList<Integer> hour = new ArrayList<Integer>();
		ArrayList<Integer> minute = new ArrayList<Integer>();
		
		AlarmRing alarmRing = new AlarmRingImpl(hour, minute);
			
		alarmRing.addAlarmTime(10, 25);
		assertTrue(alarmRing.shouldRing(10, 25));
		assertFalse(alarmRing.shouldRing(11, 40));
		assertTrue(alarmRing.shouldRing(10, 25));
		
	}
	
}
