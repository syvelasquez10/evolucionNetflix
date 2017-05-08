// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.statusbar;

import android.app.Activity;

class StatusBarModule$3 implements Runnable
{
    final /* synthetic */ StatusBarModule this$0;
    final /* synthetic */ Activity val$activity;
    final /* synthetic */ boolean val$hidden;
    
    StatusBarModule$3(final StatusBarModule this$0, final boolean val$hidden, final Activity val$activity) {
        this.this$0 = this$0;
        this.val$hidden = val$hidden;
        this.val$activity = val$activity;
    }
    
    @Override
    public void run() {
        if (this.val$hidden) {
            this.val$activity.getWindow().addFlags(1024);
            this.val$activity.getWindow().clearFlags(2048);
            return;
        }
        this.val$activity.getWindow().addFlags(2048);
        this.val$activity.getWindow().clearFlags(1024);
    }
}
