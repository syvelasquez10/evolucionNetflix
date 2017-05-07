// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.quest.QuestUpdateListener;
import com.google.android.gms.games.quest.Quests$LoadQuestsResult;
import com.google.android.gms.games.Games;
import android.content.Intent;
import com.google.android.gms.games.quest.Quests$ClaimMilestoneResult;
import com.google.android.gms.games.quest.Quests$AcceptQuestResult;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.quest.Quests;

public final class QuestsImpl implements Quests
{
    @Override
    public PendingResult<Quests$AcceptQuestResult> accept(final GoogleApiClient googleApiClient, final String s) {
        return googleApiClient.b((PendingResult<Quests$AcceptQuestResult>)new QuestsImpl$1(this, s));
    }
    
    @Override
    public PendingResult<Quests$ClaimMilestoneResult> claim(final GoogleApiClient googleApiClient, final String s, final String s2) {
        return googleApiClient.b((PendingResult<Quests$ClaimMilestoneResult>)new QuestsImpl$2(this, s, s2));
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
    public PendingResult<Quests$LoadQuestsResult> load(final GoogleApiClient googleApiClient, final int[] array, final int n, final boolean b) {
        return googleApiClient.a((PendingResult<Quests$LoadQuestsResult>)new QuestsImpl$3(this, array, n, b));
    }
    
    @Override
    public PendingResult<Quests$LoadQuestsResult> loadByIds(final GoogleApiClient googleApiClient, final boolean b, final String... array) {
        return googleApiClient.a((PendingResult<Quests$LoadQuestsResult>)new QuestsImpl$4(this, b, array));
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
}
