// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.quest.Quests$LoadQuestsResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api$a;

class QuestsImpl$4 extends QuestsImpl$LoadsImpl
{
    final /* synthetic */ boolean XU;
    final /* synthetic */ QuestsImpl Zg;
    final /* synthetic */ String[] Zj;
    
    QuestsImpl$4(final QuestsImpl zg, final boolean xu, final String[] zj) {
        this.Zg = zg;
        this.XU = xu;
        this.Zj = zj;
        super((QuestsImpl$1)null);
    }
    
    @Override
    protected void a(final GamesClientImpl gamesClientImpl) {
        gamesClientImpl.b((BaseImplementation$b<Quests$LoadQuestsResult>)this, this.XU, this.Zj);
    }
}
