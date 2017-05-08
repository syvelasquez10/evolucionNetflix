// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.security.GeneralSecurityException;
import java.io.IOException;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import android.content.Context;
import java.net.URL;
import android.os.ConditionVariable;

public final class bg extends di implements br
{
    volatile long a;
    ConditionVariable b;
    private long c;
    private ConditionVariable d;
    private ar e;
    private bq f;
    private bq g;
    private bq h;
    private bq i;
    private URL j;
    private Context k;
    private volatile boolean l;
    
    public bg(final Context k, final ar e, final bq f, final bq g, final bq h, final bq i, final URL j) {
        this.c = System.currentTimeMillis();
        this.a = 10000L;
        this.b = new ConditionVariable(false);
        this.d = new ConditionVariable(false);
        this.l = false;
        this.k = k;
        this.f = f;
        this.g = g;
        this.h = h;
        this.i = i;
        this.e = e;
        this.j = j;
        final bq f2 = this.f;
        synchronized (f2.c) {
            f2.c.add(this);
        }
    }
    
    private JSONObject a(final JSONArray jsonArray) {
        final JSONObject jsonObject = new JSONObject();
        try {
            final JSONObject jsonObject2 = new JSONObject();
            jsonObject2.put("appID", (Object)this.e.a());
            jsonObject2.put("deviceID", (Object)this.e.c());
            jsonObject2.put("crPlatform", (Object)"android");
            jsonObject2.put("crVersion", (Object)this.e.d());
            jsonObject2.put("deviceModel", (Object)this.e.j());
            jsonObject2.put("osName", (Object)"android");
            jsonObject2.put("osVersion", (Object)this.e.k());
            jsonObject2.put("carrier", (Object)this.e.f());
            jsonObject2.put("mobileCountryCode", this.e.g());
            jsonObject2.put("mobileNetworkCode", this.e.h());
            jsonObject2.put("appVersion", (Object)this.e.b());
            jsonObject2.put("locale", (Object)new bv$k().a);
            jsonObject.put("appState", (Object)jsonObject2);
            jsonObject.put("transactions", (Object)jsonArray);
            if (b(jsonArray)) {
                jsonObject.put("breadcrumbs", (Object)new bm(this.g).a);
                jsonObject.put("endpoints", (Object)new bm(this.h).a);
                jsonObject.put("systemBreadcrumbs", (Object)new bm(this.i).a);
            }
            return jsonObject;
        }
        catch (JSONException ex) {
            return null;
        }
    }
    
    private static boolean b(final JSONArray jsonArray) {
        final boolean b = false;
        final int n = 0;
        boolean b2 = b;
        if (n >= jsonArray.length()) {
            return b2;
        }
        final JSONArray optJSONArray = jsonArray.optJSONArray(n);
        if (optJSONArray == null) {
            goto Label_0079;
        }
        try {
            final be$a b3 = new be(optJSONArray).b;
            if (b3 != be$a.c && b3 != be$a.i && b3 != be$a.h) {
                b2 = true;
                return b2;
            }
            goto Label_0079;
        }
        catch (JSONException ex) {
            dw.b((Throwable)ex);
        }
        catch (ParseException ex2) {
            dw.b(ex2);
            goto Label_0079;
        }
    }
    
    @Override
    public final void a() {
    Label_0053_Outer:
        while (!this.l) {
            this.b.block();
            this.d.block();
            if (this.l) {
                break;
            }
            final long n = this.a - (System.currentTimeMillis() - this.c);
            while (true) {
                if (n <= 0L) {
                    break Label_0053;
                }
                try {
                    Thread.sleep(n);
                    this.c = System.currentTimeMillis();
                    final bq a = this.f.a(this.k);
                    this.f.a(a);
                    final JSONArray a2 = new bm(a).a;
                    dz.a(a.a);
                    if (a2.length() <= 0 || this.a(a2) == null) {
                        continue Label_0053_Outer;
                    }
                    final JSONObject a3 = this.a(a2);
                    try {
                        final HttpURLConnection a4 = new dc(this.j).a();
                        final OutputStream outputStream = a4.getOutputStream();
                        outputStream.write(a3.toString().getBytes("UTF8"));
                        outputStream.close();
                        a4.getResponseCode();
                        a4.disconnect();
                    }
                    catch (IOException ex) {
                        dw.c("Request failed for " + this.j, ex);
                    }
                    catch (GeneralSecurityException ex2) {
                        dw.c("Request failed for " + this.j, ex2);
                        dw.b(ex2);
                    }
                }
                catch (InterruptedException ex3) {
                    continue;
                }
                break;
            }
        }
    }
    
    @Override
    public final void b() {
        this.d.open();
    }
    
    @Override
    public final void c() {
        this.d.close();
    }
}
