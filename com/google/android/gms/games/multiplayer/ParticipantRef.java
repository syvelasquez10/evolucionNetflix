// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer;

import android.os.Parcel;
import com.google.android.gms.games.Player;
import android.net.Uri;
import android.database.CharArrayBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.PlayerRef;
import com.google.android.gms.common.data.b;

public final class ParticipantRef extends b implements Participant
{
    private final PlayerRef LL;
    
    public ParticipantRef(final DataHolder dataHolder, final int n) {
        super(dataHolder, n);
        this.LL = new PlayerRef(dataHolder, n);
    }
    
    public int describeContents() {
        return 0;
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
        if (this.ai("external_player_id")) {
            return this.getString("default_display_name");
        }
        return this.LL.getDisplayName();
    }
    
    @Override
    public void getDisplayName(final CharArrayBuffer charArrayBuffer) {
        if (this.ai("external_player_id")) {
            this.a("default_display_name", charArrayBuffer);
            return;
        }
        this.LL.getDisplayName(charArrayBuffer);
    }
    
    @Override
    public Uri getHiResImageUri() {
        if (this.ai("external_player_id")) {
            return this.ah("default_display_hi_res_image_uri");
        }
        return this.LL.getHiResImageUri();
    }
    
    @Override
    public String getHiResImageUrl() {
        if (this.ai("external_player_id")) {
            return this.getString("default_display_hi_res_image_url");
        }
        return this.LL.getHiResImageUrl();
    }
    
    @Override
    public Uri getIconImageUri() {
        if (this.ai("external_player_id")) {
            return this.ah("default_display_image_uri");
        }
        return this.LL.getIconImageUri();
    }
    
    @Override
    public String getIconImageUrl() {
        if (this.ai("external_player_id")) {
            return this.getString("default_display_image_url");
        }
        return this.LL.getIconImageUrl();
    }
    
    @Override
    public String getParticipantId() {
        return this.getString("external_participant_id");
    }
    
    @Override
    public Player getPlayer() {
        if (this.ai("external_player_id")) {
            return null;
        }
        return this.LL;
    }
    
    @Override
    public ParticipantResult getResult() {
        if (this.ai("result_type")) {
            return null;
        }
        return new ParticipantResult(this.getParticipantId(), this.getInteger("result_type"), this.getInteger("placing"));
    }
    
    @Override
    public int getStatus() {
        return this.getInteger("player_status");
    }
    
    @Override
    public String gi() {
        return this.getString("client_address");
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
