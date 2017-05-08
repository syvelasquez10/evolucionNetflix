// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.net;

enum NetworkSpeedType
{
    DSL_512("DSL 512", 3, 524288L), 
    EDGE("EDGE", 2, 262144L), 
    GPRS("GPRS", 1, 131072L), 
    LTE_4G_BASIC("LTE/4G basic", 5, 3145728L), 
    NoLimit("No Limit", 0, -1L), 
    UMTS_1MBPS("UMTS 1mpbs", 4, 1048576L), 
    WIFI_SPEED1("WiFi low", 6, 5242880L), 
    WIFI_SPEED2("WiFi medium", 7, 8388608L), 
    WIFI_SPEED3("WiFi high", 8, 16777216L);
    
    private final String mDisplayStr;
    private final int mKey;
    private final long mSpeedLimitBitsPerSecond;
    
    private NetworkSpeedType(final String mDisplayStr, final int mKey, final long mSpeedLimitBitsPerSecond) {
        this.mDisplayStr = mDisplayStr;
        this.mKey = mKey;
        this.mSpeedLimitBitsPerSecond = mSpeedLimitBitsPerSecond;
    }
    
    public static NetworkSpeedType getByKeyValue(final int n) {
        final NetworkSpeedType[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final NetworkSpeedType networkSpeedType = values[i];
            if (networkSpeedType.getKeyValue() == n) {
                return networkSpeedType;
            }
        }
        return NetworkSpeedType.NoLimit;
    }
    
    public int getKeyValue() {
        return this.mKey;
    }
    
    public long getSpeedInBitsPerSecond() {
        return this.mSpeedLimitBitsPerSecond;
    }
    
    public String getStringForDebugMenu() {
        if (this.mSpeedLimitBitsPerSecond == -1L) {
            return this.mDisplayStr;
        }
        String s;
        if (this.mSpeedLimitBitsPerSecond < 1024L) {
            s = "" + this.mSpeedLimitBitsPerSecond + "bps";
        }
        else if (this.mSpeedLimitBitsPerSecond < 1048576L) {
            s = "" + this.mSpeedLimitBitsPerSecond / 1024L + "kps";
        }
        else if (this.mSpeedLimitBitsPerSecond < 1073741824L) {
            s = "" + this.mSpeedLimitBitsPerSecond / 1048576L + "mbps";
        }
        else {
            s = "" + this.mSpeedLimitBitsPerSecond / 1073741824L + "gbps";
        }
        return this.mDisplayStr + " (" + s + ")";
    }
}
