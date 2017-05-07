// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.notification;

import com.google.android.gms.common.data.DataBuffer;

public final class GameNotificationBuffer extends DataBuffer<GameNotification>
{
    public GameNotification dO(final int n) {
        return new GameNotificationRef(this.IC, n);
    }
}
