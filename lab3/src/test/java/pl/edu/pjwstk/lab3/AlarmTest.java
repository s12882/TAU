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
	public void AlarmShouldRingTwiceTest() {
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
		
		AlarmRing alarmRing = new AlarmRingImpl(timesToRing);
			
		alarmRing.addAlarmTime(time);
		assertTrue(alarmRing.shouldRing(time));
		assertFalse(alarmRing.shouldRing(time1));
		assertTrue(alarmRing.shouldRing(time));
			
	}
	
}
