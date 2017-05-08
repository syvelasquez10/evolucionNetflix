// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.error.crypto;

import com.netflix.mediaclient.service.error.ErrorDescriptor;
import com.netflix.mediaclient.service.configuration.crypto.CryptoProvider;
import com.netflix.mediaclient.service.configuration.crypto.CryptoManagerRegistry;
import android.app.ActivityManager;
import org.json.JSONArray;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.service.offline.agent.OfflineAgent;
import java.util.Iterator;
import com.netflix.mediaclient.ui.offline.DownloadButton;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentListener;
import java.util.ArrayList;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface;
import java.util.concurrent.atomic.AtomicBoolean;
import android.os.Handler;
import java.util.List;
import com.netflix.mediaclient.servicemgr.ErrorLogging;
import com.netflix.mediaclient.servicemgr.IErrorHandler;
import android.content.Context;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import com.netflix.mediaclient.util.TimeUtils;
import android.os.SystemClock;
import com.netflix.mediaclient.StatusCode;

class CryptoErrorManager$FatalCryptoError
{
    private static final String APP_STARTUP_TIME = "appStartupTime";
    private static final String CAUSE = "cause";
    private static final String DEVICE_BOOT_TIME = "deviceBootTime";
    private static final String SOURCE = "src";
    private static final String TS = "ts";
    private static final String UP = "up";
    long appStartupTimeInMs;
    long deviceBootTimeInMs;
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
        this.deviceBootTimeInMs = TimeUtils.getDeviceCurrentBootTimeInMs();
    }
    
    CryptoErrorManager$FatalCryptoError(final JSONObject jsonObject) {
        this.timestamp = jsonObject.getLong("ts");
        this.howLongDeviceWasUpInMs = jsonObject.getLong("up");
        this.appStartupTimeInMs = jsonObject.getLong("appStartupTime");
        this.deviceBootTimeInMs = jsonObject.getLong("deviceBootTime");
        this.errorSource = ErrorSource.valueOf(jsonObject.getString("src"));
        this.statusCode = StatusCode.getStatusCodeByValue(jsonObject.getInt("cause"));
    }
    
    boolean belongToApplicationInstance(final long n) {
        return this.appStartupTimeInMs == n;
    }
    
    long getDeviceBootTimeInMs() {
        return this.deviceBootTimeInMs;
    }
    
    boolean isValid() {
        return this.timestamp + CryptoErrorManager.DELTA_MS > System.currentTimeMillis();
    }
    
    JSONObject toJson() {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("ts", this.timestamp);
        jsonObject.put("appStartupTime", this.appStartupTimeInMs);
        jsonObject.put("deviceBootTime", this.deviceBootTimeInMs);
        jsonObject.put("up", this.howLongDeviceWasUpInMs);
        jsonObject.put("src", (Object)this.errorSource.name());
        jsonObject.put("cause", this.statusCode.getValue());
        return jsonObject;
    }
    
    @Override
    public String toString() {
        return "FatalCryptoError{appStartupTimeInMs=" + this.appStartupTimeInMs + ", timestamp=" + this.timestamp + ", howLongDeviceWasUpInMs=" + this.howLongDeviceWasUpInMs + ", deviceBootTimeInMs=" + this.deviceBootTimeInMs + ", errorSource=" + this.errorSource + ", statusCode=" + this.statusCode + '}';
    }
    
    boolean wasDeviceRestartedSinceErrorOccured(long deviceBootTimeInMs) {
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        if (this.belongToApplicationInstance(deviceBootTimeInMs)) {
            Log.d(CryptoErrorManager.TAG, "belongToApplicationInstance: true, device not restarted...");
            return false;
        }
        if (elapsedRealtime > 0L && elapsedRealtime < this.howLongDeviceWasUpInMs) {
            Log.d(CryptoErrorManager.TAG, "Up time: %d; howLongDeviceWasUpInMs: %d. Device was restarted...", elapsedRealtime, this.howLongDeviceWasUpInMs);
            return true;
        }
        deviceBootTimeInMs = this.getDeviceBootTimeInMs();
        final long deviceCurrentBootTimeInMs = TimeUtils.getDeviceCurrentBootTimeInMs();
        final boolean b = deviceCurrentBootTimeInMs != deviceBootTimeInMs;
        Log.d(CryptoErrorManager.TAG, "Old error boot time: %d; Current boot time: %d. Device was restarted: %b", deviceBootTimeInMs, deviceCurrentBootTimeInMs, b);
        return b;
    }
}
