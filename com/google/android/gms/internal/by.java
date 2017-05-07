// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Set;
import java.util.Date;
import java.util.Collection;
import java.util.HashSet;
import com.google.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.a;
import com.google.ads.AdSize;
import com.google.ads.AdRequest;

public final class by
{
    public static int a(final AdRequest.ErrorCode errorCode) {
        switch (by$1.nM[errorCode.ordinal()]) {
            default: {
                return 0;
            }
            case 2: {
                return 1;
            }
            case 3: {
                return 2;
            }
            case 4: {
                return 3;
            }
        }
    }
    
    public static AdSize b(final ak ak) {
        int i = 0;
        for (AdSize[] array = { AdSize.SMART_BANNER, AdSize.BANNER, AdSize.IAB_MRECT, AdSize.IAB_BANNER, AdSize.IAB_LEADERBOARD, AdSize.IAB_WIDE_SKYSCRAPER }; i < array.length; ++i) {
            if (array[i].getWidth() == ak.width && array[i].getHeight() == ak.height) {
                return array[i];
            }
        }
        return new AdSize(a.a(ak.width, ak.height, ak.lS));
    }
    
    public static MediationAdRequest e(final ah ah) {
        HashSet<String> set;
        if (ah.lJ != null) {
            set = new HashSet<String>(ah.lJ);
        }
        else {
            set = null;
        }
        return new MediationAdRequest(new Date(ah.lH), g(ah.lI), set, ah.lK, ah.lP);
    }
    
    public static AdRequest.Gender g(final int n) {
        switch (n) {
            default: {
                return AdRequest.Gender.UNKNOWN;
            }
            case 2: {
                return AdRequest.Gender.FEMALE;
            }
            case 1: {
                return AdRequest.Gender.MALE;
            }
        }
    }
}
