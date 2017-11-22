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
        set.insert(1);
        set.insert(300);
        set.insert(68756453);

        assertTrue(set.containsArithTriple());
    }

}
