// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.net.URI;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

public final class zzw$zza extends HttpEntityEnclosingRequestBase
{
    public zzw$zza() {
    }
    
    public zzw$zza(final String s) {
        this.setURI(URI.create(s));
    }
    
    public String getMethod() {
        return "PATCH";
    }
}
