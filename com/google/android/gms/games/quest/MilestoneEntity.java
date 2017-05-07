// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.quest;

import android.os.Parcel;
import com.google.android.gms.common.internal.m;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class MilestoneEntity implements SafeParcelable, Milestone
{
    public static final MilestoneEntityCreator CREATOR;
    private final int BR;
    private final String Wb;
    private final String Xj;
    private final long acD;
    private final long acE;
    private final byte[] acF;
    private final int mState;
    
    static {
        CREATOR = new MilestoneEntityCreator();
    }
    
    MilestoneEntity(final int br, final String xj, final long acD, final long acE, final byte[] acF, final int mState, final String wb) {
        this.BR = br;
        this.Xj = xj;
        this.acD = acD;
        this.acE = acE;
        this.acF = acF;
        this.mState = mState;
        this.Wb = wb;
    }
    
    public MilestoneEntity(final Milestone milestone) {
        this.BR = 4;
        this.Xj = milestone.getMilestoneId();
        this.acD = milestone.getCurrentProgress();
        this.acE = milestone.getTargetProgress();
        this.mState = milestone.getState();
        this.Wb = milestone.getEventId();
        final byte[] completionRewardData = milestone.getCompletionRewardData();
        if (completionRewardData == null) {
            this.acF = null;
            return;
        }
        System.arraycopy(completionRewardData, 0, this.acF = new byte[completionRewardData.length], 0, completionRewardData.length);
    }
    
    static int a(final Milestone milestone) {
        return m.hashCode(milestone.getMilestoneId(), milestone.getCurrentProgress(), milestone.getTargetProgress(), milestone.getState(), milestone.getEventId());
    }
    
    static boolean a(final Milestone milestone, final Object o) {
        final boolean b = true;
        boolean b2;
        if (!(o instanceof Milestone)) {
            b2 = false;
        }
        else {
            b2 = b;
            if (milestone != o) {
                final Milestone milestone2 = (Milestone)o;
                if (m.equal(milestone2.getMilestoneId(), milestone.getMilestoneId()) && m.equal(milestone2.getCurrentProgress(), milestone.getCurrentProgress()) && m.equal(milestone2.getTargetProgress(), milestone.getTargetProgress()) && m.equal(milestone2.getState(), milestone.getState())) {
                    b2 = b;
                    if (m.equal(milestone2.getEventId(), milestone.getEventId())) {
                        return b2;
                    }
                }
                return false;
            }
        }
        return b2;
    }
    
    static String b(final Milestone milestone) {
        return m.h(milestone).a("MilestoneId", milestone.getMilestoneId()).a("CurrentProgress", milestone.getCurrentProgress()).a("TargetProgress", milestone.getTargetProgress()).a("State", milestone.getState()).a("CompletionRewardData", milestone.getCompletionRewardData()).a("EventId", milestone.getEventId()).toString();
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return a(this, o);
    }
    
    public Milestone freeze() {
        return this;
    }
    
    @Override
    public byte[] getCompletionRewardData() {
        return this.acF;
    }
    
    @Override
    public long getCurrentProgress() {
        return this.acD;
    }
    
    @Override
    public String getEventId() {
        return this.Wb;
    }
    
    @Override
    public String getMilestoneId() {
        return this.Xj;
    }
    
    @Override
    public int getState() {
        return this.mState;
    }
    
    @Override
    public long getTargetProgress() {
        return this.acE;
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public int hashCode() {
        return a(this);
    }
    
    public boolean isDataValid() {
        return true;
    }
    
    @Override
    public String toString() {
        return b(this);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        MilestoneEntityCreator.a(this, parcel, n);
    }
}
