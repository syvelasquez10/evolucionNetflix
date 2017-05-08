// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.statusbar;

import android.annotation.TargetApi;
import android.view.View;
import android.app.Activity;

class StatusBarModule$4 implements Runnable
{
    final /* synthetic */ StatusBarModule this$0;
    final /* synthetic */ Activity val$activity;
    final /* synthetic */ String val$style;
    
    StatusBarModule$4(final StatusBarModule this$0, final Activity val$activity, final String val$style) {
        this.this$0 = this$0;
        this.val$activity = val$activity;
        this.val$style = val$style;
    }
    
    @TargetApi(23)
    @Override
    public void run() {
        final View decorView = this.val$activity.getWindow().getDecorView();
        int systemUiVisibility;
        if (this.val$style.equals("dark-content")) {
            systemUiVisibility = 8192;
        }
        else {
            systemUiVisibility = 0;
        }
        decorView.setSystemUiVisibility(systemUiVisibility);
    }
}
