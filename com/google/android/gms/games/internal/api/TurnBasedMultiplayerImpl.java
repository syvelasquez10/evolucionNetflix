// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import android.os.Bundle;
import com.google.android.gms.games.multiplayer.turnbased.LoadMatchesResponse;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.multiplayer.turnbased.OnTurnBasedMatchUpdateReceivedListener;
import android.content.Intent;
import java.util.List;
import com.google.android.gms.games.multiplayer.ParticipantResult;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfig;
import com.google.android.gms.common.api.BaseImplementation;
import android.os.RemoteException;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;

public final class TurnBasedMultiplayerImpl implements TurnBasedMultiplayer
{
    @Override
    public PendingResult<InitiateMatchResult> acceptInvitation(final GoogleApiClient googleApiClient, final String s) {
        return googleApiClient.b((PendingResult<InitiateMatchResult>)new InitiateMatchImpl() {
            protected void a(final GamesClientImpl gamesClientImpl) {
                gamesClientImpl.e((BaseImplementation.b<InitiateMatchResult>)this, s);
            }
        });
    }
    
    @Override
    public PendingResult<CancelMatchResult> cancelMatch(final GoogleApiClient googleApiClient, final String s) {
        return googleApiClient.b((PendingResult<CancelMatchResult>)new CancelMatchImpl(s) {
            protected void a(final GamesClientImpl gamesClientImpl) {
                gamesClientImpl.g((BaseImplementation.b<CancelMatchResult>)this, s);
            }
        });
    }
    
