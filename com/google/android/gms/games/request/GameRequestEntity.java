// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.request;

import android.os.Parcel;
import java.util.Collection;
import com.google.android.gms.games.Game;
import java.util.Arrays;
import com.google.android.gms.internal.fo;
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
    private final String Jo;
    private final int LF;
    private final GameEntity Lt;
    private final int MB;
    private final long Mu;
    private final byte[] Nf;
    private final PlayerEntity Nm;
    private final ArrayList<PlayerEntity> Nn;
    private final long No;
    private final Bundle Np;
    private final int xH;
    
    static {
        CREATOR = new GameRequestEntityCreator();
    }
    
    GameRequestEntity(final int xh, final GameEntity lt, final PlayerEntity nm, final byte[] nf, final String jo, final ArrayList<PlayerEntity> nn, final int lf, final long mu, final long no, final Bundle np, final int mb) {
        this.xH = xh;
        this.Lt = lt;
        this.Nm = nm;
        this.Nf = nf;
        this.Jo = jo;
        this.Nn = nn;
        this.LF = lf;
        this.Mu = mu;
        this.No = no;
        this.Np = np;
        this.MB = mb;
    }
    
    public GameRequestEntity(final GameRequest gameRequest) {
        this.xH = 2;
        this.Lt = new GameEntity(gameRequest.getGame());
        this.Nm = new PlayerEntity(gameRequest.getSender());
        this.Jo = gameRequest.getRequestId();
        this.LF = gameRequest.getType();
        this.Mu = gameRequest.getCreationTimestamp();
        this.No = gameRequest.getExpirationTimestamp();
        this.MB = gameRequest.getStatus();
        final byte[] data = gameRequest.getData();
        if (data == null) {
            this.Nf = null;
        }
        else {
            System.arraycopy(data, 0, this.Nf = new byte[data.length], 0, data.length);
        }
        final List<Player> recipients = gameRequest.getRecipients();
        final int size = recipients.size();
        this.Nn = new ArrayList<PlayerEntity>(size);
        this.Np = new Bundle();
        for (int i = 0; i < size; ++i) {
            final Player player = recipients.get(i).freeze();
            final String playerId = player.getPlayerId();
            this.Nn.add((PlayerEntity)player);
            this.Np.putInt(playerId, gameRequest.getRecipientStatus(playerId));
        }
    }
    
    static int a(final GameRequest gameRequest) {
        return fo.hashCode(gameRequest.getGame(), gameRequest.getRecipients(), gameRequest.getRequestId(), gameRequest.getSender(), b(gameRequest), gameRequest.getType(), gameRequest.getCreationTimestamp(), gameRequest.getExpirationTimestamp());
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
                if (fo.equal(gameRequest2.getGame(), gameRequest.getGame()) && fo.equal(gameRequest2.getRecipients(), gameRequest.getRecipients()) && fo.equal(gameRequest2.getRequestId(), gameRequest.getRequestId()) && fo.equal(gameRequest2.getSender(), gameRequest.getSender()) && Arrays.equals(b(gameRequest2), b(gameRequest)) && fo.equal(gameRequest2.getType(), gameRequest.getType()) && fo.equal(gameRequest2.getCreationTimestamp(), gameRequest.getCreationTimestamp())) {
                    b2 = b;
                    if (fo.equal(gameRequest2.getExpirationTimestamp(), gameRequest.getExpirationTimestamp())) {
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
        return fo.e(gameRequest).a("Game", gameRequest.getGame()).a("Sender", gameRequest.getSender()).a("Recipients", gameRequest.getRecipients()).a("Data", gameRequest.getData()).a("RequestId", gameRequest.getRequestId()).a("Type", gameRequest.getType()).a("CreationTimestamp", gameRequest.getCreationTimestamp()).a("ExpirationTimestamp", gameRequest.getExpirationTimestamp()).toString();
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
        return this.Mu;
    }
    
    @Override
    public byte[] getData() {
        return this.Nf;
    }
    
    @Override
    public long getExpirationTimestamp() {
        return this.No;
    }
    
    @Override
    public Game getGame() {
        return this.Lt;
    }
    
    @Override
    public int getRecipientStatus(final String s) {
        return this.Np.getInt(s, 0);
    }
    
    @Override
    public List<Player> getRecipients() {
        return new ArrayList<Player>(this.Nn);
    }
    
    @Override
    public String getRequestId() {
        return this.Jo;
    }
    
    @Override
    public Player getSender() {
        return this.Nm;
    }
    
    @Override
    public int getStatus() {
        return this.MB;
    }
    
    @Override
    public int getType() {
        return this.LF;
    }
    
    public int getVersionCode() {
        return this.xH;
    }
    
    public Bundle hK() {
        return this.Np;
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
    
    @Override
    public String toString() {
        return c(this);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        GameRequestEntityCreator.a(this, parcel, n);
    }
}
