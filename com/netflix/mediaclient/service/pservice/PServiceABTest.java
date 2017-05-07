// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pservice;

import java.util.Map;

public final class PServiceABTest
{
    static Map<PDiskData$ListType, Integer> mPartnerDefaultExp;
    static Map<PDiskData$ListType, Integer> mWidgetDefaultExp;
    
    static {
        PServiceABTest.mPartnerDefaultExp = new PServiceABTest$1();
        PServiceABTest.mWidgetDefaultExp = new PServiceABTest$2();
    }
    
    public static int getVideoCountOfListForPartnerExp(final PDiskData$ListType pDiskData$ListType, final PDiskData pDiskData) {
        final String preAppPartnerExperience = pDiskData.preAppPartnerExperience;
        switch (preAppPartnerExperience.hashCode()) {
            case 1544803905: {
                if (preAppPartnerExperience.equals("default")) {
                    break;
                }
                break;
            }
        }
        return PServiceABTest.mPartnerDefaultExp.get(pDiskData$ListType);
    }
    
    public static int getVideoCountOfListForWidgetExp(final PDiskData$ListType pDiskData$ListType, final PDiskData pDiskData) {
        final String preAppWidgetExperience = pDiskData.preAppWidgetExperience;
        switch (preAppWidgetExperience.hashCode()) {
            case 1544803905: {
                if (preAppWidgetExperience.equals("default")) {
                    break;
                }
                break;
            }
        }
        return PServiceABTest.mWidgetDefaultExp.get(pDiskData$ListType);
    }
}