    @Override
    public PendingResult<InitiateMatchResult> createMatch(final GoogleApiClient googleApiClient, final TurnBasedMatchConfig turnBasedMatchConfig) {
        return googleApiClient.b((PendingResult<InitiateMatchResult>)new InitiateMatchImpl() {
            protected void a(final GamesClientImpl gamesClientImpl) {
                gamesClientImpl.a((BaseImplementation.b<InitiateMatchResult>)this, turnBasedMatchConfig);
            }
        });
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
    public PendingResult<UpdateMatchResult> finishMatch(final GoogleApiClient googleApiClient, final String s) {
        return this.finishMatch(googleApiClient, s, null, (ParticipantResult[])null);
    }
    
    @Override
    public PendingResult<UpdateMatchResult> finishMatch(final GoogleApiClient googleApiClient, final String s, final byte[] array, final List<ParticipantResult> list) {
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
    public PendingResult<UpdateMatchResult> finishMatch(final GoogleApiClient googleApiClient, final String s, final byte[] array, final ParticipantResult... array2) {
        return googleApiClient.b((PendingResult<UpdateMatchResult>)new UpdateMatchImpl() {
            protected void a(final GamesClientImpl gamesClientImpl) {
                gamesClientImpl.a((BaseImplementation.b<UpdateMatchResult>)this, s, array, array2);
            }
        });
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
    public PendingResult<LeaveMatchResult> leaveMatch(final GoogleApiClient googleApiClient, final String s) {
        return googleApiClient.b((PendingResult<LeaveMatchResult>)new LeaveMatchImpl() {
            protected void a(final GamesClientImpl gamesClientImpl) {
                gamesClientImpl.f((BaseImplementation.b<LeaveMatchResult>)this, s);
            }
        });
    }
    
    @Override
    public PendingResult<LeaveMatchResult> leaveMatchDuringTurn(final GoogleApiClient googleApiClient, final String s, final String s2) {
        return googleApiClient.b((PendingResult<LeaveMatchResult>)new LeaveMatchImpl() {
            protected void a(final GamesClientImpl gamesClientImpl) {
                gamesClientImpl.a((BaseImplementation.b<LeaveMatchResult>)this, s, s2);
            }
        });
    }
    
    @Override
    public PendingResult<LoadMatchResult> loadMatch(final GoogleApiClient googleApiClient, final String s) {
        return googleApiClient.a((PendingResult<LoadMatchResult>)new LoadMatchImpl() {
            protected void a(final GamesClientImpl gamesClientImpl) {
                gamesClientImpl.h((BaseImplementation.b<LoadMatchResult>)this, s);
            }
        });
    }
    
    @Override
    public PendingResult<LoadMatchesResult> loadMatchesByStatus(final GoogleApiClient googleApiClient, final int n, final int[] array) {
        return googleApiClient.a((PendingResult<LoadMatchesResult>)new LoadMatchesImpl() {
            protected void a(final GamesClientImpl gamesClientImpl) {
                gamesClientImpl.a((BaseImplementation.b<LoadMatchesResult>)this, n, array);
            }
        });
    }
    
    @Override
    public PendingResult<LoadMatchesResult> loadMatchesByStatus(final GoogleApiClient googleApiClient, final int[] array) {
        return this.loadMatchesByStatus(googleApiClient, 0, array);
    }
    
    @Override
    public void registerMatchUpdateListener(final GoogleApiClient googleApiClient, final OnTurnBasedMatchUpdateReceivedListener onTurnBasedMatchUpdateReceivedListener) {
        Games.c(googleApiClient).a(onTurnBasedMatchUpdateReceivedListener);
    }
    
    @Override
    public PendingResult<InitiateMatchResult> rematch(final GoogleApiClient googleApiClient, final String s) {
        return googleApiClient.b((PendingResult<InitiateMatchResult>)new InitiateMatchImpl() {
            protected void a(final GamesClientImpl gamesClientImpl) {
                gamesClientImpl.d((BaseImplementation.b<InitiateMatchResult>)this, s);
            }
        });
    }
    
    @Override
    public PendingResult<UpdateMatchResult> takeTurn(final GoogleApiClient googleApiClient, final String s, final byte[] array, final String s2) {
        return this.takeTurn(googleApiClient, s, array, s2, (ParticipantResult[])null);
    }
    
    @Override
    public PendingResult<UpdateMatchResult> takeTurn(final GoogleApiClient googleApiClient, final String s, final byte[] array, final String s2, final List<ParticipantResult> list) {
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
    public PendingResult<UpdateMatchResult> takeTurn(final GoogleApiClient googleApiClient, final String s, final byte[] array, final String s2, final ParticipantResult... array2) {
        return googleApiClient.b((PendingResult<UpdateMatchResult>)new UpdateMatchImpl() {
            protected void a(final GamesClientImpl gamesClientImpl) {
                gamesClientImpl.a((BaseImplementation.b<UpdateMatchResult>)this, s, array, s2, array2);
            }
        });
    }
    
    @Override
    public void unregisterMatchUpdateListener(final GoogleApiClient googleApiClient) {
        Games.c(googleApiClient).kg();
    }
    
    private abstract static class CancelMatchImpl extends BaseGamesApiMethodImpl<CancelMatchResult>
    {
        private final String BL;
        
        public CancelMatchImpl(final String bl) {
            this.BL = bl;
        }
        
        public CancelMatchResult as(final Status status) {
            return new CancelMatchResult() {
                @Override
                public String getMatchId() {
                    return CancelMatchImpl.this.BL;
                }
                
                @Override
                public Status getStatus() {
                    return status;
                }
            };
        }
    }
    
    private abstract static class InitiateMatchImpl extends BaseGamesApiMethodImpl<InitiateMatchResult>
    {
        public InitiateMatchResult at(final Status status) {
            return new InitiateMatchResult() {
                @Override
                public TurnBasedMatch getMatch() {
                    return null;
                }
                
                @Override
                public Status getStatus() {
                    return status;
                }
            };
        }
    }
    
    private abstract static class LeaveMatchImpl extends BaseGamesApiMethodImpl<LeaveMatchResult>
    {
        public LeaveMatchResult au(final Status status) {
            return new LeaveMatchResult() {
                @Override
                public TurnBasedMatch getMatch() {
                    return null;
                }
                
                @Override
                public Status getStatus() {
                    return status;
                }
            };
        }
    }
    
    private abstract static class LoadMatchImpl extends BaseGamesApiMethodImpl<LoadMatchResult>
    {
        public LoadMatchResult av(final Status status) {
            return new LoadMatchResult() {
                @Override
                public TurnBasedMatch getMatch() {
                    return null;
                }
                
                @Override
                public Status getStatus() {
                    return status;
                }
            };
        }
    }
    
    private abstract static class LoadMatchesImpl extends BaseGamesApiMethodImpl<LoadMatchesResult>
    {
        public LoadMatchesResult aw(final Status status) {
            return new LoadMatchesResult() {
                @Override
                public LoadMatchesResponse getMatches() {
                    return new LoadMatchesResponse(new Bundle());
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
    
    private abstract static class UpdateMatchImpl extends BaseGamesApiMethodImpl<UpdateMatchResult>
    {
        public UpdateMatchResult ax(final Status status) {
            return new UpdateMatchResult() {
                @Override
                public TurnBasedMatch getMatch() {
                    return null;
                }
                
                @Override
                public Status getStatus() {
                    return status;
                }
            };
        }
    }
}
