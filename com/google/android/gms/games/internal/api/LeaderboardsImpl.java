// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.leaderboard.ScoreSubmissionData;
import com.google.android.gms.games.leaderboard.Leaderboard;
import com.google.android.gms.games.leaderboard.LeaderboardScore;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.leaderboard.LeaderboardBuffer;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.leaderboard.LeaderboardScoreBuffer;
import com.google.android.gms.common.api.BaseImplementation;
import android.os.RemoteException;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api;
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
    public PendingResult<LoadPlayerScoreResult> loadCurrentPlayerLeaderboardScore(final GoogleApiClient googleApiClient, final String s, final int n, final int n2) {
        return googleApiClient.a((PendingResult<LoadPlayerScoreResult>)new LoadPlayerScoreImpl() {
            protected void a(final GamesClientImpl gamesClientImpl) {
                gamesClientImpl.a((BaseImplementation.b<LoadPlayerScoreResult>)this, null, s, n, n2);
            }
        });
    }
    
    @Override
    public PendingResult<LeaderboardMetadataResult> loadLeaderboardMetadata(final GoogleApiClient googleApiClient, final String s, final boolean b) {
        return googleApiClient.a((PendingResult<LeaderboardMetadataResult>)new LoadMetadataImpl() {
            protected void a(final GamesClientImpl gamesClientImpl) {
                gamesClientImpl.a((BaseImplementation.b<LeaderboardMetadataResult>)this, s, b);
            }
        });
    }
    
    @Override
    public PendingResult<LeaderboardMetadataResult> loadLeaderboardMetadata(final GoogleApiClient googleApiClient, final boolean b) {
        return googleApiClient.a((PendingResult<LeaderboardMetadataResult>)new LoadMetadataImpl() {
            protected void a(final GamesClientImpl gamesClientImpl) {
                gamesClientImpl.b((BaseImplementation.b<LeaderboardMetadataResult>)this, b);
            }
        });
    }
    
    @Override
    public PendingResult<LoadScoresResult> loadMoreScores(final GoogleApiClient googleApiClient, final LeaderboardScoreBuffer leaderboardScoreBuffer, final int n, final int n2) {
        return googleApiClient.a((PendingResult<LoadScoresResult>)new LoadScoresImpl() {
            protected void a(final GamesClientImpl gamesClientImpl) {
                gamesClientImpl.a((BaseImplementation.b<LoadScoresResult>)this, leaderboardScoreBuffer, n, n2);
            }
        });
    }
    
    @Override
    public PendingResult<LoadScoresResult> loadPlayerCenteredScores(final GoogleApiClient googleApiClient, final String s, final int n, final int n2, final int n3) {
        return this.loadPlayerCenteredScores(googleApiClient, s, n, n2, n3, false);
    }
    
    @Override
    public PendingResult<LoadScoresResult> loadPlayerCenteredScores(final GoogleApiClient googleApiClient, final String s, final int n, final int n2, final int n3, final boolean b) {
        return googleApiClient.a((PendingResult<LoadScoresResult>)new LoadScoresImpl() {
            protected void a(final GamesClientImpl gamesClientImpl) {
                gamesClientImpl.b((BaseImplementation.b<LoadScoresResult>)this, s, n, n2, n3, b);
            }
        });
    }
    
    @Override
    public PendingResult<LoadScoresResult> loadTopScores(final GoogleApiClient googleApiClient, final String s, final int n, final int n2, final int n3) {
        return this.loadTopScores(googleApiClient, s, n, n2, n3, false);
    }
    
    @Override
    public PendingResult<LoadScoresResult> loadTopScores(final GoogleApiClient googleApiClient, final String s, final int n, final int n2, final int n3, final boolean b) {
        return googleApiClient.a((PendingResult<LoadScoresResult>)new LoadScoresImpl() {
            protected void a(final GamesClientImpl gamesClientImpl) {
                gamesClientImpl.a((BaseImplementation.b<LoadScoresResult>)this, s, n, n2, n3, b);
            }
        });
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
    public PendingResult<SubmitScoreResult> submitScoreImmediate(final GoogleApiClient googleApiClient, final String s, final long n) {
        return this.submitScoreImmediate(googleApiClient, s, n, null);
    }
    
    @Override
    public PendingResult<SubmitScoreResult> submitScoreImmediate(final GoogleApiClient googleApiClient, final String s, final long n, final String s2) {
        return googleApiClient.b((PendingResult<SubmitScoreResult>)new SubmitScoreImpl() {
            protected void a(final GamesClientImpl gamesClientImpl) {
                gamesClientImpl.a((BaseImplementation.b<SubmitScoreResult>)this, s, n, s2);
            }
        });
    }
    
    private abstract static class LoadMetadataImpl extends BaseGamesApiMethodImpl<LeaderboardMetadataResult>
    {
        public LeaderboardMetadataResult U(final Status status) {
            return new LeaderboardMetadataResult() {
                @Override
                public LeaderboardBuffer getLeaderboards() {
                    return new LeaderboardBuffer(DataHolder.as(14));
                }
                
                @Override
                public Status getStatus() {
                    return status;
                }
                
                @Override
                public void release() {
                }
            };
        }
    }
    
    private abstract static class LoadPlayerScoreImpl extends BaseGamesApiMethodImpl<LoadPlayerScoreResult>
    {
        public LoadPlayerScoreResult V(final Status status) {
            return new LoadPlayerScoreResult() {
                @Override
                public LeaderboardScore getScore() {
                    return null;
                }
                
                @Override
                public Status getStatus() {
                    return status;
                }
            };
        }
    }
    
    private abstract static class LoadScoresImpl extends BaseGamesApiMethodImpl<LoadScoresResult>
    {
        public LoadScoresResult W(final Status status) {
            return new LoadScoresResult() {
                @Override
                public Leaderboard getLeaderboard() {
                    return null;
                }
                
                @Override
                public LeaderboardScoreBuffer getScores() {
                    return new LeaderboardScoreBuffer(DataHolder.as(14));
                }
                
                @Override
                public Status getStatus() {
                    return status;
                }
                
                @Override
                public void release() {
                }
            };
        }
    }
    
    protected abstract static class SubmitScoreImpl extends BaseGamesApiMethodImpl<SubmitScoreResult>
    {
        public SubmitScoreResult X(final Status status) {
            return new SubmitScoreResult() {
                @Override
                public ScoreSubmissionData getScoreData() {
                    return new ScoreSubmissionData(DataHolder.as(14));
                }
                
                @Override
                public Status getStatus() {
                    return status;
                }
                
                @Override
                public void release() {
                }
            };
        }
    }
}
