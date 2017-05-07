// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.quest.Quests$LoadQuestsResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api$a;

class QuestsImpl$3 extends QuestsImpl$LoadsImpl
{
    final /* synthetic */ boolean XU;
    final /* synthetic */ int Yu;
    final /* synthetic */ QuestsImpl Zg;
    final /* synthetic */ int[] Zi;
    
    QuestsImpl$3(final QuestsImpl zg, final int[] zi, final int yu, final boolean xu) {
        this.Zg = zg;
        this.Zi = zi;
        this.Yu = yu;
        this.XU = xu;
        super((QuestsImpl$1)null);
    }
    
    @Override
    protected void a(final GamesClientImpl gamesClientImpl) {
        gamesClientImpl.a((BaseImplementation$b<Quests$LoadQuestsResult>)this, this.Zi, this.Yu, this.XU);
    }
}
