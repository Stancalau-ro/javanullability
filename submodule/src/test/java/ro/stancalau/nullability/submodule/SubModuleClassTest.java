package ro.stancalau.nullability.submodule;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

//Run "gradle clean" for the package-info.java files to get generated.
//If you delete generated/ro/stancalau/nullability/submodule/package-info.java,
//you will see that some highlighted errors disappear.
public class SubModuleClassTest {

    private SubModuleClass smc;

    @Before
    public void setUp() throws Exception {
        smc = new SubModuleClass();
    }

    @Test(expected = NullPointerException.class)
    public void accessPublicFields() throws Exception {

        //Below condition should be highlighted as the public field is @Nonnull
        if (smc.nonNullField == null) {
            assertNull(smc.nonNullField);
        }

        //Below condition should NOT be highlighted as the public field is @Nullable
        if (smc.nullableField == null) {
            assertNull(smc.nullableField);
        }

        //Below call on @Nullable field should be marked as potential NPE
        byte[] bytes = smc.nullableField.getBytes(); //Actually throws NPE
    }

    @Test(expected = NullPointerException.class)
    public void getNullFromNonNullMethod() throws Exception {

        //Below condition should be highlighted as "always false"
        if (smc.getNullFromNonNullMethod("") == null) {
            assertTrue(true);
        }

        //The method parameter below should be highlighted as an effective null passed to a @Nonnull formal parameter
        //Also, some IDEs will say the assert always fails. Note how it does NOT fail if using JSR305.
        assertNull(smc.getNullFromNonNullMethod(null));

        //Below statement is not marked in any way.
        byte[] bytes = smc.getNullFromNonNullMethod("").getBytes(); //Actually throws NPE
    }

    @Test(expected = NullPointerException.class)
    public void getNullFromNullableMethod() throws Exception {

        //No highlighting below, all legal.
        if (smc.getNullFromNullableMethod(null) != null) {
            fail("Should be null but isn't");
        }

        //No highlighting below, all legal.
        if (smc.getNullFromNullableMethod("") != null) {
            fail("Should be null but isn't");
        }

        //Below call on @Nullable method should be marked as potential NPE
        byte[] bytes = smc.getNullFromNullableMethod("").getBytes(); //Actually throws NPE
    }
}