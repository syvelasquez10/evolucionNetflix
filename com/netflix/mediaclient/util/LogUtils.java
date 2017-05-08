// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import android.support.v4.content.LocalBroadcastManager;
import android.content.Intent;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import java.util.Collections;
import com.netflix.mediaclient.servicemgr.UiLocation;
import com.netflix.mediaclient.servicemgr.interface_.LoMoType;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.servicemgr.interface_.BasicLoMo;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.servicemgr.interface_.details.SeasonDetails;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.player.bladerunnerclient.volley.BladerunnerErrorStatus;
import com.netflix.mediaclient.android.app.Status;

public final class LogUtils
{
    private static final int CLIENT_CODE_STACK_INDEX;
    private static final int MAX_TAG_LENGTH = 23;
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
    
    public static String getCurrMethodName() {
        return Thread.currentThread().getStackTrace()[LogUtils.CLIENT_CODE_STACK_INDEX].getMethodName();
    }
    
    public static String getErrorCodeForServerLogs(final Status status) {
        String s = String.valueOf(status.getStatusCode().getValue());
        if (status instanceof BladerunnerErrorStatus) {
            s = ((BladerunnerErrorStatus)status).getErrorCodeForLogging();
        }
        return s;
    }
    
    public static String getErrorMessageForServerLogs(final Status status) {
        String s = status.getMessage();
        if (status instanceof BladerunnerErrorStatus) {
            s = ((BladerunnerErrorStatus)status).getErrorMessageForLogging();
        }
        return s;
    }
    
    public static String getTag(final Class clazz) {
        String s2;
        final String s = s2 = clazz.getSimpleName();
        if (s.length() > 23) {
            s2 = s.substring(0, 23);
        }
        return s2;
    }
    
    public static void logCurrentThreadName(final String s, final String s2) {
        if (Log.isLoggable()) {
            Log.v(s, "Current thread name: " + Thread.currentThread().getName() + ", msg: " + s2);
        }
    }
    
    public static void logEmptySeasonId(final IClientLogging clientLogging, String format, final SeasonDetails seasonDetails) {
        if (seasonDetails == null) {
            Log.v("nf_log", "No season details");
            return;
        }
        format = String.format("For Show Id %s, the Current Season Details Id is empty - %s, see SPY-7455", format, seasonDetails.toString());
        clientLogging.getErrorLogging().logHandledException(format);
    }
    
    public static void reportErrorSafely(final String s) {
        reportErrorSafely(s, null);
    }
    
    public static void reportErrorSafely(final String s, Throwable t) {
        String s2 = s;
        if (s == null) {
            s2 = "";
        }
        if (t != null) {
            Log.e("nf_log", s2, t);
        }
        else {
            Log.e("nf_log", s2);
            t = new RuntimeException(s2);
        }
        ErrorLoggingManager.logHandledException(t);
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
        if (Log.isLoggable()) {
            Log.v("nf_presentation", String.format("%s, %s, offset %d, id: %s, boxartImageTypeIdentifier: %s", basicLoMo.getTitle(), uiLocation, n, video.getId(), video.getBoxartImageTypeIdentifier()));
        }
        serviceManager.getClientLogging().getPresentationTracking().reportPresentation(basicLoMo, Collections.singletonList(video.getId()), Collections.singletonList(video.getBoxartImageTypeIdentifier()), n, uiLocation);
    }
    
    public static void reportSignUpOnDevice(final Context context) {
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.ONSIGNUP");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    protected static void validateArgument(final Object o, final String s) {
        if (o == null) {
            Log.e("nf_log", s);
            throw new IllegalArgumentException(s);
        }
    }
}
