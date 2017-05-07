// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games;

import android.os.Parcel;
import android.net.Uri;
import android.database.CharArrayBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.b;

public final class GameRef extends b implements Game
{
    public GameRef(final DataHolder dataHolder, final int n) {
        super(dataHolder, n);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return GameEntity.a(this, o);
    }
    
    public Game freeze() {
        return new GameEntity(this);
    }
    
    @Override
    public boolean gb() {
        return this.getBoolean("play_enabled_game");
    }
    
    @Override
    public boolean gc() {
        return this.getBoolean("identity_sharing_confirmed");
    }
    
    @Override
    public boolean gd() {
        return this.getInteger("installed") > 0;
    }
    
    @Override
    public String ge() {
        return this.getString("package_name");
    }
    
    @Override
    public int getAchievementTotalCount() {
        return this.getInteger("achievement_total_count");
    }
    
    @Override
    public String getApplicationId() {
        return this.getString("external_game_id");
    }
    
    @Override
    public String getDescription() {
        return this.getString("game_description");
    }
    
    @Override
    public void getDescription(final CharArrayBuffer charArrayBuffer) {
        this.a("game_description", charArrayBuffer);
    }
    
    @Override
    public String getDeveloperName() {
        return this.getString("developer_name");
    }
    
    @Override
    public void getDeveloperName(final CharArrayBuffer charArrayBuffer) {
        this.a("developer_name", charArrayBuffer);
    }
    
    @Override
    public String getDisplayName() {
        return this.getString("display_name");
    }
    
    @Override
    public void getDisplayName(final CharArrayBuffer charArrayBuffer) {
        this.a("display_name", charArrayBuffer);
    }
    
    @Override
    public Uri getFeaturedImageUri() {
        return this.ah("featured_image_uri");
    }
    
    @Override
    public String getFeaturedImageUrl() {
        return this.getString("featured_image_url");
    }
    
    @Override
    public Uri getHiResImageUri() {
        return this.ah("game_hi_res_image_uri");
    }
    
    @Override
    public String getHiResImageUrl() {
        return this.getString("game_hi_res_image_url");
    }
    
    @Override
    public Uri getIconImageUri() {
        return this.ah("game_icon_image_uri");
    }
    
    @Override
    public String getIconImageUrl() {
        return this.getString("game_icon_image_url");
    }
    
    @Override
    public int getLeaderboardCount() {
        return this.getInteger("leaderboard_count");
    }
    
    @Override
    public String getPrimaryCategory() {
        return this.getString("primary_category");
    }
    
    @Override
    public String getSecondaryCategory() {
        return this.getString("secondary_category");
    }
    
    @Override
    public int gf() {
        return this.getInteger("gameplay_acl_status");
    }
    
    @Override
    public int hashCode() {
        return GameEntity.a(this);
    }
    
    @Override
    public boolean isMuted() {
        return this.getBoolean("muted");
    }
    
    @Override
    public boolean isRealTimeMultiplayerEnabled() {
        return this.getInteger("real_time_support") > 0;
    }
    
    @Override
    public boolean isTurnBasedMultiplayerEnabled() {
        return this.getInteger("turn_based_support") > 0;
    }
    
    @Override
    public String toString() {
        return GameEntity.b(this);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ((GameEntity)this.freeze()).writeToParcel(parcel, n);
    }
}
