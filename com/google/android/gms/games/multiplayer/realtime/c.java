// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer.realtime;

import android.os.Parcel;
import com.google.android.gms.games.multiplayer.d;
import java.util.ArrayList;
import com.google.android.gms.games.multiplayer.Participant;
import android.database.CharArrayBuffer;
import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.b;

public final class c extends b implements Room
{
    private final int vH;
    
    c(final DataHolder dataHolder, final int n, final int vh) {
        super(dataHolder, n);
        this.vH = vh;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return RoomEntity.a(this, o);
    }
    
    public Room freeze() {
        return new RoomEntity(this);
    }
    
    @Override
    public Bundle getAutoMatchCriteria() {
        if (!this.getBoolean("has_automatch_criteria")) {
            return null;
        }
        return RoomConfig.createAutoMatchCriteria(this.getInteger("automatch_min_players"), this.getInteger("automatch_max_players"), this.getLong("automatch_bit_mask"));
    }
    
    @Override
    public int getAutoMatchWaitEstimateSeconds() {
        return this.getInteger("automatch_wait_estimate_sec");
    }
    
    @Override
    public long getCreationTimestamp() {
        return this.getLong("creation_timestamp");
    }
    
    @Override
    public String getCreatorId() {
        return this.getString("creator_external");
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
    public Participant getParticipant(final String s) {
        return RoomEntity.c(this, s);
    }
    
    @Override
    public String getParticipantId(final String s) {
        return RoomEntity.b(this, s);
    }
    
    @Override
    public ArrayList<String> getParticipantIds() {
        return RoomEntity.c(this);
    }
    
    @Override
    public int getParticipantStatus(final String s) {
        return RoomEntity.a(this, s);
    }
    
    public ArrayList<Participant> getParticipants() {
        final ArrayList<d> list = (ArrayList<d>)new ArrayList<Participant>(this.vH);
        for (int i = 0; i < this.vH; ++i) {
            list.add(new d(this.nE, this.nG + i));
        }
        return (ArrayList<Participant>)list;
    }
    
    @Override
    public String getRoomId() {
        return this.getString("external_match_id");
    }
    
    @Override
    public int getStatus() {
        return this.getInteger("status");
    }
    
    @Override
    public int getVariant() {
        return this.getInteger("variant");
    }
    
    @Override
    public int hashCode() {
        return RoomEntity.a(this);
    }
    
    @Override
    public String toString() {
        return RoomEntity.b(this);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ((RoomEntity)this.freeze()).writeToParcel(parcel, n);
    }
}
