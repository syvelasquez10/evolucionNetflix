// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.text.TextUtils;

class zzaq
{
    private final long zzOa;
    private final long zzaQf;
    private final long zzaQg;
    private String zzaQh;
    
    zzaq(final long zzaQf, final long zzOa, final long zzaQg) {
        this.zzaQf = zzaQf;
        this.zzOa = zzOa;
        this.zzaQg = zzaQg;
    }
    
    long zzAe() {
        return this.zzaQf;
    }
    
    long zzAf() {
        return this.zzaQg;
    }
    
    String zzAg() {
        return this.zzaQh;
    }
    
    void zzeK(final String zzaQh) {
        if (zzaQh == null || TextUtils.isEmpty((CharSequence)zzaQh.trim())) {
            return;
        }
        this.zzaQh = zzaQh;
    }
}
