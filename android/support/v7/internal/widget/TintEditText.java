// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.util.AttributeSet;
import android.content.Context;
import android.widget.EditText;

public class TintEditText extends EditText
{
    private static final int[] TINT_ATTRS;
    
    static {
        TINT_ATTRS = new int[] { 16842964 };
    }
    
    public TintEditText(final Context context, final AttributeSet set) {
        this(context, set, 16842862);
    }
    
    public TintEditText(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        final TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, set, TintEditText.TINT_ATTRS, n, 0);
        this.setBackgroundDrawable(obtainStyledAttributes.getDrawable(0));
        obtainStyledAttributes.recycle();
    }
}
