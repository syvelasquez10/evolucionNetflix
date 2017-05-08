// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.error.crypto;

import com.netflix.mediaclient.service.error.ErrorDescriptor;
import com.netflix.mediaclient.service.configuration.crypto.CryptoProvider;
import com.netflix.mediaclient.service.configuration.crypto.CryptoManagerRegistry;
import org.json.JSONArray;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.ui.offline.DownloadButton;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentListener;
import java.util.Iterator;
import android.util.Log;
import java.util.ArrayList;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.List;
import com.netflix.mediaclient.servicemgr.ErrorLogging;
import com.netflix.mediaclient.servicemgr.IErrorHandler;
import android.content.Context;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.util.TimeUtils;
import org.json.JSONObject;
import android.os.SystemClock;
import com.netflix.mediaclient.StatusCode;

class CryptoErrorManager$FatalCryptoError
{
    private static final String APP_STARTUP_TIME = "appStartupTime";
    private static final String CAUSE = "cause";
    private static final String SOURCE = "src";
    private static final String TS = "ts";
    private static final String UP = "up";
    long appStartupTimeInMs;
    ErrorSource errorSource;
    long howLongDeviceWasUpInMs;
    StatusCode statusCode;
    long timestamp;
    
    CryptoErrorManager$FatalCryptoError(final ErrorSource errorSource, final StatusCode statusCode, final long appStartupTimeInMs) {
        this.errorSource = errorSource;
        this.statusCode = statusCode;
        this.timestamp = System.currentTimeMillis();
        this.howLongDeviceWasUpInMs = SystemClock.elapsedRealtime();
        this.appStartupTimeInMs = appStartupTimeInMs;
    }
    
    CryptoErrorManager$FatalCryptoError(final JSONObject jsonObject) {
        this.timestamp = jsonObject.getLong("ts");
        this.howLongDeviceWasUpInMs = jsonObject.getLong("up");
        this.appStartupTimeInMs = jsonObject.getLong("appStartupTime");
        this.errorSource = ErrorSource.valueOf(jsonObject.getString("src"));
        this.statusCode = StatusCode.getStatusCodeByValue(jsonObject.getInt("cause"));
    }
    
    boolean belongToApplicationInstance(final long n) {
        return this.appStartupTimeInMs == n;
    }
    
    long getDeviceBootTimeInMs() {
        return this.timestamp - this.howLongDeviceWasUpInMs;
    }
    
    boolean isValid() {
        return this.timestamp + CryptoErrorManager.DELTA_MS > System.currentTimeMillis();
    }
    
    JSONObject toJson() {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("ts", this.timestamp);
        jsonObject.put("appStartupTime", this.appStartupTimeInMs);
        jsonObject.put("up", this.howLongDeviceWasUpInMs);
        jsonObject.put("src", (Object)this.errorSource.name());
        jsonObject.put("cause", this.statusCode.getValue());
        return jsonObject;
    }
    
    @Override
    public String toString() {
        return "FatalCryptoError{timestamp=" + this.timestamp + ", howLongDeviceWasUpInMs=" + this.howLongDeviceWasUpInMs + ", appStartupTimeInMs=" + this.appStartupTimeInMs + ", errorSource=" + this.errorSource + ", statusCode=" + this.statusCode + '}';
    }
    
    boolean wasDeviceRestartedSinceErrorOccured(final long n) {
        final boolean b = true;
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        boolean b2;
        if (this.belongToApplicationInstance(n)) {
            b2 = false;
        }
        else {
            if (elapsedRealtime > 0L) {
                b2 = b;
                if (elapsedRealtime < this.howLongDeviceWasUpInMs) {
                    return b2;
                }
            }
            b2 = b;
            if (this.getDeviceBootTimeInMs() == TimeUtils.getDeviceCurrentBootTimeInMs()) {
                return false;
            }
        }
        return b2;
    }
}
