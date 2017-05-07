// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import java.io.IOException;
import java.io.InputStream;
import android.graphics.drawable.Drawable;
import java.util.concurrent.Future;
import java.util.concurrent.CancellationException;
import java.util.concurrent.TimeoutException;
import com.google.android.gms.common.internal.n;
import android.text.TextUtils;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.concurrent.ExecutionException;
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
    private final fz.a tn;
    private List<String> ua;
    
    public fo(final Context mContext, final u pw, final ai ty, final go tx, final fz.a tn) {
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
    
    private bq.a a(final ah ah, final a a, final JSONObject jsonObject) throws ExecutionException, InterruptedException, JSONException {
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
        final bq.a a2 = a.a(this, jsonObject);
        if (a2 == null) {
            gs.T("Failed to retrieve ad assets.");
            return null;
        }
        a2.a(new bq(this.pw, ah, jsonObject));
        return a2;
    }
    
    private fz a(final bq.a a) {
        while (true) {
            while (true) {
                Label_0180: {
                    synchronized (this.mw) {
                        int tc;
                        final int n = tc = this.tc;
                        if (a == null) {
                            tc = n;
                            if (this.tc == -2) {
                                tc = 0;
                            }
                        }
                        // monitorexit(this.mw)
                        if (tc != -2) {
                            final bq.a a2 = null;
                            return new fz(this.tn.vv.tx, null, this.tn.vw.qf, tc, this.tn.vw.qg, this.ua, this.tn.vw.orientation, this.tn.vw.qj, this.tn.vv.tA, false, null, null, null, null, null, 0L, this.tn.lH, this.tn.vw.tH, this.tn.vs, this.tn.vt, this.tn.vw.tN, this.tn.vp, a2);
                        }
                        break Label_0180;
                    }
                }
                continue;
            }
        }
    }
    
    private String[] b(final JSONObject jsonObject, final String s) throws JSONException {
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
    
    private JSONObject c(final ah ah) throws TimeoutException, JSONException {
        if (this.cI()) {
            return null;
        }
        final gk<JSONObject> gk = new gk<JSONObject>();
        ah.a("/nativeAdPreProcess", new by() {
            @Override
            public void a(final gv gv, final Map<String, String> map) {
                ah.g("/nativeAdPreProcess");
                try {
                    final String s = map.get("success");
                    if (!TextUtils.isEmpty((CharSequence)s)) {
                        gk.a(new JSONObject(s).getJSONArray("ads").getJSONObject(0));
                        return;
                    }
                }
                catch (JSONException ex) {
                    gs.b("Malformed native JSON response.", (Throwable)ex);
                }
                fo.this.s(0);
                n.a(fo.this.cI(), (Object)"Unable to set the ad state error!");
                gk.a(null);
            }
        });
        ah.a("google.afma.nativeAds.preProcessJsonGmsg", new JSONObject(this.tn.vw.tG));
        return gk.get();
    }
    
    private ah cH() throws CancellationException, ExecutionException, InterruptedException, TimeoutException {
        if (this.cI()) {
            return null;
        }
        final ah ah = this.tY.a(this.mContext, this.tn.vv.lD, "https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/native_ads.html").get();
        ah.a(this.pw, this.pw, this.pw, this.pw, false, this.pw);
        return ah;
    }
    
    public Future<Drawable> a(JSONObject jsonObject, final String s, final boolean b) throws JSONException {
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
        return this.tX.a(s2, (go.a<Drawable>)new go.a<Drawable>() {
            public Drawable a(final InputStream inputStream) {
                byte[] d;
                while (true) {
                    try {
                        d = jy.d(inputStream);
                        if (d == null) {
                            fo.this.a(2, b);
                            return null;
                        }
                    }
                    catch (IOException ex) {
                        d = null;
                        continue;
                    }
                    break;
                }
                final Bitmap decodeByteArray = BitmapFactory.decodeByteArray(d, 0, d.length);
                if (decodeByteArray == null) {
                    fo.this.a(2, b);
                    return null;
                }
                return (Drawable)new BitmapDrawable(Resources.getSystem(), decodeByteArray);
            }
            
            public Drawable cJ() {
                fo.this.a(2, b);
                return null;
            }
        });
    }
    
    public void a(final int n, final boolean b) {
        if (b) {
            this.s(n);
        }
    }
    
    protected a b(final JSONObject jsonObject) throws JSONException {
        if (this.cI()) {
            return null;
        }
        final String string = jsonObject.getString("template_id");
        if ("2".equals(string)) {
            return (a)new fp();
        }
        if ("1".equals(string)) {
            return (a)new fq();
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
    
    public interface a<T extends bq.a>
    {
        T a(final fo p0, final JSONObject p1) throws JSONException, InterruptedException, ExecutionException;
    }
}
