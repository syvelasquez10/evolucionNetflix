// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.request;

import android.os.Parcel;
import com.google.android.gms.games.PlayerRef;
import java.util.ArrayList;
import com.google.android.gms.games.Player;
import java.util.List;
import com.google.android.gms.games.GameRef;
import com.google.android.gms.games.Game;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;

public final class GameRequestRef extends d implements GameRequest
{
    private final int aaz;
    
    public GameRequestRef(final DataHolder dataHolder, final int n, final int aaz) {
        super(dataHolder, n);
        this.aaz = aaz;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return GameRequestEntity.a(this, o);
    }
    
    public GameRequest freeze() {
        return new GameRequestEntity(this);
    }
    
    @Override
    public long getCreationTimestamp() {
        return this.getLong("creation_timestamp");
    }
    
    @Override
    public byte[] getData() {
        return this.getByteArray("data");
    }
    
    @Override
    public long getExpirationTimestamp() {
        return this.getLong("expiration_timestamp");
    }
    
    @Override
    public Game getGame() {
        return new GameRef(this.IC, this.JQ);
    }
    
    @Override
    public int getRecipientStatus(final String s) {
        for (int i = this.JQ; i < this.JQ + this.aaz; ++i) {
            final int ar = this.IC.ar(i);
            if (this.IC.c("recipient_external_player_id", i, ar).equals(s)) {
                return this.IC.b("recipient_status", i, ar);
            }
        }
        return -1;
    }
    
    @Override
    public List<Player> getRecipients() {
        final ArrayList<PlayerRef> list = (ArrayList<PlayerRef>)new ArrayList<Player>(this.aaz);
        for (int i = 0; i < this.aaz; ++i) {
            list.add(new PlayerRef(this.IC, this.JQ + i, "recipient_"));
        }
        return (List<Player>)list;
    }
    
    @Override
    public String getRequestId() {
        return this.getString("external_request_id");
    }
    
    @Override
    public Player getSender() {
        return new PlayerRef(this.IC, this.gA(), "sender_");
    }
    
    @Override
    public int getStatus() {
        return this.getInteger("status");
    }
    
    @Override
    public int getType() {
        return this.getInteger("type");
    }
    
    @Override
    public int hashCode() {
        return GameRequestEntity.a(this);
    }
    
    @Override
    public boolean isConsumed(final String s) {
        return this.getRecipientStatus(s) == 1;
    }
    
    @Override
    public String toString() {
        return GameRequestEntity.c(this);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ((GameRequestEntity)this.freeze()).writeToParcel(parcel, n);
    }
}
