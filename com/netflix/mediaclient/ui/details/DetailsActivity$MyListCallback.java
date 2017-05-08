// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.Log;
import android.content.Context;
import android.widget.Toast;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class DetailsActivity$MyListCallback extends LoggingManagerCallback
{
    final /* synthetic */ DetailsActivity this$0;
    
    public DetailsActivity$MyListCallback(final DetailsActivity this$0, final String s) {
        this.this$0 = this$0;
        super(s);
    }
    
    @Override
    public void onQueueAdd(final Status status) {
        super.onQueueAdd(status);
        int n = 2131231072;
        if (CommonStatus.OK == status) {
            n = 2131231129;
        }
        else if (status.getStatusCode() == StatusCode.ALREADY_IN_QUEUE) {
            n = 2131231227;
        }
        else if (status.getStatusCode() == StatusCode.NOT_VALID) {
            n = 2131231071;
        }
        Toast.makeText((Context)this.this$0, n, 1).show();
    }
    
    @Override
    public void onQueueRemove(final Status status) {
        int n = 2131231130;
        super.onQueueRemove(status);
        if (CommonStatus.OK != status) {
            if (status.getStatusCode() == StatusCode.NOT_IN_QUEUE) {
                Log.w("DetailsActivity", "It was already removed");
            }
            else {
                n = 2131231074;
            }
        }
        Toast.makeText((Context)this.this$0, n, 1).show();
    }
}
