// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.game;

import com.google.android.gms.games.Game;
import java.util.ArrayList;
import com.google.android.gms.common.data.Freezable;
import android.os.Parcelable;

public interface ExtendedGame extends Parcelable, Freezable<ExtendedGame>
{
    ArrayList<GameBadge> gW();
    
    int gX();
    
    boolean gY();
    
    int gZ();
    
    Game getGame();
    
    long ha();
    
    long hb();
    
    String hc();
    
    long hd();
    
    String he();
}
