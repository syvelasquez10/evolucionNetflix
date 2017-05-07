// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.experience;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.DataBuffer;

public final class ExperienceEventBuffer extends DataBuffer<ExperienceEvent>
{
    public ExperienceEventBuffer(final DataHolder dataHolder) {
        super(dataHolder);
    }
    
    public ExperienceEvent dI(final int n) {
        return new ExperienceEventRef(this.IC, n);
    }
}
