// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api$a;
import android.os.Bundle;

class NotificationsImpl$6 extends NotificationsImpl$ContactSettingUpdateImpl
{
    final /* synthetic */ boolean YP;
    final /* synthetic */ Bundle YQ;
    
    @Override
    protected void a(final GamesClientImpl gamesClientImpl) {
        gamesClientImpl.a((BaseImplementation$b<Status>)this, this.YP, this.YQ);
    }
}
