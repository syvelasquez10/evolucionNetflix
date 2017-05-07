// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import com.netflix.mediaclient.ui.common.PlaybackLauncher;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.BroadcastReceiver;
import android.os.Bundle;
import android.view.View;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import com.netflix.mediaclient.ui.common.PlayContext;
import android.view.KeyEvent;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import com.netflix.mediaclient.servicemgr.ServiceManagerUtils;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import android.content.Intent;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.Fragment;
import android.app.Activity;
import com.netflix.mediaclient.util.AndroidUtils;
import android.app.DialogFragment;
import com.netflix.mediaclient.service.mdx.MdxKeyEventHandler;
import com.netflix.mediaclient.service.mdx.MdxKeyEventHandler$MdxKeyEventCallbacks;
import com.netflix.mediaclient.service.mdx.MdxErrorHandler;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.ui.common.LanguageSelector$LanguageSelectorCallback;
import com.netflix.mediaclient.ui.common.LanguageSelector;
import android.os.Handler;
import com.netflix.mediaclient.servicemgr.IMdx;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.util.MdxUtils$MdxTargetSelectionDialogInterface;
import com.netflix.mediaclient.ui.details.NetflixRatingBar$RatingBarDataProvider;
import com.netflix.mediaclient.ui.details.AbsEpisodeView$EpisodeRowListener;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.details.SeasonDetails;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class MdxMiniPlayerFrag$FetchSeasonDetailsCallback extends LoggingManagerCallback
{
    final /* synthetic */ MdxMiniPlayerFrag this$0;
    
    public MdxMiniPlayerFrag$FetchSeasonDetailsCallback(final MdxMiniPlayerFrag this$0) {
        this.this$0 = this$0;
        super("MdxMiniPlayerFrag");
    }
    
    @Override
    public void onSeasonDetailsFetched(final SeasonDetails seasonDetails, final Status status) {
        super.onSeasonDetailsFetched(seasonDetails, status);
        ThreadUtils.assertOnMain();
        if (status.isError()) {
            return;
        }
        if (seasonDetails == null) {
            Log.w("MdxMiniPlayerFrag", "Season is null, should NOT happen!");
        }
        MdxMiniPlayerFrag.state.isEpisodeReady = (seasonDetails != null);
        this.this$0.views.setControlsEnabled(MdxMiniPlayerFrag.state.controlsEnabled);
    }
}
