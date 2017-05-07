// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer.realtime;

import android.os.Parcel;
import java.util.Collection;
import com.google.android.gms.internal.fc;
import android.database.CharArrayBuffer;
import com.google.android.gms.games.Player;
import com.google.android.gms.internal.dv;
import com.google.android.gms.internal.ee;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import java.util.ArrayList;
import android.os.Bundle;
import android.os.Parcelable$Creator;
import com.google.android.gms.internal.fm;

public final class RoomEntity extends fm implements Room
{
    public static final Parcelable$Creator<RoomEntity> CREATOR;
    private final int kg;
    private final String sJ;
    private final String uk;
    private final Bundle wH;
    private final String wL;
    private final int wM;
    private final int wN;
    private final long wk;
    private final ArrayList<ParticipantEntity> wn;
    private final int wo;
    
    static {
        CREATOR = (Parcelable$Creator)new a();
    }
    
    RoomEntity(final int kg, final String uk, final String wl, final long wk, final int wm, final String sj, final int wo, final Bundle wh, final ArrayList<ParticipantEntity> wn, final int wn2) {
        this.kg = kg;
        this.uk = uk;
        this.wL = wl;
        this.wk = wk;
        this.wM = wm;
        this.sJ = sj;
        this.wo = wo;
        this.wH = wh;
        this.wn = wn;
        this.wN = wn2;
    }
    
    public RoomEntity(final Room room) {
        this.kg = 2;
        this.uk = room.getRoomId();
        this.wL = room.getCreatorId();
        this.wk = room.getCreationTimestamp();
        this.wM = room.getStatus();
        this.sJ = room.getDescription();
        this.wo = room.getVariant();
        this.wH = room.getAutoMatchCriteria();
        final ArrayList<Participant> participants = room.getParticipants();
        final int size = participants.size();
        this.wn = new ArrayList<ParticipantEntity>(size);
        for (int i = 0; i < size; ++i) {
            this.wn.add((ParticipantEntity)participants.get(i).freeze());
        }
        this.wN = room.getAutoMatchWaitEstimateSeconds();
    }
    
    static int a(final Room room) {
        return ee.hashCode(room.getRoomId(), room.getCreatorId(), room.getCreationTimestamp(), room.getStatus(), room.getDescription(), room.getVariant(), room.getAutoMatchCriteria(), room.getParticipants(), room.getAutoMatchWaitEstimateSeconds());
    }
    
    static int a(final Room room, final String s) {
        final ArrayList<Participant> participants = room.getParticipants();
        for (int size = participants.size(), i = 0; i < size; ++i) {
            final Participant participant = participants.get(i);
            if (participant.getParticipantId().equals(s)) {
                return participant.getStatus();
            }
        }
        throw new IllegalStateException("Participant " + s + " is not in room " + room.getRoomId());
    }
    
    static boolean a(final Room room, final Object o) {
        final boolean b = true;
        boolean b2;
        if (!(o instanceof Room)) {
            b2 = false;
        }
        else {
            b2 = b;
            if (room != o) {
                final Room room2 = (Room)o;
                if (ee.equal(room2.getRoomId(), room.getRoomId()) && ee.equal(room2.getCreatorId(), room.getCreatorId()) && ee.equal(room2.getCreationTimestamp(), room.getCreationTimestamp()) && ee.equal(room2.getStatus(), room.getStatus()) && ee.equal(room2.getDescription(), room.getDescription()) && ee.equal(room2.getVariant(), room.getVariant()) && ee.equal(room2.getAutoMatchCriteria(), room.getAutoMatchCriteria()) && ee.equal(room2.getParticipants(), room.getParticipants())) {
                    b2 = b;
                    if (ee.equal(room2.getAutoMatchWaitEstimateSeconds(), room.getAutoMatchWaitEstimateSeconds())) {
                        return b2;
                    }
                }
                return false;
            }
        }
        return b2;
    }
    
    static String b(final Room room) {
        return ee.e(room).a("RoomId", room.getRoomId()).a("CreatorId", room.getCreatorId()).a("CreationTimestamp", room.getCreationTimestamp()).a("RoomStatus", room.getStatus()).a("Description", room.getDescription()).a("Variant", room.getVariant()).a("AutoMatchCriteria", room.getAutoMatchCriteria()).a("Participants", room.getParticipants()).a("AutoMatchWaitEstimateSeconds", room.getAutoMatchWaitEstimateSeconds()).toString();
    }
    
