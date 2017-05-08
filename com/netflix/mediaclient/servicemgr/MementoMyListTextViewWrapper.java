// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.TextView;

public class MementoMyListTextViewWrapper extends TextViewWrapper
{
    private TextView label;
    
    public MementoMyListTextViewWrapper(final AddToMyListWrapper addToMyListWrapper, final NetflixActivity netflixActivity, final TextView textView, final TextView textView2, final String s, final VideoType videoType, final int n, final boolean b) {
        super(addToMyListWrapper, netflixActivity, textView, s, videoType, n, b);
    }
    
    @Override
    protected void setAsInList() {
        if (this.textView != null) {
            this.textView.setText(2131297071);
        }
    }
    
    @Override
    protected void setAsNotInList() {
        if (this.textView != null) {
            this.textView.setText(2131297053);
        }
    }
}
