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
		Time time = new Time(10, 25);
		
		alarmRing.addAlarmTime(time);
		expect(alarmRing.shouldRing(time)).andReturn(true);
		
		EasyMock.replay(alarmRing);
		alarmRing.addAlarmTime(time);
		assertTrue(alarmRing.shouldRing(time));
		verify(alarmRing);
	}
	
	@Test
	public void AlarmShouldNotRingTest() {
		AlarmRing alarmRing = EasyMock.createMock(AlarmRing.class);
		Time time = new Time(10, 25);
		Time time1 = new Time(10, 25);
		
		alarmRing.addAlarmTime(time);
		expect(alarmRing.shouldRing(time1)).andReturn(false);
		
		EasyMock.replay(alarmRing);
		alarmRing.addAlarmTime(time);
		assertFalse(alarmRing.shouldRing(time1));
		verify(alarmRing);
	}
	
	@Test
	public void AlarmShouldNotRingTwiceTest() {
		AlarmRing alarmRing = EasyMock.createMock(AlarmRing.class);
		Time time = new Time(10, 25);
		
		alarmRing.addAlarmTime(time);
		expect(alarmRing.shouldRing(time)).andReturn(true);
		expect(alarmRing.shouldRing(time)).andReturn(false);
		
		EasyMock.replay(alarmRing);
		alarmRing.addAlarmTime(time);
		assertTrue(alarmRing.shouldRing(time));
		assertFalse(alarmRing.shouldRing(time));
		verify(alarmRing);
	}
	
	@Test
	public void AlarmShouldRingTwiceDifferentTimeTest() {
		AlarmRing alarmRing = EasyMock.createMock(AlarmRing.class);
		Time time = new Time(10, 25);
		Time time1 = new Time(11, 40);
		Time time2 = new Time(12, 40);
		
		alarmRing.addAlarmTime(time);
		alarmRing.addAlarmTime(time2);
		expect(alarmRing.shouldRing(time)).andReturn(true);
		expect(alarmRing.shouldRing(time1)).andReturn(false);
		expect(alarmRing.shouldRing(time2)).andReturn(true);
		
		EasyMock.replay(alarmRing);
		alarmRing.addAlarmTime(time);
		alarmRing.addAlarmTime(time2);
		assertTrue(alarmRing.shouldRing(time));
		assertFalse(alarmRing.shouldRing(time1));
		assertTrue(alarmRing.shouldRing(time2));
		verify(alarmRing);
	}
	
	@Test
	public void AlarmShouldRingTwiceSameTimeTest() {
		AlarmRing alarmRing = EasyMock.createMock(AlarmRing.class);
		Time time = new Time(10, 25);
		Time time1 = new Time(11, 40);
		
		alarmRing.addAlarmTime(time);
		expect(alarmRing.shouldRing(time)).andReturn(true);
		expect(alarmRing.shouldRing(time1)).andReturn(false);
		expect(alarmRing.shouldRing(time)).andReturn(true);
		
		EasyMock.replay(alarmRing);
		
		alarmRing.addAlarmTime(time);
		assertTrue(alarmRing.shouldRing(time));
		assertFalse(alarmRing.shouldRing(time1));
		assertTrue(alarmRing.shouldRing(time));
		verify(alarmRing);
	}
	
	@Test
	public void AlarmShouldRingTwiceJUnitTest() {
		ArrayList<Time> timesToRing = new ArrayList<Time>();
		Time time = new Time(10, 25);
		Time time1 = new Time(11, 40);
		Time time2 = new Time(12, 40);
		Time time3 = new Time(15, 40);
		
		AlarmRing alarmRing = new AlarmRingImpl(timesToRing);
			
		alarmRing.addAlarmTime(time);
		alarmRing.addAlarmTime(time1);
		alarmRing.addAlarmTime(time2);
		
		assertEquals(alarmRing.shouldRing(time), true);
		assertEquals(alarmRing.shouldRing(time1), true);
		assertEquals(alarmRing.shouldRing(time3), false);
		assertEquals(alarmRing.shouldRing(time2), true);
			
	}
	
	@Test(expected = RuntimeException.class)
	public void WrongTimeEcxepionThownTest() {
		ArrayList<Time> timesToRing = new ArrayList<Time>();
		Time time = new Time(10, 65);
	
		AlarmRing alarmRing = new AlarmRingImpl(timesToRing);
			
		alarmRing.addAlarmTime(time);			
	}
	
}
