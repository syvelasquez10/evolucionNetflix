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
import android.support.v4.app.Fragment;

public class SupportStreetViewPanoramaFragment extends Fragment
{
    private StreetViewPanorama aiW;
    private final SupportStreetViewPanoramaFragment$b ajk;
    
    public SupportStreetViewPanoramaFragment() {
        this.ajk = new SupportStreetViewPanoramaFragment$b(this);
    }
    
    public static SupportStreetViewPanoramaFragment newInstance() {
        return new SupportStreetViewPanoramaFragment();
    }
    
    public static SupportStreetViewPanoramaFragment newInstance(final StreetViewPanoramaOptions streetViewPanoramaOptions) {
        final SupportStreetViewPanoramaFragment supportStreetViewPanoramaFragment = new SupportStreetViewPanoramaFragment();
        final Bundle arguments = new Bundle();
        arguments.putParcelable("StreetViewPanoramaOptions", (Parcelable)streetViewPanoramaOptions);
        supportStreetViewPanoramaFragment.setArguments(arguments);
        return supportStreetViewPanoramaFragment;
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
        this.ajk.my();
        if (this.ajk.it() == null) {
            return null;
        }
        return this.ajk.it().mB();
    }
    
    @Override
    public void onActivityCreated(final Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(SupportStreetViewPanoramaFragment.class.getClassLoader());
        }
        super.onActivityCreated(bundle);
    }
    
    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        this.ajk.setActivity(activity);
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.ajk.onCreate(bundle);
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        return this.ajk.onCreateView(layoutInflater, viewGroup, bundle);
    }
    
    @Override
    public void onDestroy() {
        this.ajk.onDestroy();
        super.onDestroy();
    }
    
    @Override
    public void onDestroyView() {
        this.ajk.onDestroyView();
        super.onDestroyView();
    }
    
    @Override
    public void onInflate(final Activity activity, final AttributeSet set, final Bundle bundle) {
        super.onInflate(activity, set, bundle);
        this.ajk.setActivity(activity);
        this.ajk.onInflate(activity, new Bundle(), bundle);
    }
    
    @Override
    public void onLowMemory() {
        this.ajk.onLowMemory();
        super.onLowMemory();
    }
    
    @Override
    public void onPause() {
        this.ajk.onPause();
        super.onPause();
    }
    
    @Override
    public void onResume() {
        super.onResume();
        this.ajk.onResume();
    }
    
    @Override
    public void onSaveInstanceState(final Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(SupportStreetViewPanoramaFragment.class.getClassLoader());
        }
        super.onSaveInstanceState(bundle);
        this.ajk.onSaveInstanceState(bundle);
    }
    
    @Override
    public void setArguments(final Bundle arguments) {
        super.setArguments(arguments);
    }
}
