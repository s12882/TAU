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

/**
 * To jest ta najważniejsza klasa do testów behawioralnych która implementuje opowieść (story)
 *
 * Zobacz, że ta jedna klasa może pasować do wielu opowieści.
 */

public class AlarmRingSteps {
    private AlarmRing alarm;
    Time time;

    @Given("there is the time $alarmTime to ring")
    public void createAlarm(String alarmTime){  
        alarm = new AlarmRingImpl();
        given(time.getTime()).willReturn(alarmTime);
    }

    @When("$alarmTime is set to Alarm Ring")
    public void setAlarm(String alarmTime) {
        	alarm.addAlarmTime(time);
    }

    @Then("Alarm should ring at $alarmTime")
    public void alarmRinged(String alarmTime) {
        assertTrue(alarm.shouldRing());
        verify(time).getTime().equals(alarmTime);
    }

}
