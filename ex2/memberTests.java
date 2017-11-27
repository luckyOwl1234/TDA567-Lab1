import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class memberTests {

    private Set set;

    @Before
    public void setUp() {
        set = new Set();
    }


    @Test
    public void member_EmptyArray_ReturnFalse() {
        assertFalse(set.member(1));
    }

    @Test
    public void member_ContainsValue_ReturnTrue() {
        set.insert(1);
        assertTrue(set.member(1));
    }

    @Test
    public void member_DoNotContainValue_ReturnFalse() {
        set.insert(1);
        assertFalse(set.member(2));
    }

    @Test
    public void member_DoNotContainValueInverse_ReturnFalse() {
        set.insert(2);
        assertFalse(set.member(1));
    }

    @Test
    public void member_BranchCoverage_ReturnTrue() {
        set.insert(1);
        set.insert(2);
        set.insert(3);
        assertTrue(set.member(3));
    }
}
