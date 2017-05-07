// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet.fragment;

import android.support.v4.app.FragmentActivity;
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
import android.support.v4.app.FragmentManager;
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
import com.google.android.gms.dynamic.h;
import com.google.android.gms.wallet.MaskedWalletRequest;
import android.support.v4.app.Fragment;

public final class SupportWalletFragment extends Fragment
{
    private final Fragment Hz;
    private WalletFragmentInitParams acA;
    private MaskedWalletRequest acB;
    private Boolean acC;
    private b acv;
    private final h acw;
    private final c acx;
    private a acy;
    private WalletFragmentOptions acz;
    private boolean mCreated;
    
    public SupportWalletFragment() {
        this.mCreated = false;
        this.acw = h.a(this);
        this.acx = new c();
        this.acy = new a(this);
        this.Hz = this;
    }
    
    public static SupportWalletFragment newInstance(final WalletFragmentOptions walletFragmentOptions) {
        final SupportWalletFragment supportWalletFragment = new SupportWalletFragment();
        final Bundle arguments = new Bundle();
        arguments.putParcelable("extraWalletFragmentOptions", (Parcelable)walletFragmentOptions);
        supportWalletFragment.Hz.setArguments(arguments);
        return supportWalletFragment;
    }
    
    public int getState() {
        if (this.acv != null) {
            return this.acv.getState();
        }
        return 0;
    }
    
    public void initialize(final WalletFragmentInitParams acA) {
        if (this.acv != null) {
            this.acv.initialize(acA);
            this.acA = null;
        }
        else {
            if (this.acA != null) {
                Log.w("SupportWalletFragment", "initialize(WalletFragmentInitParams) was called more than once. Ignoring.");
                return;
            }
            this.acA = acA;
            if (this.acB != null) {
                Log.w("SupportWalletFragment", "updateMaskedWallet() was called before initialize()");
            }
        }
    }
    
