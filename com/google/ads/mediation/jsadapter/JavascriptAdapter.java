// 
// Decompiled by Procyon v0.5.30
// 

package com.google.ads.mediation.jsadapter;

import com.google.ads.AdRequest;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.webkit.WebViewClient;
import android.content.Context;
import com.google.ads.mediation.NetworkExtras;
import com.google.ads.mediation.MediationAdRequest;
import com.google.ads.AdSize;
import com.google.ads.mediation.MediationServerParameters;
import android.app.Activity;
import com.google.android.gms.internal.ct;
import android.view.View;
import com.google.ads.mediation.MediationBannerListener;
import android.widget.FrameLayout;
import android.webkit.WebView;
import com.google.ads.mediation.EmptyNetworkExtras;
import com.google.ads.mediation.MediationBannerAdapter;

public final class JavascriptAdapter implements MediationBannerAdapter<EmptyNetworkExtras, JavascriptServerParameters>
{
    private WebView C;
    private FrameLayout D;
    private boolean E;
    private MediationBannerListener k;
    private int v;
    private int w;
    
    @Override
    public void destroy() {
        this.E = true;
    }
    
    @Override
    public Class<EmptyNetworkExtras> getAdditionalParametersType() {
        return EmptyNetworkExtras.class;
    }
    
    @Override
    public View getBannerView() {
        return (View)this.D;
    }
    
    @Override
    public Class<JavascriptServerParameters> getServerParametersType() {
        return JavascriptServerParameters.class;
    }
    
    public WebView getWebView() {
        return this.C;
    }
    
    public int getWebViewHeight() {
        return this.v;
    }
    
    public int getWebViewWidth() {
        return this.w;
    }
    
    public void passbackReceived() {
        ct.r("Passback received");
        this.sendAdNotReceivedUpdate();
    }
    
    @Override
    public void requestBannerAd(final MediationBannerListener k, final Activity activity, final JavascriptServerParameters javascriptServerParameters, final AdSize adSize, final MediationAdRequest mediationAdRequest, final EmptyNetworkExtras emptyNetworkExtras) {
        this.k = k;
        int v;
        if (javascriptServerParameters.height != null) {
            v = javascriptServerParameters.height;
        }
        else {
            v = adSize.getHeightInPixels((Context)activity);
        }
        this.v = v;
        int w;
        if (javascriptServerParameters.width != null) {
            w = javascriptServerParameters.width;
        }
        else {
            w = adSize.getWidthInPixels((Context)activity);
        }
        this.w = w;
        this.E = false;
        this.C = new WebView((Context)activity);
        this.C.getSettings().setJavaScriptEnabled(true);
        this.C.setWebViewClient((WebViewClient)new BannerWebViewClient(this, javascriptServerParameters.passBackUrl));
        this.C.setBackgroundColor(0);
        (this.D = new FrameLayout((Context)activity)).addView((View)this.C, (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(this.w, this.v, 17));
        this.C.loadDataWithBaseURL((String)null, javascriptServerParameters.htmlScript, "text/html", "utf-8", (String)null);
    }
    
    public void sendAdNotReceivedUpdate() {
        if (!this.E) {
            this.E = true;
            this.k.onFailedToReceiveAd(this, AdRequest.ErrorCode.NO_FILL);
        }
    }
    
    public void sendAdReceivedUpdate() {
        if (!this.E) {
            this.E = true;
            this.k.onReceivedAd(this);
        }
    }
    
    public boolean shouldStopAdCheck() {
        return this.E;
    }
    
    public void startCheckingForAd() {
        new AdViewCheckTask(this, 200L, 100L).start();
    }
}
