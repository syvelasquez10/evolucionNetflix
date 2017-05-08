// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pdslogging;

import android.support.v4.content.LocalBroadcastManager;
import android.content.Intent;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.android.app.Status;
import android.content.Context;

public class PdsLoggingUtils
{
    public static void downloadStoppedOnError(final Context context, final String s, final Status status) {
        sendIntent(context, PdsDownloadSessionManager.STOP_DOWNLOAD_ERROR, s, LogUtils.getErrorCodeForServerLogs(status), LogUtils.getErrorMessageForServerLogs(status));
    }
    
    public static void downloadStoppedOnLicenseError(final Context context, final String s, final Status status) {
        sendIntent(context, PdsDownloadSessionManager.STOP_DOWNLOAD_LICENSE_ERROR, s, LogUtils.getErrorCodeForServerLogs(status), LogUtils.getErrorMessageForServerLogs(status));
    }
    
    public static void downloadStoppedOnManifestExpiry(final Context context, final String s, final Status status) {
        sendIntent(context, PdsDownloadSessionManager.STOP_DOWNLOAD_MANIFEST_EXPIRED, s, LogUtils.getErrorCodeForServerLogs(status), LogUtils.getErrorMessageForServerLogs(status));
    }
    
    private static void sendIntent(final Context context, final String s, final String s2, final String s3, final String s4) {
        final Intent intent = new Intent(s);
        intent.addCategory(PdsDownloadSessionManager.CATEGORY_NF_PDSLOG_DOWNLOAD);
        intent.putExtra(PdsDownloadSessionManager.EXTRA_PLAYABLE_ID, s2);
        intent.putExtra(PdsDownloadSessionManager.EXTRA_ERROR_CODE, s3);
        intent.putExtra(PdsDownloadSessionManager.EXTRA_ERROR_MESSAGE, s4);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
}
