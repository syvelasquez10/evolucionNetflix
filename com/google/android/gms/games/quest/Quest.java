// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.quest;

import java.util.List;
import com.google.android.gms.games.Game;
import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.common.data.Freezable;
import android.os.Parcelable;

public interface Quest extends Parcelable, Freezable<Quest>
{
    public static final int[] QUEST_STATE_ALL = { 1, 2, 3, 4, 6, 5 };
    public static final String[] QUEST_STATE_NON_TERMINAL = { Integer.toString(1), Integer.toString(2), Integer.toString(3) };
    public static final int STATE_ACCEPTED = 3;
    public static final int STATE_COMPLETED = 4;
    public static final int STATE_EXPIRED = 5;
    public static final int STATE_FAILED = 6;
    public static final int STATE_OPEN = 2;
    public static final int STATE_UPCOMING = 1;
    public static final long UNSET_QUEST_TIMESTAMP = -1L;
    
    long getAcceptedTimestamp();
    
    Uri getBannerImageUri();
    
    @Deprecated
    String getBannerImageUrl();
    
    Milestone getCurrentMilestone();
    
    String getDescription();
    
    void getDescription(final CharArrayBuffer p0);
    
    long getEndTimestamp();
    
    Game getGame();
    
    Uri getIconImageUri();
    
    @Deprecated
    String getIconImageUrl();
    
    long getLastUpdatedTimestamp();
    
    String getName();
    
    void getName(final CharArrayBuffer p0);
    
    String getQuestId();
    
    long getStartTimestamp();
    
    int getState();
    
    int getType();
    
    boolean isEndingSoon();
    
    List<Milestone> lH();
    
    long lI();
}
