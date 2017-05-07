// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;

class UserAgent$DoDummyWebCallTask$1 extends SimpleUserAgentWebCallback
{
    final /* synthetic */ UserAgent$DoDummyWebCallTask this$1;
    
    UserAgent$DoDummyWebCallTask$1(final UserAgent$DoDummyWebCallTask this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void onDummyWebCallDone(final Status status) {
        if (this.this$1.mCallback != null) {
            this.this$1.mCallback.onDummyWebCallDone(status);
            this.this$1.mCallback = null;
        }
        Log.d("nf_service_useragent", "dummy web call done");
    }
}