    @Override
    public void onActivityResult(final int n, final int n2, final Intent intent) {
        super.onActivityResult(n, n2, intent);
        if (this.acv != null) {
            this.acv.onActivityResult(n, n2, intent);
        }
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            bundle.setClassLoader(WalletFragmentOptions.class.getClassLoader());
            final WalletFragmentInitParams acA = (WalletFragmentInitParams)bundle.getParcelable("walletFragmentInitParams");
            if (acA != null) {
                if (this.acA != null) {
                    Log.w("SupportWalletFragment", "initialize(WalletFragmentInitParams) was called more than once.Ignoring.");
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
        else if (this.Hz.getArguments() != null) {
            final WalletFragmentOptions acz = (WalletFragmentOptions)this.Hz.getArguments().getParcelable("extraWalletFragmentOptions");
            if (acz != null) {
                acz.I((Context)this.Hz.getActivity());
                this.acz = acz;
            }
        }
        this.mCreated = true;
        this.acx.onCreate(bundle);
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        return this.acx.onCreateView(layoutInflater, viewGroup, bundle);
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mCreated = false;
    }
    
    @Override
    public void onInflate(final Activity activity, final AttributeSet set, final Bundle bundle) {
        super.onInflate(activity, set, bundle);
        if (this.acz == null) {
            this.acz = WalletFragmentOptions.a((Context)activity, set);
        }
        final Bundle bundle2 = new Bundle();
        bundle2.putParcelable("attrKeyWalletFragmentOptions", (Parcelable)this.acz);
        this.acx.onInflate(activity, bundle2, bundle);
    }
    
    @Override
    public void onPause() {
        super.onPause();
        this.acx.onPause();
    }
    
    @Override
    public void onResume() {
        super.onResume();
        this.acx.onResume();
        final FragmentManager supportFragmentManager = this.Hz.getActivity().getSupportFragmentManager();
        final Fragment fragmentByTag = supportFragmentManager.findFragmentByTag("GooglePlayServicesErrorDialog");
        if (fragmentByTag != null) {
            supportFragmentManager.beginTransaction().remove(fragmentByTag).commit();
            GooglePlayServicesUtil.showErrorDialogFragment(GooglePlayServicesUtil.isGooglePlayServicesAvailable((Context)this.Hz.getActivity()), this.Hz.getActivity(), -1);
        }
    }
    
    @Override
    public void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.setClassLoader(WalletFragmentOptions.class.getClassLoader());
        this.acx.onSaveInstanceState(bundle);
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
    
    @Override
    public void onStart() {
        super.onStart();
        this.acx.onStart();
    }
    
    @Override
    public void onStop() {
        super.onStop();
        this.acx.onStop();
    }
    
    public void setEnabled(final boolean b) {
        if (this.acv != null) {
            this.acv.setEnabled(b);
            this.acC = null;
            return;
        }
        this.acC = b;
    }
    
    public void setOnStateChangedListener(final OnStateChangedListener onStateChangedListener) {
        this.acy.a(onStateChangedListener);
    }
    
    public void updateMaskedWalletRequest(final MaskedWalletRequest acB) {
        if (this.acv != null) {
            this.acv.updateMaskedWalletRequest(acB);
            this.acB = null;
            return;
        }
        this.acB = acB;
    }
    
    public interface OnStateChangedListener
    {
        void onStateChanged(final SupportWalletFragment p0, final int p1, final int p2, final Bundle p3);
    }
    
    static class a extends ja.a
    {
        private OnStateChangedListener acD;
        private final SupportWalletFragment acE;
        
        a(final SupportWalletFragment acE) {
            this.acE = acE;
        }
        
        public void a(final int n, final int n2, final Bundle bundle) {
            if (this.acD != null) {
                this.acD.onStateChanged(this.acE, n, n2, bundle);
            }
        }
        
        public void a(final OnStateChangedListener acD) {
            this.acD = acD;
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
            final Button button = new Button((Context)SupportWalletFragment.this.Hz.getActivity());
            button.setText(R.string.wallet_buy_button_place_holder);
            final int n = -1;
            int a = -2;
            int a2 = n;
            if (SupportWalletFragment.this.acz != null) {
                final WalletFragmentStyle fragmentStyle = SupportWalletFragment.this.acz.getFragmentStyle();
                a = a;
                a2 = n;
                if (fragmentStyle != null) {
                    final DisplayMetrics displayMetrics = SupportWalletFragment.this.Hz.getResources().getDisplayMetrics();
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
            final FragmentActivity activity = SupportWalletFragment.this.Hz.getActivity();
            if (SupportWalletFragment.this.acv != null || !SupportWalletFragment.this.mCreated || activity == null) {
                return;
            }
            try {
                SupportWalletFragment.this.acv = new b(jh.a(activity, SupportWalletFragment.this.acw, SupportWalletFragment.this.acz, SupportWalletFragment.this.acy));
                SupportWalletFragment.this.acz = null;
                f.a(SupportWalletFragment.this.acv);
                if (SupportWalletFragment.this.acA != null) {
                    SupportWalletFragment.this.acv.initialize(SupportWalletFragment.this.acA);
                    SupportWalletFragment.this.acA = null;
                }
                if (SupportWalletFragment.this.acB != null) {
                    SupportWalletFragment.this.acv.updateMaskedWalletRequest(SupportWalletFragment.this.acB);
                    SupportWalletFragment.this.acB = null;
                }
                if (SupportWalletFragment.this.acC != null) {
                    SupportWalletFragment.this.acv.setEnabled(SupportWalletFragment.this.acC);
                    SupportWalletFragment.this.acC = null;
                }
            }
            catch (GooglePlayServicesNotAvailableException ex) {}
        }
        
        public void onClick(final View view) {
            final FragmentActivity activity = SupportWalletFragment.this.Hz.getActivity();
            GooglePlayServicesUtil.showErrorDialogFragment(GooglePlayServicesUtil.isGooglePlayServicesAvailable((Context)activity), activity, -1);
        }
    }
}
