// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import com.facebook.internal.LikeBoxCountView$LikeBoxCountViewCaretPosition;
import android.app.Activity;
import com.facebook.internal.LikeActionController$CreationCallback;
import android.content.res.TypedArray;
import com.facebook.android.R$styleable;
import android.widget.LinearLayout$LayoutParams;
import android.view.View$OnClickListener;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import com.facebook.android.R$color;
import com.facebook.android.R$dimen;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.util.AttributeSet;
import android.widget.TextView;
import com.facebook.internal.LikeButton;
import com.facebook.internal.LikeBoxCountView;
import com.facebook.internal.LikeActionController;
import android.widget.LinearLayout;
import android.widget.FrameLayout;
import android.os.Bundle;
import com.facebook.internal.Utility;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class LikeView$LikeControllerBroadcastReceiver extends BroadcastReceiver
{
    final /* synthetic */ LikeView this$0;
    
    private LikeView$LikeControllerBroadcastReceiver(final LikeView this$0) {
        this.this$0 = this$0;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        final boolean b = true;
        final String action = intent.getAction();
        final Bundle extras = intent.getExtras();
        boolean b2 = b;
        if (extras != null) {
            final String string = extras.getString("com.facebook.sdk.LikeActionController.OBJECT_ID");
            b2 = b;
            if (!Utility.isNullOrEmpty(string)) {
                b2 = (Utility.areObjectsEqual(this.this$0.objectId, string) && b);
            }
        }
        if (b2) {
            if ("com.facebook.sdk.LikeActionController.UPDATED".equals(action)) {
                this.this$0.updateLikeStateAndLayout();
                return;
            }
            if ("com.facebook.sdk.LikeActionController.DID_ERROR".equals(action)) {
                if (this.this$0.onErrorListener != null) {
                    this.this$0.onErrorListener.onError(extras);
                }
            }
            else if ("com.facebook.sdk.LikeActionController.DID_RESET".equals(action)) {
                this.this$0.setObjectIdForced(this.this$0.objectId);
                this.this$0.updateLikeStateAndLayout();
            }
        }
    }
}
