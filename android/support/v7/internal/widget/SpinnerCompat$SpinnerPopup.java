// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.widget.ListAdapter;

interface SpinnerCompat$SpinnerPopup
{
    void dismiss();
    
    boolean isShowing();
    
    void setAdapter(final ListAdapter p0);
    
    void setPromptText(final CharSequence p0);
    
    void show();
}
