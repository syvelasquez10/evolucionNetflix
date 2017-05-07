// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.event.EventBuffer;
import com.google.android.gms.games.event.Events$LoadEventsResult;
import com.google.android.gms.common.api.a;

final class GamesClientImpl$LoadEventResultImpl extends a implements Events$LoadEventsResult
{
    private final EventBuffer WH;
    
    GamesClientImpl$LoadEventResultImpl(final DataHolder dataHolder) {
        super(dataHolder);
        this.WH = new EventBuffer(dataHolder);
    }
    
    @Override
    public EventBuffer getEvents() {
        return this.WH;
    }
}
