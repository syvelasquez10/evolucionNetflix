// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.ui.common.PlaybackLauncher;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import com.netflix.mediaclient.service.mdx.MdxErrorHandler$ErrorHandlerCallbacks;
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
import java.util.HashSet;
import com.netflix.mediaclient.service.mdx.MdxKeyEventHandler;
import com.netflix.mediaclient.service.mdx.MdxKeyEventHandler$MdxKeyEventCallbacks;
import com.netflix.mediaclient.service.mdx.MdxErrorHandler;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.ui.common.LanguageSelector;
import android.os.Handler;
import com.netflix.mediaclient.servicemgr.IMdx;
import com.netflix.mediaclient.servicemgr.model.details.VideoDetails;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import java.util.Set;
import com.netflix.mediaclient.util.MdxUtils$MdxTargetSelectionDialogInterface;
import com.netflix.mediaclient.ui.details.EpisodeRowView$EpisodeRowListener;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import android.app.Dialog;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.media.Language;
import com.netflix.mediaclient.ui.common.LanguageSelector$LanguageSelectorCallback;

class MdxMiniPlayerFrag$6 implements LanguageSelector$LanguageSelectorCallback
{
    final /* synthetic */ MdxMiniPlayerFrag this$0;
    
    MdxMiniPlayerFrag$6(final MdxMiniPlayerFrag this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void languageChanged(final Language language, final boolean b) {
        Log.v("MdxMiniPlayerFrag", "Language changed via dialog: " + language);
        if (this.this$0.remotePlayer != null) {
            this.this$0.remotePlayer.changeLanguage(language);
            this.this$0.remotePlayer.requestAudioAndSubtitleData();
        }
        this.this$0.updateLanguage();
    }
    
    @Override
    public void updateDialog(final Dialog dialog) {
        this.this$0.log("Updating dialog");
        this.this$0.activity.updateVisibleDialog(dialog);
    }
    
    @Override
    public void userCanceled() {
        this.this$0.log("User canceled selection");
    }
    
    @Override
    public boolean wasPlaying() {
        return false;
    }
}
