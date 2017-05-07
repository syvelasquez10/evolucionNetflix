// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import java.util.Collection;
import com.google.android.gms.common.internal.ClientSettings;
import java.util.List;
import com.google.android.gms.common.internal.n;
import java.util.HashMap;
import java.util.HashSet;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import java.util.Map;
import android.view.View;
import java.util.Set;
import android.os.Looper;

public final class GoogleApiClient$Builder
{
    private String Dd;
    private Looper IB;
    private final Set<String> IE;
    private int IF;
    private View IG;
    private String IH;
    private final Map<Api<?>, Api$ApiOptions> II;
    private FragmentActivity IJ;
    private int IK;
    private GoogleApiClient$OnConnectionFailedListener IL;
    private final Set<GoogleApiClient$ConnectionCallbacks> IM;
    private final Set<GoogleApiClient$OnConnectionFailedListener> IN;
    private final Context mContext;
    
    public GoogleApiClient$Builder(final Context mContext) {
        this.IE = new HashSet<String>();
        this.II = new HashMap<Api<?>, Api$ApiOptions>();
        this.IK = -1;
        this.IM = new HashSet<GoogleApiClient$ConnectionCallbacks>();
        this.IN = new HashSet<GoogleApiClient$OnConnectionFailedListener>();
        this.mContext = mContext;
        this.IB = mContext.getMainLooper();
        this.IH = mContext.getPackageName();
    }
    
    private GoogleApiClient gm() {
        final d a = d.a(this.IJ);
        GoogleApiClient ak;
        if ((ak = a.ak(this.IK)) == null) {
            ak = new b(this.mContext.getApplicationContext(), this.IB, this.gl(), this.II, this.IM, this.IN, this.IK);
        }
        a.a(this.IK, ak, this.IL);
        return ak;
    }
    
    public <O extends Api$ApiOptions$HasOptions> GoogleApiClient$Builder addApi(final Api<O> api, final O o) {
        n.b(o, "Null options are not permitted for this Api");
        this.II.put((Api<?>)api, (Object)o);
        final List<Scope> ge = api.ge();
        for (int size = ge.size(), i = 0; i < size; ++i) {
            this.IE.add(ge.get(i).gt());
        }
        return this;
    }
    
    public GoogleApiClient$Builder addConnectionCallbacks(final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks) {
        this.IM.add(googleApiClient$ConnectionCallbacks);
        return this;
    }
    
    public GoogleApiClient$Builder addOnConnectionFailedListener(final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        this.IN.add(googleApiClient$OnConnectionFailedListener);
        return this;
    }
    
    public GoogleApiClient build() {
        n.b(!this.II.isEmpty(), "must call addApi() to add at least one API");
        if (this.IK >= 0) {
            return this.gm();
        }
        return new b(this.mContext, this.IB, this.gl(), this.II, this.IM, this.IN, -1);
    }
    
    public ClientSettings gl() {
        return new ClientSettings(this.Dd, this.IE, this.IF, this.IG, this.IH);
    }
}
