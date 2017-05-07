// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.game;

import com.google.android.gms.games.internal.constants.PlatformType;
import com.google.android.gms.internal.fo;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.b;

public final class GameInstanceRef extends b implements GameInstance
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
    
    public boolean hi() {
        return this.getInteger("real_time_support") > 0;
    }
    
    public boolean hj() {
        return this.getInteger("turn_based_support") > 0;
    }
    
    public int hk() {
        return this.getInteger("platform_type");
    }
    
    public boolean hl() {
        return this.getInteger("piracy_check") > 0;
    }
    
    public boolean hm() {
        return this.getInteger("installed") > 0;
    }
    
    @Override
    public String toString() {
        return fo.e(this).a("ApplicationId", this.getApplicationId()).a("DisplayName", this.getDisplayName()).a("SupportsRealTime", this.hi()).a("SupportsTurnBased", this.hj()).a("PlatformType", PlatformType.bd(this.hk())).a("PackageName", this.getPackageName()).a("PiracyCheckEnabled", this.hl()).a("Installed", this.hm()).toString();
    }
}
