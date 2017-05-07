// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.view.View;
import java.util.LinkedHashSet;
import java.util.Collections;
import java.util.Set;
import android.net.Uri;
import android.webkit.WebChromeClient;
import android.app.Activity;
import android.webkit.WebView;
import android.view.Window;
import android.webkit.WebSettings;
import android.app.DownloadManager$Request;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.view.ViewTreeObserver;
import android.net.http.SslError;
import android.content.Context;

@zzgk
public class zzhv
{
    public static zzhv zzL(final int n) {
        if (n >= 19) {
            return new zzhv$zzg();
        }
        if (n >= 18) {
            return new zzhv$zze();
        }
        if (n >= 17) {
            return new zzhv$zzd();
        }
        if (n >= 16) {
            return new zzhv$zzf();
        }
        if (n >= 14) {
            return new zzhv$zzc();
        }
        if (n >= 11) {
            return new zzhv$zzb();
        }
        if (n >= 9) {
            return new zzhv$zza();
        }
        return new zzhv();
    }
    
    public String getDefaultUserAgent(final Context context) {
        return "";
    }
    
    public String zza(final SslError sslError) {
        return "";
    }
    
    public void zza(final ViewTreeObserver viewTreeObserver, final ViewTreeObserver$OnGlobalLayoutListener viewTreeObserver$OnGlobalLayoutListener) {
        viewTreeObserver.removeGlobalOnLayoutListener(viewTreeObserver$OnGlobalLayoutListener);
    }
    
    public boolean zza(final DownloadManager$Request downloadManager$Request) {
        return false;
    }
    
    public boolean zza(final Context context, final WebSettings webSettings) {
        return false;
    }
    
    public boolean zza(final Window window) {
        return false;
    }
    
    public boolean zza(final WebView webView) {
        return false;
    }
    
    public zziq zzb(final zzip zzip, final boolean b) {
        return new zziq(zzip, b);
    }
    
    public void zzb(final Activity activity, final ViewTreeObserver$OnGlobalLayoutListener viewTreeObserver$OnGlobalLayoutListener) {
        final Window window = activity.getWindow();
        if (window != null && window.getDecorView() != null && window.getDecorView().getViewTreeObserver() != null) {
            this.zza(window.getDecorView().getViewTreeObserver(), viewTreeObserver$OnGlobalLayoutListener);
        }
    }
    
    public boolean zzb(final WebView webView) {
        return false;
    }
    
    public WebChromeClient zzf(final zzip zzip) {
        return null;
    }
    
    public Set<String> zzf(final Uri uri) {
        if (uri.isOpaque()) {
            return Collections.emptySet();
        }
        final String encodedQuery = uri.getEncodedQuery();
        if (encodedQuery == null) {
            return Collections.emptySet();
        }
        final LinkedHashSet<String> set = new LinkedHashSet<String>();
        int n = 0;
        int n2;
        do {
            if ((n2 = encodedQuery.indexOf(38, n)) == -1) {
                n2 = encodedQuery.length();
            }
            final int index = encodedQuery.indexOf(61, n);
            int n3;
            if (index > n2 || (n3 = index) == -1) {
                n3 = n2;
            }
            set.add(Uri.decode(encodedQuery.substring(n, n3)));
        } while ((n = n2 + 1) < encodedQuery.length());
        return (Set<String>)Collections.unmodifiableSet((Set<?>)set);
    }
    
    public int zzgv() {
        return 0;
    }
    
    public int zzgw() {
        return 1;
    }
    
    public int zzgx() {
        return 5;
    }
    
    public boolean zzk(final View view) {
        return false;
    }
    
    public boolean zzl(final View view) {
        return false;
    }
}
