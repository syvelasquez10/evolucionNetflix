// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.multiplayer.turnbased.OnTurnBasedMatchUpdateReceivedListener;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer$LoadMatchesResult;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer$LoadMatchResult;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer$LeaveMatchResult;
import android.content.Intent;
import java.util.List;
import com.google.android.gms.games.multiplayer.ParticipantResult;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer$UpdateMatchResult;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfig;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer$CancelMatchResult;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer$InitiateMatchResult;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;

public final class TurnBasedMultiplayerImpl implements TurnBasedMultiplayer
{
    @Override
    public PendingResult<TurnBasedMultiplayer$InitiateMatchResult> acceptInvitation(final GoogleApiClient googleApiClient, final String s) {
        return googleApiClient.b((PendingResult<TurnBasedMultiplayer$InitiateMatchResult>)new TurnBasedMultiplayerImpl$3(this, s));
    }
    
    @Override
    public PendingResult<TurnBasedMultiplayer$CancelMatchResult> cancelMatch(final GoogleApiClient googleApiClient, final String s) {
        return googleApiClient.b((PendingResult<TurnBasedMultiplayer$CancelMatchResult>)new TurnBasedMultiplayerImpl$8(this, s, s));
    }
    
    @Override
    public PendingResult<TurnBasedMultiplayer$InitiateMatchResult> createMatch(final GoogleApiClient googleApiClient, final TurnBasedMatchConfig turnBasedMatchConfig) {
        return googleApiClient.b((PendingResult<TurnBasedMultiplayer$InitiateMatchResult>)new TurnBasedMultiplayerImpl$1(this, turnBasedMatchConfig));
    }
    
    @Override
    public void declineInvitation(final GoogleApiClient googleApiClient, final String s) {
        Games.c(googleApiClient).p(s, 1);
    }
    
    @Override
    public void dismissInvitation(final GoogleApiClient googleApiClient, final String s) {
        Games.c(googleApiClient).o(s, 1);
    }
    
    @Override
    public void dismissMatch(final GoogleApiClient googleApiClient, final String s) {
        Games.c(googleApiClient).bv(s);
    }
    
    @Override
    public PendingResult<TurnBasedMultiplayer$UpdateMatchResult> finishMatch(final GoogleApiClient googleApiClient, final String s) {
        return this.finishMatch(googleApiClient, s, null, (ParticipantResult[])null);
    }
    
    @Override
    public PendingResult<TurnBasedMultiplayer$UpdateMatchResult> finishMatch(final GoogleApiClient googleApiClient, final String s, final byte[] array, final List<ParticipantResult> list) {
        ParticipantResult[] array2;
        if (list == null) {
            array2 = null;
        }
        else {
            array2 = list.toArray(new ParticipantResult[list.size()]);
        }
        return this.finishMatch(googleApiClient, s, array, array2);
    }
    
    @Override
    public PendingResult<TurnBasedMultiplayer$UpdateMatchResult> finishMatch(final GoogleApiClient googleApiClient, final String s, final byte[] array, final ParticipantResult... array2) {
        return googleApiClient.b((PendingResult<TurnBasedMultiplayer$UpdateMatchResult>)new TurnBasedMultiplayerImpl$5(this, s, array, array2));
    }
    
    @Override
    public Intent getInboxIntent(final GoogleApiClient googleApiClient) {
        return Games.c(googleApiClient).kd();
    }
    
    @Override
    public int getMaxMatchDataSize(final GoogleApiClient googleApiClient) {
        return Games.c(googleApiClient).kn();
    }
    
    @Override
    public Intent getSelectOpponentsIntent(final GoogleApiClient googleApiClient, final int n, final int n2) {
        return Games.c(googleApiClient).a(n, n2, true);
    }
    
    @Override
    public Intent getSelectOpponentsIntent(final GoogleApiClient googleApiClient, final int n, final int n2, final boolean b) {
        return Games.c(googleApiClient).a(n, n2, b);
    }
    
    @Override
    public PendingResult<TurnBasedMultiplayer$LeaveMatchResult> leaveMatch(final GoogleApiClient googleApiClient, final String s) {
        return googleApiClient.b((PendingResult<TurnBasedMultiplayer$LeaveMatchResult>)new TurnBasedMultiplayerImpl$6(this, s));
    }
    
    @Override
    public PendingResult<TurnBasedMultiplayer$LeaveMatchResult> leaveMatchDuringTurn(final GoogleApiClient googleApiClient, final String s, final String s2) {
        return googleApiClient.b((PendingResult<TurnBasedMultiplayer$LeaveMatchResult>)new TurnBasedMultiplayerImpl$7(this, s, s2));
    }
    
    @Override
    public PendingResult<TurnBasedMultiplayer$LoadMatchResult> loadMatch(final GoogleApiClient googleApiClient, final String s) {
        return googleApiClient.a((PendingResult<TurnBasedMultiplayer$LoadMatchResult>)new TurnBasedMultiplayerImpl$10(this, s));
    }
    
    @Override
    public PendingResult<TurnBasedMultiplayer$LoadMatchesResult> loadMatchesByStatus(final GoogleApiClient googleApiClient, final int n, final int[] array) {
        return googleApiClient.a((PendingResult<TurnBasedMultiplayer$LoadMatchesResult>)new TurnBasedMultiplayerImpl$9(this, n, array));
    }
    
    @Override
    public PendingResult<TurnBasedMultiplayer$LoadMatchesResult> loadMatchesByStatus(final GoogleApiClient googleApiClient, final int[] array) {
        return this.loadMatchesByStatus(googleApiClient, 0, array);
    }
    
    @Override
    public void registerMatchUpdateListener(final GoogleApiClient googleApiClient, final OnTurnBasedMatchUpdateReceivedListener onTurnBasedMatchUpdateReceivedListener) {
        Games.c(googleApiClient).a(onTurnBasedMatchUpdateReceivedListener);
    }
    
    @Override
    public PendingResult<TurnBasedMultiplayer$InitiateMatchResult> rematch(final GoogleApiClient googleApiClient, final String s) {
        return googleApiClient.b((PendingResult<TurnBasedMultiplayer$InitiateMatchResult>)new TurnBasedMultiplayerImpl$2(this, s));
    }
    
    @Override
    public PendingResult<TurnBasedMultiplayer$UpdateMatchResult> takeTurn(final GoogleApiClient googleApiClient, final String s, final byte[] array, final String s2) {
        return this.takeTurn(googleApiClient, s, array, s2, (ParticipantResult[])null);
    }
    
    @Override
    public PendingResult<TurnBasedMultiplayer$UpdateMatchResult> takeTurn(final GoogleApiClient googleApiClient, final String s, final byte[] array, final String s2, final List<ParticipantResult> list) {
        ParticipantResult[] array2;
        if (list == null) {
            array2 = null;
        }
        else {
            array2 = list.toArray(new ParticipantResult[list.size()]);
        }
        return this.takeTurn(googleApiClient, s, array, s2, array2);
    }
    
    @Override
    public PendingResult<TurnBasedMultiplayer$UpdateMatchResult> takeTurn(final GoogleApiClient googleApiClient, final String s, final byte[] array, final String s2, final ParticipantResult... array2) {
        return googleApiClient.b((PendingResult<TurnBasedMultiplayer$UpdateMatchResult>)new TurnBasedMultiplayerImpl$4(this, s, array, s2, array2));
    }
    
    @Override
    public void unregisterMatchUpdateListener(final GoogleApiClient googleApiClient) {
        Games.c(googleApiClient).kg();
    }
}
