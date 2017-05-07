// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.media.PlayerType;
import com.netflix.mediaclient.service.error.action.RestartApplicationAction;
import com.netflix.mediaclient.service.error.action.ResetApplicationAction;
import com.netflix.mediaclient.event.nrdp.media.Error;
import com.netflix.mediaclient.event.nrdp.media.NccpNetworkingError;
import com.netflix.mediaclient.event.network.NetworkError;
import com.netflix.mediaclient.event.nrdp.media.MediaEvent;
import org.json.JSONObject;
import org.json.JSONException;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.error.action.ForceExitAction;
import com.netflix.mediaclient.service.error.action.UnregisterAction;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$AlertDialogDescriptor;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$TwoButtonAlertDialogDescriptor;
import com.netflix.mediaclient.service.error.action.LaunchHelpInBrowserAction;
import com.netflix.mediaclient.service.error.action.ExitPlayerAction;
import com.netflix.mediaclient.service.error.ErrorDescriptor;
import com.netflix.mediaclient.event.nrdp.media.NccpActionId;
import java.util.Locale;
import com.netflix.mediaclient.Log;

final class PlayerErrorDialogDescriptorFactory$2 implements Runnable
{
    final /* synthetic */ PlayerFragment val$fragment;
    final /* synthetic */ PlayerErrorDialogDescriptorFactory$LinkTag val$link;
    
    PlayerErrorDialogDescriptorFactory$2(final PlayerErrorDialogDescriptorFactory$LinkTag val$link, final PlayerFragment val$fragment) {
        this.val$link = val$link;
        this.val$fragment = val$fragment;
    }
    
    @Override
    public void run() {
        String s;
        if (this.val$link.href.toLowerCase(PlayerErrorDialogDescriptorFactory.US_LOCALE).trim().startsWith("http")) {
            s = this.val$link.href;
        }
        else {
            s = "http://www.netflix.com" + "/" + this.val$link.href;
        }
        Log.d("ErrorManager", "Launch browser");
        this.val$fragment.runOnUiThread(new PlayerErrorDialogDescriptorFactory$2$1(this, s));
    }
}
