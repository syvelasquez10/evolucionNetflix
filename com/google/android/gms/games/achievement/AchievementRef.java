// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.achievement;

import com.google.android.gms.internal.fo;
import android.net.Uri;
import com.google.android.gms.games.PlayerRef;
import com.google.android.gms.games.Player;
import android.database.CharArrayBuffer;
import com.google.android.gms.internal.fb;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.b;

public final class AchievementRef extends b implements Achievement
{
    AchievementRef(final DataHolder dataHolder, final int n) {
        super(dataHolder, n);
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
        fb.x(b);
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
        fb.x(b);
        return this.getString("formatted_current_steps");
    }
    
    @Override
    public void getFormattedCurrentSteps(final CharArrayBuffer charArrayBuffer) {
        boolean b = true;
        if (this.getType() != 1) {
            b = false;
        }
        fb.x(b);
        this.a("formatted_current_steps", charArrayBuffer);
    }
    
    @Override
    public String getFormattedTotalSteps() {
        boolean b = true;
        if (this.getType() != 1) {
            b = false;
        }
        fb.x(b);
        return this.getString("formatted_total_steps");
    }
    
    @Override
    public void getFormattedTotalSteps(final CharArrayBuffer charArrayBuffer) {
        boolean b = true;
        if (this.getType() != 1) {
            b = false;
        }
        fb.x(b);
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
        return new PlayerRef(this.BB, this.BD);
    }
    
    @Override
    public Uri getRevealedImageUri() {
        return this.ah("revealed_icon_image_uri");
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
        fb.x(b);
        return this.getInteger("total_steps");
    }
    
    @Override
    public int getType() {
        return this.getInteger("type");
    }
    
    @Override
    public Uri getUnlockedImageUri() {
        return this.ah("unlocked_icon_image_uri");
    }
    
    @Override
    public String getUnlockedImageUrl() {
        return this.getString("unlocked_icon_image_url");
    }
    
    @Override
    public String toString() {
        final fo.a a = fo.e(this).a("AchievementId", this.getAchievementId()).a("Type", this.getType()).a("Name", this.getName()).a("Description", this.getDescription()).a("UnlockedImageUri", this.getUnlockedImageUri()).a("UnlockedImageUrl", this.getUnlockedImageUrl()).a("RevealedImageUri", this.getRevealedImageUri()).a("RevealedImageUrl", this.getRevealedImageUrl()).a("Player", this.getPlayer()).a("LastUpdatedTimeStamp", this.getLastUpdatedTimestamp());
        if (this.getType() == 1) {
            a.a("CurrentSteps", this.getCurrentSteps());
            a.a("TotalSteps", this.getTotalSteps());
        }
        return a.toString();
    }
}
