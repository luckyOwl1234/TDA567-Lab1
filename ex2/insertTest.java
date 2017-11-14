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
    public void testInsertDifferentValues() {
        set.insert(1);
        set.insert(2);
        set.insert(3);
        assertArrayEquals(correctArray, set.toArray());
    }

    @Test
    public void testInsertInverseOrder(){
        set.insert(3);
        set.insert(2);
        set.insert(1);
        assertArrayEquals(correctArray, set.toArray());
    }

    @Test
    public void testInsertSameValues(){
        set.insert(1);
        set.insert(1);
        set.insert(2);
        set.insert(3);
        assertArrayEquals(correctArray, set.toArray());
    }

    @Test
    public void testInsertLargeNumbers(){
        set.insert(Integer.MIN_VALUE);
        set.insert(-1);
        set.insert(0);
        set.insert(1);
        set.insert(Integer.MAX_VALUE);
        assertArrayEquals(new int[] {Integer.MIN_VALUE,-1,0,1,Integer.MAX_VALUE}, set.toArray());
    }

    @Test
    public void testInsertNull(){
        set.insert(Integer.parseInt(null));
        assertNull(set.toArray());
    }

    @Test
    public void testInsertPsuedorandom() {
        set.insert(2);
        set.insert(1);
        set.insert(3);
    }
}
