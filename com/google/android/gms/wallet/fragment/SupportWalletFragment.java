// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet.fragment;

import android.support.v4.app.FragmentActivity;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.dynamic.c;
import com.google.android.gms.internal.oy;
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
import com.google.android.gms.internal.oq;
import com.google.android.gms.dynamic.LifecycleDelegate;
import com.google.android.gms.internal.or;
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
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.dynamic.h;
import android.support.v4.app.Fragment;

public final class SupportWalletFragment extends Fragment
{
    private final Fragment Ll;
    private b atF;
    private final h atG;
    private final c atH;
    private a atI;
    private WalletFragmentOptions atJ;
    private WalletFragmentInitParams atK;
    private MaskedWalletRequest atL;
    private MaskedWallet atM;
    private Boolean atN;
    private boolean mCreated;
    
    public SupportWalletFragment() {
        this.mCreated = false;
        this.atG = h.a(this);
        this.atH = new c();
        this.atI = new a(this);
        this.Ll = this;
    }
    
    public static SupportWalletFragment newInstance(final WalletFragmentOptions walletFragmentOptions) {
        final SupportWalletFragment supportWalletFragment = new SupportWalletFragment();
        final Bundle arguments = new Bundle();
        arguments.putParcelable("extraWalletFragmentOptions", (Parcelable)walletFragmentOptions);
        supportWalletFragment.Ll.setArguments(arguments);
        return supportWalletFragment;
    }
    
    public int getState() {
        if (this.atF != null) {
            return this.atF.getState();
        }
        return 0;
    }
    
    public void initialize(final WalletFragmentInitParams atK) {
        if (this.atF != null) {
            this.atF.initialize(atK);
            this.atK = null;
        }
        else {
            if (this.atK != null) {
                Log.w("SupportWalletFragment", "initialize(WalletFragmentInitParams) was called more than once. Ignoring.");
                return;
            }
            this.atK = atK;
            if (this.atL != null) {
                Log.w("SupportWalletFragment", "updateMaskedWalletRequest() was called before initialize()");
            }
            if (this.atM != null) {
                Log.w("SupportWalletFragment", "updateMaskedWallet() was called before initialize()");
            }
        }
    }
    
