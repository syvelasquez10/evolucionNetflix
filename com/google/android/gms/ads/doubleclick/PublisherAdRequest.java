// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.doubleclick;

import com.google.android.gms.internal.fq;
import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import android.location.Location;
import java.util.Set;
import java.util.Date;
import com.google.android.gms.internal.as;

public final class PublisherAdRequest
{
    public static final String DEVICE_ID_EMULATOR;
    public static final int ERROR_CODE_INTERNAL_ERROR = 0;
    public static final int ERROR_CODE_INVALID_REQUEST = 1;
    public static final int ERROR_CODE_NETWORK_ERROR = 2;
    public static final int ERROR_CODE_NO_FILL = 3;
    public static final int GENDER_FEMALE = 2;
    public static final int GENDER_MALE = 1;
    public static final int GENDER_UNKNOWN = 0;
    private final as kp;
    
    static {
        DEVICE_ID_EMULATOR = as.DEVICE_ID_EMULATOR;
    }
    
    private PublisherAdRequest(final Builder builder) {
        this.kp = new as(builder.kq);
    }
    
    as O() {
        return this.kp;
    }
    
    public Date getBirthday() {
        return this.kp.getBirthday();
    }
    
    public String getContentUrl() {
        return this.kp.getContentUrl();
    }
    
    public int getGender() {
        return this.kp.getGender();
    }
    
    public Set<String> getKeywords() {
        return this.kp.getKeywords();
    }
    
    public Location getLocation() {
        return this.kp.getLocation();
    }
    
    public boolean getManualImpressionsEnabled() {
        return this.kp.getManualImpressionsEnabled();
    }
    
    @Deprecated
    public <T extends NetworkExtras> T getNetworkExtras(final Class<T> clazz) {
        return this.kp.getNetworkExtras(clazz);
    }
    
    public <T extends MediationAdapter> Bundle getNetworkExtrasBundle(final Class<T> clazz) {
        return this.kp.getNetworkExtrasBundle(clazz);
    }
    
    public String getPublisherProvidedId() {
        return this.kp.getPublisherProvidedId();
    }
    
    public boolean isTestDevice(final Context context) {
        return this.kp.isTestDevice(context);
    }
    
    public static final class Builder
    {
        private final as.a kq;
        
        public Builder() {
            this.kq = new as.a();
        }
        
        public Builder addKeyword(final String s) {
            this.kq.g(s);
            return this;
        }
        
        public Builder addNetworkExtras(final NetworkExtras networkExtras) {
            this.kq.a(networkExtras);
            return this;
        }
        
        public Builder addNetworkExtrasBundle(final Class<? extends MediationAdapter> clazz, final Bundle bundle) {
            this.kq.a(clazz, bundle);
            return this;
        }
        
        public Builder addTestDevice(final String s) {
            this.kq.h(s);
            return this;
        }
        
        public PublisherAdRequest build() {
            return new PublisherAdRequest(this, null);
        }
        
        public Builder setBirthday(final Date date) {
            this.kq.a(date);
            return this;
        }
        
        public Builder setContentUrl(final String s) {
            fq.b(s, (Object)"Content URL must be non-null.");
            fq.b(s, (Object)"Content URL must be non-empty.");
            fq.a(s.length() <= 512, "Content URL must not exceed %d in length.  Provided length was %d.", 512, s.length());
            this.kq.i(s);
            return this;
        }
        
        public Builder setGender(final int n) {
            this.kq.d(n);
            return this;
        }
        
        public Builder setLocation(final Location location) {
            this.kq.a(location);
            return this;
        }
        
        public Builder setManualImpressionsEnabled(final boolean b) {
            this.kq.f(b);
            return this;
        }
        
        public Builder setPublisherProvidedId(final String s) {
            this.kq.j(s);
            return this;
        }
        
        public Builder tagForChildDirectedTreatment(final boolean b) {
            this.kq.g(b);
            return this;
        }
    }
}
