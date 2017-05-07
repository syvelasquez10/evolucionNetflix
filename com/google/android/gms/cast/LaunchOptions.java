// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import android.os.Parcel;
import com.google.android.gms.common.internal.m;
import com.google.android.gms.internal.ik;
import java.util.Locale;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class LaunchOptions implements SafeParcelable
{
    public static final Parcelable$Creator<LaunchOptions> CREATOR;
    private final int BR;
    private boolean Fb;
    private String Fc;
    
    static {
        CREATOR = (Parcelable$Creator)new c();
    }
    
    public LaunchOptions() {
        this(1, false, ik.b(Locale.getDefault()));
    }
    
    LaunchOptions(final int br, final boolean fb, final String fc) {
        this.BR = br;
        this.Fb = fb;
        this.Fc = fc;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof LaunchOptions)) {
                return false;
            }
            final LaunchOptions launchOptions = (LaunchOptions)o;
            if (this.Fb != launchOptions.Fb || !ik.a(this.Fc, launchOptions.Fc)) {
                return false;
            }
        }
        return true;
    }
    
    public String getLanguage() {
        return this.Fc;
    }
    
    public boolean getRelaunchIfRunning() {
        return this.Fb;
    }
    
    int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.Fb, this.Fc);
    }
    
    public void setLanguage(final String fc) {
        this.Fc = fc;
    }
    
    public void setRelaunchIfRunning(final boolean fb) {
        this.Fb = fb;
    }
    
    @Override
    public String toString() {
        return String.format("LaunchOptions(relaunchIfRunning=%b, language=%s)", this.Fb, this.Fc);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        c.a(this, parcel, n);
    }
    
    public static final class Builder
    {
        private LaunchOptions Fd;
        
        public Builder() {
            this.Fd = new LaunchOptions();
        }
        
        public LaunchOptions build() {
            return this.Fd;
        }
        
        public Builder setLocale(final Locale locale) {
            this.Fd.setLanguage(ik.b(locale));
            return this;
        }
        
        public Builder setRelaunchIfRunning(final boolean relaunchIfRunning) {
            this.Fd.setRelaunchIfRunning(relaunchIfRunning);
            return this;
        }
    }
}
