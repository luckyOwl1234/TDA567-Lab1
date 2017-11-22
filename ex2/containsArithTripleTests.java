import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;


public class containsArithTripleTests {

    private Set set;

    @Before
    public void setUp(){
        set = new Set();
        //correctArray = new int[] {1,2,3};
    }

    @Test
    public void containsArithTriple_MinimumValues_ReturnTrue(){
        set.insert(0);
        set.insert(1);
        set.insert(2);

        assertTrue(set.containsArithTriple());
    }

    @Test
    public void containsArithTriple_BadValues_ReturnFalse(){
        set.insert(1);
        set.insert(2);
        set.insert(4);

        assertFalse(set.containsArithTriple());
    }

    @Test
    public void containsArithTriple_InsertOneValue_ReturnFalse(){
        set.insert(1);

        assertFalse(set.containsArithTriple());
    }

    @Test
    public void containsArithTriple_NoValue_ReturnFalse(){
        assertFalse(set.containsArithTriple());
    }

}
