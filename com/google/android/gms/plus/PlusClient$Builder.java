// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus;

import com.google.android.gms.plus.internal.e;
import android.content.Context;
import com.google.android.gms.plus.internal.i;
import com.google.android.gms.common.GooglePlayServicesClient$OnConnectionFailedListener;
import com.google.android.gms.common.GooglePlayServicesClient$ConnectionCallbacks;

@Deprecated
public class PlusClient$Builder
{
    private final GooglePlayServicesClient$ConnectionCallbacks akX;
    private final GooglePlayServicesClient$OnConnectionFailedListener akY;
    private final i akZ;
    private final Context mContext;
    
    public PlusClient$Builder(final Context mContext, final GooglePlayServicesClient$ConnectionCallbacks akX, final GooglePlayServicesClient$OnConnectionFailedListener akY) {
        this.mContext = mContext;
        this.akX = akX;
        this.akY = akY;
        this.akZ = new i(this.mContext);
    }
    
    public PlusClient build() {
        return new PlusClient(new e(this.mContext, this.akX, this.akY, this.akZ.no()));
    }
    
    public PlusClient$Builder clearScopes() {
        this.akZ.nn();
        return this;
    }
    
    public PlusClient$Builder setAccountName(final String s) {
        this.akZ.ce(s);
        return this;
    }
    
    public PlusClient$Builder setActions(final String... array) {
        this.akZ.h(array);
        return this;
    }
    
    public PlusClient$Builder setScopes(final String... array) {
        this.akZ.g(array);
        return this;
    }
}
