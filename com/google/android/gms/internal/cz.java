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

@ez
public final class cz<NETWORK_EXTRAS extends NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters> extends cu.a
{
    private final MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> qG;
    private final NETWORK_EXTRAS qH;
    
    public cz(final MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> qg, final NETWORK_EXTRAS qh) {
        this.qG = qg;
        this.qH = qh;
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
                    gs.d("Could not get MediationServerParameters.", t);
                    throw new RemoteException();
                }
            }
            hashMap2 = new HashMap<String, String>(0);
        }
        final Class<SERVER_PARAMETERS> serverParametersType = this.qG.getServerParametersType();
        MediationServerParameters mediationServerParameters = null;
        if (serverParametersType != null) {
            mediationServerParameters = serverParametersType.newInstance();
            mediationServerParameters.load(hashMap2);
        }
        return (SERVER_PARAMETERS)mediationServerParameters;
    }
    
    public void a(final d d, final av av, final String s, final cv cv) throws RemoteException {
        this.a(d, av, s, null, cv);
    }
    
    public void a(final d d, final av av, final String s, final String s2, final cv cv) throws RemoteException {
        if (!(this.qG instanceof MediationInterstitialAdapter)) {
            gs.W("MediationAdapter is not a MediationInterstitialAdapter: " + this.qG.getClass().getCanonicalName());
            throw new RemoteException();
        }
        gs.S("Requesting interstitial ad from adapter.");
        try {
            ((MediationInterstitialAdapter)this.qG).requestInterstitialAd(new da<Object, Object>(cv), e.f(d), this.b(s, av.nX, s2), db.d(av), this.qH);
        }
        catch (Throwable t) {
            gs.d("Could not request interstitial ad from adapter.", t);
            throw new RemoteException();
        }
    }
    
    public void a(final d d, final ay ay, final av av, final String s, final cv cv) throws RemoteException {
        this.a(d, ay, av, s, null, cv);
    }
    
    public void a(final d d, final ay ay, final av av, final String s, final String s2, final cv cv) throws RemoteException {
        if (!(this.qG instanceof MediationBannerAdapter)) {
            gs.W("MediationAdapter is not a MediationBannerAdapter: " + this.qG.getClass().getCanonicalName());
            throw new RemoteException();
        }
        gs.S("Requesting banner ad from adapter.");
        try {
            ((MediationBannerAdapter)this.qG).requestBannerAd(new da<Object, Object>(cv), e.f(d), this.b(s, av.nX, s2), db.b(ay), db.d(av), this.qH);
        }
        catch (Throwable t) {
            gs.d("Could not request banner ad from adapter.", t);
            throw new RemoteException();
        }
    }
    
    public void destroy() throws RemoteException {
        try {
            this.qG.destroy();
        }
        catch (Throwable t) {
            gs.d("Could not destroy adapter.", t);
            throw new RemoteException();
        }
    }
    
    public d getView() throws RemoteException {
        if (!(this.qG instanceof MediationBannerAdapter)) {
            gs.W("MediationAdapter is not a MediationBannerAdapter: " + this.qG.getClass().getCanonicalName());
            throw new RemoteException();
        }
        try {
            return e.k(((MediationBannerAdapter)this.qG).getBannerView());
        }
        catch (Throwable t) {
            gs.d("Could not get banner view from adapter.", t);
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
        if (!(this.qG instanceof MediationInterstitialAdapter)) {
            gs.W("MediationAdapter is not a MediationInterstitialAdapter: " + this.qG.getClass().getCanonicalName());
            throw new RemoteException();
        }
        gs.S("Showing interstitial from adapter.");
        try {
            ((MediationInterstitialAdapter)this.qG).showInterstitial();
        }
        catch (Throwable t) {
            gs.d("Could not show interstitial from adapter.", t);
            throw new RemoteException();
        }
    }
}
