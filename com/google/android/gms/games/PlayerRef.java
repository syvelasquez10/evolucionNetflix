// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games;

import android.os.Parcel;
import com.google.android.gms.games.internal.player.MostRecentGameInfo;
import android.net.Uri;
import android.database.CharArrayBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.internal.player.MostRecentGameInfoRef;
import com.google.android.gms.games.internal.player.PlayerColumnNames;
import com.google.android.gms.common.data.d;

public final class PlayerRef extends d implements Player
{
    private final PlayerLevelInfo VE;
    private final PlayerColumnNames VN;
    private final MostRecentGameInfoRef VO;
    
    public PlayerRef(final DataHolder dataHolder, final int n) {
        this(dataHolder, n, null);
    }
    
    public PlayerRef(final DataHolder dataHolder, int integer, final String s) {
        super(dataHolder, integer);
        this.VN = new PlayerColumnNames(s);
        this.VO = new MostRecentGameInfoRef(dataHolder, integer, this.VN);
        if (this.jT()) {
            integer = this.getInteger(this.VN.aaR);
            final int integer2 = this.getInteger(this.VN.aaU);
            final PlayerLevel playerLevel = new PlayerLevel(integer, this.getLong(this.VN.aaS), this.getLong(this.VN.aaT));
            PlayerLevel playerLevel2;
            if (integer != integer2) {
                playerLevel2 = new PlayerLevel(integer2, this.getLong(this.VN.aaT), this.getLong(this.VN.aaV));
            }
            else {
                playerLevel2 = playerLevel;
            }
            this.VE = new PlayerLevelInfo(this.getLong(this.VN.aaQ), this.getLong(this.VN.aaW), playerLevel, playerLevel2);
            return;
        }
        this.VE = null;
    }
    
    private boolean jT() {
        return !this.aS(this.VN.aaQ) && this.getLong(this.VN.aaQ) != -1L;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return PlayerEntity.a(this, o);
    }
    
    public Player freeze() {
        return new PlayerEntity(this);
    }
    
    @Override
    public String getDisplayName() {
        return this.getString(this.VN.aaI);
    }
    
    @Override
    public void getDisplayName(final CharArrayBuffer charArrayBuffer) {
        this.a(this.VN.aaI, charArrayBuffer);
    }
    
    @Override
    public Uri getHiResImageUri() {
        return this.aR(this.VN.aaL);
    }
    
    @Override
    public String getHiResImageUrl() {
        return this.getString(this.VN.aaM);
    }
    
    @Override
    public Uri getIconImageUri() {
        return this.aR(this.VN.aaJ);
    }
    
    @Override
    public String getIconImageUrl() {
        return this.getString(this.VN.aaK);
    }
    
    @Override
    public long getLastPlayedWithTimestamp() {
        if (!this.aQ(this.VN.aaP) || this.aS(this.VN.aaP)) {
            return -1L;
        }
        return this.getLong(this.VN.aaP);
    }
    
    @Override
    public PlayerLevelInfo getLevelInfo() {
        return this.VE;
    }
    
    @Override
    public String getPlayerId() {
        return this.getString(this.VN.aaH);
    }
    
    @Override
    public long getRetrievedTimestamp() {
        return this.getLong(this.VN.aaN);
    }
    
    @Override
    public String getTitle() {
        return this.getString(this.VN.aaX);
    }
    
    @Override
    public void getTitle(final CharArrayBuffer charArrayBuffer) {
        this.a(this.VN.aaX, charArrayBuffer);
    }
    
    @Override
    public boolean hasHiResImage() {
        return this.getHiResImageUri() != null;
    }
    
    @Override
    public boolean hasIconImage() {
        return this.getIconImageUri() != null;
    }
    
    @Override
    public int hashCode() {
        return PlayerEntity.b(this);
    }
    
    @Override
    public boolean isProfileVisible() {
        return this.getBoolean(this.VN.aaZ);
    }
    
    @Override
    public int jR() {
        return this.getInteger(this.VN.aaO);
    }
    
    @Override
    public MostRecentGameInfo jS() {
        if (this.aS(this.VN.aba)) {
            return null;
        }
        return this.VO;
    }
    
    @Override
    public String toString() {
        return PlayerEntity.c(this);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ((PlayerEntity)this.freeze()).writeToParcel(parcel, n);
    }
}
