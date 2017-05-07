// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.PendingResult;
import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;

public interface Leaderboards
{
    Intent getAllLeaderboardsIntent(final GoogleApiClient p0);
    
    Intent getLeaderboardIntent(final GoogleApiClient p0, final String p1);
    
    PendingResult<LoadPlayerScoreResult> loadCurrentPlayerLeaderboardScore(final GoogleApiClient p0, final String p1, final int p2, final int p3);
    
    PendingResult<LeaderboardMetadataResult> loadLeaderboardMetadata(final GoogleApiClient p0, final String p1, final boolean p2);
    
    PendingResult<LeaderboardMetadataResult> loadLeaderboardMetadata(final GoogleApiClient p0, final boolean p1);
    
    PendingResult<LoadScoresResult> loadMoreScores(final GoogleApiClient p0, final LeaderboardScoreBuffer p1, final int p2, final int p3);
    
    PendingResult<LoadScoresResult> loadPlayerCenteredScores(final GoogleApiClient p0, final String p1, final int p2, final int p3, final int p4);
    
    PendingResult<LoadScoresResult> loadPlayerCenteredScores(final GoogleApiClient p0, final String p1, final int p2, final int p3, final int p4, final boolean p5);
    
    PendingResult<LoadScoresResult> loadTopScores(final GoogleApiClient p0, final String p1, final int p2, final int p3, final int p4);
    
    PendingResult<LoadScoresResult> loadTopScores(final GoogleApiClient p0, final String p1, final int p2, final int p3, final int p4, final boolean p5);
    
    void submitScore(final GoogleApiClient p0, final String p1, final long p2);
    
    void submitScore(final GoogleApiClient p0, final String p1, final long p2, final String p3);
    
    PendingResult<SubmitScoreResult> submitScoreImmediate(final GoogleApiClient p0, final String p1, final long p2);
    
    PendingResult<SubmitScoreResult> submitScoreImmediate(final GoogleApiClient p0, final String p1, final long p2, final String p3);
    
    public interface LeaderboardMetadataResult extends Releasable, Result
    {
        LeaderboardBuffer getLeaderboards();
    }
    
    public interface LoadPlayerScoreResult extends Result
    {
        LeaderboardScore getScore();
    }
    
    public interface LoadScoresResult extends Releasable, Result
    {
        Leaderboard getLeaderboard();
        
        LeaderboardScoreBuffer getScores();
    }
    
    public interface SubmitScoreResult extends Releasable, Result
    {
        ScoreSubmissionData getScoreData();
    }
}
