// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Collections;
import org.json.JSONObject;
import java.util.List;

@ez
public final class fz
{
    public final int errorCode;
    public final int orientation;
    public final String qA;
    public final co qB;
    public final List<String> qf;
    public final List<String> qg;
    public final long qj;
    public final cl qy;
    public final cu qz;
    public final gv rN;
    public final String tA;
    public final long tH;
    public final boolean tI;
    public final long tJ;
    public final List<String> tK;
    public final String tN;
    public final av tx;
    public final JSONObject vp;
    public final cm vq;
    public final ay vr;
    public final long vs;
    public final long vt;
    public final bq.a vu;
    
    public fz(final av tx, final gv rn, final List<String> list, final int errorCode, final List<String> list2, final List<String> list3, final int orientation, final long qj, final String ta, final boolean ti, final cl qy, final cu qz, final String qa, final cm vq, final co qb, final long tj, final ay vr, final long th, final long vs, final long vt, final String tn, final JSONObject vp, final bq.a vu) {
        this.tx = tx;
        this.rN = rn;
        List<String> unmodifiableList;
        if (list != null) {
            unmodifiableList = Collections.unmodifiableList((List<? extends String>)list);
        }
        else {
            unmodifiableList = null;
        }
        this.qf = unmodifiableList;
        this.errorCode = errorCode;
        List<String> unmodifiableList2;
        if (list2 != null) {
            unmodifiableList2 = Collections.unmodifiableList((List<? extends String>)list2);
        }
        else {
            unmodifiableList2 = null;
        }
        this.qg = unmodifiableList2;
        List<String> unmodifiableList3;
        if (list3 != null) {
            unmodifiableList3 = Collections.unmodifiableList((List<? extends String>)list3);
        }
        else {
            unmodifiableList3 = null;
        }
        this.tK = unmodifiableList3;
        this.orientation = orientation;
        this.qj = qj;
        this.tA = ta;
        this.tI = ti;
        this.qy = qy;
        this.qz = qz;
        this.qA = qa;
        this.vq = vq;
        this.qB = qb;
        this.tJ = tj;
        this.vr = vr;
        this.tH = th;
        this.vs = vs;
        this.vt = vt;
        this.tN = tn;
        this.vp = vp;
        this.vu = vu;
    }
    
    public fz(final a a, final gv gv, final cl cl, final cu cu, final String s, final co co, final bq.a a2) {
        this(a.vv.tx, gv, a.vw.qf, a.errorCode, a.vw.qg, a.vw.tK, a.vw.orientation, a.vw.qj, a.vv.tA, a.vw.tI, cl, cu, s, a.vq, co, a.vw.tJ, a.lH, a.vw.tH, a.vs, a.vt, a.vw.tN, a.vp, a2);
    }
    
    @ez
    public static final class a
    {
        public final int errorCode;
        public final ay lH;
        public final JSONObject vp;
        public final cm vq;
        public final long vs;
        public final long vt;
        public final fi vv;
        public final fk vw;
        
        public a(final fi vv, final fk vw, final cm vq, final ay lh, final int errorCode, final long vs, final long vt, final JSONObject vp) {
            this.vv = vv;
            this.vw = vw;
            this.vq = vq;
            this.lH = lh;
            this.errorCode = errorCode;
            this.vs = vs;
            this.vt = vt;
            this.vp = vp;
        }
    }
}
