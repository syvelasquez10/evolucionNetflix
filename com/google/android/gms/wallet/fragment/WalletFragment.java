// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet.fragment;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.dynamic.c;
import com.google.android.gms.internal.jh;
import com.google.android.gms.dynamic.f;
import android.util.DisplayMetrics;
import android.view.ViewGroup$LayoutParams;
import com.google.android.gms.R;
import android.widget.Button;
import android.widget.FrameLayout;
import android.view.View$OnClickListener;
import com.google.android.gms.dynamic.a;
import com.google.android.gms.dynamic.e;
import android.os.RemoteException;
import com.google.android.gms.internal.iz;
import com.google.android.gms.dynamic.LifecycleDelegate;
import com.google.android.gms.internal.ja;
import android.app.FragmentManager;
import com.google.android.gms.common.GooglePlayServicesUtil;
import android.util.AttributeSet;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.os.Parcelable;
import android.os.Bundle;
import com.google.android.gms.dynamic.b;
import com.google.android.gms.wallet.MaskedWalletRequest;
import android.app.Fragment;

public final class WalletFragment extends Fragment
{
    private final Fragment Hv;
    private WalletFragmentInitParams acA;
    private MaskedWalletRequest acB;
    private Boolean acC;
    private b acH;
    private final com.google.android.gms.dynamic.b acI;
    private final c acJ;
    private a acK;
    private WalletFragmentOptions acz;
    private boolean mCreated;
    
    public WalletFragment() {
        this.mCreated = false;
        this.acI = com.google.android.gms.dynamic.b.a(this);
        this.acJ = new c();
        this.acK = new a(this);
        this.Hv = this;
    }
    
    public static WalletFragment newInstance(final WalletFragmentOptions walletFragmentOptions) {
        final WalletFragment walletFragment = new WalletFragment();
        final Bundle arguments = new Bundle();
        arguments.putParcelable("extraWalletFragmentOptions", (Parcelable)walletFragmentOptions);
        walletFragment.Hv.setArguments(arguments);
        return walletFragment;
    }
    
    public int getState() {
        if (this.acH != null) {
            return this.acH.getState();
        }
        return 0;
    }
    
    public void initialize(final WalletFragmentInitParams acA) {
        if (this.acH != null) {
            this.acH.initialize(acA);
            this.acA = null;
        }
        else {
            if (this.acA != null) {
                Log.w("WalletFragment", "initialize(WalletFragmentInitParams) was called more than once. Ignoring.");
                return;
            }
            this.acA = acA;
            if (this.acB != null) {
                Log.w("WalletFragment", "updateMaskedWallet() was called before initialize()");
            }
        }
    }
    
