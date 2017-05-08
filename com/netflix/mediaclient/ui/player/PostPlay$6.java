// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.animation.TimeInterpolator;
import com.netflix.mediaclient.util.DeviceUtils;
import android.view.View$OnClickListener;
import android.view.View$OnTouchListener;
import com.netflix.mediaclient.ui.common.PlayContext;
import android.content.res.Configuration;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.UserActionLogging$PostPlayExperience;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.text.TextUtils;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.service.logging.client.model.Error;
import android.content.Context;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.IPlayer;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import com.netflix.mediaclient.util.DeviceCategory;
import com.netflix.mediaclient.servicemgr.Asset;
import android.widget.TextView;
import com.netflix.mediaclient.servicemgr.interface_.details.PostPlayVideo;
import com.netflix.mediaclient.servicemgr.interface_.details.PostPlayContext;
import java.util.List;
import android.view.animation.DecelerateInterpolator;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View;
import com.netflix.model.leafs.InteractivePostplay;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.ui.iko.InteractivePostPlayManager;
import com.netflix.mediaclient.Log;

class PostPlay$6 implements Runnable
{
    final /* synthetic */ PostPlay this$0;
    
    PostPlay$6(final PostPlay this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        if (this.this$0.mPlayerFragment == null) {
            Log.e("nf_postplay", "onInterrupterStart() - called with null PlayerFragment!");
            return;
        }
        if (!this.this$0.mPlayerFragment.getState().noUserInteraction()) {
            Log.d("nf_postplay", "Interrupter process, there was user interaction in meantime. Do nothing");
            return;
        }
        if (this.this$0.mPlayerFragment.getScreen().getState() == PlayerUiState.Loading) {
            Log.d("nf_postplay", "This is 3rd consecutive auto play with no user interaction, but after 2 minutes we are still loading, postpone for 2 more minutes");
            this.this$0.mPlayerFragment.getHandler().postDelayed((Runnable)this, 120000L);
            return;
        }
        if (this.this$0.mInterrupter != null) {
            Log.d("nf_postplay", "This is 3rd consecutive auto play with no user interaction, after 2 minutes start interrupter mode");
            this.this$0.mPlayerFragment.doPause();
            this.this$0.mInterrupter.setVisibility(0);
            this.this$0.mPlayerFragment.getScreen().moveToState(PlayerUiState.Interrupter);
            this.this$0.mPlayerFragment.getHandler().postDelayed(this.this$0.onInterrupterDismiss, (long)this.this$0.mInterrputerTimeoutOffset);
            return;
        }
        Log.w("nf_postplay", "Interrupter UI NOT found, this should not happen!");
    }
}
