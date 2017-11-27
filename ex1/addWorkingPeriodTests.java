import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class addWorkingPeriodTests {

    int size;
    WorkSchedule work;

    String[] empty;
    String[] john;
    String[] mark;
    String[] johnMark;

    @Before
    public void setUp() {
        size = 24;
        work = new WorkSchedule(size);

        empty = new String[]{};
        john = new String[]{"John"};
        mark = new String[]{"Mark"};
        johnMark = new String[]{"John", "Mark"};
    }

    @Test
    public void addWorkingPeriod_AddWorkerWhenNoneIsNeeded_DoNotAdd(){
        work.addWorkingPeriod("John", 2, 4);

        // For every hour, 0 to size-1 (0 to 23 in our case)
        for (int i = 0; i < size; i++) {
            // Ensure no one was added to any hour
            if ( !Arrays.equals(work.readSchedule(i).workingEmployees, empty) ) {
                fail();
            }
        }
    }

    @Test
    public void addWorkingPeriod_AddWorkerWhenNoneIsNeededReturnValue_ReturnFalse(){
        assertFalse(work.addWorkingPeriod("John", 2, 4));
    }

    @Test
    public void addWorkingPeriod_AddWorkerOnePerson_AddWorker() {
        work.setRequiredNumber(1, 1, 6);
        work.addWorkingPeriod("John", 2, 5);

        for (int i = 0; i < size; i++) {
            // 0..1..[2..3..4..5]..6..7..
            if ( i >= 2 && i <= 5 ) {
                if ( !Arrays.equals(work.readSchedule(i).workingEmployees, john)) {
                    fail();
                }
            }
            // [0..1]..2..3..4..5..[6..7..
            else {
                if ( !Arrays.equals(work.readSchedule(i).workingEmployees, empty)) {
                    fail();
                }
            }
        }
    }

    @Test
    public void addWorkingPeriod_AddWorkerOnePersonReturnValue_ReturnTrue(){
        work.setRequiredNumber(1, 1, 6);
        assertTrue(work.addWorkingPeriod("John", 2, 5));
    }

    @Test
    public void addWorkingPeriod_StartTimeBiggerThanEndTime_DoNotAddWorker() {
        work.setRequiredNumber(1, 2, 4);
        work.addWorkingPeriod("John", 4, 2);

        for (int i = 0; i < size; i++) {
            if ( !Arrays.equals(work.readSchedule(i).workingEmployees, empty) ) {
                fail();
            }
        }
    }

    // BUG: addWorkingPeriod returns true even if it doesnt add a worker to the schedule
    @Test
    public void addWorkingPeriod_StartTimeBiggerThanEndTimeReturnValue_ReturnFalse(){
        work.setRequiredNumber(1, 2, 4);
        assertFalse(work.addWorkingPeriod("John", 4, 2));
    }

    @Test
    public void addWorkingPeriod_StartTimeLessThanZero_DoNotAddWorker() {
        work.setRequiredNumber(1, 2, 4);
        work.addWorkingPeriod("John", -1, 4);

        for (int i = 0; i < size; i++) {
            if ( !Arrays.equals(work.readSchedule(i).workingEmployees, empty) ) {
                fail();
            }
        }
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

        for (int i = 0; i < size; i++) {
            if ( !Arrays.equals(work.readSchedule(i).workingEmployees, empty) ) {
                fail();
            }
        }
    }

    @Test
    public void addWorkingPeriod_EndTimeHigherThanSizeReturnValue_ReturnFalse() {
        work.setRequiredNumber(1, 2, 4);
        assertFalse(work.addWorkingPeriod("John", 2, 25));
    }


    @Test
    public void addWorkingPeriod_AddMoreWorkersThanNeeded_DoNotAddSecondWorker() {
        work.setRequiredNumber(1, 2, 4);
        work.addWorkingPeriod("John", 2, 4);
        work.addWorkingPeriod("Mark", 2, 4);

        for (int i = 0; i < size; i++) {
            if ( i >= 2 && i <= 4 ) {
                if ( !Arrays.equals(work.readSchedule(i).workingEmployees, john)) {
                    fail();
                }
            }
            else {
                if ( !Arrays.equals(work.readSchedule(i).workingEmployees, empty)) {
                    fail();
                }
            }
        }
    }

    @Test
    public void addWorkingPeriod_AddMoreWorkersThanNeededReturnValue_ReturnFalse() {
        work.setRequiredNumber(1, 2, 4);
        work.addWorkingPeriod("John", 2, 4);
        assertFalse(work.addWorkingPeriod("Mark", 2, 4));
    }

    @Test
    public void addWorkingPeriod_AddFewerWorkersThanNeeded_AddWorkers() {
        work.setRequiredNumber(3, 2, 4);
        work.addWorkingPeriod("John", 2, 4);
        work.addWorkingPeriod("Mark", 2, 4);

        for (int i = 0; i < size; i++) {
            if ( i >= 2 && i <= 4 ) {
                if ( !Arrays.equals(work.readSchedule(i).workingEmployees, johnMark)) {
                    fail();
                }
            }
            else {
                if ( !Arrays.equals(work.readSchedule(i).workingEmployees, empty)) {
                    fail();
                }
            }
        }
    }

    @Test
    public void addWorkingPeriod_AddFewerWorkersThanNeededReturnValue_ReturnTrue() {
        work.setRequiredNumber(3, 2, 4);
        work.addWorkingPeriod("John", 2, 4);
        assertTrue(work.addWorkingPeriod("Mark", 2, 4));
    }

    @Test
    public void addWorkingPeriod_AddWorkerOverBiggerIntervalThanNeeded_DoNotAddWorker(){
        work.setRequiredNumber(1, 5, 6);
        work.addWorkingPeriod("Mark", 2, 6);

        for (int i = 0; i < size; i++) {
            if ( !Arrays.equals(work.readSchedule(i).workingEmployees, empty) ) {
                fail();
            }
        }
    }

    @Test
    public void addWorkingPeriod_AddWorkerOverBiggerIntervalThanNeededReturnValue_ReturnFalse(){
        work.setRequiredNumber(1, 2, 4);
        work.setRequiredNumber(1, 5, 6);
        work.addWorkingPeriod("John", 2, 4);
        assertFalse(work.addWorkingPeriod("Mark", 2, 6));
    }

    @Test
    public void addWorkingPeriod_StartTimeAndEndTimeIsTheSame_AddWorker(){
        work.setRequiredNumber(1, 2, 2);
        work.addWorkingPeriod("John", 2, 2);


        for (int i = 0; i < size; i++) {
            if ( i == 2 ) {
                if ( !Arrays.equals(work.readSchedule(i).workingEmployees, john)) {
                    fail();
                }
            }
            else {
                if ( !Arrays.equals(work.readSchedule(i).workingEmployees, empty)) {
                    fail();
                }
            }
        }
    }

    @Test
    public void addWorkingPeriod_StartTimeAndEndTimeIsTheSameReturnValue_ReturnTrue(){
        work.setRequiredNumber(1, 2,2);
        assertTrue(work.addWorkingPeriod("John", 2,2));
    }

    @Test
    public void addWorkingPeriod_AddSameWorkerMultipleTimes_DoNotAddSecondWorker(){
        work.setRequiredNumber(2, 2, 4);
        work.addWorkingPeriod("John", 2, 4);
        work.addWorkingPeriod("John", 2, 4);

        for (int i = 0; i < size; i++) {
            if ( i >= 2 && i <= 4 ) {
                if ( !Arrays.equals(work.readSchedule(i).workingEmployees, john)) {
                    fail();
                }
            }
            else {
                if ( !Arrays.equals(work.readSchedule(i).workingEmployees, empty)) {
                    fail();
                }
            }
        }
    }

    @Test
    public void addWorkingPeriod_AddSameWorkerMultipleTimesReturnValue_ReturnFalse(){
        work.setRequiredNumber(2, 2,4);
        work.addWorkingPeriod("John", 2,4);
        assertFalse(work.addWorkingPeriod("John", 2,4));
    }
}
