// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Result;

class EventsImpl$UpdateImpl$1 implements Result
{
    final /* synthetic */ Status CW;
    final /* synthetic */ EventsImpl$UpdateImpl Yi;
    
    EventsImpl$UpdateImpl$1(final EventsImpl$UpdateImpl yi, final Status cw) {
        this.Yi = yi;
        this.CW = cw;
    }
    
    @Override
    public Status getStatus() {
        return this.CW;
    }
}
