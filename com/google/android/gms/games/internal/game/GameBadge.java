// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.game;

import android.net.Uri;
import com.google.android.gms.common.data.Freezable;
import android.os.Parcelable;

public interface GameBadge extends Parcelable, Freezable<GameBadge>
{
    String getDescription();
    
    Uri getIconImageUri();
    
    String getTitle();
    
    int getType();
}
