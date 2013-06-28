package freedom.dandyism.mindbeacon;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;

public class BeaconActivity extends FragmentActivity
{
    private int hours;
    private int minutes;

    public int hours() {
        return hours;
    }

    public void hours(int hours) {
        // TODO: validation
        this.hours = hours;
    }

    public int minutes() {
        return minutes;
    }

    public void minutes(int minutes) {
        // TODO: validation
        this.minutes = minutes;
    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    /**
     * Shows the TimePicker dialog when the "Set Interval" button is clicked.
     */
    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }
}
