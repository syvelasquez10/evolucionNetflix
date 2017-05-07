// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.ads.search.SearchAdRequest;
import java.util.Set;
import java.util.Date;
import com.google.android.gms.ads.mediation.admob.AdMobExtras;
import java.util.Collections;
import java.util.Collection;
import java.util.ArrayList;
import android.content.Context;
import java.util.List;
import android.os.Bundle;
import android.location.Location;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class v implements SafeParcelable
{
    public static final w CREATOR;
    public final boolean eA;
    public final boolean eB;
    public final String eC;
    public final ai eD;
    public final Location eE;
    public final long ex;
    public final Bundle extras;
    public final int ey;
    public final List<String> ez;
    public final int tagForChildDirectedTreatment;
    public final int versionCode;
    
    static {
        CREATOR = new w();
    }
    
    v(final int versionCode, final long ex, final Bundle extras, final int ey, final List<String> ez, final boolean ea, final int tagForChildDirectedTreatment, final boolean eb, final String ec, final ai ed, final Location ee) {
        this.versionCode = versionCode;
        this.ex = ex;
        this.extras = extras;
        this.ey = ey;
        this.ez = ez;
        this.eA = ea;
        this.tagForChildDirectedTreatment = tagForChildDirectedTreatment;
        this.eB = eb;
        this.eC = ec;
        this.eD = ed;
        this.eE = ee;
    }
    
    public v(final Context context, final af af) {
        final ai ai = null;
        this.versionCode = 2;
        final Date birthday = af.getBirthday();
        long time;
        if (birthday != null) {
            time = birthday.getTime();
        }
        else {
            time = -1L;
        }
        this.ex = time;
        this.ey = af.getGender();
        final Set<String> keywords = af.getKeywords();
        Object unmodifiableList;
        if (!keywords.isEmpty()) {
            unmodifiableList = Collections.unmodifiableList((List<?>)new ArrayList<Object>(keywords));
        }
        else {
            unmodifiableList = null;
        }
        this.ez = (List<String>)unmodifiableList;
        this.eA = af.isTestDevice(context);
        this.tagForChildDirectedTreatment = af.S();
        this.eE = af.getLocation();
        final AdMobExtras adMobExtras = af.getNetworkExtras(AdMobExtras.class);
        Bundle extras;
        if (adMobExtras != null) {
            extras = adMobExtras.getExtras();
        }
        else {
            extras = null;
        }
        this.extras = extras;
        this.eB = af.getManualImpressionsEnabled();
        this.eC = af.getPublisherProvidedId();
        final SearchAdRequest q = af.Q();
        ai ed = ai;
        if (q != null) {
            ed = new ai(q);
        }
        this.eD = ed;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        w.a(this, parcel, n);
    }
}
