// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.a;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import java.util.Set;
import java.util.Date;
import com.google.android.gms.dynamic.e;
import android.content.Context;
import java.util.Collection;
import java.util.HashSet;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.dynamic.d;
import java.util.Iterator;
import com.google.ads.mediation.admob.AdMobAdapter;
import android.os.RemoteException;
import org.json.JSONObject;
import android.os.Bundle;
import com.google.android.gms.ads.mediation.MediationAdapter;

public final class bu extends br.a
{
    private final MediationAdapter nE;
    private final Bundle nF;
    
    public bu(final MediationAdapter ne, final Bundle nf) {
        this.nE = ne;
        this.nF = nf;
    }
    
    private Bundle a(final String s, final int n, final String s2) throws RemoteException {
        dw.z("Server parameters: " + s);
        Bundle bundle;
        try {
            bundle = new Bundle();
            if (s != null) {
                final JSONObject jsonObject = new JSONObject(s);
                bundle = new Bundle();
                final Iterator keys = jsonObject.keys();
                while (keys.hasNext()) {
                    final String s3 = keys.next();
                    bundle.putString(s3, jsonObject.getString(s3));
                }
            }
        }
        catch (Throwable t) {
            dw.c("Could not get Server Parameters Bundle.", t);
            throw new RemoteException();
        }
        if (this.nE instanceof AdMobAdapter) {
            bundle.putString("adJson", s2);
            bundle.putInt("tagForChildDirectedTreatment", n);
        }
        return bundle;
    }
    
    public void a(final d d, final ah ah, final String s, final bs bs) throws RemoteException {
        this.a(d, ah, s, null, bs);
    }
    
    public void a(final d d, final ah ah, final String s, final String s2, final bs bs) throws RemoteException {
        if (!(this.nE instanceof MediationInterstitialAdapter)) {
            dw.z("MediationAdapter is not a MediationInterstitialAdapter: " + this.nE.getClass().getCanonicalName());
            throw new RemoteException();
        }
        dw.v("Requesting interstitial ad from adapter.");
        try {
            final MediationInterstitialAdapter mediationInterstitialAdapter = (MediationInterstitialAdapter)this.nE;
            HashSet<String> set;
            if (ah.lJ != null) {
                set = new HashSet<String>(ah.lJ);
            }
            else {
                set = null;
            }
            mediationInterstitialAdapter.requestInterstitialAd(e.d(d), new bv(bs), this.a(s, ah.lL, s2), new bt(new Date(ah.lH), ah.lI, set, ah.lK, ah.lL), this.nF);
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
        if (!(this.nE instanceof MediationBannerAdapter)) {
            dw.z("MediationAdapter is not a MediationBannerAdapter: " + this.nE.getClass().getCanonicalName());
            throw new RemoteException();
        }
        dw.v("Requesting banner ad from adapter.");
        try {
            final MediationBannerAdapter mediationBannerAdapter = (MediationBannerAdapter)this.nE;
            HashSet<String> set;
            if (ah.lJ != null) {
                set = new HashSet<String>(ah.lJ);
            }
            else {
                set = null;
            }
            mediationBannerAdapter.requestBannerAd(e.d(d), new bv(bs), this.a(s, ah.lL, s2), a.a(ak.width, ak.height, ak.lS), new bt(new Date(ah.lH), ah.lI, set, ah.lK, ah.lL), this.nF);
        }
        catch (Throwable t) {
            dw.c("Could not request banner ad from adapter.", t);
            throw new RemoteException();
        }
    }
    
    public void destroy() throws RemoteException {
        try {
            this.nE.onDestroy();
        }
        catch (Throwable t) {
            dw.c("Could not destroy adapter.", t);
            throw new RemoteException();
        }
    }
    
    public d getView() throws RemoteException {
        if (!(this.nE instanceof MediationBannerAdapter)) {
            dw.z("MediationAdapter is not a MediationBannerAdapter: " + this.nE.getClass().getCanonicalName());
            throw new RemoteException();
        }
        try {
            return e.h(((MediationBannerAdapter)this.nE).getBannerView());
        }
        catch (Throwable t) {
            dw.c("Could not get banner view from adapter.", t);
            throw new RemoteException();
        }
    }
    
    public void pause() throws RemoteException {
        try {
            this.nE.onPause();
        }
        catch (Throwable t) {
            dw.c("Could not pause adapter.", t);
            throw new RemoteException();
        }
    }
    
    public void resume() throws RemoteException {
        try {
            this.nE.onResume();
        }
        catch (Throwable t) {
            dw.c("Could not resume adapter.", t);
            throw new RemoteException();
        }
    }
    
    public void showInterstitial() throws RemoteException {
        if (!(this.nE instanceof MediationInterstitialAdapter)) {
            dw.z("MediationAdapter is not a MediationInterstitialAdapter: " + this.nE.getClass().getCanonicalName());
            throw new RemoteException();
        }
        dw.v("Showing interstitial from adapter.");
        try {
            ((MediationInterstitialAdapter)this.nE).showInterstitial();
        }
        catch (Throwable t) {
            dw.c("Could not show interstitial from adapter.", t);
            throw new RemoteException();
        }
    }
}
