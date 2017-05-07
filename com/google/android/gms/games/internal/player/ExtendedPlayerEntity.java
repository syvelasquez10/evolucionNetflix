// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.player;

import com.google.android.gms.internal.fo;
import com.google.android.gms.games.Player;
import android.net.Uri;
import com.google.android.gms.games.PlayerEntity;

public final class ExtendedPlayerEntity implements ExtendedPlayer
{
    private final PlayerEntity LH;
    private final String LI;
    private final long LJ;
    private final Uri LK;
    
    public ExtendedPlayerEntity(final ExtendedPlayer extendedPlayer) {
        final Player player = extendedPlayer.getPlayer();
        PlayerEntity lh;
        if (player == null) {
            lh = null;
        }
        else {
            lh = new PlayerEntity(player);
        }
        this.LH = lh;
        this.LI = extendedPlayer.hu();
        this.LJ = extendedPlayer.hv();
        this.LK = extendedPlayer.hw();
    }
    
    static int a(final ExtendedPlayer extendedPlayer) {
        return fo.hashCode(extendedPlayer.getPlayer(), extendedPlayer.hu(), extendedPlayer.hv(), extendedPlayer.hw());
    }
    
    static boolean a(final ExtendedPlayer extendedPlayer, final Object o) {
        final boolean b = true;
        boolean b2;
        if (!(o instanceof ExtendedPlayer)) {
            b2 = false;
        }
        else {
            b2 = b;
            if (extendedPlayer != o) {
                final ExtendedPlayer extendedPlayer2 = (ExtendedPlayer)o;
                if (fo.equal(extendedPlayer2.getPlayer(), extendedPlayer.getPlayer()) && fo.equal(extendedPlayer2.hu(), extendedPlayer.hu()) && fo.equal(extendedPlayer2.hv(), extendedPlayer.hv())) {
                    b2 = b;
                    if (fo.equal(extendedPlayer2.hw(), extendedPlayer.hw())) {
                        return b2;
                    }
                }
                return false;
            }
        }
        return b2;
    }
    
    static String b(final ExtendedPlayer extendedPlayer) {
        return fo.e(extendedPlayer).a("Player", extendedPlayer.getPlayer()).a("MostRecentGameId", extendedPlayer.hu()).a("MostRecentActivityTimestamp", extendedPlayer.hv()).a("MostRecentGameIconImageUri", extendedPlayer.hw()).toString();
    }
    
    @Override
    public boolean equals(final Object o) {
        return a(this, o);
    }
    
    @Override
    public int hashCode() {
        return a(this);
    }
    
    @Override
    public String hu() {
        return this.LI;
    }
    
    @Override
    public long hv() {
        return this.LJ;
    }
    
    @Override
    public Uri hw() {
        return this.LK;
    }
    
    public PlayerEntity hx() {
        return this.LH;
    }
    
    public ExtendedPlayer hy() {
        return this;
    }
    
    @Override
    public boolean isDataValid() {
        return true;
    }
    
    @Override
    public String toString() {
        return b(this);
    }
}
