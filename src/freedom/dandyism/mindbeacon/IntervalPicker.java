package freedom.dandyism.mindbeacon;

import android.widget.NumberPicker;
import android.content.Context;
import android.util.AttributeSet;

public class IntervalPicker extends NumberPicker {
    
    public IntervalPicker(Context context) {
        super(context);
    }

    public IntervalPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        processAttributeSet(attrs);
    }

    public IntervalPicker(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        processAttributeSet(attrs);
    }

    private void processAttributeSet(AttributeSet attrs) {
        this.setMinValue(attrs.getAttributeIntValue(null, "min", 0));
        this.setMaxValue(attrs.getAttributeIntValue(null, "max", 0));
    }
}
