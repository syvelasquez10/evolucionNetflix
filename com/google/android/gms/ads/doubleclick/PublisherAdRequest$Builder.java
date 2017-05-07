// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.doubleclick;

import android.location.Location;
import com.google.android.gms.common.internal.n;
import java.util.Date;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import android.os.Bundle;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import com.google.android.gms.internal.bg$a;

public final class PublisherAdRequest$Builder
{
    private final bg$a le;
    
    public PublisherAdRequest$Builder() {
        this.le = new bg$a();
    }
    
    public PublisherAdRequest$Builder addCustomEventExtrasBundle(final Class<? extends CustomEvent> clazz, final Bundle bundle) {
        this.le.b(clazz, bundle);
        return this;
    }
    
    public PublisherAdRequest$Builder addKeyword(final String s) {
        this.le.r(s);
        return this;
    }
    
    public PublisherAdRequest$Builder addNetworkExtras(final NetworkExtras networkExtras) {
        this.le.a(networkExtras);
        return this;
    }
    
    public PublisherAdRequest$Builder addNetworkExtrasBundle(final Class<? extends MediationAdapter> clazz, final Bundle bundle) {
        this.le.a(clazz, bundle);
        return this;
    }
    
    public PublisherAdRequest$Builder addTestDevice(final String s) {
        this.le.s(s);
        return this;
    }
    
    public PublisherAdRequest build() {
        return new PublisherAdRequest(this, null);
    }
    
    public PublisherAdRequest$Builder setBirthday(final Date date) {
        this.le.a(date);
        return this;
    }
    
    public PublisherAdRequest$Builder setContentUrl(final String s) {
        n.b(s, (Object)"Content URL must be non-null.");
        n.b(s, (Object)"Content URL must be non-empty.");
        n.b(s.length() <= 512, "Content URL must not exceed %d in length.  Provided length was %d.", 512, s.length());
        this.le.t(s);
        return this;
    }
    
    public PublisherAdRequest$Builder setGender(final int n) {
        this.le.g(n);
        return this;
    }
    
    public PublisherAdRequest$Builder setLocation(final Location location) {
        this.le.a(location);
        return this;
    }
    
    public PublisherAdRequest$Builder setManualImpressionsEnabled(final boolean b) {
        this.le.g(b);
        return this;
    }
    
    public PublisherAdRequest$Builder setPublisherProvidedId(final String s) {
        this.le.u(s);
        return this;
    }
    
    public PublisherAdRequest$Builder tagForChildDirectedTreatment(final boolean b) {
        this.le.h(b);
        return this;
    }
}
