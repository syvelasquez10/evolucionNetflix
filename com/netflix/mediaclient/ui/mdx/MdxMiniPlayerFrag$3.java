// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.ui.common.PlaybackLauncher;
import com.netflix.mediaclient.util.MdxUtils$MdxTargetSelectionDialogInterface;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.BroadcastReceiver;
import android.os.Bundle;
import com.netflix.mediaclient.util.DeviceUtils;
import android.view.View;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import com.netflix.mediaclient.ui.common.PlayContext;
import android.view.KeyEvent;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import com.netflix.mediaclient.servicemgr.ServiceManagerUtils;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import android.content.Intent;
import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.Fragment;
import android.app.DialogFragment;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.mdx.MdxKeyEventHandler;
import com.netflix.mediaclient.service.mdx.MdxKeyEventHandler$MdxKeyEventCallbacks;
import com.netflix.mediaclient.service.mdx.MdxErrorHandler;
import com.netflix.mediaclient.ui.common.LanguageSelector$LanguageSelectorCallback;
import com.netflix.mediaclient.ui.common.LanguageSelector;
import android.os.Handler;
import com.netflix.mediaclient.servicemgr.IMdx;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;

class MdxMiniPlayerFrag$3 implements Runnable
{
    final /* synthetic */ MdxMiniPlayerFrag this$0;
    
    MdxMiniPlayerFrag$3(final MdxMiniPlayerFrag this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        if (AndroidUtils.isActivityFinishedOrDestroyed((Context)this.this$0.activity) || this.this$0.draggingInProgress) {
            this.this$0.log("skipping seekbar update");
            return;
        }
        final long n = System.currentTimeMillis() - this.this$0.simulatedVideoPositionTimeFiredMs;
        if (this.this$0.simulatedVideoPositionTimeFiredMs > 0L && n > 0L) {
            this.this$0.simulatedCurrentTimelinePositionMs += n;
            final int progress = (int)this.this$0.simulatedCurrentTimelinePositionMs / 1000;
            this.this$0.log("updateSeekBarRunnable, timelinePosInSeconds: " + progress);
            this.this$0.views.setProgress(progress);
        }
        this.this$0.simulatedVideoPositionTimeFiredMs = System.currentTimeMillis();
        this.this$0.handler.postDelayed(this.this$0.updateSeekBarRunnable, 1000L);
    }
}
