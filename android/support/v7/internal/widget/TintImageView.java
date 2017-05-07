// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.util.AttributeSet;
import android.content.Context;
import android.widget.ImageView;

public class TintImageView extends ImageView
{
    private static final int[] TINT_ATTRS;
    private final TintManager mTintManager;
    
    static {
        TINT_ATTRS = new int[] { 16842964, 16843033 };
    }
    
    public TintImageView(final Context context) {
        this(context, null);
    }
    
    public TintImageView(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public TintImageView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        final TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, set, TintImageView.TINT_ATTRS, n, 0);
        if (obtainStyledAttributes.length() > 0) {
            if (obtainStyledAttributes.hasValue(0)) {
                this.setBackgroundDrawable(obtainStyledAttributes.getDrawable(0));
            }
            if (obtainStyledAttributes.hasValue(1)) {
                this.setImageDrawable(obtainStyledAttributes.getDrawable(1));
            }
        }
        obtainStyledAttributes.recycle();
        this.mTintManager = obtainStyledAttributes.getTintManager();
    }
    
    public void setImageResource(final int n) {
        this.setImageDrawable(this.mTintManager.getDrawable(n));
    }
}
