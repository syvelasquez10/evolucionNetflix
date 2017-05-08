// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import org.json.JSONException;
import com.netflix.mediaclient.event.nrdp.registration.ActivateEvent;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.service.logging.client.model.ActionOnUIError;
import com.netflix.mediaclient.android.app.NetflixStatus;
import com.netflix.mediaclient.android.app.Status;
import java.util.List;
import com.netflix.mediaclient.service.logging.client.model.DeepErrorElement;
import java.util.ArrayList;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.service.logging.client.model.DeepErrorElement$Debug;
import com.netflix.mediaclient.service.logging.client.model.RootCause;
import com.netflix.mediaclient.StatusCode;

public final class StatusUtils
{
    private static final String LOG_PROPERTY_REASON = "reason";
    
    private static Error createError(final StatusCode statusCode, final boolean fatal, final RootCause rootCause, final DeepErrorElement$Debug debug) {
        final ArrayList<DeepErrorElement> list = new ArrayList<DeepErrorElement>();
        final DeepErrorElement deepErrorElement = new DeepErrorElement();
        list.add(deepErrorElement);
        deepErrorElement.setErrorCode("" + statusCode.getValue());
        deepErrorElement.setFatal(fatal);
        deepErrorElement.setDebug(debug);
        return new Error(rootCause, list);
    }
    
    public static Status createStatus(final StatusCode statusCode, final String s, final boolean b, final RootCause rootCause) {
        final NetflixStatus netflixStatus = new NetflixStatus(statusCode);
        final DeepErrorElement$Debug deepErrorElement$Debug = new DeepErrorElement$Debug();
        deepErrorElement$Debug.setMessage(JsonUtils.createJson("reason", s));
        netflixStatus.setError(createError(statusCode, b, rootCause, deepErrorElement$Debug));
        return netflixStatus;
    }
    
    public static UIError createUiError(final String errorCode, final ActionOnUIError actionOnUIError, final String s, final boolean fatal, final RootCause rootCause, final DeepErrorElement$Debug debug) {
        final DeepErrorElement deepErrorElement = new DeepErrorElement();
        deepErrorElement.setErrorCode(errorCode);
        deepErrorElement.setFatal(fatal);
        deepErrorElement.setDebug(debug);
        final UIError uiError = new UIError(rootCause, actionOnUIError, s);
        uiError.addDeepError(deepErrorElement);
        return uiError;
    }
    
    public static NetflixStatus toActionIdResult(final ActivateEvent activateEvent) {
        final int actionID = activateEvent.getActionID();
        boolean b = true;
        Label_0088: {
            if (!activateEvent.isNetworkError()) {
                break Label_0088;
            }
            StatusCode statusCode = StatusCode.NETWORK_ERROR;
        Label_0070_Outer:
            while (true) {
                final NetflixStatus netflixStatus = new NetflixStatus(statusCode);
                if (actionID == 3) {
                    netflixStatus.setMessage(activateEvent.getMessage());
                }
                final NetflixStatus netflixStatus2 = new NetflixStatus(statusCode);
                final DeepErrorElement$Debug deepErrorElement$Debug = new DeepErrorElement$Debug();
                while (true) {
                    try {
                        deepErrorElement$Debug.setMessage(activateEvent.getData());
                        netflixStatus2.setError(createError(statusCode, b, RootCause.serverFailure, deepErrorElement$Debug));
                        return netflixStatus;
                        Label_0217: {
                            statusCode = StatusCode.NRD_LOGIN_ACTIONID_9;
                        }
                        continue Label_0070_Outer;
                        Label_0196:
                        statusCode = StatusCode.NRD_LOGIN_ACTIONID_6;
                        continue Label_0070_Outer;
                        Label_0224:
                        statusCode = StatusCode.NRD_LOGIN_ACTIONID_10;
                        continue Label_0070_Outer;
                        Label_0189:
                        statusCode = StatusCode.NRD_LOGIN_ACTIONID_5;
                        continue Label_0070_Outer;
                        Label_0238:
                        statusCode = StatusCode.NRD_LOGIN_ACTIONID_12;
                        b = false;
                        continue Label_0070_Outer;
                        Label_0159:
                        statusCode = StatusCode.NRD_LOGIN_ACTIONID_1;
                        continue Label_0070_Outer;
                        Label_0175:
                        statusCode = StatusCode.NRD_LOGIN_ACTIONID_3;
                        continue Label_0070_Outer;
                        Label_0210:
                        statusCode = StatusCode.NRD_LOGIN_ACTIONID_8;
                        continue Label_0070_Outer;
                        Label_0152:
                        statusCode = StatusCode.NRD_ERROR;
                        continue Label_0070_Outer;
                        Label_0231:
                        statusCode = StatusCode.NRD_LOGIN_ACTIONID_11;
                        continue Label_0070_Outer;
                        // switch([Lcom.strobel.decompiler.ast.Label;@394cc8bc, actionID)
                        Label_0166:
                        statusCode = StatusCode.NRD_LOGIN_ACTIONID_2;
                        b = false;
                        continue Label_0070_Outer;
                        Label_0182:
                        statusCode = StatusCode.NRD_LOGIN_ACTIONID_4;
                        continue Label_0070_Outer;
                        Label_0203:
                        statusCode = StatusCode.NRD_LOGIN_ACTIONID_7;
                        continue Label_0070_Outer;
                    }
                    catch (JSONException ex) {
                        continue;
                    }
                    break;
                }
                break;
            }
        }
    }
}
