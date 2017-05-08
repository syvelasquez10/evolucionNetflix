// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import android.view.View$OnClickListener;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import android.widget.TextView;
import com.netflix.mediaclient.android.activity.NetflixActivity;

class TextViewWrapper implements AddToListData$StateListener
{
    private final NetflixActivity activity;
    private final AddToMyListWrapper addToMyListWrapper;
    private final boolean keepVisibilityState;
    protected final TextView textView;
    private final int trackId;
    private final String videoId;
    private final VideoType videoType;
    
    public TextViewWrapper(final AddToMyListWrapper addToMyListWrapper, final NetflixActivity activity, final TextView textView, final String videoId, final VideoType videoType, final int trackId, final boolean keepVisibilityState) {
        this.addToMyListWrapper = addToMyListWrapper;
        this.activity = activity;
        this.textView = textView;
        this.videoId = videoId;
        this.videoType = videoType;
        this.trackId = trackId;
        this.keepVisibilityState = keepVisibilityState;
    }
    
    protected void hide() {
        this.textView.setVisibility(8);
    }
    
    protected void setAsInList() {
        this.textView.setText((CharSequence)this.activity.getString(2131296692, new Object[] { "\u2212" }));
    }
    
    protected void setAsNotInList() {
        this.textView.setText((CharSequence)this.activity.getString(2131296692, new Object[] { "+" }));
    }
    
    protected void show() {
        this.textView.setVisibility(0);
    }
    
    @Override
    public void update(final AddToListData$AddToListState addToListData$AddToListState) {
        switch (TextViewWrapper$3.$SwitchMap$com$netflix$mediaclient$servicemgr$AddToListData$AddToListState[addToListData$AddToListState.ordinal()]) {
            case 1: {
                this.textView.setContentDescription((CharSequence)this.activity.getString(2131296773));
                this.textView.setEnabled(true);
                this.setAsInList();
                this.textView.setOnClickListener((View$OnClickListener)new TextViewWrapper$1(this));
                break;
            }
            case 2: {
                this.textView.setContentDescription((CharSequence)this.activity.getString(2131296533));
                this.textView.setEnabled(true);
                this.setAsNotInList();
                this.textView.setOnClickListener((View$OnClickListener)new TextViewWrapper$2(this));
                break;
            }
            case 3: {
                this.textView.setEnabled(false);
                break;
            }
        }
        if (!this.keepVisibilityState) {
            if (!this.addToMyListWrapper.serviceMan.isCurrentProfileIQEnabled()) {
                this.hide();
                return;
            }
            this.show();
        }
    }
}
