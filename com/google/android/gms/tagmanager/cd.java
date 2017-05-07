// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import android.net.Uri;

class cd
{
    private static cd YP;
    private volatile String WJ;
    private volatile a YQ;
    private volatile String YR;
    private volatile String YS;
    
    cd() {
        this.clear();
    }
    
    private String bI(final String s) {
        return s.split("&")[0].split("=")[1];
    }
    
    private String h(final Uri uri) {
        return uri.getQuery().replace("&gtm_debug=x", "");
    }
    
    static cd kT() {
        synchronized (cd.class) {
            if (cd.YP == null) {
                cd.YP = new cd();
            }
            return cd.YP;
        }
    }
    
    void clear() {
        this.YQ = a.YT;
        this.YR = null;
        this.WJ = null;
        this.YS = null;
    }
    
    boolean g(final Uri uri) {
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
                            bh.y("Container preview url: " + decode);
                            if (decode.matches(".*?&gtm_debug=x$")) {
                                this.YQ = a.YV;
                                this.YS = this.h(uri);
                                if (this.YQ == a.YU || this.YQ == a.YV) {
                                    this.YR = "/r?" + this.YS;
                                }
                                this.WJ = this.bI(this.YS);
                                return b;
                            }
                        }
                        catch (UnsupportedEncodingException ex) {
                            b = false;
                            return b;
                        }
                        this.YQ = a.YU;
                        continue;
                    }
                }
            }
            if (!decode.matches("^tagmanager.c.\\S+:\\/\\/preview\\/p\\?id=\\S+&gtm_preview=$")) {
                bh.z("Invalid preview uri: " + decode);
                b = false;
                return b;
            }
            final Uri uri2;
            if (this.bI(uri2.getQuery()).equals(this.WJ)) {
                bh.y("Exit preview mode for container: " + this.WJ);
                this.YQ = a.YT;
                this.YR = null;
                return b;
            }
            b = false;
            return b;
        }
    }
    
    String getContainerId() {
        return this.WJ;
    }
    
    a kU() {
        return this.YQ;
    }
    
    String kV() {
        return this.YR;
    }
    
    enum a
    {
        YT, 
        YU, 
        YV;
    }
}
