import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class addWorkingPeriodTests {

    WorkSchedule work;

    @Before
    public void setUp() {
        work = new WorkSchedule(24);
    }

    @Test
    public void addWorkingPeriod_AddWorkerWhenNoneIsNeeded_DoNotAdd(){
        work.addWorkingPeriod("John", 2,4);
        assertEquals(0, work.readSchedule(2).requiredNumber);
    }

    @Test
    public void addWorkingPeriod_AddWorkerOnePerson_AddWorker() {
        work.setRequiredNumber(1, 2, 4);
        work.addWorkingPeriod("John", 2, 4);
        Assert.assertArrayEquals(new String[]{"John"}, work.readSchedule(2).workingEmployees);
    }

    @Test
    public void addWorkingPeriod_StartTimeBiggerThanEndTime_DoNotAddWorker() {
        work.setRequiredNumber(1, 2, 4);
        work.addWorkingPeriod("John", 4, 2);
        Assert.assertEquals(0, work.readSchedule(2).workingEmployees.length);
    }

    @Test
    public void addWorkingPeriod_StartTimeLessThanZero_DoNotAddWorker() {
        work.setRequiredNumber(1, 2, 4);
        work.addWorkingPeriod("John", -1, 4);
        Assert.assertEquals(0, work.readSchedule(2).workingEmployees.length);
    }

    @Test
    public void addWorkingPeriod_EndTimeHigherThanSize_DoNotAddWorker() {
        work.setRequiredNumber(1, 2, 4);
        work.addWorkingPeriod("John", 2, 25);
        Assert.assertEquals(0, work.readSchedule(4).workingEmployees.length);
    }

    @Test
    public void addWorkingPeriod_AddMoreWorkersThanNeeded_DoNotAddWorker() {
        work.setRequiredNumber(1, 2, 4);
        work.addWorkingPeriod("John", 2, 4);
        work.addWorkingPeriod("Mark", 2, 4);
        Assert.assertEquals(1, work.readSchedule(2).workingEmployees.length);
    }

    @Test
    public void addWorkingPeriod_AddWorkerFewerWorkersThanNeeded_AddWorker() {
        work.setRequiredNumber(3, 2, 4);
        work.addWorkingPeriod("John", 2, 4);
        work.addWorkingPeriod("Mark", 2, 4);
        Assert.assertEquals(2, work.readSchedule(3).workingEmployees.length);
    }
}
