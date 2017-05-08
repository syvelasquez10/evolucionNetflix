// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import com.netflix.mediaclient.service.logging.signin.model.SignInRequestSessionEnded;
import com.netflix.mediaclient.service.logging.signin.model.CredentialStoreSessionEnded;
import com.netflix.mediaclient.service.logging.signin.model.CredentialRetrievalSessionEnded;
import com.netflix.mediaclient.service.logging.client.LoggingSession;
import com.netflix.mediaclient.service.logging.client.model.Event;
import java.io.Serializable;
import com.netflix.mediaclient.servicemgr.SignInLogging$CredentialSaved;
import com.netflix.mediaclient.servicemgr.SignInLogging$SignInType;
import com.netflix.mediaclient.servicemgr.SignInLogging$CredentialService;
import org.json.JSONException;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import com.netflix.mediaclient.service.logging.signin.SignInRequestSession;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.service.logging.signin.CredentialStoreSession;
import com.netflix.mediaclient.service.logging.signin.CredentialRetrievalSession;
import com.netflix.mediaclient.servicemgr.SignInLogging;

final class SignInLoggingImpl implements SignInLogging
{
    private static final String TAG = "nf_log_signin";
    private CredentialRetrievalSession mCredentialRetrievalSession;
    private CredentialStoreSession mCredentialStoreSession;
    private DataContext mDataContext;
    private EventHandler mEventHandler;
    private SignInRequestSession mSignInRequestSession;
    
    SignInLoggingImpl(final EventHandler mEventHandler) {
        this.mEventHandler = mEventHandler;
    }
    
    private void handleCredentialRetrievalEnd(final Intent intent) {
        IClientLogging$CompletionReason value = null;
        Log.d("nf_log_signin", "CREDENTIAL_RETRIEVAL_ENDED");
        final String stringExtra = intent.getStringExtra("reason");
        final String stringExtra2 = intent.getStringExtra("error");
        while (true) {
            try {
                final UIError instance = UIError.createInstance(stringExtra2);
                if (StringUtils.isNotEmpty(stringExtra)) {
                    value = IClientLogging$CompletionReason.valueOf(stringExtra);
                }
                this.endCredentialRetrieval(value, instance);
            }
            catch (JSONException ex) {
                Log.e("nf_log_signin", "Failed JSON", (Throwable)ex);
                final UIError instance = null;
                continue;
            }
            break;
        }
    }
    
    private void handleCredentialRetrievalStart(final Intent intent) {
        Log.d("nf_log_signin", "CREDENTIAL_RETRIEVAL_START");
        final String stringExtra = intent.getStringExtra("credSavedService");
        SignInLogging$CredentialService value = null;
        if (StringUtils.isNotEmpty(stringExtra)) {
            value = SignInLogging$CredentialService.valueOf(stringExtra);
        }
        this.startCredentialRetrieval(value);
    }
    
    private void handleCredentialStoreEnd(final Intent intent) {
        IClientLogging$CompletionReason value = null;
        Log.d("nf_log_signin", "CREDENTIAL_SAVE_ENDED");
        final String stringExtra = intent.getStringExtra("reason");
        final String stringExtra2 = intent.getStringExtra("error");
        while (true) {
            try {
                final UIError instance = UIError.createInstance(stringExtra2);
                if (StringUtils.isNotEmpty(stringExtra)) {
                    value = IClientLogging$CompletionReason.valueOf(stringExtra);
                }
                this.endCredentialStore(value, instance);
            }
            catch (JSONException ex) {
                Log.e("nf_log_signin", "Failed JSON", (Throwable)ex);
                final UIError instance = null;
                continue;
            }
            break;
        }
    }
    
    private void handleCredentialStoreStart(final Intent intent) {
        Log.d("nf_log_signin", "CREDENTIAL_SAVE_START");
        final String stringExtra = intent.getStringExtra("credSavedService");
        SignInLogging$CredentialService value = null;
        if (StringUtils.isNotEmpty(stringExtra)) {
            value = SignInLogging$CredentialService.valueOf(stringExtra);
        }
        this.startCredentialStore(value);
    }
    
