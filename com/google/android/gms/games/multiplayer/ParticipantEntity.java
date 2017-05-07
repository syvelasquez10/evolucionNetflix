// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer;

import android.os.Parcel;
import com.google.android.gms.internal.gm;
import android.database.CharArrayBuffer;
import com.google.android.gms.internal.fe;
import com.google.android.gms.internal.fo;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import android.net.Uri;
import android.os.Parcelable$Creator;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;

public final class ParticipantEntity extends GamesDowngradeableSafeParcel implements Participant
{
    public static final Parcelable$Creator<ParticipantEntity> CREATOR;
    private final String HA;
    private final Uri HF;
    private final Uri HG;
    private final String HQ;
    private final String HR;
    private final String Is;
    private final String Jg;
    private final PlayerEntity LH;
    private final int MB;
    private final boolean MC;
    private final int MD;
    private final ParticipantResult ME;
    private final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new ParticipantEntityCreatorCompat();
    }
    
    ParticipantEntity(final int xh, final String jg, final String ha, final Uri hf, final Uri hg, final int mb, final String is, final boolean mc, final PlayerEntity lh, final int md, final ParticipantResult me, final String hq, final String hr) {
        this.xH = xh;
        this.Jg = jg;
        this.HA = ha;
        this.HF = hf;
        this.HG = hg;
        this.MB = mb;
        this.Is = is;
        this.MC = mc;
        this.LH = lh;
        this.MD = md;
        this.ME = me;
        this.HQ = hq;
        this.HR = hr;
    }
    
    public ParticipantEntity(final Participant participant) {
        this.xH = 3;
        this.Jg = participant.getParticipantId();
        this.HA = participant.getDisplayName();
        this.HF = participant.getIconImageUri();
        this.HG = participant.getHiResImageUri();
        this.MB = participant.getStatus();
        this.Is = participant.gi();
        this.MC = participant.isConnectedToRoom();
        final Player player = participant.getPlayer();
        PlayerEntity lh;
        if (player == null) {
            lh = null;
        }
        else {
            lh = new PlayerEntity(player);
        }
        this.LH = lh;
        this.MD = participant.getCapabilities();
        this.ME = participant.getResult();
        this.HQ = participant.getIconImageUrl();
        this.HR = participant.getHiResImageUrl();
    }
    
    static int a(final Participant participant) {
        return fo.hashCode(participant.getPlayer(), participant.getStatus(), participant.gi(), participant.isConnectedToRoom(), participant.getDisplayName(), participant.getIconImageUri(), participant.getHiResImageUri(), participant.getCapabilities(), participant.getResult(), participant.getParticipantId());
    }
    
    static boolean a(final Participant participant, final Object o) {
        final boolean b = true;
        boolean b2;
        if (!(o instanceof Participant)) {
            b2 = false;
        }
        else {
            b2 = b;
            if (participant != o) {
                final Participant participant2 = (Participant)o;
                if (fo.equal(participant2.getPlayer(), participant.getPlayer()) && fo.equal(participant2.getStatus(), participant.getStatus()) && fo.equal(participant2.gi(), participant.gi()) && fo.equal(participant2.isConnectedToRoom(), participant.isConnectedToRoom()) && fo.equal(participant2.getDisplayName(), participant.getDisplayName()) && fo.equal(participant2.getIconImageUri(), participant.getIconImageUri()) && fo.equal(participant2.getHiResImageUri(), participant.getHiResImageUri()) && fo.equal(participant2.getCapabilities(), participant.getCapabilities()) && fo.equal(participant2.getResult(), participant.getResult())) {
                    b2 = b;
                    if (fo.equal(participant2.getParticipantId(), participant.getParticipantId())) {
                        return b2;
                    }
                }
                return false;
            }
        }
        return b2;
    }
    
    static String b(final Participant participant) {
        return fo.e(participant).a("ParticipantId", participant.getParticipantId()).a("Player", participant.getPlayer()).a("Status", participant.getStatus()).a("ClientAddress", participant.gi()).a("ConnectedToRoom", participant.isConnectedToRoom()).a("DisplayName", participant.getDisplayName()).a("IconImage", participant.getIconImageUri()).a("IconImageUrl", participant.getIconImageUrl()).a("HiResImage", participant.getHiResImageUri()).a("HiResImageUrl", participant.getHiResImageUrl()).a("Capabilities", participant.getCapabilities()).a("Result", participant.getResult()).toString();
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return a(this, o);
    }
    
    public Participant freeze() {
        return this;
    }
    
    @Override
    public int getCapabilities() {
        return this.MD;
    }
    
    @Override
    public String getDisplayName() {
        if (this.LH == null) {
            return this.HA;
        }
        return this.LH.getDisplayName();
    }
    
    @Override
    public void getDisplayName(final CharArrayBuffer charArrayBuffer) {
        if (this.LH == null) {
            gm.b(this.HA, charArrayBuffer);
            return;
        }
        this.LH.getDisplayName(charArrayBuffer);
    }
    
    @Override
    public Uri getHiResImageUri() {
        if (this.LH == null) {
            return this.HG;
        }
        return this.LH.getHiResImageUri();
    }
    
    @Override
    public String getHiResImageUrl() {
        if (this.LH == null) {
            return this.HR;
        }
        return this.LH.getHiResImageUrl();
    }
    
    @Override
    public Uri getIconImageUri() {
        if (this.LH == null) {
            return this.HF;
        }
        return this.LH.getIconImageUri();
    }
    
    @Override
    public String getIconImageUrl() {
        if (this.LH == null) {
            return this.HQ;
        }
        return this.LH.getIconImageUrl();
    }
    
    @Override
    public String getParticipantId() {
        return this.Jg;
    }
    
    @Override
    public Player getPlayer() {
        return this.LH;
    }
    
    @Override
    public ParticipantResult getResult() {
        return this.ME;
    }
    
    @Override
    public int getStatus() {
        return this.MB;
    }
    
    public int getVersionCode() {
        return this.xH;
    }
    
    @Override
    public String gi() {
        return this.Is;
    }
    
    @Override
    public int hashCode() {
        return a(this);
    }
    
    @Override
    public boolean isConnectedToRoom() {
        return this.MC;
    }
    
    public boolean isDataValid() {
        return true;
    }
    
    @Override
    public String toString() {
        return b(this);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final String s = null;
        final boolean b = false;
        if (!this.eK()) {
            ParticipantEntityCreator.a(this, parcel, n);
        }
        else {
            parcel.writeString(this.Jg);
            parcel.writeString(this.HA);
            String string;
            if (this.HF == null) {
                string = null;
            }
            else {
                string = this.HF.toString();
            }
            parcel.writeString(string);
            String string2;
            if (this.HG == null) {
                string2 = s;
            }
            else {
                string2 = this.HG.toString();
            }
            parcel.writeString(string2);
            parcel.writeInt(this.MB);
            parcel.writeString(this.Is);
            int n2;
            if (this.MC) {
                n2 = 1;
            }
            else {
                n2 = 0;
            }
            parcel.writeInt(n2);
            int n3;
            if (this.LH == null) {
                n3 = (b ? 1 : 0);
            }
            else {
                n3 = 1;
            }
            parcel.writeInt(n3);
            if (this.LH != null) {
                this.LH.writeToParcel(parcel, n);
            }
        }
    }
    
    static final class ParticipantEntityCreatorCompat extends ParticipantEntityCreator
    {
        @Override
        public ParticipantEntity av(final Parcel parcel) {
            int n = 1;
            if (GamesDowngradeableSafeParcel.c(fe.eJ()) || fe.al(ParticipantEntity.class.getCanonicalName())) {
                return super.av(parcel);
            }
            final String string = parcel.readString();
            final String string2 = parcel.readString();
            final String string3 = parcel.readString();
            Uri parse;
            if (string3 == null) {
                parse = null;
            }
            else {
                parse = Uri.parse(string3);
            }
            final String string4 = parcel.readString();
            Uri parse2;
            if (string4 == null) {
                parse2 = null;
            }
            else {
                parse2 = Uri.parse(string4);
            }
            final int int1 = parcel.readInt();
            final String string5 = parcel.readString();
            final boolean b = parcel.readInt() > 0;
            if (parcel.readInt() <= 0) {
                n = 0;
            }
            PlayerEntity playerEntity;
            if (n != 0) {
                playerEntity = (PlayerEntity)PlayerEntity.CREATOR.createFromParcel(parcel);
            }
            else {
                playerEntity = null;
            }
            return new ParticipantEntity(3, string, string2, parse, parse2, int1, string5, b, playerEntity, 7, null, null, null);
        }
    }
}
