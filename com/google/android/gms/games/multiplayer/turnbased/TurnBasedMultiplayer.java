// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer.turnbased;

import android.content.Intent;
import com.google.android.gms.games.multiplayer.ParticipantResult;
import java.util.List;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;

public interface TurnBasedMultiplayer
{
    PendingResult<TurnBasedMultiplayer$InitiateMatchResult> acceptInvitation(final GoogleApiClient p0, final String p1);
    
    PendingResult<TurnBasedMultiplayer$CancelMatchResult> cancelMatch(final GoogleApiClient p0, final String p1);
    
    PendingResult<TurnBasedMultiplayer$InitiateMatchResult> createMatch(final GoogleApiClient p0, final TurnBasedMatchConfig p1);
    
    void declineInvitation(final GoogleApiClient p0, final String p1);
    
    void dismissInvitation(final GoogleApiClient p0, final String p1);
    
    void dismissMatch(final GoogleApiClient p0, final String p1);
    
    PendingResult<TurnBasedMultiplayer$UpdateMatchResult> finishMatch(final GoogleApiClient p0, final String p1);
    
    PendingResult<TurnBasedMultiplayer$UpdateMatchResult> finishMatch(final GoogleApiClient p0, final String p1, final byte[] p2, final List<ParticipantResult> p3);
    
    PendingResult<TurnBasedMultiplayer$UpdateMatchResult> finishMatch(final GoogleApiClient p0, final String p1, final byte[] p2, final ParticipantResult... p3);
    
    Intent getInboxIntent(final GoogleApiClient p0);
    
    int getMaxMatchDataSize(final GoogleApiClient p0);
    
    Intent getSelectOpponentsIntent(final GoogleApiClient p0, final int p1, final int p2);
    
    Intent getSelectOpponentsIntent(final GoogleApiClient p0, final int p1, final int p2, final boolean p3);
    
    PendingResult<TurnBasedMultiplayer$LeaveMatchResult> leaveMatch(final GoogleApiClient p0, final String p1);
    
    PendingResult<TurnBasedMultiplayer$LeaveMatchResult> leaveMatchDuringTurn(final GoogleApiClient p0, final String p1, final String p2);
    
    PendingResult<TurnBasedMultiplayer$LoadMatchResult> loadMatch(final GoogleApiClient p0, final String p1);
    
    PendingResult<TurnBasedMultiplayer$LoadMatchesResult> loadMatchesByStatus(final GoogleApiClient p0, final int p1, final int[] p2);
    
    PendingResult<TurnBasedMultiplayer$LoadMatchesResult> loadMatchesByStatus(final GoogleApiClient p0, final int[] p1);
    
    void registerMatchUpdateListener(final GoogleApiClient p0, final OnTurnBasedMatchUpdateReceivedListener p1);
    
    PendingResult<TurnBasedMultiplayer$InitiateMatchResult> rematch(final GoogleApiClient p0, final String p1);
    
    PendingResult<TurnBasedMultiplayer$UpdateMatchResult> takeTurn(final GoogleApiClient p0, final String p1, final byte[] p2, final String p3);
    
    PendingResult<TurnBasedMultiplayer$UpdateMatchResult> takeTurn(final GoogleApiClient p0, final String p1, final byte[] p2, final String p3, final List<ParticipantResult> p4);
    
    PendingResult<TurnBasedMultiplayer$UpdateMatchResult> takeTurn(final GoogleApiClient p0, final String p1, final byte[] p2, final String p3, final ParticipantResult... p4);
    
    void unregisterMatchUpdateListener(final GoogleApiClient p0);
}
