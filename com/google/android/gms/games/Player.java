// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games;

import com.google.android.gms.games.internal.player.MostRecentGameInfo;
import android.net.Uri;
import android.database.CharArrayBuffer;
import com.google.android.gms.common.data.Freezable;
import android.os.Parcelable;

public interface Player extends Parcelable, Freezable<Player>
{
    public static final long CURRENT_XP_UNKNOWN = -1L;
    public static final long TIMESTAMP_UNKNOWN = -1L;
    
    String getDisplayName();
    
    void getDisplayName(final CharArrayBuffer p0);
    
    Uri getHiResImageUri();
    
    @Deprecated
    String getHiResImageUrl();
    
    Uri getIconImageUri();
    
    @Deprecated
    String getIconImageUrl();
    
    long getLastPlayedWithTimestamp();
    
    PlayerLevelInfo getLevelInfo();
    
    String getPlayerId();
    
    long getRetrievedTimestamp();
    
    String getTitle();
    
    void getTitle(final CharArrayBuffer p0);
    
    boolean hasHiResImage();
    
    boolean hasIconImage();
    
    boolean isProfileVisible();
    
    int jR();
    
    MostRecentGameInfo jS();
}
