// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer.realtime;

import android.os.Parcel;
import java.util.Collection;
import com.google.android.gms.internal.gm;
import android.database.CharArrayBuffer;
import com.google.android.gms.games.Player;
import com.google.android.gms.internal.fe;
import com.google.android.gms.internal.fo;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import java.util.ArrayList;
import android.os.Bundle;
import android.os.Parcelable$Creator;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;

public final class RoomEntity extends GamesDowngradeableSafeParcel implements Room
{
    public static final Parcelable$Creator<RoomEntity> CREATOR;
    private final String HD;
    private final String Ja;
    private final Bundle MO;
    private final String MS;
    private final int MT;
    private final int MU;
    private final long Mu;
    private final ArrayList<ParticipantEntity> Mx;
    private final int My;
    private final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new RoomEntityCreatorCompat();
    }
    
    RoomEntity(final int xh, final String ja, final String ms, final long mu, final int mt, final String hd, final int my, final Bundle mo, final ArrayList<ParticipantEntity> mx, final int mu2) {
        this.xH = xh;
        this.Ja = ja;
        this.MS = ms;
        this.Mu = mu;
        this.MT = mt;
        this.HD = hd;
        this.My = my;
        this.MO = mo;
        this.Mx = mx;
        this.MU = mu2;
    }
    
    public RoomEntity(final Room room) {
        this.xH = 2;
        this.Ja = room.getRoomId();
        this.MS = room.getCreatorId();
        this.Mu = room.getCreationTimestamp();
        this.MT = room.getStatus();
        this.HD = room.getDescription();
        this.My = room.getVariant();
        this.MO = room.getAutoMatchCriteria();
        final ArrayList<Participant> participants = room.getParticipants();
        final int size = participants.size();
        this.Mx = new ArrayList<ParticipantEntity>(size);
        for (int i = 0; i < size; ++i) {
            this.Mx.add((ParticipantEntity)participants.get(i).freeze());
        }
        this.MU = room.getAutoMatchWaitEstimateSeconds();
    }
    
    static int a(final Room room) {
        return fo.hashCode(room.getRoomId(), room.getCreatorId(), room.getCreationTimestamp(), room.getStatus(), room.getDescription(), room.getVariant(), room.getAutoMatchCriteria(), room.getParticipants(), room.getAutoMatchWaitEstimateSeconds());
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
                if (fo.equal(room2.getRoomId(), room.getRoomId()) && fo.equal(room2.getCreatorId(), room.getCreatorId()) && fo.equal(room2.getCreationTimestamp(), room.getCreationTimestamp()) && fo.equal(room2.getStatus(), room.getStatus()) && fo.equal(room2.getDescription(), room.getDescription()) && fo.equal(room2.getVariant(), room.getVariant()) && fo.equal(room2.getAutoMatchCriteria(), room.getAutoMatchCriteria()) && fo.equal(room2.getParticipants(), room.getParticipants())) {
                    b2 = b;
                    if (fo.equal(room2.getAutoMatchWaitEstimateSeconds(), room.getAutoMatchWaitEstimateSeconds())) {
                        return b2;
                    }
                }
                return false;
            }
        }
        return b2;
    }
    
    static String b(final Room room) {
        return fo.e(room).a("RoomId", room.getRoomId()).a("CreatorId", room.getCreatorId()).a("CreationTimestamp", room.getCreationTimestamp()).a("RoomStatus", room.getStatus()).a("Description", room.getDescription()).a("Variant", room.getVariant()).a("AutoMatchCriteria", room.getAutoMatchCriteria()).a("Participants", room.getParticipants()).a("AutoMatchWaitEstimateSeconds", room.getAutoMatchWaitEstimateSeconds()).toString();
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
        return this.MO;
    }
    
    @Override
    public int getAutoMatchWaitEstimateSeconds() {
        return this.MU;
    }
    
    @Override
    public long getCreationTimestamp() {
        return this.Mu;
    }
    
    @Override
    public String getCreatorId() {
        return this.MS;
    }
    
    @Override
    public String getDescription() {
        return this.HD;
    }
    
    @Override
    public void getDescription(final CharArrayBuffer charArrayBuffer) {
        gm.b(this.HD, charArrayBuffer);
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
        return new ArrayList<Participant>(this.Mx);
    }
    
    @Override
    public String getRoomId() {
        return this.Ja;
    }
    
    @Override
    public int getStatus() {
        return this.MT;
    }
    
    @Override
    public int getVariant() {
        return this.My;
    }
    
    public int getVersionCode() {
        return this.xH;
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
        if (!this.eK()) {
            RoomEntityCreator.a(this, parcel, n);
        }
        else {
            parcel.writeString(this.Ja);
            parcel.writeString(this.MS);
            parcel.writeLong(this.Mu);
            parcel.writeInt(this.MT);
            parcel.writeString(this.HD);
            parcel.writeInt(this.My);
            parcel.writeBundle(this.MO);
            final int size = this.Mx.size();
            parcel.writeInt(size);
            for (int i = 0; i < size; ++i) {
                this.Mx.get(i).writeToParcel(parcel, n);
            }
        }
    }
    
    static final class RoomEntityCreatorCompat extends RoomEntityCreator
    {
        @Override
        public RoomEntity ax(final Parcel parcel) {
            if (GamesDowngradeableSafeParcel.c(fe.eJ()) || fe.al(RoomEntity.class.getCanonicalName())) {
                return super.ax(parcel);
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
