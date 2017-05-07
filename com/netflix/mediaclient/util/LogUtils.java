// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.servicemgr.UIViewLogging;
import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import java.util.Collections;
import com.netflix.mediaclient.servicemgr.UiLocation;
import com.netflix.mediaclient.servicemgr.model.LoMoType;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.servicemgr.model.BasicLoMo;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.media.PlayerType;
import com.netflix.mediaclient.service.logging.client.model.HttpResponse;
import com.android.volley.VolleyError;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging;
import org.json.JSONException;
import com.netflix.mediaclient.servicemgr.UserActionLogging;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import android.support.v4.content.LocalBroadcastManager;
import android.graphics.Point;
import android.view.WindowManager;
import com.netflix.mediaclient.service.logging.apm.model.Display;
import android.content.Context;
import com.netflix.mediaclient.service.logging.client.model.RootCause;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.service.logging.client.model.ActionOnUIError;
import com.netflix.mediaclient.android.app.Status;
import java.util.ArrayList;
import com.netflix.mediaclient.service.logging.client.model.DeepErrorElement;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.logging.client.model.FalcorPathResult;
import java.util.List;
import com.netflix.mediaclient.service.logging.JsonSerializer;
import android.content.Intent;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.StatusCode;

public final class LogUtils
{
    private static final int CLIENT_CODE_STACK_INDEX;
    private static final String TAG = "nf_log";
    
    static {
        int n = 0;
        final StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        final int length = stackTrace.length;
        int n2 = 0;
        int client_CODE_STACK_INDEX;
        while (true) {
            client_CODE_STACK_INDEX = n;
            if (n2 >= length) {
                break;
            }
            final StackTraceElement stackTraceElement = stackTrace[n2];
            ++n;
            if (stackTraceElement.getClassName().equals(LogUtils.class.getName())) {
                client_CODE_STACK_INDEX = n;
                break;
            }
            ++n2;
        }
        CLIENT_CODE_STACK_INDEX = client_CODE_STACK_INDEX;
    }
    
    private static void addToIntent(final Intent intent, final String s, final JsonSerializer jsonSerializer) {
        final String stringSafely = toStringSafely(jsonSerializer);
        if (stringSafely != null) {
            intent.putExtra(s, stringSafely);
        }
    }
    
    private static void addToIntent(final Intent intent, final String s, final List<FalcorPathResult> list) {
        try {
            final String jsonArray = FalcorPathResult.createJSONArray(list);
            if (jsonArray != null) {
                intent.putExtra(s, jsonArray);
            }
        }
        catch (Throwable t) {
            Log.e("nf_log", "addToIntent:: Failed to create JSON string for list of FalcorPathResult ", t);
        }
    }
    
    private static List<DeepErrorElement> createDeepErrorList(final StatusCode statusCode, final String stackTrace) {
        final ArrayList<DeepErrorElement> list = new ArrayList<DeepErrorElement>();
        final DeepErrorElement deepErrorElement = new DeepErrorElement();
        deepErrorElement.setErrorCode(String.valueOf(statusCode.getValue()));
        final DeepErrorElement.Debug debug = new DeepErrorElement.Debug();
        debug.setStackTrace(stackTrace);
        deepErrorElement.setDebug(debug);
        list.add(deepErrorElement);
        return list;
    }
    
    public static String createSessionLookupKey(final String s, final String s2) {
        return "" + s + "." + s2;
    }
    
