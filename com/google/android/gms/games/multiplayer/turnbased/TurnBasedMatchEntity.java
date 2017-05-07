// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer.turnbased;

import android.os.Parcel;
import java.util.Collection;
import com.google.android.gms.games.Game;
import com.google.android.gms.internal.gm;
import android.database.CharArrayBuffer;
import com.google.android.gms.games.Player;
import com.google.android.gms.internal.fo;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import java.util.ArrayList;
import android.os.Bundle;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class TurnBasedMatchEntity implements SafeParcelable, TurnBasedMatch
{
    public static final TurnBasedMatchEntityCreator CREATOR;
    private final String HD;
    private final String Jb;
    private final GameEntity Lt;
    private final Bundle MO;
    private final String MS;
    private final long Mu;
    private final ArrayList<ParticipantEntity> Mx;
    private final int My;
    private final String Na;
    private final long Nb;
    private final String Nc;
    private final int Nd;
    private final int Ne;
    private final byte[] Nf;
    private final String Ng;
    private final byte[] Nh;
    private final int Ni;
    private final int Nj;
    private final boolean Nk;
    private final String Nl;
    private final int xH;
    
    static {
        CREATOR = new TurnBasedMatchEntityCreator();
    }
    
    TurnBasedMatchEntity(final int xh, final GameEntity lt, final String jb, final String ms, final long mu, final String na, final long nb, final String nc, final int nd, final int my, final int ne, final byte[] nf, final ArrayList<ParticipantEntity> mx, final String ng, final byte[] nh, final int ni, final Bundle mo, final int nj, final boolean nk, final String hd, final String nl) {
        this.xH = xh;
        this.Lt = lt;
        this.Jb = jb;
        this.MS = ms;
        this.Mu = mu;
        this.Na = na;
        this.Nb = nb;
        this.Nc = nc;
        this.Nd = nd;
        this.Nj = nj;
        this.My = my;
        this.Ne = ne;
        this.Nf = nf;
        this.Mx = mx;
        this.Ng = ng;
        this.Nh = nh;
        this.Ni = ni;
        this.MO = mo;
        this.Nk = nk;
        this.HD = hd;
        this.Nl = nl;
    }
    
    public TurnBasedMatchEntity(final TurnBasedMatch turnBasedMatch) {
        this.xH = 2;
        this.Lt = new GameEntity(turnBasedMatch.getGame());
        this.Jb = turnBasedMatch.getMatchId();
        this.MS = turnBasedMatch.getCreatorId();
        this.Mu = turnBasedMatch.getCreationTimestamp();
        this.Na = turnBasedMatch.getLastUpdaterId();
        this.Nb = turnBasedMatch.getLastUpdatedTimestamp();
        this.Nc = turnBasedMatch.getPendingParticipantId();
        this.Nd = turnBasedMatch.getStatus();
        this.Nj = turnBasedMatch.getTurnStatus();
        this.My = turnBasedMatch.getVariant();
        this.Ne = turnBasedMatch.getVersion();
        this.Ng = turnBasedMatch.getRematchId();
        this.Ni = turnBasedMatch.getMatchNumber();
        this.MO = turnBasedMatch.getAutoMatchCriteria();
        this.Nk = turnBasedMatch.isLocallyModified();
        this.HD = turnBasedMatch.getDescription();
        this.Nl = turnBasedMatch.getDescriptionParticipantId();
        final byte[] data = turnBasedMatch.getData();
        if (data == null) {
            this.Nf = null;
        }
        else {
            System.arraycopy(data, 0, this.Nf = new byte[data.length], 0, data.length);
        }
        final byte[] previousMatchData = turnBasedMatch.getPreviousMatchData();
        if (previousMatchData == null) {
            this.Nh = null;
        }
        else {
            System.arraycopy(previousMatchData, 0, this.Nh = new byte[previousMatchData.length], 0, previousMatchData.length);
        }
        final ArrayList<Participant> participants = turnBasedMatch.getParticipants();
        final int size = participants.size();
        this.Mx = new ArrayList<ParticipantEntity>(size);
        for (int i = 0; i < size; ++i) {
            this.Mx.add((ParticipantEntity)participants.get(i).freeze());
        }
    }
    
    static int a(final TurnBasedMatch turnBasedMatch) {
        return fo.hashCode(turnBasedMatch.getGame(), turnBasedMatch.getMatchId(), turnBasedMatch.getCreatorId(), turnBasedMatch.getCreationTimestamp(), turnBasedMatch.getLastUpdaterId(), turnBasedMatch.getLastUpdatedTimestamp(), turnBasedMatch.getPendingParticipantId(), turnBasedMatch.getStatus(), turnBasedMatch.getTurnStatus(), turnBasedMatch.getDescription(), turnBasedMatch.getVariant(), turnBasedMatch.getVersion(), turnBasedMatch.getParticipants(), turnBasedMatch.getRematchId(), turnBasedMatch.getMatchNumber(), turnBasedMatch.getAutoMatchCriteria(), turnBasedMatch.getAvailableAutoMatchSlots(), turnBasedMatch.isLocallyModified());
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
                if (fo.equal(turnBasedMatch2.getGame(), turnBasedMatch.getGame()) && fo.equal(turnBasedMatch2.getMatchId(), turnBasedMatch.getMatchId()) && fo.equal(turnBasedMatch2.getCreatorId(), turnBasedMatch.getCreatorId()) && fo.equal(turnBasedMatch2.getCreationTimestamp(), turnBasedMatch.getCreationTimestamp()) && fo.equal(turnBasedMatch2.getLastUpdaterId(), turnBasedMatch.getLastUpdaterId()) && fo.equal(turnBasedMatch2.getLastUpdatedTimestamp(), turnBasedMatch.getLastUpdatedTimestamp()) && fo.equal(turnBasedMatch2.getPendingParticipantId(), turnBasedMatch.getPendingParticipantId()) && fo.equal(turnBasedMatch2.getStatus(), turnBasedMatch.getStatus()) && fo.equal(turnBasedMatch2.getTurnStatus(), turnBasedMatch.getTurnStatus()) && fo.equal(turnBasedMatch2.getDescription(), turnBasedMatch.getDescription()) && fo.equal(turnBasedMatch2.getVariant(), turnBasedMatch.getVariant()) && fo.equal(turnBasedMatch2.getVersion(), turnBasedMatch.getVersion()) && fo.equal(turnBasedMatch2.getParticipants(), turnBasedMatch.getParticipants()) && fo.equal(turnBasedMatch2.getRematchId(), turnBasedMatch.getRematchId()) && fo.equal(turnBasedMatch2.getMatchNumber(), turnBasedMatch.getMatchNumber()) && fo.equal(turnBasedMatch2.getAutoMatchCriteria(), turnBasedMatch.getAutoMatchCriteria()) && fo.equal(turnBasedMatch2.getAvailableAutoMatchSlots(), turnBasedMatch.getAvailableAutoMatchSlots())) {
                    b2 = b;
                    if (fo.equal(turnBasedMatch2.isLocallyModified(), turnBasedMatch.isLocallyModified())) {
                        return b2;
                    }
                }
                return false;
            }
        }
        return b2;
    }
    
    static String b(final TurnBasedMatch turnBasedMatch) {
        return fo.e(turnBasedMatch).a("Game", turnBasedMatch.getGame()).a("MatchId", turnBasedMatch.getMatchId()).a("CreatorId", turnBasedMatch.getCreatorId()).a("CreationTimestamp", turnBasedMatch.getCreationTimestamp()).a("LastUpdaterId", turnBasedMatch.getLastUpdaterId()).a("LastUpdatedTimestamp", turnBasedMatch.getLastUpdatedTimestamp()).a("PendingParticipantId", turnBasedMatch.getPendingParticipantId()).a("MatchStatus", turnBasedMatch.getStatus()).a("TurnStatus", turnBasedMatch.getTurnStatus()).a("Description", turnBasedMatch.getDescription()).a("Variant", turnBasedMatch.getVariant()).a("Data", turnBasedMatch.getData()).a("Version", turnBasedMatch.getVersion()).a("Participants", turnBasedMatch.getParticipants()).a("RematchId", turnBasedMatch.getRematchId()).a("PreviousData", turnBasedMatch.getPreviousMatchData()).a("MatchNumber", turnBasedMatch.getMatchNumber()).a("AutoMatchCriteria", turnBasedMatch.getAutoMatchCriteria()).a("AvailableAutoMatchSlots", turnBasedMatch.getAvailableAutoMatchSlots()).a("LocallyModified", turnBasedMatch.isLocallyModified()).a("DescriptionParticipantId", turnBasedMatch.getDescriptionParticipantId()).toString();
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
        return this.Nd == 2 && this.Ng == null;
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
        return this.MO;
    }
    
    @Override
    public int getAvailableAutoMatchSlots() {
        if (this.MO == null) {
            return 0;
        }
        return this.MO.getInt("max_automatch_players");
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
    public byte[] getData() {
        return this.Nf;
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
    public Participant getDescriptionParticipant() {
        return this.getParticipant(this.getDescriptionParticipantId());
    }
    
    @Override
    public String getDescriptionParticipantId() {
        return this.Nl;
    }
    
    @Override
    public Game getGame() {
        return this.Lt;
    }
    
    @Override
    public long getLastUpdatedTimestamp() {
        return this.Nb;
    }
    
    @Override
    public String getLastUpdaterId() {
        return this.Na;
    }
    
    @Override
    public String getMatchId() {
        return this.Jb;
    }
    
    @Override
    public int getMatchNumber() {
        return this.Ni;
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
    public String getPendingParticipantId() {
        return this.Nc;
    }
    
    @Override
    public byte[] getPreviousMatchData() {
        return this.Nh;
    }
    
    @Override
    public String getRematchId() {
        return this.Ng;
    }
    
    @Override
    public int getStatus() {
        return this.Nd;
    }
    
    @Override
    public int getTurnStatus() {
        return this.Nj;
    }
    
    @Override
    public int getVariant() {
        return this.My;
    }
    
    @Override
    public int getVersion() {
        return this.Ne;
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
    public boolean isLocallyModified() {
        return this.Nk;
    }
    
    @Override
    public String toString() {
        return b(this);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        TurnBasedMatchEntityCreator.a(this, parcel, n);
    }
}
