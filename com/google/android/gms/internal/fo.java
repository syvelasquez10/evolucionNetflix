// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import org.json.JSONException;
import android.text.TextUtils;
import android.graphics.drawable.Drawable;
import java.util.concurrent.Future;
import org.json.JSONArray;
import java.util.Arrays;
import org.json.JSONObject;
import java.util.List;
import android.content.Context;
import java.util.concurrent.Callable;

@ez
public class fo implements Callable<fz>
{
    private final Context mContext;
    private final Object mw;
    private final u pw;
    private final go tX;
    private final ai tY;
    private boolean tZ;
    private int tc;
    private final fz$a tn;
    private List<String> ua;
    
    public fo(final Context mContext, final u pw, final ai ty, final go tx, final fz$a tn) {
        this.mw = new Object();
        this.mContext = mContext;
        this.pw = pw;
        this.tX = tx;
        this.tY = ty;
        this.tn = tn;
        this.tZ = false;
        this.tc = -2;
        this.ua = null;
    }
    
    private bq$a a(final ah ah, final fo$a fo$a, final JSONObject jsonObject) {
        if (this.cI()) {
            return null;
        }
        final String[] b = this.b(jsonObject.getJSONObject("tracking_urls_and_actions"), "impression_tracking_urls");
        List<String> list;
        if (b == null) {
            list = null;
        }
        else {
            list = Arrays.asList(b);
        }
        this.ua = list;
        final bq$a a = fo$a.a(this, jsonObject);
        if (a == null) {
            gs.T("Failed to retrieve ad assets.");
            return null;
        }
        a.a(new bq(this.pw, ah, jsonObject));
        return a;
    }
    
    private fz a(final bq$a bq$a) {
        while (true) {
            while (true) {
                Label_0180: {
                    synchronized (this.mw) {
                        int tc;
                        final int n = tc = this.tc;
                        if (bq$a == null) {
                            tc = n;
                            if (this.tc == -2) {
                                tc = 0;
                            }
                        }
                        // monitorexit(this.mw)
                        if (tc != -2) {
                            final bq$a bq$a2 = null;
                            return new fz(this.tn.vv.tx, null, this.tn.vw.qf, tc, this.tn.vw.qg, this.ua, this.tn.vw.orientation, this.tn.vw.qj, this.tn.vv.tA, false, null, null, null, null, null, 0L, this.tn.lH, this.tn.vw.tH, this.tn.vs, this.tn.vt, this.tn.vw.tN, this.tn.vp, bq$a2);
                        }
                        break Label_0180;
                    }
                }
                continue;
            }
        }
    }
    
    private String[] b(final JSONObject jsonObject, final String s) {
        final JSONArray optJSONArray = jsonObject.optJSONArray(s);
        if (optJSONArray == null) {
            return null;
        }
        final String[] array = new String[optJSONArray.length()];
        for (int i = 0; i < optJSONArray.length(); ++i) {
            array[i] = optJSONArray.getString(i);
        }
        return array;
    }
    
    private JSONObject c(final ah ah) {
        if (this.cI()) {
            return null;
        }
        final gk<JSONObject> gk = new gk<JSONObject>();
        ah.a("/nativeAdPreProcess", new fo$1(this, ah, gk));
        ah.a("google.afma.nativeAds.preProcessJsonGmsg", new JSONObject(this.tn.vw.tG));
        return gk.get();
    }
    
    private ah cH() {
        if (this.cI()) {
            return null;
        }
        final ah ah = this.tY.a(this.mContext, this.tn.vv.lD, "https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/native_ads.html").get();
        ah.a(this.pw, this.pw, this.pw, this.pw, false, this.pw);
        return ah;
    }
    
    public Future<Drawable> a(JSONObject jsonObject, final String s, final boolean b) {
        if (b) {
            jsonObject = jsonObject.getJSONObject(s);
        }
        else {
            jsonObject = jsonObject.optJSONObject(s);
        }
        JSONObject jsonObject2 = jsonObject;
        if (jsonObject == null) {
            jsonObject2 = new JSONObject();
        }
        String s2;
        if (b) {
            s2 = jsonObject2.getString("url");
        }
        else {
            s2 = jsonObject2.optString("url");
        }
        if (TextUtils.isEmpty((CharSequence)s2)) {
            this.a(0, b);
            return new gl<Drawable>(null);
        }
        return this.tX.a(s2, (go$a<Drawable>)new fo$2(this, b));
    }
    
    public void a(final int n, final boolean b) {
        if (b) {
            this.s(n);
        }
    }
    
    protected fo$a b(final JSONObject jsonObject) {
        if (this.cI()) {
            return null;
        }
        final String string = jsonObject.getString("template_id");
        if ("2".equals(string)) {
            return new fp();
        }
        if ("1".equals(string)) {
            return new fq();
        }
        this.s(0);
        return null;
    }
    
    public fz cG() {
        try {
            final ah ch = this.cH();
            final JSONObject c = this.c(ch);
            return this.a(this.a(ch, this.b(c), c));
        }
        catch (JSONException ex) {
            gs.d("Malformed native JSON response.", (Throwable)ex);
        }
        catch (TimeoutException ex2) {
            gs.d("Timeout when loading native ad.", ex2);
            goto Label_0037;
        }
        catch (InterruptedException ex3) {
            goto Label_0037;
        }
        catch (ExecutionException ex4) {
            goto Label_0037;
        }
        catch (CancellationException ex5) {
            goto Label_0037;
        }
    }
    
    public boolean cI() {
        synchronized (this.mw) {
            return this.tZ;
        }
    }
    
    public void s(final int tc) {
        synchronized (this.mw) {
            this.tZ = true;
            this.tc = tc;
        }
    }
}
