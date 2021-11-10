package Phase_1.Alarm;

import java.time.LocalDateTime;
import java.util.*;

/**
 * This interface is created to help manage the AlarmStarter class
 *
 * @author  Owen Huang
 */
public interface AlarmMenu{

    /**
     * Create an Alarm object based on the date and time passed in
     *
     * @param   time the date and time the alarm will go off
     * @return  an Alarm object
     */
    Alarm createAlarm(LocalDateTime time);

    /**
     * Starts the alarm object being passed in and when the time is up,
     * run the Runnable object whenFired
     *
     * @param   alarm an Alarm object that contains the time alarm should go off
     * @param   whenFired when the time is up, or when the alarm goes off, execute this runnable object
     * @return  an TimerTask object that should start the countdown of the alarm in real time
     *
     *  @throws Exception {@inheritDoc}
     *  @throws UnsupportedOperationException {@inheritDoc} when user tries to get an alarm into the past
     */
    TimerTask startAlarm(Alarm alarm, Runnable whenFired) throws Exception;

    /**
     * Cancels the alarm passing in
     *
     * @param   alarm is an Alarm object that user wants to get cancelled
     * @return  true if the alarm is cancelled successfully, false if the alarm does not exist or failed to cancell
     * through other means
     */
    boolean cancelAlarm(Alarm alarm);
}