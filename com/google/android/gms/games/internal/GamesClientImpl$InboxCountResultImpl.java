// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import android.os.Bundle;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Notifications$InboxCountResult;

final class GamesClientImpl$InboxCountResultImpl implements Notifications$InboxCountResult
{
    private final Status CM;
    private final Bundle WA;
    
    GamesClientImpl$InboxCountResultImpl(final Status cm, final Bundle wa) {
        this.CM = cm;
        this.WA = wa;
    }
    
    @Override
    public Status getStatus() {
        return this.CM;
    }
}
