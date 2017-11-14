import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class insertTest {

    private Set set;

    @Before
    public void setUp(){
        set = new Set();
    }

    @Test
    public void testInsertDifferentValues() {
        set.insert(1);
        set.insert(2);
        assertArrayEquals(new int[] {1,2}, set.toArray());
    }

    @Test
    public void testInsertSameValues(){
        set.insert(3);
        set.insert(1);
        set.insert(2);
        set.insert(2);
        assertArrayEquals(new int[] {1,2,3}, set.toArray());
    }
}
