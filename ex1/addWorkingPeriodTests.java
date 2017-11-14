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
    public void addWorkingPeriodAddOnePersonTest() {
        work.setRequiredNumber(1, 2, 4);
        work.addWorkingPeriod("John", 2, 4);
        Assert.assertArrayEquals(new String[]{"John"}, work.workingEmployees(2, 4));
    }

    @Test
    public void addWorkingPeriodAddWrongNameTest() {
        work.setRequiredNumber(1, 2, 4);
        work.addWorkingPeriod("John", 2, 4);
        assertFalse("Mark".equals(work.workingEmployees(2, 4)));
        //assertArrayEquals(new String[]{"Mark"}, work.workingEmployees(2,4));
    }

    @Test
    public void addWorkingPeriodStartBiggerEndTest() {
        work.setRequiredNumber(1, 2, 4);
        work.addWorkingPeriod("John", 4, 2);
        Assert.assertEquals(0, work.workingEmployees(2, 4).length);
    }

    @Test
    public void addWorkingPeriodStartLessZeroTest() {
        work.setRequiredNumber(1, 2, 4);
        work.addWorkingPeriod("John", -1, 4);
        Assert.assertEquals(0, work.workingEmployees(2, 4).length);
    }

    @Test
    public void addWorkingPeriodEndMoreSizeTest() {
        work.setRequiredNumber(1, 2, 4);
        work.addWorkingPeriod("John", 2, 25);
        Assert.assertEquals(0, work.workingEmployees(2, 4).length);
    }

    @Test
    public void addWorkingPeriodAddingMoreNeededTest() {
        work.setRequiredNumber(1, 2, 4);
        work.addWorkingPeriod("John", 2, 4);
        work.addWorkingPeriod("Mark", 2, 4);
        Assert.assertEquals(1, work.workingEmployees(2, 4).length);
    }

    @Test
    public void addWorkingPeriodAddingLessNeededTest() {
        work.setRequiredNumber(3, 2, 4);
        work.addWorkingPeriod("John", 2, 4);
        work.addWorkingPeriod("Mark", 2, 4);
        Assert.assertEquals(2, work.workingEmployees(2, 4).length);
    }
}
