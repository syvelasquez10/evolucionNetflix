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

@zzgr
public class zzjg extends zzja
{
    public zzjg(final zziz zziz, final boolean b) {
        super(zziz, b);
    }
    
    public WebResourceResponse shouldInterceptRequest(final WebView webView, final String s) {
        try {
            if (!"mraid.js".equalsIgnoreCase(new File(s).getName())) {
                return super.shouldInterceptRequest(webView, s);
            }
            if (!(webView instanceof zziz)) {
                zzb.zzaH("Tried to intercept request from a WebView that wasn't an AdWebView.");
                return super.shouldInterceptRequest(webView, s);
            }
            final zziz zziz = (zziz)webView;
            zziz.zzhe().zzeG();
            String s2;
            if (zziz.zzaN().zztf) {
                s2 = zzby.zzuP.get();
            }
            else if (zziz.zzhi()) {
                s2 = zzby.zzuO.get();
            }
            else {
                s2 = zzby.zzuN.get();
            }
            zzb.v("shouldInterceptRequest(" + s2 + ")");
            return this.zzd(zziz.getContext(), this.zzoM.zzhh().zzJu, s2);
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
    
    protected WebResourceResponse zzd(final Context context, final String s, final String s2) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("User-Agent", zzp.zzbv().zzf(context, s));
        hashMap.put("Cache-Control", "max-stale=3600");
        final String s3 = new zzih(context).zza(s2, hashMap).get(60L, TimeUnit.SECONDS);
        if (s3 == null) {
            return null;
        }
        return new WebResourceResponse("application/javascript", "UTF-8", (InputStream)new ByteArrayInputStream(s3.getBytes("UTF-8")));
    }
}