    @Override
    public void onActivityResult(final int n, final int n2, final Intent intent) {
        super.onActivityResult(n, n2, intent);
        if (this.atF != null) {
            this.atF.onActivityResult(n, n2, intent);
        }
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            bundle.setClassLoader(WalletFragmentOptions.class.getClassLoader());
            final WalletFragmentInitParams atK = (WalletFragmentInitParams)bundle.getParcelable("walletFragmentInitParams");
            if (atK != null) {
                if (this.atK != null) {
                    Log.w("SupportWalletFragment", "initialize(WalletFragmentInitParams) was called more than once.Ignoring.");
                }
                this.atK = atK;
            }
            if (this.atL == null) {
                this.atL = (MaskedWalletRequest)bundle.getParcelable("maskedWalletRequest");
            }
            if (this.atM == null) {
                this.atM = (MaskedWallet)bundle.getParcelable("maskedWallet");
            }
            if (bundle.containsKey("walletFragmentOptions")) {
                this.atJ = (WalletFragmentOptions)bundle.getParcelable("walletFragmentOptions");
            }
            if (bundle.containsKey("enabled")) {
                this.atN = bundle.getBoolean("enabled");
            }
        }
        else if (this.Ll.getArguments() != null) {
            final WalletFragmentOptions atJ = (WalletFragmentOptions)this.Ll.getArguments().getParcelable("extraWalletFragmentOptions");
            if (atJ != null) {
                atJ.Z((Context)this.Ll.getActivity());
                this.atJ = atJ;
            }
        }
        this.mCreated = true;
        this.atH.onCreate(bundle);
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        return this.atH.onCreateView(layoutInflater, viewGroup, bundle);
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mCreated = false;
    }
    
    @Override
    public void onInflate(final Activity activity, final AttributeSet set, final Bundle bundle) {
        super.onInflate(activity, set, bundle);
        if (this.atJ == null) {
            this.atJ = WalletFragmentOptions.a((Context)activity, set);
        }
        final Bundle bundle2 = new Bundle();
        bundle2.putParcelable("attrKeyWalletFragmentOptions", (Parcelable)this.atJ);
        this.atH.onInflate(activity, bundle2, bundle);
    }
    
    @Override
    public void onPause() {
        super.onPause();
        this.atH.onPause();
    }
    
    @Override
    public void onResume() {
        super.onResume();
        this.atH.onResume();
        final FragmentManager supportFragmentManager = this.Ll.getActivity().getSupportFragmentManager();
        final Fragment fragmentByTag = supportFragmentManager.findFragmentByTag("GooglePlayServicesErrorDialog");
        if (fragmentByTag != null) {
            supportFragmentManager.beginTransaction().remove(fragmentByTag).commit();
            GooglePlayServicesUtil.showErrorDialogFragment(GooglePlayServicesUtil.isGooglePlayServicesAvailable((Context)this.Ll.getActivity()), this.Ll.getActivity(), -1);
        }
    }
    
    @Override
    public void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.setClassLoader(WalletFragmentOptions.class.getClassLoader());
        this.atH.onSaveInstanceState(bundle);
        if (this.atK != null) {
            bundle.putParcelable("walletFragmentInitParams", (Parcelable)this.atK);
            this.atK = null;
        }
        if (this.atL != null) {
            bundle.putParcelable("maskedWalletRequest", (Parcelable)this.atL);
            this.atL = null;
        }
        if (this.atM != null) {
            bundle.putParcelable("maskedWallet", (Parcelable)this.atM);
            this.atM = null;
        }
        if (this.atJ != null) {
            bundle.putParcelable("walletFragmentOptions", (Parcelable)this.atJ);
            this.atJ = null;
        }
        if (this.atN != null) {
            bundle.putBoolean("enabled", (boolean)this.atN);
            this.atN = null;
        }
    }
    
    @Override
    public void onStart() {
        super.onStart();
        this.atH.onStart();
    }
    
    @Override
    public void onStop() {
        super.onStop();
        this.atH.onStop();
    }
    
    public void setEnabled(final boolean b) {
        if (this.atF != null) {
            this.atF.setEnabled(b);
            this.atN = null;
            return;
        }
        this.atN = b;
    }
    
    public void setOnStateChangedListener(final OnStateChangedListener onStateChangedListener) {
        this.atI.a(onStateChangedListener);
    }
    
    public void updateMaskedWallet(final MaskedWallet atM) {
        if (this.atF != null) {
            this.atF.updateMaskedWallet(atM);
            this.atM = null;
            return;
        }
        this.atM = atM;
    }
    
    public void updateMaskedWalletRequest(final MaskedWalletRequest atL) {
        if (this.atF != null) {
            this.atF.updateMaskedWalletRequest(atL);
            this.atL = null;
            return;
        }
        this.atL = atL;
    }
    
    public interface OnStateChangedListener
    {
        void onStateChanged(final SupportWalletFragment p0, final int p1, final int p2, final Bundle p3);
    }
    
    static class a extends or.a
    {
        private OnStateChangedListener atO;
        private final SupportWalletFragment atP;
        
        a(final SupportWalletFragment atP) {
            this.atP = atP;
        }
        
        public void a(final int n, final int n2, final Bundle bundle) {
            if (this.atO != null) {
                this.atO.onStateChanged(this.atP, n, n2, bundle);
            }
        }
        
        public void a(final OnStateChangedListener atO) {
            this.atO = atO;
        }
    }
    
    private static class b implements LifecycleDelegate
    {
        private final oq atQ;
        
        private b(final oq atQ) {
            this.atQ = atQ;
        }
        
        private int getState() {
            try {
                return this.atQ.getState();
            }
            catch (RemoteException ex) {
                throw new RuntimeException((Throwable)ex);
            }
        }
        
        private void initialize(final WalletFragmentInitParams walletFragmentInitParams) {
            try {
                this.atQ.initialize(walletFragmentInitParams);
            }
            catch (RemoteException ex) {
                throw new RuntimeException((Throwable)ex);
            }
        }
        
        private void onActivityResult(final int n, final int n2, final Intent intent) {
            try {
                this.atQ.onActivityResult(n, n2, intent);
            }
            catch (RemoteException ex) {
                throw new RuntimeException((Throwable)ex);
            }
        }
        
        private void setEnabled(final boolean enabled) {
            try {
                this.atQ.setEnabled(enabled);
            }
            catch (RemoteException ex) {
                throw new RuntimeException((Throwable)ex);
            }
        }
        
        private void updateMaskedWallet(final MaskedWallet maskedWallet) {
            try {
                this.atQ.updateMaskedWallet(maskedWallet);
            }
            catch (RemoteException ex) {
                throw new RuntimeException((Throwable)ex);
            }
        }
        
        private void updateMaskedWalletRequest(final MaskedWalletRequest maskedWalletRequest) {
            try {
                this.atQ.updateMaskedWalletRequest(maskedWalletRequest);
            }
            catch (RemoteException ex) {
                throw new RuntimeException((Throwable)ex);
            }
        }
        
        @Override
        public void onCreate(final Bundle bundle) {
            try {
                this.atQ.onCreate(bundle);
            }
            catch (RemoteException ex) {
                throw new RuntimeException((Throwable)ex);
            }
        }
        
        @Override
        public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
            try {
                return e.f(this.atQ.onCreateView(e.k(layoutInflater), e.k(viewGroup), bundle));
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
                this.atQ.a(e.k(activity), walletFragmentOptions, bundle2);
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
                this.atQ.onPause();
            }
            catch (RemoteException ex) {
                throw new RuntimeException((Throwable)ex);
            }
        }
        
        @Override
        public void onResume() {
            try {
                this.atQ.onResume();
            }
            catch (RemoteException ex) {
                throw new RuntimeException((Throwable)ex);
            }
        }
        
        @Override
        public void onSaveInstanceState(final Bundle bundle) {
            try {
                this.atQ.onSaveInstanceState(bundle);
            }
            catch (RemoteException ex) {
                throw new RuntimeException((Throwable)ex);
            }
        }
        
        @Override
        public void onStart() {
            try {
                this.atQ.onStart();
            }
            catch (RemoteException ex) {
                throw new RuntimeException((Throwable)ex);
            }
        }
        
        @Override
        public void onStop() {
            try {
                this.atQ.onStop();
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
            final Button button = new Button((Context)SupportWalletFragment.this.Ll.getActivity());
            button.setText(R.string.wallet_buy_button_place_holder);
            final int n = -1;
            int a = -2;
            int a2 = n;
            if (SupportWalletFragment.this.atJ != null) {
                final WalletFragmentStyle fragmentStyle = SupportWalletFragment.this.atJ.getFragmentStyle();
                a = a;
                a2 = n;
                if (fragmentStyle != null) {
                    final DisplayMetrics displayMetrics = SupportWalletFragment.this.Ll.getResources().getDisplayMetrics();
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
            final FragmentActivity activity = SupportWalletFragment.this.Ll.getActivity();
            if (SupportWalletFragment.this.atF != null || !SupportWalletFragment.this.mCreated || activity == null) {
                return;
            }
            try {
                SupportWalletFragment.this.atF = new b(oy.a(activity, SupportWalletFragment.this.atG, SupportWalletFragment.this.atJ, SupportWalletFragment.this.atI));
                SupportWalletFragment.this.atJ = null;
                f.a(SupportWalletFragment.this.atF);
                if (SupportWalletFragment.this.atK != null) {
                    SupportWalletFragment.this.atF.initialize(SupportWalletFragment.this.atK);
                    SupportWalletFragment.this.atK = null;
                }
                if (SupportWalletFragment.this.atL != null) {
                    SupportWalletFragment.this.atF.updateMaskedWalletRequest(SupportWalletFragment.this.atL);
                    SupportWalletFragment.this.atL = null;
                }
                if (SupportWalletFragment.this.atM != null) {
                    SupportWalletFragment.this.atF.updateMaskedWallet(SupportWalletFragment.this.atM);
                    SupportWalletFragment.this.atM = null;
                }
                if (SupportWalletFragment.this.atN != null) {
                    SupportWalletFragment.this.atF.setEnabled(SupportWalletFragment.this.atN);
                    SupportWalletFragment.this.atN = null;
                }
            }
            catch (GooglePlayServicesNotAvailableException ex) {}
        }
        
        public void onClick(final View view) {
            final FragmentActivity activity = SupportWalletFragment.this.Ll.getActivity();
            GooglePlayServicesUtil.showErrorDialogFragment(GooglePlayServicesUtil.isGooglePlayServicesAvailable((Context)activity), activity, -1);
        }
    }
}
