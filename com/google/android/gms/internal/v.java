// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Bundle;

@ez
public class v
{
    private v$a lZ;
    private boolean ma;
    private boolean mb;
    
    public v() {
        final boolean b = false;
        final Bundle bd = gb.bD();
        boolean mb = b;
        if (bd != null) {
            mb = b;
            if (bd.getBoolean("gads:block_autoclicks", false)) {
                mb = true;
            }
        }
        this.mb = mb;
    }
    
    public v(final boolean mb) {
        this.mb = mb;
    }
    
    public void a(final v$a lz) {
        this.lZ = lz;
    }
    
    public void ar() {
        this.ma = true;
    }
    
    public boolean av() {
        return !this.mb || this.ma;
    }
    
    public void d(final String s) {
        gs.S("Action was blocked because no click was detected.");
        if (this.lZ != null) {
            this.lZ.e(s);
        }
    }
}
