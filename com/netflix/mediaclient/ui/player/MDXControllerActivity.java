// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import android.app.Activity;
import com.netflix.mediaclient.util.AndroidUtils;
import android.os.Bundle;
import android.content.res.Configuration;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.IMdxSharedState;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.NetflixApplication;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.service.webclient.model.EpisodeDetails;
import android.text.TextUtils;
import android.content.IntentFilter;
import android.content.Context;
import android.content.Intent;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View;
import android.content.BroadcastReceiver;
import android.annotation.TargetApi;

@TargetApi(14)
public class MDXControllerActivity extends PlayerActivity
{
    private static final String ACTION_FINISH_ACTIVITY = "com.netflix.mediaclient.ui.player.MDXControllerActivity.ACTION_FINISH";
    private static final String EXTRA_SHOW_MDX_CONTROLLER = "extra_shoe_mdx_controller";
    private final BroadcastReceiver finishReceiver;
    private PostPlay postPlayController;
    private View postPlayViewGroup;
    private String videoId;
    
    public MDXControllerActivity() {
        this.finishReceiver = new MDXControllerActivity$1(this);
    }
    
    private static Intent createIntent(final NetflixActivity netflixActivity) {
        return new Intent((Context)netflixActivity, (Class)MDXControllerActivity.class);
    }
    
    public static void finishMDXController(final Context context) {
        context.sendBroadcast(new Intent("com.netflix.mediaclient.ui.player.MDXControllerActivity.ACTION_FINISH"));
    }
    
    private void setupPostplayViews() {
        this.postPlayViewGroup = this.findViewById(2131165596);
        this.postPlayController = PostPlayFactory.create(this, PostPlayFactory$PostPlayType.EpisodesForMDX);
    }
    
    private void setupReceivers() {
        if (this.finishReceiver != null) {
            final IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.netflix.mediaclient.ui.player.MDXControllerActivity.ACTION_FINISH");
            this.registerReceiver(this.finishReceiver, intentFilter);
        }
    }
    
    private void showEpisodesData() {
        if (this.postPlayViewGroup != null) {
            this.postPlayViewGroup.setVisibility(0);
            if (this.getIntent().hasExtra("extra_get_details_video_id")) {
                this.videoId = this.getIntent().getExtras().getString("extra_get_details_video_id");
                if (!TextUtils.isEmpty((CharSequence)this.videoId) && this.postPlayController != null) {
                    this.postPlayController.init(this.videoId);
                }
            }
            else if (this.getIntent().hasExtra("extra_get_details_EPISODE_DETAILS")) {
                final EpisodeDetails episodeDetails = (EpisodeDetails)this.getIntent().getSerializableExtra("extra_get_details_EPISODE_DETAILS");
                if (episodeDetails != null && this.postPlayController != null) {
                    ((PostPlayForMDX)this.postPlayController).init(episodeDetails);
                }
            }
        }
    }
    
    public static void showMDXController(final NetflixActivity netflixActivity, final int n, final PlayContext playContext) {
        showMDXController(netflixActivity, String.valueOf(n), playContext);
    }
    
    public static void showMDXController(final NetflixActivity netflixActivity, final String s, final PlayContext playContext) {
        if (NetflixApplication.isActivityVisible()) {
            final Intent intent = createIntent(netflixActivity);
            intent.addFlags(131072);
            intent.putExtra("extra_get_details_video_id", s);
            intent.putExtra("extra_shoe_mdx_controller", true);
            netflixActivity.startActivity(intent);
        }
    }
    
    private void unregisterReceivers() {
        if (this.finishReceiver != null) {
            this.unregisterReceiver(this.finishReceiver);
        }
    }
    
    @Override
    protected ManagerStatusListener createManagerStatusListener() {
        return new MDXControllerActivity$2(this);
    }
    
    @Override
    protected IMdxSharedState getSharedState() {
        return null;
    }
    
    @Override
    public IClientLogging$ModalView getUiScreen() {
        return IClientLogging$ModalView.mdxPlayback;
    }
    
    public boolean handleBackPressed() {
        this.setResult(-1);
        return true;
    }
    
    @Override
    public boolean isLoadingData() {
        return false;
    }
    
    @Override
    public void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.setContentView(2130903135);
        this.setupPostplayViews();
        this.showEpisodesData();
        if (this.getSupportActionBar() != null) {
            this.getSupportActionBar().hide();
        }
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2130903135);
        if (this.getSupportActionBar() != null) {
            this.getSupportActionBar().hide();
        }
        this.setupPostplayViews();
        AndroidUtils.logDeviceDensity(this);
        this.getWindow().getAttributes().buttonBrightness = 0.0f;
        UserActionLogUtils.reportPlayActionStarted((Context)this, null, this.getUiScreen());
        ConsolidatedLoggingUtils.pauseReporting((Context)this);
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        this.unregisterReceivers();
        if (!NetflixApplication.isActivityVisible()) {
            this.finish();
        }
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        this.setupReceivers();
    }
    
    @Override
    protected void onStart() {
        super.onStart();
    }
    
    @Override
    protected void onStop() {
        super.onStop();
    }
    
    @Override
    protected void showMDXPostPlayOnResume() {
    }
}
