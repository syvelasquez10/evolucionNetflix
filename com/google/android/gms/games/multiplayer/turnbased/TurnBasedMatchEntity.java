// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer.turnbased;

import android.os.Parcel;
import java.util.Collection;
import com.google.android.gms.games.Game;
import com.google.android.gms.internal.jv;
import android.database.CharArrayBuffer;
import com.google.android.gms.games.Player;
import com.google.android.gms.common.internal.m;
import com.google.android.gms.games.multiplayer.Participant;
import android.os.Bundle;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import java.util.ArrayList;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class TurnBasedMatchEntity implements SafeParcelable, TurnBasedMatch
{
    public static final TurnBasedMatchEntityCreator CREATOR;
    private final int BR;
    private final int Di;
    private final String Tg;
    private final long VZ;
    private final String WW;
    private final GameEntity aan;
    private final long abO;
    private final ArrayList<ParticipantEntity> abR;
    private final int abS;
    private final int acA;
    private final boolean acB;
    private final String acC;
    private final Bundle ach;
    private final String acl;
    private final String act;
    private final String acu;
    private final int acv;
    private final byte[] acw;
    private final String acx;
    private final byte[] acy;
    private final int acz;
    
    static {
        CREATOR = new TurnBasedMatchEntityCreator();
    }
    
    TurnBasedMatchEntity(final int br, final GameEntity aan, final String ww, final String acl, final long abO, final String act, final long vz, final String acu, final int acv, final int abS, final int di, final byte[] acw, final ArrayList<ParticipantEntity> abR, final String acx, final byte[] acy, final int acz, final Bundle ach, final int acA, final boolean acB, final String tg, final String acC) {
        this.BR = br;
        this.aan = aan;
        this.WW = ww;
        this.acl = acl;
        this.abO = abO;
        this.act = act;
        this.VZ = vz;
        this.acu = acu;
        this.acv = acv;
        this.acA = acA;
        this.abS = abS;
        this.Di = di;
        this.acw = acw;
        this.abR = abR;
        this.acx = acx;
        this.acy = acy;
        this.acz = acz;
        this.ach = ach;
        this.acB = acB;
        this.Tg = tg;
        this.acC = acC;
    }
    
    public TurnBasedMatchEntity(final TurnBasedMatch turnBasedMatch) {
        this.BR = 2;
        this.aan = new GameEntity(turnBasedMatch.getGame());
        this.WW = turnBasedMatch.getMatchId();
        this.acl = turnBasedMatch.getCreatorId();
        this.abO = turnBasedMatch.getCreationTimestamp();
        this.act = turnBasedMatch.getLastUpdaterId();
        this.VZ = turnBasedMatch.getLastUpdatedTimestamp();
        this.acu = turnBasedMatch.getPendingParticipantId();
        this.acv = turnBasedMatch.getStatus();
        this.acA = turnBasedMatch.getTurnStatus();
        this.abS = turnBasedMatch.getVariant();
        this.Di = turnBasedMatch.getVersion();
        this.acx = turnBasedMatch.getRematchId();
        this.acz = turnBasedMatch.getMatchNumber();
        this.ach = turnBasedMatch.getAutoMatchCriteria();
        this.acB = turnBasedMatch.isLocallyModified();
        this.Tg = turnBasedMatch.getDescription();
        this.acC = turnBasedMatch.getDescriptionParticipantId();
        final byte[] data = turnBasedMatch.getData();
        if (data == null) {
            this.acw = null;
        }
        else {
            System.arraycopy(data, 0, this.acw = new byte[data.length], 0, data.length);
        }
        final byte[] previousMatchData = turnBasedMatch.getPreviousMatchData();
        if (previousMatchData == null) {
            this.acy = null;
        }
        else {
            System.arraycopy(previousMatchData, 0, this.acy = new byte[previousMatchData.length], 0, previousMatchData.length);
        }
        final ArrayList<Participant> participants = turnBasedMatch.getParticipants();
        final int size = participants.size();
        this.abR = new ArrayList<ParticipantEntity>(size);
        for (int i = 0; i < size; ++i) {
            this.abR.add((ParticipantEntity)participants.get(i).freeze());
        }
    }
    
    static int a(final TurnBasedMatch turnBasedMatch) {
        return m.hashCode(turnBasedMatch.getGame(), turnBasedMatch.getMatchId(), turnBasedMatch.getCreatorId(), turnBasedMatch.getCreationTimestamp(), turnBasedMatch.getLastUpdaterId(), turnBasedMatch.getLastUpdatedTimestamp(), turnBasedMatch.getPendingParticipantId(), turnBasedMatch.getStatus(), turnBasedMatch.getTurnStatus(), turnBasedMatch.getDescription(), turnBasedMatch.getVariant(), turnBasedMatch.getVersion(), turnBasedMatch.getParticipants(), turnBasedMatch.getRematchId(), turnBasedMatch.getMatchNumber(), turnBasedMatch.getAutoMatchCriteria(), turnBasedMatch.getAvailableAutoMatchSlots(), turnBasedMatch.isLocallyModified());
    }
    
    static int a(final TurnBasedMatch turnBasedMatch, final String s) {
        final ArrayList<Participant> participants = turnBasedMatch.getParticipants();
        for (int size = participants.size(), i = 0; i < size; ++i) {
            final Participant participant = participants.get(i);
            if (participant.getParticipantId().equals(s)) {
                return participant.getStatus();
            }
        }
        throw new IllegalStateException("Participant " + s + " is not in match " + turnBasedMatch.getMatchId());
    }
    
    static boolean a(final TurnBasedMatch turnBasedMatch, final Object o) {
        final boolean b = true;
        boolean b2;
        if (!(o instanceof TurnBasedMatch)) {
            b2 = false;
        }
        else {
            b2 = b;
            if (turnBasedMatch != o) {
                final TurnBasedMatch turnBasedMatch2 = (TurnBasedMatch)o;
                if (m.equal(turnBasedMatch2.getGame(), turnBasedMatch.getGame()) && m.equal(turnBasedMatch2.getMatchId(), turnBasedMatch.getMatchId()) && m.equal(turnBasedMatch2.getCreatorId(), turnBasedMatch.getCreatorId()) && m.equal(turnBasedMatch2.getCreationTimestamp(), turnBasedMatch.getCreationTimestamp()) && m.equal(turnBasedMatch2.getLastUpdaterId(), turnBasedMatch.getLastUpdaterId()) && m.equal(turnBasedMatch2.getLastUpdatedTimestamp(), turnBasedMatch.getLastUpdatedTimestamp()) && m.equal(turnBasedMatch2.getPendingParticipantId(), turnBasedMatch.getPendingParticipantId()) && m.equal(turnBasedMatch2.getStatus(), turnBasedMatch.getStatus()) && m.equal(turnBasedMatch2.getTurnStatus(), turnBasedMatch.getTurnStatus()) && m.equal(turnBasedMatch2.getDescription(), turnBasedMatch.getDescription()) && m.equal(turnBasedMatch2.getVariant(), turnBasedMatch.getVariant()) && m.equal(turnBasedMatch2.getVersion(), turnBasedMatch.getVersion()) && m.equal(turnBasedMatch2.getParticipants(), turnBasedMatch.getParticipants()) && m.equal(turnBasedMatch2.getRematchId(), turnBasedMatch.getRematchId()) && m.equal(turnBasedMatch2.getMatchNumber(), turnBasedMatch.getMatchNumber()) && m.equal(turnBasedMatch2.getAutoMatchCriteria(), turnBasedMatch.getAutoMatchCriteria()) && m.equal(turnBasedMatch2.getAvailableAutoMatchSlots(), turnBasedMatch.getAvailableAutoMatchSlots())) {
                    b2 = b;
                    if (m.equal(turnBasedMatch2.isLocallyModified(), turnBasedMatch.isLocallyModified())) {
                        return b2;
                    }
                }
                return false;
            }
        }
        return b2;
    }
    
    static String b(final TurnBasedMatch turnBasedMatch) {
        return m.h(turnBasedMatch).a("Game", turnBasedMatch.getGame()).a("MatchId", turnBasedMatch.getMatchId()).a("CreatorId", turnBasedMatch.getCreatorId()).a("CreationTimestamp", turnBasedMatch.getCreationTimestamp()).a("LastUpdaterId", turnBasedMatch.getLastUpdaterId()).a("LastUpdatedTimestamp", turnBasedMatch.getLastUpdatedTimestamp()).a("PendingParticipantId", turnBasedMatch.getPendingParticipantId()).a("MatchStatus", turnBasedMatch.getStatus()).a("TurnStatus", turnBasedMatch.getTurnStatus()).a("Description", turnBasedMatch.getDescription()).a("Variant", turnBasedMatch.getVariant()).a("Data", turnBasedMatch.getData()).a("Version", turnBasedMatch.getVersion()).a("Participants", turnBasedMatch.getParticipants()).a("RematchId", turnBasedMatch.getRematchId()).a("PreviousData", turnBasedMatch.getPreviousMatchData()).a("MatchNumber", turnBasedMatch.getMatchNumber()).a("AutoMatchCriteria", turnBasedMatch.getAutoMatchCriteria()).a("AvailableAutoMatchSlots", turnBasedMatch.getAvailableAutoMatchSlots()).a("LocallyModified", turnBasedMatch.isLocallyModified()).a("DescriptionParticipantId", turnBasedMatch.getDescriptionParticipantId()).toString();
    }
    
    static String b(final TurnBasedMatch turnBasedMatch, final String s) {
        final ArrayList<Participant> participants = turnBasedMatch.getParticipants();
        for (int size = participants.size(), i = 0; i < size; ++i) {
            final Participant participant = participants.get(i);
            final Player player = participant.getPlayer();
            if (player != null && player.getPlayerId().equals(s)) {
                return participant.getParticipantId();
            }
        }
        return null;
    }
    
    static Participant c(final TurnBasedMatch turnBasedMatch, final String s) {
        final ArrayList<Participant> participants = turnBasedMatch.getParticipants();
        for (int size = participants.size(), i = 0; i < size; ++i) {
            final Participant participant = participants.get(i);
            if (participant.getParticipantId().equals(s)) {
                return participant;
            }
        }
        throw new IllegalStateException("Participant " + s + " is not in match " + turnBasedMatch.getMatchId());
    }
    
    static ArrayList<String> c(final TurnBasedMatch turnBasedMatch) {
        final ArrayList<Participant> participants = turnBasedMatch.getParticipants();
        final int size = participants.size();
        final ArrayList list = new ArrayList<String>(size);
        for (int i = 0; i < size; ++i) {
            list.add(participants.get(i).getParticipantId());
        }
        return (ArrayList<String>)list;
    }
    
    @Override
    public boolean canRematch() {
        return this.acv == 2 && this.acx == null;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return a(this, o);
    }
    
    public TurnBasedMatch freeze() {
        return this;
    }
    
    @Override
    public Bundle getAutoMatchCriteria() {
        return this.ach;
    }
    
    @Override
    public int getAvailableAutoMatchSlots() {
        if (this.ach == null) {
            return 0;
        }
        return this.ach.getInt("max_automatch_players");
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
    public byte[] getData() {
        return this.acw;
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
    public Participant getDescriptionParticipant() {
        final String descriptionParticipantId = this.getDescriptionParticipantId();
        if (descriptionParticipantId == null) {
            return null;
        }
        return this.getParticipant(descriptionParticipantId);
    }
    
    @Override
    public String getDescriptionParticipantId() {
        return this.acC;
    }
    
    @Override
    public Game getGame() {
        return this.aan;
    }
    
    @Override
    public long getLastUpdatedTimestamp() {
        return this.VZ;
    }
    
    @Override
    public String getLastUpdaterId() {
        return this.act;
    }
    
    @Override
    public String getMatchId() {
        return this.WW;
    }
    
    @Override
    public int getMatchNumber() {
        return this.acz;
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
    public String getPendingParticipantId() {
        return this.acu;
    }
    
    @Override
    public byte[] getPreviousMatchData() {
        return this.acy;
    }
    
    @Override
    public String getRematchId() {
        return this.acx;
    }
    
    @Override
    public int getStatus() {
        return this.acv;
    }
    
    @Override
    public int getTurnStatus() {
        return this.acA;
    }
    
    @Override
    public int getVariant() {
        return this.abS;
    }
    
    @Override
    public int getVersion() {
        return this.Di;
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
    public boolean isLocallyModified() {
        return this.acB;
    }
    
    @Override
    public String toString() {
        return b(this);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        TurnBasedMatchEntityCreator.a(this, parcel, n);
    }
}
