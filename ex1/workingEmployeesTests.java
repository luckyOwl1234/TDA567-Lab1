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
    public void workingEmployees_CheckingWorkingEmployeesArrayBelowZero_ReturnEmptyArray(){
        work.setRequiredNumber(1,0,23);
        work.addWorkingPeriod("John", 7,17);
        assertEquals(0, work.workingEmployees(-1, -2).length);
    }

    @Test
    public void workingEmployees_AddWorkersForStartTimeEqualToEndTime_ContainCorrectWorkers(){
        work.setRequiredNumber(2, 5,5);
        work.addWorkingPeriod("John", 5,5);
        assertArrayEquals(work.readSchedule(5).workingEmployees, work.workingEmployees(5,5));
    }

    @Test
    public void workingEmployees_AddWorkersToLimit_ContainCorrectWorkers(){
        work.setRequiredNumber(2, 4,8);
        work.addWorkingPeriod("John", 4,6);
        work.addWorkingPeriod("Mark", 4,5);
        assertArrayEquals(new String[] {"John", "Mark"}, work.workingEmployees(4,6));
    }

    @Test
    public void workingEmployees_EmptySchedule_ReturnEmptyArray(){
        assertEquals(0, work.workingEmployees(0,23).length);
    }

}
