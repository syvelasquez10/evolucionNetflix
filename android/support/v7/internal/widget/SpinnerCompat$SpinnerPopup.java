// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.widget.ListAdapter;
import android.graphics.drawable.Drawable;

interface SpinnerCompat$SpinnerPopup
{
    void dismiss();
    
    Drawable getBackground();
    
    CharSequence getHintText();
    
    int getHorizontalOffset();
    
    int getVerticalOffset();
    
    boolean isShowing();
    
    void setAdapter(final ListAdapter p0);
    
    void setBackgroundDrawable(final Drawable p0);
    
    void setHorizontalOffset(final int p0);
    
    void setPromptText(final CharSequence p0);
    
    void setVerticalOffset(final int p0);
    
    void show();
}
