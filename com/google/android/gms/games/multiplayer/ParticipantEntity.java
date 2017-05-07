// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer;

import android.os.Parcel;
import com.google.android.gms.internal.jv;
import android.database.CharArrayBuffer;
import com.google.android.gms.common.internal.c;
import com.google.android.gms.common.internal.m;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import android.net.Uri;
import android.os.Parcelable$Creator;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;

public final class ParticipantEntity extends GamesDowngradeableSafeParcel implements Participant
{
    public static final Parcelable$Creator<ParticipantEntity> CREATOR;
    private final int BR;
    private final int EZ;
    private final int Fa;
    private final String Nz;
    private final Uri UW;
    private final Uri UX;
    private final PlayerEntity VW;
    private final String Vh;
    private final String Vi;
    private final String Wf;
    private final String Xg;
    private final boolean abV;
    private final ParticipantResult abW;
    
    static {
        CREATOR = (Parcelable$Creator)new ParticipantEntityCreatorCompat();
    }
    
    ParticipantEntity(final int br, final String xg, final String nz, final Uri uw, final Uri ux, final int fa, final String wf, final boolean abV, final PlayerEntity vw, final int ez, final ParticipantResult abW, final String vh, final String vi) {
        this.BR = br;
        this.Xg = xg;
        this.Nz = nz;
        this.UW = uw;
        this.UX = ux;
        this.Fa = fa;
        this.Wf = wf;
        this.abV = abV;
        this.VW = vw;
        this.EZ = ez;
        this.abW = abW;
        this.Vh = vh;
        this.Vi = vi;
    }
    
    public ParticipantEntity(final Participant participant) {
        this.BR = 3;
        this.Xg = participant.getParticipantId();
        this.Nz = participant.getDisplayName();
        this.UW = participant.getIconImageUri();
        this.UX = participant.getHiResImageUri();
        this.Fa = participant.getStatus();
        this.Wf = participant.jU();
        this.abV = participant.isConnectedToRoom();
        final Player player = participant.getPlayer();
        PlayerEntity vw;
        if (player == null) {
            vw = null;
        }
        else {
            vw = new PlayerEntity(player);
        }
        this.VW = vw;
        this.EZ = participant.getCapabilities();
        this.abW = participant.getResult();
        this.Vh = participant.getIconImageUrl();
        this.Vi = participant.getHiResImageUrl();
    }
    
    static int a(final Participant participant) {
        return m.hashCode(participant.getPlayer(), participant.getStatus(), participant.jU(), participant.isConnectedToRoom(), participant.getDisplayName(), participant.getIconImageUri(), participant.getHiResImageUri(), participant.getCapabilities(), participant.getResult(), participant.getParticipantId());
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
                if (m.equal(participant2.getPlayer(), participant.getPlayer()) && m.equal(participant2.getStatus(), participant.getStatus()) && m.equal(participant2.jU(), participant.jU()) && m.equal(participant2.isConnectedToRoom(), participant.isConnectedToRoom()) && m.equal(participant2.getDisplayName(), participant.getDisplayName()) && m.equal(participant2.getIconImageUri(), participant.getIconImageUri()) && m.equal(participant2.getHiResImageUri(), participant.getHiResImageUri()) && m.equal(participant2.getCapabilities(), participant.getCapabilities()) && m.equal(participant2.getResult(), participant.getResult())) {
                    b2 = b;
                    if (m.equal(participant2.getParticipantId(), participant.getParticipantId())) {
                        return b2;
                    }
                }
                return false;
            }
        }
        return b2;
    }
    
    static String b(final Participant participant) {
        return m.h(participant).a("ParticipantId", participant.getParticipantId()).a("Player", participant.getPlayer()).a("Status", participant.getStatus()).a("ClientAddress", participant.jU()).a("ConnectedToRoom", participant.isConnectedToRoom()).a("DisplayName", participant.getDisplayName()).a("IconImage", participant.getIconImageUri()).a("IconImageUrl", participant.getIconImageUrl()).a("HiResImage", participant.getHiResImageUri()).a("HiResImageUrl", participant.getHiResImageUrl()).a("Capabilities", participant.getCapabilities()).a("Result", participant.getResult()).toString();
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
        return this.EZ;
    }
    
    @Override
    public String getDisplayName() {
        if (this.VW == null) {
            return this.Nz;
        }
        return this.VW.getDisplayName();
    }
    
    @Override
    public void getDisplayName(final CharArrayBuffer charArrayBuffer) {
        if (this.VW == null) {
            jv.b(this.Nz, charArrayBuffer);
            return;
        }
        this.VW.getDisplayName(charArrayBuffer);
    }
    
    @Override
    public Uri getHiResImageUri() {
        if (this.VW == null) {
            return this.UX;
        }
        return this.VW.getHiResImageUri();
    }
    
    @Override
    public String getHiResImageUrl() {
        if (this.VW == null) {
            return this.Vi;
        }
        return this.VW.getHiResImageUrl();
    }
    
    @Override
    public Uri getIconImageUri() {
        if (this.VW == null) {
            return this.UW;
        }
        return this.VW.getIconImageUri();
    }
    
    @Override
    public String getIconImageUrl() {
        if (this.VW == null) {
            return this.Vh;
        }
        return this.VW.getIconImageUrl();
    }
    
    @Override
    public String getParticipantId() {
        return this.Xg;
    }
    
    @Override
    public Player getPlayer() {
        return this.VW;
    }
    
    @Override
    public ParticipantResult getResult() {
        return this.abW;
    }
    
    @Override
    public int getStatus() {
        return this.Fa;
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public int hashCode() {
        return a(this);
    }
    
    @Override
    public boolean isConnectedToRoom() {
        return this.abV;
    }
    
    public boolean isDataValid() {
        return true;
    }
    
    @Override
    public String jU() {
        return this.Wf;
    }
    
    @Override
    public String toString() {
        return b(this);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final String s = null;
        final boolean b = false;
        if (!this.gQ()) {
            ParticipantEntityCreator.a(this, parcel, n);
        }
        else {
            parcel.writeString(this.Xg);
            parcel.writeString(this.Nz);
            String string;
            if (this.UW == null) {
                string = null;
            }
            else {
                string = this.UW.toString();
            }
            parcel.writeString(string);
            String string2;
            if (this.UX == null) {
                string2 = s;
            }
            else {
                string2 = this.UX.toString();
            }
            parcel.writeString(string2);
            parcel.writeInt(this.Fa);
            parcel.writeString(this.Wf);
            int n2;
            if (this.abV) {
                n2 = 1;
            }
            else {
                n2 = 0;
            }
            parcel.writeInt(n2);
            int n3;
            if (this.VW == null) {
                n3 = (b ? 1 : 0);
            }
            else {
                n3 = 1;
            }
            parcel.writeInt(n3);
            if (this.VW != null) {
                this.VW.writeToParcel(parcel, n);
            }
        }
    }
    
    static final class ParticipantEntityCreatorCompat extends ParticipantEntityCreator
    {
        @Override
        public ParticipantEntity cm(final Parcel parcel) {
            int n = 1;
            if (GamesDowngradeableSafeParcel.c(c.gP()) || c.aV(ParticipantEntity.class.getCanonicalName())) {
                return super.cm(parcel);
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
