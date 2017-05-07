// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import android.net.Uri;

class ce
{
    private static ce apS;
    private volatile String anR;
    private volatile a apT;
    private volatile String apU;
    private volatile String apV;
    
    ce() {
        this.clear();
    }
    
    private String cF(final String s) {
        return s.split("&")[0].split("=")[1];
    }
    
    private String j(final Uri uri) {
        return uri.getQuery().replace("&gtm_debug=x", "");
    }
    
    static ce oH() {
        synchronized (ce.class) {
            if (ce.apS == null) {
                ce.apS = new ce();
            }
            return ce.apS;
        }
    }
    
    void clear() {
        this.apT = a.apW;
        this.apU = null;
        this.anR = null;
        this.apV = null;
    }
    
    String getContainerId() {
        return this.anR;
    }
    
    boolean i(final Uri uri) {
        while (true) {
            boolean b = true;
            String decode;
            synchronized (this) {
                while (true) {
                    while (true) {
                        try {
                            decode = URLDecoder.decode(uri.toString(), "UTF-8");
                            if (!decode.matches("^tagmanager.c.\\S+:\\/\\/preview\\/p\\?id=\\S+&gtm_auth=\\S+&gtm_preview=\\d+(&gtm_debug=x)?$")) {
                                break;
                            }
                            bh.V("Container preview url: " + decode);
                            if (decode.matches(".*?&gtm_debug=x$")) {
                                this.apT = a.apY;
                                this.apV = this.j(uri);
                                if (this.apT == a.apX || this.apT == a.apY) {
                                    this.apU = "/r?" + this.apV;
                                }
                                this.anR = this.cF(this.apV);
                                return b;
                            }
                        }
                        catch (UnsupportedEncodingException ex) {
                            b = false;
                            return b;
                        }
                        this.apT = a.apX;
                        continue;
                    }
                }
            }
            if (!decode.matches("^tagmanager.c.\\S+:\\/\\/preview\\/p\\?id=\\S+&gtm_preview=$")) {
                bh.W("Invalid preview uri: " + decode);
                b = false;
                return b;
            }
            final Uri uri2;
            if (this.cF(uri2.getQuery()).equals(this.anR)) {
                bh.V("Exit preview mode for container: " + this.anR);
                this.apT = a.apW;
                this.apU = null;
                return b;
            }
            b = false;
            return b;
        }
    }
    
    a oI() {
        return this.apT;
    }
    
    String oJ() {
        return this.apU;
    }
    
    enum a
    {
        apW, 
        apX, 
        apY;
    }
}
