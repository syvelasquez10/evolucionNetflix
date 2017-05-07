// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.internal.u;
import android.content.Context;
import com.google.android.gms.dynamic.f;
import com.google.android.gms.dynamic.a;
import com.google.android.gms.dynamic.e;
import com.google.android.gms.maps.internal.t;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.dynamic.LifecycleDelegate;
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
    private final b aiV;
    private StreetViewPanorama aiW;
    
    public StreetViewPanoramaFragment() {
        this.aiV = new b(this);
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
    
    static class a implements LifecycleDelegate
    {
        private final Fragment Sb;
        private final IStreetViewPanoramaFragmentDelegate aiX;
        
        public a(final Fragment fragment, final IStreetViewPanoramaFragmentDelegate streetViewPanoramaFragmentDelegate) {
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
    
    static class b extends a<StreetViewPanoramaFragment.a>
    {
        private final Fragment Sb;
        protected f<StreetViewPanoramaFragment.a> aiI;
        private Activity nr;
        
        b(final Fragment sb) {
            this.Sb = sb;
        }
        
        private void setActivity(final Activity nr) {
            this.nr = nr;
            this.my();
        }
        
        @Override
        protected void a(final f<StreetViewPanoramaFragment.a> aiI) {
            this.aiI = aiI;
            this.my();
        }
        
        public void my() {
            if (this.nr == null || this.aiI == null || this.it() != null) {
                return;
            }
            try {
                MapsInitializer.initialize((Context)this.nr);
                this.aiI.a(new StreetViewPanoramaFragment.a(this.Sb, u.R((Context)this.nr).k(e.k(this.nr))));
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
            catch (GooglePlayServicesNotAvailableException ex2) {}
        }
    }
}
