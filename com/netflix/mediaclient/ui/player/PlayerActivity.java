// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.ui.verifyplay.PlayVerifierVault;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import android.view.KeyEvent;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.ui.details.AbsEpisodeView$EpisodeRowListener;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag$DialogCanceledListener;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import android.app.Fragment;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.Asset;
import android.os.Parcelable;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import android.content.Context;
import android.annotation.TargetApi;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag$DialogCanceledListenerProvider;
import com.netflix.mediaclient.android.activity.FragmentHostActivity;

@TargetApi(14)
public class PlayerActivity extends FragmentHostActivity implements NetflixDialogFrag$DialogCanceledListenerProvider, PlayContextProvider
{
    static final String EXTRA_GET_DETAILS_EPISODE_DETAILS = "extra_get_details_EPISODE_DETAILS";
    static final String EXTRA_GET_DETAILS_IS_EPISODE = "extra_get_details_is_episode";
    static final String EXTRA_GET_DETAILS_PLAY_CONTEXT = "extra_get_details_play_context";
    static final String EXTRA_GET_DETAILS_VIDEO_ID = "extra_get_details_video_id";
    static final String EXTRA_GET_DETAILS_VIDEO_TYPE = "extra_get_details_video_type";
    public static final String INTENT_PLAY = "com.netflix.mediaclient.intent.action.NOTIFICATION_PLAY";
    private static final String TAG = "PlayerActivity";
    private PlayerFragment playerFragment;
    
    public static Intent createColdStartIntent(final Context context, final String s, final VideoType videoType, final PlayContext playContext) {
        if (Log.isLoggable()) {
            Log.d("PlayerActivity", "Performing 'cold start' - activity itself will get details for videoId: " + s);
        }
        final Intent intent = new Intent(context, (Class)PlayerActivity.class);
        intent.addFlags(131072);
        intent.putExtra("extra_get_details_video_id", s);
        intent.putExtra("extra_get_details_video_type", videoType.getValue());
        intent.putExtra("extra_get_details_play_context", (Parcelable)playContext);
        return intent;
    }
    
    public static Intent createColdStartIntentForService(final String s, final VideoType videoType, final PlayContext playContext) {
        if (Log.isLoggable()) {
            Log.d("PlayerActivity", "Performing 'cold start' - activity itself will get details for videoId: " + s);
        }
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.NOTIFICATION_PLAY");
        intent.addFlags(131072);
        intent.putExtra("extra_get_details_video_id", s);
        intent.putExtra("extra_get_details_video_type", videoType.getValue());
        intent.putExtra("extra_get_details_play_context", (Parcelable)playContext);
        return intent;
    }
    
    public static Asset getAssetFromIntent(final Intent intent) {
        if (intent == null) {
            Log.e("PlayerActivity", "Got null intent");
            return null;
        }
        return Asset.fromIntent(intent);
    }
    
    private static String parseVideoId(final Intent intent) {
        if (intent.hasExtra("playableid")) {
            return intent.getStringExtra("playableid");
        }
        if (intent.hasExtra("extra_video_id")) {
            return intent.getStringExtra("extra_video_id");
        }
        if (intent.hasExtra("extra_get_details_video_id")) {
            return intent.getStringExtra("extra_get_details_video_id");
        }
        if (Log.isLoggable()) {
            Log.w("PlayerActivity", "Couldn't parse video id from intent: " + intent);
        }
        return null;
    }
    
    public static boolean shouldResumePreviousPlay(final Intent intent, final Intent intent2) {
        return StringUtils.safeEquals(parseVideoId(intent), parseVideoId(intent2));
    }
    
    protected void cleanupAndExit() {
        if (this.playerFragment == null) {
            return;
        }
        this.playerFragment.cleanupAndExit();
    }
    
    @Override
    protected ManagerStatusListener createManagerStatusListener() {
        return new PlayerActivity$1(this);
    }
    
    @Override
    protected Fragment createPrimaryFrag() {
        final Intent intent = this.getIntent();
        if (intent.hasExtra("extra_get_details_video_id")) {
            this.playerFragment = PlayerFragment.createPlayerFragment(intent.getStringExtra("extra_get_details_video_id"), intent.getStringExtra("extra_get_details_video_type"), intent.getParcelableExtra("extra_get_details_play_context"), -1);
        }
        else {
            this.playerFragment = PlayerFragment.createPlayerFragment(getAssetFromIntent(this.getIntent()));
        }
        if (intent.hasExtra("BookmarkSecondsFromStart")) {
            this.playerFragment.getArguments().putInt("BookmarkSecondsFromStart", intent.getIntExtra("BookmarkSecondsFromStart", -1));
        }
        return this.playerFragment;
    }
    
