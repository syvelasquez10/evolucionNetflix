// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.d$a;

class ct$b
{
    private bz<d$a> aqY;
    private d$a aqt;
    
    public ct$b(final bz<d$a> aqY, final d$a aqt) {
        this.aqY = aqY;
        this.aqt = aqt;
    }
    
    public int getSize() {
        final int qf = this.aqY.getObject().qF();
        int qf2;
        if (this.aqt == null) {
            qf2 = 0;
        }
        else {
            qf2 = this.aqt.qF();
        }
        return qf2 + qf;
    }
    
    public d$a oT() {
        return this.aqt;
    }
    
    public bz<d$a> pn() {
        return this.aqY;
    }
}
