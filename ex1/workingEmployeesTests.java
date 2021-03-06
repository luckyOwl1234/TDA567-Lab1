import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class workingEmployeesTests {

    WorkSchedule work;

    @Before
    public void setUp() {
        work = new WorkSchedule(24);
    }

    @Test
    public void workingEmployees_AddWorkersForStartTimeEqualToEndTime_ContainCorrectWorkers() {
        work.setRequiredNumber(2, 5, 5);
        work.addWorkingPeriod("John", 5, 5);

        String[] expected = work.readSchedule(5).workingEmployees;
        String[] actual = work.workingEmployees(5, 5);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void workingEmployees_AddWorkersToLimit_ContainCorrectWorkers() {
        work.setRequiredNumber(2, 4, 8);
        work.addWorkingPeriod("John", 4, 6);
        work.addWorkingPeriod("Mark", 4, 5);

        String[] expected = new String[]{"John", "Mark"};
        String[] actual = work.workingEmployees(4, 6);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void workingEmployees_EmptySchedule_ReturnEmptyArray() {
        int expected = 0;
        int actual = work.workingEmployees(0, 23).length;

        assertEquals(expected, actual);
    }

    @Test
    public void workingEmployees_AllWorkersAtDifferentTimes_AllWorkersAdded() {
        work.setRequiredNumber(4, 0, 23);

        work.addWorkingPeriod("Alfa", 1, 1);
        work.addWorkingPeriod("Bravo", 3, 5);
        work.addWorkingPeriod("Charlie", 5, 8);
        work.addWorkingPeriod("Delta", 9, 23);

        String[] expected = new String[]{"Alfa", "Bravo", "Charlie", "Delta"};
        String[] actual = work.workingEmployees(0, 23);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void workingEmployees_AddWorkerInbetweenShifts_AddWorker() {
        work.setRequiredNumber(1, 0, 4);

        work.addWorkingPeriod("John", 2, 3);

        String[] expected = new String[]{"John"};
        String[] actual = work.workingEmployees(2, 3);

        assertArrayEquals(expected, actual);
    }
}
