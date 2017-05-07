// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet.fragment;

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
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;
import android.app.Fragment;

public final class WalletFragment extends Fragment
{
    private final Fragment Sb;
    private WalletFragmentOptions atJ;
    private WalletFragmentInitParams atK;
    private MaskedWalletRequest atL;
    private MaskedWallet atM;
    private Boolean atN;
    private b atS;
    private final com.google.android.gms.dynamic.b atT;
    private final c atU;
    private a atV;
    private boolean mCreated;
    
    public WalletFragment() {
        this.mCreated = false;
        this.atT = com.google.android.gms.dynamic.b.a(this);
        this.atU = new c();
        this.atV = new a(this);
        this.Sb = this;
    }
    
    public static WalletFragment newInstance(final WalletFragmentOptions walletFragmentOptions) {
        final WalletFragment walletFragment = new WalletFragment();
        final Bundle arguments = new Bundle();
        arguments.putParcelable("extraWalletFragmentOptions", (Parcelable)walletFragmentOptions);
        walletFragment.Sb.setArguments(arguments);
        return walletFragment;
    }
    
    public int getState() {
        if (this.atS != null) {
            return this.atS.getState();
        }
        return 0;
    }
    
    public void initialize(final WalletFragmentInitParams atK) {
        if (this.atS != null) {
            this.atS.initialize(atK);
            this.atK = null;
        }
        else {
            if (this.atK != null) {
                Log.w("WalletFragment", "initialize(WalletFragmentInitParams) was called more than once. Ignoring.");
                return;
            }
            this.atK = atK;
            if (this.atL != null) {
                Log.w("WalletFragment", "updateMaskedWalletRequest() was called before initialize()");
            }
            if (this.atM != null) {
                Log.w("WalletFragment", "updateMaskedWallet() was called before initialize()");
            }
        }
    }
    
