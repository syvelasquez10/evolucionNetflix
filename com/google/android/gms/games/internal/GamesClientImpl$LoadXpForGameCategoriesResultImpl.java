// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import android.os.Bundle;
import java.util.List;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Players$LoadXpForGameCategoriesResult;

final class GamesClientImpl$LoadXpForGameCategoriesResultImpl implements Players$LoadXpForGameCategoriesResult
{
    private final Status CM;
    private final List<String> WT;
    private final Bundle WU;
    
    GamesClientImpl$LoadXpForGameCategoriesResultImpl(final Status cm, final Bundle wu) {
        this.CM = cm;
        this.WT = (List<String>)wu.getStringArrayList("game_category_list");
        this.WU = wu;
    }
    
    @Override
    public Status getStatus() {
        return this.CM;
    }
}
