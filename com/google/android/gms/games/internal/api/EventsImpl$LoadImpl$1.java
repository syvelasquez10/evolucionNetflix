// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.event.EventBuffer;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.event.Events$LoadEventsResult;

class EventsImpl$LoadImpl$1 implements Events$LoadEventsResult
{
    final /* synthetic */ Status CW;
    final /* synthetic */ EventsImpl$LoadImpl Yh;
    
    EventsImpl$LoadImpl$1(final EventsImpl$LoadImpl yh, final Status cw) {
        this.Yh = yh;
        this.CW = cw;
    }
    
    @Override
    public EventBuffer getEvents() {
        return new EventBuffer(DataHolder.as(14));
    }
    
    @Override
    public Status getStatus() {
        return this.CW;
    }
    
    @Override
    public void release() {
    }
}
