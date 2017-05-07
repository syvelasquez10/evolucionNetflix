// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.event.nrdp.media.NccpError;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.util.StatusUtils;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.android.widget.UpdateDialog$Builder;
import android.content.Context;
import com.netflix.mediaclient.android.widget.AlertDialogFactory;
import com.netflix.mediaclient.service.logging.client.model.ActionOnUIError;
import com.netflix.mediaclient.service.logging.client.model.RootCause;
import com.netflix.mediaclient.media.PlayerType;
import com.netflix.mediaclient.event.nrdp.media.Error;
import com.netflix.mediaclient.event.nrdp.media.NccpNetworkingError;
import com.netflix.mediaclient.event.android.NetworkError;
import org.json.JSONException;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$TwoButtonAlertDialogDescriptor;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$AlertDialogDescriptor;
import com.netflix.mediaclient.event.nrdp.media.NccpActionId;
import com.netflix.mediaclient.event.nrdp.media.MediaEvent;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import android.os.Handler;
import java.util.Locale;
import com.netflix.mediaclient.event.UIEvent;
import com.netflix.mediaclient.event.nrdp.device.ReasonCode;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;

class ErrorManager$2 implements Runnable
{
    final /* synthetic */ ErrorManager this$0;
    final /* synthetic */ JSONObject val$json;
    
    ErrorManager$2(final ErrorManager this$0, final JSONObject val$json) {
        this.this$0 = this$0;
        this.val$json = val$json;
    }
    
    @Override
    public void run() {
        Log.d("ErrorManager", "Publish NCCP reason code event to UI");
        this.this$0.context.getNetflixApplication().publishEvent(new ReasonCode(this.val$json));
        Log.d("ErrorManager", "Exit from playback after UI is alerted to handle");
        this.this$0.context.finish();
    }
}
