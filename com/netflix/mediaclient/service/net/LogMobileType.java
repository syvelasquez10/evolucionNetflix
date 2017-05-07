// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.net;

import com.netflix.mediaclient.Log;
import android.net.NetworkInfo;

public enum LogMobileType
{
    MOBILE("mobile");
    
    private static final String TAG = "nf_net";
    
    UKNOWN("uknown"), 
    WIFI("wifi"), 
    _2G("2g"), 
    _3G("3g"), 
    _4G("4g");
    
    private String desc;
    
    private LogMobileType(final String desc) {
        this.desc = desc;
    }
    
    public static LogMobileType toLogMobileType(final NetworkInfo networkInfo) {
        if (networkInfo == null) {
            return null;
        }
        if (networkInfo.getType() == 6) {
            return LogMobileType._4G;
        }
        if (networkInfo.getType() == 1) {
            return LogMobileType.WIFI;
        }
        final NetworkType networkType = NetworkType.getNetworkType(networkInfo.getSubtype());
        if (Log.isLoggable()) {
            Log.d("nf_net", "NetworkType" + networkType);
        }
        return toLogMobileType(networkType);
    }
    
    private static LogMobileType toLogMobileType(final NetworkType networkType) {
        if (networkType == null) {
            return LogMobileType.UKNOWN;
        }
        if (NetworkType.is2G(networkType)) {
            return LogMobileType._2G;
        }
        if (NetworkType.is3G(networkType)) {
            return LogMobileType._3G;
        }
        if (NetworkType.is4G(networkType)) {
            return LogMobileType._4G;
        }
        if (NetworkType.UNKNOWN.equals(networkType)) {
            return LogMobileType.MOBILE;
        }
        return LogMobileType.UKNOWN;
    }
    
    public final String getDesc() {
        return this.desc;
    }
}
