package ro.stancalau.nullability.submodule;

import javax.annotation.Nullable;

//Run "gradle clean" for the package-info.java files to get generated.
//If you delete generated/ro/stancalau/nullability/submodule/package-info.java,
//you will see that some highlighted errors disappear.
public class SubModuleClass {

    //The IDE should highlight the assignation below as fields are @Nonnull by default
    //To disable the default field contract,
    //remove ElementType.FIELD from ro.stancalau.NotNullByDefault
    public String nonNullField = null;

    //The below field should NOT be highlighted as it is explicitly set to @Nullable
    @Nullable
    public String nullableField = null;

    public String getNullFromNonNullMethod(String param) {

        //The local temp variable should NOT be highlighted as it's explicitly @Nullable
        @Nullable String temp = null;

        //The IDE should highlight the condition as the parameters are @Nonnull by default
        if (param != null) {
            //The IDE should highlight the temp below as the return type is @Nonnull by default
            return temp;
        }

        //Some IDEs don't highlight the null below
        //because this if branch should not happen due to nullability contracts
        //and previous return statement.
        return null;
    }

    @Nullable
    public String getNullFromNullableMethod(@Nullable String param) {

        //The local temp variable should be highlighted as default @Nonnull
        //To disable the local variable default contract,
        //remove ElementType.LOCAL_VARIABLE from ro.stancalau.NotNullByDefault
        String temp = null;

        //The IDE should NOT highlight the condition as the parameter is explicitly @Nullable
        if (param != null) {
            return temp;
        }

        //The IDE should NOT highlight the null below as the return type is explicitly @Nullable
        return null;
    }
}