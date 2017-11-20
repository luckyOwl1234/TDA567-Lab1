import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class insertTest {

    private Set set;
    private int[] correctArray;

    @Before
    public void setUp(){
        set = new Set();
        correctArray = new int[] {1,2,3};
    }

    @Test
    public void insert_addOneValueIgnoresForLoop_AddValue(){
        set.insert(1);
        assertArrayEquals(new int[]{1}, set.toArray());
    }

    @Test
    public void insert_addOneValueSmallerThanExsistingNumber_AddValue(){
        set.insert(2);
        set.insert(1);
        assertArrayEquals(new int[]{1,2}, set.toArray());
    }

    @Test
    public void insert_addValueBiggerThanExsistingNumber_AddValue(){
        set.insert(1);
        set.insert(2);
        assertArrayEquals(new int[]{1,2}, set.toArray());
    }

    @Test
    public void insert_addAlreadyExsistingValue_DoNotAddDoubleValue(){
        set.insert(1);
        set.insert(1);
        assertArrayEquals(new int[]{1}, set.toArray());
    }

    @Test
    public void insert_addValuesInInverseOrder_AddValues(){
        set.insert(3);
        set.insert(2);
        set.insert(1);
        assertArrayEquals(correctArray, set.toArray());
    }

    @Test
    public void insert_addSameValueAfterArrayAlreadyContainsValues_AddValuesExceptDoubles(){
        set.insert(1);
        set.insert(2);
        set.insert(2);
        set.insert(3);
        assertArrayEquals(correctArray, set.toArray());
    }

    @Test
    public void insert_addAllBorderCaseValues_AddValues(){
        set.insert(Integer.MIN_VALUE);
        set.insert(-1);
        set.insert(0);
        set.insert(1);
        set.insert(Integer.MAX_VALUE);
        assertArrayEquals(new int[] {Integer.MIN_VALUE,-1,0,1,Integer.MAX_VALUE}, set.toArray());
    }

    @Test
    public void insert_addValuesInNotSequentialOrder_AddValues() {
        set.insert(3);
        set.insert(1);
        set.insert(2);
        assertArrayEquals(correctArray, set.toArray());
    }
}
