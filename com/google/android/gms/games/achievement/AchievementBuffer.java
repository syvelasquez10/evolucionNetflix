// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.achievement;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.DataBuffer;

public final class AchievementBuffer extends DataBuffer<Achievement>
{
    public AchievementBuffer(final DataHolder dataHolder) {
        super(dataHolder);
    }
    
    @Override
    public Achievement get(final int n) {
        return new AchievementRef(this.IC, n);
    }
}
