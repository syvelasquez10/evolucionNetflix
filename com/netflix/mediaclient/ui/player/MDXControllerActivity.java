// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.servicemgr.UserActionLogging;
import com.netflix.mediaclient.util.LogUtils;
import android.app.Activity;
import com.netflix.mediaclient.util.AndroidUtils;
import android.os.Bundle;
import android.content.res.Configuration;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.ui.common.PlayContext;
import android.text.TextUtils;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.content.Context;
import android.content.Intent;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View;
import android.annotation.TargetApi;

@TargetApi(14)
public class MDXControllerActivity extends PlayerActivity
{
    private static final String ACTION_FINISH_ACTIVITY = "com.netflix.mediaclient.ui.player.MDXControllerActivity.ACTION_FINISH";
    static final String EXTRA_SHOW_MDX_CONTROLLER = "extra_shoe_mdx_controller";
    private FinishReceiver finishReceiver;
    private PostPlay mPostPlay;
    private View postPlay;
    private String videoId;
    
    public MDXControllerActivity() {
        this.finishReceiver = new FinishReceiver();
    }
    
    private static Intent createIntent(final NetflixActivity netflixActivity) {
        return new Intent((Context)netflixActivity, (Class)MDXControllerActivity.class);
    }
    
    public static void finishMDXController(final NetflixActivity netflixActivity) {
        netflixActivity.sendBroadcast(new Intent("com.netflix.mediaclient.ui.player.MDXControllerActivity.ACTION_FINISH"));
    }
    
    private void setupPostplayViews() {
        this.postPlay = this.findViewById(2131165554);
        this.mPostPlay = PostPlayFactory.create(this, PostPlayFactory.PostPlayType.EpisodesForMDX);
    }
    
    private void setupReceivers() {
        if (this.finishReceiver != null) {
            final IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.netflix.mediaclient.ui.player.MDXControllerActivity.ACTION_FINISH");
            this.registerReceiver((BroadcastReceiver)this.finishReceiver, intentFilter);
        }
    }
    
    private void showEpisodesData() {
        if (this.postPlay != null) {
            this.postPlay.setVisibility(0);
            if (this.getIntent().hasExtra("extra_get_details_video_id")) {
                this.videoId = this.getIntent().getExtras().getString("extra_get_details_video_id");
                if (!TextUtils.isEmpty((CharSequence)this.videoId) && this.mPostPlay != null) {
                    this.mPostPlay.init(this.videoId);
                }
            }
        }
    }
    
    public static void showMDXController(final NetflixActivity netflixActivity, final int n, final PlayContext playContext) {
        showMDXController(netflixActivity, String.valueOf(n), playContext);
    }
    
    public static void showMDXController(final NetflixActivity netflixActivity, final String s, final PlayContext playContext) {
        final Intent intent = createIntent(netflixActivity);
        intent.putExtra("extra_get_details_video_id", s);
        netflixActivity.startActivity(intent);
    }
    
    private void unregisterReceivers() {
        if (this.finishReceiver != null) {
            this.unregisterReceiver((BroadcastReceiver)this.finishReceiver);
        }
    }
    
    @Override
    protected ManagerStatusListener createManagerStatusListener() {
        return new ManagerStatusListener() {
            @Override
            public void onManagerReady(final ServiceManager serviceManager, final int n) {
                MDXControllerActivity.this.showEpisodesData();
            }
            
            @Override
            public void onManagerUnavailable(final ServiceManager serviceManager, final int n) {
                MDXControllerActivity.this.cleanupAndExit();
            }
        };
    }
    
    @Override
    public IClientLogging.ModalView getUiScreen() {
        return IClientLogging.ModalView.mdxPlayback;
    }
    
    @Override
    public boolean isLoadingData() {
        return false;
    }
    
    @Override
    public void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.setContentView(2130903114);
        this.setupPostplayViews();
        this.showEpisodesData();
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2130903114);
        this.setupPostplayViews();
        AndroidUtils.logDeviceDensity(this);
        this.getWindow().getAttributes().buttonBrightness = 0.0f;
        LogUtils.reportPlayActionStarted((Context)this, null, this.getUiScreen());
        LogUtils.pauseReporting((Context)this);
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        this.unregisterReceivers();
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
    
    class FinishReceiver extends BroadcastReceiver
    {
        public void onReceive(final Context context, final Intent intent) {
            if (intent.getAction() == "com.netflix.mediaclient.ui.player.MDXControllerActivity.ACTION_FINISH") {
                MDXControllerActivity.this.finish();
            }
        }
    }
}