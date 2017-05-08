// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import com.netflix.mediaclient.service.logging.client.model.UIError;
import org.json.JSONException;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging$EntryPoint;
import com.netflix.mediaclient.util.StringUtils;
import android.content.Intent;
import com.netflix.mediaclient.service.logging.client.LoggingSession;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging$TerminationReason;
import com.netflix.mediaclient.service.logging.customerSupport.model.DialScreenDismissedEvent;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging$Action;
import com.netflix.mediaclient.service.logging.client.model.Event;
import com.netflix.mediaclient.service.logging.customerSupport.model.BackToDialScreenEvent;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging$ReturnToDialScreenFrom;
import com.netflix.mediaclient.service.logging.apm.model.Orientation;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging$CallQuality;
import com.netflix.mediaclient.service.logging.customerSupport.HelpRequestSession;
import com.netflix.mediaclient.service.logging.customerSupport.CustomerSupportCallSession;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging;

class CustomerServiceLoggingImpl implements CustomerServiceLogging
{
    private static final String TAG = "nf_log_cs";
    private CustomerSupportCallSession mCustomerSupportCallSession;
    private EventHandler mEventHandler;
    private HelpRequestSession mHelpRequestSession;
    
    CustomerServiceLoggingImpl(final EventHandler mEventHandler) {
        this.mEventHandler = mEventHandler;
    }
    
    @Override
    public void callConnected(final CustomerServiceLogging$CallQuality customerServiceLogging$CallQuality) {
        if (this.mCustomerSupportCallSession != null) {
            Log.d("nf_log_cs", "Call session exist, call connected");
            this.mCustomerSupportCallSession.callConnected(customerServiceLogging$CallQuality);
            return;
        }
        Log.e("nf_log_cs", "Call session does NOT exist, and call connected API is called...");
    }
    
    @Override
    public void callQualityChanged(final CustomerServiceLogging$CallQuality callQuality) {
        if (this.mCustomerSupportCallSession != null) {
            Log.d("nf_log_cs", "Call session exist, change its quality");
            this.mCustomerSupportCallSession.setCallQuality(callQuality);
            return;
        }
        Log.e("nf_log_cs", "Call session does NOT exist, and change call quality API is called...");
    }
    
    @Override
    public void createBackToDialScreenEvent(final IClientLogging$ModalView clientLogging$ModalView, final Orientation orientation, final CustomerServiceLogging$ReturnToDialScreenFrom customerServiceLogging$ReturnToDialScreenFrom) {
        if (Log.isLoggable()) {
            Log.d("nf_log_cs", "Back to dial screen event using " + customerServiceLogging$ReturnToDialScreenFrom);
        }
        this.mEventHandler.post(new BackToDialScreenEvent(clientLogging$ModalView, orientation, customerServiceLogging$ReturnToDialScreenFrom));
        Log.d("nf_log_cs", "Back to dial screen event posted.");
    }
    
    @Override
    public void createDialScreenDismissedEvent(final CustomerServiceLogging$Action customerServiceLogging$Action) {
        if (Log.isLoggable()) {
            Log.d("nf_log_cs", "Dial screen dismissed event caused by " + customerServiceLogging$Action);
        }
        this.mEventHandler.post(new DialScreenDismissedEvent(customerServiceLogging$Action));
        Log.d("nf_log_cs", "Dial screen dismissed event posted.");
    }
    
    @Override
    public void endAllActiveSessions() {
        synchronized (this) {
            this.endCustomerSupportCallSession(CustomerServiceLogging$TerminationReason.canceledByUserAfterConnected, IClientLogging$CompletionReason.canceled, null);
            this.endHelpRequestSession(null, null, IClientLogging$CompletionReason.canceled, null);
        }
    }
    
    @Override
    public void endCustomerSupportCallSession(final CustomerServiceLogging$TerminationReason customerServiceLogging$TerminationReason, final IClientLogging$CompletionReason clientLogging$CompletionReason, final Error error) {
        if (this.mCustomerSupportCallSession != null) {
            Log.d("nf_log_cs", "Call session end started");
            this.mEventHandler.post(this.mCustomerSupportCallSession.createCustomerSupportCallSessionEndedEvent(customerServiceLogging$TerminationReason, clientLogging$CompletionReason, error));
            this.mEventHandler.removeSession(this.mCustomerSupportCallSession);
            this.mCustomerSupportCallSession = null;
            Log.d("nf_log_cs", "Call session end done.");
        }
    }
    
    @Override
    public void endHelpRequestSession(final CustomerServiceLogging$Action customerServiceLogging$Action, final String s, final IClientLogging$CompletionReason clientLogging$CompletionReason, final Error error) {
        if (this.mHelpRequestSession != null) {
            Log.d("nf_log_cs", "Help session end started");
            this.mEventHandler.post(this.mHelpRequestSession.createHelpRequestSessionEndedEvent(customerServiceLogging$Action, s, clientLogging$CompletionReason, error));
            this.mEventHandler.removeSession(this.mHelpRequestSession);
            this.mHelpRequestSession = null;
            Log.d("nf_log_cs", "help session end done.");
        }
    }
    
