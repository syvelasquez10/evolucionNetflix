// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import android.app.Activity;
import com.google.android.gms.internal.iu;
import com.google.android.gms.common.GooglePlayServicesClient;

@Deprecated
public class WalletClient implements GooglePlayServicesClient
{
    private final iu Hj;
    
    public WalletClient(final Activity activity, final int n, final String s, final int n2, final ConnectionCallbacks connectionCallbacks, final OnConnectionFailedListener onConnectionFailedListener) {
        this.Hj = new iu(activity, connectionCallbacks, onConnectionFailedListener, n, s, n2);
    }
    
    public WalletClient(final Activity activity, final int n, final String s, final ConnectionCallbacks connectionCallbacks, final OnConnectionFailedListener onConnectionFailedListener) {
        this(activity, n, s, 0, connectionCallbacks, onConnectionFailedListener);
    }
    
    public void changeMaskedWallet(final String s, final String s2, final int n) {
        this.Hj.changeMaskedWallet(s, s2, n);
    }
    
    public void checkForPreAuthorization(final int n) {
        this.Hj.checkForPreAuthorization(n);
    }
    
    @Override
    public void connect() {
        this.Hj.connect();
    }
    
    @Override
    public void disconnect() {
        this.Hj.disconnect();
    }
    
    @Override
    public boolean isConnected() {
        return this.Hj.isConnected();
    }
    
    @Override
    public boolean isConnecting() {
        return this.Hj.isConnecting();
    }
    
    @Override
    public boolean isConnectionCallbacksRegistered(final ConnectionCallbacks connectionCallbacks) {
        return this.Hj.isConnectionCallbacksRegistered(connectionCallbacks);
    }
    
    @Override
    public boolean isConnectionFailedListenerRegistered(final OnConnectionFailedListener onConnectionFailedListener) {
        return this.Hj.isConnectionFailedListenerRegistered(onConnectionFailedListener);
    }
    
    public void loadFullWallet(final FullWalletRequest fullWalletRequest, final int n) {
        this.Hj.loadFullWallet(fullWalletRequest, n);
    }
    
    public void loadMaskedWallet(final MaskedWalletRequest maskedWalletRequest, final int n) {
        this.Hj.loadMaskedWallet(maskedWalletRequest, n);
    }
    
    public void notifyTransactionStatus(final NotifyTransactionStatusRequest notifyTransactionStatusRequest) {
        this.Hj.notifyTransactionStatus(notifyTransactionStatusRequest);
    }
    
    @Override
    public void registerConnectionCallbacks(final ConnectionCallbacks connectionCallbacks) {
        this.Hj.registerConnectionCallbacks(connectionCallbacks);
    }
    
    @Override
    public void registerConnectionFailedListener(final OnConnectionFailedListener onConnectionFailedListener) {
        this.Hj.registerConnectionFailedListener(onConnectionFailedListener);
    }
    
    @Override
    public void unregisterConnectionCallbacks(final ConnectionCallbacks connectionCallbacks) {
        this.Hj.unregisterConnectionCallbacks(connectionCallbacks);
    }
    
    @Override
    public void unregisterConnectionFailedListener(final OnConnectionFailedListener onConnectionFailedListener) {
        this.Hj.unregisterConnectionFailedListener(onConnectionFailedListener);
    }
}
