package freedom.dandyism.mindbeacon;

import android.test.ActivityInstrumentationTestCase2;
import android.view.KeyEvent;
import android.widget.Button;
import com.jayway.android.robotium.solo.Solo;

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

    private Solo solo;
    private Button mIntervalBttn;
    private Button mDoneBttn;

    public BeaconActivityTest() {
        super("freedom.dandyism.mindbeacon", BeaconActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

        solo = new Solo(getInstrumentation(), getActivity());

        /* FIXME */
        mIntervalBttn = (Button) solo.getCurrentActivity().findViewById(R.id.button_interval);
    }

    /**
     * This is a hack to get around the fact that Solo.clickOnButton() doesn't work.
     *
     * FIXME: Remind me to stab someone for this.
     *
     * @param button Must be initialized in {@link #setUp()}.
     */
    private void clickButton(final Button button) {
        solo.getCurrentActivity().runOnUiThread(new Runnable() {
            public void run() {
                button.requestFocus();
            }
        });

        this.sendKeys(KeyEvent.KEYCODE_DPAD_CENTER);
    }

    /**
     * Checks for all the necessary elements when the app starts.
     */
    public void testPreConditions() {
        assertTrue("There is no button to start the beacon.", solo.searchButton("Start Beacon"));
        assertTrue("There is no button to set the interval.", solo.searchButton("Set Interval"));
    }

    /**
     * Clicking the "Set Interval" button should display a TimePicker.
     */
    public void testShowTimePicker() {
        clickButton(mIntervalBttn);
        assertTrue("The TimePicker dialog is not shown.", solo.waitForFragmentByTag("timePicker"));
    }

    /**
     * Clicking "Done" in the TimePicker dialog should set the interval.
     */
    public void testSetInterval() {
        BeaconActivity activity = (BeaconActivity) solo.getCurrentActivity();
        clickButton(mIntervalBttn);
        solo.clearEditText(0);
        solo.clearEditText(1);
        solo.enterText(0, "1");
        solo.enterText(1, "30");
        mDoneBttn = (Button) solo.getButton("Done");
        clickButton(mDoneBttn);
        assertEquals("The interval hour was not set.", 1, activity.hours());
        assertEquals("The interval minute was not set.", 30, activity.minutes());
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }
}
