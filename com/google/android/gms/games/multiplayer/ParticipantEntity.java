// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer;

import android.os.Parcel;
import com.google.android.gms.internal.fc;
import android.database.CharArrayBuffer;
import com.google.android.gms.internal.dv;
import com.google.android.gms.internal.ee;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import android.net.Uri;
import android.os.Parcelable$Creator;
import com.google.android.gms.internal.fm;

public final class ParticipantEntity extends fm implements Participant
{
    public static final Parcelable$Creator<ParticipantEntity> CREATOR;
    private final int kg;
    private final String qa;
    private final Uri sL;
    private final Uri sM;
    private final String up;
    private final int wr;
    private final String ws;
    private final boolean wt;
    private final PlayerEntity wu;
    private final int wv;
    private final ParticipantResult ww;
    
    static {
        CREATOR = (Parcelable$Creator)new a();
    }
    
    ParticipantEntity(final int kg, final String up, final String qa, final Uri sl, final Uri sm, final int wr, final String ws, final boolean wt, final PlayerEntity wu, final int wv, final ParticipantResult ww) {
        this.kg = kg;
        this.up = up;
        this.qa = qa;
        this.sL = sl;
        this.sM = sm;
        this.wr = wr;
        this.ws = ws;
        this.wt = wt;
        this.wu = wu;
        this.wv = wv;
        this.ww = ww;
    }
    
    public ParticipantEntity(final Participant participant) {
        this.kg = 2;
        this.up = participant.getParticipantId();
        this.qa = participant.getDisplayName();
        this.sL = participant.getIconImageUri();
        this.sM = participant.getHiResImageUri();
        this.wr = participant.getStatus();
        this.ws = participant.dy();
        this.wt = participant.isConnectedToRoom();
        final Player player = participant.getPlayer();
        PlayerEntity wu;
        if (player == null) {
            wu = null;
        }
        else {
            wu = new PlayerEntity(player);
        }
        this.wu = wu;
        this.wv = participant.getCapabilities();
        this.ww = participant.getResult();
    }
    
    static int a(final Participant participant) {
        return ee.hashCode(participant.getPlayer(), participant.getStatus(), participant.dy(), participant.isConnectedToRoom(), participant.getDisplayName(), participant.getIconImageUri(), participant.getHiResImageUri(), participant.getCapabilities(), participant.getResult());
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
                if (ee.equal(participant2.getPlayer(), participant.getPlayer()) && ee.equal(participant2.getStatus(), participant.getStatus()) && ee.equal(participant2.dy(), participant.dy()) && ee.equal(participant2.isConnectedToRoom(), participant.isConnectedToRoom()) && ee.equal(participant2.getDisplayName(), participant.getDisplayName()) && ee.equal(participant2.getIconImageUri(), participant.getIconImageUri()) && ee.equal(participant2.getHiResImageUri(), participant.getHiResImageUri()) && ee.equal(participant2.getCapabilities(), participant.getCapabilities())) {
                    b2 = b;
                    if (ee.equal(participant2.getResult(), participant.getResult())) {
                        return b2;
                    }
                }
                return false;
            }
        }
        return b2;
    }
    
    static String b(final Participant participant) {
        return ee.e(participant).a("Player", participant.getPlayer()).a("Status", participant.getStatus()).a("ClientAddress", participant.dy()).a("ConnectedToRoom", participant.isConnectedToRoom()).a("DisplayName", participant.getDisplayName()).a("IconImage", participant.getIconImageUri()).a("HiResImage", participant.getHiResImageUri()).a("Capabilities", participant.getCapabilities()).a("Result", participant.getResult()).toString();
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public String dy() {
        return this.ws;
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
        return this.wv;
    }
    
    @Override
    public String getDisplayName() {
        if (this.wu == null) {
            return this.qa;
        }
        return this.wu.getDisplayName();
    }
    
    @Override
    public void getDisplayName(final CharArrayBuffer charArrayBuffer) {
        if (this.wu == null) {
            fc.b(this.qa, charArrayBuffer);
            return;
        }
        this.wu.getDisplayName(charArrayBuffer);
    }
    
    @Override
    public Uri getHiResImageUri() {
        if (this.wu == null) {
            return this.sM;
        }
        return this.wu.getHiResImageUri();
    }
    
    @Override
    public Uri getIconImageUri() {
        if (this.wu == null) {
            return this.sL;
        }
        return this.wu.getIconImageUri();
    }
    
    @Override
    public String getParticipantId() {
        return this.up;
    }
    
    @Override
    public Player getPlayer() {
        return this.wu;
    }
    
    @Override
    public ParticipantResult getResult() {
        return this.ww;
    }
    
    @Override
    public int getStatus() {
        return this.wr;
    }
    
    public int getVersionCode() {
        return this.kg;
    }
    
    @Override
    public int hashCode() {
        return a(this);
    }
    
    @Override
    public boolean isConnectedToRoom() {
        return this.wt;
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
        if (!this.bN()) {
            c.a(this, parcel, n);
        }
        else {
            parcel.writeString(this.up);
            parcel.writeString(this.qa);
            String string;
            if (this.sL == null) {
                string = null;
            }
            else {
                string = this.sL.toString();
            }
            parcel.writeString(string);
            String string2;
            if (this.sM == null) {
                string2 = s;
            }
            else {
                string2 = this.sM.toString();
            }
            parcel.writeString(string2);
            parcel.writeInt(this.wr);
            parcel.writeString(this.ws);
            int n2;
            if (this.wt) {
                n2 = 1;
            }
            else {
                n2 = 0;
            }
            parcel.writeInt(n2);
            int n3;
            if (this.wu == null) {
                n3 = (b ? 1 : 0);
            }
            else {
                n3 = 1;
            }
            parcel.writeInt(n3);
            if (this.wu != null) {
                this.wu.writeToParcel(parcel, n);
            }
        }
    }
    
    static final class a extends c
    {
        @Override
        public ParticipantEntity ab(final Parcel parcel) {
            int n = 1;
            if (fm.c(dv.bM()) || dv.P(ParticipantEntity.class.getCanonicalName())) {
                return super.ab(parcel);
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
            return new ParticipantEntity(2, string, string2, parse, parse2, int1, string5, b, playerEntity, 7, null);
        }
    }
}