    public void onActivityResult(final int n, final int n2, final Intent intent) {
        super.onActivityResult(n, n2, intent);
        if (this.atS != null) {
            this.atS.onActivityResult(n, n2, intent);
        }
    }
    
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            bundle.setClassLoader(WalletFragmentOptions.class.getClassLoader());
            final WalletFragmentInitParams atK = (WalletFragmentInitParams)bundle.getParcelable("walletFragmentInitParams");
            if (atK != null) {
                if (this.atK != null) {
                    Log.w("WalletFragment", "initialize(WalletFragmentInitParams) was called more than once.Ignoring.");
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
        else if (this.Sb.getArguments() != null) {
            final WalletFragmentOptions atJ = (WalletFragmentOptions)this.Sb.getArguments().getParcelable("extraWalletFragmentOptions");
            if (atJ != null) {
                atJ.Z((Context)this.Sb.getActivity());
                this.atJ = atJ;
            }
        }
        this.mCreated = true;
        this.atU.onCreate(bundle);
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        return this.atU.onCreateView(layoutInflater, viewGroup, bundle);
    }
    
    public void onDestroy() {
        super.onDestroy();
        this.mCreated = false;
    }
    
    public void onInflate(final Activity activity, final AttributeSet set, final Bundle bundle) {
        super.onInflate(activity, set, bundle);
        if (this.atJ == null) {
            this.atJ = WalletFragmentOptions.a((Context)activity, set);
        }
        final Bundle bundle2 = new Bundle();
        bundle2.putParcelable("attrKeyWalletFragmentOptions", (Parcelable)this.atJ);
        this.atU.onInflate(activity, bundle2, bundle);
    }
    
    public void onPause() {
        super.onPause();
        this.atU.onPause();
    }
    
    public void onResume() {
        super.onResume();
        this.atU.onResume();
        final FragmentManager fragmentManager = this.Sb.getActivity().getFragmentManager();
        final Fragment fragmentByTag = fragmentManager.findFragmentByTag("GooglePlayServicesErrorDialog");
        if (fragmentByTag != null) {
            fragmentManager.beginTransaction().remove(fragmentByTag).commit();
            GooglePlayServicesUtil.showErrorDialogFragment(GooglePlayServicesUtil.isGooglePlayServicesAvailable((Context)this.Sb.getActivity()), this.Sb.getActivity(), -1);
        }
    }
    
    public void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.setClassLoader(WalletFragmentOptions.class.getClassLoader());
        this.atU.onSaveInstanceState(bundle);
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
    
    public void onStart() {
        super.onStart();
        this.atU.onStart();
    }
    
    public void onStop() {
        super.onStop();
        this.atU.onStop();
    }
    
    public void setEnabled(final boolean b) {
        if (this.atS != null) {
            this.atS.setEnabled(b);
            this.atN = null;
            return;
        }
        this.atN = b;
    }
    
    public void setOnStateChangedListener(final OnStateChangedListener onStateChangedListener) {
        this.atV.a(onStateChangedListener);
    }
    
    public void updateMaskedWallet(final MaskedWallet atM) {
        if (this.atS != null) {
            this.atS.updateMaskedWallet(atM);
            this.atM = null;
            return;
        }
        this.atM = atM;
    }
    
    public void updateMaskedWalletRequest(final MaskedWalletRequest atL) {
        if (this.atS != null) {
            this.atS.updateMaskedWalletRequest(atL);
            this.atL = null;
            return;
        }
        this.atL = atL;
    }
    
    public interface OnStateChangedListener
    {
        void onStateChanged(final WalletFragment p0, final int p1, final int p2, final Bundle p3);
    }
    
    static class a extends or.a
    {
        private OnStateChangedListener atW;
        private final WalletFragment atX;
        
        a(final WalletFragment atX) {
            this.atX = atX;
        }
        
        public void a(final int n, final int n2, final Bundle bundle) {
            if (this.atW != null) {
                this.atW.onStateChanged(this.atX, n, n2, bundle);
            }
        }
        
        public void a(final OnStateChangedListener atW) {
            this.atW = atW;
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
            final Button button = new Button((Context)WalletFragment.this.Sb.getActivity());
            button.setText(R.string.wallet_buy_button_place_holder);
            final int n = -1;
            int a = -2;
            int a2 = n;
            if (WalletFragment.this.atJ != null) {
                final WalletFragmentStyle fragmentStyle = WalletFragment.this.atJ.getFragmentStyle();
                a = a;
                a2 = n;
                if (fragmentStyle != null) {
                    final DisplayMetrics displayMetrics = WalletFragment.this.Sb.getResources().getDisplayMetrics();
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
            final Activity activity = WalletFragment.this.Sb.getActivity();
            if (WalletFragment.this.atS != null || !WalletFragment.this.mCreated || activity == null) {
                return;
            }
            try {
                WalletFragment.this.atS = new b(oy.a(activity, WalletFragment.this.atT, WalletFragment.this.atJ, WalletFragment.this.atV));
                WalletFragment.this.atJ = null;
                f.a(WalletFragment.this.atS);
                if (WalletFragment.this.atK != null) {
                    WalletFragment.this.atS.initialize(WalletFragment.this.atK);
                    WalletFragment.this.atK = null;
                }
                if (WalletFragment.this.atL != null) {
                    WalletFragment.this.atS.updateMaskedWalletRequest(WalletFragment.this.atL);
                    WalletFragment.this.atL = null;
                }
                if (WalletFragment.this.atM != null) {
                    WalletFragment.this.atS.updateMaskedWallet(WalletFragment.this.atM);
                    WalletFragment.this.atM = null;
                }
                if (WalletFragment.this.atN != null) {
                    WalletFragment.this.atS.setEnabled(WalletFragment.this.atN);
                    WalletFragment.this.atN = null;
                }
            }
            catch (GooglePlayServicesNotAvailableException ex) {}
        }
        
        public void onClick(final View view) {
            final Activity activity = WalletFragment.this.Sb.getActivity();
            GooglePlayServicesUtil.showErrorDialogFragment(GooglePlayServicesUtil.isGooglePlayServicesAvailable((Context)activity), activity, -1);
        }
    }
}
