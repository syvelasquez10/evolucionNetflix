// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util.log;

import com.netflix.mediaclient.servicemgr.SignInLogging$SignInType;
import org.json.JSONException;
import com.netflix.mediaclient.Log;
import android.support.v4.content.LocalBroadcastManager;
import android.content.Intent;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.SignInLogging$CredentialService;
import android.content.Context;
import com.google.android.gms.common.api.Status;
import org.json.JSONObject;
import java.util.List;
import com.netflix.mediaclient.service.logging.client.model.RootCause;
import com.netflix.mediaclient.service.logging.client.model.DeepErrorElement$Debug;
import com.netflix.mediaclient.service.logging.client.model.DeepErrorElement;
import java.util.ArrayList;
import com.netflix.mediaclient.service.logging.client.model.Error;

public final class SignInLogUtils extends ConsolidatedLoggingUtils
{
    public static Error credentialRequestResultToError(final int n) {
        final ArrayList<DeepErrorElement> list = new ArrayList<DeepErrorElement>();
        final DeepErrorElement deepErrorElement = new DeepErrorElement();
        deepErrorElement.setFatal(true);
        deepErrorElement.setErrorCode(String.valueOf(n));
        final DeepErrorElement$Debug debug = new DeepErrorElement$Debug();
        deepErrorElement.setDebug(debug);
        list.add(deepErrorElement);
        final Error error = new Error(RootCause.serverFailure, list);
        try {
            final JSONObject message = new JSONObject();
            message.put("code", n);
            debug.setMessage(message);
            return error;
        }
        catch (Throwable t) {
            return error;
        }
    }
    
    public static Error credentialRequestResultToError(final Status status) {
        if (status == null) {
            return null;
        }
        final ArrayList<DeepErrorElement> list = new ArrayList<DeepErrorElement>();
        final DeepErrorElement deepErrorElement = new DeepErrorElement();
        deepErrorElement.setFatal(true);
        deepErrorElement.setErrorCode(String.valueOf(status.getStatusCode()));
        final DeepErrorElement$Debug debug = new DeepErrorElement$Debug();
        deepErrorElement.setDebug(debug);
        list.add(deepErrorElement);
        final Error error = new Error(RootCause.serverFailure, list);
        try {
            final JSONObject message = new JSONObject();
            message.put("message", (Object)status.getStatusMessage());
            message.put("code", status.getStatusCode());
            debug.setMessage(message);
            return error;
        }
        catch (Throwable t) {
            return error;
        }
    }
    
    public static void reportCredentialRetrievalSessionEnded(final Context context, final SignInLogging$CredentialService signInLogging$CredentialService, final IClientLogging$CompletionReason clientLogging$CompletionReason, final Error error) {
        if (ConsolidatedLoggingUtils.isNull(context, "Context can not be null!")) {
            return;
        }
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_SIGNIN_CREDENTIAL_RETRIEVAL_ENDED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        if (signInLogging$CredentialService != null) {
            intent.putExtra("credSavedService", signInLogging$CredentialService.name());
        }
        if (clientLogging$CompletionReason != null) {
            intent.putExtra("reason", clientLogging$CompletionReason.name());
        }
        while (true) {
            if (error == null) {
                break Label_0080;
            }
            try {
                intent.putExtra("error", error.toJSONObject().toString());
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
            catch (JSONException ex) {
                Log.e("nf_log", "Failed to get JSON string from UIError", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public static void reportCredentialRetrievalSessionStarted(final Context context, final SignInLogging$CredentialService signInLogging$CredentialService) {
        if (ConsolidatedLoggingUtils.isNull(context, "Context can not be null!")) {
            return;
        }
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_SIGNIN_CREDENTIAL_RETRIEVAL_START");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        if (signInLogging$CredentialService != null) {
            intent.putExtra("credSavedService", signInLogging$CredentialService.name());
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportCredentialStoreSessionEnded(final Context context, final SignInLogging$CredentialService signInLogging$CredentialService, final IClientLogging$CompletionReason clientLogging$CompletionReason, final Error error) {
        if (ConsolidatedLoggingUtils.isNull(context, "Context can not be null!")) {
            return;
        }
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_SIGNIN_CREDENTIAL_SAVE_ENDED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        if (signInLogging$CredentialService != null) {
            intent.putExtra("credSavedService", signInLogging$CredentialService.name());
        }
        if (clientLogging$CompletionReason != null) {
            intent.putExtra("reason", clientLogging$CompletionReason.name());
        }
        while (true) {
            if (error == null) {
                break Label_0080;
            }
            try {
                intent.putExtra("error", error.toJSONObject().toString());
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
            catch (JSONException ex) {
                Log.e("nf_log", "Failed to get JSON string from UIError", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public static void reportCredentialStoreSessionStarted(final Context context, final SignInLogging$CredentialService signInLogging$CredentialService) {
        if (ConsolidatedLoggingUtils.isNull(context, "Context can not be null!")) {
            return;
        }
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_SIGNIN_CREDENTIAL_SAVE_START");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        if (signInLogging$CredentialService != null) {
            intent.putExtra("credSavedService", signInLogging$CredentialService.name());
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportSignInRequestSessionEnded(final Context context, final SignInLogging$SignInType signInLogging$SignInType, final IClientLogging$CompletionReason clientLogging$CompletionReason, final Error error) {
        if (ConsolidatedLoggingUtils.isNull(context, "Context can not be null!")) {
            return;
        }
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_SIGNIN_ENDED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        if (signInLogging$SignInType != null) {
            intent.putExtra("type", signInLogging$SignInType.name());
        }
        if (clientLogging$CompletionReason != null) {
            intent.putExtra("reason", clientLogging$CompletionReason.name());
        }
        while (true) {
            if (error == null) {
                break Label_0080;
            }
            try {
                intent.putExtra("error", error.toJSONObject().toString());
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
            catch (JSONException ex) {
                Log.e("nf_log", "Failed to get JSON string from UIError", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public static void reportSignInRequestSessionStarted(final Context context, final SignInLogging$SignInType signInLogging$SignInType) {
        if (ConsolidatedLoggingUtils.isNull(context, "Context can not be null!")) {
            return;
        }
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_SIGNIN_START");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        if (signInLogging$SignInType != null) {
            intent.putExtra("type", signInLogging$SignInType.name());
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
}
