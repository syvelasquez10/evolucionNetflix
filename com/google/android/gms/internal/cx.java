// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.a;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.dynamic.e;
import android.content.Context;
import java.util.Set;
import java.util.Date;
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

@ez
public final class cx extends cu.a
{
    private final MediationAdapter qE;
    
    public cx(final MediationAdapter qe) {
        this.qE = qe;
    }
    
    private Bundle a(final String s, final int n, final String s2) throws RemoteException {
        gs.W("Server parameters: " + s);
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
            gs.d("Could not get Server Parameters Bundle.", t);
            throw new RemoteException();
        }
        if (this.qE instanceof AdMobAdapter) {
            bundle.putString("adJson", s2);
            bundle.putInt("tagForChildDirectedTreatment", n);
        }
        return bundle;
    }
    
    public void a(final d d, final av av, final String s, final cv cv) throws RemoteException {
        this.a(d, av, s, null, cv);
    }
    
    public void a(final d d, final av av, final String s, final String s2, final cv cv) throws RemoteException {
        if (!(this.qE instanceof MediationInterstitialAdapter)) {
            gs.W("MediationAdapter is not a MediationInterstitialAdapter: " + this.qE.getClass().getCanonicalName());
            throw new RemoteException();
        }
        gs.S("Requesting interstitial ad from adapter.");
        try {
            final MediationInterstitialAdapter mediationInterstitialAdapter = (MediationInterstitialAdapter)this.qE;
            HashSet<String> set;
            if (av.nV != null) {
                set = new HashSet<String>(av.nV);
            }
            else {
                set = null;
            }
            final cw cw = new cw(new Date(av.nT), av.nU, set, av.ob, av.nW, av.nX);
            Bundle bundle;
            if (av.od != null) {
                bundle = av.od.getBundle(mediationInterstitialAdapter.getClass().getName());
            }
            else {
                bundle = null;
            }
            mediationInterstitialAdapter.requestInterstitialAd(e.f(d), new cy(cv), this.a(s, av.nX, s2), cw, bundle);
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
        final Bundle bundle = null;
        if (!(this.qE instanceof MediationBannerAdapter)) {
            gs.W("MediationAdapter is not a MediationBannerAdapter: " + this.qE.getClass().getCanonicalName());
            throw new RemoteException();
        }
        gs.S("Requesting banner ad from adapter.");
        try {
            final MediationBannerAdapter mediationBannerAdapter = (MediationBannerAdapter)this.qE;
            HashSet<String> set;
            if (av.nV != null) {
                set = new HashSet<String>(av.nV);
            }
            else {
                set = null;
            }
            final cw cw = new cw(new Date(av.nT), av.nU, set, av.ob, av.nW, av.nX);
            Bundle bundle2 = bundle;
            if (av.od != null) {
                bundle2 = av.od.getBundle(mediationBannerAdapter.getClass().getName());
            }
            mediationBannerAdapter.requestBannerAd(e.f(d), new cy(cv), this.a(s, av.nX, s2), a.a(ay.width, ay.height, ay.of), cw, bundle2);
        }
        catch (Throwable t) {
            gs.d("Could not request banner ad from adapter.", t);
            throw new RemoteException();
        }
    }
    
    public void destroy() throws RemoteException {
        try {
            this.qE.onDestroy();
        }
        catch (Throwable t) {
            gs.d("Could not destroy adapter.", t);
            throw new RemoteException();
        }
    }
    
    public d getView() throws RemoteException {
        if (!(this.qE instanceof MediationBannerAdapter)) {
            gs.W("MediationAdapter is not a MediationBannerAdapter: " + this.qE.getClass().getCanonicalName());
            throw new RemoteException();
        }
        try {
            return e.k(((MediationBannerAdapter)this.qE).getBannerView());
        }
        catch (Throwable t) {
            gs.d("Could not get banner view from adapter.", t);
            throw new RemoteException();
        }
    }
    
    public void pause() throws RemoteException {
        try {
            this.qE.onPause();
        }
        catch (Throwable t) {
            gs.d("Could not pause adapter.", t);
            throw new RemoteException();
        }
    }
    
    public void resume() throws RemoteException {
        try {
            this.qE.onResume();
        }
        catch (Throwable t) {
            gs.d("Could not resume adapter.", t);
            throw new RemoteException();
        }
    }
    
    public void showInterstitial() throws RemoteException {
        if (!(this.qE instanceof MediationInterstitialAdapter)) {
            gs.W("MediationAdapter is not a MediationInterstitialAdapter: " + this.qE.getClass().getCanonicalName());
            throw new RemoteException();
        }
        gs.S("Showing interstitial from adapter.");
        try {
            ((MediationInterstitialAdapter)this.qE).showInterstitial();
        }
        catch (Throwable t) {
            gs.d("Could not show interstitial from adapter.", t);
            throw new RemoteException();
        }
    }
}
