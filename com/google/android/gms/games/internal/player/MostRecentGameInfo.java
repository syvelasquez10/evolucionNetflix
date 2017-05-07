// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.player;

import android.net.Uri;
import com.google.android.gms.common.data.Freezable;
import android.os.Parcelable;

public interface MostRecentGameInfo extends Parcelable, Freezable<MostRecentGameInfo>
{
    String ln();
    
    String lo();
    
    long lp();
    
    Uri lq();
    
    Uri lr();
    
    Uri ls();
}
