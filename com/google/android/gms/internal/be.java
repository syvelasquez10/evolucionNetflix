// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.ads.mediation.MediationBannerListener;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.dynamic.c;
import android.app.Activity;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.dynamic.b;
import java.util.Iterator;
import com.google.ads.mediation.admob.AdMobServerParameters;
import java.util.Map;
import android.os.RemoteException;
import java.util.HashMap;
import org.json.JSONObject;
import com.google.ads.mediation.MediationAdapter;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.NetworkExtras;

public final class be<NETWORK_EXTRAS extends NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters> extends bc.a
{
    private final MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> gg;
    private final NETWORK_EXTRAS gh;
    
    public be(final MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> gg, final NETWORK_EXTRAS gh) {
        this.gg = gg;
        this.gh = gh;
    }
    
    private SERVER_PARAMETERS a(final String s, final int tagForChildDirectedTreatment, final String adJson) throws RemoteException {
        HashMap<String, String> hashMap;
        if (s != null) {
            try {
                final JSONObject jsonObject = new JSONObject(s);
                hashMap = new HashMap<String, String>(jsonObject.length());
                final Iterator keys = jsonObject.keys();
                while (keys.hasNext()) {
                    final String s2 = keys.next();
                    hashMap.put(s2, jsonObject.getString(s2));
                }
            }
            catch (Throwable t) {
                ct.b("Could not get MediationServerParameters.", t);
                throw new RemoteException();
            }
        }
        else {
            hashMap = new HashMap<String, String>(0);
        }
        final Class<SERVER_PARAMETERS> serverParametersType = this.gg.getServerParametersType();
        MediationServerParameters mediationServerParameters = null;
        if (serverParametersType != null) {
            mediationServerParameters = serverParametersType.newInstance();
            mediationServerParameters.load(hashMap);
        }
        if (mediationServerParameters instanceof AdMobServerParameters) {
            final AdMobServerParameters adMobServerParameters = (AdMobServerParameters)mediationServerParameters;
            adMobServerParameters.adJson = adJson;
            adMobServerParameters.tagForChildDirectedTreatment = tagForChildDirectedTreatment;
            return (SERVER_PARAMETERS)mediationServerParameters;
        }
        return (SERVER_PARAMETERS)mediationServerParameters;
    }
    
    public void a(final b b, final v v, final String s, final bd bd) throws RemoteException {
        this.a(b, v, s, null, bd);
    }
    
    public void a(final b b, final v v, final String s, final String s2, final bd bd) throws RemoteException {
        if (!(this.gg instanceof MediationInterstitialAdapter)) {
            ct.v("MediationAdapter is not a MediationInterstitialAdapter: " + this.gg.getClass().getCanonicalName());
            throw new RemoteException();
        }
        ct.r("Requesting interstitial ad from adapter.");
        try {
            ((MediationInterstitialAdapter)this.gg).requestInterstitialAd(new bf<Object, Object>(bd), c.b(b), this.a(s, v.tagForChildDirectedTreatment, s2), bg.e(v), this.gh);
        }
        catch (Throwable t) {
            ct.b("Could not request interstitial ad from adapter.", t);
            throw new RemoteException();
        }
    }
    
    public void a(final b b, final x x, final v v, final String s, final bd bd) throws RemoteException {
        this.a(b, x, v, s, null, bd);
    }
    
    public void a(final b b, final x x, final v v, final String s, final String s2, final bd bd) throws RemoteException {
        if (!(this.gg instanceof MediationBannerAdapter)) {
            ct.v("MediationAdapter is not a MediationBannerAdapter: " + this.gg.getClass().getCanonicalName());
            throw new RemoteException();
        }
        ct.r("Requesting banner ad from adapter.");
        try {
            ((MediationBannerAdapter)this.gg).requestBannerAd(new bf<Object, Object>(bd), c.b(b), this.a(s, v.tagForChildDirectedTreatment, s2), bg.b(x), bg.e(v), this.gh);
        }
        catch (Throwable t) {
            ct.b("Could not request banner ad from adapter.", t);
            throw new RemoteException();
        }
    }
    
    public void destroy() throws RemoteException {
        try {
            this.gg.destroy();
        }
        catch (Throwable t) {
            ct.b("Could not destroy adapter.", t);
            throw new RemoteException();
        }
    }
    
    public b getView() throws RemoteException {
        if (!(this.gg instanceof MediationBannerAdapter)) {
            ct.v("MediationAdapter is not a MediationBannerAdapter: " + this.gg.getClass().getCanonicalName());
            throw new RemoteException();
        }
        try {
            return c.h(((MediationBannerAdapter)this.gg).getBannerView());
        }
        catch (Throwable t) {
            ct.b("Could not get banner view from adapter.", t);
            throw new RemoteException();
        }
    }
    
    public void showInterstitial() throws RemoteException {
        if (!(this.gg instanceof MediationInterstitialAdapter)) {
            ct.v("MediationAdapter is not a MediationInterstitialAdapter: " + this.gg.getClass().getCanonicalName());
            throw new RemoteException();
        }
        ct.r("Showing interstitial from adapter.");
        try {
            ((MediationInterstitialAdapter)this.gg).showInterstitial();
        }
        catch (Throwable t) {
            ct.b("Could not show interstitial from adapter.", t);
            throw new RemoteException();
        }
    }
}
