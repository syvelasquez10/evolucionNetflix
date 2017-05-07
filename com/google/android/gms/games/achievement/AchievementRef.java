// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.achievement;

import android.os.Parcel;
import android.net.Uri;
import com.google.android.gms.games.PlayerRef;
import com.google.android.gms.games.Player;
import android.database.CharArrayBuffer;
import com.google.android.gms.common.internal.a;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;

public final class AchievementRef extends d implements Achievement
{
    AchievementRef(final DataHolder dataHolder, final int n) {
        super(dataHolder, n);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public Achievement freeze() {
        return new AchievementEntity(this);
    }
    
    @Override
    public String getAchievementId() {
        return this.getString("external_achievement_id");
    }
    
    @Override
    public int getCurrentSteps() {
        boolean b = true;
        if (this.getType() != 1) {
            b = false;
        }
        a.I(b);
        return this.getInteger("current_steps");
    }
    
    @Override
    public String getDescription() {
        return this.getString("description");
    }
    
    @Override
    public void getDescription(final CharArrayBuffer charArrayBuffer) {
        this.a("description", charArrayBuffer);
    }
    
    @Override
    public String getFormattedCurrentSteps() {
        boolean b = true;
        if (this.getType() != 1) {
            b = false;
        }
        a.I(b);
        return this.getString("formatted_current_steps");
    }
    
    @Override
    public void getFormattedCurrentSteps(final CharArrayBuffer charArrayBuffer) {
        boolean b = true;
        if (this.getType() != 1) {
            b = false;
        }
        a.I(b);
        this.a("formatted_current_steps", charArrayBuffer);
    }
    
    @Override
    public String getFormattedTotalSteps() {
        boolean b = true;
        if (this.getType() != 1) {
            b = false;
        }
        a.I(b);
        return this.getString("formatted_total_steps");
    }
    
    @Override
    public void getFormattedTotalSteps(final CharArrayBuffer charArrayBuffer) {
        boolean b = true;
        if (this.getType() != 1) {
            b = false;
        }
        a.I(b);
        this.a("formatted_total_steps", charArrayBuffer);
    }
    
    @Override
    public long getLastUpdatedTimestamp() {
        return this.getLong("last_updated_timestamp");
    }
    
    @Override
    public String getName() {
        return this.getString("name");
    }
    
    @Override
    public void getName(final CharArrayBuffer charArrayBuffer) {
        this.a("name", charArrayBuffer);
    }
    
    @Override
    public Player getPlayer() {
        return new PlayerRef(this.IC, this.JQ);
    }
    
    @Override
    public Uri getRevealedImageUri() {
        return this.aR("revealed_icon_image_uri");
    }
    
    @Override
    public String getRevealedImageUrl() {
        return this.getString("revealed_icon_image_url");
    }
    
    @Override
    public int getState() {
        return this.getInteger("state");
    }
    
    @Override
    public int getTotalSteps() {
        boolean b = true;
        if (this.getType() != 1) {
            b = false;
        }
        a.I(b);
        return this.getInteger("total_steps");
    }
    
    @Override
    public int getType() {
        return this.getInteger("type");
    }
    
    @Override
    public Uri getUnlockedImageUri() {
        return this.aR("unlocked_icon_image_uri");
    }
    
    @Override
    public String getUnlockedImageUrl() {
        return this.getString("unlocked_icon_image_url");
    }
    
    @Override
    public long getXpValue() {
        if (!this.aQ("instance_xp_value") || this.aS("instance_xp_value")) {
            return this.getLong("definition_xp_value");
        }
        return this.getLong("instance_xp_value");
    }
    
    @Override
    public String toString() {
        return AchievementEntity.b(this);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ((AchievementEntity)this.freeze()).writeToParcel(parcel, n);
    }
}
