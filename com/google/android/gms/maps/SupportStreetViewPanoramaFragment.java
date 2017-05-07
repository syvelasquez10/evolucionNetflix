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
import android.support.v4.app.Fragment;

public class SupportStreetViewPanoramaFragment extends Fragment
{
    private StreetViewPanorama aiW;
    private final b ajk;
    
    public SupportStreetViewPanoramaFragment() {
        this.ajk = new b(this);
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
    
    static class a implements LifecycleDelegate
    {
        private final Fragment Ll;
        private final IStreetViewPanoramaFragmentDelegate aiX;
        
        public a(final Fragment fragment, final IStreetViewPanoramaFragmentDelegate streetViewPanoramaFragmentDelegate) {
            this.aiX = n.i(streetViewPanoramaFragmentDelegate);
            this.Ll = n.i(fragment);
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
                    arguments = this.Ll.getArguments();
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
    
    static class b extends a<SupportStreetViewPanoramaFragment.a>
    {
        private final Fragment Ll;
        protected f<SupportStreetViewPanoramaFragment.a> aiI;
        private Activity nr;
        
        b(final Fragment ll) {
            this.Ll = ll;
        }
        
        private void setActivity(final Activity nr) {
            this.nr = nr;
            this.my();
        }
        
        @Override
        protected void a(final f<SupportStreetViewPanoramaFragment.a> aiI) {
            this.aiI = aiI;
            this.my();
        }
        
        public void my() {
            if (this.nr == null || this.aiI == null || this.it() != null) {
                return;
            }
            try {
                MapsInitializer.initialize((Context)this.nr);
                this.aiI.a(new SupportStreetViewPanoramaFragment.a(this.Ll, u.R((Context)this.nr).k(e.k(this.nr))));
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
            catch (GooglePlayServicesNotAvailableException ex2) {}
        }
    }
}
