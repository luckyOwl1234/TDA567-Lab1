import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class sectionTests {

    private Set a;
    private Set s;

    @Before
    public void setUp(){
        a = new Set();
        s = new Set();
    }

    @Test
    public void sectionTest(){
        a.insert(1);
        a.insert(2);
        a.insert(3);

        s.insert(3);
        s.insert(4);
        s.insert(5);

        a.section(s);
        assertArrayEquals(new int[]{1}, a.toArray());
    }
}
