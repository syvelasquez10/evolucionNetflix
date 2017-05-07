// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a;
import com.google.android.gms.common.api.Result;
import java.util.Locale;
import com.google.android.gms.internal.ji;
import com.google.android.gms.internal.jj;
import com.google.android.gms.internal.jf;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.fq;
import android.app.Activity;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.fc;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.internal.jg;
import com.google.android.gms.internal.iu;
import com.google.android.gms.internal.ka;
import com.google.android.gms.common.api.Api;

public final class Wallet
{
    public static final Api<WalletOptions> API;
    public static final Payments Payments;
    public static final ka aco;
    public static final iu acp;
    private static final Api.c<jg> wx;
    private static final Api.b<jg, WalletOptions> wy;
    
    static {
        wx = new Api.c();
        wy = new Api.b<jg, WalletOptions>() {
            public jg a(final Context context, final Looper looper, final fc fc, WalletOptions walletOptions, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
                fq.b(context instanceof Activity, "An Activity must be used for Wallet APIs");
                final Activity activity = (Activity)context;
                if (walletOptions == null) {
                    walletOptions = new WalletOptions();
                }
                return new jg(activity, looper, connectionCallbacks, onConnectionFailedListener, walletOptions.environment, fc.getAccountName(), walletOptions.theme);
            }
            
            @Override
            public int getPriority() {
                return Integer.MAX_VALUE;
            }
        };
        API = new Api<WalletOptions>((Api.b<C, WalletOptions>)Wallet.wy, (Api.c<C>)Wallet.wx, new Scope[0]);
        Payments = new jf();
        aco = new jj();
        acp = new ji();
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
            this.environment = builder.acq;
            this.theme = builder.mTheme;
        }
        
        public static final class Builder
        {
            private int acq;
            private int mTheme;
            
            public Builder() {
                this.acq = 0;
                this.mTheme = 0;
            }
            
            public WalletOptions build() {
                return new WalletOptions(this);
            }
            
            public Builder setEnvironment(final int acq) {
                if (acq == 0 || acq == 2 || acq == 1) {
                    this.acq = acq;
                    return this;
                }
                throw new IllegalArgumentException(String.format(Locale.US, "Invalid environment value %d", acq));
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
    
    public abstract static class a<R extends Result> extends com.google.android.gms.common.api.a.b<R, jg>
    {
        public a() {
            super(Wallet.wx);
        }
    }
    
    public abstract static class b extends a<Status>
    {
        protected Status f(final Status status) {
            return status;
        }
    }
}
