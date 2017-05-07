// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import android.content.Context;

class v extends k<w>
{
    public v(final Context context) {
        super(context, (k.a)new a());
    }
    
    private static class a implements k.a<w>
    {
        private final w uU;
        
        public a() {
            this.uU = new w();
        }
        
        @Override
        public void a(final String s, final int uw) {
            if ("ga_dispatchPeriod".equals(s)) {
                this.uU.uW = uw;
                return;
            }
            aa.z("int configuration name not recognized:  " + s);
        }
        
        @Override
        public void a(final String s, final String s2) {
        }
        
        @Override
        public void b(final String s, final String uv) {
            if ("ga_appName".equals(s)) {
                this.uU.so = uv;
                return;
            }
            if ("ga_appVersion".equals(s)) {
                this.uU.sp = uv;
                return;
            }
            if ("ga_logLevel".equals(s)) {
                this.uU.uV = uv;
                return;
            }
            aa.z("string configuration name not recognized:  " + s);
        }
        
        @Override
        public void c(final String s, final boolean b) {
            if ("ga_dryRun".equals(s)) {
                final w uu = this.uU;
                int ux;
                if (b) {
                    ux = 1;
                }
                else {
                    ux = 0;
                }
                uu.uX = ux;
                return;
            }
            aa.z("bool configuration name not recognized:  " + s);
        }
        
        public w cB() {
            return this.uU;
        }
    }
}
