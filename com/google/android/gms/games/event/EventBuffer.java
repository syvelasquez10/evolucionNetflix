// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.event;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.DataBuffer;

public final class EventBuffer extends DataBuffer<Event>
{
    public EventBuffer(final DataHolder dataHolder) {
        super(dataHolder);
    }
    
    @Override
    public Event get(final int n) {
        return new EventRef(this.IC, n);
    }
}
