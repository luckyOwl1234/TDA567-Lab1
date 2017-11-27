import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class sectionTests {

    private Set arrayWithValuesRemoved;
    private Set arrayThatRemoves;

    @Before
    public void setUp(){
        arrayWithValuesRemoved = new Set();
        arrayThatRemoves = new Set();
    }

    @Test
    public void section_EmptyArrays_DoNothing(){
        arrayWithValuesRemoved.section(arrayThatRemoves);
        assertArrayEquals(new int[]{}, arrayWithValuesRemoved.toArray());
    }

    @Test
    public void section_EffectedArrayEmpty_DoNothing(){
        arrayThatRemoves.insert(1);
        arrayWithValuesRemoved.section(arrayThatRemoves);
        assertArrayEquals(new int[]{}, arrayWithValuesRemoved.toArray());
    }

    @Test
    public void section_EffectingArrayEmpty_DoNothing(){
        arrayWithValuesRemoved.insert(1);
        arrayWithValuesRemoved.section(arrayThatRemoves);
        assertArrayEquals(new int[]{1}, arrayWithValuesRemoved.toArray());
    }

    @Test
    public void section_RemoveValueFromArrayWithOneValue_RemoveValue(){
        arrayWithValuesRemoved.insert(1);
        arrayThatRemoves.insert(1);
        arrayWithValuesRemoved.section(arrayThatRemoves);
        assertArrayEquals(new int[]{}, arrayWithValuesRemoved.toArray());
    }

    @Test
    public void section_RemoveValueInANoneSingleArray_RemoveValue(){
        arrayWithValuesRemoved.insert(1);
        arrayWithValuesRemoved.insert(2);
        arrayWithValuesRemoved.insert(3);

        arrayThatRemoves.insert(2);
        arrayThatRemoves.insert(3);
        arrayThatRemoves.insert(5);

        arrayWithValuesRemoved.section(arrayThatRemoves);
        assertArrayEquals(new int[]{1}, arrayWithValuesRemoved.toArray());
    }

    @Test
    public void section_InvokingObjectValueLargerThanParameter_DoNotRemoveValue() {
        arrayWithValuesRemoved.insert(2);
        arrayThatRemoves.insert(1);

        arrayWithValuesRemoved.section(arrayThatRemoves);
        assertArrayEquals(new int[]{2}, arrayWithValuesRemoved.toArray());
    }
}