    @Override
    protected int getContentLayoutId() {
        return 2130903129;
    }
    
    @Override
    public DataContext getDataContext() {
        return new DataContext(getAssetFromIntent(this.getIntent()));
    }
    
    @Override
    public NetflixDialogFrag$DialogCanceledListener getDialogCanceledListener() {
        if (this.playerFragment == null) {
            return null;
        }
        return this.playerFragment.getDialogCanceledListener();
    }
    
    @Override
    public AbsEpisodeView$EpisodeRowListener getEpisodeRowListener() {
        if (this.playerFragment == null) {
            return null;
        }
        return this.playerFragment.getEpisodeRowListener();
    }
    
    @Override
    public PlayContext getPlayContext() {
        return getAssetFromIntent(this.getIntent());
    }
    
    protected PlayerFragment getPlayerFragment() {
        return (PlayerFragment)this.getPrimaryFrag();
    }
    
    @Override
    public IClientLogging$ModalView getUiScreen() {
        return IClientLogging$ModalView.playback;
    }
    
    @Override
    protected boolean handleBackPressed() {
        return this.playerFragment != null && this.playerFragment.handleBackPressed();
    }
    
    @Override
    protected boolean hasEmbeddedToolbar() {
        return false;
    }
    
    @Override
    public void invalidateOptionsMenu() {
        super.invalidateOptionsMenu();
        Log.d("PlayerActivity", "Check if MDX status is changed");
        if (this.playerFragment != null) {
            this.playerFragment.setTargetSelection();
        }
    }
    
    @Override
    public boolean isLoadingData() {
        return false;
    }
    
    @Override
    protected void lockScreenOrientation() {
    }
    
    @Override
    public boolean onKeyDown(final int n, final KeyEvent keyEvent) {
        return (this.playerFragment != null && this.playerFragment.onKeyDown(n, keyEvent)) || super.onKeyDown(n, keyEvent);
    }
    
    @Override
    protected void onNewIntent(final Intent intent) {
        super.onNewIntent(intent);
        if (shouldResumePreviousPlay(this.getIntent(), intent)) {
            Log.d("PlayerActivity", "Got same video ID - resuming the playback...");
            return;
        }
        this.setIntent(intent);
        if (this.playerFragment == null) {
            ErrorLoggingManager.logHandledException("SPY-8343 - PlayerActivity received onNewIntent() prior to onCreate() - skipping to avoid crash");
            return;
        }
        if (intent.hasExtra("extra_get_details_video_id")) {
            this.playerFragment.setExternalBundle(PlayerFragment.getBundle(intent.getStringExtra("extra_get_details_video_id"), intent.getStringExtra("extra_get_details_video_type"), intent.getParcelableExtra("extra_get_details_play_context")));
        }
        else {
            this.playerFragment.setExternalBundle(PlayerFragment.getBundle(getAssetFromIntent(this.getIntent())));
        }
        this.playerFragment.resetCurrentPlayback();
    }
    
    @Override
    public void onPlayVerified(final boolean b, final PlayVerifierVault playVerifierVault) {
        if (this.playerFragment == null) {
            return;
        }
        this.playerFragment.onPlayVerified(b, playVerifierVault);
    }
    
    public boolean onSearchRequested() {
        return false;
    }
    
    public void onWindowFocusChanged(final boolean b) {
        if (this.playerFragment != null) {
            this.playerFragment.onWindowFocusChanged(b);
        }
    }
    
    @Override
    public void performUpAction() {
        if (this.playerFragment != null) {
            this.playerFragment.performUpAction();
        }
    }
    
    @Override
    protected boolean shouldAttachToolbar() {
        return false;
    }
    
    @Override
    public boolean showAboutInMenu() {
        return false;
    }
    
    public boolean showMdxInMenu() {
        return true;
    }
    
    @Override
    public boolean showSettingsInMenu() {
        return false;
    }
    
    @Override
    public boolean showSignOutInMenu() {
        return false;
    }
    
    public void updateIntent(final Asset asset) {
        asset.toIntent(this.getIntent());
    }
}
