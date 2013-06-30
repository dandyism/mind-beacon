package freedom.dandyism.mindbeacon;

import android.content.BroadcastReceiver;
import android.widget.Toast;
import android.content.Context;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;

public class AlarmManagerBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO
        Toast.makeText(context, "Alarm has gone off.", Toast.LENGTH_LONG).show();
    }

    public void SetAlarm(Context context, int intervalInMillis) {
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), intervalInMillis, pi);
    }
}
