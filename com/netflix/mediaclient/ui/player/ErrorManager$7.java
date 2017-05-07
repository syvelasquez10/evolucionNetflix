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
import com.netflix.mediaclient.service.NetflixService;
import android.content.Intent;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;

class ErrorManager$7 implements Runnable
{
    final /* synthetic */ ErrorManager this$0;
    
    ErrorManager$7(final ErrorManager this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        AndroidUtils.clearApplicationData((Context)this.this$0.context);
        Log.e("ErrorManager", "resetApp");
        if (this.this$0.context instanceof NetflixActivity) {
            NetflixActivity.finishAllActivities((Context)this.this$0.context);
        }
        final Intent intent = new Intent();
        intent.setClass((Context)this.this$0.context, (Class)NetflixService.class);
        this.this$0.context.stopService(intent);
    }
}