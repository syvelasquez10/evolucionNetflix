// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api;

import android.os.Parcelable;
import android.os.Bundle;
import com.google.android.gms.auth.api.credentials.PasswordSpecification;
import com.google.android.gms.common.api.Api$ApiOptions$Optional;

public final class Auth$AuthCredentialsOptions implements Api$ApiOptions$Optional
{
    private final String zzRY;
    private final PasswordSpecification zzRZ;
    
    public Bundle zzly() {
        final Bundle bundle = new Bundle();
        bundle.putString("consumer_package", this.zzRY);
        bundle.putParcelable("password_specification", (Parcelable)this.zzRZ);
        return bundle;
    }
}
