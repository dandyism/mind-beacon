package freedom.dandyism.mindbeacon;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

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

    private BeaconActivity mActivity;
    private Button mToggleButton;
    private Button mIntervalButton;

    public BeaconActivityTest() {
        super("freedom.dandyism.mindbeacon", BeaconActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        mActivity = getActivity();

        mToggleButton = (Button) mActivity.findViewById(R.id.button_toggle);
        mIntervalButton = (Button) mActivity.findViewById(R.id.button_interval);
    }

    public void testPreConditions() {
        assertEquals(mToggleButton.getText(), "Start Beacon");
        assertEquals(mIntervalButton.getText(), "Set Interval");
    }
}
