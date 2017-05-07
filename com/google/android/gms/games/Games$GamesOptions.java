// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games;

import java.util.ArrayList;
import com.google.android.gms.common.api.Api$ApiOptions$Optional;

public final class Games$GamesOptions implements Api$ApiOptions$Optional
{
    public final boolean Vs;
    public final boolean Vt;
    public final int Vu;
    public final boolean Vv;
    public final int Vw;
    public final String Vx;
    public final ArrayList<String> Vy;
    
    private Games$GamesOptions() {
        this.Vs = false;
        this.Vt = true;
        this.Vu = 17;
        this.Vv = false;
        this.Vw = 4368;
        this.Vx = null;
        this.Vy = new ArrayList<String>();
    }
    
    private Games$GamesOptions(final Games$GamesOptions$Builder games$GamesOptions$Builder) {
        this.Vs = games$GamesOptions$Builder.Vs;
        this.Vt = games$GamesOptions$Builder.Vt;
        this.Vu = games$GamesOptions$Builder.Vu;
        this.Vv = games$GamesOptions$Builder.Vv;
        this.Vw = games$GamesOptions$Builder.Vw;
        this.Vx = games$GamesOptions$Builder.Vx;
        this.Vy = games$GamesOptions$Builder.Vy;
    }
    
    public static Games$GamesOptions$Builder builder() {
        return new Games$GamesOptions$Builder(null);
    }
}
