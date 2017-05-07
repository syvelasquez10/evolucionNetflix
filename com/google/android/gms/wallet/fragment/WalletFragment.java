// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet.fragment;

import com.google.android.gms.dynamic.e;
import android.os.RemoteException;
import com.google.android.gms.internal.oq;
import com.google.android.gms.dynamic.LifecycleDelegate;
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
    private WalletFragment$b atS;
    private final b atT;
    private final WalletFragment$c atU;
    private WalletFragment$a atV;
    private boolean mCreated;
    
    public WalletFragment() {
        this.mCreated = false;
        this.atT = b.a(this);
        this.atU = new WalletFragment$c(this, null);
        this.atV = new WalletFragment$a(this);
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
    
    public void setOnStateChangedListener(final WalletFragment$OnStateChangedListener walletFragment$OnStateChangedListener) {
        this.atV.a(walletFragment$OnStateChangedListener);
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
}