    public void onActivityResult(final int n, final int n2, final Intent intent) {
        super.onActivityResult(n, n2, intent);
        if (this.acH != null) {
            this.acH.onActivityResult(n, n2, intent);
        }
    }
    
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            bundle.setClassLoader(WalletFragmentOptions.class.getClassLoader());
            final WalletFragmentInitParams acA = (WalletFragmentInitParams)bundle.getParcelable("walletFragmentInitParams");
            if (acA != null) {
                if (this.acA != null) {
                    Log.w("WalletFragment", "initialize(WalletFragmentInitParams) was called more than once.Ignoring.");
                }
                this.acA = acA;
            }
            if (this.acB == null) {
                this.acB = (MaskedWalletRequest)bundle.getParcelable("maskedWalletRequest");
            }
            if (bundle.containsKey("walletFragmentOptions")) {
                this.acz = (WalletFragmentOptions)bundle.getParcelable("walletFragmentOptions");
            }
            if (bundle.containsKey("enabled")) {
                this.acC = bundle.getBoolean("enabled");
            }
        }
        else if (this.Hv.getArguments() != null) {
            final WalletFragmentOptions acz = (WalletFragmentOptions)this.Hv.getArguments().getParcelable("extraWalletFragmentOptions");
            if (acz != null) {
                acz.I((Context)this.Hv.getActivity());
                this.acz = acz;
            }
        }
        this.mCreated = true;
        this.acJ.onCreate(bundle);
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        return this.acJ.onCreateView(layoutInflater, viewGroup, bundle);
    }
    
    public void onDestroy() {
        super.onDestroy();
        this.mCreated = false;
    }
    
    public void onInflate(final Activity activity, final AttributeSet set, final Bundle bundle) {
        super.onInflate(activity, set, bundle);
        if (this.acz == null) {
            this.acz = WalletFragmentOptions.a((Context)activity, set);
        }
        final Bundle bundle2 = new Bundle();
        bundle2.putParcelable("attrKeyWalletFragmentOptions", (Parcelable)this.acz);
        this.acJ.onInflate(activity, bundle2, bundle);
    }
    
    public void onPause() {
        super.onPause();
        this.acJ.onPause();
    }
    
    public void onResume() {
        super.onResume();
        this.acJ.onResume();
        final FragmentManager fragmentManager = this.Hv.getActivity().getFragmentManager();
        final Fragment fragmentByTag = fragmentManager.findFragmentByTag("GooglePlayServicesErrorDialog");
        if (fragmentByTag != null) {
            fragmentManager.beginTransaction().remove(fragmentByTag).commit();
            GooglePlayServicesUtil.showErrorDialogFragment(GooglePlayServicesUtil.isGooglePlayServicesAvailable((Context)this.Hv.getActivity()), this.Hv.getActivity(), -1);
        }
    }
    
    public void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.setClassLoader(WalletFragmentOptions.class.getClassLoader());
        this.acJ.onSaveInstanceState(bundle);
        if (this.acA != null) {
            bundle.putParcelable("walletFragmentInitParams", (Parcelable)this.acA);
            this.acA = null;
        }
        if (this.acB != null) {
            bundle.putParcelable("maskedWalletRequest", (Parcelable)this.acB);
            this.acB = null;
        }
        if (this.acz != null) {
            bundle.putParcelable("walletFragmentOptions", (Parcelable)this.acz);
            this.acz = null;
        }
        if (this.acC != null) {
            bundle.putBoolean("enabled", (boolean)this.acC);
            this.acC = null;
        }
    }
    
    public void onStart() {
        super.onStart();
        this.acJ.onStart();
    }
    
    public void onStop() {
        super.onStop();
        this.acJ.onStop();
    }
    
    public void setEnabled(final boolean b) {
        if (this.acH != null) {
            this.acH.setEnabled(b);
            this.acC = null;
            return;
        }
        this.acC = b;
    }
    
    public void setOnStateChangedListener(final OnStateChangedListener onStateChangedListener) {
        this.acK.a(onStateChangedListener);
    }
    
    public void updateMaskedWalletRequest(final MaskedWalletRequest acB) {
        if (this.acH != null) {
            this.acH.updateMaskedWalletRequest(acB);
            this.acB = null;
            return;
        }
        this.acB = acB;
    }
    
    public interface OnStateChangedListener
    {
        void onStateChanged(final WalletFragment p0, final int p1, final int p2, final Bundle p3);
    }
    
    static class a extends ja.a
    {
        private OnStateChangedListener acL;
        private final WalletFragment acM;
        
        a(final WalletFragment acM) {
            this.acM = acM;
        }
        
        public void a(final int n, final int n2, final Bundle bundle) {
            if (this.acL != null) {
                this.acL.onStateChanged(this.acM, n, n2, bundle);
            }
        }
        
        public void a(final OnStateChangedListener acL) {
            this.acL = acL;
        }
    }
    
    private static class b implements LifecycleDelegate
    {
        private final iz acF;
        
        private b(final iz acF) {
            this.acF = acF;
        }
        
        private int getState() {
            try {
                return this.acF.getState();
            }
            catch (RemoteException ex) {
                throw new RuntimeException((Throwable)ex);
            }
        }
        
        private void initialize(final WalletFragmentInitParams walletFragmentInitParams) {
            try {
                this.acF.initialize(walletFragmentInitParams);
            }
            catch (RemoteException ex) {
                throw new RuntimeException((Throwable)ex);
            }
        }
        
        private void onActivityResult(final int n, final int n2, final Intent intent) {
            try {
                this.acF.onActivityResult(n, n2, intent);
            }
            catch (RemoteException ex) {
                throw new RuntimeException((Throwable)ex);
            }
        }
        
        private void setEnabled(final boolean enabled) {
            try {
                this.acF.setEnabled(enabled);
            }
            catch (RemoteException ex) {
                throw new RuntimeException((Throwable)ex);
            }
        }
        
        private void updateMaskedWalletRequest(final MaskedWalletRequest maskedWalletRequest) {
            try {
                this.acF.updateMaskedWalletRequest(maskedWalletRequest);
            }
            catch (RemoteException ex) {
                throw new RuntimeException((Throwable)ex);
            }
        }
        
        @Override
        public void onCreate(final Bundle bundle) {
            try {
                this.acF.onCreate(bundle);
            }
            catch (RemoteException ex) {
                throw new RuntimeException((Throwable)ex);
            }
        }
        
        @Override
        public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
            try {
                return e.d(this.acF.onCreateView(e.h(layoutInflater), e.h(viewGroup), bundle));
            }
            catch (RemoteException ex) {
                throw new RuntimeException((Throwable)ex);
            }
        }
        
        @Override
        public void onDestroy() {
        }
        
        @Override
        public void onDestroyView() {
        }
        
        @Override
        public void onInflate(final Activity activity, final Bundle bundle, final Bundle bundle2) {
            final WalletFragmentOptions walletFragmentOptions = (WalletFragmentOptions)bundle.getParcelable("extraWalletFragmentOptions");
            try {
                this.acF.a(e.h(activity), walletFragmentOptions, bundle2);
            }
            catch (RemoteException ex) {
                throw new RuntimeException((Throwable)ex);
            }
        }
        
        @Override
        public void onLowMemory() {
        }
        
        @Override
        public void onPause() {
            try {
                this.acF.onPause();
            }
            catch (RemoteException ex) {
                throw new RuntimeException((Throwable)ex);
            }
        }
        
        @Override
        public void onResume() {
            try {
                this.acF.onResume();
            }
            catch (RemoteException ex) {
                throw new RuntimeException((Throwable)ex);
            }
        }
        
        @Override
        public void onSaveInstanceState(final Bundle bundle) {
            try {
                this.acF.onSaveInstanceState(bundle);
            }
            catch (RemoteException ex) {
                throw new RuntimeException((Throwable)ex);
            }
        }
        
        @Override
        public void onStart() {
            try {
                this.acF.onStart();
            }
            catch (RemoteException ex) {
                throw new RuntimeException((Throwable)ex);
            }
        }
        
        @Override
        public void onStop() {
            try {
                this.acF.onStop();
            }
            catch (RemoteException ex) {
                throw new RuntimeException((Throwable)ex);
            }
        }
    }
    
    private class c extends a<b> implements View$OnClickListener
    {
        @Override
        protected void a(final FrameLayout frameLayout) {
            final Button button = new Button((Context)WalletFragment.this.Hv.getActivity());
            button.setText(R.string.wallet_buy_button_place_holder);
            final int n = -1;
            int a = -2;
            int a2 = n;
            if (WalletFragment.this.acz != null) {
                final WalletFragmentStyle fragmentStyle = WalletFragment.this.acz.getFragmentStyle();
                a = a;
                a2 = n;
                if (fragmentStyle != null) {
                    final DisplayMetrics displayMetrics = WalletFragment.this.Hv.getResources().getDisplayMetrics();
                    a2 = fragmentStyle.a("buyButtonWidth", displayMetrics, -1);
                    a = fragmentStyle.a("buyButtonHeight", displayMetrics, -2);
                }
            }
            button.setLayoutParams(new ViewGroup$LayoutParams(a2, a));
            button.setOnClickListener((View$OnClickListener)this);
            frameLayout.addView((View)button);
        }
        
        @Override
        protected void a(final f<b> f) {
            final Activity activity = WalletFragment.this.Hv.getActivity();
            if (WalletFragment.this.acH != null || !WalletFragment.this.mCreated || activity == null) {
                return;
            }
            try {
                WalletFragment.this.acH = new b(jh.a(activity, WalletFragment.this.acI, WalletFragment.this.acz, WalletFragment.this.acK));
                WalletFragment.this.acz = null;
                f.a(WalletFragment.this.acH);
                if (WalletFragment.this.acA != null) {
                    WalletFragment.this.acH.initialize(WalletFragment.this.acA);
                    WalletFragment.this.acA = null;
                }
                if (WalletFragment.this.acB != null) {
                    WalletFragment.this.acH.updateMaskedWalletRequest(WalletFragment.this.acB);
                    WalletFragment.this.acB = null;
                }
                if (WalletFragment.this.acC != null) {
                    WalletFragment.this.acH.setEnabled(WalletFragment.this.acC);
                    WalletFragment.this.acC = null;
                }
            }
            catch (GooglePlayServicesNotAvailableException ex) {}
        }
        
        public void onClick(final View view) {
            final Activity activity = WalletFragment.this.Hv.getActivity();
            GooglePlayServicesUtil.showErrorDialogFragment(GooglePlayServicesUtil.isGooglePlayServicesAvailable((Context)activity), activity, -1);
        }
    }
}
