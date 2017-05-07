// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.text.TextUtils;
import android.webkit.WebViewClient;
import android.os.Looper;
import android.os.Handler;
import android.graphics.Canvas;
import android.view.View$MeasureSpec;
import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;
import android.webkit.WebView;
import android.os.AsyncTask;

public final class fc$a extends AsyncTask<Void, Void, Boolean>
{
    private final WebView tj;
    private Bitmap tk;
    final /* synthetic */ fc tl;
    
    public fc$a(final fc tl, final WebView tj) {
        this.tl = tl;
        this.tj = tj;
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
                            final int width = this.tk.getWidth();
                            final int height = this.tk.getHeight();
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
                                    if (this.tk.getPixel(n, n3) != 0) {
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
        --this.tl.tf;
        if (b || this.tl.cB() || this.tl.tf <= 0L) {
            this.tl.ti = b;
            this.tl.tg.a(this.tl.md);
        }
        else if (this.tl.tf > 0L) {
            if (gs.u(2)) {
                gs.S("Ad not detected, scheduling another run.");
            }
            this.tl.td.postDelayed((Runnable)this.tl, this.tl.te);
        }
    }
    
    protected void onPreExecute() {
        synchronized (this) {
            this.tk = Bitmap.createBitmap(this.tl.lf, this.tl.lg, Bitmap$Config.ARGB_8888);
            this.tj.setVisibility(0);
            this.tj.measure(View$MeasureSpec.makeMeasureSpec(this.tl.lf, 0), View$MeasureSpec.makeMeasureSpec(this.tl.lg, 0));
            this.tj.layout(0, 0, this.tl.lf, this.tl.lg);
            this.tj.draw(new Canvas(this.tk));
            this.tj.invalidate();
        }
    }
}
