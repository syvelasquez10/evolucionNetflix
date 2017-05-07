// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.Intent;

@ez
public class ee
{
    private final String oA;
    
    public ee(final String oa) {
        this.oA = oa;
    }
    
    public boolean a(final String s, final int n, final Intent intent) {
        if (s != null && intent != null) {
            final String e = ed.e(intent);
            final String f = ed.f(intent);
            if (e != null && f != null) {
                if (!s.equals(ed.D(e))) {
                    gs.W("Developer payload not match.");
                    return false;
                }
                if (this.oA != null && !ef.b(this.oA, e, f)) {
                    gs.W("Fail to verify signature.");
                    return false;
                }
                return true;
            }
        }
        return false;
    }
    
    public String cu() {
        return gj.dp();
    }
}
