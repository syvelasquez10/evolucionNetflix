// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.pm.PackageInfo;
import android.os.Bundle;
import java.util.List;
import android.content.pm.ApplicationInfo;

@ez
public final class fi$a
{
    public final ApplicationInfo applicationInfo;
    public final String lA;
    public final gt lD;
    public final ay lH;
    public final List<String> lS;
    public final String tA;
    public final String tB;
    public final Bundle tC;
    public final int tD;
    public final Bundle tE;
    public final boolean tF;
    public final Bundle tw;
    public final av tx;
    public final PackageInfo ty;
    
    public fi$a(final Bundle tw, final av tx, final ay lh, final String la, final ApplicationInfo applicationInfo, final PackageInfo ty, final String ta, final String tb, final gt ld, final Bundle tc, final List<String> ls, final Bundle te, final boolean tf) {
        this.tw = tw;
        this.tx = tx;
        this.lH = lh;
        this.lA = la;
        this.applicationInfo = applicationInfo;
        this.ty = ty;
        this.tA = ta;
        this.tB = tb;
        this.lD = ld;
        this.tC = tc;
        this.tF = tf;
        if (ls != null && ls.size() > 0) {
            this.tD = 2;
            this.lS = ls;
        }
        else {
            this.tD = 0;
            this.lS = null;
        }
        this.tE = te;
    }
}
