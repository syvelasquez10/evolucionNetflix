// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.IBinder;
import com.google.android.gms.wallet.NotifyTransactionStatusRequest;
import com.google.android.gms.wallet.MaskedWalletRequest;
import android.os.RemoteException;
import com.google.android.gms.wallet.FullWalletRequest;
import com.google.android.gms.common.internal.j;
import com.google.android.gms.common.internal.d$e;
import com.google.android.gms.common.internal.k;
import android.accounts.Account;
import android.text.TextUtils;
import android.content.Context;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import android.os.Looper;
import android.app.Activity;
import com.google.android.gms.common.internal.d;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.wallet.MaskedWallet;
import android.app.PendingIntent$CanceledException;
import android.os.Parcelable;
import android.content.Intent;
import android.content.IntentSender$SendIntentException;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import android.app.PendingIntent;
import android.os.Bundle;
import com.google.android.gms.wallet.FullWallet;

final class ox$b extends ox$a
{
    private final int Lm;
    final /* synthetic */ ox aul;
    
    public ox$b(final ox aul, final int lm) {
        this.aul = aul;
        super(null);
        this.Lm = lm;
    }
    
    @Override
    public void a(final int n, final FullWallet fullWallet, final Bundle bundle) {
        PendingIntent pendingIntent = null;
        if (bundle != null) {
            pendingIntent = (PendingIntent)bundle.getParcelable("com.google.android.gms.wallet.EXTRA_PENDING_INTENT");
        }
        final ConnectionResult connectionResult = new ConnectionResult(n, pendingIntent);
        if (connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult(this.aul.nr, this.Lm);
                return;
            }
            catch (IntentSender$SendIntentException ex) {
                Log.w("WalletClientImpl", "Exception starting pending intent", (Throwable)ex);
                return;
            }
        }
        final Intent intent = new Intent();
        int n2;
        if (connectionResult.isSuccess()) {
            n2 = -1;
            intent.putExtra("com.google.android.gms.wallet.EXTRA_FULL_WALLET", (Parcelable)fullWallet);
        }
        else {
            if (n == 408) {
                n2 = 0;
            }
            else {
                n2 = 1;
            }
            intent.putExtra("com.google.android.gms.wallet.EXTRA_ERROR_CODE", n);
        }
        final PendingIntent pendingResult = this.aul.nr.createPendingResult(this.Lm, intent, 1073741824);
        if (pendingResult == null) {
            Log.w("WalletClientImpl", "Null pending result returned for onFullWalletLoaded");
            return;
        }
        try {
            pendingResult.send(n2);
        }
        catch (PendingIntent$CanceledException ex2) {
            Log.w("WalletClientImpl", "Exception setting pending result", (Throwable)ex2);
        }
    }
    
    @Override
    public void a(final int n, final MaskedWallet maskedWallet, final Bundle bundle) {
        PendingIntent pendingIntent = null;
        if (bundle != null) {
            pendingIntent = (PendingIntent)bundle.getParcelable("com.google.android.gms.wallet.EXTRA_PENDING_INTENT");
        }
        final ConnectionResult connectionResult = new ConnectionResult(n, pendingIntent);
        if (connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult(this.aul.nr, this.Lm);
                return;
            }
            catch (IntentSender$SendIntentException ex) {
                Log.w("WalletClientImpl", "Exception starting pending intent", (Throwable)ex);
                return;
            }
        }
        final Intent intent = new Intent();
        int n2;
        if (connectionResult.isSuccess()) {
            n2 = -1;
            intent.putExtra("com.google.android.gms.wallet.EXTRA_MASKED_WALLET", (Parcelable)maskedWallet);
        }
        else {
            if (n == 408) {
                n2 = 0;
            }
            else {
                n2 = 1;
            }
            intent.putExtra("com.google.android.gms.wallet.EXTRA_ERROR_CODE", n);
        }
        final PendingIntent pendingResult = this.aul.nr.createPendingResult(this.Lm, intent, 1073741824);
        if (pendingResult == null) {
            Log.w("WalletClientImpl", "Null pending result returned for onMaskedWalletLoaded");
            return;
        }
        try {
            pendingResult.send(n2);
        }
        catch (PendingIntent$CanceledException ex2) {
            Log.w("WalletClientImpl", "Exception setting pending result", (Throwable)ex2);
        }
    }
    
    @Override
    public void a(final int n, final boolean b, final Bundle bundle) {
        final Intent intent = new Intent();
        intent.putExtra("com.google.android.gm.wallet.EXTRA_IS_USER_PREAUTHORIZED", b);
        final PendingIntent pendingResult = this.aul.nr.createPendingResult(this.Lm, intent, 1073741824);
        if (pendingResult == null) {
            Log.w("WalletClientImpl", "Null pending result returned for onPreAuthorizationDetermined");
            return;
        }
        try {
            pendingResult.send(-1);
        }
        catch (PendingIntent$CanceledException ex) {
            Log.w("WalletClientImpl", "Exception setting pending result", (Throwable)ex);
        }
    }
    
    @Override
    public void i(final int n, final Bundle bundle) {
        n.b(bundle, "Bundle should not be null");
        final ConnectionResult connectionResult = new ConnectionResult(n, (PendingIntent)bundle.getParcelable("com.google.android.gms.wallet.EXTRA_PENDING_INTENT"));
        if (connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult(this.aul.nr, this.Lm);
                return;
            }
            catch (IntentSender$SendIntentException ex) {
                Log.w("WalletClientImpl", "Exception starting pending intent", (Throwable)ex);
                return;
            }
        }
        Log.e("WalletClientImpl", "Create Wallet Objects confirmation UI will not be shown connection result: " + connectionResult);
        final Intent intent = new Intent();
        intent.putExtra("com.google.android.gms.wallet.EXTRA_ERROR_CODE", 413);
        final PendingIntent pendingResult = this.aul.nr.createPendingResult(this.Lm, intent, 1073741824);
        if (pendingResult == null) {
            Log.w("WalletClientImpl", "Null pending result returned for onWalletObjectsCreated");
            return;
        }
        try {
            pendingResult.send(1);
        }
        catch (PendingIntent$CanceledException ex2) {
            Log.w("WalletClientImpl", "Exception setting pending result", (Throwable)ex2);
        }
    }
}
