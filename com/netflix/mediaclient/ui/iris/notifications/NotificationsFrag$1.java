// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iris.notifications;

import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;

class NotificationsFrag$1 implements ErrorWrapper$Callback
{
    final /* synthetic */ NotificationsFrag this$0;
    
    NotificationsFrag$1(final NotificationsFrag this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onRetryRequested() {
        this.this$0.refresh();
    }
}
