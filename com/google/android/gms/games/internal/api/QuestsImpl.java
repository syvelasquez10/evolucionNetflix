// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.quest.QuestBuffer;
import com.google.android.gms.games.quest.Milestone;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.games.quest.Quest;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.quest.QuestUpdateListener;
import com.google.android.gms.games.Games;
import android.content.Intent;
import com.google.android.gms.common.api.BaseImplementation;
import android.os.RemoteException;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.quest.Quests;

public final class QuestsImpl implements Quests
{
    @Override
    public PendingResult<AcceptQuestResult> accept(final GoogleApiClient googleApiClient, final String s) {
        return googleApiClient.b((PendingResult<AcceptQuestResult>)new AcceptImpl() {
            protected void a(final GamesClientImpl gamesClientImpl) {
                gamesClientImpl.i((BaseImplementation.b<AcceptQuestResult>)this, s);
            }
        });
    }
    
    @Override
    public PendingResult<ClaimMilestoneResult> claim(final GoogleApiClient googleApiClient, final String s, final String s2) {
        return googleApiClient.b((PendingResult<ClaimMilestoneResult>)new ClaimImpl() {
            protected void a(final GamesClientImpl gamesClientImpl) {
                gamesClientImpl.b((BaseImplementation.b<ClaimMilestoneResult>)this, s, s2);
            }
        });
    }
    
    @Override
    public Intent getQuestIntent(final GoogleApiClient googleApiClient, final String s) {
        return Games.c(googleApiClient).bz(s);
    }
    
    @Override
    public Intent getQuestsIntent(final GoogleApiClient googleApiClient, final int[] array) {
        return Games.c(googleApiClient).b(array);
    }
    
    @Override
    public PendingResult<LoadQuestsResult> load(final GoogleApiClient googleApiClient, final int[] array, final int n, final boolean b) {
        return googleApiClient.a((PendingResult<LoadQuestsResult>)new LoadsImpl() {
            protected void a(final GamesClientImpl gamesClientImpl) {
                gamesClientImpl.a((BaseImplementation.b<LoadQuestsResult>)this, array, n, b);
            }
        });
    }
    
    @Override
    public PendingResult<LoadQuestsResult> loadByIds(final GoogleApiClient googleApiClient, final boolean b, final String... array) {
        return googleApiClient.a((PendingResult<LoadQuestsResult>)new LoadsImpl() {
            protected void a(final GamesClientImpl gamesClientImpl) {
                gamesClientImpl.b((BaseImplementation.b<LoadQuestsResult>)this, b, array);
            }
        });
    }
    
    @Override
    public void registerQuestUpdateListener(final GoogleApiClient googleApiClient, final QuestUpdateListener questUpdateListener) {
        Games.c(googleApiClient).a(questUpdateListener);
    }
    
    @Override
    public void showStateChangedPopup(final GoogleApiClient googleApiClient, final String s) {
        Games.c(googleApiClient).bA(s);
    }
    
    @Override
    public void unregisterQuestUpdateListener(final GoogleApiClient googleApiClient) {
        Games.c(googleApiClient).kh();
    }
    
    private abstract static class AcceptImpl extends BaseGamesApiMethodImpl<AcceptQuestResult>
    {
        public AcceptQuestResult ah(final Status status) {
            return new AcceptQuestResult() {
                @Override
                public Quest getQuest() {
                    return null;
                }
                
                @Override
                public Status getStatus() {
                    return status;
                }
            };
        }
    }
    
    private abstract static class ClaimImpl extends BaseGamesApiMethodImpl<ClaimMilestoneResult>
    {
        public ClaimMilestoneResult ai(final Status status) {
            return new ClaimMilestoneResult() {
                @Override
                public Milestone getMilestone() {
                    return null;
                }
                
                @Override
                public Quest getQuest() {
                    return null;
                }
                
                @Override
                public Status getStatus() {
                    return status;
                }
            };
        }
    }
    
    private abstract static class LoadsImpl extends BaseGamesApiMethodImpl<LoadQuestsResult>
    {
        public LoadQuestsResult aj(final Status status) {
            return new LoadQuestsResult() {
                @Override
                public QuestBuffer getQuests() {
                    return new QuestBuffer(DataHolder.as(status.getStatusCode()));
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
