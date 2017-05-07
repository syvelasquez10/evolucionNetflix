// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Collections;
import java.util.List;

public final class cj
{
    public final int errorCode;
    public final List<String> fK;
    public final List<String> fL;
    public final long fO;
    public final cw gJ;
    public final at gb;
    public final bc gc;
    public final String gd;
    public final aw ge;
    public final List<String> hA;
    public final v hr;
    public final String hu;
    public final long hx;
    public final boolean hy;
    public final long hz;
    public final au is;
    public final x it;
    public final int orientation;
    
    public cj(final v hr, final cw gj, final List<String> list, final int errorCode, final List<String> list2, final List<String> list3, final int orientation, final long fo, final String hu, final boolean hy, final at gb, final bc gc, final String gd, final au is, final aw ge, final long hz, final x it, final long hx) {
        this.hr = hr;
        this.gJ = gj;
        List<String> unmodifiableList;
        if (list != null) {
            unmodifiableList = Collections.unmodifiableList((List<? extends String>)list);
        }
        else {
            unmodifiableList = null;
        }
        this.fK = unmodifiableList;
        this.errorCode = errorCode;
        List<String> unmodifiableList2;
        if (list2 != null) {
            unmodifiableList2 = Collections.unmodifiableList((List<? extends String>)list2);
        }
        else {
            unmodifiableList2 = null;
        }
        this.fL = unmodifiableList2;
        List<String> unmodifiableList3;
        if (list3 != null) {
            unmodifiableList3 = Collections.unmodifiableList((List<? extends String>)list3);
        }
        else {
            unmodifiableList3 = null;
        }
        this.hA = unmodifiableList3;
        this.orientation = orientation;
        this.fO = fo;
        this.hu = hu;
        this.hy = hy;
        this.gb = gb;
        this.gc = gc;
        this.gd = gd;
        this.is = is;
        this.ge = ge;
        this.hz = hz;
        this.it = it;
        this.hx = hx;
    }
}