    private void handleSignInEnd(final Intent intent) {
    Label_0059_Outer:
        while (true) {
            SignInLogging$CredentialSaved value = null;
            Log.d("nf_log_signin", "SIGNIN_ENDED");
            Serializable s = intent.getStringExtra("reason");
            final String stringExtra = intent.getStringExtra("error");
            Serializable s2 = intent.getStringExtra("type");
            final String stringExtra2 = intent.getStringExtra("credSavedState");
            while (true) {
                Label_0116: {
                    while (true) {
                        while (true) {
                            try {
                                final UIError instance = UIError.createInstance(stringExtra);
                                if (!StringUtils.isNotEmpty((String)s)) {
                                    break Label_0116;
                                }
                                s = IClientLogging$CompletionReason.valueOf((String)s);
                                if (StringUtils.isNotEmpty((String)s2)) {
                                    s2 = SignInLogging$SignInType.valueOf((String)s2);
                                    if (StringUtils.isNotEmpty(stringExtra2)) {
                                        value = SignInLogging$CredentialSaved.valueOf(stringExtra2);
                                    }
                                    this.endSignInRequest((SignInLogging$SignInType)s2, (IClientLogging$CompletionReason)s, instance, value);
                                    return;
                                }
                            }
                            catch (JSONException ex) {
                                Log.e("nf_log_signin", "Failed JSON", (Throwable)ex);
                                final UIError instance = null;
                                continue Label_0059_Outer;
                            }
                            break;
                        }
                        s2 = null;
                        continue;
                    }
                }
                s = null;
                continue;
            }
        }
    }
    
    private void handleSignInStart(final Intent intent) {
        Log.d("nf_log_signin", "SIGNIN_START");
        final String stringExtra = intent.getStringExtra("type");
        SignInLogging$SignInType value = null;
        if (StringUtils.isNotEmpty(stringExtra)) {
            value = SignInLogging$SignInType.valueOf(stringExtra);
        }
        this.startSignInRequest(value);
    }
    
    private void populateEvent(final Event event, final DataContext dataContext) {
        if (event == null) {
            return;
        }
        event.setDataContext(dataContext);
    }
    
    @Override
    public void endAllActiveSessions() {
        synchronized (this) {
            this.endSignInRequest(null, IClientLogging$CompletionReason.canceled, null, null);
            this.endCredentialStore(IClientLogging$CompletionReason.canceled, null);
            this.endCredentialRetrieval(IClientLogging$CompletionReason.canceled, null);
        }
    }
    
    @Override
    public void endCredentialRetrieval(final IClientLogging$CompletionReason clientLogging$CompletionReason, final Error error) {
        synchronized (this) {
            final CredentialRetrievalSession mCredentialRetrievalSession = this.mCredentialRetrievalSession;
            this.mCredentialRetrievalSession = null;
            if (mCredentialRetrievalSession == null) {
                Log.e("nf_log_signin", "CredentialService retrieval session does NOT exist!");
            }
            else {
                Log.d("nf_log_signin", "CredentialService retrieval session ended");
                final CredentialRetrievalSessionEnded endedEvent = mCredentialRetrievalSession.createEndedEvent(clientLogging$CompletionReason, error);
                this.populateEvent(endedEvent, this.mDataContext);
                this.mEventHandler.removeSession(mCredentialRetrievalSession);
                Log.d("nf_log_signin", "CredentialService retrieval session end event posting...");
                this.mEventHandler.post(endedEvent);
                Log.d("nf_log_signin", "CredentialService retrieval session end event posted.");
            }
        }
    }
    
    @Override
    public void endCredentialStore(final IClientLogging$CompletionReason clientLogging$CompletionReason, final Error error) {
        synchronized (this) {
            final CredentialStoreSession mCredentialStoreSession = this.mCredentialStoreSession;
            this.mCredentialStoreSession = null;
            if (mCredentialStoreSession == null) {
                Log.e("nf_log_signin", "CredentialService store session does NOT exist!");
            }
            else {
                Log.d("nf_log_signin", "CredentialService store session ended");
                final CredentialStoreSessionEnded endedEvent = mCredentialStoreSession.createEndedEvent(clientLogging$CompletionReason, error);
                this.populateEvent(endedEvent, this.mDataContext);
                this.mEventHandler.removeSession(mCredentialStoreSession);
                Log.d("nf_log_signin", "CredentialService store session end event posting...");
                this.mEventHandler.post(endedEvent);
                Log.d("nf_log_signin", "CredentialService store session end event posted.");
            }
        }
    }
    
