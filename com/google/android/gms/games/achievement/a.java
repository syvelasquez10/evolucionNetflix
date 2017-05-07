// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.achievement;

import com.google.android.gms.internal.ee;
import android.net.Uri;
import com.google.android.gms.games.d;
import com.google.android.gms.games.Player;
import android.database.CharArrayBuffer;
import com.google.android.gms.internal.ds;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.b;

public final class a extends b implements Achievement
{
    a(final DataHolder dataHolder, final int n) {
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
        ds.p(b);
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
        ds.p(b);
        return this.getString("formatted_current_steps");
    }
    
    @Override
    public void getFormattedCurrentSteps(final CharArrayBuffer charArrayBuffer) {
        boolean b = true;
        if (this.getType() != 1) {
            b = false;
        }
        ds.p(b);
        this.a("formatted_current_steps", charArrayBuffer);
    }
    
    @Override
    public String getFormattedTotalSteps() {
        boolean b = true;
        if (this.getType() != 1) {
            b = false;
        }
        ds.p(b);
        return this.getString("formatted_total_steps");
    }
    
    @Override
    public void getFormattedTotalSteps(final CharArrayBuffer charArrayBuffer) {
        boolean b = true;
        if (this.getType() != 1) {
            b = false;
        }
        ds.p(b);
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
        return new d(this.nE, this.nG);
    }
    
    @Override
    public Uri getRevealedImageUri() {
        return this.L("revealed_icon_image_uri");
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
        ds.p(b);
        return this.getInteger("total_steps");
    }
    
    @Override
    public int getType() {
        return this.getInteger("type");
    }
    
    @Override
    public Uri getUnlockedImageUri() {
        return this.L("unlocked_icon_image_uri");
    }
    
    @Override
    public String toString() {
        final ee.a a = ee.e(this).a("id", this.getAchievementId()).a("name", this.getName()).a("state", this.getState()).a("type", this.getType());
        if (this.getType() == 1) {
            a.a("steps", this.getCurrentSteps() + "/" + this.getTotalSteps());
        }
        return a.toString();
    }
}
