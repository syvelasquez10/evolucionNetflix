// 
// Decompiled by Procyon v0.5.30
// 

package com.google.ads.mediation.admob;

import com.google.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.AdListener;
import com.google.ads.AdSize;
import com.google.ads.mediation.MediationServerParameters;
import android.app.Activity;
import com.google.ads.mediation.MediationBannerListener;
import android.view.View;
import java.util.Iterator;
import java.util.Set;
import java.util.Date;
import com.google.android.gms.ads.mediation.NetworkExtras;
import android.text.TextUtils;
import android.os.Bundle;
import com.google.android.gms.internal.cs;
import com.google.android.gms.internal.bg;
import com.google.android.gms.ads.AdRequest;
import com.google.ads.mediation.MediationAdRequest;
import android.content.Context;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.AdView;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.admob.AdMobExtras;
import com.google.ads.mediation.MediationBannerAdapter;

public final class AdMobAdapter implements MediationBannerAdapter<AdMobExtras, AdMobServerParameters>, MediationInterstitialAdapter<AdMobExtras, AdMobServerParameters>
{
    private AdView h;
    private InterstitialAd i;
    
    private static AdRequest a(final Context context, final MediationAdRequest mediationAdRequest, AdMobExtras adMobExtras, final AdMobServerParameters adMobServerParameters) {
        final AdRequest.Builder builder = new AdRequest.Builder();
        final Date birthday = mediationAdRequest.getBirthday();
        if (birthday != null) {
            builder.setBirthday(birthday);
        }
        final com.google.ads.AdRequest.Gender gender = mediationAdRequest.getGender();
        if (gender != null) {
            builder.setGender(bg.a(gender));
        }
        final Set<String> keywords = mediationAdRequest.getKeywords();
        if (keywords != null) {
            final Iterator<String> iterator = keywords.iterator();
            while (iterator.hasNext()) {
                builder.addKeyword(iterator.next());
            }
        }
        if (mediationAdRequest.isTesting()) {
            builder.addTestDevice(cs.l(context));
        }
        if (adMobServerParameters.tagForChildDirectedTreatment != -1) {
            builder.tagForChildDirectedTreatment(adMobServerParameters.tagForChildDirectedTreatment == 1);
        }
        if (adMobExtras == null) {
            adMobExtras = new AdMobExtras(new Bundle());
        }
        final Bundle extras = adMobExtras.getExtras();
        extras.putInt("gw", 1);
        extras.putString("mad_hac", adMobServerParameters.allowHouseAds);
        if (!TextUtils.isEmpty((CharSequence)adMobServerParameters.adJson)) {
            extras.putString("_ad", adMobServerParameters.adJson);
        }
        extras.putBoolean("_noRefresh", true);
        builder.addNetworkExtras(adMobExtras);
        return builder.build();
    }
    
    @Override
    public void destroy() {
        if (this.h != null) {
            this.h.destroy();
            this.h = null;
        }
        if (this.i != null) {
            this.i = null;
        }
    }
    
    @Override
    public Class<AdMobExtras> getAdditionalParametersType() {
        return AdMobExtras.class;
    }
    
    @Override
    public View getBannerView() {
        return (View)this.h;
    }
    
    @Override
    public Class<AdMobServerParameters> getServerParametersType() {
        return AdMobServerParameters.class;
    }
    
    @Override
    public void requestBannerAd(final MediationBannerListener mediationBannerListener, final Activity activity, final AdMobServerParameters adMobServerParameters, final AdSize adSize, final MediationAdRequest mediationAdRequest, final AdMobExtras adMobExtras) {
        (this.h = new AdView((Context)activity)).setAdSize(new com.google.android.gms.ads.AdSize(adSize.getWidth(), adSize.getHeight()));
        this.h.setAdUnitId(adMobServerParameters.adUnitId);
        this.h.setAdListener(new a(this, mediationBannerListener));
        this.h.loadAd(a((Context)activity, mediationAdRequest, adMobExtras, adMobServerParameters));
    }
    
    @Override
    public void requestInterstitialAd(final MediationInterstitialListener mediationInterstitialListener, final Activity activity, final AdMobServerParameters adMobServerParameters, final MediationAdRequest mediationAdRequest, final AdMobExtras adMobExtras) {
        (this.i = new InterstitialAd((Context)activity)).setAdUnitId(adMobServerParameters.adUnitId);
        this.i.setAdListener(new b(this, mediationInterstitialListener));
        this.i.loadAd(a((Context)activity, mediationAdRequest, adMobExtras, adMobServerParameters));
    }
    
    @Override
    public void showInterstitial() {
        this.i.show();
    }
    
    private static final class a extends AdListener
    {
        private final AdMobAdapter j;
        private final MediationBannerListener k;
        
        public a(final AdMobAdapter j, final MediationBannerListener k) {
            this.j = j;
            this.k = k;
        }
        
        @Override
        public void onAdClosed() {
            this.k.onDismissScreen(this.j);
        }
        
        @Override
        public void onAdFailedToLoad(final int n) {
            this.k.onFailedToReceiveAd(this.j, bg.h(n));
        }
        
        @Override
        public void onAdLeftApplication() {
            this.k.onLeaveApplication(this.j);
        }
        
        @Override
        public void onAdLoaded() {
            this.k.onReceivedAd(this.j);
        }
        
        @Override
        public void onAdOpened() {
            this.k.onClick(this.j);
            this.k.onPresentScreen(this.j);
        }
    }
    
    private static final class b extends AdListener
    {
        private final AdMobAdapter j;
        private final MediationInterstitialListener l;
        
        public b(final AdMobAdapter j, final MediationInterstitialListener l) {
            this.j = j;
            this.l = l;
        }
        
        @Override
        public void onAdClosed() {
            this.l.onDismissScreen(this.j);
        }
        
        @Override
        public void onAdFailedToLoad(final int n) {
            this.l.onFailedToReceiveAd(this.j, bg.h(n));
        }
        
        @Override
        public void onAdLeftApplication() {
            this.l.onLeaveApplication(this.j);
        }
        
        @Override
        public void onAdLoaded() {
            this.l.onReceivedAd(this.j);
        }
        
        @Override
        public void onAdOpened() {
            this.l.onPresentScreen(this.j);
        }
    }
}