    @Override
    public void endSignInRequest(final SignInLogging$SignInType signInLogging$SignInType, final IClientLogging$CompletionReason clientLogging$CompletionReason, final Error error, final SignInLogging$CredentialSaved signInLogging$CredentialSaved) {
        synchronized (this) {
            final SignInRequestSession mSignInRequestSession = this.mSignInRequestSession;
            this.mSignInRequestSession = null;
            if (mSignInRequestSession == null) {
                Log.e("nf_log_signin", "SignInRequest session for sign-in type " + signInLogging$SignInType + " does NOT exist!");
            }
            else {
                Log.d("nf_log_signin", "SignInRequest session ended");
                final SignInRequestSessionEnded endedEvent = mSignInRequestSession.createEndedEvent(clientLogging$CompletionReason, error, signInLogging$CredentialSaved);
                this.populateEvent(endedEvent, this.mDataContext);
                this.mEventHandler.removeSession(mSignInRequestSession);
                Log.d("nf_log_signin", "SignInRequest session end event posting...");
                this.mEventHandler.post(endedEvent);
                Log.d("nf_log_signin", "SignInRequest session end event posted.");
            }
        }
    }
    
    public boolean handleIntent(final Intent intent) {
        final String action = intent.getAction();
        if ("com.netflix.mediaclient.intent.action.LOG_SIGNIN_START".equals(action)) {
            this.handleSignInStart(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_SIGNIN_ENDED".equals(action)) {
            this.handleSignInEnd(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_SIGNIN_CREDENTIAL_RETRIEVAL_START".equals(action)) {
            this.handleCredentialRetrievalStart(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_SIGNIN_CREDENTIAL_RETRIEVAL_ENDED".equals(action)) {
            this.handleCredentialRetrievalEnd(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_SIGNIN_CREDENTIAL_SAVE_START".equals(action)) {
            this.handleCredentialStoreStart(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_SIGNIN_CREDENTIAL_SAVE_ENDED".equals(action)) {
            this.handleCredentialStoreEnd(intent);
            return true;
        }
        if (Log.isLoggable()) {
            Log.d("nf_log_signin", "We do not support action " + action);
        }
        return false;
    }
    
    @Override
    public void startCredentialRetrieval(final SignInLogging$CredentialService signInLogging$CredentialService) {
        synchronized (this) {
            if (this.mCredentialRetrievalSession != null) {
                Log.e("nf_log_signin", "Credential retrieval session already exist!");
            }
            else {
                Log.d("nf_log_signin", "Credential retrieval session starting...");
                final CredentialRetrievalSession mCredentialRetrievalSession = new CredentialRetrievalSession(signInLogging$CredentialService);
                this.mEventHandler.addSession(mCredentialRetrievalSession);
                this.mCredentialRetrievalSession = mCredentialRetrievalSession;
                Log.d("nf_log_signin", "Credential retrieval session done.");
            }
        }
    }
    
    @Override
    public void startCredentialStore(final SignInLogging$CredentialService signInLogging$CredentialService) {
        synchronized (this) {
            if (this.mCredentialStoreSession != null) {
                Log.e("nf_log_signin", "Credential store session already exist!");
            }
            else {
                Log.d("nf_log_signin", "Credential store session starting...");
                final CredentialStoreSession mCredentialStoreSession = new CredentialStoreSession(signInLogging$CredentialService);
                this.mEventHandler.addSession(mCredentialStoreSession);
                this.mCredentialStoreSession = mCredentialStoreSession;
                Log.d("nf_log_signin", "Credential store session done.");
            }
        }
    }
    
    @Override
    public void startSignInRequest(final SignInLogging$SignInType signInLogging$SignInType) {
        synchronized (this) {
            if (this.mSignInRequestSession == null) {
                Log.d("nf_log_signin", "SignInRequest session starting...");
                this.mSignInRequestSession = new SignInRequestSession(signInLogging$SignInType);
                this.mEventHandler.addSession(this.mSignInRequestSession);
                Log.d("nf_log_signin", "SignInRequest session start done.");
            }
            else {
                Log.e("nf_log_signin", "SignInRequest session already exist!");
            }
        }
    }
}
