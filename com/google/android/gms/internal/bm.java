// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.text.TextUtils;

@ez
public final class bm
{
    private String oU;
    private String oV;
    private String oW;
    
    public bm() {
        this.oU = null;
        this.oV = null;
        this.oW = null;
        this.oU = "https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/sdk-core-v40.html";
        this.oV = null;
        this.oW = null;
    }
    
    public bm(final String ou, final String ov, final String ow) {
        this.oU = null;
        this.oV = null;
        this.oW = null;
        if (TextUtils.isEmpty((CharSequence)ou)) {
            this.oU = "https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/sdk-core-v40.html";
        }
        else {
            this.oU = ou;
        }
        this.oV = ov;
        this.oW = ow;
    }
    
    public String bp() {
        return this.oU;
    }
    
    public String bq() {
        return this.oV;
    }
    
    public String br() {
        return this.oW;
    }
}
