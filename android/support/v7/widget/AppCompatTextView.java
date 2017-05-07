// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.util.AttributeSet;
import android.content.Context;
import android.widget.TextView;

public class AppCompatTextView extends TextView
{
    private AppCompatTextHelper mTextHelper;
    
    public AppCompatTextView(final Context context) {
        this(context, null);
    }
    
    public AppCompatTextView(final Context context, final AttributeSet set) {
        this(context, set, 16842884);
    }
    
    public AppCompatTextView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        (this.mTextHelper = new AppCompatTextHelper(this)).loadFromAttributes(set, n);
    }
    
    public void setTextAppearance(final Context context, final int n) {
        super.setTextAppearance(context, n);
        if (this.mTextHelper != null) {
            this.mTextHelper.onSetTextAppearance(context, n);
        }
    }
}
