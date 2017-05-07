// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.game;

import com.google.android.gms.games.internal.constants.PlatformType;
import com.google.android.gms.common.internal.m;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;

public final class GameInstanceRef extends d implements GameInstance
{
    GameInstanceRef(final DataHolder dataHolder, final int n) {
        super(dataHolder, n);
    }
    
    public String getApplicationId() {
        return this.getString("external_game_id");
    }
    
    public String getDisplayName() {
        return this.getString("instance_display_name");
    }
    
    public String getPackageName() {
        return this.getString("package_name");
    }
    
    public boolean lb() {
        return this.getInteger("real_time_support") > 0;
    }
    
    public boolean lc() {
        return this.getInteger("turn_based_support") > 0;
    }
    
    public int ld() {
        return this.getInteger("platform_type");
    }
    
    public boolean le() {
        return this.getInteger("piracy_check") > 0;
    }
    
    public boolean lf() {
        return this.getInteger("installed") > 0;
    }
    
    @Override
    public String toString() {
        return m.h(this).a("ApplicationId", this.getApplicationId()).a("DisplayName", this.getDisplayName()).a("SupportsRealTime", this.lb()).a("SupportsTurnBased", this.lc()).a("PlatformType", PlatformType.dH(this.ld())).a("PackageName", this.getPackageName()).a("PiracyCheckEnabled", this.le()).a("Installed", this.lf()).toString();
    }
}