    public static UIError createUIError(final Status status, final String s, final ActionOnUIError actionOnUIError) {
        final RootCause serverFailure = RootCause.serverFailure;
        final StatusCode statusCode = status.getStatusCode();
        final List<DeepErrorElement> deepErrorList = createDeepErrorList(statusCode, s);
        RootCause rootCause = null;
        switch (statusCode) {
            default: {
                rootCause = RootCause.unknownFailure;
                break;
            }
            case NRD_ERROR:
            case INTERNAL_ERROR: {
                rootCause = RootCause.clientFailure;
                break;
            }
            case FALCOR_RESPONSE_PARSE_ERROR: {
                rootCause = RootCause.serverResponseBad;
                break;
            }
            case NRD_LOGIN_ACTIONID_4:
            case NRD_LOGIN_ACTIONID_8: {
                rootCause = RootCause.clientRequestBad;
                break;
            }
            case SERVER_ERROR:
            case NRD_LOGIN_ACTIONID_1:
            case NRD_LOGIN_ACTIONID_2:
            case NRD_LOGIN_ACTIONID_3:
            case NRD_LOGIN_ACTIONID_5:
            case NRD_LOGIN_ACTIONID_6:
            case NRD_LOGIN_ACTIONID_7:
            case NRD_LOGIN_ACTIONID_9:
            case NRD_LOGIN_ACTIONID_10:
            case NRD_LOGIN_ACTIONID_11:
            case NRD_LOGIN_ACTIONID_12: {
                rootCause = RootCause.serverFailure;
                break;
            }
            case NETWORK_ERROR:
            case NO_CONNECTIVITY: {
                rootCause = RootCause.networkFailure;
                break;
            }
            case HTTP_SSL_DATE_TIME_ERROR: {
                rootCause = RootCause.sslExpiredCert;
                break;
            }
            case HTTP_SSL_ERROR: {
                rootCause = RootCause.sslHandshakeFailure;
                break;
            }
            case HTTP_SSL_NO_VALID_CERT: {
                rootCause = RootCause.sslUntrustedCert;
                break;
            }
        }
        return new UIError(rootCause, actionOnUIError, s, deepErrorList);
    }
    
    public static String getCurrMethodName() {
        return Thread.currentThread().getStackTrace()[LogUtils.CLIENT_CODE_STACK_INDEX].getMethodName();
    }
    
