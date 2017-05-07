// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation;
import com.google.android.gms.common.api.Result;
import java.util.Locale;
import com.google.android.gms.internal.oz;
import com.google.android.gms.internal.pa;
import com.google.android.gms.internal.ow;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.n;
import android.app.Activity;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.internal.ol;
import com.google.android.gms.wallet.wobs.r;
import com.google.android.gms.internal.ox;
import com.google.android.gms.common.api.Api;

public final class Wallet
{
    public static final Api<WalletOptions> API;
    private static final Api.c<ox> CU;
    private static final Api.b<ox, WalletOptions> CV;
    public static final Payments Payments;
    public static final r aty;
    public static final ol atz;
    
    static {
        CU = new Api.c();
        CV = new Api.b<ox, WalletOptions>() {
            public ox a(final Context context, final Looper looper, final ClientSettings clientSettings, WalletOptions walletOptions, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
                n.b(context instanceof Activity, (Object)"An Activity must be used for Wallet APIs");
                final Activity activity = (Activity)context;
                if (walletOptions == null) {
                    walletOptions = new WalletOptions();
                }
                return new ox(activity, looper, connectionCallbacks, onConnectionFailedListener, walletOptions.environment, clientSettings.getAccountName(), walletOptions.theme);
            }
            
            @Override
            public int getPriority() {
                return Integer.MAX_VALUE;
            }
        };
        API = new Api<WalletOptions>((Api.b<C, WalletOptions>)Wallet.CV, (Api.c<C>)Wallet.CU, new Scope[0]);
        Payments = new ow();
        aty = new pa();
        atz = new oz();
    }
    
    @Deprecated
    public static void changeMaskedWallet(final GoogleApiClient googleApiClient, final String s, final String s2, final int n) {
        Wallet.Payments.changeMaskedWallet(googleApiClient, s, s2, n);
    }
    
    @Deprecated
    public static void checkForPreAuthorization(final GoogleApiClient googleApiClient, final int n) {
        Wallet.Payments.checkForPreAuthorization(googleApiClient, n);
    }
    
    @Deprecated
    public static void loadFullWallet(final GoogleApiClient googleApiClient, final FullWalletRequest fullWalletRequest, final int n) {
        Wallet.Payments.loadFullWallet(googleApiClient, fullWalletRequest, n);
    }
    
    @Deprecated
    public static void loadMaskedWallet(final GoogleApiClient googleApiClient, final MaskedWalletRequest maskedWalletRequest, final int n) {
        Wallet.Payments.loadMaskedWallet(googleApiClient, maskedWalletRequest, n);
    }
    
    @Deprecated
    public static void notifyTransactionStatus(final GoogleApiClient googleApiClient, final NotifyTransactionStatusRequest notifyTransactionStatusRequest) {
        Wallet.Payments.notifyTransactionStatus(googleApiClient, notifyTransactionStatusRequest);
    }
    
    public static final class WalletOptions implements HasOptions
    {
        public final int environment;
        public final int theme;
        
        private WalletOptions() {
            this(new Builder());
        }
        
        private WalletOptions(final Builder builder) {
            this.environment = builder.atA;
            this.theme = builder.mTheme;
        }
        
        public static final class Builder
        {
            private int atA;
            private int mTheme;
            
            public Builder() {
                this.atA = 0;
                this.mTheme = 0;
            }
            
            public WalletOptions build() {
                return new WalletOptions(this);
            }
            
            public Builder setEnvironment(final int atA) {
                if (atA == 0 || atA == 2 || atA == 1) {
                    this.atA = atA;
                    return this;
                }
                throw new IllegalArgumentException(String.format(Locale.US, "Invalid environment value %d", atA));
            }
            
            public Builder setTheme(final int mTheme) {
                if (mTheme == 0 || mTheme == 1) {
                    this.mTheme = mTheme;
                    return this;
                }
                throw new IllegalArgumentException(String.format(Locale.US, "Invalid theme value %d", mTheme));
            }
        }
    }
    
    public abstract static class a<R extends Result> extends BaseImplementation.a<R, ox>
    {
        public a() {
            super(Wallet.CU);
        }
    }
    
    public abstract static class b extends a<Status>
    {
        protected Status d(final Status status) {
            return status;
        }
    }
}
