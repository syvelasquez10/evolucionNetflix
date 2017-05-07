// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games;

import android.net.Uri;
import android.database.CharArrayBuffer;
import com.google.android.gms.common.data.Freezable;
import android.os.Parcelable;

public interface Game extends Parcelable, Freezable<Game>
{
    int getAchievementTotalCount();
    
    String getApplicationId();
    
    String getDescription();
    
    void getDescription(final CharArrayBuffer p0);
    
    String getDeveloperName();
    
    void getDeveloperName(final CharArrayBuffer p0);
    
    String getDisplayName();
    
    void getDisplayName(final CharArrayBuffer p0);
    
    Uri getFeaturedImageUri();
    
    int getGameplayAclStatus();
    
    Uri getHiResImageUri();
    
    Uri getIconImageUri();
    
    String getInstancePackageName();
    
    int getLeaderboardCount();
    
    String getPrimaryCategory();
    
    String getSecondaryCategory();
    
    boolean isInstanceInstalled();
    
    boolean isPlayEnabledGame();
    
    boolean isRealTimeMultiplayerEnabled();
    
    boolean isTurnBasedMultiplayerEnabled();
}
