// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.event;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Releasable;

public interface Events$LoadEventsResult extends Releasable, Result
{
    EventBuffer getEvents();
}
