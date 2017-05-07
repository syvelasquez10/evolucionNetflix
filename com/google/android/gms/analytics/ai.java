// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import android.content.Context;

class ai extends k<aj>
{
    public ai(final Context context) {
        super(context, (k.a)new a());
    }
    
    private static class a implements k.a<aj>
    {
        private final aj wg;
        
        public a() {
            this.wg = new aj();
        }
        
        @Override
        public void a(final String s, final int wj) {
            if ("ga_sessionTimeout".equals(s)) {
                this.wg.wj = wj;
                return;
            }
            aa.z("int configuration name not recognized:  " + s);
        }
        
        @Override
        public void a(final String s, final String s2) {
            this.wg.wn.put(s, s2);
        }
        
        @Override
        public void b(final String s, final String wh) {
            if ("ga_trackingId".equals(s)) {
                this.wg.wh = wh;
                return;
            }
            if ("ga_sampleFrequency".equals(s)) {
                try {
                    this.wg.wi = Double.parseDouble(wh);
                    return;
                }
                catch (NumberFormatException ex) {
                    aa.w("Error parsing ga_sampleFrequency value: " + wh);
                    return;
                }
            }
            aa.z("string configuration name not recognized:  " + s);
        }
        
        @Override
        public void c(final String s, final boolean b) {
            final boolean b2 = true;
            final boolean b3 = true;
            int wk = 1;
            if ("ga_autoActivityTracking".equals(s)) {
                final aj wg = this.wg;
                if (!b) {
                    wk = 0;
                }
                wg.wk = wk;
                return;
            }
            if ("ga_anonymizeIp".equals(s)) {
                this.wg.wl = ((b && b2) ? 1 : 0);
                return;
            }
            if ("ga_reportUncaughtExceptions".equals(s)) {
                this.wg.wm = ((b && b3) ? 1 : 0);
                return;
            }
            aa.z("bool configuration name not recognized:  " + s);
        }
        
        public aj di() {
            return this.wg;
        }
    }
}