    public boolean handleIntent(final Intent intent) {
        Error instance = null;
        final UIError uiError = null;
        final CustomerServiceLogging$Action customerServiceLogging$Action = null;
        final Orientation orientation = null;
        final CustomerServiceLogging$EntryPoint customerServiceLogging$EntryPoint = null;
        final String action = intent.getAction();
        if ("com.netflix.mediaclient.intent.action.LOG_CS_HELP_SESSION_START".equals(action)) {
            Log.d("nf_log_cs", "HELP_SESSION_START");
            final String stringExtra = intent.getStringExtra("entry");
            CustomerServiceLogging$EntryPoint value = customerServiceLogging$EntryPoint;
            if (StringUtils.isNotEmpty(stringExtra)) {
                value = CustomerServiceLogging$EntryPoint.valueOf(stringExtra);
            }
            this.startHelpRequestSession(value);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_CS_HELP_SESSION_ENDED".equals(action)) {
            Log.d("nf_log_cs", "HELP_SESSION_ENDED");
            final CustomerServiceLogging$Action value2 = CustomerServiceLogging$Action.valueOf(intent.getStringExtra("action"));
            final IClientLogging$CompletionReason value3 = IClientLogging$CompletionReason.valueOf(intent.getStringExtra("reason"));
            while (true) {
                try {
                    instance = Error.createInstance(intent.getStringExtra("error"));
                    this.endHelpRequestSession(value2, intent.getStringExtra("url"), value3, instance);
                    return true;
                }
                catch (JSONException ex) {
                    Log.e("nf_log_cs", "Failure to create Error", (Throwable)ex);
                    continue;
                }
                break;
            }
        }
        if ("com.netflix.mediaclient.intent.action.LOG_CS_CALL_SESSION_START".equals(action)) {
            Log.d("nf_log_cs", "CALL_SESSION_START");
            this.startCustomerSupportCallSession(intent.getStringExtra("uuid"), intent.getBooleanExtra("displayed", false));
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_CS_CALL_SESSION_ENDED".equals(action)) {
            Log.d("nf_log_cs", "CALL_SESSION_ENDED");
            final IClientLogging$CompletionReason value4 = IClientLogging$CompletionReason.valueOf(intent.getStringExtra("reason"));
            final CustomerServiceLogging$TerminationReason value5 = CustomerServiceLogging$TerminationReason.valueOf(intent.getStringExtra("terminationReason"));
            while (true) {
                try {
                    final UIError instance2 = Error.createInstance(intent.getStringExtra("error"));
                    this.endCustomerSupportCallSession(value5, value4, instance2);
                    return true;
                }
                catch (JSONException ex2) {
                    Log.e("nf_log_cs", "Failure to create Error", (Throwable)ex2);
                    final UIError instance2 = uiError;
                    continue;
                }
                break;
            }
        }
        if ("com.netflix.mediaclient.intent.action.LOG_CS_CALL_CONNECTED".equals(action)) {
            Log.d("nf_log_cs", "CALL_CONNECTED");
            this.callConnected(CustomerServiceLogging$CallQuality.valueOf(intent.getStringExtra("call_quality")));
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_CS_CALL_SESSION_QUALITY_CHANGED".equals(action)) {
            Log.d("nf_log_cs", "CALL_SESSION_QUALITY_CHANGED");
            this.callQualityChanged(CustomerServiceLogging$CallQuality.valueOf(intent.getStringExtra("call_quality")));
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_CS_CALL_UI_EXIT".equals(action)) {
            Log.d("nf_log_cs", "CALL_UI_EXIT");
            final String stringExtra2 = intent.getStringExtra("action");
            CustomerServiceLogging$Action value6 = customerServiceLogging$Action;
            if (StringUtils.isNotEmpty(stringExtra2)) {
                value6 = CustomerServiceLogging$Action.valueOf(stringExtra2);
            }
            this.createDialScreenDismissedEvent(value6);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_CS_CALL_UI_BACK_TO".equals(action)) {
            Log.d("nf_log_cs", "CALL_UI_BACK_TO");
            final String stringExtra3 = intent.getStringExtra("source");
            IClientLogging$ModalView value7;
            if (StringUtils.isNotEmpty(stringExtra3)) {
                value7 = IClientLogging$ModalView.valueOf(stringExtra3);
            }
            else {
                value7 = null;
            }
            final String stringExtra4 = intent.getStringExtra("using");
            CustomerServiceLogging$ReturnToDialScreenFrom value8;
            if (StringUtils.isNotEmpty(stringExtra4)) {
                value8 = CustomerServiceLogging$ReturnToDialScreenFrom.valueOf(stringExtra4);
            }
            else {
                value8 = null;
            }
            final String stringExtra5 = intent.getStringExtra("orientation");
            Orientation value9 = orientation;
            if (StringUtils.isNotEmpty(stringExtra5)) {
                value9 = Orientation.valueOf(stringExtra5);
            }
            this.createBackToDialScreenEvent(value7, value9, value8);
            return true;
        }
        if (Log.isLoggable()) {
            Log.d("nf_log_cs", "We do not support action " + action);
        }
        return false;
    }
    
    @Override
    public void startCustomerSupportCallSession(final String s, final boolean b) {
        Log.d("nf_log_cs", "Call session start started");
        if (this.mCustomerSupportCallSession != null) {
            Log.w("nf_log_cs", "Call session existed before! It should not happen!");
            return;
        }
        this.mCustomerSupportCallSession = new CustomerSupportCallSession(s, b);
        this.mEventHandler.addSession(this.mCustomerSupportCallSession);
        Log.d("nf_log_cs", "Call session start done.");
    }
    
    @Override
    public void startHelpRequestSession(final CustomerServiceLogging$EntryPoint customerServiceLogging$EntryPoint) {
        Log.d("nf_log_cs", "Help session start started");
        if (this.mHelpRequestSession != null) {
            Log.w("nf_log_cs", "Help session existed before! It should not happen!");
            return;
        }
        this.mHelpRequestSession = new HelpRequestSession(customerServiceLogging$EntryPoint);
        this.mEventHandler.addSession(this.mHelpRequestSession);
        Log.d("nf_log_cs", "Help session start done.");
    }
}
