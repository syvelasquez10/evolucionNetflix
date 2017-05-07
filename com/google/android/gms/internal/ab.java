// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import android.graphics.Point;
import android.graphics.Rect;
import android.content.Intent;
import android.content.IntentFilter;
import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Map;
import android.util.DisplayMetrics;
import java.util.concurrent.TimeUnit;
import android.content.BroadcastReceiver;
import android.app.KeyguardManager;
import android.os.PowerManager;
import android.view.WindowManager;
import android.content.Context;
import android.view.View;
import android.view.ViewTreeObserver;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import android.view.ViewTreeObserver$OnScrollChangedListener;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;

public final class ab implements ViewTreeObserver$OnGlobalLayoutListener, ViewTreeObserver$OnScrollChangedListener
{
    private static final long lw;
    private HashSet<y> lA;
    private final Object li;
    private final WeakReference<dh> ll;
    private WeakReference<ViewTreeObserver> lm;
    private final WeakReference<View> ln;
    private final z lo;
    private final Context lp;
    private final ad lq;
    private boolean lr;
    private final WindowManager ls;
    private final PowerManager lt;
    private final KeyguardManager lu;
    private ac lv;
    private long lx;
    private boolean ly;
    private BroadcastReceiver lz;
    
    static {
        lw = TimeUnit.MILLISECONDS.toNanos(100L);
    }
    
    public ab(final ak ak, final dh dh) {
        this(ak, dh, dh.oj.bK(), (View)dh.oj, new ae(dh.oj.getContext(), dh.oj.bK()));
    }
    
    public ab(final ak ak, final dh dh, final dx dx, final View view, final ad lq) {
        this.li = new Object();
        this.lx = Long.MIN_VALUE;
        this.lA = new HashSet<y>();
        this.ll = new WeakReference<dh>(dh);
        this.ln = new WeakReference<View>(view);
        this.lm = new WeakReference<ViewTreeObserver>(null);
        this.ly = true;
        this.lo = new z(Integer.toString(dh.hashCode()), dx, ak.lS, dh.qs);
        this.lq = lq;
        this.ls = (WindowManager)view.getContext().getSystemService("window");
        this.lt = (PowerManager)view.getContext().getApplicationContext().getSystemService("power");
        this.lu = (KeyguardManager)view.getContext().getSystemService("keyguard");
        this.lp = view.getContext().getApplicationContext();
        this.a(lq);
        this.lq.a((ad.a)new ad.a() {
            @Override
            public void ay() {
                ab.this.lr = true;
                ab.this.d(false);
                ab.this.ap();
            }
        });
        this.b(this.lq);
        dw.x("Tracking ad unit: " + this.lo.ao());
    }
    
    protected int a(final int n, final DisplayMetrics displayMetrics) {
        return (int)(n / displayMetrics.density);
    }
    
    public void a(final ac lv) {
        synchronized (this.li) {
            this.lv = lv;
        }
    }
    
    protected void a(final ad ad) {
        ad.d("http://googleads.g.doubleclick.net/mads/static/sdk/native/sdk-core-v40.html");
    }
    
    protected void a(final dz dz, final Map<String, String> map) {
        this.d(false);
    }
    
    public void a(final y y) {
        this.lA.add(y);
    }
    
    protected void a(final JSONObject jsonObject) throws JSONException {
        final JSONArray jsonArray = new JSONArray();
        final JSONObject jsonObject2 = new JSONObject();
        jsonArray.put((Object)jsonObject);
        jsonObject2.put("units", (Object)jsonArray);
        this.lq.a("AFMA_updateActiveView", jsonObject2);
    }
    
    protected boolean a(final View view, final boolean b) {
        return view.getVisibility() == 0 && b && view.isShown() && this.lt.isScreenOn() && !this.lu.inKeyguardRestrictedInputMode();
    }
    
    protected void ap() {
        synchronized (this.li) {
            if (this.lz != null) {
                return;
            }
            final IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            this.lz = new BroadcastReceiver() {
                public void onReceive(final Context context, final Intent intent) {
                    ab.this.d(false);
                }
            };
            this.lp.registerReceiver(this.lz, intentFilter);
        }
    }
    
    protected void aq() {
        synchronized (this.li) {
            if (this.lz != null) {
                this.lp.unregisterReceiver(this.lz);
                this.lz = null;
            }
        }
    }
    
    public void ar() {
        synchronized (this.li) {
            if (!this.ly) {
                return;
            }
            this.av();
            this.aq();
            try {
                this.a(this.ax());
                this.ly = false;
                this.as();
                dw.x("Untracked ad unit: " + this.lo.ao());
            }
            catch (JSONException ex) {
                dw.b("JSON Failure while processing active view data.", (Throwable)ex);
            }
        }
    }
    
    protected void as() {
        if (this.lv != null) {
            this.lv.a(this);
        }
    }
    
    public boolean at() {
        synchronized (this.li) {
            return this.ly;
        }
    }
    
