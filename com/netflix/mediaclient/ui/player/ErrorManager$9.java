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
import com.netflix.mediaclient.android.widget.AlertDialogFactory;
import com.netflix.mediaclient.service.logging.client.model.ActionOnUIError;
import com.netflix.mediaclient.service.logging.client.model.RootCause;
import com.netflix.mediaclient.media.PlayerType;
import com.netflix.mediaclient.event.nrdp.media.Error;
import com.netflix.mediaclient.event.nrdp.media.NccpNetworkingError;
import com.netflix.mediaclient.event.android.NetworkError;
import org.json.JSONObject;
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
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.Log;

class ErrorManager$9 implements Runnable
{
    final /* synthetic */ ErrorManager this$0;
    
    ErrorManager$9(final ErrorManager this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                Log.d("ErrorManager", "Waiting 1.5 second to exit app");
                this.wait(1500L);
                Log.d("ErrorManager", "Kill app");
                AndroidUtils.forceStop((Context)this.this$0.context);
            }
            catch (Exception ex) {
                Log.w("ErrorManager", "Wait is interrupted", ex);
                continue;
            }
            break;
        }
    }
}
