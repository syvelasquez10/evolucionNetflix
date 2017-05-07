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
import com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate;
import android.app.Fragment;
import com.google.android.gms.dynamic.LifecycleDelegate;

class StreetViewPanoramaFragment$a implements LifecycleDelegate
{
    private final Fragment Sb;
    private final IStreetViewPanoramaFragmentDelegate aiX;
    
    public StreetViewPanoramaFragment$a(final Fragment fragment, final IStreetViewPanoramaFragmentDelegate streetViewPanoramaFragmentDelegate) {
        this.aiX = n.i(streetViewPanoramaFragmentDelegate);
        this.Sb = n.i(fragment);
    }
    
    public IStreetViewPanoramaFragmentDelegate mB() {
        return this.aiX;
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
                arguments = this.Sb.getArguments();
                if (arguments != null && arguments.containsKey("StreetViewPanoramaOptions")) {
                    t.a(bundle, "StreetViewPanoramaOptions", arguments.getParcelable("StreetViewPanoramaOptions"));
                }
                this.aiX.onCreate(bundle);
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        try {
            return e.f(this.aiX.onCreateView(e.k(layoutInflater), e.k(viewGroup), bundle));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public void onDestroy() {
        try {
            this.aiX.onDestroy();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public void onDestroyView() {
        try {
            this.aiX.onDestroyView();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public void onInflate(final Activity activity, final Bundle bundle, final Bundle bundle2) {
        try {
            this.aiX.onInflate(e.k(activity), null, bundle2);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public void onLowMemory() {
        try {
            this.aiX.onLowMemory();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public void onPause() {
        try {
            this.aiX.onPause();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public void onResume() {
        try {
            this.aiX.onResume();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public void onSaveInstanceState(final Bundle bundle) {
        try {
            this.aiX.onSaveInstanceState(bundle);
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
