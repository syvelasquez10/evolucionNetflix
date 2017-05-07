// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import android.content.Context;

class ah extends j<ai>
{
    public ah(final Context context) {
        super(context, (j.a)new a());
    }
    
    private static class a implements j.a<ai>
    {
        private final ai BB;
        
        public a() {
            this.BB = new ai();
        }
        
        @Override
        public void c(final String s, final int be) {
            if ("ga_sessionTimeout".equals(s)) {
                this.BB.BE = be;
                return;
            }
            z.W("int configuration name not recognized:  " + s);
        }
        
        @Override
        public void d(final String s, final boolean b) {
            final boolean b2 = true;
            final boolean b3 = true;
            int bf = 1;
            if ("ga_autoActivityTracking".equals(s)) {
                final ai bb = this.BB;
                if (!b) {
                    bf = 0;
                }
                bb.BF = bf;
                return;
            }
            if ("ga_anonymizeIp".equals(s)) {
                this.BB.BG = ((b && b2) ? 1 : 0);
                return;
            }
            if ("ga_reportUncaughtExceptions".equals(s)) {
                this.BB.BH = ((b && b3) ? 1 : 0);
                return;
            }
            z.W("bool configuration name not recognized:  " + s);
        }
        
        public ai eZ() {
            return this.BB;
        }
        
        @Override
        public void f(final String s, final String s2) {
            this.BB.BI.put(s, s2);
        }
        
        @Override
        public void g(final String s, final String bc) {
            if ("ga_trackingId".equals(s)) {
                this.BB.BC = bc;
                return;
            }
            if ("ga_sampleFrequency".equals(s)) {
                try {
                    this.BB.BD = Double.parseDouble(bc);
                    return;
                }
                catch (NumberFormatException ex) {
                    z.T("Error parsing ga_sampleFrequency value: " + bc);
                    return;
                }
            }
            z.W("string configuration name not recognized:  " + s);
        }
    }
}
