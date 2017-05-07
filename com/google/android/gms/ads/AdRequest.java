// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads;

import android.content.Context;
import com.google.android.gms.ads.mediation.NetworkExtras;
import android.location.Location;
import java.util.Set;
import java.util.Date;
import com.google.android.gms.internal.af;

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
    private final af dW;
    
    static {
        DEVICE_ID_EMULATOR = af.DEVICE_ID_EMULATOR;
    }
    
    private AdRequest(final Builder builder) {
        this.dW = new af(builder.dX);
    }
    
    public Date getBirthday() {
        return this.dW.getBirthday();
    }
    
    public int getGender() {
        return this.dW.getGender();
    }
    
    public Set<String> getKeywords() {
        return this.dW.getKeywords();
    }
    
    public Location getLocation() {
        return this.dW.getLocation();
    }
    
    public <T extends NetworkExtras> T getNetworkExtras(final Class<T> clazz) {
        return this.dW.getNetworkExtras(clazz);
    }
    
    public boolean isTestDevice(final Context context) {
        return this.dW.isTestDevice(context);
    }
    
    af v() {
        return this.dW;
    }
    
    public static final class Builder
    {
        private final af.a dX;
        
        public Builder() {
            this.dX = new af.a();
        }
        
        public Builder addKeyword(final String s) {
            this.dX.g(s);
            return this;
        }
        
        public Builder addNetworkExtras(final NetworkExtras networkExtras) {
            this.dX.a(networkExtras);
            return this;
        }
        
        public Builder addTestDevice(final String s) {
            this.dX.h(s);
            return this;
        }
        
        public AdRequest build() {
            return new AdRequest(this, null);
        }
        
        public Builder setBirthday(final Date date) {
            this.dX.a(date);
            return this;
        }
        
        public Builder setGender(final int n) {
            this.dX.d(n);
            return this;
        }
        
        public Builder setLocation(final Location location) {
            this.dX.a(location);
            return this;
        }
        
        public Builder tagForChildDirectedTreatment(final boolean b) {
            this.dX.e(b);
            return this;
        }
    }
}
