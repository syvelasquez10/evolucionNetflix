// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.quest.Quests$LoadQuestsResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api$a;

class QuestsImpl$5 extends QuestsImpl$LoadsImpl
{
    final /* synthetic */ boolean XU;
    final /* synthetic */ String XX;
    final /* synthetic */ int Yu;
    final /* synthetic */ int[] Zi;
    final /* synthetic */ String Zk;
    
    @Override
    protected void a(final GamesClientImpl gamesClientImpl) {
        gamesClientImpl.a((BaseImplementation$b<Quests$LoadQuestsResult>)this, this.XX, this.Zk, this.Zi, this.Yu, this.XU);
    }
}
