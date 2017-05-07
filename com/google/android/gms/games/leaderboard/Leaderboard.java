// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.leaderboard;

import java.util.ArrayList;
import android.net.Uri;
import com.google.android.gms.games.Game;
import android.database.CharArrayBuffer;
import com.google.android.gms.common.data.Freezable;

public interface Leaderboard extends Freezable<Leaderboard>
{
    public static final int SCORE_ORDER_LARGER_IS_BETTER = 1;
    public static final int SCORE_ORDER_SMALLER_IS_BETTER = 0;
    
    String getDisplayName();
    
    void getDisplayName(final CharArrayBuffer p0);
    
    Game getGame();
    
    Uri getIconImageUri();
    
    @Deprecated
    String getIconImageUrl();
    
    String getLeaderboardId();
    
    int getScoreOrder();
    
    ArrayList<LeaderboardVariant> getVariants();
}
