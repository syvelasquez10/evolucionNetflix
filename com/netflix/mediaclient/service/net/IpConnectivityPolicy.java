// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.net;

public enum IpConnectivityPolicy
{
    IP_V4_ONLY(0), 
    IP_V4_V6(2), 
    IP_V6_ONLY(1), 
    IP_V6_V4(3);
    
    private int mValue;
    
    private IpConnectivityPolicy(final int mValue) {
        this.mValue = mValue;
    }
    
    public static IpConnectivityPolicy from(final int n) {
        switch (n) {
            default: {
                return null;
            }
            case 0: {
                return IpConnectivityPolicy.IP_V4_ONLY;
            }
            case 1: {
                return IpConnectivityPolicy.IP_V6_ONLY;
            }
            case 2: {
                return IpConnectivityPolicy.IP_V4_V6;
            }
            case 3: {
                return IpConnectivityPolicy.IP_V6_V4;
            }
        }
    }
    
    public int getValue() {
        return this.mValue;
    }
}
