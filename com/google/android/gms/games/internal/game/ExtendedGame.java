// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.game;

import com.google.android.gms.games.snapshot.SnapshotMetadata;
import java.util.ArrayList;
import com.google.android.gms.games.Game;
import com.google.android.gms.common.data.Freezable;
import android.os.Parcelable;

public interface ExtendedGame extends Parcelable, Freezable<ExtendedGame>
{
    Game getGame();
    
    ArrayList<GameBadge> kO();
    
    int kP();
    
    boolean kQ();
    
    int kR();
    
    long kS();
    
    long kT();
    
    String kU();
    
    long kV();
    
    String kW();
    
    SnapshotMetadata kX();
}