    public static Display getDisplay(final Context context) {
        if (context != null) {
            final WindowManager windowManager = (WindowManager)context.getSystemService("window");
            if (windowManager != null) {
                final android.view.Display defaultDisplay = windowManager.getDefaultDisplay();
                final float refreshRate = defaultDisplay.getRefreshRate();
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "Refresh rate: " + refreshRate);
                }
                float n = refreshRate;
                if (refreshRate < 10.0f) {
                    n = 60.0f;
                }
                final Point point = new Point();
                defaultDisplay.getSize(point);
                return new Display(Display.Connector.internal, null, point.x, point.y, (int)n, null);
            }
        }
        return null;
    }
    
    public static void logCurrentThreadName(final String s, final String s2) {
        if (Log.isLoggable(s, 2)) {
            Log.v(s, "Current thread name: " + Thread.currentThread().getName() + ", msg: " + s2);
        }
    }
    
    public static void pauseReporting(final Context context) {
        validateArgument(context, "Context can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_PAUSE_EVENTS_DELIVERY");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportAddProfileActionEnded(final Context context, final IClientLogging.CompletionReason completionReason, final IClientLogging.ModalView modalView, final StatusCode statusCode, final UserActionLogging.Profile profile) {
        validateArgument(context, "Context can not be null!");
        validateArgument(completionReason, "Reason can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_ADD_PROFILE_ENDED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("reason", completionReason.name());
        intent.putExtra("view", modalView.name());
        final UIError uiError = toUIError(statusCode);
        while (true) {
            if (uiError == null) {
                break Label_0086;
            }
            try {
                intent.putExtra("error", uiError.toJSONObject().toString());
                if (profile != null) {
                    intent.putExtra("profile_is_kids", profile.isKids());
                    if (profile.getId() != null) {
                        intent.putExtra("profile_id", profile.getId());
                    }
                    if (profile.getName() != null) {
                        intent.putExtra("profile_name", profile.getName());
                    }
                    if (profile.getAge() != null) {
                        intent.putExtra("profile_age", (int)profile.getAge());
                    }
                }
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
            catch (JSONException ex) {
                Log.e("nf_log", "Failed to get JSON string from UIError", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public static void reportAddProfileActionStarted(final Context context, final UserActionLogging.CommandName commandName, final IClientLogging.ModalView modalView) {
        validateArgument(context, "Context can not be null!");
        validateArgument(modalView, "View can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_ADD_PROFILE_START");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("view", modalView.name());
        if (commandName != null) {
            intent.putExtra("cmd", commandName.name());
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportAddToQueueActionEnded(final Context context, final IClientLogging.CompletionReason completionReason, final UIError uiError, final Integer n) {
        validateArgument(context, "Context can not be null!");
        validateArgument(completionReason, "Reason can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_ADD_TO_PLAYLIST_ENDED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("reason", completionReason.name());
        while (true) {
            if (uiError == null) {
                break Label_0068;
            }
            try {
                intent.putExtra("error", uiError.toJSONObject().toString());
                if (n != null) {
                    intent.putExtra("title_rank", (int)n);
                }
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
            catch (JSONException ex) {
                Log.e("nf_log", "Failed to get JSON string from UIError", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public static void reportAddToQueueActionStarted(final Context context, final UserActionLogging.CommandName commandName, final IClientLogging.ModalView modalView) {
        validateArgument(context, "Context can not be null!");
        validateArgument(modalView, "View can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_ADD_TO_PLAYLIST_START");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("view", modalView.name());
        if (commandName != null) {
            intent.putExtra("cmd", commandName.name());
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
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
            applicationPerformanceMetricsLogging.endAssetRequestSession(s, IClientLogging.CompletionReason.failed, null, toError(volleyError, s));
        }
    }
    
    public static void reportAssetRequestResult(final String s, final StatusCode statusCode, final ApplicationPerformanceMetricsLogging applicationPerformanceMetricsLogging) {
        if (applicationPerformanceMetricsLogging == null) {
            Log.e("nf_log", "APM is unavailable, can not report asset request result");
        }
        else if (!StringUtils.isEmpty(s)) {
            final IClientLogging.CompletionReason failed = IClientLogging.CompletionReason.failed;
            final Error error = toError(statusCode, s);
            Enum<IClientLogging.CompletionReason> success = failed;
            if (error == null) {
                success = IClientLogging.CompletionReason.success;
            }
            applicationPerformanceMetricsLogging.endAssetRequestSession(s, (IClientLogging.CompletionReason)success, null, error);
        }
    }
    
    public static void reportDataRequestEnded(final Context context, final String s, final IClientLogging.CompletionReason completionReason, final List<FalcorPathResult> list, final Error error, final HttpResponse httpResponse) {
        validateArgument(context, "Context can not be null!");
        validateArgument(s, "Request ID can not be null!");
        validateArgument(completionReason, "Completion reason can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_APM_DATA_REQUEST_ENDED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("request_id", s);
        intent.putExtra("reason", completionReason.name());
        addToIntent(intent, "error", error);
        addToIntent(intent, "http_response", httpResponse);
        addToIntent(intent, "falcorPathResults", list);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportDataRequestStarted(final Context context, final String s, final String s2) {
        validateArgument(context, "Context can not be null!");
        validateArgument(s, "Request ID can not be null!");
        validateArgument(s2, "Request URL can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_APM_DATA_REQUEST_STARTED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("request_id", s);
        intent.putExtra("url", s2);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportDeleteProfileActionEnded(final Context context, final IClientLogging.CompletionReason completionReason, final IClientLogging.ModalView modalView, final StatusCode statusCode) {
        validateArgument(context, "Context can not be null!");
        validateArgument(completionReason, "Reason can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_DELETE_PROFILE_ENDED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("reason", completionReason.name());
        intent.putExtra("view", modalView.name());
        final UIError uiError = toUIError(statusCode);
        while (true) {
            if (uiError == null) {
                break Label_0086;
            }
            try {
                intent.putExtra("error", uiError.toJSONObject().toString());
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
            catch (JSONException ex) {
                Log.e("nf_log", "Failed to get JSON string from UIError", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public static void reportDeleteProfileActionStarted(final Context context, final UserActionLogging.CommandName commandName, final IClientLogging.ModalView modalView, final String s) {
        validateArgument(context, "Context can not be null!");
        validateArgument(modalView, "View can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_DELETE_PROFILE_START");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("view", modalView.name());
        intent.putExtra("profile_id", s);
        if (commandName != null) {
            intent.putExtra("cmd", commandName.name());
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportEditProfileActionEnded(final Context context, final IClientLogging.CompletionReason completionReason, final IClientLogging.ModalView modalView, final StatusCode statusCode, final UserActionLogging.Profile profile) {
        validateArgument(context, "Context can not be null!");
        validateArgument(completionReason, "Reason can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_EDIT_PROFILE_ENDED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("reason", completionReason.name());
        intent.putExtra("view", modalView.name());
        final UIError uiError = toUIError(statusCode);
        while (true) {
            if (uiError == null) {
                break Label_0086;
            }
            try {
                intent.putExtra("error", uiError.toJSONObject().toString());
                if (profile != null) {
                    intent.putExtra("profile_is_kids", profile.isKids());
                    if (profile.getId() != null) {
                        intent.putExtra("profile_id", profile.getId());
                    }
                    if (profile.getName() != null) {
                        intent.putExtra("profile_name", profile.getName());
                    }
                    if (profile.getAge() != null) {
                        intent.putExtra("profile_age", (int)profile.getAge());
                    }
                }
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
            catch (JSONException ex) {
                Log.e("nf_log", "Failed to get JSON string from UIError", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public static void reportEditProfileActionStarted(final Context context, final UserActionLogging.CommandName commandName, final IClientLogging.ModalView modalView) {
        validateArgument(context, "Context can not be null!");
        validateArgument(modalView, "View can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_EDIT_PROFILE_START");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("view", modalView.name());
        if (commandName != null) {
            intent.putExtra("cmd", commandName.name());
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportLoginActionEnded(final Context context, final IClientLogging.CompletionReason completionReason, final UIError uiError) {
        validateArgument(context, "Context can not be null!");
        validateArgument(completionReason, "Reason can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_LOGIN_ENDED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("reason", completionReason.name());
        while (true) {
            if (uiError == null) {
                break Label_0064;
            }
            try {
                intent.putExtra("error", uiError.toJSONObject().toString());
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
            catch (JSONException ex) {
                Log.e("nf_log", "Failed to get JSON string from UIError", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public static void reportLoginActionStarted(final Context context, final UserActionLogging.CommandName commandName, final IClientLogging.ModalView modalView) {
        validateArgument(context, "Context can not be null!");
        validateArgument(modalView, "View can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_LOGIN_START");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("view", modalView.name());
        if (commandName != null) {
            intent.putExtra("cmd", commandName.name());
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportNavigationActionEnded(final Context context, final IClientLogging.ModalView modalView, final IClientLogging.CompletionReason completionReason, final UIError uiError) {
        validateArgument(context, "Context can not be null!");
        validateArgument(completionReason, "Reason can not be null!");
        if (modalView == null) {
            return;
        }
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_NAVIGATION_ENDED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("view", modalView.name());
        intent.putExtra("reason", completionReason.name());
        while (true) {
            if (uiError == null) {
                break Label_0086;
            }
            try {
                intent.putExtra("error", uiError.toJSONObject().toString());
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
            catch (JSONException ex) {
                Log.e("nf_log", "Failed to get JSON string from UIError", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public static void reportNavigationActionStarted(final Context context, final UserActionLogging.CommandName commandName, final IClientLogging.ModalView modalView) {
        validateArgument(context, "Context can not be null!");
        if (modalView == null) {
            return;
        }
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_NAVIGATION_START");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("view", modalView.name());
        if (commandName != null) {
            intent.putExtra("cmd", commandName.name());
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportPlayActionEnded(final Context context, final IClientLogging.CompletionReason completionReason, final UIError uiError, final Integer n, final PlayerType playerType) {
        validateArgument(context, "Context can not be null!");
        validateArgument(completionReason, "Reason can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_START_PLAY_ENDED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("reason", completionReason.name());
        while (true) {
            if (uiError == null) {
                break Label_0068;
            }
            try {
                intent.putExtra("error", uiError.toJSONObject().toString());
                if (n != null) {
                    intent.putExtra("rank", (int)n);
                }
                if (playerType != null) {
                    intent.putExtra("playerType", playerType.getValue());
                }
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
            catch (JSONException ex) {
                Log.e("nf_log", "Failed to get JSON string from UIError", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public static void reportPlayActionStarted(final Context context, final UserActionLogging.CommandName commandName, final IClientLogging.ModalView modalView) {
        validateArgument(context, "Context can not be null!");
        validateArgument(modalView, "View can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_START_PLAY_START");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("view", modalView.name());
        if (commandName != null) {
            intent.putExtra("cmd", commandName.name());
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportPresentationTracking(final ServiceManager serviceManager, final BasicLoMo basicLoMo, final Video video, final int n) {
        if (serviceManager == null || !serviceManager.isReady()) {
            Log.w("nf_presentation", "Manager not ready - can't report presentation tracking");
            return;
        }
        if (!VideoType.isPresentationTrackingType(video.getType())) {
            Log.v("nf_presentation", "Video is not presentation-trackable");
            return;
        }
        UiLocation uiLocation;
        if (basicLoMo.getType() == LoMoType.FLAT_GENRE) {
            uiLocation = UiLocation.GENRE_LOLOMO;
        }
        else {
            uiLocation = UiLocation.HOME_LOLOMO;
        }
        if (Log.isLoggable("nf_presentation", 2)) {
            Log.v("nf_presentation", String.format("%s, %s, offset %d, id: %s", basicLoMo.getTitle(), uiLocation, n, video.getId()));
        }
        serviceManager.getClientLogging().getPresentationTracking().reportPresentation(basicLoMo, Collections.singletonList(video.getId()), n, uiLocation);
    }
    
    public static void reportRateActionEnded(final Context context, final IClientLogging.CompletionReason completionReason, final UIError uiError, final Integer n, final Integer n2) {
        validateArgument(context, "Context can not be null!");
        validateArgument(completionReason, "Reason can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_RATE_TITLE_ENDED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("reason", completionReason.name());
        while (true) {
            if (uiError == null) {
                break Label_0068;
            }
            try {
                intent.putExtra("error", uiError.toJSONObject().toString());
                if (n != null) {
                    intent.putExtra("rank", (int)n);
                }
                if (n2 != null) {
                    intent.putExtra("rating", (int)n2);
                }
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
            catch (JSONException ex) {
                Log.e("nf_log", "Failed to get JSON string from UIError", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public static void reportRateActionStarted(final Context context, final UserActionLogging.CommandName commandName, final IClientLogging.ModalView modalView) {
        validateArgument(context, "Context can not be null!");
        validateArgument(modalView, "View can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_RATE_TITLE_START");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("view", modalView.name());
        if (commandName != null) {
            intent.putExtra("cmd", commandName.name());
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportRemoveFromQueueActionEnded(final Context context, final IClientLogging.CompletionReason completionReason, final UIError uiError) {
        validateArgument(context, "Context can not be null!");
        validateArgument(completionReason, "Reason can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_REMOVE_FROM_PLAYLIST_ENDED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("reason", completionReason.name());
        while (true) {
            if (uiError == null) {
                break Label_0064;
            }
            try {
                intent.putExtra("error", uiError.toJSONObject().toString());
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
            catch (JSONException ex) {
                Log.e("nf_log", "Failed to get JSON string from UIError", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public static void reportRemoveFromQueueActionStarted(final Context context, final UserActionLogging.CommandName commandName, final IClientLogging.ModalView modalView) {
        validateArgument(context, "Context can not be null!");
        validateArgument(modalView, "View can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_REMOVE_FROM_PLAYLIST_START");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("view", modalView.name());
        if (commandName != null) {
            intent.putExtra("cmd", commandName.name());
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportSearchActionEnded(final long n, final Context context, final IClientLogging.CompletionReason completionReason, final UIError uiError) {
        validateArgument(context, "Context can not be null!");
        validateArgument(completionReason, "Reason can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_SEARCH_ENDED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("reason", completionReason.name());
        intent.putExtra("id", n);
        while (true) {
            if (uiError == null) {
                break Label_0080;
            }
            try {
                intent.putExtra("error", uiError.toJSONObject().toString());
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
            catch (JSONException ex) {
                Log.e("nf_log", "Failed to get JSON string from UIError", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public static void reportSearchActionStarted(final long n, final Context context, final UserActionLogging.CommandName commandName, final IClientLogging.ModalView modalView, final String s) {
        validateArgument(context, "Context can not be null!");
        validateArgument(modalView, "View can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_SEARCH_START");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("view", modalView.name());
        intent.putExtra("id", n);
        if (commandName != null) {
            intent.putExtra("cmd", commandName.name());
        }
        if (s != null) {
            intent.putExtra("term", s);
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportSelectProfileActionEnded(final Context context, final IClientLogging.CompletionReason completionReason, final IClientLogging.ModalView modalView, final StatusCode statusCode) {
        validateArgument(context, "Context can not be null!");
        validateArgument(completionReason, "Reason can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_SELECT_PROFILE_ENDED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("reason", completionReason.name());
        intent.putExtra("view", modalView.name());
        final UIError uiError = toUIError(statusCode);
        while (true) {
            if (uiError == null) {
                break Label_0086;
            }
            try {
                intent.putExtra("error", uiError.toJSONObject().toString());
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
            catch (JSONException ex) {
                Log.e("nf_log", "Failed to get JSON string from UIError", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public static void reportSelectProfileActionStarted(final Context context, final UserActionLogging.CommandName commandName, final IClientLogging.ModalView modalView, final String s, final UserActionLogging.RememberProfile rememberProfile) {
        validateArgument(context, "Context can not be null!");
        validateArgument(modalView, "View can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_SELECT_PROFILE_START");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("view", modalView.name());
        if (commandName != null) {
            intent.putExtra("cmd", commandName.name());
        }
        intent.putExtra("profile_id", s);
        if (rememberProfile != null) {
            intent.putExtra("remember_profile", rememberProfile.name());
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportSignUpOnDevice(final Context context) {
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.ONSIGNUP");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportUIViewCommand(final Context context, final UIViewLogging.UIViewCommandName uiViewCommandName, final IClientLogging.ModalView modalView, final DataContext dataContext) {
        reportUIViewCommandStarted(context, uiViewCommandName, modalView, dataContext);
        reportUIViewCommandEnded(context);
    }
    
    public static void reportUIViewCommandEnded(final Context context) {
        validateArgument(context, "Context can not be null!");
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent("com.netflix.mediaclient.intent.action.LOG_UIVIEW_CMD_ENDED"));
    }
    
    public static void reportUIViewCommandStarted(final Context context, UIViewLogging.UIViewCommandName string, final IClientLogging.ModalView modalView, final DataContext dataContext) {
        validateArgument(context, "Context can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIVIEW_CMD_START");
        if (modalView != null) {
            intent.putExtra("view", modalView.name());
        }
        if (string != null) {
            intent.putExtra("cmd", string.name());
        }
        Label_0079: {
            if (dataContext == null) {
                break Label_0079;
            }
            string = null;
            while (true) {
                try {
                    string = (UIViewLogging.UIViewCommandName)dataContext.toJSONObject().toString();
                    intent.putExtra("dataContext", (String)string);
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                }
                catch (JSONException ex) {
                    Log.w("nf_log", "failed to create dataContext: " + dataContext.toString());
                    continue;
                }
                break;
            }
        }
    }
    
    public static void reportUiModalViewChanged(final Context context, final IClientLogging.ModalView modalView) {
        validateArgument(context, "Context can not be null!");
        validateArgument(modalView, "View can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_APM_UI_MODAL_VIEW_CHANGED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("view", modalView.name());
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void resumeReporting(final Context context, final boolean b) {
        validateArgument(context, "Context can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_PAUSE_EVENTS_DELIVERY");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("flush", b);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    private static Error toError(final VolleyError volleyError, final String s) {
        if (volleyError == null || volleyError.networkResponse == null) {
            return new Error(RootCause.unknownFailure, null);
        }
        final int statusCode = volleyError.networkResponse.statusCode;
        if (statusCode >= 400 && statusCode < 500) {
            return new Error(RootCause.http4xx, null);
        }
        if (statusCode == 500) {
            new Error(RootCause.serverFailure, null);
            return new Error(RootCause.serverFailure, null);
        }
        if (statusCode > 500 && statusCode < 600) {
            return new Error(RootCause.http5xx, null);
        }
        return new Error(RootCause.unknownFailure, null);
    }
    
    private static Error toError(final StatusCode statusCode, final String s) {
        final Error error = null;
        Error error2 = null;
        switch (statusCode) {
            default: {
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "Report  generic failure for " + s + ", status code not found " + statusCode);
                }
                error2 = new Error(RootCause.unknownFailure, null);
                break;
            }
            case OK:
            case NON_RECOMMENDED_APP_VERSION: {
                error2 = error;
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "Report success for " + s);
                    return null;
                }
                break;
            }
            case NETWORK_ERROR: {
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "Report network error for " + s);
                }
                error2 = new Error(RootCause.networkFailure, null);
                break;
            }
            case NO_CONNECTIVITY: {
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "Report no connectivity for " + s);
                }
                error2 = new Error(RootCause.tcpNoRouteToHost, null);
                break;
            }
            case HTTP_SSL_NO_VALID_CERT: {
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "Report SSL error, no valid certificate for " + s);
                }
                error2 = new Error(RootCause.sslExpiredCert, null);
                break;
            }
            case HTTP_SSL_DATE_TIME_ERROR: {
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "Report SSL error, date time error for " + s);
                }
                error2 = new Error(RootCause.sslUntrustedCert, null);
                break;
            }
            case HTTP_SSL_ERROR: {
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "Report SSL error, generic for " + s);
                }
                error2 = new Error(RootCause.sslUntrustedCert, null);
                break;
            }
            case SERVER_ERROR: {
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "Report server error, generic for " + s);
                }
                error2 = new Error(RootCause.serverFailure, null);
                break;
            }
            case UNKNOWN: {
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "Report uknown error for " + s);
                }
                error2 = new Error(RootCause.unknownFailure, null);
                break;
            }
        }
        return error2;
    }
    
    private static String toStringSafely(final JsonSerializer jsonSerializer) {
        if (jsonSerializer == null) {
            return null;
        }
        try {
            return jsonSerializer.toJSONObject().toString();
        }
        catch (Throwable t) {
            Log.e("nf_log", "toStringSafely:: Failed to create JSON string for event " + jsonSerializer, t);
            return null;
        }
    }
    
    private static UIError toUIError(final StatusCode statusCode) {
        StatusCode unknown = statusCode;
        if (statusCode == null) {
            unknown = StatusCode.UNKNOWN;
        }
        UIError uiError = null;
        switch (unknown) {
            default: {
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "Report  generic failure for, status code not found " + unknown);
                }
                uiError = new UIError(RootCause.unknownFailure, null, null, null);
                break;
            }
            case OK:
            case NON_RECOMMENDED_APP_VERSION: {
                Log.d("nf_log", "Report success");
                return null;
            }
            case NETWORK_ERROR: {
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "Report network error for");
                }
                uiError = new UIError(RootCause.networkFailure, null, null, null);
                break;
            }
            case NO_CONNECTIVITY: {
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "Report no connectivity for");
                }
                uiError = new UIError(RootCause.tcpNoRouteToHost, null, null, null);
                break;
            }
            case HTTP_SSL_NO_VALID_CERT: {
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "Report SSL error, no valid certificate for");
                }
                uiError = new UIError(RootCause.sslExpiredCert, null, null, null);
                break;
            }
            case HTTP_SSL_DATE_TIME_ERROR: {
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "Report SSL error, date time error for");
                }
                uiError = new UIError(RootCause.sslUntrustedCert, null, null, null);
                break;
            }
            case HTTP_SSL_ERROR: {
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "Report SSL error, generic for");
                }
                uiError = new UIError(RootCause.sslUntrustedCert, null, null, null);
                break;
            }
            case SERVER_ERROR: {
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "Report server error, generic for");
                }
                uiError = new UIError(RootCause.serverFailure, null, null, null);
                break;
            }
            case UNKNOWN: {
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "Report uknown error for");
                }
                uiError = new UIError(RootCause.unknownFailure, null, null, null);
                break;
            }
        }
        return uiError;
    }
    
    private static void validateArgument(final Object o, final String s) {
        if (o == null) {
            Log.e("nf_log", s);
            throw new IllegalArgumentException(s);
        }
    }
    
    public static class LogReportErrorArgs
    {
        private UIError error;
        private IClientLogging.CompletionReason reason;
        private Status status;
        
        public LogReportErrorArgs(final Status status, final ActionOnUIError actionOnUIError, final String s, final List<DeepErrorElement> list) {
            this.status = status;
            this.populate(actionOnUIError, s, list);
        }
        
        private void populate(final ActionOnUIError actionOnUIError, final String s, final List<DeepErrorElement> list) {
            final Error access$000 = toError(this.status.getStatusCode(), "na");
            if (access$000 != null) {
                this.error = new UIError(access$000.getRootCause(), actionOnUIError, s, list);
                this.reason = IClientLogging.CompletionReason.failed;
                return;
            }
            this.reason = IClientLogging.CompletionReason.success;
        }
        
        public UIError getError() {
            return this.error;
        }
        
        public IClientLogging.CompletionReason getReason() {
            return this.reason;
        }
        
        public Status getStatus() {
            return this.status;
        }
    }
}
