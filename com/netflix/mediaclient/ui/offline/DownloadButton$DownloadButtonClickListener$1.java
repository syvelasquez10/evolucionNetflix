// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.offline;

import com.netflix.mediaclient.servicemgr.interface_.offline.OfflinePlayableViewData;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflinePlayableUiList;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.app.DialogFragment;
import com.netflix.mediaclient.ui.verifyplay.PinAndAgeVerifier;
import com.netflix.mediaclient.servicemgr.Asset;
import com.netflix.mediaclient.ui.verifyplay.PlayVerifierVault$RequestedBy;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.util.ConnectivityUtils;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import android.view.View;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.verifyplay.PlayVerifierVault;
import com.netflix.mediaclient.ui.verifyplay.PinAndAgeVerifier$PinAndAgeVerifyCallback;

class DownloadButton$DownloadButtonClickListener$1 implements PinAndAgeVerifier$PinAndAgeVerifyCallback
{
    final /* synthetic */ DownloadButton$DownloadButtonClickListener this$1;
    
    DownloadButton$DownloadButtonClickListener$1(final DownloadButton$DownloadButtonClickListener this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void onOfflineDownloadPinAndAgeVerified(final boolean b, final PlayVerifierVault playVerifierVault) {
        if (Log.isLoggable()) {
            Log.i("download_button", "onOfflineDownloadPinAndAgeVerified verified=" + b);
        }
        if (!AndroidUtils.isActivityFinishedOrDestroyed((Context)this.this$1.netflixActivity) && b) {
            this.this$1.netflixActivity.getServiceManager().getOfflineAgent().requestOfflineViewing(this.this$1.playableId, this.this$1.videoType, ((PlayContextProvider)this.this$1.netflixActivity).getPlayContext());
        }
    }
    
    @Override
    public void onPlayVerified(final boolean b, final PlayVerifierVault playVerifierVault) {
    }
}
