// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.agent;

import com.netflix.mediaclient.util.Time;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.service.offline.download.OfflinePlayablePersistentData$LicenseData;
import com.netflix.mediaclient.android.app.NetflixStatus;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.util.ConnectivityUtils;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.offline.download.OfflinePlayablePersistentData;
import android.content.Context;
import java.util.concurrent.TimeUnit;

public class PlayabilityEnforcer
{
    private static final long PERSISTENT_INTERVAL;
    private static final long REFRESH_LICENSE_AHEAD_MS = 864000000L;
    private static final String TAG = "nf_PlayabilityEnforcer";
    private static final Object lastContactNetflixTimeLock;
    private static long lastContactNetflixTimeMS;
    
    static {
        PERSISTENT_INTERVAL = TimeUnit.MINUTES.toMillis(1L);
        lastContactNetflixTimeLock = new Object();
        PlayabilityEnforcer.lastContactNetflixTimeMS = 0L;
    }
    
    static Status forceResetPlayWindow(final Context context, final OfflinePlayablePersistentData offlinePlayablePersistentData) {
        if (offlinePlayablePersistentData.mLicenseData.mPwResettable && offlinePlayablePersistentData.mLicenseData.mPlayWindowResetLimit > 0L && ConnectivityUtils.isConnected(context)) {
            final OfflinePlayablePersistentData$LicenseData mLicenseData = offlinePlayablePersistentData.mLicenseData;
            --mLicenseData.mPlayWindowResetLimit;
            offlinePlayablePersistentData.mPlayStartTime = 0L;
            return CommonStatus.OK;
        }
        return new NetflixStatus(StatusCode.DL_PLAY_WINDOW_RENEW_FAILED);
    }
    
    private static long getLastContactNetflix(final Context context) {
        synchronized (PlayabilityEnforcer.lastContactNetflixTimeLock) {
            if (PlayabilityEnforcer.lastContactNetflixTimeMS == 0L) {
                PlayabilityEnforcer.lastContactNetflixTimeMS = PreferenceUtils.getLongPref(context, "last_contact_netflix_ms", -1L);
            }
            if (Log.isLoggable()) {
                Log.d("nf_PlayabilityEnforcer", "getLastContactNetflix " + PlayabilityEnforcer.lastContactNetflixTimeMS + ", now " + System.currentTimeMillis());
            }
            return PlayabilityEnforcer.lastContactNetflixTimeMS;
        }
    }
    
    public static boolean hasRecentHomingAndConnectivity(final Context context) {
        final long lastContactNetflix = getLastContactNetflix(context);
        return lastContactNetflix > 0L && lastContactNetflix + 2L * PlayabilityEnforcer.PERSISTENT_INTERVAL >= System.currentTimeMillis() && ConnectivityUtils.isConnected(context);
    }
    
    public static boolean isAllowedByPlayWindow(final Context context, final OfflinePlayablePersistentData offlinePlayablePersistentData, final OfflinePlayablePersistentData$LicenseData offlinePlayablePersistentData$LicenseData) {
        if (Log.isLoggable()) {
            Log.d("nf_PlayabilityEnforcer", "mShouldUsePlayWindowLimits " + offlinePlayablePersistentData$LicenseData.mShouldUsePlayWindowLimits + ", now " + System.currentTimeMillis() + ", mPlayStartTime " + offlinePlayablePersistentData.mPlayStartTime);
        }
        if (!offlinePlayablePersistentData$LicenseData.mShouldUsePlayWindowLimits || offlinePlayablePersistentData.mPlayStartTime <= 0L) {
            return true;
        }
        final long currentTimeMillis = System.currentTimeMillis();
        if (Log.isLoggable()) {
            Log.d("nf_PlayabilityEnforcer", "mPlayableWindowInHour " + offlinePlayablePersistentData$LicenseData.mPlayableWindowInHour + ", mPwResettable " + offlinePlayablePersistentData$LicenseData.mPwResettable + ", mPlayWindowResetLimit " + offlinePlayablePersistentData$LicenseData.mPlayWindowResetLimit + ", delta1 " + (currentTimeMillis - offlinePlayablePersistentData.mPlayStartTime) + ", lastHoming " + getLastContactNetflix(context) + ", delta2 " + (currentTimeMillis - getLastContactNetflix(context)));
        }
        if (currentTimeMillis - offlinePlayablePersistentData.mPlayStartTime < TimeUnit.HOURS.toMillis(offlinePlayablePersistentData$LicenseData.mPlayableWindowInHour)) {
            return true;
        }
        final long lastContactNetflix = getLastContactNetflix(context);
        if (!offlinePlayablePersistentData$LicenseData.mPwResettable || offlinePlayablePersistentData$LicenseData.mPlayWindowResetLimit <= 0L || lastContactNetflix <= 0L || currentTimeMillis - lastContactNetflix >= TimeUnit.HOURS.toMillis(offlinePlayablePersistentData$LicenseData.mPlayableWindowInHour)) {
            return false;
        }
        --offlinePlayablePersistentData$LicenseData.mPlayWindowResetLimit;
        synchronized (offlinePlayablePersistentData) {
            offlinePlayablePersistentData.mPlayStartTime = 0L;
            // monitorexit(offlinePlayablePersistentData)
            Log.d("nf_PlayabilityEnforcer", "reset play window");
            return true;
        }
        return false;
    }
    
    public static boolean isAllowedByViewWindow(final OfflinePlayablePersistentData$LicenseData offlinePlayablePersistentData$LicenseData) {
        return offlinePlayablePersistentData$LicenseData.mViewingWindow >= System.currentTimeMillis();
    }
    
    public static boolean isLicenseExpired(final OfflinePlayablePersistentData$LicenseData offlinePlayablePersistentData$LicenseData) {
        return offlinePlayablePersistentData$LicenseData.mExpirationTimeInMs < Time.now();
    }
    
    public static boolean shouldRefreshLicense(final OfflinePlayablePersistentData$LicenseData offlinePlayablePersistentData$LicenseData) {
        final long now = Time.now();
        return (offlinePlayablePersistentData$LicenseData.mShouldRefreshByTimestamp && now >= offlinePlayablePersistentData$LicenseData.mRefreshLicenseTimestamp) || (offlinePlayablePersistentData$LicenseData.mExpirationTimeInMs > 0L && offlinePlayablePersistentData$LicenseData.mExpirationTimeInMs <= now + 864000000L);
    }
    
    public static void updateLastContactNetflix(final Context context) {
        while (true) {
            while (true) {
                Label_0094: {
                    synchronized (PlayabilityEnforcer.lastContactNetflixTimeLock) {
                        final long currentTimeMillis = System.currentTimeMillis();
                        if (currentTimeMillis >= PlayabilityEnforcer.lastContactNetflixTimeMS + PlayabilityEnforcer.PERSISTENT_INTERVAL) {
                            PreferenceUtils.putLongPref(context, "last_contact_netflix_ms", currentTimeMillis);
                            PlayabilityEnforcer.lastContactNetflixTimeMS = currentTimeMillis;
                        }
                        if (Log.isLoggable()) {
                            final StringBuilder sb = new StringBuilder();
                            if (currentTimeMillis != PlayabilityEnforcer.lastContactNetflixTimeMS) {
                                break Label_0094;
                            }
                            final String s = "persisted,";
                            Log.d("nf_PlayabilityEnforcer", sb.append(s).append("updateLastContactNetflix ").append(PlayabilityEnforcer.lastContactNetflixTimeMS).toString());
                        }
                        return;
                    }
                }
                final String s = "";
                continue;
            }
        }
    }
}
