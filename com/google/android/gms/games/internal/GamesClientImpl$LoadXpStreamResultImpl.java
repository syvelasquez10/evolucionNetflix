// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.internal.experience.ExperienceEventBuffer;
import com.google.android.gms.games.Players$LoadXpStreamResult;
import com.google.android.gms.common.api.a;

final class GamesClientImpl$LoadXpStreamResultImpl extends a implements Players$LoadXpStreamResult
{
    private final ExperienceEventBuffer WV;
    
    GamesClientImpl$LoadXpStreamResultImpl(final DataHolder dataHolder) {
        super(dataHolder);
        this.WV = new ExperienceEventBuffer(dataHolder);
    }
}
