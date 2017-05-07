// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import android.content.Intent;
import android.content.Context;
import android.content.IntentFilter;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.content.BroadcastReceiver;

public final class MdxReceiver extends BroadcastReceiver
{
    private static final String TAG = "nf_mdx";
    private final NetflixActivity mActivity;
    
    public MdxReceiver(final NetflixActivity mActivity) {
        this.mActivity = mActivity;
        Log.v("nf_mdx", "Receiver created");
    }
    
    public IntentFilter getFilter() {
        Log.v("nf_mdx", "Get filter called");
        final IntentFilter intentFilter = new IntentFilter("com.netflix.mediaclient.intent.action.MDXUPDATE_READY");
        intentFilter.addAction("com.netflix.mediaclient.intent.action.MDXUPDATE_NOTREADY");
        intentFilter.addAction("com.netflix.mediaclient.intent.action.MDXUPDATE_TARGETLIST");
        intentFilter.addCategory("com.netflix.mediaclient.intent.category.MDX");
        return intentFilter;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        if (Log.isLoggable("nf_mdx", 2)) {
            Log.v("nf_mdx", "MDX broadcast " + intent);
        }
        if (!this.mActivity.isFinishing()) {
            final String action = intent.getAction();
            if (action != null) {
                if (!this.mActivity.shouldAddMdxToMenu()) {
                    Log.d("nf_mdx", "Ignore MDX broadcast");
                    return;
                }
                if ("com.netflix.mediaclient.intent.action.MDXUPDATE_NOTREADY".equals(action)) {
                    Log.d("nf_mdx", "MDX is NOT ready, invalidate action bar");
                    this.mActivity.invalidateOptionsMenu();
                    return;
                }
                if ("com.netflix.mediaclient.intent.action.MDXUPDATE_READY".equals(action)) {
                    Log.d("nf_mdx", "MDX is ready, invalidate action bar");
                    this.mActivity.invalidateOptionsMenu();
                    return;
                }
                if ("com.netflix.mediaclient.intent.action.MDXUPDATE_TARGETLIST".equals(action)) {
                    Log.d("nf_mdx", "MDX is ready, got target list update, invalidate action bar");
                    this.mActivity.invalidateOptionsMenu();
                }
            }
        }
    }
}
