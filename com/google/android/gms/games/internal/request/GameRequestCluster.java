// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.request;

import android.os.Parcel;
import java.util.Collection;
import com.google.android.gms.common.internal.m;
import com.google.android.gms.games.Player;
import java.util.List;
import com.google.android.gms.games.Game;
import com.google.android.gms.common.internal.a;
import com.google.android.gms.games.request.GameRequestEntity;
import java.util.ArrayList;
import com.google.android.gms.games.request.GameRequest;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class GameRequestCluster implements SafeParcelable, GameRequest
{
    public static final GameRequestClusterCreator CREATOR;
    private final int BR;
    private final ArrayList<GameRequestEntity> abg;
    
    static {
        CREATOR = new GameRequestClusterCreator();
    }
    
    GameRequestCluster(final int br, final ArrayList<GameRequestEntity> abg) {
        this.BR = br;
        this.abg = abg;
        this.lg();
    }
    
    private void lg() {
        a.I(!this.abg.isEmpty());
        final GameRequestEntity gameRequestEntity = this.abg.get(0);
        for (int size = this.abg.size(), i = 1; i < size; ++i) {
            final GameRequestEntity gameRequestEntity2 = this.abg.get(i);
            a.a(gameRequestEntity.getType() == gameRequestEntity2.getType(), "All the requests must be of the same type");
            a.a(gameRequestEntity.getSender().equals(gameRequestEntity2.getSender()), "All the requests must be from the same sender");
        }
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof GameRequestCluster)) {
            return false;
        }
        if (this == o) {
            return true;
        }
        final GameRequestCluster gameRequestCluster = (GameRequestCluster)o;
        if (gameRequestCluster.abg.size() != this.abg.size()) {
            return false;
        }
        for (int size = this.abg.size(), i = 0; i < size; ++i) {
            if (!this.abg.get(i).equals(gameRequestCluster.abg.get(i))) {
                return false;
            }
        }
        return true;
    }
    
    public GameRequest freeze() {
        return this;
    }
    
    @Override
    public long getCreationTimestamp() {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }
    
    @Override
    public byte[] getData() {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }
    
    @Override
    public long getExpirationTimestamp() {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }
    
    @Override
    public Game getGame() {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }
    
    @Override
    public int getRecipientStatus(final String s) {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }
    
    @Override
    public String getRequestId() {
        return this.abg.get(0).getRequestId();
    }
    
    @Override
    public Player getSender() {
        return this.abg.get(0).getSender();
    }
    
    @Override
    public int getStatus() {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }
    
    @Override
    public int getType() {
        return this.abg.get(0).getType();
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.abg.toArray());
    }
    
    @Override
    public boolean isConsumed(final String s) {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }
    
    public boolean isDataValid() {
        return true;
    }
    
    public ArrayList<GameRequest> lu() {
        return new ArrayList<GameRequest>(this.abg);
    }
    
    public ArrayList<Player> lv() {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        GameRequestClusterCreator.a(this, parcel, n);
    }
}
