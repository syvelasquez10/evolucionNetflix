// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.leaderboard.Leaderboards$SubmitScoreResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.games.leaderboard.Leaderboards$LoadScoresResult;
import com.google.android.gms.games.leaderboard.LeaderboardScoreBuffer;
import com.google.android.gms.games.leaderboard.Leaderboards$LeaderboardMetadataResult;
import com.google.android.gms.games.leaderboard.Leaderboards$LoadPlayerScoreResult;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.Games;
import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.leaderboard.Leaderboards;

public final class LeaderboardsImpl implements Leaderboards
{
    @Override
    public Intent getAllLeaderboardsIntent(final GoogleApiClient googleApiClient) {
        return Games.c(googleApiClient).kb();
    }
    
    @Override
    public Intent getLeaderboardIntent(final GoogleApiClient googleApiClient, final String s) {
        return Games.c(googleApiClient).bu(s);
    }
    
    @Override
    public PendingResult<Leaderboards$LoadPlayerScoreResult> loadCurrentPlayerLeaderboardScore(final GoogleApiClient googleApiClient, final String s, final int n, final int n2) {
        return googleApiClient.a((PendingResult<Leaderboards$LoadPlayerScoreResult>)new LeaderboardsImpl$3(this, s, n, n2));
    }
    
    @Override
    public PendingResult<Leaderboards$LeaderboardMetadataResult> loadLeaderboardMetadata(final GoogleApiClient googleApiClient, final String s, final boolean b) {
        return googleApiClient.a((PendingResult<Leaderboards$LeaderboardMetadataResult>)new LeaderboardsImpl$2(this, s, b));
    }
    
    @Override
    public PendingResult<Leaderboards$LeaderboardMetadataResult> loadLeaderboardMetadata(final GoogleApiClient googleApiClient, final boolean b) {
        return googleApiClient.a((PendingResult<Leaderboards$LeaderboardMetadataResult>)new LeaderboardsImpl$1(this, b));
    }
    
    @Override
    public PendingResult<Leaderboards$LoadScoresResult> loadMoreScores(final GoogleApiClient googleApiClient, final LeaderboardScoreBuffer leaderboardScoreBuffer, final int n, final int n2) {
        return googleApiClient.a((PendingResult<Leaderboards$LoadScoresResult>)new LeaderboardsImpl$6(this, leaderboardScoreBuffer, n, n2));
    }
    
    @Override
    public PendingResult<Leaderboards$LoadScoresResult> loadPlayerCenteredScores(final GoogleApiClient googleApiClient, final String s, final int n, final int n2, final int n3) {
        return this.loadPlayerCenteredScores(googleApiClient, s, n, n2, n3, false);
    }
    
    @Override
    public PendingResult<Leaderboards$LoadScoresResult> loadPlayerCenteredScores(final GoogleApiClient googleApiClient, final String s, final int n, final int n2, final int n3, final boolean b) {
        return googleApiClient.a((PendingResult<Leaderboards$LoadScoresResult>)new LeaderboardsImpl$5(this, s, n, n2, n3, b));
    }
    
    @Override
    public PendingResult<Leaderboards$LoadScoresResult> loadTopScores(final GoogleApiClient googleApiClient, final String s, final int n, final int n2, final int n3) {
        return this.loadTopScores(googleApiClient, s, n, n2, n3, false);
    }
    
    @Override
    public PendingResult<Leaderboards$LoadScoresResult> loadTopScores(final GoogleApiClient googleApiClient, final String s, final int n, final int n2, final int n3, final boolean b) {
        return googleApiClient.a((PendingResult<Leaderboards$LoadScoresResult>)new LeaderboardsImpl$4(this, s, n, n2, n3, b));
    }
    
    @Override
    public void submitScore(final GoogleApiClient googleApiClient, final String s, final long n) {
        this.submitScore(googleApiClient, s, n, null);
    }
    
    @Override
    public void submitScore(final GoogleApiClient googleApiClient, final String s, final long n, final String s2) {
        Games.c(googleApiClient).a(null, s, n, s2);
    }
    
    @Override
    public PendingResult<Leaderboards$SubmitScoreResult> submitScoreImmediate(final GoogleApiClient googleApiClient, final String s, final long n) {
        return this.submitScoreImmediate(googleApiClient, s, n, null);
    }
    
    @Override
    public PendingResult<Leaderboards$SubmitScoreResult> submitScoreImmediate(final GoogleApiClient googleApiClient, final String s, final long n, final String s2) {
        return googleApiClient.b((PendingResult<Leaderboards$SubmitScoreResult>)new LeaderboardsImpl$7(this, s, n, s2));
    }
}
