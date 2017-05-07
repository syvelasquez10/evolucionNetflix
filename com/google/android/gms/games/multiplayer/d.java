// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer;

import android.os.Parcel;
import com.google.android.gms.games.Player;
import android.net.Uri;
import android.database.CharArrayBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.b;

public final class d extends b implements Participant
{
    private final com.google.android.gms.games.d wx;
    
    public d(final DataHolder dataHolder, final int n) {
        super(dataHolder, n);
        this.wx = new com.google.android.gms.games.d(dataHolder, n);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public String dy() {
        return this.getString("client_address");
    }
    
    @Override
    public boolean equals(final Object o) {
        return ParticipantEntity.a(this, o);
    }
    
    public Participant freeze() {
        return new ParticipantEntity(this);
    }
    
    @Override
    public int getCapabilities() {
        return this.getInteger("capabilities");
    }
    
    @Override
    public String getDisplayName() {
        if (this.M("external_player_id")) {
            return this.getString("default_display_name");
        }
        return this.wx.getDisplayName();
    }
    
    @Override
    public void getDisplayName(final CharArrayBuffer charArrayBuffer) {
        if (this.M("external_player_id")) {
            this.a("default_display_name", charArrayBuffer);
            return;
        }
        this.wx.getDisplayName(charArrayBuffer);
    }
    
    @Override
    public Uri getHiResImageUri() {
        if (this.M("external_player_id")) {
            return this.L("default_display_hi_res_image_uri");
        }
        return this.wx.getHiResImageUri();
    }
    
    @Override
    public Uri getIconImageUri() {
        if (this.M("external_player_id")) {
            return this.L("default_display_image_uri");
        }
        return this.wx.getIconImageUri();
    }
    
    @Override
    public String getParticipantId() {
        return this.getString("external_participant_id");
    }
    
    @Override
    public Player getPlayer() {
        if (this.M("external_player_id")) {
            return null;
        }
        return this.wx;
    }
    
    @Override
    public ParticipantResult getResult() {
        if (this.M("result_type")) {
            return null;
        }
        return new ParticipantResult(this.getParticipantId(), this.getInteger("result_type"), this.getInteger("placing"));
    }
    
    @Override
    public int getStatus() {
        return this.getInteger("player_status");
    }
    
    @Override
    public int hashCode() {
        return ParticipantEntity.a(this);
    }
    
    @Override
    public boolean isConnectedToRoom() {
        return this.getInteger("connected") > 0;
    }
    
    @Override
    public String toString() {
        return ParticipantEntity.b(this);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ((ParticipantEntity)this.freeze()).writeToParcel(parcel, n);
    }
}
