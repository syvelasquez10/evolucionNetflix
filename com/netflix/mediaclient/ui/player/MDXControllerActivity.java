// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.servicemgr.UserActionLogging;
import com.netflix.mediaclient.util.LogUtils;
import android.app.Activity;
import com.netflix.mediaclient.util.AndroidUtils;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.ui.common.PlayContext;
import android.text.TextUtils;
import android.content.Context;
import android.content.Intent;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View;
import android.annotation.TargetApi;

@TargetApi(14)
public class MDXControllerActivity extends PlayerActivity
{
    static final String EXTRA_SHOW_MDX_CONTROLLER = "extra_shoe_mdx_controller";
    private PostPlay mPostPlay;
    private View postPlay;
    private String videoId;
    
    private static Intent createIntent(final NetflixActivity netflixActivity) {
        return new Intent((Context)netflixActivity, (Class)MDXControllerActivity.class);
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
        final Intent intent = createIntent(netflixActivity);
        intent.putExtra("extra_get_details_video_id", String.valueOf(n));
        intent.addFlags(131072);
        netflixActivity.startActivity(intent);
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
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2130903115);
        this.postPlay = this.findViewById(2131165548);
        this.mPostPlay = PostPlayFactory.create(this, PostPlayFactory.PostPlayType.EpisodesForMDX);
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
    }
    
    @Override
    protected void onResume() {
        super.onResume();
    }
    
    @Override
    protected void onStart() {
        super.onStart();
    }
    
    @Override
    protected void onStop() {
        super.onStop();
    }
}
