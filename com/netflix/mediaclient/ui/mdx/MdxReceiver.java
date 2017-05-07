// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import android.content.IntentFilter;
import com.netflix.mediaclient.ui.common.PinVerifier;
import com.netflix.mediaclient.util.WebApiUtils;
import com.netflix.mediaclient.ui.player.MDXControllerActivity;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.MdxPostplayState;
import com.netflix.mediaclient.util.StringUtils;
import android.content.Context;
import android.content.Intent;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.content.BroadcastReceiver;

public final class MdxReceiver extends BroadcastReceiver
{
    private static final String PIN_TAG = "nf_pin_mdx";
    private static final String TAG = "nf_mdx";
    private final NetflixActivity mActivity;
    
    public MdxReceiver(final NetflixActivity mActivity) {
        this.mActivity = mActivity;
        Log.v("nf_mdx", "Receiver created");
    }
    
    private void cancelPin() {
        this.mActivity.removeVisibleDialog();
    }
    
    private void showMdxController(final Intent intent, final Context context) {
        final String string = intent.getExtras().getString("postplayState");
        if (!StringUtils.isEmpty(string)) {
            final MdxPostplayState mdxPostplayState = new MdxPostplayState(string);
            if (mdxPostplayState.isInCountdown()) {
                final MdxPostplayState.PostplayTitle[] postplayTitle = mdxPostplayState.getPostplayTitle();
                if (postplayTitle.length > 0 && postplayTitle[0].isEpisode() && postplayTitle[0].getId() > 0) {
                    final WebApiUtils.VideoIds videoIds = this.mActivity.getServiceManager().getMdx().getVideoIds();
                    if (videoIds != null && videoIds.episodeId > 0) {
                        MDXControllerActivity.showMDXController(this.mActivity, videoIds.episodeId, PlayContext.DFLT_MDX_CONTEXT);
                    }
                }
            }
        }
    }
    
    private void verifyPinAndNotify(final Intent intent) {
        PinVerifier.getInstance().verify(this.mActivity, true, (PinVerifier.PinVerificationCallback)new PinVerifier.PinVerificationCallback() {
            final /* synthetic */ String val$uuid = intent.getExtras().getString("uuid");
            
            @Override
            public void onPinCancelled() {
                Log.d("nf_pin_mdx", "pinVerification skipped - notify target");
                MdxReceiver.this.mActivity.sendBroadcast(new Intent("com.netflix.mediaclient.intent.action.MDX_PINCANCELLED").addCategory("com.netflix.mediaclient.intent.category.MDX").putExtra("uuid", this.val$uuid));
            }
            
            @Override
            public void onPinVerification(final boolean b) {
                Log.d("nf_pin_mdx", String.format("onPinVerification %b", b));
                if (b) {
                    Log.d("nf_pin_mdx", "notifying target");
                    MdxReceiver.this.mActivity.sendBroadcast(new Intent("com.netflix.mediaclient.intent.action.MDX_PINCONFIRMED").addCategory("com.netflix.mediaclient.intent.category.MDX").putExtra("uuid", this.val$uuid));
                }
            }
        });
    }
    
    public IntentFilter getFilter() {
        Log.v("nf_mdx", "Get filter called");
        final IntentFilter intentFilter = new IntentFilter("com.netflix.mediaclient.intent.action.MDXUPDATE_READY");
        intentFilter.addAction("com.netflix.mediaclient.intent.action.MDXUPDATE_NOTREADY");
        intentFilter.addAction("com.netflix.mediaclient.intent.action.MDXUPDATE_TARGETLIST");
        intentFilter.addAction("com.netflix.mediaclient.intent.action.PIN_VERIFICATION_SHOW");
        intentFilter.addAction("com.netflix.mediaclient.intent.action.PIN_VERIFICATION_NOT_REQUIRED");
        intentFilter.addAction("com.netflix.mediaclient.intent.action.MDXUPDATE_POSTPLAY");
        intentFilter.addAction("com.netflix.mediaclient.intent.action.MDXUPDATE_PLAYBACKEND");
        intentFilter.addAction("com.netflix.mediaclient.intent.action.MDXUPDATE_STATE");
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
                    return;
                }
                if ("com.netflix.mediaclient.intent.action.PIN_VERIFICATION_SHOW".equals(action)) {
                    Log.d("nf_mdx", "MDX PIN show dialog");
                    this.verifyPinAndNotify(intent);
                    return;
                }
                if ("com.netflix.mediaclient.intent.action.PIN_VERIFICATION_NOT_REQUIRED".equals(action)) {
                    Log.d("nf_mdx", "MDX cancel pin dialog - verified on other controller");
                    this.cancelPin();
                    return;
                }
                if ("com.netflix.mediaclient.intent.action.MDXUPDATE_POSTPLAY".equals(action)) {
                    this.showMdxController(intent, context);
                    return;
                }
                if ("com.netflix.mediaclient.intent.action.MDXUPDATE_PLAYBACKEND".equals(action) || "com.netflix.mediaclient.intent.action.MDXUPDATE_STATE".equals(action)) {
                    Log.d("nf_mdx", "MDX playback end/state event");
                    PinVerifier.getInstance().registerPlayStopEvent();
                }
            }
        }
    }
}
