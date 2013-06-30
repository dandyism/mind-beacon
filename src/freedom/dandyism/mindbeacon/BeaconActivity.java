package freedom.dandyism.mindbeacon;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;

public class BeaconActivity extends FragmentActivity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}
