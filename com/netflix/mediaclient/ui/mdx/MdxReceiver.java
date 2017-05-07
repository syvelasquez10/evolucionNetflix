// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;
import android.text.TextUtils;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.details.PostPlayVideo;
import java.util.List;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;
import com.netflix.mediaclient.servicemgr.IMdxSharedState;
import android.content.IntentFilter;
import com.netflix.mediaclient.ui.pin.PinDialogVault;
import com.netflix.mediaclient.service.mdx.MdxAgent;
import com.netflix.mediaclient.util.StringUtils;
import android.content.Intent;
import com.netflix.mediaclient.util.WebApiUtils;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.MdxPostplayState;
import com.netflix.mediaclient.ui.player.MDXControllerActivity;
import android.content.Context;
import com.netflix.mediaclient.ui.pin.PinVerifier;
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
    
    private void cancelPin() {
        Log.d("nf_pin", "cancelPin on PIN_VERIFICATION_NOT_REQUIRED");
        PinVerifier.getInstance().dismissPinVerification();
    }
    
    private void hideMdxController(final Context context) {
        MDXControllerActivity.finishMDXController(context);
    }
    
    private void showFirstEpisodeInNextSeries(final MdxPostplayState mdxPostplayState) {
        final WebApiUtils.VideoIds videoIds = this.mActivity.getServiceManager().getMdx().getVideoIds();
        if (videoIds != null && videoIds.episodeId > 0) {
            this.mActivity.getServiceManager().getBrowse().fetchPostPlayVideos(String.valueOf(videoIds.episodeId), new FetchNextSeriesEpisodeVideoDetailsForMdxCallback("nf_mdx", this.mActivity));
        }
    }
    
    private void showMdxController(final Intent intent, final Context context) {
        final String string = intent.getExtras().getString("postplayState");
        if (!StringUtils.isEmpty(string)) {
            final MdxPostplayState mdxPostplayState = new MdxPostplayState(string);
            if (mdxPostplayState.isInCountdown()) {
                this.showNextEpisodeInSeries(mdxPostplayState);
            }
            else if (mdxPostplayState.isInPrompt()) {
                this.showFirstEpisodeInNextSeries(mdxPostplayState);
            }
        }
    }
    
    private void showNextEpisodeInSeries(final MdxPostplayState mdxPostplayState) {
        final WebApiUtils.VideoIds videoIdsPostplay = ((MdxAgent)this.mActivity.getServiceManager().getMdx()).getVideoIdsPostplay();
        if (videoIdsPostplay != null && videoIdsPostplay.episodeId > 0) {
            this.mActivity.getServiceManager().getBrowse().fetchEpisodeDetails(String.valueOf(videoIdsPostplay.episodeId), new FetchPostPlayForPlaybackCallback("nf_mdx", this.mActivity));
        }
    }
    
    private void verifyPinAndNotify(final Intent intent) {
        final String string = intent.getExtras().getString("uuid");
        Log.d("nf_pin", "verifyPinAndNotify on PIN_VERIFICATION_SHOW");
        PinVerifier.getInstance().verify(this.mActivity, true, new PinDialogVault(PinDialogVault.PinInvokedFrom.MDX.getValue(), string));
    }
    
    public IntentFilter getFilter() {
        Log.v("nf_mdx", "Get filter called");
        final IntentFilter intentFilter = new IntentFilter("com.netflix.mediaclient.intent.action.MDXUPDATE_READY");
        intentFilter.addAction("com.netflix.mediaclient.intent.action.MDXUPDATE_NOTREADY");
        intentFilter.addAction("com.netflix.mediaclient.intent.action.MDXUPDATE_TARGETLIST");
        intentFilter.addAction("com.netflix.mediaclient.intent.action.PIN_VERIFICATION_SHOW");
        intentFilter.addAction("com.netflix.mediaclient.intent.action.PIN_VERIFICATION_NOT_REQUIRED");
        intentFilter.addAction("com.netflix.mediaclient.intent.action.MDXUPDATE_POSTPLAY");
        intentFilter.addAction("com.netflix.mediaclient.intent.action.MDXUPDATE_PLAYBACKSTART");
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
                if ("com.netflix.mediaclient.intent.action.MDXUPDATE_PLAYBACKSTART".equals(action)) {
                    this.hideMdxController(context);
                    return;
                }
                if (intent.getAction().equals("com.netflix.mediaclient.intent.action.MDXUPDATE_STATE")) {
                    final IMdxSharedState sharedState = this.mActivity.getServiceManager().getMdx().getSharedState();
                    if (sharedState != null && sharedState.getMdxPlaybackState() == IMdxSharedState.MdxPlaybackState.Transitioning) {
                        this.hideMdxController(context);
                    }
                }
            }
        }
    }
    
    private static class FetchNextSeriesEpisodeVideoDetailsForMdxCallback extends LoggingManagerCallback
    {
        private final NetflixActivity mActivity;
        private boolean processed;
        
        public FetchNextSeriesEpisodeVideoDetailsForMdxCallback(final String s, final NetflixActivity mActivity) {
            super(s);
            this.processed = false;
            this.mActivity = mActivity;
        }
        
        @Override
        public void onPostPlayVideosFetched(final List<PostPlayVideo> list, final Status status) {
            super.onPostPlayVideosFetched(list, status);
            if (!this.processed && this.mActivity != null && status.isSucces() && list.size() > 0) {
                final String id = list.get(0).getId();
                if (!TextUtils.isEmpty((CharSequence)id)) {
                    this.mActivity.getServiceManager().getBrowse().fetchEpisodeDetails(id, new FetchPostPlayForPlaybackCallback("nf_mdx", this.mActivity));
                    this.processed = true;
                }
            }
        }
    }
    
    private static class FetchPostPlayForPlaybackCallback extends LoggingManagerCallback
    {
        private final NetflixActivity mActivity;
        private boolean processed;
        
        public FetchPostPlayForPlaybackCallback(final String s, final NetflixActivity mActivity) {
            super(s);
            this.processed = false;
            this.mActivity = mActivity;
        }
        
        @Override
        public void onEpisodeDetailsFetched(final EpisodeDetails episodeDetails, final Status status) {
            super.onEpisodeDetailsFetched(episodeDetails, status);
            if (status.isSucces() && episodeDetails != null && !this.processed) {
                MDXControllerActivity.showMDXController(this.mActivity, episodeDetails.getId(), PlayContext.DFLT_MDX_CONTEXT);
                this.processed = true;
            }
        }
    }
}
