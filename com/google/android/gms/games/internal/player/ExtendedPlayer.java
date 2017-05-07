// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.player;

import android.net.Uri;
import com.google.android.gms.games.Player;
import com.google.android.gms.common.data.Freezable;

public interface ExtendedPlayer extends Freezable<ExtendedPlayer>
{
    Player getPlayer();
    
    String hu();
    
    long hv();
    
    Uri hw();
}
