// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import com.netflix.mediaclient.service.logging.logblob.LogBlobType;
import org.json.JSONException;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.UserVisibleErrorCodeGenerator;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.servicemgr.interface_.offline.WatchState;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.javabridge.ui.LogArguments$LogLevel;
import com.netflix.mediaclient.service.logging.offline.OfflineBaseLogblob;

public class OfflineDialogLogblob extends OfflineBaseLogblob
{
    private static final String TAG = "offlineDialogLogblob";
    
    private OfflineDialogLogblob(final Logblob$Severity mSeverity, final String s, final String s2, final String s3, final String s4, final String s5) {
        super(s2, s3);
        this.mSeverity = mSeverity;
        this.mJson.put("level", LogArguments$LogLevel.ERROR.getLevel());
        if (StringUtils.isNotEmpty(s)) {
            this.mJson.put("mid", (Object)s);
        }
        if (StringUtils.isNotEmpty(s4)) {
            this.mJson.put("errorcode", (Object)s4);
        }
        if (StringUtils.isNotEmpty(s5)) {
            this.mJson.put("errormsg", (Object)s5);
        }
    }
    
    public static void sendLogblob(final NetflixActivity netflixActivity, final String s, final String s2, final String s3, final WatchState watchState) {
        if (!AndroidUtils.isActivityFinishedOrDestroyed((Context)netflixActivity)) {
            final ServiceManager serviceManager = ServiceManager.getServiceManager(netflixActivity);
            if (serviceManager != null) {
                final IClientLogging clientLogging = serviceManager.getClientLogging();
                if (clientLogging != null) {
                    final LogblobLogging logblobLogging = clientLogging.getLogblobLogging();
                    if (logblobLogging != null) {
                        final String offlineErrorCodeForCompleteDownload = UserVisibleErrorCodeGenerator.getOfflineErrorCodeForCompleteDownload(watchState);
                        try {
                            logblobLogging.sendLogblob(new OfflineDialogLogblob(Logblob$Severity.error, s, s2, s3, offlineErrorCodeForCompleteDownload, "offlineWatchError"));
                        }
                        catch (JSONException ex) {
                            Log.e("offlineDialogLogblob", "JSONException:", (Throwable)ex);
                        }
                        catch (Exception ex2) {
                            Log.e("offlineDialogLogblob", "Exception:", ex2);
                        }
                    }
                }
            }
        }
    }
    
    @Override
    public String getType() {
        return LogBlobType.OFFLINE_LOGBLOB_TYPE.getValue();
    }
    
    @Override
    public boolean shouldSendNow() {
        return true;
    }
}
