// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.achievement;

import android.net.Uri;
import com.google.android.gms.games.Player;
import android.database.CharArrayBuffer;
import com.google.android.gms.common.data.Freezable;
import android.os.Parcelable;

public interface Achievement extends Parcelable, Freezable<Achievement>
{
    public static final int STATE_HIDDEN = 2;
    public static final int STATE_REVEALED = 1;
    public static final int STATE_UNLOCKED = 0;
    public static final int TYPE_INCREMENTAL = 1;
    public static final int TYPE_STANDARD = 0;
    
    String getAchievementId();
    
    int getCurrentSteps();
    
    String getDescription();
    
    void getDescription(final CharArrayBuffer p0);
    
    String getFormattedCurrentSteps();
    
    void getFormattedCurrentSteps(final CharArrayBuffer p0);
    
    String getFormattedTotalSteps();
    
    void getFormattedTotalSteps(final CharArrayBuffer p0);
    
    long getLastUpdatedTimestamp();
    
    String getName();
    
    void getName(final CharArrayBuffer p0);
    
    Player getPlayer();
    
    Uri getRevealedImageUri();
    
    @Deprecated
    String getRevealedImageUrl();
    
    int getState();
    
    int getTotalSteps();
    
    int getType();
    
    Uri getUnlockedImageUri();
    
    @Deprecated
    String getUnlockedImageUrl();
    
    long getXpValue();
}
