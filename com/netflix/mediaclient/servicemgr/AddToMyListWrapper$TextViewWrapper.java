// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import android.content.Context;
import android.widget.Toast;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.Log;
import android.view.View$OnClickListener;
import android.widget.TextView;
import com.netflix.mediaclient.android.activity.NetflixActivity;

class AddToMyListWrapper$TextViewWrapper implements AddToListData$StateListener
{
    private final NetflixActivity activity;
    private final boolean keepVisibilityState;
    private final TextView textView;
    final /* synthetic */ AddToMyListWrapper this$0;
    private final int trackId;
    private final String videoId;
    
    public AddToMyListWrapper$TextViewWrapper(final AddToMyListWrapper this$0, final NetflixActivity activity, final TextView textView, final String videoId, final int trackId, final boolean keepVisibilityState) {
        this.this$0 = this$0;
        this.activity = activity;
        this.textView = textView;
        this.videoId = videoId;
        this.trackId = trackId;
        this.keepVisibilityState = keepVisibilityState;
    }
    
    @Override
    public void update(final AddToListData$AddToListState addToListData$AddToListState) {
        switch (AddToMyListWrapper$1.$SwitchMap$com$netflix$mediaclient$servicemgr$AddToListData$AddToListState[addToListData$AddToListState.ordinal()]) {
            case 1: {
                this.textView.setContentDescription((CharSequence)this.activity.getString(2131492976));
                this.textView.setText((CharSequence)this.activity.getString(2131492973, new Object[] { "\u2212" }));
                this.textView.setEnabled(true);
                this.textView.setOnClickListener((View$OnClickListener)new AddToMyListWrapper$TextViewWrapper$1(this));
                break;
            }
            case 2: {
                this.textView.setContentDescription((CharSequence)this.activity.getString(2131492975));
                this.textView.setText((CharSequence)this.activity.getString(2131492973, new Object[] { "+" }));
                this.textView.setEnabled(true);
                this.textView.setOnClickListener((View$OnClickListener)new AddToMyListWrapper$TextViewWrapper$2(this));
                break;
            }
            case 3: {
                this.textView.setEnabled(false);
                break;
            }
        }
        if (!this.keepVisibilityState) {
            if (!this.this$0.serviceMan.isCurrentProfileIQEnabled()) {
                this.textView.setVisibility(8);
                return;
            }
            this.textView.setVisibility(0);
        }
    }
}
