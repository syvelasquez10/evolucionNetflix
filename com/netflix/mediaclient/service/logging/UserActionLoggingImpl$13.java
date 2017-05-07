// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import org.json.JSONObject;
import com.netflix.mediaclient.servicemgr.UserActionLogging$PaymentType;
import com.netflix.mediaclient.service.logging.uiaction.model.SelectProfileEndedEvent;
import com.netflix.mediaclient.service.logging.uiaction.model.EditProfileEndedEvent;
import com.netflix.mediaclient.service.logging.uiaction.model.DeleteProfileEndedEvent;
import com.netflix.mediaclient.service.logging.uiaction.model.AddProfileEndedEvent;
import com.netflix.mediaclient.servicemgr.UserActionLogging$Streams;
import com.netflix.mediaclient.media.PlayerType;
import com.netflix.mediaclient.servicemgr.UserActionLogging$RememberProfile;
import com.netflix.mediaclient.servicemgr.UserActionLogging$Profile;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import java.io.Serializable;
import org.json.JSONException;
import com.netflix.mediaclient.util.StringUtils;
import android.content.Intent;
import java.util.concurrent.ConcurrentHashMap;
import com.netflix.mediaclient.service.logging.uiaction.UpgradeStreamsSession;
import com.netflix.mediaclient.service.logging.uiaction.SubmitPaymentSession;
import com.netflix.mediaclient.service.logging.uiaction.StartPlaySession;
import com.netflix.mediaclient.service.logging.uiaction.SelectProfileSession;
import com.netflix.mediaclient.service.logging.uiaction.SearchSession;
import java.util.Map;
import com.netflix.mediaclient.service.logging.uiaction.SayThanksSession;
import com.netflix.mediaclient.service.logging.uiaction.RemoveFromPlaylistSession;
import com.netflix.mediaclient.service.logging.uiaction.RegisterSession;
import com.netflix.mediaclient.service.logging.uiaction.RateTitleSession;
import com.netflix.mediaclient.service.logging.uiaction.NavigationSession;
import com.netflix.mediaclient.service.logging.uiaction.LoginSession;
import com.netflix.mediaclient.service.logging.uiaction.EditProfileSession;
import com.netflix.mediaclient.service.logging.uiaction.DeleteProfileSession;
import com.netflix.mediaclient.service.logging.uiaction.AddToPlaylistSession;
import com.netflix.mediaclient.service.logging.uiaction.AddProfileSession;
import com.netflix.mediaclient.service.logging.uiaction.AcknowledgeSignupSession;
import com.netflix.mediaclient.servicemgr.UserActionLogging;
import com.netflix.mediaclient.service.logging.uiaction.model.NewLolomoEndedEvent;
import com.netflix.mediaclient.service.logging.uiaction.NewLolomoSession;
import com.netflix.mediaclient.service.logging.client.LoggingSession;
import com.netflix.mediaclient.service.logging.client.model.Event;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.service.logging.client.model.DataContext;

class UserActionLoggingImpl$13 implements Runnable
{
    final /* synthetic */ UserActionLoggingImpl this$0;
    final /* synthetic */ DataContext val$dataContext;
    final /* synthetic */ IClientLogging$ModalView val$dest;
    final /* synthetic */ UIError val$error;
    final /* synthetic */ String val$mercuryEventGuid;
    final /* synthetic */ String val$mercuryMessageGuid;
    final /* synthetic */ IClientLogging$CompletionReason val$reason;
    final /* synthetic */ String val$renoCause;
    final /* synthetic */ long val$renoCreationTimestamp;
    final /* synthetic */ String val$renoMessageGuid;
    
    UserActionLoggingImpl$13(final UserActionLoggingImpl this$0, final IClientLogging$CompletionReason val$reason, final UIError val$error, final IClientLogging$ModalView val$dest, final String val$renoCause, final String val$renoMessageGuid, final long val$renoCreationTimestamp, final String val$mercuryMessageGuid, final String val$mercuryEventGuid, final DataContext val$dataContext) {
        this.this$0 = this$0;
        this.val$reason = val$reason;
        this.val$error = val$error;
        this.val$dest = val$dest;
        this.val$renoCause = val$renoCause;
        this.val$renoMessageGuid = val$renoMessageGuid;
        this.val$renoCreationTimestamp = val$renoCreationTimestamp;
        this.val$mercuryMessageGuid = val$mercuryMessageGuid;
        this.val$mercuryEventGuid = val$mercuryEventGuid;
        this.val$dataContext = val$dataContext;
    }
    
    @Override
    public void run() {
        if (this.this$0.mNewLolomoSession == null) {
            Log.w("nf_log", "NewLolomoSession does NOT exist!");
            return;
        }
        final NewLolomoEndedEvent endedEvent = this.this$0.mNewLolomoSession.createEndedEvent(this.val$reason, this.val$error, this.val$dest, this.val$renoCause, this.val$renoMessageGuid, this.val$renoCreationTimestamp, this.val$mercuryMessageGuid, this.val$mercuryEventGuid);
        if (endedEvent == null) {
            Log.d("nf_log", "NewLolomoSession still waits on session id, do not post at this time.");
            return;
        }
        this.this$0.populateEvent(endedEvent, this.val$dataContext, this.val$dest);
        this.this$0.mEventHandler.removeSession(this.this$0.mNewLolomoSession);
        Log.d("nf_log", "NewLolomoSession end event posting...");
        this.this$0.mEventHandler.post(endedEvent);
        this.this$0.mNewLolomoSession = null;
        Log.d("nf_log", "NewLolomoSession end event posted.");
    }
}
