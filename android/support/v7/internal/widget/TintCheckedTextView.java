// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.util.AttributeSet;
import android.content.Context;
import android.widget.CheckedTextView;

public class TintCheckedTextView extends CheckedTextView
{
    private static final int[] TINT_ATTRS;
    private final TintManager mTintManager;
    
    static {
        TINT_ATTRS = new int[] { 16843016 };
    }
    
    public TintCheckedTextView(final Context context) {
        this(context, null);
    }
    
    public TintCheckedTextView(final Context context, final AttributeSet set) {
        this(context, set, 16843720);
    }
    
    public TintCheckedTextView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        final TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, set, TintCheckedTextView.TINT_ATTRS, n, 0);
        this.setCheckMarkDrawable(obtainStyledAttributes.getDrawable(0));
        obtainStyledAttributes.recycle();
        this.mTintManager = obtainStyledAttributes.getTintManager();
    }
    
    public void setCheckMarkDrawable(final int n) {
        this.setCheckMarkDrawable(this.mTintManager.getDrawable(n));
    }
}
