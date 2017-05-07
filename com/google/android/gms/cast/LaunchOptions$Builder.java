// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import com.google.android.gms.internal.ik;
import java.util.Locale;

public final class LaunchOptions$Builder
{
    private LaunchOptions Fd;
    
    public LaunchOptions$Builder() {
        this.Fd = new LaunchOptions();
    }
    
    public LaunchOptions build() {
        return this.Fd;
    }
    
    public LaunchOptions$Builder setLocale(final Locale locale) {
        this.Fd.setLanguage(ik.b(locale));
        return this;
    }
    
    public LaunchOptions$Builder setRelaunchIfRunning(final boolean relaunchIfRunning) {
        this.Fd.setRelaunchIfRunning(relaunchIfRunning);
        return this;
    }
}
