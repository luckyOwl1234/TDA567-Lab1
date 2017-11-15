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
    public void workingEmployees_AddToLimit_ContainCorrectWorkers(){
        work.setRequiredNumber(2, 4,8);
        work.addWorkingPeriod("Linus", 4,6);
        work.addWorkingPeriod("Joakim", 4,5);
        assertArrayEquals(new String[] {"Linus", "Joakim"}, work.readSchedule(4).workingEmployees);
    }

    @Test
    public void workingEmployees_EmptySchedule_IsEmpty(){
        assertEquals(0, work.readSchedule(3).workingEmployees.length);
    }

}
