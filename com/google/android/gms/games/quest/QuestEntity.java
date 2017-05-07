// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.quest;

import android.os.Parcel;
import java.util.Collection;
import com.google.android.gms.games.Game;
import com.google.android.gms.internal.jv;
import android.database.CharArrayBuffer;
import com.google.android.gms.common.internal.m;
import java.util.List;
import java.util.ArrayList;
import android.net.Uri;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class QuestEntity implements SafeParcelable, Quest
{
    public static final QuestEntityCreator CREATOR;
    private final int BR;
    private final int FD;
    private final String Tg;
    private final long VZ;
    private final GameEntity aan;
    private final String acG;
    private final long acH;
    private final Uri acI;
    private final String acJ;
    private final long acK;
    private final Uri acL;
    private final String acM;
    private final long acN;
    private final long acO;
    private final ArrayList<MilestoneEntity> acP;
    private final String mName;
    private final int mState;
    
    static {
        CREATOR = new QuestEntityCreator();
    }
    
    QuestEntity(final int br, final GameEntity aan, final String acG, final long acH, final Uri acI, final String acJ, final String tg, final long acK, final long vz, final Uri acL, final String acM, final String mName, final long acN, final long acO, final int mState, final int fd, final ArrayList<MilestoneEntity> acP) {
        this.BR = br;
        this.aan = aan;
        this.acG = acG;
        this.acH = acH;
        this.acI = acI;
        this.acJ = acJ;
        this.Tg = tg;
        this.acK = acK;
        this.VZ = vz;
        this.acL = acL;
        this.acM = acM;
        this.mName = mName;
        this.acN = acN;
        this.acO = acO;
        this.mState = mState;
        this.FD = fd;
        this.acP = acP;
    }
    
    public QuestEntity(final Quest quest) {
        this.BR = 2;
        this.aan = new GameEntity(quest.getGame());
        this.acG = quest.getQuestId();
        this.acH = quest.getAcceptedTimestamp();
        this.Tg = quest.getDescription();
        this.acI = quest.getBannerImageUri();
        this.acJ = quest.getBannerImageUrl();
        this.acK = quest.getEndTimestamp();
        this.acL = quest.getIconImageUri();
        this.acM = quest.getIconImageUrl();
        this.VZ = quest.getLastUpdatedTimestamp();
        this.mName = quest.getName();
        this.acN = quest.lI();
        this.acO = quest.getStartTimestamp();
        this.mState = quest.getState();
        this.FD = quest.getType();
        final List<Milestone> lh = quest.lH();
        final int size = lh.size();
        this.acP = new ArrayList<MilestoneEntity>(size);
        for (int i = 0; i < size; ++i) {
            this.acP.add((MilestoneEntity)lh.get(i).freeze());
        }
    }
    
    static int a(final Quest quest) {
        return m.hashCode(quest.getGame(), quest.getQuestId(), quest.getAcceptedTimestamp(), quest.getBannerImageUri(), quest.getDescription(), quest.getEndTimestamp(), quest.getIconImageUri(), quest.getLastUpdatedTimestamp(), quest.lH(), quest.getName(), quest.lI(), quest.getStartTimestamp(), quest.getState());
    }
    
    static boolean a(final Quest quest, final Object o) {
        final boolean b = true;
        boolean b2;
        if (!(o instanceof Quest)) {
            b2 = false;
        }
        else {
            b2 = b;
            if (quest != o) {
                final Quest quest2 = (Quest)o;
                if (m.equal(quest2.getGame(), quest.getGame()) && m.equal(quest2.getQuestId(), quest.getQuestId()) && m.equal(quest2.getAcceptedTimestamp(), quest.getAcceptedTimestamp()) && m.equal(quest2.getBannerImageUri(), quest.getBannerImageUri()) && m.equal(quest2.getDescription(), quest.getDescription()) && m.equal(quest2.getEndTimestamp(), quest.getEndTimestamp()) && m.equal(quest2.getIconImageUri(), quest.getIconImageUri()) && m.equal(quest2.getLastUpdatedTimestamp(), quest.getLastUpdatedTimestamp()) && m.equal(quest2.lH(), quest.lH()) && m.equal(quest2.getName(), quest.getName()) && m.equal(quest2.lI(), quest.lI()) && m.equal(quest2.getStartTimestamp(), quest.getStartTimestamp())) {
                    b2 = b;
                    if (m.equal(quest2.getState(), quest.getState())) {
                        return b2;
                    }
                }
                return false;
            }
        }
        return b2;
    }
    
    static String b(final Quest quest) {
        return m.h(quest).a("Game", quest.getGame()).a("QuestId", quest.getQuestId()).a("AcceptedTimestamp", quest.getAcceptedTimestamp()).a("BannerImageUri", quest.getBannerImageUri()).a("BannerImageUrl", quest.getBannerImageUrl()).a("Description", quest.getDescription()).a("EndTimestamp", quest.getEndTimestamp()).a("IconImageUri", quest.getIconImageUri()).a("IconImageUrl", quest.getIconImageUrl()).a("LastUpdatedTimestamp", quest.getLastUpdatedTimestamp()).a("Milestones", quest.lH()).a("Name", quest.getName()).a("NotifyTimestamp", quest.lI()).a("StartTimestamp", quest.getStartTimestamp()).a("State", quest.getState()).toString();
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return a(this, o);
    }
    
    public Quest freeze() {
        return this;
    }
    
    @Override
    public long getAcceptedTimestamp() {
        return this.acH;
    }
    
    @Override
    public Uri getBannerImageUri() {
        return this.acI;
    }
    
    @Override
    public String getBannerImageUrl() {
        return this.acJ;
    }
    
    @Override
    public Milestone getCurrentMilestone() {
        return this.lH().get(0);
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
    public long getEndTimestamp() {
        return this.acK;
    }
    
    @Override
    public Game getGame() {
        return this.aan;
    }
    
    @Override
    public Uri getIconImageUri() {
        return this.acL;
    }
    
    @Override
    public String getIconImageUrl() {
        return this.acM;
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
    public String getQuestId() {
        return this.acG;
    }
    
    @Override
    public long getStartTimestamp() {
        return this.acO;
    }
    
    @Override
    public int getState() {
        return this.mState;
    }
    
    @Override
    public int getType() {
        return this.FD;
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
    public boolean isEndingSoon() {
        return this.acN <= System.currentTimeMillis() + 1800000L;
    }
    
    @Override
    public List<Milestone> lH() {
        return new ArrayList<Milestone>(this.acP);
    }
    
    @Override
    public long lI() {
        return this.acN;
    }
    
    @Override
    public String toString() {
        return b(this);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        QuestEntityCreator.a(this, parcel, n);
    }
}
