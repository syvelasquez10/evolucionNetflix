// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.quest;

import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import android.content.Intent;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;

public interface Quests
{
    public static final String EXTRA_QUEST = "quest";
    public static final int SELECT_ACCEPTED = 3;
    public static final int SELECT_COMPLETED = 4;
    public static final int SELECT_COMPLETED_UNCLAIMED = 101;
    public static final int SELECT_ENDING_SOON = 102;
    public static final int SELECT_EXPIRED = 5;
    public static final int SELECT_FAILED = 6;
    public static final int SELECT_OPEN = 2;
    public static final int SELECT_RECENTLY_FAILED = 103;
    public static final int SELECT_UPCOMING = 1;
    public static final int SORT_ORDER_ENDING_SOON_FIRST = 1;
    public static final int SORT_ORDER_RECENTLY_UPDATED_FIRST = 0;
    public static final int[] acQ = { 1, 2, 3, 4, 101, 5, 102, 6, 103 };
    
    PendingResult<AcceptQuestResult> accept(final GoogleApiClient p0, final String p1);
    
    PendingResult<ClaimMilestoneResult> claim(final GoogleApiClient p0, final String p1, final String p2);
    
    Intent getQuestIntent(final GoogleApiClient p0, final String p1);
    
    Intent getQuestsIntent(final GoogleApiClient p0, final int[] p1);
    
    PendingResult<LoadQuestsResult> load(final GoogleApiClient p0, final int[] p1, final int p2, final boolean p3);
    
    PendingResult<LoadQuestsResult> loadByIds(final GoogleApiClient p0, final boolean p1, final String... p2);
    
    void registerQuestUpdateListener(final GoogleApiClient p0, final QuestUpdateListener p1);
    
    void showStateChangedPopup(final GoogleApiClient p0, final String p1);
    
    void unregisterQuestUpdateListener(final GoogleApiClient p0);
    
    public interface AcceptQuestResult extends Result
    {
        Quest getQuest();
    }
    
    public interface ClaimMilestoneResult extends Result
    {
        Milestone getMilestone();
        
        Quest getQuest();
    }
    
    public interface LoadQuestsResult extends Releasable, Result
    {
        QuestBuffer getQuests();
    }
}
