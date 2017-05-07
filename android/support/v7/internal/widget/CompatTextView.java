// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.content.res.TypedArray;
import android.text.method.TransformationMethod;
import android.support.v7.internal.text.AllCapsTransformationMethod;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.TextView;

public class CompatTextView extends TextView
{
    public CompatTextView(final Context context) {
        this(context, null);
    }
    
    public CompatTextView(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public CompatTextView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R.styleable.CompatTextView, n, 0);
        final boolean boolean1 = obtainStyledAttributes.getBoolean(R.styleable.CompatTextView_textAllCaps, false);
        obtainStyledAttributes.recycle();
        if (boolean1) {
            this.setTransformationMethod((TransformationMethod)new AllCapsTransformationMethod(context));
        }
    }
}
