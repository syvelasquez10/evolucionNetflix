// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.signin.internal;

import android.content.Intent;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import android.os.IInterface;

public interface zzd extends IInterface
{
    void zza(final GoogleSignInAccount p0, final Status p1);
    
    void zza(final Status p0, final Intent p1);
    
    void zzk(final Status p0);
    
    void zzl(final Status p0);
    
    void zzm(final Status p0);
}
