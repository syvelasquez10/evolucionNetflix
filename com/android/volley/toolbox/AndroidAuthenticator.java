// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley.toolbox;

import android.accounts.AccountManagerFuture;
import com.android.volley.AuthFailureError;
import android.content.Intent;
import android.os.Handler;
import android.accounts.AccountManagerCallback;
import android.os.Bundle;
import android.accounts.AccountManager;
import android.content.Context;
import android.accounts.Account;

public class AndroidAuthenticator implements Authenticator
{
    private final Account mAccount;
    private final String mAuthTokenType;
    private final Context mContext;
    private final boolean mNotifyAuthFailure;
    
    public AndroidAuthenticator(final Context context, final Account account, final String s) {
        this(context, account, s, false);
    }
    
    public AndroidAuthenticator(final Context mContext, final Account mAccount, final String mAuthTokenType, final boolean mNotifyAuthFailure) {
        this.mContext = mContext;
        this.mAccount = mAccount;
        this.mAuthTokenType = mAuthTokenType;
        this.mNotifyAuthFailure = mNotifyAuthFailure;
    }
    
    public Account getAccount() {
        return this.mAccount;
    }
    
    @Override
    public String getAuthToken() {
        final String s = null;
        final AccountManagerFuture authToken = AccountManager.get(this.mContext).getAuthToken(this.mAccount, this.mAuthTokenType, (Bundle)null, this.mNotifyAuthFailure, (AccountManagerCallback)null, (Handler)null);
        String string = null;
        Label_0109: {
            Bundle bundle;
            try {
                bundle = (Bundle)authToken.getResult();
                string = s;
                if (!authToken.isDone()) {
                    break Label_0109;
                }
                string = s;
                if (authToken.isCancelled()) {
                    break Label_0109;
                }
                if (bundle.containsKey("intent")) {
                    throw new AuthFailureError((Intent)bundle.getParcelable("intent"));
                }
            }
            catch (Exception ex) {
                throw new AuthFailureError("Error while retrieving auth token", ex);
            }
            string = bundle.getString("authtoken");
        }
        if (string == null) {
            throw new AuthFailureError("Got null auth token for type: " + this.mAuthTokenType);
        }
        return string;
    }
    
    @Override
    public void invalidateAuthToken(final String s) {
        AccountManager.get(this.mContext).invalidateAuthToken(this.mAccount.type, s);
    }
}
