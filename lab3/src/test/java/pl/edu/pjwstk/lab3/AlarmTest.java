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
		
		alarmRing.addAlarmTime(10);
		expect(alarmRing.shouldRing(10)).andReturn(true);
		
		EasyMock.replay(alarmRing);
		alarmRing.addAlarmTime(10);
		assertTrue(alarmRing.shouldRing(10));
		verify(alarmRing);
	}
	
	
	@Test
	public void AlarmShouldNotRingTest() {
		AlarmRing alarmRing = EasyMock.createMock(AlarmRing.class);
		
		alarmRing.addAlarmTime(10);
		expect(alarmRing.shouldRing(2)).andReturn(false);
		
		EasyMock.replay(alarmRing);
		alarmRing.addAlarmTime(10);
		assertFalse(alarmRing.shouldRing(2));
		verify(alarmRing);
	}
	
	@Test
	public void AlarmShouldNotRingTwiceTest() {
		AlarmRing alarmRing = EasyMock.createMock(AlarmRing.class);
		
		alarmRing.addAlarmTime(10);
		expect(alarmRing.shouldRing(10)).andReturn(true);
		expect(alarmRing.shouldRing(10)).andReturn(false);
		
		EasyMock.replay(alarmRing);
		alarmRing.addAlarmTime(10);
		assertTrue(alarmRing.shouldRing(10));
		assertFalse(alarmRing.shouldRing(10));
		verify(alarmRing);
	}
	
	@Test
	public void AlarmShouldRingTwiceTest() {
		AlarmRing alarmRing = EasyMock.createMock(AlarmRing.class);
		
		alarmRing.addAlarmTime(10);
		expect(alarmRing.shouldRing(10)).andReturn(true);
		expect(alarmRing.shouldRing(11)).andReturn(false);
		expect(alarmRing.shouldRing(10)).andReturn(true);
		
		EasyMock.replay(alarmRing);
		alarmRing.addAlarmTime(10);
		assertTrue(alarmRing.shouldRing(10));
		assertFalse(alarmRing.shouldRing(11));
		assertTrue(alarmRing.shouldRing(10));
		verify(alarmRing);
	}
	
}
