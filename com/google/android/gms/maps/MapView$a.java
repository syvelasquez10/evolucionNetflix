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
import com.google.android.gms.maps.internal.IMapViewDelegate;
import android.view.ViewGroup;
import com.google.android.gms.dynamic.LifecycleDelegate;

class MapView$a implements LifecycleDelegate
{
    private final ViewGroup aiK;
    private final IMapViewDelegate aiL;
    private View aiM;
    
    public MapView$a(final ViewGroup viewGroup, final IMapViewDelegate mapViewDelegate) {
        this.aiL = n.i(mapViewDelegate);
        this.aiK = n.i(viewGroup);
    }
    
    public IMapViewDelegate mz() {
        return this.aiL;
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        try {
            this.aiL.onCreate(bundle);
            this.aiM = e.f(this.aiL.getView());
            this.aiK.removeAllViews();
            this.aiK.addView(this.aiM);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        throw new UnsupportedOperationException("onCreateView not allowed on MapViewDelegate");
    }
    
    @Override
    public void onDestroy() {
        try {
            this.aiL.onDestroy();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public void onDestroyView() {
        throw new UnsupportedOperationException("onDestroyView not allowed on MapViewDelegate");
    }
    
    @Override
    public void onInflate(final Activity activity, final Bundle bundle, final Bundle bundle2) {
        throw new UnsupportedOperationException("onInflate not allowed on MapViewDelegate");
    }
    
    @Override
    public void onLowMemory() {
        try {
            this.aiL.onLowMemory();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public void onPause() {
        try {
            this.aiL.onPause();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public void onResume() {
        try {
            this.aiL.onResume();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public void onSaveInstanceState(final Bundle bundle) {
        try {
            this.aiL.onSaveInstanceState(bundle);
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
