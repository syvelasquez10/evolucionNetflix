// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.ui.common.PlaybackLauncher;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.BroadcastReceiver;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.IBrowseManager;
import com.netflix.mediaclient.util.MdxUtils$SetVideoRatingCallback;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.MdxUtils;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import android.view.View;
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
import com.netflix.mediaclient.Log;
import java.util.HashSet;
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
import java.util.Set;
import com.netflix.mediaclient.util.MdxUtils$MdxTargetSelectionDialogInterface;
import com.netflix.mediaclient.ui.details.AbsEpisodeView$EpisodeRowListener;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class MdxMiniPlayerFrag$2 extends LoggingManagerCallback
{
    final /* synthetic */ MdxMiniPlayerFrag this$0;
    final /* synthetic */ String val$id;
    
    MdxMiniPlayerFrag$2(final MdxMiniPlayerFrag this$0, final String s, final String val$id) {
        this.this$0 = this$0;
        this.val$id = val$id;
        super(s);
    }
    
    @Override
    public void onVideoHide(final Status status) {
        if (this.this$0.activity.destroyed()) {
            return;
        }
        this.this$0.views.setSharingButtonVisibility(status.isError());
        this.this$0.views.setSharingButtonEnabled(status.isError());
        if (status.isSucces()) {
            this.this$0.log("onVideoHide, unshared state is: true");
            MdxMiniPlayerFrag.state.isVideoUnshared = true;
            MdxMiniPlayerFrag.dontShareIdSet.add(this.val$id);
        }
        this.this$0.log("DEBUG: onVideoHide status: " + status.getStatusCode());
    }
}
