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
import android.content.Intent;
import android.net.Uri;
import com.netflix.mediaclient.Log;

class ErrorManager$3$1 implements Runnable
{
    final /* synthetic */ ErrorManager$3 this$1;
    final /* synthetic */ String val$urlLink;
    
    ErrorManager$3$1(final ErrorManager$3 this$1, final String val$urlLink) {
        this.this$1 = this$1;
        this.val$urlLink = val$urlLink;
    }
    
    @Override
    public void run() {
        if (Log.isLoggable("ErrorManager", 3)) {
            Log.d("ErrorManager", "Open browser to " + this.val$urlLink);
        }
        this.this$1.this$0.context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.val$urlLink)));
        Log.d("ErrorManager", "Exit from playback after browser is started");
        this.this$1.this$0.context.finish();
    }
}
