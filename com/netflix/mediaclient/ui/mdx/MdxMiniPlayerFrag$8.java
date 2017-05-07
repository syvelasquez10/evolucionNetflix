// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.ui.common.PlaybackLauncher;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.BroadcastReceiver;
import android.os.Bundle;
import com.netflix.mediaclient.util.MdxUtils$SetVideoRatingCallback;
import com.netflix.mediaclient.util.MdxUtils;
import com.netflix.mediaclient.servicemgr.model.Playable;
import android.view.View;
import com.netflix.mediaclient.ui.common.PlayContext;
import android.view.KeyEvent;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;
import com.netflix.mediaclient.servicemgr.ServiceManagerUtils;
import com.netflix.mediaclient.servicemgr.model.VideoType;
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
import com.netflix.mediaclient.service.mdx.MdxErrorHandler;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.ui.common.LanguageSelector$LanguageSelectorCallback;
import com.netflix.mediaclient.ui.common.LanguageSelector;
import android.os.Handler;
import com.netflix.mediaclient.servicemgr.IMdx;
import com.netflix.mediaclient.servicemgr.model.details.VideoDetails;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import java.util.Set;
import com.netflix.mediaclient.util.MdxUtils$MdxTargetSelectionDialogInterface;
import com.netflix.mediaclient.ui.details.EpisodeRowView$EpisodeRowListener;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.mediaclient.service.mdx.MdxKeyEventHandler$MdxKeyEventCallbacks;

class MdxMiniPlayerFrag$8 implements MdxKeyEventHandler$MdxKeyEventCallbacks
{
    final /* synthetic */ MdxMiniPlayerFrag this$0;
    
    MdxMiniPlayerFrag$8(final MdxMiniPlayerFrag this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public int getVolumeAsPercent() {
        return this.this$0.getVolume();
    }
    
    @Override
    public void onVolumeSet(final int mostRecentVolume) {
        MdxMiniPlayerFrag.state.mostRecentVolume = mostRecentVolume;
    }
}
