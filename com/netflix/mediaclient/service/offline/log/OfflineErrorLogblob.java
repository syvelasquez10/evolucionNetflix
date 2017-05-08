// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.log;

import com.netflix.mediaclient.service.logging.logblob.LogBlobType;
import com.netflix.mediaclient.service.job.NetflixJob$NetflixJobId;
import com.netflix.mediaclient.util.UserVisibleErrorCodeGenerator;
import com.netflix.mediaclient.servicemgr.interface_.offline.StopReason;
import org.json.JSONException;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.Logblob;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.LogblobLogging;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.javabridge.ui.LogArguments$LogLevel;
import com.netflix.mediaclient.servicemgr.Logblob$Severity;
import com.netflix.mediaclient.service.logging.offline.OfflineBaseLogblob;

public final class OfflineErrorLogblob extends OfflineBaseLogblob
{
    private static final String TAG = "offlineErrorLogBlob";
    private final boolean mShouldSendNow;
    
    private OfflineErrorLogblob(final Logblob$Severity mSeverity, final String s, final String s2, final String s3, final String s4, final String s5, final boolean mShouldSendNow) {
        super(s2, s3);
        this.mSeverity = mSeverity;
        this.mJson.put("level", LogArguments$LogLevel.ERROR.getLevel());
        if (StringUtils.isNotEmpty(s)) {
            this.mJson.put("mid", (Object)s);
        }
        if (StringUtils.isNotEmpty(s5)) {
            this.mJson.put("errormsg", (Object)s5);
        }
        if (StringUtils.isNotEmpty(s4)) {
            this.mJson.put("errorcode", (Object)s4);
        }
        this.mShouldSendNow = mShouldSendNow;
    }
    
    public static void sendBladerunnerError(final LogblobLogging logblobLogging, final String s, final String s2, final String s3, final Status status) {
        if (logblobLogging == null) {
            return;
        }
        try {
            final OfflineErrorLogblob offlineErrorLogblob = new OfflineErrorLogblob(Logblob$Severity.error, s, s2, s3, LogUtils.getErrorCodeForServerLogs(status), LogUtils.getErrorMessageForServerLogs(status), true);
            offlineErrorLogblob.setDebugMessage(status.getDebugMessageForServerLogs());
            logblobLogging.sendLogblob(offlineErrorLogblob);
        }
        catch (JSONException ex) {
            Log.i("offlineErrorLogBlob", "JSONException:", ex);
        }
        catch (Exception ex2) {
            Log.i("offlineErrorLogBlob", "Exception:", ex2);
        }
    }
    
    public static void sendDownloadStopError(final LogblobLogging logblobLogging, final String s, final String s2, final String s3, final StopReason stopReason) {
        if (logblobLogging != null) {
        Label_0092_Outer:
            while (true) {
                while (true) {
                    Label_0280: {
                        try {
                            final Logblob$Severity error = Logblob$Severity.error;
                            switch (OfflineErrorLogblob$1.$SwitchMap$com$netflix$mediaclient$servicemgr$interface_$offline$StopReason[stopReason.ordinal()]) {
                                case 1:
                                case 2:
                                case 3:
                                case 4: {
                                    goto Label_0162;
                                    goto Label_0162;
                                }
                                case 5:
                                case 6:
                                case 7: {
                                    goto Label_0168;
                                    goto Label_0168;
                                }
                                case 8: {
                                    goto Label_0174;
                                    goto Label_0174;
                                }
                                case 9: {
                                    goto Label_0185;
                                    goto Label_0185;
                                }
                                case 10: {
                                    goto Label_0196;
                                    goto Label_0196;
                                }
                                case 11: {
                                    goto Label_0207;
                                    goto Label_0207;
                                }
                                case 12: {
                                    goto Label_0218;
                                    goto Label_0218;
                                }
                                case 13: {
                                    goto Label_0229;
                                    goto Label_0229;
                                }
                                case 14: {
                                    goto Label_0240;
                                    goto Label_0240;
                                }
                                case 15: {
                                    goto Label_0251;
                                    goto Label_0251;
                                }
                                default: {
                                    break Label_0280;
                                }
                            }
                            while (true) {
                                logblobLogging.sendLogblob(new OfflineErrorLogblob(error, s, s2, s3, UserVisibleErrorCodeGenerator.getOfflineErrorCodeForStoppedDownload(stopReason), "downloadStopError", true));
                                return;
                                Log.d("offlineErrorLogBlob", " onDownloadStopped stopReason: %s, no-op", stopReason);
                                continue Label_0092_Outer;
                            }
                        }
                        // iftrue(Label_0004:, !false)
                        catch (JSONException ex) {
                            Log.i("offlineErrorLogBlob", "JSONException:", ex);
                            return;
                        }
                        catch (Exception ex2) {
                            Log.i("offlineErrorLogBlob", "Exception:", ex2);
                            return;
                        }
                    }
                    continue;
                }
            }
        }
        Label_0004:;
    }
    
    public static void sendNetflixJobStartLogBlob(final LogblobLogging logblobLogging, final NetflixJob$NetflixJobId netflixJob$NetflixJobId) {
        if (logblobLogging == null) {
            return;
        }
        try {
            logblobLogging.sendLogblob(new OfflineErrorLogblob(Logblob$Severity.info, "-1", "-1", "-1", "" + netflixJob$NetflixJobId.getIntValue(), "NetflixStartJob", false));
        }
        catch (JSONException ex) {
            Log.i("offlineErrorLogBlob", "JSONException:", ex);
        }
        catch (Exception ex2) {
            Log.i("offlineErrorLogBlob", "Exception:", ex2);
        }
    }
    
    public static void sendNotEnoughSpaceLogBlob(final LogblobLogging logblobLogging, final String s, final String s2, final String s3, final Status status) {
        if (logblobLogging == null) {
            return;
        }
        try {
            final OfflineErrorLogblob offlineErrorLogblob = new OfflineErrorLogblob(Logblob$Severity.warn, s, s2, s3, LogUtils.getErrorCodeForServerLogs(status), LogUtils.getErrorMessageForServerLogs(status), false);
            offlineErrorLogblob.setDebugMessage(status.getDebugMessageForServerLogs());
            logblobLogging.sendLogblob(offlineErrorLogblob);
        }
        catch (JSONException ex) {
            Log.i("offlineErrorLogBlob", "JSONException:", ex);
        }
        catch (Exception ex2) {
            Log.i("offlineErrorLogBlob", "Exception:", ex2);
        }
    }
    
    private void setDebugMessage(final String s) {
        if (!StringUtils.isNotEmpty(s)) {
            return;
        }
        try {
            this.mJson.put("dbgmsg", (Object)s);
        }
        catch (JSONException ex) {
            Log.i("offlineErrorLogBlob", "JSONException:", ex);
        }
    }
    
    @Override
    public Logblob$Severity getSeverity() {
        return this.mSeverity;
    }
    
    @Override
    public String getType() {
        return LogBlobType.OFFLINE_LOGBLOB_TYPE.getValue();
    }
    
    @Override
    public boolean shouldSendNow() {
        return this.mShouldSendNow;
    }
}