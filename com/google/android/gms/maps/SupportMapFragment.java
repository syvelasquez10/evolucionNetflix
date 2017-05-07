// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.dynamic.e;
import com.google.android.gms.maps.internal.u;
import com.google.android.gms.dynamic.f;
import com.google.android.gms.dynamic.a;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.app.Activity;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.internal.IMapFragmentDelegate;
import android.os.RemoteException;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import android.os.Parcelable;
import android.os.Bundle;
import android.support.v4.app.Fragment;

public class SupportMapFragment extends Fragment
{
    private GoogleMap aiG;
    private final SupportMapFragment$b ajj;
    
    public SupportMapFragment() {
        this.ajj = new SupportMapFragment$b(this);
    }
    
    public static SupportMapFragment newInstance() {
        return new SupportMapFragment();
    }
    
    public static SupportMapFragment newInstance(final GoogleMapOptions googleMapOptions) {
        final SupportMapFragment supportMapFragment = new SupportMapFragment();
        final Bundle arguments = new Bundle();
        arguments.putParcelable("MapOptions", (Parcelable)googleMapOptions);
        supportMapFragment.setArguments(arguments);
        return supportMapFragment;
    }
    
    public final GoogleMap getMap() {
        final IMapFragmentDelegate mx = this.mx();
        if (mx != null) {
            try {
                final IGoogleMapDelegate map = mx.getMap();
                if (map != null) {
                    if (this.aiG == null || this.aiG.mo().asBinder() != map.asBinder()) {
                        this.aiG = new GoogleMap(map);
                    }
                    return this.aiG;
                }
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
        return null;
    }
    
    protected IMapFragmentDelegate mx() {
        this.ajj.my();
        if (this.ajj.it() == null) {
            return null;
        }
        return this.ajj.it().mx();
    }
    
    @Override
    public void onActivityCreated(final Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(SupportMapFragment.class.getClassLoader());
        }
        super.onActivityCreated(bundle);
    }
    
    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        this.ajj.setActivity(activity);
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.ajj.onCreate(bundle);
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        return this.ajj.onCreateView(layoutInflater, viewGroup, bundle);
    }
    
    @Override
    public void onDestroy() {
        this.ajj.onDestroy();
        super.onDestroy();
    }
    
    @Override
    public void onDestroyView() {
        this.ajj.onDestroyView();
        super.onDestroyView();
    }
    
    @Override
    public void onInflate(final Activity activity, final AttributeSet set, final Bundle bundle) {
        super.onInflate(activity, set, bundle);
        this.ajj.setActivity(activity);
        final GoogleMapOptions fromAttributes = GoogleMapOptions.createFromAttributes((Context)activity, set);
        final Bundle bundle2 = new Bundle();
        bundle2.putParcelable("MapOptions", (Parcelable)fromAttributes);
        this.ajj.onInflate(activity, bundle2, bundle);
    }
    
    @Override
    public void onLowMemory() {
        this.ajj.onLowMemory();
        super.onLowMemory();
    }
    
    @Override
    public void onPause() {
        this.ajj.onPause();
        super.onPause();
    }
    
    @Override
    public void onResume() {
        super.onResume();
        this.ajj.onResume();
    }
    
    @Override
    public void onSaveInstanceState(final Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(SupportMapFragment.class.getClassLoader());
        }
        super.onSaveInstanceState(bundle);
        this.ajj.onSaveInstanceState(bundle);
    }
    
    @Override
    public void setArguments(final Bundle arguments) {
        super.setArguments(arguments);
    }
}
