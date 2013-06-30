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

    public BeaconActivityTest() {
        super("freedom.dandyism.mindbeacon", BeaconActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

        solo = new Solo(getInstrumentation(), getActivity());
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
        assertTrue("There is no button to start the beacon.", solo.searchButton(solo.getString(R.string.tgglbttn_text)));
        assertTrue("There is no hour picker.", solo.waitForView(solo.getView(R.id.hour_picker)));
        assertTrue("There is no minute picker.", solo.waitForView(solo.getView(R.id.minute_picker)));
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }
}
