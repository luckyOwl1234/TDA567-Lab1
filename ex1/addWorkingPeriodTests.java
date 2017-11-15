import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
        work.addWorkingPeriod("John", 2, 4);
        assertEquals(0, work.readSchedule(2).requiredNumber);
    }

    @Test
    public void addWorkingPeriod_AddWorkerWhenNoneIsNeededReturnValue_ReturnFalse(){
        assertFalse(work.addWorkingPeriod("John", 2, 4));
    }

    @Test
    public void addWorkingPeriod_AddWorkerOnePerson_AddWorker() {
        work.setRequiredNumber(1, 2, 4);
        work.addWorkingPeriod("John", 2, 4);
        assertArrayEquals(new String[]{"John"}, work.readSchedule(2).workingEmployees);
    }

    @Test
    public void addWorkingPeriod_AddWorkerOnePersonReturnValue_ReturnTrue(){
        work.setRequiredNumber(1, 2, 4);
        assertTrue(work.addWorkingPeriod("John", 2, 4));
    }

    @Test
    public void addWorkingPeriod_StartTimeBiggerThanEndTime_DoNotAddWorker() {
        work.setRequiredNumber(1, 2, 4);
        work.addWorkingPeriod("John", 4, 2);
        assertEquals(0, work.readSchedule(4).workingEmployees.length);
    }

    //Bug addWorkingPeriod returns true even if it doesnt add a worker to the schedule
    @Test
    public void addWorkingPeriod_StartTimeBiggerThanEndTimeReturnValue_ReturnFalse(){
        work.setRequiredNumber(1, 2, 4);
        assertFalse(work.addWorkingPeriod("John", 4, 2));
    }

    @Test
    public void addWorkingPeriod_StartTimeLessThanZero_DoNotAddWorker() {
        work.setRequiredNumber(1, 2, 4);
        work.addWorkingPeriod("John", -1, 4);
        Assert.assertEquals(0, work.readSchedule(2).workingEmployees.length);
    }

    @Test
    public void addWorkingPeriod_StartTimeLessThanZeroReturnValue_ReturnFalse() {
        work.setRequiredNumber(1, 2, 4);
        assertFalse(work.addWorkingPeriod("John", -1, 4));
    }

    @Test
    public void addWorkingPeriod_EndTimeHigherThanSize_DoNotAddWorker() {
        work.setRequiredNumber(1, 2, 4);
        work.addWorkingPeriod("John", 2, 25);
        assertEquals(0, work.readSchedule(4).workingEmployees.length);
    }

    @Test
    public void addWorkingPeriod_EndTimeHigherThanSizeReturnValue_ReturnFalse() {
        work.setRequiredNumber(1, 2, 4);
        assertFalse(work.addWorkingPeriod("John", 2, 25));
    }


    @Test
    public void addWorkingPeriod_AddMoreWorkersThanNeeded_DoNotAddWorker() {
        work.setRequiredNumber(1, 2, 4);
        work.addWorkingPeriod("John", 2, 4);
        work.addWorkingPeriod("Mark", 2, 4);
        assertEquals(1, work.readSchedule(2).workingEmployees.length);
    }

    @Test
    public void addWorkingPeriod_AddMoreWorkersThanNeededReturnValue_ReturnFalse() {
        work.setRequiredNumber(1, 2, 4);
        work.addWorkingPeriod("John", 2, 4);
        assertFalse(work.addWorkingPeriod("Mark", 2, 4));
    }

    @Test
    public void addWorkingPeriod_AddFewerWorkersThanNeeded_AddWorker() {
        work.setRequiredNumber(3, 2, 4);
        work.addWorkingPeriod("John", 2, 4);
        work.addWorkingPeriod("Mark", 2, 4);
        assertEquals(2, work.readSchedule(3).workingEmployees.length);
    }

    @Test
    public void addWorkingPeriod_AddFewerWorkersThanNeededReturnValue_ReturnTrue() {
        work.setRequiredNumber(3, 2, 4);
        work.addWorkingPeriod("John", 2, 4);
        assertTrue(work.addWorkingPeriod("Mark", 2, 4));
    }

    @Test
    public void addWorkingPeriod_AddWorkerOverBiggerIntervalThanNeeded_DoNotAddWorker(){
        work.setRequiredNumber(1, 2, 4);
        work.setRequiredNumber(1, 5, 6);
        work.addWorkingPeriod("John", 2, 4);
        work.addWorkingPeriod("Mark", 2, 6);
        assertEquals(0, work.readSchedule(5).workingEmployees.length);
    }

    @Test
    public void addWorkingPeriod_AddWorkerOverBiggerIntervalThanNeededReturnValue_ReturnFalse(){
        work.setRequiredNumber(1, 2, 4);
        work.setRequiredNumber(1, 5, 6);
        work.addWorkingPeriod("John", 2, 4);
        assertFalse(work.addWorkingPeriod("Mark", 2, 6));
    }

    @Test
    public void addWorkingPeriod_StartTimeAndEndTimeIsTheSame_DoNotAddWorker(){
        work.setRequiredNumber(1, 2,2);
        work.addWorkingPeriod("John", 2,2);
        assertEquals(1, work.readSchedule(2).workingEmployees.length);
    }

    @Test
    public void addWorkingPeriod_StartTimeAndEndTimeIsTheSameReturnValue_ReturnTrue(){
        work.setRequiredNumber(1, 2,2);
        assertTrue(work.addWorkingPeriod("John", 2,2));
    }

    @Test
    public void addWorkingPeriod_AddSameWorkerMultipleTimes_DoNotAdd(){
        work.setRequiredNumber(2, 2,4);
        work.addWorkingPeriod("John", 2,4);
        work.addWorkingPeriod("John", 2,4);
        assertEquals(1, work.readSchedule(2).workingEmployees.length);
    }

    @Test
    public void addWorkingPeriod_AddSameWorkerMultipleTimesReturnValue_ReturnFalse(){
        work.setRequiredNumber(2, 2,4);
        work.addWorkingPeriod("John", 2,4);
        assertFalse(work.addWorkingPeriod("John", 2,4));
    }
}
