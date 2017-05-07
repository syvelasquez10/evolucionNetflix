// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.request;

import android.os.Parcel;
import java.util.Collection;
import com.google.android.gms.games.Game;
import java.util.Arrays;
import com.google.android.gms.common.internal.m;
import java.util.List;
import com.google.android.gms.games.Player;
import android.os.Bundle;
import java.util.ArrayList;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class GameRequestEntity implements SafeParcelable, GameRequest
{
    public static final GameRequestEntityCreator CREATOR;
    private final int BR;
    private final int FD;
    private final int Fa;
    private final String Xr;
    private final GameEntity aan;
    private final long abO;
    private final PlayerEntity acR;
    private final ArrayList<PlayerEntity> acS;
    private final long acT;
    private final Bundle acU;
    private final byte[] acw;
    
    static {
        CREATOR = new GameRequestEntityCreator();
    }
    
    GameRequestEntity(final int br, final GameEntity aan, final PlayerEntity acR, final byte[] acw, final String xr, final ArrayList<PlayerEntity> acS, final int fd, final long abO, final long acT, final Bundle acU, final int fa) {
        this.BR = br;
        this.aan = aan;
        this.acR = acR;
        this.acw = acw;
        this.Xr = xr;
        this.acS = acS;
        this.FD = fd;
        this.abO = abO;
        this.acT = acT;
        this.acU = acU;
        this.Fa = fa;
    }
    
    public GameRequestEntity(final GameRequest gameRequest) {
        this.BR = 2;
        this.aan = new GameEntity(gameRequest.getGame());
        this.acR = new PlayerEntity(gameRequest.getSender());
        this.Xr = gameRequest.getRequestId();
        this.FD = gameRequest.getType();
        this.abO = gameRequest.getCreationTimestamp();
        this.acT = gameRequest.getExpirationTimestamp();
        this.Fa = gameRequest.getStatus();
        final byte[] data = gameRequest.getData();
        if (data == null) {
            this.acw = null;
        }
        else {
            System.arraycopy(data, 0, this.acw = new byte[data.length], 0, data.length);
        }
        final List<Player> recipients = gameRequest.getRecipients();
        final int size = recipients.size();
        this.acS = new ArrayList<PlayerEntity>(size);
        this.acU = new Bundle();
        for (int i = 0; i < size; ++i) {
            final Player player = recipients.get(i).freeze();
            final String playerId = player.getPlayerId();
            this.acS.add((PlayerEntity)player);
            this.acU.putInt(playerId, gameRequest.getRecipientStatus(playerId));
        }
    }
    
    static int a(final GameRequest gameRequest) {
        return m.hashCode(gameRequest.getGame(), gameRequest.getRecipients(), gameRequest.getRequestId(), gameRequest.getSender(), b(gameRequest), gameRequest.getType(), gameRequest.getCreationTimestamp(), gameRequest.getExpirationTimestamp());
    }
    
    static boolean a(final GameRequest gameRequest, final Object o) {
        final boolean b = true;
        boolean b2;
        if (!(o instanceof GameRequest)) {
            b2 = false;
        }
        else {
            b2 = b;
            if (gameRequest != o) {
                final GameRequest gameRequest2 = (GameRequest)o;
                if (m.equal(gameRequest2.getGame(), gameRequest.getGame()) && m.equal(gameRequest2.getRecipients(), gameRequest.getRecipients()) && m.equal(gameRequest2.getRequestId(), gameRequest.getRequestId()) && m.equal(gameRequest2.getSender(), gameRequest.getSender()) && Arrays.equals(b(gameRequest2), b(gameRequest)) && m.equal(gameRequest2.getType(), gameRequest.getType()) && m.equal(gameRequest2.getCreationTimestamp(), gameRequest.getCreationTimestamp())) {
                    b2 = b;
                    if (m.equal(gameRequest2.getExpirationTimestamp(), gameRequest.getExpirationTimestamp())) {
                        return b2;
                    }
                }
                return false;
            }
        }
        return b2;
    }
    
    private static int[] b(final GameRequest gameRequest) {
        final List<Player> recipients = gameRequest.getRecipients();
        final int size = recipients.size();
        final int[] array = new int[size];
        for (int i = 0; i < size; ++i) {
            array[i] = gameRequest.getRecipientStatus(recipients.get(i).getPlayerId());
        }
        return array;
    }
    
    static String c(final GameRequest gameRequest) {
        return m.h(gameRequest).a("Game", gameRequest.getGame()).a("Sender", gameRequest.getSender()).a("Recipients", gameRequest.getRecipients()).a("Data", gameRequest.getData()).a("RequestId", gameRequest.getRequestId()).a("Type", gameRequest.getType()).a("CreationTimestamp", gameRequest.getCreationTimestamp()).a("ExpirationTimestamp", gameRequest.getExpirationTimestamp()).toString();
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return a(this, o);
    }
    
    public GameRequest freeze() {
        return this;
    }
    
    @Override
    public long getCreationTimestamp() {
        return this.abO;
    }
    
    @Override
    public byte[] getData() {
        return this.acw;
    }
    
    @Override
    public long getExpirationTimestamp() {
        return this.acT;
    }
    
    @Override
    public Game getGame() {
        return this.aan;
    }
    
    @Override
    public int getRecipientStatus(final String s) {
        return this.acU.getInt(s, 0);
    }
    
    @Override
    public List<Player> getRecipients() {
        return new ArrayList<Player>(this.acS);
    }
    
    @Override
    public String getRequestId() {
        return this.Xr;
    }
    
    @Override
    public Player getSender() {
        return this.acR;
    }
    
    @Override
    public int getStatus() {
        return this.Fa;
    }
    
    @Override
    public int getType() {
        return this.FD;
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public int hashCode() {
        return a(this);
    }
    
    @Override
    public boolean isConsumed(final String s) {
        return this.getRecipientStatus(s) == 1;
    }
    
    public boolean isDataValid() {
        return true;
    }
    
    public Bundle lJ() {
        return this.acU;
    }
    
    @Override
    public String toString() {
        return c(this);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        GameRequestEntityCreator.a(this, parcel, n);
    }
}
