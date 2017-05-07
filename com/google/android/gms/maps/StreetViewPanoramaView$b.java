// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps;

import android.app.Activity;
import android.view.LayoutInflater;
import android.os.RemoteException;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.dynamic.e;
import android.os.Bundle;
import com.google.android.gms.common.internal.n;
import android.view.View;
import com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate;
import android.view.ViewGroup;
import com.google.android.gms.dynamic.LifecycleDelegate;

class StreetViewPanoramaView$b implements LifecycleDelegate
{
    private final ViewGroup aiK;
    private final IStreetViewPanoramaViewDelegate ajh;
    private View aji;
    
    public StreetViewPanoramaView$b(final ViewGroup viewGroup, final IStreetViewPanoramaViewDelegate streetViewPanoramaViewDelegate) {
        this.ajh = n.i(streetViewPanoramaViewDelegate);
        this.aiK = n.i(viewGroup);
    }
    
    public IStreetViewPanoramaViewDelegate mF() {
        return this.ajh;
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        try {
            this.ajh.onCreate(bundle);
            this.aji = e.f(this.ajh.getView());
            this.aiK.removeAllViews();
            this.aiK.addView(this.aji);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        throw new UnsupportedOperationException("onCreateView not allowed on StreetViewPanoramaViewDelegate");
    }
    
    @Override
    public void onDestroy() {
        try {
            this.ajh.onDestroy();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public void onDestroyView() {
        throw new UnsupportedOperationException("onDestroyView not allowed on StreetViewPanoramaViewDelegate");
    }
    
    @Override
    public void onInflate(final Activity activity, final Bundle bundle, final Bundle bundle2) {
        throw new UnsupportedOperationException("onInflate not allowed on StreetViewPanoramaViewDelegate");
    }
    
    @Override
    public void onLowMemory() {
        try {
            this.ajh.onLowMemory();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public void onPause() {
        try {
            this.ajh.onPause();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public void onResume() {
        try {
            this.ajh.onResume();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public void onSaveInstanceState(final Bundle bundle) {
        try {
            this.ajh.onSaveInstanceState(bundle);
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
