// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget.advisor;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;

class Advisor$4 implements ViewTreeObserver$OnGlobalLayoutListener
{
    final /* synthetic */ Advisor this$0;
    final /* synthetic */ View val$view;
    
    Advisor$4(final Advisor this$0, final View val$view) {
        this.this$0 = this$0;
        this.val$view = val$view;
    }
    
    @SuppressLint({ "NewApi" })
    public void onGlobalLayout() {
        this.this$0.popupWindow.update((int)this.val$view.getX(), (int)this.val$view.getY(), this.this$0.popupWindow.getWidth(), this.this$0.popupWindow.getHeight());
    }
}
