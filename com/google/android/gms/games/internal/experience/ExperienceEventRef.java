// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.experience;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.GameRef;
import com.google.android.gms.common.data.d;

public final class ExperienceEventRef extends d implements ExperienceEvent
{
    private final GameRef aam;
    
    public ExperienceEventRef(final DataHolder dataHolder, final int n) {
        super(dataHolder, n);
        if (this.aS("external_game_id")) {
            this.aam = null;
            return;
        }
        this.aam = new GameRef(this.IC, this.JQ);
    }
    
    @Override
    public String getIconImageUrl() {
        return this.getString("icon_url");
    }
}
