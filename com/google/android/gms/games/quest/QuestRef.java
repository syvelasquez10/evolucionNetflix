// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.quest;

import android.os.Parcel;
import java.util.ArrayList;
import java.util.List;
import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.games.GameRef;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Game;
import com.google.android.gms.common.data.d;

public final class QuestRef extends d implements Quest
{
    private final int aaz;
    private final Game abm;
    
    QuestRef(final DataHolder dataHolder, final int n, final int aaz) {
        super(dataHolder, n);
        this.abm = new GameRef(dataHolder, n);
        this.aaz = aaz;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return QuestEntity.a(this, o);
    }
    
    public Quest freeze() {
        return new QuestEntity(this);
    }
    
    @Override
    public long getAcceptedTimestamp() {
        return this.getLong("accepted_ts");
    }
    
    @Override
    public Uri getBannerImageUri() {
        return this.aR("quest_banner_image_uri");
    }
    
    @Override
    public String getBannerImageUrl() {
        return this.getString("quest_banner_image_url");
    }
    
    @Override
    public Milestone getCurrentMilestone() {
        return this.lH().get(0);
    }
    
    @Override
    public String getDescription() {
        return this.getString("quest_description");
    }
    
    @Override
    public void getDescription(final CharArrayBuffer charArrayBuffer) {
        this.a("quest_description", charArrayBuffer);
    }
    
    @Override
    public long getEndTimestamp() {
        return this.getLong("quest_end_ts");
    }
    
    @Override
    public Game getGame() {
        return this.abm;
    }
    
    @Override
    public Uri getIconImageUri() {
        return this.aR("quest_icon_image_uri");
    }
    
    @Override
    public String getIconImageUrl() {
        return this.getString("quest_icon_image_url");
    }
    
    @Override
    public long getLastUpdatedTimestamp() {
        return this.getLong("quest_last_updated_ts");
    }
    
    @Override
    public String getName() {
        return this.getString("quest_name");
    }
    
    @Override
    public void getName(final CharArrayBuffer charArrayBuffer) {
        this.a("quest_name", charArrayBuffer);
    }
    
    @Override
    public String getQuestId() {
        return this.getString("external_quest_id");
    }
    
    @Override
    public long getStartTimestamp() {
        return this.getLong("quest_start_ts");
    }
    
    @Override
    public int getState() {
        return this.getInteger("quest_state");
    }
    
    @Override
    public int getType() {
        return this.getInteger("quest_type");
    }
    
    @Override
    public int hashCode() {
        return QuestEntity.a(this);
    }
    
    @Override
    public boolean isEndingSoon() {
        return this.lI() <= System.currentTimeMillis() + 1800000L;
    }
    
    @Override
    public List<Milestone> lH() {
        final ArrayList<MilestoneRef> list = (ArrayList<MilestoneRef>)new ArrayList<Milestone>(this.aaz);
        for (int i = 0; i < this.aaz; ++i) {
            list.add(new MilestoneRef(this.IC, this.JQ + i));
        }
        return (List<Milestone>)list;
    }
    
    @Override
    public long lI() {
        return this.getLong("notification_ts");
    }
    
    @Override
    public String toString() {
        return QuestEntity.b(this);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ((QuestEntity)this.freeze()).writeToParcel(parcel, n);
    }
}
