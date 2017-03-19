package ro.stancalau.nullability.module;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

//Run "gradle clean" for the package-info.java files to get generated.
//If you delete generated/ro/stancalau/nullability/module/package-info.java,
//you will see that some highlighted errors disappear.
public class ModuleClassTest {

    ModuleClass mc;

    @Before
    public void setUp() throws Exception {
        mc = new ModuleClass();
    }

    @Test(expected = NullPointerException.class)
    public void accessPublicFields() throws Exception {

        //Below condition should be highlighted as the public field is @Nonnull
        if (mc.nonNullField == null) {
            assertNull(mc.nonNullField);
        }

        //Below condition should NOT be highlighted as the public field is @Nullable
        if (mc.nullableField == null) {
            assertNull(mc.nullableField);
        }

        //Below call on @Nullable field should be marked as potential NPE
        byte[] bytes = mc.nullableField.getBytes(); //Actually throws NPE
    }

    @Test(expected = NullPointerException.class)
    public void getNullFromNonNullMethod() throws Exception {

        //Below condition should be highlighted as "always false"
        if (mc.getNullFromNonNullMethod("") == null) {
            assertTrue(true);
        }

        //The method parameter below should be highlighted as an effective null passed to a @Nonnull formal parameter
        //Also, some IDEs will say the assert always fails. Note how it does NOT fail if using JSR305.
        assertNull(mc.getNullFromNonNullMethod(null));

        //Below statement is not marked in any way.
        byte[] bytes = mc.getNullFromNonNullMethod("").getBytes(); //Actually throws NPE
    }

    @Test(expected = NullPointerException.class)
    public void getNullFromNullableMethod() throws Exception {

        //No highlighting below, all legal.
        if (mc.getNullFromNullableMethod(null) != null) {
            fail("Should be null but isn't");
        }

        //No highlighting below, all legal.
        if (mc.getNullFromNullableMethod("") != null) {
            fail("Should be null but isn't");
        }

        //Below call on @Nullable method should be marked as potential NPE
        byte[] bytes = mc.getNullFromNullableMethod("").getBytes(); //Actually throws NPE
    }
}