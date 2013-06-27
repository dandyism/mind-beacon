package freedom.dandyism.mindbeacon;

import android.test.ActivityInstrumentationTestCase2;

/**
 * This is a simple framework for a test of an Application.  See
 * {@link android.test.ApplicationTestCase ApplicationTestCase} for more information on
 * how to write and extend Application tests.
 * <p/>
 * To run this test, you can type:
 * adb shell am instrument -w \
 * -e class freedom.dandyism.mindbeacon.BeaconActivityTest \
 * freedom.dandyism.mindbeacon.tests/android.test.InstrumentationTestRunner
 */
public class BeaconActivityTest extends ActivityInstrumentationTestCase2<BeaconActivity> {

    public BeaconActivityTest() {
        super("freedom.dandyism.mindbeacon", BeaconActivity.class);
    }

}
