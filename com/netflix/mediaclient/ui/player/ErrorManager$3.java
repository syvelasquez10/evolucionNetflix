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
import org.json.JSONObject;
import org.json.JSONException;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$TwoButtonAlertDialogDescriptor;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$AlertDialogDescriptor;
import com.netflix.mediaclient.event.nrdp.media.NccpActionId;
import com.netflix.mediaclient.event.nrdp.media.MediaEvent;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import android.os.Handler;
import java.util.Locale;
import com.netflix.mediaclient.Log;

class ErrorManager$3 implements Runnable
{
    final /* synthetic */ ErrorManager this$0;
    final /* synthetic */ ErrorManager$LinkTag val$link;
    
    ErrorManager$3(final ErrorManager this$0, final ErrorManager$LinkTag val$link) {
        this.this$0 = this$0;
        this.val$link = val$link;
    }
    
    @Override
    public void run() {
        String s;
        if (this.val$link.href.toLowerCase(ErrorManager.US_LOCALE).trim().startsWith("http")) {
            s = this.val$link.href;
        }
        else {
            s = "http://www.netflix.com" + "/" + this.val$link.href;
        }
        Log.d("ErrorManager", "Launch browser");
        this.this$0.context.runOnUiThread((Runnable)new ErrorManager$3$1(this, s));
    }
}
