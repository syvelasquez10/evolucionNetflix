// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.os.Build$VERSION;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.Spinner;

public class TintSpinner extends Spinner
{
    private static final int[] TINT_ATTRS;
    
    static {
        TINT_ATTRS = new int[] { 16842964, 16843126 };
    }
    
    public TintSpinner(final Context context, final AttributeSet set) {
        this(context, set, 16842881);
    }
    
    public TintSpinner(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        final TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, set, TintSpinner.TINT_ATTRS, n, 0);
        this.setBackgroundDrawable(obtainStyledAttributes.getDrawable(0));
        if (Build$VERSION.SDK_INT >= 16 && obtainStyledAttributes.hasValue(1)) {
            this.setPopupBackgroundDrawable(obtainStyledAttributes.getDrawable(1));
        }
        obtainStyledAttributes.recycle();
    }
}
