// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.kong.postplay;

import com.netflix.mediaclient.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View$OnTouchListener;

class KongBackgroundScreen$1 implements View$OnTouchListener
{
    final /* synthetic */ KongBackgroundScreen this$0;
    final /* synthetic */ View val$kongPostPlayContainer;
    
    KongBackgroundScreen$1(final KongBackgroundScreen this$0, final View val$kongPostPlayContainer) {
        this.this$0 = this$0;
        this.val$kongPostPlayContainer = val$kongPostPlayContainer;
    }
    
    public boolean onTouch(final View view, final MotionEvent motionEvent) {
        if (this.this$0.playerFragment != null) {
            this.this$0.playerFragment.setUserInteraction();
        }
        if (this.val$kongPostPlayContainer.getVisibility() == 0) {
            Log.d("KongBackgroundScreen", "Hijacking tap when post play is in visible state");
            return true;
        }
        return false;
    }
}
