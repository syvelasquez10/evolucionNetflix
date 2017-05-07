// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.ads.mediation.MediationBannerListener;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.dynamic.e;
import android.app.Activity;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.dynamic.d;
import java.util.Iterator;
import java.util.Map;
import android.os.RemoteException;
import java.util.HashMap;
import org.json.JSONObject;
import com.google.ads.mediation.MediationAdapter;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.NetworkExtras;

public final class bw<NETWORK_EXTRAS extends NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters> extends br.a
{
    private final MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> nH;
    private final NETWORK_EXTRAS nI;
    
    public bw(final MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> nh, final NETWORK_EXTRAS ni) {
        this.nH = nh;
        this.nI = ni;
    }
    
    private SERVER_PARAMETERS b(String s, final int n, final String s2) throws RemoteException {
        HashMap<String, String> hashMap2 = null;
        Label_0098: {
            if (s != null) {
                try {
                    final JSONObject jsonObject = new JSONObject(s);
                    final HashMap hashMap = new HashMap<String, String>(jsonObject.length());
                    final Iterator keys = jsonObject.keys();
                    while (true) {
                        hashMap2 = (HashMap<String, String>)hashMap;
                        if (!keys.hasNext()) {
                            break Label_0098;
                        }
                        s = keys.next();
                        hashMap.put(s, jsonObject.getString(s));
                    }
                }
                catch (Throwable t) {
                    dw.c("Could not get MediationServerParameters.", t);
                    throw new RemoteException();
                }
            }
            hashMap2 = new HashMap<String, String>(0);
        }
        final Class<SERVER_PARAMETERS> serverParametersType = this.nH.getServerParametersType();
        MediationServerParameters mediationServerParameters = null;
        if (serverParametersType != null) {
            mediationServerParameters = serverParametersType.newInstance();
            mediationServerParameters.load(hashMap2);
        }
        return (SERVER_PARAMETERS)mediationServerParameters;
    }
    
    public void a(final d d, final ah ah, final String s, final bs bs) throws RemoteException {
        this.a(d, ah, s, null, bs);
    }
    
    public void a(final d d, final ah ah, final String s, final String s2, final bs bs) throws RemoteException {
        if (!(this.nH instanceof MediationInterstitialAdapter)) {
            dw.z("MediationAdapter is not a MediationInterstitialAdapter: " + this.nH.getClass().getCanonicalName());
            throw new RemoteException();
        }
        dw.v("Requesting interstitial ad from adapter.");
        try {
            ((MediationInterstitialAdapter)this.nH).requestInterstitialAd(new bx<Object, Object>(bs), e.d(d), this.b(s, ah.lL, s2), by.e(ah), this.nI);
        }
        catch (Throwable t) {
            dw.c("Could not request interstitial ad from adapter.", t);
            throw new RemoteException();
        }
    }
    
    public void a(final d d, final ak ak, final ah ah, final String s, final bs bs) throws RemoteException {
        this.a(d, ak, ah, s, null, bs);
    }
    
    public void a(final d d, final ak ak, final ah ah, final String s, final String s2, final bs bs) throws RemoteException {
        if (!(this.nH instanceof MediationBannerAdapter)) {
            dw.z("MediationAdapter is not a MediationBannerAdapter: " + this.nH.getClass().getCanonicalName());
            throw new RemoteException();
        }
        dw.v("Requesting banner ad from adapter.");
        try {
            ((MediationBannerAdapter)this.nH).requestBannerAd(new bx<Object, Object>(bs), e.d(d), this.b(s, ah.lL, s2), by.b(ak), by.e(ah), this.nI);
        }
        catch (Throwable t) {
            dw.c("Could not request banner ad from adapter.", t);
            throw new RemoteException();
        }
    }
    
    public void destroy() throws RemoteException {
        try {
            this.nH.destroy();
        }
        catch (Throwable t) {
            dw.c("Could not destroy adapter.", t);
            throw new RemoteException();
        }
    }
    
    public d getView() throws RemoteException {
        if (!(this.nH instanceof MediationBannerAdapter)) {
            dw.z("MediationAdapter is not a MediationBannerAdapter: " + this.nH.getClass().getCanonicalName());
            throw new RemoteException();
        }
        try {
            return e.h(((MediationBannerAdapter)this.nH).getBannerView());
        }
        catch (Throwable t) {
            dw.c("Could not get banner view from adapter.", t);
            throw new RemoteException();
        }
    }
    
    public void pause() throws RemoteException {
        throw new RemoteException();
    }
    
    public void resume() throws RemoteException {
        throw new RemoteException();
    }
    
    public void showInterstitial() throws RemoteException {
        if (!(this.nH instanceof MediationInterstitialAdapter)) {
            dw.z("MediationAdapter is not a MediationInterstitialAdapter: " + this.nH.getClass().getCanonicalName());
            throw new RemoteException();
        }
        dw.v("Showing interstitial from adapter.");
        try {
            ((MediationInterstitialAdapter)this.nH).showInterstitial();
        }
        catch (Throwable t) {
            dw.c("Could not show interstitial from adapter.", t);
            throw new RemoteException();
        }
    }
}
