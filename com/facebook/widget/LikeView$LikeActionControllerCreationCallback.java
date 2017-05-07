// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import com.facebook.internal.LikeBoxCountView$LikeBoxCountViewCaretPosition;
import android.app.Activity;
import android.content.res.TypedArray;
import com.facebook.android.R$styleable;
import android.widget.LinearLayout$LayoutParams;
import android.view.View$OnClickListener;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import com.facebook.android.R$color;
import com.facebook.android.R$dimen;
import android.content.Intent;
import com.facebook.internal.Utility;
import android.os.Bundle;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.TextView;
import com.facebook.internal.LikeButton;
import com.facebook.internal.LikeBoxCountView;
import android.widget.LinearLayout;
import android.content.BroadcastReceiver;
import android.widget.FrameLayout;
import com.facebook.internal.LikeActionController;
import com.facebook.internal.LikeActionController$CreationCallback;

class LikeView$LikeActionControllerCreationCallback implements LikeActionController$CreationCallback
{
    private boolean isCancelled;
    final /* synthetic */ LikeView this$0;
    
    private LikeView$LikeActionControllerCreationCallback(final LikeView this$0) {
        this.this$0 = this$0;
    }
    
    public void cancel() {
        this.isCancelled = true;
    }
    
    @Override
    public void onComplete(final LikeActionController likeActionController) {
        if (this.isCancelled) {
            return;
        }
        this.this$0.associateWithLikeActionController(likeActionController);
        this.this$0.updateLikeStateAndLayout();
        this.this$0.creationCallback = null;
    }
}
