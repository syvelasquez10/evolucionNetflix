// 
// Decompiled by Procyon v0.5.30
// 

package com.google.ads.mediation.admob;

import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import android.view.View;
import android.location.Location;
import java.util.Iterator;
import java.util.Set;
import java.util.Date;
import com.google.android.gms.ads.mediation.MediationAdapter;
import android.text.TextUtils;
import com.google.android.gms.internal.gr;
import com.google.android.gms.ads.AdRequest$Builder;
import com.google.android.gms.ads.AdRequest;
import android.os.Bundle;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import android.content.Context;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;

public final class AdMobAdapter implements MediationBannerAdapter, MediationInterstitialAdapter
{
    private AdView i;
    private InterstitialAd j;
    
    static AdRequest a(final Context context, final MediationAdRequest mediationAdRequest, Bundle bundle, final Bundle bundle2) {
        final AdRequest$Builder adRequest$Builder = new AdRequest$Builder();
        final Date birthday = mediationAdRequest.getBirthday();
        if (birthday != null) {
            adRequest$Builder.setBirthday(birthday);
        }
        final int gender = mediationAdRequest.getGender();
        if (gender != 0) {
            adRequest$Builder.setGender(gender);
        }
        final Set<String> keywords = mediationAdRequest.getKeywords();
        if (keywords != null) {
            final Iterator<String> iterator = keywords.iterator();
            while (iterator.hasNext()) {
                adRequest$Builder.addKeyword(iterator.next());
            }
        }
        final Location location = mediationAdRequest.getLocation();
        if (location != null) {
            adRequest$Builder.setLocation(location);
        }
        if (mediationAdRequest.isTesting()) {
            adRequest$Builder.addTestDevice(gr.v(context));
        }
        if (bundle2.getInt("tagForChildDirectedTreatment") != -1) {
            adRequest$Builder.tagForChildDirectedTreatment(bundle2.getInt("tagForChildDirectedTreatment") == 1);
        }
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putInt("gw", 1);
        bundle.putString("mad_hac", bundle2.getString("mad_hac"));
        if (!TextUtils.isEmpty((CharSequence)bundle2.getString("adJson"))) {
            bundle.putString("_ad", bundle2.getString("adJson"));
        }
        bundle.putBoolean("_noRefresh", true);
        adRequest$Builder.addNetworkExtrasBundle(AdMobAdapter.class, bundle);
        return adRequest$Builder.build();
    }
    
    @Override
    public View getBannerView() {
        return (View)this.i;
    }
    
    @Override
    public void onDestroy() {
        if (this.i != null) {
            this.i.destroy();
            this.i = null;
        }
        if (this.j != null) {
            this.j = null;
        }
    }
    
    @Override
    public void onPause() {
        if (this.i != null) {
            this.i.pause();
        }
    }
    
    @Override
    public void onResume() {
        if (this.i != null) {
            this.i.resume();
        }
    }
    
    @Override
    public void requestBannerAd(final Context context, final MediationBannerListener mediationBannerListener, final Bundle bundle, final AdSize adSize, final MediationAdRequest mediationAdRequest, final Bundle bundle2) {
        (this.i = new AdView(context)).setAdSize(new AdSize(adSize.getWidth(), adSize.getHeight()));
        this.i.setAdUnitId(bundle.getString("pubid"));
        this.i.setAdListener(new AdMobAdapter$a(this, mediationBannerListener));
        this.i.loadAd(a(context, mediationAdRequest, bundle2, bundle));
    }
    
    @Override
    public void requestInterstitialAd(final Context context, final MediationInterstitialListener mediationInterstitialListener, final Bundle bundle, final MediationAdRequest mediationAdRequest, final Bundle bundle2) {
        (this.j = new InterstitialAd(context)).setAdUnitId(bundle.getString("pubid"));
        this.j.setAdListener(new AdMobAdapter$b(this, mediationInterstitialListener));
        this.j.loadAd(a(context, mediationAdRequest, bundle2, bundle));
    }
    
    @Override
    public void showInterstitial() {
        this.j.show();
    }
}
