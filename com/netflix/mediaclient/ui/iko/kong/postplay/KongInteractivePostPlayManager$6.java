// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.kong.postplay;

import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractivePostPlayModel$KongSound;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.ui.common.MediaPlayerWrapper$PlaybackEventsListener;
import android.view.TextureView;
import android.view.LayoutInflater;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.android.widget.PressAnimationFrameLayout;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import java.io.IOException;
import android.graphics.BitmapFactory;
import com.netflix.mediaclient.util.FileUtils;
import java.io.File;
import com.netflix.mediaclient.util.ThreadUtils;
import android.graphics.Bitmap;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.android.osp.AsyncTaskCompat;
import com.facebook.device.yearclass.YearClass;
import java.util.Collections;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractivePostPlayModel;
import com.netflix.mediaclient.service.resfetcher.volley.LocalCachedFileMetadata;
import java.util.Map;
import java.util.List;
import com.netflix.mediaclient.ui.player.PostPlay;
import com.netflix.mediaclient.ui.player.PlayerFragment;
import android.graphics.BitmapFactory$Options;
import com.netflix.mediaclient.ui.common.MediaPlayerWrapper;
import android.view.View;
import android.os.Handler;
import android.animation.Animator;
import android.widget.ImageView;
import android.view.ViewGroup;
import com.netflix.mediaclient.ui.iko.InteractivePostPlayManager;
import com.netflix.mediaclient.ui.common.PlayContext;
import android.content.Context;
import android.widget.Toast;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.ui.common.PlayContextImp;

class KongInteractivePostPlayManager$6 extends KongInteractivePostPlayManager$BattleVideoDetailsForPlaybackCallback
{
    final /* synthetic */ KongInteractivePostPlayManager this$0;
    final /* synthetic */ boolean val$autoplay;
    final /* synthetic */ PlayContextImp val$playContext;
    
    KongInteractivePostPlayManager$6(final KongInteractivePostPlayManager this$0, final PlayContextImp val$playContext, final boolean val$autoplay) {
        this.this$0 = this$0;
        this.val$playContext = val$playContext;
        this.val$autoplay = val$autoplay;
        super(this$0);
    }
    
    @Override
    protected void handleResponse(final VideoDetails videoDetails, final Status status) {
        super.handleResponse(videoDetails, status);
        if (!this.this$0.isActivityValid()) {
            return;
        }
        if (status.isError() || videoDetails == null) {
            Log.e("KongInteractivePostPlayManager", "Error loading video details for video playback");
            Toast.makeText((Context)this.this$0.getActivity(), 2131296660, 1).show();
            return;
        }
        this.this$0.playerFragment.playNextVideo(videoDetails.getPlayable(), this.val$playContext, this.val$autoplay);
    }
}
