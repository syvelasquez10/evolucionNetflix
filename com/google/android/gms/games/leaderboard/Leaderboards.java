// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.api.PendingResult;
import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;

public interface Leaderboards
{
    Intent getAllLeaderboardsIntent(final GoogleApiClient p0);
    
    Intent getLeaderboardIntent(final GoogleApiClient p0, final String p1);
    
    PendingResult<Leaderboards$LoadPlayerScoreResult> loadCurrentPlayerLeaderboardScore(final GoogleApiClient p0, final String p1, final int p2, final int p3);
    
    PendingResult<Leaderboards$LeaderboardMetadataResult> loadLeaderboardMetadata(final GoogleApiClient p0, final String p1, final boolean p2);
    
    PendingResult<Leaderboards$LeaderboardMetadataResult> loadLeaderboardMetadata(final GoogleApiClient p0, final boolean p1);
    
    PendingResult<Leaderboards$LoadScoresResult> loadMoreScores(final GoogleApiClient p0, final LeaderboardScoreBuffer p1, final int p2, final int p3);
    
    PendingResult<Leaderboards$LoadScoresResult> loadPlayerCenteredScores(final GoogleApiClient p0, final String p1, final int p2, final int p3, final int p4);
    
    PendingResult<Leaderboards$LoadScoresResult> loadPlayerCenteredScores(final GoogleApiClient p0, final String p1, final int p2, final int p3, final int p4, final boolean p5);
    
    PendingResult<Leaderboards$LoadScoresResult> loadTopScores(final GoogleApiClient p0, final String p1, final int p2, final int p3, final int p4);
    
    PendingResult<Leaderboards$LoadScoresResult> loadTopScores(final GoogleApiClient p0, final String p1, final int p2, final int p3, final int p4, final boolean p5);
    
    void submitScore(final GoogleApiClient p0, final String p1, final long p2);
    
    void submitScore(final GoogleApiClient p0, final String p1, final long p2, final String p3);
    
    PendingResult<Leaderboards$SubmitScoreResult> submitScoreImmediate(final GoogleApiClient p0, final String p1, final long p2);
    
    PendingResult<Leaderboards$SubmitScoreResult> submitScoreImmediate(final GoogleApiClient p0, final String p1, final long p2, final String p3);
}
