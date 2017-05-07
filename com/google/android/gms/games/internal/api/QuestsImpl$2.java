// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.quest.Quests$ClaimMilestoneResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api$a;

class QuestsImpl$2 extends QuestsImpl$ClaimImpl
{
    final /* synthetic */ String Zf;
    final /* synthetic */ QuestsImpl Zg;
    final /* synthetic */ String Zh;
    
    QuestsImpl$2(final QuestsImpl zg, final String zf, final String zh) {
        this.Zg = zg;
        this.Zf = zf;
        this.Zh = zh;
        super((QuestsImpl$1)null);
    }
    
    @Override
    protected void a(final GamesClientImpl gamesClientImpl) {
        gamesClientImpl.b((BaseImplementation$b<Quests$ClaimMilestoneResult>)this, this.Zf, this.Zh);
    }
}
