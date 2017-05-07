// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.dynamic.e;
import com.google.android.gms.maps.internal.u;
import android.content.Context;
import com.google.android.gms.dynamic.f;
import com.google.android.gms.dynamic.a;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.app.Activity;
import com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate;
import com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate;
import android.os.RemoteException;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import android.os.Parcelable;
import android.os.Bundle;
import android.app.Fragment;

public class StreetViewPanoramaFragment extends Fragment
{
    private final StreetViewPanoramaFragment$b aiV;
    private StreetViewPanorama aiW;
    
    public StreetViewPanoramaFragment() {
        this.aiV = new StreetViewPanoramaFragment$b(this);
    }
    
    public static StreetViewPanoramaFragment newInstance() {
        return new StreetViewPanoramaFragment();
    }
    
    public static StreetViewPanoramaFragment newInstance(final StreetViewPanoramaOptions streetViewPanoramaOptions) {
        final StreetViewPanoramaFragment streetViewPanoramaFragment = new StreetViewPanoramaFragment();
        final Bundle arguments = new Bundle();
        arguments.putParcelable("StreetViewPanoramaOptions", (Parcelable)streetViewPanoramaOptions);
        streetViewPanoramaFragment.setArguments(arguments);
        return streetViewPanoramaFragment;
    }
    
    public final StreetViewPanorama getStreetViewPanorama() {
        final IStreetViewPanoramaFragmentDelegate mb = this.mB();
        if (mb != null) {
            try {
                final IStreetViewPanoramaDelegate streetViewPanorama = mb.getStreetViewPanorama();
                if (streetViewPanorama != null) {
                    if (this.aiW == null || this.aiW.mA().asBinder() != streetViewPanorama.asBinder()) {
                        this.aiW = new StreetViewPanorama(streetViewPanorama);
                    }
                    return this.aiW;
                }
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
        return null;
    }
    
    protected IStreetViewPanoramaFragmentDelegate mB() {
        this.aiV.my();
        if (this.aiV.it() == null) {
            return null;
        }
        return this.aiV.it().mB();
    }
    
    public void onActivityCreated(final Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(StreetViewPanoramaFragment.class.getClassLoader());
        }
        super.onActivityCreated(bundle);
    }
    
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        this.aiV.setActivity(activity);
    }
    
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.aiV.onCreate(bundle);
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        return this.aiV.onCreateView(layoutInflater, viewGroup, bundle);
    }
    
    public void onDestroy() {
        this.aiV.onDestroy();
        super.onDestroy();
    }
    
    public void onDestroyView() {
        this.aiV.onDestroyView();
        super.onDestroyView();
    }
    
    public void onInflate(final Activity activity, final AttributeSet set, final Bundle bundle) {
        super.onInflate(activity, set, bundle);
        this.aiV.setActivity(activity);
        this.aiV.onInflate(activity, new Bundle(), bundle);
    }
    
    public void onLowMemory() {
        this.aiV.onLowMemory();
        super.onLowMemory();
    }
    
    public void onPause() {
        this.aiV.onPause();
        super.onPause();
    }
    
    public void onResume() {
        super.onResume();
        this.aiV.onResume();
    }
    
    public void onSaveInstanceState(final Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(StreetViewPanoramaFragment.class.getClassLoader());
        }
        super.onSaveInstanceState(bundle);
        this.aiV.onSaveInstanceState(bundle);
    }
    
    public void setArguments(final Bundle arguments) {
        super.setArguments(arguments);
    }
}
