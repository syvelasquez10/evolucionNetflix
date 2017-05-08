// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.job;

import com.netflix.mediaclient.util.ConnectivityUtils$NetType;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.ConnectivityUtils;
import android.content.Context;
import com.google.gson.annotations.SerializedName;

public class NetflixJob
{
    private static final String TAG = "nf_netflix_job";
    @SerializedName("minimumDelay")
    private long mMinimumDelay;
    private transient NetflixJob$NetflixJobId mNetflixJobId;
    @SerializedName("value")
    private final int mNetflixJobIdValue;
    @SerializedName("isRepeating")
    private final boolean mRepeating;
    @SerializedName("repeatingPeriodMs")
    private final long mRepeatingPeriodInMs;
    @SerializedName("requiresCharging")
    private final boolean mRequiresCharging;
    @SerializedName("requiresIdle")
    private final boolean mRequiresIdle;
    @SerializedName("requiresUnmeteredNetwork")
    private final boolean mRequiresUnmeteredConnection;
    
    private NetflixJob(final NetflixJob$NetflixJobId mNetflixJobId, final boolean mRequiresUnmeteredConnection, final boolean mRepeating, final long mRepeatingPeriodInMs, final boolean mRequiresCharging, final boolean mRequiresIdle) {
        this.mNetflixJobId = mNetflixJobId;
        this.mRequiresUnmeteredConnection = mRequiresUnmeteredConnection;
        this.mRepeating = mRepeating;
        this.mRepeatingPeriodInMs = mRepeatingPeriodInMs;
        this.mNetflixJobIdValue = mNetflixJobId.getIntValue();
        this.mRequiresCharging = mRequiresCharging;
        this.mRequiresIdle = mRequiresIdle;
    }
    
    public static NetflixJob buildPrefetchLolomoJob(final boolean b, final boolean b2, final long n, final boolean b3, final boolean b4) {
        return new NetflixJob(NetflixJob$NetflixJobId.FALKOR_METADATA, b, b2, n, b3, b4);
    }
    
    public boolean canExecute(final Context context) {
        return this.canExecuteByNetwork(context);
    }
    
    public boolean canExecuteByNetwork(final Context context) {
        final boolean b = true;
        boolean b2 = false;
        if (ConnectivityUtils.isConnectedOrConnecting(context)) {
            final ConnectivityUtils$NetType currentNetType = ConnectivityUtils.getCurrentNetType(context);
            if (Log.isLoggable()) {
                Log.i("nf_netflix_job", "canExecute netType=" + currentNetType);
            }
            if (currentNetType != null) {
                b2 = b;
                if (this.mRequiresUnmeteredConnection) {
                    b2 = (currentNetType != ConnectivityUtils$NetType.mobile && b);
                }
            }
            else {
                b2 = false;
            }
        }
        else if (Log.isLoggable()) {
            Log.i("nf_netflix_job", "isConnectedOrConnecting false");
            return false;
        }
        return b2;
    }
    
    public long getMinimumDelay() {
        return this.mMinimumDelay;
    }
    
    public NetflixJob$NetflixJobId getNetflixJobId() {
        if (this.mNetflixJobId == null) {
            this.mNetflixJobId = NetflixJob$NetflixJobId.getJobIdByValue(this.mNetflixJobIdValue);
        }
        return this.mNetflixJobId;
    }
    
    public long getRepeatingPeriodInMs() {
        return this.mRepeatingPeriodInMs;
    }
    
    public boolean getRequiresCharging() {
        return this.mRequiresCharging;
    }
    
    public boolean getRequiresIdle() {
        return this.mRequiresIdle;
    }
    
    public boolean isRepeating() {
        return this.mRepeating;
    }
    
    public boolean isRequiresUnmeteredConnection() {
        return this.mRequiresUnmeteredConnection;
    }
    
    public void setMinimumDelay(final long mMinimumDelay) {
        if (this.mRepeating) {
            Log.e("nf_netflix_job", "Error, setting minimum delay on a repeating job.");
            return;
        }
        this.mMinimumDelay = mMinimumDelay;
    }
}
