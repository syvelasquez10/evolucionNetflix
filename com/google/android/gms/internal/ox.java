// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.IBinder;
import com.google.android.gms.wallet.NotifyTransactionStatusRequest;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;
import android.os.RemoteException;
import com.google.android.gms.wallet.FullWallet;
import android.util.Log;
import com.google.android.gms.wallet.FullWalletRequest;
import com.google.android.gms.common.internal.j;
import com.google.android.gms.common.internal.d$e;
import com.google.android.gms.common.internal.k;
import android.os.Parcelable;
import android.accounts.Account;
import android.text.TextUtils;
import android.os.Bundle;
import android.content.Context;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import android.os.Looper;
import android.app.Activity;
import com.google.android.gms.common.internal.d;

public class ox extends d<os>
{
    private final String Dd;
    private final int atA;
    private final int mTheme;
    private final Activity nr;
    
    public ox(final Activity nr, final Looper looper, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener, final int atA, final String dd, final int mTheme) {
        super((Context)nr, looper, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener, new String[0]);
        this.nr = nr;
        this.atA = atA;
        this.Dd = dd;
        this.mTheme = mTheme;
    }
    
    public static Bundle a(final int n, final String s, final String s2, final int n2) {
        final Bundle bundle = new Bundle();
        bundle.putInt("com.google.android.gms.wallet.EXTRA_ENVIRONMENT", n);
        bundle.putString("androidPackageName", s);
        if (!TextUtils.isEmpty((CharSequence)s2)) {
            bundle.putParcelable("com.google.android.gms.wallet.EXTRA_BUYER_ACCOUNT", (Parcelable)new Account(s2, "com.google"));
        }
        bundle.putInt("com.google.android.gms.wallet.EXTRA_THEME", n2);
        return bundle;
    }
    
    private Bundle pM() {
        return a(this.atA, this.nr.getPackageName(), this.Dd, this.mTheme);
    }
    
    @Override
    protected void a(final k k, final d$e d$e) {
        k.k(d$e, 6111000, this.getContext().getPackageName());
    }
    
    public void a(final FullWalletRequest fullWalletRequest, final int n) {
        final ox$b ox$b = new ox$b(this, n);
        final Bundle pm = this.pM();
        try {
            this.gS().a(fullWalletRequest, pm, ox$b);
        }
        catch (RemoteException ex) {
            Log.e("WalletClientImpl", "RemoteException getting full wallet", (Throwable)ex);
            ox$b.a(8, (FullWallet)null, Bundle.EMPTY);
        }
    }
    
    public void a(final MaskedWalletRequest maskedWalletRequest, final int n) {
        final Bundle pm = this.pM();
        final ox$b ox$b = new ox$b(this, n);
        try {
            this.gS().a(maskedWalletRequest, pm, ox$b);
        }
        catch (RemoteException ex) {
            Log.e("WalletClientImpl", "RemoteException getting masked wallet", (Throwable)ex);
            ox$b.a(8, (MaskedWallet)null, Bundle.EMPTY);
        }
    }
    
    public void a(final NotifyTransactionStatusRequest notifyTransactionStatusRequest) {
        final Bundle pm = this.pM();
        try {
            this.gS().a(notifyTransactionStatusRequest, pm);
        }
        catch (RemoteException ex) {}
    }
    
    protected os bP(final IBinder binder) {
        return os$a.bL(binder);
    }
    
    public void d(final String s, final String s2, final int n) {
        final Bundle pm = this.pM();
        final ox$b ox$b = new ox$b(this, n);
        try {
            this.gS().a(s, s2, pm, ox$b);
        }
        catch (RemoteException ex) {
            Log.e("WalletClientImpl", "RemoteException changing masked wallet", (Throwable)ex);
            ox$b.a(8, (MaskedWallet)null, Bundle.EMPTY);
        }
    }
    
    public void fH(final int n) {
        final Bundle pm = this.pM();
        final ox$b ox$b = new ox$b(this, n);
        try {
            this.gS().a(pm, ox$b);
        }
        catch (RemoteException ex) {
            Log.e("WalletClientImpl", "RemoteException during checkForPreAuthorization", (Throwable)ex);
            ox$b.a(8, false, Bundle.EMPTY);
        }
    }
    
    @Override
    protected String getServiceDescriptor() {
        return "com.google.android.gms.wallet.internal.IOwService";
    }
    
    @Override
    protected String getStartServiceAction() {
        return "com.google.android.gms.wallet.service.BIND";
    }
}
