// 
// Decompiled by Procyon v0.5.30
// 

package com.google.ads.mediation.jsadapter;

import android.graphics.Canvas;
import android.view.View$MeasureSpec;
import android.graphics.Bitmap$Config;
import com.google.android.gms.internal.ct;
import android.graphics.Bitmap;
import android.webkit.WebView;
import android.os.AsyncTask;
import android.os.Looper;
import android.os.Handler;

public final class AdViewCheckTask implements Runnable
{
    public static final int BACKGROUND_COLOR = 0;
    private final JavascriptAdapter r;
    private final Handler s;
    private final long t;
    private long u;
    
    public AdViewCheckTask(final JavascriptAdapter r, final long t, final long u) {
        this.r = r;
        this.t = t;
        this.u = u;
        this.s = new Handler(Looper.getMainLooper());
    }
    
    @Override
    public void run() {
        if (this.r == null || this.r.shouldStopAdCheck()) {
            return;
        }
        new a(this.r.getWebViewWidth(), this.r.getWebViewHeight(), this.r.getWebView()).execute((Object[])new Void[0]);
    }
    
    public void start() {
        this.s.postDelayed((Runnable)this, this.t);
    }
    
    private final class a extends AsyncTask<Void, Void, Boolean>
    {
        private final int v;
        private final int w;
        private final WebView x;
        private Bitmap y;
        
        public a(final int w, final int v, final WebView x) {
            this.v = v;
            this.w = w;
            this.x = x;
        }
        
        protected Boolean a(final Void... array) {
            while (true) {
            Label_0052_Outer:
                while (true) {
                    int n = 0;
                Label_0139:
                    while (true) {
                        int n3 = 0;
                        int n4 = 0;
                        Label_0126: {
                            synchronized (this) {
                                final int width = this.y.getWidth();
                                final int height = this.y.getHeight();
                                Boolean b;
                                if (width == 0 || height == 0) {
                                    b = false;
                                }
                                else {
                                    n = 0;
                                    final int n2 = 0;
                                    if (n < width) {
                                        n3 = 0;
                                        if (n3 >= height) {
                                            break Label_0139;
                                        }
                                        n4 = n2;
                                        if (this.y.getPixel(n, n3) != 0) {
                                            n4 = n2 + 1;
                                        }
                                        break Label_0126;
                                    }
                                    else {
                                        b = (n2 / (width * height / 100.0) > 0.1);
                                    }
                                }
                                return b;
                            }
                        }
                        n3 += 10;
                        final int n2 = n4;
                        continue;
                    }
                    n += 10;
                    continue Label_0052_Outer;
                }
            }
        }
        
        protected void a(final Boolean b) {
            --AdViewCheckTask.this.u;
            if (b) {
                AdViewCheckTask.this.r.sendAdReceivedUpdate();
                return;
            }
            if (AdViewCheckTask.this.u > 0L) {
                if (ct.n(2)) {
                    ct.r("Ad not detected, scheduling another run.");
                }
                AdViewCheckTask.this.s.postDelayed((Runnable)AdViewCheckTask.this, AdViewCheckTask.this.t);
                return;
            }
            ct.r("Ad not detected, Not scheduling anymore runs.");
            AdViewCheckTask.this.r.sendAdNotReceivedUpdate();
        }
        
        protected void onPreExecute() {
            synchronized (this) {
                this.y = Bitmap.createBitmap(this.w, this.v, Bitmap$Config.ARGB_8888);
                this.x.setVisibility(0);
                this.x.measure(View$MeasureSpec.makeMeasureSpec(this.w, 0), View$MeasureSpec.makeMeasureSpec(this.v, 0));
                this.x.layout(0, 0, this.w, this.v);
                this.x.draw(new Canvas(this.y));
                this.x.invalidate();
            }
        }
    }
}
