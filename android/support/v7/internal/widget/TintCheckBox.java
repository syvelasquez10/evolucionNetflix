// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.util.AttributeSet;
import android.content.Context;
import android.widget.CheckBox;

public class TintCheckBox extends CheckBox
{
    private static final int[] TINT_ATTRS;
    private final TintManager mTintManager;
    
    static {
        TINT_ATTRS = new int[] { 16843015 };
    }
    
    public TintCheckBox(final Context context) {
        this(context, null);
    }
    
    public TintCheckBox(final Context context, final AttributeSet set) {
        this(context, set, 16842860);
    }
    
    public TintCheckBox(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        final TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, set, TintCheckBox.TINT_ATTRS, n, 0);
        this.setButtonDrawable(obtainStyledAttributes.getDrawable(0));
        obtainStyledAttributes.recycle();
        this.mTintManager = obtainStyledAttributes.getTintManager();
    }
    
    public void setButtonDrawable(final int n) {
        this.setButtonDrawable(this.mTintManager.getDrawable(n));
    }
}
