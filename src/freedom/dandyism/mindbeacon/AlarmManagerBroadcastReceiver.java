package freedom.dandyism.mindbeacon;

import android.content.BroadcastReceiver;
import android.widget.Toast;
import android.content.Context;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.app.Activity;
import android.view.View;
import android.os.Bundle;
import android.media.RingtoneManager;
import android.media.Ringtone;
import android.os.PowerManager;
import android.view.WindowManager;
import android.preference.PreferenceManager;
import android.net.Uri;

public class AlarmManagerBroadcastReceiver extends BroadcastReceiver {

    private static boolean dialogIsDisplayed = false;
    private static AlarmManager alarmManager;
    private static PendingIntent alarmIntent;

    public static class ReminderActivity extends Activity {

        RingtoneManager rm;
        Ringtone ringtone;

        /** Called when the activity is first created. */
        @Override
        public void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.reminder);
            dialogIsDisplayed = true;

            // Play a notification alarm
            Context context = getApplicationContext();
            rm = new RingtoneManager(this);
            // Get the alarm sound from the preferences
            String uri = PreferenceManager.getDefaultSharedPreferences(this)
                    .getString("pref_alarmsound", rm.getDefaultUri(RingtoneManager.TYPE_ALARM).toString());
            ringtone = rm.getRingtone(context, Uri.parse(uri));
            ringtone.play();
        }

        /**
         * Callback for the dismiss button.
         *
         * Shuts down the activity.
         */
        public void dismiss(View v) {
            finish();
        }

        @Override
        protected void onStop() {
            super.onStop();

            // If a dialog ever stops, we don't ever want to see it again.
            finish();
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();

            ringtone.stop();
            dialogIsDisplayed = false;
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if ( !dialogIsDisplayed ) {
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK
                    | PowerManager.ACQUIRE_CAUSES_WAKEUP
                    | PowerManager.ON_AFTER_RELEASE, "reminder");

            wl.acquire();
            Intent i = new Intent(context, ReminderActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
            context.startActivity(i);
            wl.release();
        }
    }

    public void setAlarm(Context context, int intervalInMillis) {
        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, 
                                  System.currentTimeMillis() + intervalInMillis,
                                  intervalInMillis, alarmIntent);
    }

    public void unsetAlarm() {
        if ( alarmManager != null && alarmIntent != null ) {
            alarmManager.cancel(alarmIntent);
        }
    }
}
