// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps;

import android.app.Activity;
import com.google.android.gms.dynamic.e;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.os.RemoteException;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.maps.internal.t;
import android.os.Bundle;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.maps.internal.IMapFragmentDelegate;
import android.support.v4.app.Fragment;
import com.google.android.gms.dynamic.LifecycleDelegate;

class SupportMapFragment$a implements LifecycleDelegate
{
    private final Fragment Ll;
    private final IMapFragmentDelegate aiH;
    
    public SupportMapFragment$a(final Fragment fragment, final IMapFragmentDelegate mapFragmentDelegate) {
        this.aiH = n.i(mapFragmentDelegate);
        this.Ll = n.i(fragment);
    }
    
    public IMapFragmentDelegate mx() {
        return this.aiH;
    }
    
    @Override
    public void onCreate(Bundle arguments) {
        Bundle bundle = arguments;
        Label_0014: {
            if (arguments != null) {
                break Label_0014;
            }
            try {
                bundle = new Bundle();
                arguments = this.Ll.getArguments();
                if (arguments != null && arguments.containsKey("MapOptions")) {
                    t.a(bundle, "MapOptions", arguments.getParcelable("MapOptions"));
                }
                this.aiH.onCreate(bundle);
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        try {
            return e.f(this.aiH.onCreateView(e.k(layoutInflater), e.k(viewGroup), bundle));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public void onDestroy() {
        try {
            this.aiH.onDestroy();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public void onDestroyView() {
        try {
            this.aiH.onDestroyView();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public void onInflate(final Activity activity, final Bundle bundle, final Bundle bundle2) {
        final GoogleMapOptions googleMapOptions = (GoogleMapOptions)bundle.getParcelable("MapOptions");
        try {
            this.aiH.onInflate(e.k(activity), googleMapOptions, bundle2);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public void onLowMemory() {
        try {
            this.aiH.onLowMemory();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public void onPause() {
        try {
            this.aiH.onPause();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public void onResume() {
        try {
            this.aiH.onResume();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public void onSaveInstanceState(final Bundle bundle) {
        try {
            this.aiH.onSaveInstanceState(bundle);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public void onStart() {
    }
    
    @Override
    public void onStop() {
    }
}
