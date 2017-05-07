// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import android.view.View;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Binder;
import com.google.android.gms.internal.kc;

public class PopupManager
{
    protected GamesClientImpl XO;
    protected PopupManager$PopupLocationInfo XP;
    
    private PopupManager(final GamesClientImpl xo, final int n) {
        this.XO = xo;
        this.dG(n);
    }
    
    public static PopupManager a(final GamesClientImpl gamesClientImpl, final int n) {
        if (kc.hC()) {
            return new PopupManager$PopupManagerHCMR1(gamesClientImpl, n);
        }
        return new PopupManager(gamesClientImpl, n);
    }
    
    protected void dG(final int n) {
        this.XP = new PopupManager$PopupLocationInfo(n, (IBinder)new Binder(), null);
    }
    
    public void kJ() {
        this.XO.a(this.XP.XQ, this.XP.kM());
    }
    
    public Bundle kK() {
        return this.XP.kM();
    }
    
    public IBinder kL() {
        return this.XP.XQ;
    }
    
    public void l(final View view) {
    }
    
    public void setGravity(final int gravity) {
        this.XP.gravity = gravity;
    }
}