    protected void au() {
        final View view = this.ln.get();
        if (view != null) {
            final ViewTreeObserver viewTreeObserver = this.lm.get();
            final ViewTreeObserver viewTreeObserver2 = view.getViewTreeObserver();
            if (viewTreeObserver2 != viewTreeObserver) {
                this.lm = new WeakReference<ViewTreeObserver>(viewTreeObserver2);
                viewTreeObserver2.addOnScrollChangedListener((ViewTreeObserver$OnScrollChangedListener)this);
                viewTreeObserver2.addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)this);
            }
        }
    }
    
    protected void av() {
        final ViewTreeObserver viewTreeObserver = this.lm.get();
        if (viewTreeObserver == null || !viewTreeObserver.isAlive()) {
            return;
        }
        viewTreeObserver.removeOnScrollChangedListener((ViewTreeObserver$OnScrollChangedListener)this);
        viewTreeObserver.removeGlobalOnLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)this);
    }
    
    protected JSONObject aw() throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("afmaVersion", (Object)this.lo.am()).put("activeViewJSON", (Object)this.lo.an()).put("timestamp", TimeUnit.NANOSECONDS.toMillis(System.nanoTime())).put("adFormat", (Object)this.lo.al()).put("hashCode", (Object)this.lo.ao());
        return jsonObject;
    }
    
    protected JSONObject ax() throws JSONException {
        final JSONObject aw = this.aw();
        aw.put("doneReasonCode", (Object)"u");
        return aw;
    }
    
    protected void b(final ad ad) {
        ad.a("/updateActiveView", new bb() {
            @Override
            public void b(final dz dz, final Map<String, String> map) {
                ab.this.a(dz, map);
            }
        });
        ad.a("/activeViewPingSent", new bb() {
            @Override
            public void b(final dz dz, final Map<String, String> map) {
                if (map.containsKey("pingType") && "unloadPing".equals(map.get("pingType"))) {
                    ab.this.c(ab.this.lq);
                    dw.x("Unregistered GMSG handlers for: " + ab.this.lo.ao());
                }
            }
        });
        ad.a("/visibilityChanged", new bb() {
            @Override
            public void b(final dz dz, final Map<String, String> map) {
                if (!map.containsKey("isVisible")) {
                    return;
                }
                ab.this.c(Boolean.valueOf("1".equals(map.get("isVisible")) || "true".equals(map.get("isVisible"))));
            }
        });
        ad.a("/viewabilityChanged", ba.mG);
    }
    
    protected JSONObject c(final View view) throws JSONException {
        final int[] array = new int[2];
        final int[] array2 = new int[2];
        view.getLocationOnScreen(array);
        view.getLocationInWindow(array2);
        final JSONObject aw = this.aw();
        final DisplayMetrics displayMetrics = view.getContext().getResources().getDisplayMetrics();
        final Rect rect = new Rect();
        rect.left = array[0];
        rect.top = array[1];
        rect.right = rect.left + view.getWidth();
        rect.bottom = rect.top + view.getHeight();
        final Rect rect2 = new Rect();
        rect2.right = this.ls.getDefaultDisplay().getWidth();
        rect2.bottom = this.ls.getDefaultDisplay().getHeight();
        final Rect rect3 = new Rect();
        final boolean globalVisibleRect = view.getGlobalVisibleRect(rect3, (Point)null);
        final Rect rect4 = new Rect();
        view.getLocalVisibleRect(rect4);
        aw.put("viewBox", (Object)new JSONObject().put("top", this.a(rect2.top, displayMetrics)).put("bottom", this.a(rect2.bottom, displayMetrics)).put("left", this.a(rect2.left, displayMetrics)).put("right", this.a(rect2.right, displayMetrics))).put("adBox", (Object)new JSONObject().put("top", this.a(rect.top, displayMetrics)).put("bottom", this.a(rect.bottom, displayMetrics)).put("left", this.a(rect.left, displayMetrics)).put("right", this.a(rect.right, displayMetrics))).put("globalVisibleBox", (Object)new JSONObject().put("top", this.a(rect3.top, displayMetrics)).put("bottom", this.a(rect3.bottom, displayMetrics)).put("left", this.a(rect3.left, displayMetrics)).put("right", this.a(rect3.right, displayMetrics))).put("localVisibleBox", (Object)new JSONObject().put("top", this.a(rect4.top, displayMetrics)).put("bottom", this.a(rect4.bottom, displayMetrics)).put("left", this.a(rect4.left, displayMetrics)).put("right", this.a(rect4.right, displayMetrics))).put("screenDensity", (double)displayMetrics.density).put("isVisible", this.a(view, globalVisibleRect));
        return aw;
    }
    
    protected void c(final ad ad) {
        ad.e("/viewabilityChanged");
        ad.e("/visibilityChanged");
        ad.e("/activeViewPingSent");
        ad.e("/updateActiveView");
    }
    
    protected void c(final boolean b) {
        final Iterator<y> iterator = this.lA.iterator();
        while (iterator.hasNext()) {
            iterator.next().a(this, b);
        }
    }
    
    protected void d(final boolean b) {
        final long nanoTime;
        synchronized (this.li) {
            if (!this.lr || !this.ly) {
                return;
            }
            nanoTime = System.nanoTime();
            if (b && this.lx + ab.lw > nanoTime) {
                return;
            }
        }
        this.lx = nanoTime;
        final dh dh = this.ll.get();
        final View view = this.ln.get();
        while (true) {
            Label_0154: {
                if (view == null) {
                    break Label_0154;
                }
                if (dh == null) {
                    break Label_0154;
                }
                final int n = 0;
                if (n != 0) {
                    this.ar();
                    // monitorexit(o)
                    return;
                }
                while (true) {
                    try {
                        this.a(this.c(view));
                        this.au();
                        this.as();
                        // monitorexit(o)
                        return;
                    }
                    catch (JSONException ex) {
                        dw.b("Active view update failed.", (Throwable)ex);
                        continue;
                    }
                    break;
                }
            }
            final int n = 1;
            continue;
        }
    }
    
    public void onGlobalLayout() {
        this.d(false);
    }
    
    public void onScrollChanged() {
        this.d(true);
    }
}
