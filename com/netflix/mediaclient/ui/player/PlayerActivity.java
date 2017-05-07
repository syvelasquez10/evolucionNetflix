// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.ui.verifyplay.PlayVerifierVault;
import android.view.KeyEvent;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.ui.details.AbsEpisodeView$EpisodeRowListener;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag$DialogCanceledListener;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import android.app.Fragment;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.Asset;
import android.os.Parcelable;
import android.content.Context;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import android.app.Activity;
import android.annotation.TargetApi;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag$DialogCanceledListenerProvider;
import com.netflix.mediaclient.android.activity.FragmentHostActivity;

@TargetApi(14)
public class PlayerActivity extends FragmentHostActivity implements NetflixDialogFrag$DialogCanceledListenerProvider
{
    static final String EXTRA_GET_DETAILS_EPISODE_DETAILS = "extra_get_details_EPISODE_DETAILS";
    static final String EXTRA_GET_DETAILS_IS_EPISODE = "extra_get_details_is_episode";
    static final String EXTRA_GET_DETAILS_PLAY_CONTEXT = "extra_get_details_play_context";
    static final String EXTRA_GET_DETAILS_VIDEO_ID = "extra_get_details_video_id";
    static final String EXTRA_GET_DETAILS_VIDEO_TYPE = "extra_get_details_video_type";
    public static final String INTENT_PLAY = "com.netflix.mediaclient.intent.action.NOTIFICATION_PLAY";
    public static final Boolean PIN_VERIFIED;
    private static final String TAG = "PlayerActivity";
    private PlayerFragment playerFragment;
    
    static {
        PIN_VERIFIED = true;
    }
    
    public static Intent createColdStartIntent(final Activity activity, final String s, final VideoType videoType, final PlayContext playContext) {
        if (Log.isLoggable()) {
            Log.d("PlayerActivity", "Performing 'cold start' - activity itself will get details for videoId: " + s);
        }
        final Intent intent = new Intent((Context)activity, (Class)PlayerActivity.class);
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
    
    private Asset getAssetFromIntent() {
        final Intent intent = this.getIntent();
        if (intent == null) {
            Log.e("PlayerActivity", "Got null intent");
            return null;
        }
        return Asset.fromIntent(intent);
    }
    
    public static void playVideo(final NetflixActivity netflixActivity, final Asset asset) {
        if (Log.isLoggable()) {
            Log.d("PlayerActivity", "Asset to playback: " + asset);
        }
        if (asset == null) {
            return;
        }
        netflixActivity.startActivity(toIntent(netflixActivity, asset));
    }
    
    public static void playVideo(final NetflixActivity netflixActivity, final Playable playable, final PlayContext playContext) {
        playVideo(netflixActivity, Asset.create(playable, playContext, !PlayerActivity.PIN_VERIFIED));
    }
    
    public static Intent toIntent(final NetflixActivity netflixActivity, final Asset asset) {
        final Intent intent = new Intent((Context)netflixActivity, (Class)PlayerActivity.class);
        intent.addFlags(131072);
        intent.addFlags(268435456);
        asset.toIntent(intent);
        return intent;
    }
    
    protected void cleanupAndExit() {
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
            this.playerFragment = PlayerFragment.createPlayerFragment(intent.getStringExtra("extra_get_details_video_id"), intent.getStringExtra("extra_get_details_video_type"), intent.getParcelableExtra("extra_get_details_play_context"));
        }
        else {
            this.playerFragment = PlayerFragment.createPlayerFragment(this.getAssetFromIntent());
        }
        return this.playerFragment;
    }
    
    @Override
    protected int getContentLayoutId() {
        return 2130903112;
    }
    
    @Override
    public DataContext getDataContext() {
        return new DataContext(this.getAssetFromIntent());
    }
    
    @Override
    public NetflixDialogFrag$DialogCanceledListener getDialogCanceledListener() {
        return this.playerFragment.getDialogCanceledListener();
    }
    
    @Override
    public AbsEpisodeView$EpisodeRowListener getEpisodeRowListener() {
        return this.playerFragment.getEpisodeRowListener();
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
        return this.playerFragment.handleBackPressed();
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
    public boolean onKeyDown(final int n, final KeyEvent keyEvent) {
        return this.playerFragment.onKeyDown(n, keyEvent) || super.onKeyDown(n, keyEvent);
    }
    
    @Override
    protected void onNewIntent(final Intent intent) {
        super.onNewIntent(intent);
        this.setIntent(intent);
        if (intent.hasExtra("extra_get_details_video_id")) {
            this.playerFragment.setExternalBundle(PlayerFragment.getBundle(intent.getStringExtra("extra_get_details_video_id"), intent.getStringExtra("extra_get_details_video_type"), intent.getParcelableExtra("extra_get_details_play_context")));
        }
        else {
            this.playerFragment.setExternalBundle(PlayerFragment.getBundle(this.getAssetFromIntent()));
        }
        this.playerFragment.resetCurrentPlayback();
    }
    
    @Override
    public void onPlayVerified(final boolean b, final PlayVerifierVault playVerifierVault) {
        this.playerFragment.onPlayVerified(b, playVerifierVault);
    }
    
    public boolean onSearchRequested() {
        return false;
    }
    
    public void onWindowFocusChanged(final boolean b) {
        this.playerFragment.onWindowFocusChanged(b);
    }
    
    @Override
    public void performUpAction() {
        this.playerFragment.performUpAction();
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
}
