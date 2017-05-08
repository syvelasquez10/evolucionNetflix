// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.exoplayback.logblob;

import com.netflix.mediaclient.service.logging.logblob.LogBlobType;
import com.netflix.mediaclient.util.UserVisibleErrorCodeGenerator;
import com.netflix.mediaclient.servicemgr.interface_.offline.StopReason;
import com.netflix.mediaclient.servicemgr.LogblobLogging;
import org.json.JSONException;
import com.netflix.mediaclient.servicemgr.Logblob;
import com.netflix.mediaclient.service.pdslogging.PdsLoggingUtils;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.javabridge.ui.LogArguments$LogLevel;
import com.netflix.mediaclient.servicemgr.Logblob$Severity;

public class OfflineErrorLogblob extends OfflineBaseLogblob
{
    private OfflineErrorLogblob(final Logblob$Severity mSeverity, final String s, final String s2, final String s3, final String s4, final String s5) {
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
    }
    
    public static void sendBladerunnerError(final IClientLogging clientLogging, final String s, final String s2, final String s3, final Status status) {
        try {
            clientLogging.getLogblobLogging().sendLogblob(new OfflineErrorLogblob(Logblob$Severity.error, s, s2, s3, PdsLoggingUtils.getErrorCodeForLogging(status), PdsLoggingUtils.getErrorMessageForLogging(status)));
        }
        catch (JSONException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void sendDownloadStopError(final LogblobLogging logblobLogging, final Logblob$Severity logblob$Severity, final String s, final String s2, final String s3, final StopReason stopReason) {
        try {
            logblobLogging.sendLogblob(new OfflineErrorLogblob(logblob$Severity, s, s2, s3, UserVisibleErrorCodeGenerator.getOfflineErrorCodeForStoppedDownload(stopReason), "downloadStopError"));
        }
        catch (JSONException ex) {
            ex.printStackTrace();
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
        return true;
    }
}
