// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive;

import java.util.List;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.internal.ClientSettings;
import android.os.Looper;
import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.drive.internal.q;
import com.google.android.gms.common.api.Api$b;
import com.google.android.gms.common.api.Api$ApiOptions;

public abstract class Drive$a<O extends Api$ApiOptions> implements Api$b<q, O>
{
    protected abstract Bundle a(final O p0);
    
    @Override
    public q a(final Context context, final Looper looper, final ClientSettings clientSettings, final O o, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        final List<String> scopes = clientSettings.getScopes();
        return new q(context, looper, clientSettings, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener, scopes.toArray(new String[scopes.size()]), this.a(o));
    }
    
    @Override
    public int getPriority() {
        return Integer.MAX_VALUE;
    }
}
