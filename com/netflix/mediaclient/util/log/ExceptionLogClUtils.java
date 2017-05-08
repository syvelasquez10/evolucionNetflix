// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util.log;

import org.json.JSONException;
import com.netflix.mediaclient.Log;
import android.support.v4.content.LocalBroadcastManager;
import java.util.List;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.service.logging.client.model.RootCause;
import android.content.Intent;
import com.netflix.mediaclient.service.logging.client.model.DeepErrorElement$Debug;
import com.netflix.mediaclient.service.logging.client.model.DeepErrorElement;
import java.util.ArrayList;
import android.content.Context;

public final class ExceptionLogClUtils extends ConsolidatedLoggingUtils
{
    public static void reportExceptionToCL(final Context context, Throwable stackTrace) {
        if (context == null || stackTrace == null) {
            return;
        }
        final ArrayList<DeepErrorElement> list = new ArrayList<DeepErrorElement>();
        final DeepErrorElement deepErrorElement = new DeepErrorElement();
        final DeepErrorElement$Debug debug = new DeepErrorElement$Debug();
        list.add(deepErrorElement);
        deepErrorElement.setDebug(debug);
        if (stackTrace.getStackTrace() != null) {
            debug.setStackTrace(stackTrace);
        }
        if (stackTrace.getMessage() != null) {
            debug.setMessage(stackTrace.getMessage());
        }
        stackTrace = (Throwable)new Intent("com.netflix.mediaclient.intent.action.LOG_EXCEPTION_CL");
        ((Intent)stackTrace).addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        while (true) {
            try {
                ((Intent)stackTrace).putExtra("error", new Error(RootCause.unknownFailure, list).toJSONObject().toString());
                LocalBroadcastManager.getInstance(context).sendBroadcast((Intent)stackTrace);
            }
            catch (JSONException ex) {
                Log.e("nf_log", "Failed to get JSON string from UIError for CL Exception", (Throwable)ex);
                continue;
            }
            break;
        }
    }
}
