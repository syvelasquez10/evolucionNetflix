// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads;

import com.google.android.gms.common.internal.n;
import android.content.Context;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import android.location.Location;
import java.util.Set;
import android.os.Bundle;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import java.util.Date;
import com.google.android.gms.internal.bg;

public final class AdRequest
{
    public static final String DEVICE_ID_EMULATOR;
    public static final int ERROR_CODE_INTERNAL_ERROR = 0;
    public static final int ERROR_CODE_INVALID_REQUEST = 1;
    public static final int ERROR_CODE_NETWORK_ERROR = 2;
    public static final int ERROR_CODE_NO_FILL = 3;
    public static final int GENDER_FEMALE = 2;
    public static final int GENDER_MALE = 1;
    public static final int GENDER_UNKNOWN = 0;
    public static final int MAX_CONTENT_URL_LENGTH = 512;
    private final bg ld;
    
    static {
        DEVICE_ID_EMULATOR = bg.DEVICE_ID_EMULATOR;
    }
    
    private AdRequest(final Builder builder) {
        this.ld = new bg(builder.le);
    }
    
    bg V() {
        return this.ld;
    }
    
    public Date getBirthday() {
        return this.ld.getBirthday();
    }
    
    public String getContentUrl() {
        return this.ld.getContentUrl();
    }
    
    public <T extends CustomEvent> Bundle getCustomEventExtrasBundle(final Class<T> clazz) {
        return this.ld.getCustomEventExtrasBundle(clazz);
    }
    
    public int getGender() {
        return this.ld.getGender();
    }
    
    public Set<String> getKeywords() {
        return this.ld.getKeywords();
    }
    
    public Location getLocation() {
        return this.ld.getLocation();
    }
    
    @Deprecated
    public <T extends NetworkExtras> T getNetworkExtras(final Class<T> clazz) {
        return this.ld.getNetworkExtras(clazz);
    }
    
    public <T extends MediationAdapter> Bundle getNetworkExtrasBundle(final Class<T> clazz) {
        return this.ld.getNetworkExtrasBundle(clazz);
    }
    
    public boolean isTestDevice(final Context context) {
        return this.ld.isTestDevice(context);
    }
    
    public static final class Builder
    {
        private final bg.a le;
        
        public Builder() {
            this.le = new bg.a();
        }
        
        public Builder addCustomEventExtrasBundle(final Class<? extends CustomEvent> clazz, final Bundle bundle) {
            this.le.b(clazz, bundle);
            return this;
        }
        
        public Builder addKeyword(final String s) {
            this.le.r(s);
            return this;
        }
        
        public Builder addNetworkExtras(final NetworkExtras networkExtras) {
            this.le.a(networkExtras);
            return this;
        }
        
        public Builder addNetworkExtrasBundle(final Class<? extends MediationAdapter> clazz, final Bundle bundle) {
            this.le.a(clazz, bundle);
            return this;
        }
        
        public Builder addTestDevice(final String s) {
            this.le.s(s);
            return this;
        }
        
        public AdRequest build() {
            return new AdRequest(this, null);
        }
        
        public Builder setBirthday(final Date date) {
            this.le.a(date);
            return this;
        }
        
        public Builder setContentUrl(final String s) {
            n.b(s, (Object)"Content URL must be non-null.");
            n.b(s, (Object)"Content URL must be non-empty.");
            n.b(s.length() <= 512, "Content URL must not exceed %d in length.  Provided length was %d.", 512, s.length());
            this.le.t(s);
            return this;
        }
        
        public Builder setGender(final int n) {
            this.le.g(n);
            return this;
        }
        
        public Builder setLocation(final Location location) {
            this.le.a(location);
            return this;
        }
        
        public Builder tagForChildDirectedTreatment(final boolean b) {
            this.le.h(b);
            return this;
        }
    }
}
