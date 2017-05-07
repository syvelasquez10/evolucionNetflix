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
    boolean areSnapshotsEnabled();
    
    int getAchievementTotalCount();
    
    String getApplicationId();
    
    String getDescription();
    
    void getDescription(final CharArrayBuffer p0);
    
    String getDeveloperName();
    
    void getDeveloperName(final CharArrayBuffer p0);
    
    String getDisplayName();
    
    void getDisplayName(final CharArrayBuffer p0);
    
    Uri getFeaturedImageUri();
    
    @Deprecated
    String getFeaturedImageUrl();
    
    Uri getHiResImageUri();
    
    @Deprecated
    String getHiResImageUrl();
    
    Uri getIconImageUri();
    
    @Deprecated
    String getIconImageUrl();
    
    int getLeaderboardCount();
    
    String getPrimaryCategory();
    
    String getSecondaryCategory();
    
    String getThemeColor();
    
    boolean isMuted();
    
    boolean isRealTimeMultiplayerEnabled();
    
    boolean isTurnBasedMultiplayerEnabled();
    
    boolean jL();
    
    boolean jM();
    
    boolean jN();
    
    String jO();
    
    int jP();
}
