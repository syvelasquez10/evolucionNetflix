// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games;

import java.util.ArrayList;

public final class Games$GamesOptions$Builder
{
    boolean Vs;
    boolean Vt;
    int Vu;
    boolean Vv;
    int Vw;
    String Vx;
    ArrayList<String> Vy;
    
    private Games$GamesOptions$Builder() {
        this.Vs = false;
        this.Vt = true;
        this.Vu = 17;
        this.Vv = false;
        this.Vw = 4368;
        this.Vx = null;
        this.Vy = new ArrayList<String>();
    }
    
    public Games$GamesOptions build() {
        return new Games$GamesOptions(this, null);
    }
    
    public Games$GamesOptions$Builder setSdkVariant(final int vw) {
        this.Vw = vw;
        return this;
    }
    
    public Games$GamesOptions$Builder setShowConnectingPopup(final boolean vt) {
        this.Vt = vt;
        this.Vu = 17;
        return this;
    }
    
    public Games$GamesOptions$Builder setShowConnectingPopup(final boolean vt, final int vu) {
        this.Vt = vt;
        this.Vu = vu;
        return this;
    }
}
