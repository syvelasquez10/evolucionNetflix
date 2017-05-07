// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import android.content.Context;

class zzz implements zzar
{
    private static final Object zzaOF;
    private static zzz zzaPU;
    private String zzaPV;
    private String zzaPW;
    private zzas zzaPX;
    private zzcd zzaPi;
    
    static {
        zzaOF = new Object();
    }
    
    private zzz(final Context context) {
        this(zzat.zzaO(context), new zzcs());
    }
    
    zzz(final zzas zzaPX, final zzcd zzaPi) {
        this.zzaPX = zzaPX;
        this.zzaPi = zzaPi;
    }
    
    public static zzar zzaM(final Context context) {
        synchronized (zzz.zzaOF) {
            if (zzz.zzaPU == null) {
                zzz.zzaPU = new zzz(context);
            }
            return zzz.zzaPU;
        }
    }
    
    @Override
    public boolean zzeH(final String s) {
        if (!this.zzaPi.zzkp()) {
            zzbg.zzaE("Too many urls sent too quickly with the TagManagerSender, rate limiting invoked.");
            return false;
        }
        String string = s;
        Label_0103: {
            if (this.zzaPV == null) {
                break Label_0103;
            }
            string = s;
            if (this.zzaPW == null) {
                break Label_0103;
            }
            try {
                string = this.zzaPV + "?" + this.zzaPW + "=" + URLEncoder.encode(s, "UTF-8");
                zzbg.v("Sending wrapped url hit: " + string);
                this.zzaPX.zzeL(string);
                return true;
            }
            catch (UnsupportedEncodingException ex) {
                zzbg.zzd("Error wrapping URL for testing.", ex);
                return false;
            }
        }
    }
}
