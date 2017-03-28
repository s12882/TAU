package pl.edu.pjatk.tau.lab4;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class AlarmRingSteps {
	
    private AlarmRing alarm;
    Time time;

    @Given("there is the time $alarmTime to ring")
    public void createAlarm(String alarmTime){  
    	Time timeToRing = new TimeImpl();
 	    timeToRing.setTime(alarmTime);
        alarm = new AlarmRingImpl(timeToRing);
    }

    @When("$alarmTime is set to Alarm Ring")
    public void setAlarm(String alarmTime) {
    	Time timeToRing = new TimeImpl();
 	    timeToRing.setTime(alarmTime);
        alarm.addAlarmTime(timeToRing);
    }

    @Then("Alarm should ring at $alarmTime")
    public void alarmRinged(String alarmTime){
        assertTrue(alarm.shouldRing());
    }

}
