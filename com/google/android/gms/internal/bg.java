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

public final class bg
{
    public static int a(final AdRequest.ErrorCode errorCode) {
        switch (bg$1.gm[errorCode.ordinal()]) {
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
    
    public static int a(final AdRequest.Gender gender) {
        switch (bg$1.gl[gender.ordinal()]) {
            default: {
                return 0;
            }
            case 1: {
                return 2;
            }
            case 2: {
                return 1;
            }
        }
    }
    
    public static AdSize b(final x x) {
        return new AdSize(a.a(x.width, x.height, x.eF));
    }
    
    public static MediationAdRequest e(final v v) {
        HashSet<String> set;
        if (v.ez != null) {
            set = new HashSet<String>(v.ez);
        }
        else {
            set = null;
        }
        return new MediationAdRequest(new Date(v.ex), g(v.ey), set, v.eA);
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
    
    public static final AdRequest.ErrorCode h(final int n) {
        switch (n) {
            default: {
                return AdRequest.ErrorCode.INTERNAL_ERROR;
            }
            case 1: {
                return AdRequest.ErrorCode.INVALID_REQUEST;
            }
            case 2: {
                return AdRequest.ErrorCode.NETWORK_ERROR;
            }
            case 3: {
                return AdRequest.ErrorCode.NO_FILL;
            }
        }
    }
}
