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

public class AlarmManagerBroadcastReceiver extends BroadcastReceiver {

    private static boolean dialogIsDisplayed = false;
    private static AlarmManager alarmManager;
    private static PendingIntent alarmIntent;

    public static class ReminderActivity extends Activity {

        /** Called when the activity is first created. */
        @Override
        public void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.reminder);
            dialogIsDisplayed = true;
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

            dialogIsDisplayed = false;
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if ( !dialogIsDisplayed ) {
            Intent i = new Intent(context, ReminderActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
            context.startActivity(i);
        }
    }

    public void setAlarm(Context context, int intervalInMillis) {
        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + intervalInMillis, intervalInMillis, alarmIntent);
    }

    public void unsetAlarm() {
        if ( alarmManager != null && alarmIntent != null ) {
            alarmManager.cancel(alarmIntent);
        }
    }
}
