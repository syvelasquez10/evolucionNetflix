// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.ads.AdRequest$Gender;
import java.util.Set;
import java.util.Date;
import java.util.Collection;
import java.util.HashSet;
import com.google.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.a;
import com.google.ads.AdSize;
import com.google.ads.AdRequest$ErrorCode;

@ez
public final class db
{
    public static int a(final AdRequest$ErrorCode adRequest$ErrorCode) {
        switch (db$1.qL[adRequest$ErrorCode.ordinal()]) {
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
    
    public static AdSize b(final ay ay) {
        int i = 0;
        for (AdSize[] array = { AdSize.SMART_BANNER, AdSize.BANNER, AdSize.IAB_MRECT, AdSize.IAB_BANNER, AdSize.IAB_LEADERBOARD, AdSize.IAB_WIDE_SKYSCRAPER }; i < array.length; ++i) {
            if (array[i].getWidth() == ay.width && array[i].getHeight() == ay.height) {
                return array[i];
            }
        }
        return new AdSize(a.a(ay.width, ay.height, ay.of));
    }
    
    public static MediationAdRequest d(final av av) {
        HashSet<String> set;
        if (av.nV != null) {
            set = new HashSet<String>(av.nV);
        }
        else {
            set = null;
        }
        return new MediationAdRequest(new Date(av.nT), k(av.nU), set, av.nW, av.ob);
    }
    
    public static AdRequest$Gender k(final int n) {
        switch (n) {
            default: {
                return AdRequest$Gender.UNKNOWN;
            }
            case 2: {
                return AdRequest$Gender.FEMALE;
            }
            case 1: {
                return AdRequest$Gender.MALE;
            }
        }
    }
}
