// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Collections;
import org.json.JSONObject;
import java.util.List;

public final class dh
{
    public final int errorCode;
    public final bl nA;
    public final List<String> ne;
    public final List<String> nf;
    public final long ni;
    public final bi nx;
    public final br ny;
    public final String nz;
    public final dz oj;
    public final int orientation;
    public final ah pg;
    public final String pj;
    public final long pn;
    public final boolean po;
    public final long pp;
    public final List<String> pq;
    public final String pt;
    public final JSONObject qs;
    public final bj qt;
    public final ak qu;
    public final long qv;
    public final long qw;
    
    public dh(final ah pg, final dz oj, final List<String> list, final int errorCode, final List<String> list2, final List<String> list3, final int orientation, final long ni, final String pj, final boolean po, final bi nx, final br ny, final String nz, final bj qt, final bl na, final long pp, final ak qu, final long pn, final long qv, final long qw, final String pt, final JSONObject qs) {
        this.pg = pg;
        this.oj = oj;
        List<String> unmodifiableList;
        if (list != null) {
            unmodifiableList = Collections.unmodifiableList((List<? extends String>)list);
        }
        else {
            unmodifiableList = null;
        }
        this.ne = unmodifiableList;
        this.errorCode = errorCode;
        List<String> unmodifiableList2;
        if (list2 != null) {
            unmodifiableList2 = Collections.unmodifiableList((List<? extends String>)list2);
        }
        else {
            unmodifiableList2 = null;
        }
        this.nf = unmodifiableList2;
        List<String> unmodifiableList3;
        if (list3 != null) {
            unmodifiableList3 = Collections.unmodifiableList((List<? extends String>)list3);
        }
        else {
            unmodifiableList3 = null;
        }
        this.pq = unmodifiableList3;
        this.orientation = orientation;
        this.ni = ni;
        this.pj = pj;
        this.po = po;
        this.nx = nx;
        this.ny = ny;
        this.nz = nz;
        this.qt = qt;
        this.nA = na;
        this.pp = pp;
        this.qu = qu;
        this.pn = pn;
        this.qv = qv;
        this.qw = qw;
        this.pt = pt;
        this.qs = qs;
    }
}
