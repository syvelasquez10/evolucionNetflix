// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util.log;

import android.support.v4.content.LocalBroadcastManager;
import com.netflix.mediaclient.service.logging.JsonSerializer;
import android.content.Intent;
import com.netflix.mediaclient.service.logging.client.model.FalcorPathResult;
import java.util.List;
import android.content.Context;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.service.logging.client.model.HttpResponse;
import com.android.volley.VolleyError;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging;
import com.netflix.mediaclient.servicemgr.IClientLogging;

public final class ApmLogUtils extends ConsolidatedLoggingUtils
{
    public static void reportAssetRequest(final String s, final IClientLogging.AssetType assetType, final ApplicationPerformanceMetricsLogging applicationPerformanceMetricsLogging) {
        if (applicationPerformanceMetricsLogging == null) {
            Log.e("nf_log", "APM is unavailable, can not report asset request result");
        }
        else if (!StringUtils.isEmpty(s)) {
            applicationPerformanceMetricsLogging.startAssetRequestSession(s, assetType);
        }
    }
    
    public static void reportAssetRequestFailure(final String s, final VolleyError volleyError, final ApplicationPerformanceMetricsLogging applicationPerformanceMetricsLogging) {
        if (applicationPerformanceMetricsLogging == null) {
            Log.e("nf_log", "APM is unavailable, can not report asset request result");
        }
        else if (!StringUtils.isEmpty(s)) {
            applicationPerformanceMetricsLogging.endAssetRequestSession(s, IClientLogging.CompletionReason.failed, null, ConsolidatedLoggingUtils.toError(volleyError, s));
        }
    }
    
    public static void reportAssetRequestResult(final String s, final StatusCode statusCode, final ApplicationPerformanceMetricsLogging applicationPerformanceMetricsLogging) {
        if (applicationPerformanceMetricsLogging == null) {
            Log.e("nf_log", "APM is unavailable, can not report asset request result");
        }
        else if (!StringUtils.isEmpty(s)) {
            final IClientLogging.CompletionReason failed = IClientLogging.CompletionReason.failed;
            final Error error = ConsolidatedLoggingUtils.toError(statusCode, s);
            Enum<IClientLogging.CompletionReason> success = failed;
            if (error == null) {
                success = IClientLogging.CompletionReason.success;
            }
            applicationPerformanceMetricsLogging.endAssetRequestSession(s, (IClientLogging.CompletionReason)success, null, error);
        }
    }
    
    public static void reportDataRequestEnded(final Context context, final String s, final IClientLogging.CompletionReason completionReason, final List<FalcorPathResult> list, final Error error, final HttpResponse httpResponse) {
        ConsolidatedLoggingUtils.validateArgument(context, "Context can not be null!");
        ConsolidatedLoggingUtils.validateArgument(s, "Request ID can not be null!");
        ConsolidatedLoggingUtils.validateArgument(completionReason, "Completion reason can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_APM_DATA_REQUEST_ENDED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("request_id", s);
        intent.putExtra("reason", completionReason.name());
        ConsolidatedLoggingUtils.addToIntent(intent, "error", error);
        ConsolidatedLoggingUtils.addToIntent(intent, "http_response", httpResponse);
        ConsolidatedLoggingUtils.addToIntent(intent, "falcorPathResults", list);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportDataRequestStarted(final Context context, final String s, final String s2) {
        ConsolidatedLoggingUtils.validateArgument(context, "Context can not be null!");
        ConsolidatedLoggingUtils.validateArgument(s, "Request ID can not be null!");
        ConsolidatedLoggingUtils.validateArgument(s2, "Request URL can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_APM_DATA_REQUEST_STARTED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("request_id", s);
        intent.putExtra("url", s2);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportUiModalViewChanged(final Context context, final IClientLogging.ModalView modalView) {
        ConsolidatedLoggingUtils.validateArgument(context, "Context can not be null!");
        ConsolidatedLoggingUtils.validateArgument(modalView, "View can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_APM_UI_MODAL_VIEW_CHANGED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("view", modalView.name());
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
}
