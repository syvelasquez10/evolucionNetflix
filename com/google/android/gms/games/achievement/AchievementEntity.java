// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.achievement;

import com.google.android.gms.common.data.Freezable;
import android.os.Parcel;
import com.google.android.gms.games.Player;
import com.google.android.gms.internal.jv;
import android.database.CharArrayBuffer;
import com.google.android.gms.common.internal.m;
import com.google.android.gms.common.internal.a;
import com.google.android.gms.games.PlayerEntity;
import android.net.Uri;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class AchievementEntity implements SafeParcelable, Achievement
{
    public static final AchievementEntityCreator CREATOR;
    private final int BR;
    private final int FD;
    private final String Tg;
    private final String VP;
    private final Uri VQ;
    private final String VR;
    private final Uri VS;
    private final String VT;
    private final int VU;
    private final String VV;
    private final PlayerEntity VW;
    private final int VX;
    private final String VY;
    private final long VZ;
    private final long Wa;
    private final String mName;
    private final int mState;
    
    static {
        CREATOR = new AchievementEntityCreator();
    }
    
    AchievementEntity(final int br, final String vp, final int fd, final String mName, final String tg, final Uri vq, final String vr, final Uri vs, final String vt, final int vu, final String vv, final PlayerEntity vw, final int mState, final int vx, final String vy, final long vz, final long wa) {
        this.BR = br;
        this.VP = vp;
        this.FD = fd;
        this.mName = mName;
        this.Tg = tg;
        this.VQ = vq;
        this.VR = vr;
        this.VS = vs;
        this.VT = vt;
        this.VU = vu;
        this.VV = vv;
        this.VW = vw;
        this.mState = mState;
        this.VX = vx;
        this.VY = vy;
        this.VZ = vz;
        this.Wa = wa;
    }
    
    public AchievementEntity(final Achievement achievement) {
        this.BR = 1;
        this.VP = achievement.getAchievementId();
        this.FD = achievement.getType();
        this.mName = achievement.getName();
        this.Tg = achievement.getDescription();
        this.VQ = achievement.getUnlockedImageUri();
        this.VR = achievement.getUnlockedImageUrl();
        this.VS = achievement.getRevealedImageUri();
        this.VT = achievement.getRevealedImageUrl();
        this.VW = ((Freezable<PlayerEntity>)achievement.getPlayer()).freeze();
        this.mState = achievement.getState();
        this.VZ = achievement.getLastUpdatedTimestamp();
        this.Wa = achievement.getXpValue();
        if (achievement.getType() == 1) {
            this.VU = achievement.getTotalSteps();
            this.VV = achievement.getFormattedTotalSteps();
            this.VX = achievement.getCurrentSteps();
            this.VY = achievement.getFormattedCurrentSteps();
        }
        else {
            this.VU = 0;
            this.VV = null;
            this.VX = 0;
            this.VY = null;
        }
        a.f(this.VP);
        a.f(this.Tg);
    }
    
    static int a(final Achievement achievement) {
        int currentSteps;
        int totalSteps;
        if (achievement.getType() == 1) {
            currentSteps = achievement.getCurrentSteps();
            totalSteps = achievement.getTotalSteps();
        }
        else {
            totalSteps = 0;
            currentSteps = 0;
        }
        return m.hashCode(achievement.getAchievementId(), achievement.getName(), achievement.getType(), achievement.getDescription(), achievement.getXpValue(), achievement.getState(), achievement.getLastUpdatedTimestamp(), achievement.getPlayer(), currentSteps, totalSteps);
    }
    
    static boolean a(final Achievement achievement, final Object o) {
        final boolean b = true;
        boolean b2;
        if (!(o instanceof Achievement)) {
            b2 = false;
        }
        else {
            b2 = b;
            if (achievement != o) {
                final Achievement achievement2 = (Achievement)o;
                boolean equal;
                int equal2;
                if (achievement.getType() == 1) {
                    equal = m.equal(achievement2.getCurrentSteps(), achievement.getCurrentSteps());
                    equal2 = (m.equal(achievement2.getTotalSteps(), achievement.getTotalSteps()) ? 1 : 0);
                }
                else {
                    equal2 = 1;
                    equal = true;
                }
                if (m.equal(achievement2.getAchievementId(), achievement.getAchievementId()) && m.equal(achievement2.getName(), achievement.getName()) && m.equal(achievement2.getType(), achievement.getType()) && m.equal(achievement2.getDescription(), achievement.getDescription()) && m.equal(achievement2.getXpValue(), achievement.getXpValue()) && m.equal(achievement2.getState(), achievement.getState()) && m.equal(achievement2.getLastUpdatedTimestamp(), achievement.getLastUpdatedTimestamp()) && m.equal(achievement2.getPlayer(), achievement.getPlayer()) && equal) {
                    b2 = b;
                    if (equal2 != 0) {
                        return b2;
                    }
                }
                return false;
            }
        }
        return b2;
    }
    
    static String b(final Achievement achievement) {
        final m.a a = m.h(achievement).a("Id", achievement.getAchievementId()).a("Type", achievement.getType()).a("Name", achievement.getName()).a("Description", achievement.getDescription()).a("Player", achievement.getPlayer()).a("State", achievement.getState());
        if (achievement.getType() == 1) {
            a.a("CurrentSteps", achievement.getCurrentSteps());
            a.a("TotalSteps", achievement.getTotalSteps());
        }
        return a.toString();
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return a(this, o);
    }
    
    public Achievement freeze() {
        return this;
    }
    
    @Override
    public String getAchievementId() {
        return this.VP;
    }
    
    @Override
    public int getCurrentSteps() {
        return this.VX;
    }
    
    @Override
    public String getDescription() {
        return this.Tg;
    }
    
    @Override
    public void getDescription(final CharArrayBuffer charArrayBuffer) {
        jv.b(this.Tg, charArrayBuffer);
    }
    
    @Override
    public String getFormattedCurrentSteps() {
        return this.VY;
    }
    
    @Override
    public void getFormattedCurrentSteps(final CharArrayBuffer charArrayBuffer) {
        jv.b(this.VY, charArrayBuffer);
    }
    
    @Override
    public String getFormattedTotalSteps() {
        return this.VV;
    }
    
    @Override
    public void getFormattedTotalSteps(final CharArrayBuffer charArrayBuffer) {
        jv.b(this.VV, charArrayBuffer);
    }
    
    @Override
    public long getLastUpdatedTimestamp() {
        return this.VZ;
    }
    
    @Override
    public String getName() {
        return this.mName;
    }
    
    @Override
    public void getName(final CharArrayBuffer charArrayBuffer) {
        jv.b(this.mName, charArrayBuffer);
    }
    
    @Override
    public Player getPlayer() {
        return this.VW;
    }
    
    @Override
    public Uri getRevealedImageUri() {
        return this.VS;
    }
    
    @Override
    public String getRevealedImageUrl() {
        return this.VT;
    }
    
    @Override
    public int getState() {
        return this.mState;
    }
    
    @Override
    public int getTotalSteps() {
        return this.VU;
    }
    
    @Override
    public int getType() {
        return this.FD;
    }
    
    @Override
    public Uri getUnlockedImageUri() {
        return this.VQ;
    }
    
    @Override
    public String getUnlockedImageUrl() {
        return this.VR;
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public long getXpValue() {
        return this.Wa;
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
        AchievementEntityCreator.a(this, parcel, n);
    }
}