    static String b(final Room room, final String s) {
        final ArrayList<Participant> participants = room.getParticipants();
        for (int size = participants.size(), i = 0; i < size; ++i) {
            final Participant participant = participants.get(i);
            final Player player = participant.getPlayer();
            if (player != null && player.getPlayerId().equals(s)) {
                return participant.getParticipantId();
            }
        }
        return null;
    }
    
    static Participant c(final Room room, final String s) {
        final ArrayList<Participant> participants = room.getParticipants();
        for (int size = participants.size(), i = 0; i < size; ++i) {
            final Participant participant = participants.get(i);
            if (participant.getParticipantId().equals(s)) {
                return participant;
            }
        }
        throw new IllegalStateException("Participant " + s + " is not in match " + room.getRoomId());
    }
    
    static ArrayList<String> c(final Room room) {
        final ArrayList<Participant> participants = room.getParticipants();
        final int size = participants.size();
        final ArrayList list = new ArrayList<String>(size);
        for (int i = 0; i < size; ++i) {
            list.add(participants.get(i).getParticipantId());
        }
        return (ArrayList<String>)list;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return a(this, o);
    }
    
    public Room freeze() {
        return this;
    }
    
    @Override
    public Bundle getAutoMatchCriteria() {
        return this.wH;
    }
    
    @Override
    public int getAutoMatchWaitEstimateSeconds() {
        return this.wN;
    }
    
    @Override
    public long getCreationTimestamp() {
        return this.wk;
    }
    
    @Override
    public String getCreatorId() {
        return this.wL;
    }
    
    @Override
    public String getDescription() {
        return this.sJ;
    }
    
    @Override
    public void getDescription(final CharArrayBuffer charArrayBuffer) {
        fc.b(this.sJ, charArrayBuffer);
    }
    
    @Override
    public Participant getParticipant(final String s) {
        return c(this, s);
    }
    
    @Override
    public String getParticipantId(final String s) {
        return b(this, s);
    }
    
    @Override
    public ArrayList<String> getParticipantIds() {
        return c(this);
    }
    
    @Override
    public int getParticipantStatus(final String s) {
        return a(this, s);
    }
    
    public ArrayList<Participant> getParticipants() {
        return new ArrayList<Participant>(this.wn);
    }
    
    @Override
    public String getRoomId() {
        return this.uk;
    }
    
    @Override
    public int getStatus() {
        return this.wM;
    }
    
    @Override
    public int getVariant() {
        return this.wo;
    }
    
    public int getVersionCode() {
        return this.kg;
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
        if (!this.bN()) {
            b.a(this, parcel, n);
        }
        else {
            parcel.writeString(this.uk);
            parcel.writeString(this.wL);
            parcel.writeLong(this.wk);
            parcel.writeInt(this.wM);
            parcel.writeString(this.sJ);
            parcel.writeInt(this.wo);
            parcel.writeBundle(this.wH);
            final int size = this.wn.size();
            parcel.writeInt(size);
            for (int i = 0; i < size; ++i) {
                this.wn.get(i).writeToParcel(parcel, n);
            }
        }
    }
    
    static final class a extends b
    {
        @Override
        public RoomEntity ad(final Parcel parcel) {
            if (fm.c(dv.bM()) || dv.P(RoomEntity.class.getCanonicalName())) {
                return super.ad(parcel);
            }
            final String string = parcel.readString();
            final String string2 = parcel.readString();
            final long long1 = parcel.readLong();
            final int int1 = parcel.readInt();
            final String string3 = parcel.readString();
            final int int2 = parcel.readInt();
            final Bundle bundle = parcel.readBundle();
            final int int3 = parcel.readInt();
            final ArrayList list = new ArrayList<ParticipantEntity>(int3);
            for (int i = 0; i < int3; ++i) {
                list.add(ParticipantEntity.CREATOR.createFromParcel(parcel));
            }
            return new RoomEntity(2, string, string2, long1, int1, string3, int2, bundle, (ArrayList<ParticipantEntity>)list, -1);
        }
    }
}
