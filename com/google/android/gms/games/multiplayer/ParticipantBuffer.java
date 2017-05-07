// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer;

import com.google.android.gms.common.data.DataBuffer;

public final class ParticipantBuffer extends DataBuffer<Participant>
{
    @Override
    public Participant get(final int n) {
        return new ParticipantRef(this.BB, n);
    }
}
