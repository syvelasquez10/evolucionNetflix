// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.net;

public enum NetworkType
{
    CDMA(4, "CDMA"), 
    EDGE(2, "EDGE"), 
    EHRPD(14, "EHRPD"), 
    EVDO_0(5, "EVDO_0"), 
    EVDO_A(6, "EVDO_A"), 
    EVDO_B(12, "EVDO_B"), 
    GPRS(1, "GPRS"), 
    HSDPA(8, "HSDPA"), 
    HSPA(10, "  HSPA"), 
    HSUPA(9, "HSUPA"), 
    IDEN(11, "IDEN"), 
    LTE(13, "LTE"), 
    NONE(Integer.MIN_VALUE, "NONE"), 
    UMTS(3, "UMTS"), 
    UNKNOWN(0, "UNKNOWN"), 
    _1xRTT(7, "1xRTT");
    
    private String desc;
    private int type;
    
    private NetworkType(final int type, final String desc) {
        this.type = type;
        this.desc = desc;
    }
    
    public static NetworkType getNetworkType(final int n) {
        for (int i = 0; i < values().length; ++i) {
            if (values()[i].type == n) {
                return values()[i];
            }
        }
        return NetworkType.NONE;
    }
    
    public static boolean is2G(final NetworkType networkType) {
        return NetworkType.EDGE.equals(networkType) || NetworkType.IDEN.equals(networkType) || NetworkType.CDMA.equals(networkType) || NetworkType.GPRS.equals(networkType);
    }
    
    public static boolean is3G(final NetworkType networkType) {
        return NetworkType.UMTS.equals(networkType) || NetworkType.HSDPA.equals(networkType) || NetworkType.HSPA.equals(networkType) || NetworkType.EVDO_0.equals(networkType) || NetworkType.EVDO_A.equals(networkType) || NetworkType.EVDO_B.equals(networkType) || NetworkType._1xRTT.equals(networkType) || NetworkType.HSUPA.equals(networkType) || NetworkType.EHRPD.equals(networkType);
    }
    
    public static boolean is4G(final NetworkType networkType) {
        return NetworkType.LTE.equals(networkType);
    }
    
    public final String getDesc() {
        return this.desc;
    }
    
    public final int getType() {
        return this.type;
    }
}
