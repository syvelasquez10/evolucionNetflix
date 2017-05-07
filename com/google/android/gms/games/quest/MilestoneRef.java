// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.quest;

import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;

public final class MilestoneRef extends d implements Milestone
{
    MilestoneRef(final DataHolder dataHolder, final int n) {
        super(dataHolder, n);
    }
    
    private long lG() {
        return this.getLong("initial_value");
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return MilestoneEntity.a(this, o);
    }
    
    public Milestone freeze() {
        return new MilestoneEntity(this);
    }
    
    @Override
    public byte[] getCompletionRewardData() {
        return this.getByteArray("completion_reward_data");
    }
    
    @Override
    public long getCurrentProgress() {
        switch (this.getState()) {
            default: {
                return 0L;
            }
            case 3:
            case 4: {
                return this.getTargetProgress();
            }
            case 2: {
                return this.getLong("current_value") - this.lG();
            }
        }
    }
    
    @Override
    public String getEventId() {
        return this.getString("external_event_id");
    }
    
    @Override
    public String getMilestoneId() {
        return this.getString("external_milestone_id");
    }
    
    @Override
    public int getState() {
        return this.getInteger("milestone_state");
    }
    
    @Override
    public long getTargetProgress() {
        return this.getLong("target_value");
    }
    
    @Override
    public int hashCode() {
        return MilestoneEntity.a(this);
    }
    
    @Override
    public String toString() {
        return MilestoneEntity.b(this);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ((MilestoneEntity)this.freeze()).writeToParcel(parcel, n);
    }
}
