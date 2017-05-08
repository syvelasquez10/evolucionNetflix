// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.TextView;

public class KubrickTextViewWrapper extends TextViewWrapper
{
    private TextView label;
    
    public KubrickTextViewWrapper(final AddToMyListWrapper addToMyListWrapper, final NetflixActivity netflixActivity, final TextView textView, final TextView label, final String s, final VideoType videoType, final int n, final boolean b) {
        super(addToMyListWrapper, netflixActivity, textView, s, videoType, n, b);
        this.label = label;
    }
    
    @Override
    protected void hide() {
        if (this.textView != null) {
            this.textView.setVisibility(8);
        }
        if (this.label != null) {
            this.label.setVisibility(8);
        }
    }
    
    @Override
    protected void setAsInList() {
        if (this.textView != null) {
            this.textView.setText(2131231330);
        }
    }
    
    @Override
    protected void setAsNotInList() {
        if (this.textView != null) {
            this.textView.setText(2131231314);
        }
    }
    
    @Override
    protected void show() {
        if (this.textView != null) {
            this.textView.setVisibility(0);
        }
        if (this.label != null) {
            this.label.setVisibility(0);
        }
    }
}
