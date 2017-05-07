// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.leaderboard;

import android.net.Uri;
import com.google.android.gms.games.Player;
import android.database.CharArrayBuffer;
import com.google.android.gms.common.data.Freezable;

public interface LeaderboardScore extends Freezable<LeaderboardScore>
{
    public static final int LEADERBOARD_RANK_UNKNOWN = -1;
    
    String getDisplayRank();
    
    void getDisplayRank(final CharArrayBuffer p0);
    
    String getDisplayScore();
    
    void getDisplayScore(final CharArrayBuffer p0);
    
    long getRank();
    
    long getRawScore();
    
    Player getScoreHolder();
    
    String getScoreHolderDisplayName();
    
    void getScoreHolderDisplayName(final CharArrayBuffer p0);
    
    Uri getScoreHolderHiResImageUri();
    
    @Deprecated
    String getScoreHolderHiResImageUrl();
    
    Uri getScoreHolderIconImageUri();
    
    @Deprecated
    String getScoreHolderIconImageUrl();
    
    String getScoreTag();
    
    long getTimestampMillis();
}
