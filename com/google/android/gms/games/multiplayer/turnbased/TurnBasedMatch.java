// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer.turnbased;

import java.util.ArrayList;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.Game;
import android.os.Bundle;
import com.google.android.gms.games.multiplayer.Participatable;
import com.google.android.gms.common.data.Freezable;
import android.os.Parcelable;

public interface TurnBasedMatch extends Parcelable, Freezable<TurnBasedMatch>, Participatable
{
    public static final int MATCH_STATUS_ACTIVE = 1;
    public static final int MATCH_STATUS_AUTO_MATCHING = 0;
    public static final int MATCH_STATUS_CANCELED = 4;
    public static final int MATCH_STATUS_COMPLETE = 2;
    public static final int MATCH_STATUS_EXPIRED = 3;
    public static final int MATCH_TURN_STATUS_COMPLETE = 3;
    public static final int MATCH_TURN_STATUS_INVITED = 0;
    public static final int MATCH_TURN_STATUS_MY_TURN = 1;
    public static final int MATCH_TURN_STATUS_THEIR_TURN = 2;
    public static final int MATCH_VARIANT_ANY = -1;
    public static final int[] wS = { 0, 1, 2, 3 };
    
    boolean canRematch();
    
    Bundle getAutoMatchCriteria();
    
    int getAvailableAutoMatchSlots();
    
    long getCreationTimestamp();
    
    String getCreatorId();
    
    byte[] getData();
    
    Game getGame();
    
    long getLastUpdatedTimestamp();
    
    String getLastUpdaterId();
    
    String getMatchId();
    
    int getMatchNumber();
    
    Participant getParticipant(final String p0);
    
    String getParticipantId(final String p0);
    
    ArrayList<String> getParticipantIds();
    
    int getParticipantStatus(final String p0);
    
    String getPendingParticipantId();
    
    byte[] getPreviousMatchData();
    
    String getRematchId();
    
    int getStatus();
    
    int getTurnStatus();
    
    int getVariant();
    
    int getVersion();
    
    boolean isLocallyModified();
}
