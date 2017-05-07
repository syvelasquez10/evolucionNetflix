// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.events.DriveEvent;
import com.google.android.gms.common.internal.n;
import java.util.ArrayList;
import android.content.Context;
import android.os.Looper;
import java.util.List;
import com.google.android.gms.drive.events.c;

public class y extends ad$a
{
    private final int NS;
    private final c OW;
    private final y$a OX;
    private final List<Integer> OY;
    
    public y(final Looper looper, final Context context, final int ns, final c ow) {
        this.OY = new ArrayList<Integer>();
        this.NS = ns;
        this.OW = ow;
        this.OX = new y$a(looper, context, null);
    }
    
    public void bq(final int n) {
        this.OY.add(n);
    }
    
    public boolean br(final int n) {
        return this.OY.contains(n);
    }
    
    public void c(final OnEventResponse onEventResponse) {
        final DriveEvent ih = onEventResponse.ih();
        n.I(this.NS == ih.getType());
        n.I(this.OY.contains(ih.getType()));
        this.OX.a(this.OW, ih);
    }
}
