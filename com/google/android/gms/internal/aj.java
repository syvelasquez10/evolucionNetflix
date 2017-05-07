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

public class aj
{
    public static final aj lR;
    
    static {
        lR = new aj();
    }
    
    public static aj az() {
        return aj.lR;
    }
    
    public ah a(final Context context, final as as) {
        final Date birthday = as.getBirthday();
        long time;
        if (birthday != null) {
            time = birthday.getTime();
        }
        else {
            time = -1L;
        }
        final String contentUrl = as.getContentUrl();
        final int gender = as.getGender();
        final Set<String> keywords = as.getKeywords();
        Object unmodifiableList;
        if (!keywords.isEmpty()) {
            unmodifiableList = Collections.unmodifiableList((List<?>)new ArrayList<Object>(keywords));
        }
        else {
            unmodifiableList = null;
        }
        final boolean testDevice = as.isTestDevice(context);
        final int ae = as.aE();
        final Location location = as.getLocation();
        final Bundle networkExtrasBundle = as.getNetworkExtrasBundle(AdMobAdapter.class);
        final boolean manualImpressionsEnabled = as.getManualImpressionsEnabled();
        final String publisherProvidedId = as.getPublisherProvidedId();
        final SearchAdRequest ab = as.aB();
        av av;
        if (ab != null) {
            av = new av(ab);
        }
        else {
            av = null;
        }
        return new ah(3, time, networkExtrasBundle, gender, (List<String>)unmodifiableList, testDevice, ae, manualImpressionsEnabled, publisherProvidedId, av, location, contentUrl);
    }
}
