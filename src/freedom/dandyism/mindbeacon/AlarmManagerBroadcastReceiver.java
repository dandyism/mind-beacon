package freedom.dandyism.mindbeacon;

import android.content.BroadcastReceiver;
import android.widget.Toast;
import android.content.Context;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.app.Activity;

public class AlarmManagerBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, ReminderActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

    public void SetAlarm(Context context, int intervalInMillis) {
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), intervalInMillis, pi);
    }
}
