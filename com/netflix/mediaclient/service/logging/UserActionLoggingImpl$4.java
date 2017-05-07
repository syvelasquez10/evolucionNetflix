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
import com.netflix.mediaclient.service.logging.uiaction.LoginSession;
import com.netflix.mediaclient.service.logging.uiaction.EditProfileSession;
import com.netflix.mediaclient.service.logging.uiaction.DeleteProfileSession;
import com.netflix.mediaclient.service.logging.uiaction.AddToPlaylistSession;
import com.netflix.mediaclient.service.logging.uiaction.AddProfileSession;
import com.netflix.mediaclient.service.logging.uiaction.AcknowledgeSignupSession;
import com.netflix.mediaclient.servicemgr.UserActionLogging;
import com.netflix.mediaclient.service.logging.uiaction.model.NavigationEndedEvent;
import com.netflix.mediaclient.service.logging.uiaction.NavigationSession;
import com.netflix.mediaclient.service.logging.client.LoggingSession;
import com.netflix.mediaclient.service.logging.client.model.Event;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.service.logging.client.model.DataContext;

class UserActionLoggingImpl$4 implements Runnable
{
    final /* synthetic */ UserActionLoggingImpl this$0;
    final /* synthetic */ DataContext val$dataContext;
    final /* synthetic */ UIError val$error;
    final /* synthetic */ IClientLogging$CompletionReason val$reason;
    final /* synthetic */ IClientLogging$ModalView val$view;
    
    UserActionLoggingImpl$4(final UserActionLoggingImpl this$0, final IClientLogging$ModalView val$view, final IClientLogging$CompletionReason val$reason, final UIError val$error, final DataContext val$dataContext) {
        this.this$0 = this$0;
        this.val$view = val$view;
        this.val$reason = val$reason;
        this.val$error = val$error;
        this.val$dataContext = val$dataContext;
    }
    
    @Override
    public void run() {
        Log.d("nf_log", "Navigation session ended");
        final NavigationSession access$500 = this.this$0.mNavigationSession;
        if (access$500 == null) {
            Log.w("nf_log", "Navigation session does NOT exist!");
            return;
        }
        final NavigationEndedEvent endedEvent = access$500.createEndedEvent(this.val$view, this.val$reason, this.val$error);
        if (endedEvent == null) {
            Log.d("nf_log", "We stayed in same view, cancel session.");
        }
        else {
            Log.d("nf_log", "Navigation session end event posting...");
            this.this$0.populateEvent(endedEvent, this.val$dataContext, this.this$0.mNavigationSession.getView());
            this.this$0.mEventHandler.post(endedEvent);
            Log.d("nf_log", "Navigation session end event posted.");
        }
        this.this$0.mEventHandler.removeSession(access$500);
        this.this$0.mNavigationSession = null;
    }
}
