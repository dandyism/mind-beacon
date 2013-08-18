package freedom.dandyism.mindbeacon;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.content.Context;
import android.widget.Button;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.content.Intent;

public class BeaconActivity extends FragmentActivity
{

    private AlarmManagerBroadcastReceiver alarm;
    private static boolean isStarted = false;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        alarm = new AlarmManagerBroadcastReceiver();
    }

    // Creates our options menu
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public void toggleBeacon(View v) {
        if ( alarm != null ) {
            if ( isStarted == false ) {
                launchBeacon();
            }
            else {
                killBeacon();
            }
        }
    }

    /** Launch the alarm. */
    private void launchBeacon() {
        Context context = getApplicationContext();
        IntervalPicker hour_picker = (IntervalPicker) findViewById(R.id.hour_picker);
        IntervalPicker minute_picker = (IntervalPicker) findViewById(R.id.minute_picker);
        int intervalInMillis = hour_picker.getValue()
                               * 60   // minutes
                               * 60   // seconds
                               * 1000 // milliseconds
                               + minute_picker.getValue()
                               * 60   // seconds
                               * 1000;// milliseconds

        if (alarm != null) {
            alarm.setAlarm(context, intervalInMillis);
            isStarted = true;
            Button toggle_button = (Button) findViewById(R.id.button_toggle);
            toggle_button.setText(R.string.stop_beacon);
        }
        else {
            // TODO
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()) {
            case R.id.settings:
                Intent i = new Intent(BeaconActivity.this, SettingsActivity.class);
                startActivity(i);
                return true;
        }

        return false;
    }

    /** Kill the alarm. */
    private void killBeacon() {
        if ( alarm != null ) {
            alarm.unsetAlarm();
            isStarted = false;
            Button toggle_button = (Button) findViewById(R.id.button_toggle);
            toggle_button.setText(R.string.start_beacon);
        }
    }
}
