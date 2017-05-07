// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer.turnbased;

import android.os.Parcel;
import java.util.Collection;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.Player;
import com.google.android.gms.internal.ee;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import java.util.ArrayList;
import com.google.android.gms.games.GameEntity;
import android.os.Bundle;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class TurnBasedMatchEntity implements SafeParcelable, TurnBasedMatch
{
    public static final TurnBasedMatchEntityCreator CREATOR;
    private final int kg;
    private final String ul;
    private final Bundle wH;
    private final String wL;
    private final String wU;
    private final long wV;
    private final String wW;
    private final int wX;
    private final int wY;
    private final byte[] wZ;
    private final GameEntity wj;
    private final long wk;
    private final ArrayList<ParticipantEntity> wn;
    private final int wo;
    private final String xa;
    private final byte[] xb;
    private final int xc;
    private final int xd;
    private final boolean xe;
    
    static {
        CREATOR = new TurnBasedMatchEntityCreator();
    }
    
    TurnBasedMatchEntity(final int kg, final GameEntity wj, final String ul, final String wl, final long wk, final String wu, final long wv, final String ww, final int wx, final int wo, final int wy, final byte[] wz, final ArrayList<ParticipantEntity> wn, final String xa, final byte[] xb, final int xc, final Bundle wh, final int xd, final boolean xe) {
        this.kg = kg;
        this.wj = wj;
        this.ul = ul;
        this.wL = wl;
        this.wk = wk;
        this.wU = wu;
        this.wV = wv;
        this.wW = ww;
        this.wX = wx;
        this.xd = xd;
        this.wo = wo;
        this.wY = wy;
        this.wZ = wz;
        this.wn = wn;
        this.xa = xa;
        this.xb = xb;
        this.xc = xc;
        this.wH = wh;
        this.xe = xe;
    }
    
    public TurnBasedMatchEntity(final TurnBasedMatch turnBasedMatch) {
        this.kg = 2;
        this.wj = new GameEntity(turnBasedMatch.getGame());
        this.ul = turnBasedMatch.getMatchId();
        this.wL = turnBasedMatch.getCreatorId();
        this.wk = turnBasedMatch.getCreationTimestamp();
        this.wU = turnBasedMatch.getLastUpdaterId();
        this.wV = turnBasedMatch.getLastUpdatedTimestamp();
        this.wW = turnBasedMatch.getPendingParticipantId();
        this.wX = turnBasedMatch.getStatus();
        this.xd = turnBasedMatch.getTurnStatus();
        this.wo = turnBasedMatch.getVariant();
        this.wY = turnBasedMatch.getVersion();
        this.xa = turnBasedMatch.getRematchId();
        this.xc = turnBasedMatch.getMatchNumber();
        this.wH = turnBasedMatch.getAutoMatchCriteria();
        this.xe = turnBasedMatch.isLocallyModified();
        final byte[] data = turnBasedMatch.getData();
        if (data == null) {
            this.wZ = null;
        }
        else {
            System.arraycopy(data, 0, this.wZ = new byte[data.length], 0, data.length);
        }
        final byte[] previousMatchData = turnBasedMatch.getPreviousMatchData();
        if (previousMatchData == null) {
            this.xb = null;
        }
        else {
            System.arraycopy(previousMatchData, 0, this.xb = new byte[previousMatchData.length], 0, previousMatchData.length);
        }
        final ArrayList<Participant> participants = turnBasedMatch.getParticipants();
        final int size = participants.size();
        this.wn = new ArrayList<ParticipantEntity>(size);
        for (int i = 0; i < size; ++i) {
            this.wn.add((ParticipantEntity)participants.get(i).freeze());
        }
    }
    
    static int a(final TurnBasedMatch turnBasedMatch) {
        return ee.hashCode(turnBasedMatch.getGame(), turnBasedMatch.getMatchId(), turnBasedMatch.getCreatorId(), turnBasedMatch.getCreationTimestamp(), turnBasedMatch.getLastUpdaterId(), turnBasedMatch.getLastUpdatedTimestamp(), turnBasedMatch.getPendingParticipantId(), turnBasedMatch.getStatus(), turnBasedMatch.getTurnStatus(), turnBasedMatch.getVariant(), turnBasedMatch.getVersion(), turnBasedMatch.getParticipants(), turnBasedMatch.getRematchId(), turnBasedMatch.getMatchNumber(), turnBasedMatch.getAutoMatchCriteria(), turnBasedMatch.getAvailableAutoMatchSlots(), turnBasedMatch.isLocallyModified());
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
                if (ee.equal(turnBasedMatch2.getGame(), turnBasedMatch.getGame()) && ee.equal(turnBasedMatch2.getMatchId(), turnBasedMatch.getMatchId()) && ee.equal(turnBasedMatch2.getCreatorId(), turnBasedMatch.getCreatorId()) && ee.equal(turnBasedMatch2.getCreationTimestamp(), turnBasedMatch.getCreationTimestamp()) && ee.equal(turnBasedMatch2.getLastUpdaterId(), turnBasedMatch.getLastUpdaterId()) && ee.equal(turnBasedMatch2.getLastUpdatedTimestamp(), turnBasedMatch.getLastUpdatedTimestamp()) && ee.equal(turnBasedMatch2.getPendingParticipantId(), turnBasedMatch.getPendingParticipantId()) && ee.equal(turnBasedMatch2.getStatus(), turnBasedMatch.getStatus()) && ee.equal(turnBasedMatch2.getTurnStatus(), turnBasedMatch.getTurnStatus()) && ee.equal(turnBasedMatch2.getVariant(), turnBasedMatch.getVariant()) && ee.equal(turnBasedMatch2.getVersion(), turnBasedMatch.getVersion()) && ee.equal(turnBasedMatch2.getParticipants(), turnBasedMatch.getParticipants()) && ee.equal(turnBasedMatch2.getRematchId(), turnBasedMatch.getRematchId()) && ee.equal(turnBasedMatch2.getMatchNumber(), turnBasedMatch.getMatchNumber()) && ee.equal(turnBasedMatch2.getAutoMatchCriteria(), turnBasedMatch.getAutoMatchCriteria()) && ee.equal(turnBasedMatch2.getAvailableAutoMatchSlots(), turnBasedMatch.getAvailableAutoMatchSlots())) {
                    b2 = b;
                    if (ee.equal(turnBasedMatch2.isLocallyModified(), turnBasedMatch.isLocallyModified())) {
                        return b2;
                    }
                }
                return false;
            }
        }
        return b2;
    }
    
    static String b(final TurnBasedMatch turnBasedMatch) {
        return ee.e(turnBasedMatch).a("Game", turnBasedMatch.getGame()).a("MatchId", turnBasedMatch.getMatchId()).a("CreatorId", turnBasedMatch.getCreatorId()).a("CreationTimestamp", turnBasedMatch.getCreationTimestamp()).a("LastUpdaterId", turnBasedMatch.getLastUpdaterId()).a("LastUpdatedTimestamp", turnBasedMatch.getLastUpdatedTimestamp()).a("PendingParticipantId", turnBasedMatch.getPendingParticipantId()).a("MatchStatus", turnBasedMatch.getStatus()).a("TurnStatus", turnBasedMatch.getTurnStatus()).a("Variant", turnBasedMatch.getVariant()).a("Data", turnBasedMatch.getData()).a("Version", turnBasedMatch.getVersion()).a("Participants", turnBasedMatch.getParticipants()).a("RematchId", turnBasedMatch.getRematchId()).a("PreviousData", turnBasedMatch.getPreviousMatchData()).a("MatchNumber", turnBasedMatch.getMatchNumber()).a("AutoMatchCriteria", turnBasedMatch.getAutoMatchCriteria()).a("AvailableAutoMatchSlots", turnBasedMatch.getAvailableAutoMatchSlots()).a("LocallyModified", turnBasedMatch.isLocallyModified()).toString();
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
        return this.wX == 2 && this.xa == null;
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
        return this.wH;
    }
    
    @Override
    public int getAvailableAutoMatchSlots() {
        if (this.wH == null) {
            return 0;
        }
        return this.wH.getInt("max_automatch_players");
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
    public byte[] getData() {
        return this.wZ;
    }
    
    @Override
    public Game getGame() {
        return this.wj;
    }
    
    @Override
    public long getLastUpdatedTimestamp() {
        return this.wV;
    }
    
    @Override
    public String getLastUpdaterId() {
        return this.wU;
    }
    
    @Override
    public String getMatchId() {
        return this.ul;
    }
    
    @Override
    public int getMatchNumber() {
        return this.xc;
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
    public String getPendingParticipantId() {
        return this.wW;
    }
    
    @Override
    public byte[] getPreviousMatchData() {
        return this.xb;
    }
    
    @Override
    public String getRematchId() {
        return this.xa;
    }
    
    @Override
    public int getStatus() {
        return this.wX;
    }
    
    @Override
    public int getTurnStatus() {
        return this.xd;
    }
    
    @Override
    public int getVariant() {
        return this.wo;
    }
    
    @Override
    public int getVersion() {
        return this.wY;
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
    public boolean isLocallyModified() {
        return this.xe;
    }
    
    @Override
    public String toString() {
        return b(this);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        TurnBasedMatchEntityCreator.a(this, parcel, n);
    }
}
