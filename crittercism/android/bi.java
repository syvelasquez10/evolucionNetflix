// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.util.concurrent.TimeUnit;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.security.GeneralSecurityException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import android.content.Context;
import android.os.ConditionVariable;

public final class bi extends dj implements bt
{
    private long a;
    private volatile long b;
    private ConditionVariable c;
    private ConditionVariable d;
    private au e;
    private bs f;
    private bs g;
    private bs h;
    private bs i;
    private String j;
    private Context k;
    private volatile boolean l;
    
    public bi(final Context k, final au e, final bs f, final bs g, final bs h, final bs i, final String j) {
        this.a = System.currentTimeMillis();
        this.b = 10000L;
        this.c = new ConditionVariable(false);
        this.d = new ConditionVariable(false);
        this.l = false;
        this.k = k;
        this.f = f;
        this.g = g;
        this.h = h;
        this.i = i;
        this.e = e;
        this.j = j;
        final bs f2 = this.f;
        if (this == null) {
            return;
        }
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
            jsonObject2.put("locale", (Object)new bx$k().a);
            jsonObject.put("appState", (Object)jsonObject2);
            jsonObject.put("transactions", (Object)jsonArray);
            if (b(jsonArray)) {
                jsonObject.put("breadcrumbs", (Object)new bo(this.g).a);
                jsonObject.put("endpoints", (Object)new bo(this.h).a);
                jsonObject.put("systemBreadcrumbs", (Object)new bo(this.i).a);
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
            final bg$a k = new bg(optJSONArray).k();
            if (k != bg$a.c && k != bg$a.i && k != bg$a.h) {
                b2 = true;
                return b2;
            }
            goto Label_0079;
        }
        catch (JSONException ex) {
            dy.a((Throwable)ex);
        }
        catch (ParseException ex2) {
            dy.a(ex2);
            goto Label_0079;
        }
    }
    
    @Override
    public final void a() {
    Label_0053_Outer:
        while (!this.l) {
            this.c.block();
            this.d.block();
            if (this.l) {
                break;
            }
            final long n = this.b - (System.currentTimeMillis() - this.a);
            while (true) {
                if (n <= 0L) {
                    break Label_0053;
                }
                try {
                    Thread.sleep(n);
                    this.a = System.currentTimeMillis();
                    final bs a = this.f.a(this.k);
                    this.f.a(a);
                    final JSONArray a2 = new bo(a).a;
                    ec.a(a.a);
                    if (a2.length() <= 0 || this.a(a2) == null) {
                        continue Label_0053_Outer;
                    }
                    final JSONObject a3 = this.a(a2);
                    try {
                        final HttpURLConnection a4 = new dd(new URL(this.j)).a();
                        final OutputStream outputStream = a4.getOutputStream();
                        outputStream.write(a3.toString().getBytes("UTF8"));
                        outputStream.close();
                        a4.getResponseCode();
                        a4.disconnect();
                    }
                    catch (IOException ex2) {
                        new StringBuilder("Request failed for ").append(this.j);
                        dy.a();
                    }
                    catch (GeneralSecurityException ex) {
                        new StringBuilder("Request failed for ").append(this.j);
                        dy.a();
                        dy.a(ex);
                    }
                }
                catch (InterruptedException ex3) {
                    continue;
                }
                break;
            }
        }
    }
    
    public final void a(final int n, final TimeUnit timeUnit) {
        this.b = timeUnit.toMillis(n);
    }
    
    public final void b() {
        this.c.open();
    }
    
    @Override
    public final void c() {
        final bs f = this.f;
        this.d.open();
    }
    
    @Override
    public final void d() {
        final bs f = this.f;
        this.d.close();
    }
}
