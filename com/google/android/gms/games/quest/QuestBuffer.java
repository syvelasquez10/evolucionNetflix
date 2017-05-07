// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.quest;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.g;

public final class QuestBuffer extends g<Quest>
{
    public QuestBuffer(final DataHolder dataHolder) {
        super(dataHolder);
    }
    
    @Override
    protected String gE() {
        return "external_quest_id";
    }
    
    protected Quest m(final int n, final int n2) {
        return new QuestRef(this.IC, n, n2);
    }
}
