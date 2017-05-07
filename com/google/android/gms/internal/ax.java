// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.search.SearchAdRequest;
import android.os.Bundle;
import android.location.Location;
import java.util.Set;
import java.util.Date;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.ads.mediation.admob.AdMobAdapter;
import java.util.List;
import java.util.Collections;
import java.util.Collection;
import java.util.ArrayList;
import android.content.Context;

@ez
public class ax
{
    public static final ax oe;
    
    static {
        oe = new ax();
    }
    
    public static ax bb() {
        return ax.oe;
    }
    
    public av a(final Context context, final bg bg) {
        final Date birthday = bg.getBirthday();
        long time;
        if (birthday != null) {
            time = birthday.getTime();
        }
        else {
            time = -1L;
        }
        final String contentUrl = bg.getContentUrl();
        final int gender = bg.getGender();
        final Set<String> keywords = bg.getKeywords();
        Object unmodifiableList;
        if (!keywords.isEmpty()) {
            unmodifiableList = Collections.unmodifiableList((List<?>)new ArrayList<Object>(keywords));
        }
        else {
            unmodifiableList = null;
        }
        final boolean testDevice = bg.isTestDevice(context);
        final int bg2 = bg.bg();
        final Location location = bg.getLocation();
        final Bundle networkExtrasBundle = bg.getNetworkExtrasBundle(AdMobAdapter.class);
        final boolean manualImpressionsEnabled = bg.getManualImpressionsEnabled();
        final String publisherProvidedId = bg.getPublisherProvidedId();
        final SearchAdRequest bd = bg.bd();
        bj bj;
        if (bd != null) {
            bj = new bj(bd);
        }
        else {
            bj = null;
        }
        return new av(4, time, networkExtrasBundle, gender, (List<String>)unmodifiableList, testDevice, bg2, manualImpressionsEnabled, publisherProvidedId, bj, location, contentUrl, bg.bf());
    }
}
