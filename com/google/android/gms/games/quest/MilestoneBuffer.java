// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.quest;

import com.google.android.gms.common.data.DataBuffer;

public final class MilestoneBuffer extends DataBuffer<Milestone>
{
    @Override
    public Milestone get(final int n) {
        return new MilestoneRef(this.IC, n);
    }
}
