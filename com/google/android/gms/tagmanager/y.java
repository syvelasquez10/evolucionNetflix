// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import android.content.Context;

class y implements aq
{
    private static y aoQ;
    private static final Object xz;
    private String aoR;
    private String aoS;
    private ar aoT;
    private cg aoh;
    
    static {
        xz = new Object();
    }
    
    private y(final Context context) {
        this(as.Y(context), new cw());
    }
    
    y(final ar aoT, final cg aoh) {
        this.aoT = aoT;
        this.aoh = aoh;
    }
    
    public static aq W(final Context context) {
        synchronized (y.xz) {
            if (y.aoQ == null) {
                y.aoQ = new y(context);
            }
            return y.aoQ;
        }
    }
    
    @Override
    public boolean cw(final String s) {
        if (!this.aoh.eK()) {
            bh.W("Too many urls sent too quickly with the TagManagerSender, rate limiting invoked.");
            return false;
        }
        String string = s;
        Label_0103: {
            if (this.aoR == null) {
                break Label_0103;
            }
            string = s;
            if (this.aoS == null) {
                break Label_0103;
            }
            try {
                string = this.aoR + "?" + this.aoS + "=" + URLEncoder.encode(s, "UTF-8");
                bh.V("Sending wrapped url hit: " + string);
                this.aoT.cz(string);
                return true;
            }
            catch (UnsupportedEncodingException ex) {
                bh.d("Error wrapping URL for testing.", ex);
                return false;
            }
        }
    }
}
