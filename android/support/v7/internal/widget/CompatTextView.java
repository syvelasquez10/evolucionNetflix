// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.graphics.Rect;
import android.view.View;
import java.util.Locale;
import android.content.res.TypedArray;
import android.text.method.TransformationMethod;
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
        final boolean boolean1 = obtainStyledAttributes.getBoolean(0, false);
        obtainStyledAttributes.recycle();
        if (boolean1) {
            this.setTransformationMethod((TransformationMethod)new AllCapsTransformationMethod(context));
        }
    }
    
    private static class AllCapsTransformationMethod implements TransformationMethod
    {
        private final Locale mLocale;
        
        public AllCapsTransformationMethod(final Context context) {
            this.mLocale = context.getResources().getConfiguration().locale;
        }
        
        public CharSequence getTransformation(final CharSequence charSequence, final View view) {
            if (charSequence != null) {
                return charSequence.toString().toUpperCase(this.mLocale);
            }
            return null;
        }
        
        public void onFocusChanged(final View view, final CharSequence charSequence, final boolean b, final int n, final Rect rect) {
        }
    }
}
