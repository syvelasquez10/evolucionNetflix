// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import android.content.Context;

class y implements aq
{
    private static y XM;
    private static final Object sf;
    private String XN;
    private String XO;
    private ar XP;
    private cf Xa;
    
    static {
        sf = new Object();
    }
    
    private y(final Context context) {
        this(as.H(context), new cv());
    }
    
    y(final ar xp, final cf xa) {
        this.XP = xp;
        this.Xa = xa;
    }
    
    public static aq F(final Context context) {
        synchronized (y.sf) {
            if (y.XM == null) {
                y.XM = new y(context);
            }
            return y.XM;
        }
    }
    
    @Override
    public boolean bz(final String s) {
        if (!this.Xa.cS()) {
            bh.z("Too many urls sent too quickly with the TagManagerSender, rate limiting invoked.");
            return false;
        }
        String string = s;
        Label_0103: {
            if (this.XN == null) {
                break Label_0103;
            }
            string = s;
            if (this.XO == null) {
                break Label_0103;
            }
            try {
                string = this.XN + "?" + this.XO + "=" + URLEncoder.encode(s, "UTF-8");
                bh.y("Sending wrapped url hit: " + string);
                this.XP.bC(string);
                return true;
            }
            catch (UnsupportedEncodingException ex) {
                bh.c("Error wrapping URL for testing.", ex);
                return false;
            }
        }
    }
}
