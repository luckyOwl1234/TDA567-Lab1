import java.util.Random;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class WorkScheduleTest {

    int k = 3;
    WorkSchedule work;

    @Before
    public void setUp(){
        work = new WorkSchedule(24);
    }


/*
    @Test
    public void setRequiredNumber_PositiveTest(){
        work.setRequiredNumber(1, 0, 1);
        assertEquals(1, work.readSchedule(0).requiredNumber);
    }

    /**
     * Bug, program allows negative number of workers to be added to a work period
     */
/*    @Test
    public void setRequiredNumber_NegativeTest(){
        work.setRequiredNumber(-1, 2, 3);
        assertEquals(0, work.readSchedule(2).requiredNumber);
    }

    /**
     * Bug, when starttime is same as endtime, it allows a worker to be added to work period that is zero
     */
  /*  @Test
    public void setRequiredNumber_StartSameEndTest(){
        work.setRequiredNumber(1, 4, 4);
        assertEquals(0, work.readSchedule(4).requiredNumber);
    }

    @Test
    public void setRequiredNumber_StartBiggerThenEndTest(){
        work.setRequiredNumber(1, 6, 5);
        assertEquals(0, work.readSchedule(7).requiredNumber);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void setRequiredNumber_StartOutofBoundsTest(){
        work.setRequiredNumber(1, -1, 8);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void setRequiredNumber_EndOutofBounds(){
        work.setRequiredNumber(1, 1, 25);
    }

*/


    @Test
    public void addWorkingPeriodAddOnePersonTest(){
        work.setRequiredNumber(1, 2,4);
        work.addWorkingPeriod("John", 2,4);
        assertArrayEquals(new String[]{"John"}, work.workingEmployees(2,4));
    }

    @Test
    public void addWorkingPeriodAddWrongNameTest(){
        work.setRequiredNumber(1, 2,4);
        work.addWorkingPeriod("John", 2,4);
        assertArrayEquals(new String[]{"Mark"}, work.workingEmployees(2,4));
    }

    @Test
    public void addWorkingPeriodStartBiggerEndTest(){
        work.setRequiredNumber(1, 4,2);
        work.addWorkingPeriod("John", 2,4);
        assertArrayEquals(new String[]{"John"}, work.workingEmployees(2,4));
    }

    @Test
    public void addWorkingPeriodStartLessZeroTest(){
        work.setRequiredNumber(1, 2,4);
        work.addWorkingPeriod("John", -1,4);
        assertArrayEquals(new String[]{"John"}, work.workingEmployees(2,4));
    }

    @Test
    public void addWorkingPeriodEndMoreSizeTest(){
        work.setRequiredNumber(1, 2,4);
        work.addWorkingPeriod("John", 2,25);
        assertArrayEquals(new String[]{"John"}, work.workingEmployees(2,4));
    }

    @Test
    public void addWorkingPeriodAddingMoreNeededTest(){
        work.setRequiredNumber(1, 2,4);
        work.addWorkingPeriod("John", 2,4);
        work.addWorkingPeriod("Mark", 2,4);
        assertArrayEquals(new String[]{"John"}, work.workingEmployees(2,4));
    }

    @Test
    public void addWorkingPeriodAddingLessNeededTest(){
        work.setRequiredNumber(3, 2,4);
        work.addWorkingPeriod("John", 2,4);
        work.addWorkingPeriod("Mark", 2,4);
        assertArrayEquals(new String[]{"John", "Mark"}, work.workingEmployees(2,4));
    }

    @Test
    public void addWorkingPeriodAddingNoNeededTest(){
        work.setRequiredNumber(2, 2,4);
        work.addWorkingPeriod("John", 2,4);
        work.addWorkingPeriod("Mark", 2,4);
        work.addWorkingPeriod("Linus", 2,4);
        assertArrayEquals(work.workingEmployees(2,4).length, work.readSchedule(2).workingEmployees().length);
    }



    /*
    @Test
    public String[] workingEmployeesTest(){
        return null;
    }

    @Test
    public int nextIncompleteTest(){
        return 0;
    }*/

}
