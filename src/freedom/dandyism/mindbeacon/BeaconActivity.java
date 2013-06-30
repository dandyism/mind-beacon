package freedom.dandyism.mindbeacon;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.content.Context;

public class BeaconActivity extends FragmentActivity
{

    private AlarmManagerBroadcastReceiver alarm;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        alarm = new AlarmManagerBroadcastReceiver();
    }

    /** Launch the alarm. */
    public void launchBeacon(View v) {
        Context context = getApplicationContext();
        IntervalPicker hour_picker = (IntervalPicker) findViewById(R.id.hour_picker);
        IntervalPicker minute_picker = (IntervalPicker) findViewById(R.id.minute_picker);
        int intervalInMillis = hour_picker.getValue() * 60 * 60 * 1000 + minute_picker.getValue() * 60 * 1000;

        if (alarm != null) {
            alarm.SetAlarm(context, intervalInMillis);
        }
        else {
            // TODO
        }
    }
}
