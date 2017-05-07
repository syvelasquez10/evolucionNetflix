// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.signin.internal;

import com.google.android.gms.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.ConnectionResult;
import android.os.IInterface;

public interface zze extends IInterface
{
    void zza(final ConnectionResult p0, final AuthAccountResult p1);
    
    void zza(final Status p0, final GoogleSignInAccount p1);
    
    void zzbe(final Status p0);
    
    void zzbf(final Status p0);
}
