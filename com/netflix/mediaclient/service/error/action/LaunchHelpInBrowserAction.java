// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.error.action;

import com.netflix.mediaclient.Log;
import android.net.Uri;
import android.content.Intent;
import com.netflix.mediaclient.util.StringUtils;
import android.app.Activity;

public class LaunchHelpInBrowserAction extends BaseAction
{
    private String mUrl;
    
    public LaunchHelpInBrowserAction(final Activity activity, final String mUrl) {
        super(activity);
        if (StringUtils.isEmpty(mUrl)) {
            throw new IllegalArgumentException("URL can not be null!");
        }
        this.mUrl = mUrl;
    }
    
    @Override
    public void run() {
        final Intent setData = new Intent("android.intent.action.VIEW").setData(Uri.parse(this.mUrl));
        if (setData.resolveActivity(this.mActivity.getPackageManager()) != null) {
            this.mActivity.startActivityForResult(setData, 0);
            return;
        }
        Log.e("ErrorManager", "Unable to launchHelp");
    }
}
