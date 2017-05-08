// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.home;

import android.content.Context;
import com.netflix.mediaclient.util.LaunchBrowser;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import java.io.UnsupportedEncodingException;
import com.netflix.mediaclient.util.LogUtils;
import java.net.URLDecoder;
import android.net.Uri;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.app.DialogFragment;

class AccountHandler$1 implements Runnable
{
    final /* synthetic */ AccountHandler this$0;
    
    AccountHandler$1(final AccountHandler this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.mContext.showDialog(AccountSettingsUnavailableAlertDialog.createAccountSettingsUnavailableAlertDialog(this.this$0.mContext));
    }
}
