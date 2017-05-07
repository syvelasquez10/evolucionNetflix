// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer.realtime;

import android.os.Parcel;
import java.util.Collection;
import com.google.android.gms.internal.jv;
import android.database.CharArrayBuffer;
import com.google.android.gms.common.internal.c;
import com.google.android.gms.games.Player;
import com.google.android.gms.common.internal.m;
import com.google.android.gms.games.multiplayer.Participant;
import android.os.Bundle;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import java.util.ArrayList;
import android.os.Parcelable$Creator;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;

public final class RoomEntity extends GamesDowngradeableSafeParcel implements Room
{
    public static final Parcelable$Creator<RoomEntity> CREATOR;
    private final int BR;
    private final String Tg;
    private final String WF;
    private final long abO;
    private final ArrayList<ParticipantEntity> abR;
    private final int abS;
    private final Bundle ach;
    private final String acl;
    private final int acm;
    private final int acn;
    
    static {
        CREATOR = (Parcelable$Creator)new RoomEntityCreatorCompat();
    }
    
    RoomEntity(final int br, final String wf, final String acl, final long abO, final int acm, final String tg, final int abS, final Bundle ach, final ArrayList<ParticipantEntity> abR, final int acn) {
        this.BR = br;
        this.WF = wf;
        this.acl = acl;
        this.abO = abO;
        this.acm = acm;
        this.Tg = tg;
        this.abS = abS;
        this.ach = ach;
        this.abR = abR;
        this.acn = acn;
    }
    
    public RoomEntity(final Room room) {
        this.BR = 2;
        this.WF = room.getRoomId();
        this.acl = room.getCreatorId();
        this.abO = room.getCreationTimestamp();
        this.acm = room.getStatus();
        this.Tg = room.getDescription();
        this.abS = room.getVariant();
        this.ach = room.getAutoMatchCriteria();
        final ArrayList<Participant> participants = room.getParticipants();
        final int size = participants.size();
        this.abR = new ArrayList<ParticipantEntity>(size);
        for (int i = 0; i < size; ++i) {
            this.abR.add((ParticipantEntity)participants.get(i).freeze());
        }
        this.acn = room.getAutoMatchWaitEstimateSeconds();
    }
    
    static int a(final Room room) {
        return m.hashCode(room.getRoomId(), room.getCreatorId(), room.getCreationTimestamp(), room.getStatus(), room.getDescription(), room.getVariant(), room.getAutoMatchCriteria(), room.getParticipants(), room.getAutoMatchWaitEstimateSeconds());
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
                if (m.equal(room2.getRoomId(), room.getRoomId()) && m.equal(room2.getCreatorId(), room.getCreatorId()) && m.equal(room2.getCreationTimestamp(), room.getCreationTimestamp()) && m.equal(room2.getStatus(), room.getStatus()) && m.equal(room2.getDescription(), room.getDescription()) && m.equal(room2.getVariant(), room.getVariant()) && m.equal(room2.getAutoMatchCriteria(), room.getAutoMatchCriteria()) && m.equal(room2.getParticipants(), room.getParticipants())) {
                    b2 = b;
                    if (m.equal(room2.getAutoMatchWaitEstimateSeconds(), room.getAutoMatchWaitEstimateSeconds())) {
                        return b2;
                    }
                }
                return false;
            }
        }
        return b2;
    }
    
    static String b(final Room room) {
        return m.h(room).a("RoomId", room.getRoomId()).a("CreatorId", room.getCreatorId()).a("CreationTimestamp", room.getCreationTimestamp()).a("RoomStatus", room.getStatus()).a("Description", room.getDescription()).a("Variant", room.getVariant()).a("AutoMatchCriteria", room.getAutoMatchCriteria()).a("Participants", room.getParticipants()).a("AutoMatchWaitEstimateSeconds", room.getAutoMatchWaitEstimateSeconds()).toString();
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
        return this.ach;
    }
    
    @Override
    public int getAutoMatchWaitEstimateSeconds() {
        return this.acn;
    }
    
    @Override
    public long getCreationTimestamp() {
        return this.abO;
    }
    
    @Override
    public String getCreatorId() {
        return this.acl;
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
        return new ArrayList<Participant>(this.abR);
    }
    
    @Override
    public String getRoomId() {
        return this.WF;
    }
    
    @Override
    public int getStatus() {
        return this.acm;
    }
    
    @Override
    public int getVariant() {
        return this.abS;
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
        if (!this.gQ()) {
            RoomEntityCreator.a(this, parcel, n);
        }
        else {
            parcel.writeString(this.WF);
            parcel.writeString(this.acl);
            parcel.writeLong(this.abO);
            parcel.writeInt(this.acm);
            parcel.writeString(this.Tg);
            parcel.writeInt(this.abS);
            parcel.writeBundle(this.ach);
            final int size = this.abR.size();
            parcel.writeInt(size);
            for (int i = 0; i < size; ++i) {
                this.abR.get(i).writeToParcel(parcel, n);
            }
        }
    }
    
    static final class RoomEntityCreatorCompat extends RoomEntityCreator
    {
        @Override
        public RoomEntity co(final Parcel parcel) {
            if (GamesDowngradeableSafeParcel.c(c.gP()) || c.aV(RoomEntity.class.getCanonicalName())) {
                return super.co(parcel);
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
