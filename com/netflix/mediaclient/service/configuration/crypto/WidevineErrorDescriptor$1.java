// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.crypto;

import android.app.Activity;
import com.netflix.mediaclient.util.StringUtils;
import android.net.Uri;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$TwoButtonAlertDialogDescriptor;
import com.netflix.mediaclient.util.LaunchBrowser;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$AlertDialogDescriptor;
import android.content.Context;
import com.netflix.mediaclient.service.error.ErrorDescriptor;
import com.netflix.mediaclient.util.AndroidUtils;

class WidevineErrorDescriptor$1 implements Runnable
{
    final /* synthetic */ WidevineErrorDescriptor this$0;
    
    WidevineErrorDescriptor$1(final WidevineErrorDescriptor this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        AndroidUtils.forceStop(this.this$0.mContext);
    }
}
