// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.security;

import android.content.Intent;

public interface ProviderInstaller$ProviderInstallListener
{
    void onProviderInstallFailed(final int p0, final Intent p1);
    
    void onProviderInstalled();
}
