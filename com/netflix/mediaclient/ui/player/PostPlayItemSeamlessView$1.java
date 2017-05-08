// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.model.leafs.PostPlayItem;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.Button;
import android.view.View;
import android.view.View$OnClickListener;

class PostPlayItemSeamlessView$1 implements View$OnClickListener
{
    final /* synthetic */ PostPlayItemSeamlessView this$0;
    
    PostPlayItemSeamlessView$1(final PostPlayItemSeamlessView this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        if (view == this.this$0.mWatchCredits) {
            this.this$0.mWatchCreditsTapped = true;
            this.this$0.mPlayerFragment.getScreen().onTap(true);
        }
    }
}
