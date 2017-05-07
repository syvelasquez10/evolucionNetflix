// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.quest.Quests$AcceptQuestResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api$a;

class QuestsImpl$1 extends QuestsImpl$AcceptImpl
{
    final /* synthetic */ String Zf;
    final /* synthetic */ QuestsImpl Zg;
    
    QuestsImpl$1(final QuestsImpl zg, final String zf) {
        this.Zg = zg;
        this.Zf = zf;
        super((QuestsImpl$1)null);
    }
    
    @Override
    protected void a(final GamesClientImpl gamesClientImpl) {
        gamesClientImpl.i((BaseImplementation$b<Quests$AcceptQuestResult>)this, this.Zf);
    }
}
