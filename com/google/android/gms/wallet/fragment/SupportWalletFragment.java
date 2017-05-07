// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet.fragment;

import com.google.android.gms.dynamic.e;
import android.os.RemoteException;
import com.google.android.gms.internal.oq;
import com.google.android.gms.dynamic.LifecycleDelegate;
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
    private SupportWalletFragment$b atF;
    private final h atG;
    private final SupportWalletFragment$c atH;
    private SupportWalletFragment$a atI;
    private WalletFragmentOptions atJ;
    private WalletFragmentInitParams atK;
    private MaskedWalletRequest atL;
    private MaskedWallet atM;
    private Boolean atN;
    private boolean mCreated;
    
    public SupportWalletFragment() {
        this.mCreated = false;
        this.atG = h.a(this);
        this.atH = new SupportWalletFragment$c(this, null);
        this.atI = new SupportWalletFragment$a(this);
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
    
    public void setOnStateChangedListener(final SupportWalletFragment$OnStateChangedListener supportWalletFragment$OnStateChangedListener) {
        this.atI.a(supportWalletFragment$OnStateChangedListener);
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
}
