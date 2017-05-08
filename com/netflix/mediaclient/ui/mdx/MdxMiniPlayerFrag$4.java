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
import com.netflix.mediaclient.util.AndroidUtils;
import android.app.DialogFragment;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.mdx.MdxKeyEventHandler;
import com.netflix.mediaclient.service.mdx.MdxKeyEventHandler$MdxKeyEventCallbacks;
import com.netflix.mediaclient.service.mdx.MdxErrorHandler;
import com.netflix.mediaclient.ui.common.LanguageSelector;
import android.os.Handler;
import com.netflix.mediaclient.servicemgr.IMdx;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import android.app.Dialog;
import android.content.Context;
import com.netflix.mediaclient.util.l10n.LanguageUtils;
import com.netflix.mediaclient.media.Language;
import com.netflix.mediaclient.ui.common.LanguageSelector$LanguageSelectorCallback;

class MdxMiniPlayerFrag$4 implements LanguageSelector$LanguageSelectorCallback
{
    final /* synthetic */ MdxMiniPlayerFrag this$0;
    
    MdxMiniPlayerFrag$4(final MdxMiniPlayerFrag this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void languageChanged(final Language language, final boolean b) {
        this.this$0.log("Language changed via dialog: " + language);
        if (this.this$0.remotePlayer != null) {
            this.this$0.remotePlayer.changeLanguage(language);
            LanguageUtils.saveUserChoice((Context)this.this$0.getActivity(), language);
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
