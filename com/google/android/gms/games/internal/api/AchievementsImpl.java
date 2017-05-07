// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.achievement.AchievementBuffer;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.a;
import android.os.RemoteException;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.games.Games;
import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.achievement.Achievements;

public final class AchievementsImpl implements Achievements
{
    @Override
    public Intent getAchievementsIntent(final GoogleApiClient googleApiClient) {
        return Games.c(googleApiClient).gq();
    }
    
    @Override
    public void increment(final GoogleApiClient googleApiClient, final String s, final int n) {
        googleApiClient.b(new UpdateImpl(s) {
            public void a(final GamesClientImpl gamesClientImpl) {
                gamesClientImpl.a(null, s, n);
            }
        });
    }
    
    @Override
    public PendingResult<UpdateAchievementResult> incrementImmediate(final GoogleApiClient googleApiClient, final String s, final int n) {
        return googleApiClient.b((PendingResult<UpdateAchievementResult>)new UpdateImpl(s) {
            public void a(final GamesClientImpl gamesClientImpl) {
                gamesClientImpl.a((d<UpdateAchievementResult>)this, s, n);
            }
        });
    }
    
    @Override
    public PendingResult<LoadAchievementsResult> load(final GoogleApiClient googleApiClient, final boolean b) {
        return googleApiClient.a((PendingResult<LoadAchievementsResult>)new LoadImpl() {
            public void a(final GamesClientImpl gamesClientImpl) {
                gamesClientImpl.c((d<LoadAchievementsResult>)this, b);
            }
        });
    }
    
    @Override
    public void reveal(final GoogleApiClient googleApiClient, final String s) {
        googleApiClient.b(new UpdateImpl(s) {
            public void a(final GamesClientImpl gamesClientImpl) {
                gamesClientImpl.b(null, s);
            }
        });
    }
    
    @Override
    public PendingResult<UpdateAchievementResult> revealImmediate(final GoogleApiClient googleApiClient, final String s) {
        return googleApiClient.b((PendingResult<UpdateAchievementResult>)new UpdateImpl(s) {
            public void a(final GamesClientImpl gamesClientImpl) {
                gamesClientImpl.b((d<UpdateAchievementResult>)this, s);
            }
        });
    }
    
    @Override
    public void setSteps(final GoogleApiClient googleApiClient, final String s, final int n) {
        googleApiClient.b(new UpdateImpl(s) {
            public void a(final GamesClientImpl gamesClientImpl) {
                gamesClientImpl.b(null, s, n);
            }
        });
    }
    
    @Override
    public PendingResult<UpdateAchievementResult> setStepsImmediate(final GoogleApiClient googleApiClient, final String s, final int n) {
        return googleApiClient.b((PendingResult<UpdateAchievementResult>)new UpdateImpl(s) {
            public void a(final GamesClientImpl gamesClientImpl) {
                gamesClientImpl.b((d<UpdateAchievementResult>)this, s, n);
            }
        });
    }
    
    @Override
    public void unlock(final GoogleApiClient googleApiClient, final String s) {
        googleApiClient.b(new UpdateImpl(s) {
            public void a(final GamesClientImpl gamesClientImpl) {
                gamesClientImpl.c(null, s);
            }
        });
    }
    
    @Override
    public PendingResult<UpdateAchievementResult> unlockImmediate(final GoogleApiClient googleApiClient, final String s) {
        return googleApiClient.b((PendingResult<UpdateAchievementResult>)new UpdateImpl(s) {
            public void a(final GamesClientImpl gamesClientImpl) {
                gamesClientImpl.c((d<UpdateAchievementResult>)this, s);
            }
        });
    }
    
    private abstract static class LoadImpl extends BaseGamesApiMethodImpl<LoadAchievementsResult>
    {
        public LoadAchievementsResult t(final Status status) {
            return new LoadAchievementsResult() {
                @Override
                public AchievementBuffer getAchievements() {
                    return new AchievementBuffer(DataHolder.empty(14));
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
    
    private abstract static class UpdateImpl extends BaseGamesApiMethodImpl<UpdateAchievementResult>
    {
        private final String wp;
        
        public UpdateImpl(final String wp) {
            this.wp = wp;
        }
        
        public UpdateAchievementResult u(final Status status) {
            return new UpdateAchievementResult() {
                @Override
                public String getAchievementId() {
                    return UpdateImpl.this.wp;
                }
                
                @Override
                public Status getStatus() {
                    return status;
                }
            };
        }
    }
}
