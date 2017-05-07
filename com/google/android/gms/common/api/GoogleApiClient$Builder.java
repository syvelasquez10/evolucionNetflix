// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import android.os.Handler;
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
    
    public GoogleApiClient$Builder(final Context context, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        this(context);
        n.b(googleApiClient$ConnectionCallbacks, "Must provide a connected listener");
        this.IM.add(googleApiClient$ConnectionCallbacks);
        n.b(googleApiClient$OnConnectionFailedListener, "Must provide a connection failed listener");
        this.IN.add(googleApiClient$OnConnectionFailedListener);
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
    
    public GoogleApiClient$Builder addApi(final Api<? extends Api$ApiOptions$NotRequiredOptions> api) {
        this.II.put(api, null);
        final List<Scope> ge = api.ge();
        for (int size = ge.size(), i = 0; i < size; ++i) {
            this.IE.add(ge.get(i).gt());
        }
        return this;
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
    
    public GoogleApiClient$Builder addScope(final Scope scope) {
        this.IE.add(scope.gt());
        return this;
    }
    
    public GoogleApiClient build() {
        n.b(!this.II.isEmpty(), (Object)"must call addApi() to add at least one API");
        if (this.IK >= 0) {
            return this.gm();
        }
        return new b(this.mContext, this.IB, this.gl(), this.II, this.IM, this.IN, -1);
    }
    
    public GoogleApiClient$Builder enableAutoManage(final FragmentActivity fragmentActivity, final int ik, final GoogleApiClient$OnConnectionFailedListener il) {
        n.b(ik >= 0, (Object)"clientId must be non-negative");
        this.IK = ik;
        this.IJ = n.b(fragmentActivity, "Null activity is not permitted.");
        this.IL = il;
        return this;
    }
    
    public ClientSettings gl() {
        return new ClientSettings(this.Dd, this.IE, this.IF, this.IG, this.IH);
    }
    
    public GoogleApiClient$Builder setAccountName(final String dd) {
        this.Dd = dd;
        return this;
    }
    
    public GoogleApiClient$Builder setGravityForPopups(final int if1) {
        this.IF = if1;
        return this;
    }
    
    public GoogleApiClient$Builder setHandler(final Handler handler) {
        n.b(handler, "Handler must not be null");
        this.IB = handler.getLooper();
        return this;
    }
    
    public GoogleApiClient$Builder setViewForPopups(final View ig) {
        this.IG = ig;
        return this;
    }
    
    public GoogleApiClient$Builder useDefaultAccount() {
        return this.setAccountName("<<default account>>");
    }
}
