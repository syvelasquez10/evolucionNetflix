// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.statusbar;

import android.annotation.TargetApi;
import android.view.View;
import android.support.v4.view.ViewCompat;
import android.view.View$OnApplyWindowInsetsListener;
import android.app.Activity;

class StatusBarModule$2 implements Runnable
{
    final /* synthetic */ StatusBarModule this$0;
    final /* synthetic */ Activity val$activity;
    final /* synthetic */ boolean val$translucent;
    
    StatusBarModule$2(final StatusBarModule this$0, final Activity val$activity, final boolean val$translucent) {
        this.this$0 = this$0;
        this.val$activity = val$activity;
        this.val$translucent = val$translucent;
    }
    
    @TargetApi(21)
    @Override
    public void run() {
        final View decorView = this.val$activity.getWindow().getDecorView();
        if (this.val$translucent) {
            decorView.setOnApplyWindowInsetsListener((View$OnApplyWindowInsetsListener)new StatusBarModule$2$1(this));
        }
        else {
            decorView.setOnApplyWindowInsetsListener((View$OnApplyWindowInsetsListener)null);
        }
        ViewCompat.requestApplyInsets(decorView);
    }
}
