package freedom.dandyism.mindbeacon;

import android.support.v4.app.DialogFragment;
import android.app.TimePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.TimePicker;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new TimePickerDialog(getActivity(), this, 0, 0, true);
    }

    public void onTimeSet(TimePicker view, int hour, int minute) {
        BeaconActivity activity = (BeaconActivity) getActivity();
        activity.hours(hour);
        activity.minutes(minute);
    }
}
