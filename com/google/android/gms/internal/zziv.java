// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.util.concurrent.TimeUnit;
import java.util.Map;
import com.google.android.gms.ads.internal.zzp;
import java.util.HashMap;
import android.content.Context;
import java.util.concurrent.ExecutionException;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.io.File;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;

@zzgk
public class zziv extends zziq
{
    public zziv(final zzip zzip, final boolean b) {
        super(zzip, b);
    }
    
    public WebResourceResponse shouldInterceptRequest(final WebView webView, final String s) {
        try {
            if (!"mraid.js".equalsIgnoreCase(new File(s).getName())) {
                return super.shouldInterceptRequest(webView, s);
            }
            if (!(webView instanceof zzip)) {
                zzb.zzaE("Tried to intercept request from a WebView that wasn't an AdWebView.");
                return super.shouldInterceptRequest(webView, s);
            }
            final zzip zzip = (zzip)webView;
            zzip.zzgS().zzeA();
            String s2;
            if (zzip.zzaN().zzsH) {
                s2 = zzby.zzuA.get();
            }
            else if (zzip.zzgW()) {
                s2 = zzby.zzuz.get();
            }
            else {
                s2 = zzby.zzuy.get();
            }
            zzb.v("shouldInterceptRequest(" + s2 + ")");
            return this.zze(zzip.getContext(), this.zzoL.zzgV().zzIz, s2);
        }
        catch (InterruptedException ex) {}
        catch (TimeoutException s2) {
            goto Label_0173;
        }
        catch (IOException s2) {
            goto Label_0173;
        }
        catch (ExecutionException s2) {
            goto Label_0173;
        }
    }
    
    protected WebResourceResponse zze(final Context context, final String s, final String s2) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("User-Agent", zzp.zzbx().zzf(context, s));
        hashMap.put("Cache-Control", "max-stale=3600");
        final String s3 = new zzhy(context).zzb(s2, hashMap).get(60L, TimeUnit.SECONDS);
        if (s3 == null) {
            return null;
        }
        return new WebResourceResponse("application/javascript", "UTF-8", (InputStream)new ByteArrayInputStream(s3.getBytes("UTF-8")));
    }
}
