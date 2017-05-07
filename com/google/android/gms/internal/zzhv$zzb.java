// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.graphics.Paint;
import android.view.View;
import java.util.Set;
import android.net.Uri;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.view.Window;
import android.webkit.WebSettings;
import android.content.Context;
import android.app.DownloadManager$Request;

class zzhv$zzb extends zzhv$zza
{
    @Override
    public boolean zza(final DownloadManager$Request downloadManager$Request) {
        downloadManager$Request.allowScanningByMediaScanner();
        downloadManager$Request.setNotificationVisibility(1);
        return true;
    }
    
    @Override
    public boolean zza(final Context context, final WebSettings webSettings) {
        if (context.getCacheDir() != null) {
            webSettings.setAppCachePath(context.getCacheDir().getAbsolutePath());
            webSettings.setAppCacheMaxSize(0L);
            webSettings.setAppCacheEnabled(true);
        }
        webSettings.setDatabasePath(context.getDatabasePath("com.google.android.gms.ads.db").getAbsolutePath());
        webSettings.setDatabaseEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);
        return true;
    }
    
    @Override
    public boolean zza(final Window window) {
        window.setFlags(16777216, 16777216);
        return true;
    }
    
    @Override
    public boolean zza(final WebView webView) {
        webView.onPause();
        return true;
    }
    
    @Override
    public zziq zzb(final zzip zzip, final boolean b) {
        return new zziv(zzip, b);
    }
    
    @Override
    public boolean zzb(final WebView webView) {
        webView.onResume();
        return true;
    }
    
    @Override
    public WebChromeClient zzf(final zzip zzip) {
        return new zziu(zzip);
    }
    
    @Override
    public Set<String> zzf(final Uri uri) {
        return (Set<String>)uri.getQueryParameterNames();
    }
    
    @Override
    public boolean zzk(final View view) {
        view.setLayerType(0, (Paint)null);
        return true;
    }
    
    @Override
    public boolean zzl(final View view) {
        view.setLayerType(1, (Paint)null);
        return true;
    }
}
