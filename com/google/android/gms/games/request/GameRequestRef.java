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
import com.google.android.gms.common.data.b;

public final class GameRequestRef extends b implements GameRequest
{
    private final int LE;
    
    public GameRequestRef(final DataHolder dataHolder, final int n, final int le) {
        super(dataHolder, n);
        this.LE = le;
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
        return new GameRef(this.BB, this.BD);
    }
    
    @Override
    public int getRecipientStatus(final String s) {
        for (int i = this.BD; i < this.BD + this.LE; ++i) {
            final int g = this.BB.G(i);
            if (this.BB.getString("recipient_external_player_id", i, g).equals(s)) {
                return this.BB.getInteger("recipient_status", i, g);
            }
        }
        return -1;
    }
    
    @Override
    public List<Player> getRecipients() {
        final ArrayList<PlayerRef> list = (ArrayList<PlayerRef>)new ArrayList<Player>(this.LE);
        for (int i = 0; i < this.LE; ++i) {
            list.add(new PlayerRef(this.BB, this.BD + i, "recipient_"));
        }
        return (List<Player>)list;
    }
    
    @Override
    public String getRequestId() {
        return this.getString("external_request_id");
    }
    
    @Override
    public Player getSender() {
        return new PlayerRef(this.BB, this.BD, "sender_");
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
