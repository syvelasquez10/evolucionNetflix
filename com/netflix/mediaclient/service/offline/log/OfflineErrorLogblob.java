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
            logblobLogging.sendLogblob(new OfflineErrorLogblob(Logblob$Severity.error, s, s2, s3, LogUtils.getErrorCodeForServerLogs(status), LogUtils.getErrorMessageForServerLogs(status), true));
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
        Label_0088_Outer:
            while (true) {
                while (true) {
                    Label_0265: {
                        try {
                            final Logblob$Severity error = Logblob$Severity.error;
                            switch (OfflineErrorLogblob$1.$SwitchMap$com$netflix$mediaclient$servicemgr$interface_$offline$StopReason[stopReason.ordinal()]) {
                                case 1:
                                case 2:
                                case 3:
                                case 4: {
                                    goto Label_0158;
                                    goto Label_0158;
                                }
                                case 5:
                                case 6:
                                case 7: {
                                    goto Label_0164;
                                    goto Label_0164;
                                }
                                case 8: {
                                    goto Label_0170;
                                    goto Label_0170;
                                }
                                case 9: {
                                    goto Label_0181;
                                    goto Label_0181;
                                }
                                case 10: {
                                    goto Label_0192;
                                    goto Label_0192;
                                }
                                case 11: {
                                    goto Label_0203;
                                    goto Label_0203;
                                }
                                case 12: {
                                    goto Label_0214;
                                    goto Label_0214;
                                }
                                case 13: {
                                    goto Label_0225;
                                    goto Label_0225;
                                }
                                case 14: {
                                    goto Label_0236;
                                    goto Label_0236;
                                }
                                default: {
                                    break Label_0265;
                                }
                            }
                            while (true) {
                                logblobLogging.sendLogblob(new OfflineErrorLogblob(error, s, s2, s3, UserVisibleErrorCodeGenerator.getOfflineErrorCodeForStoppedDownload(stopReason), "downloadStopError", true));
                                return;
                                Log.d("offlineErrorLogBlob", " onDownloadStopped stopReason: %s, no-op", stopReason);
                                continue Label_0088_Outer;
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
            logblobLogging.sendLogblob(new OfflineErrorLogblob(Logblob$Severity.warn, s, s2, s3, LogUtils.getErrorCodeForServerLogs(status), LogUtils.getErrorMessageForServerLogs(status), false));
        }
        catch (JSONException ex) {
            Log.i("offlineErrorLogBlob", "JSONException:", ex);
        }
        catch (Exception ex2) {
            Log.i("offlineErrorLogBlob", "Exception:", ex2);
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
