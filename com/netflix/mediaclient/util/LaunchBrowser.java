// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import android.util.Log;
import android.content.Intent;
import android.net.Uri;
import android.content.Context;

public class LaunchBrowser implements Runnable
{
    private static final String TAG;
    private Context mContext;
    private Uri mUri;
    
    static {
        TAG = LaunchBrowser.class.getSimpleName();
    }
    
    public LaunchBrowser(final Context mContext, final Uri mUri) {
        if (mUri == null) {
            throw new IllegalArgumentException("Uri can not be null");
        }
        if (mContext == null) {
            throw new IllegalArgumentException("Context can not be null");
        }
        this.mUri = mUri;
        this.mContext = mContext;
    }
    
    public LaunchBrowser(final Context mContext, final String s) {
        if (s == null) {
            throw new IllegalArgumentException("Uri can not be null");
        }
        if (mContext == null) {
            throw new IllegalArgumentException("Context can not be null");
        }
        this.mUri = Uri.parse(s);
        this.mContext = mContext;
    }
    
    @Override
    public void run() {
        final Intent setData = new Intent("android.intent.action.VIEW").setData(this.mUri);
        setData.addFlags(268435456);
        if (setData.resolveActivity(this.mContext.getPackageManager()) != null) {
            this.mContext.getApplicationContext().startActivity(setData);
            return;
        }
        Log.e(LaunchBrowser.TAG, "Unable to launchHelp");
    }
}
