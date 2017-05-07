// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games;

import android.net.Uri;
import android.database.CharArrayBuffer;
import com.google.android.gms.common.data.Freezable;
import android.os.Parcelable;

public interface Player extends Parcelable, Freezable<Player>
{
    int db();
    
    String getDisplayName();
    
    void getDisplayName(final CharArrayBuffer p0);
    
    Uri getHiResImageUri();
    
    Uri getIconImageUri();
    
    long getLastPlayedWithTimestamp();
    
    String getPlayerId();
    
    long getRetrievedTimestamp();
    
    boolean hasHiResImage();
    
    boolean hasIconImage();
}
