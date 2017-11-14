import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class memberTests {

    private Set set;

    @Before
    public void setUp(){
        set = new Set();
    }


    @Test
    public void testMemberEmptyArray(){

        assertEquals(0, set.toArray().length);
    }

    @Test
    public void testMemberIn(){
        set.insert(3);
        set.insert(1);
        set.insert(2);
        assertTrue(set.member(3));
    }

    @Test
    public void testMemberNotIn() {
        set.insert(3);
        set.insert(1);
        set.insert(2);
        assertFalse(set.member(5));
    }

    @Test
    public void testMember(){
        set.insert(3);
        assertFalse(set.member(1));
    }

}
