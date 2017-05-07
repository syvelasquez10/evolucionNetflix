// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.game;

import android.os.Parcel;
import java.util.Collection;
import com.google.android.gms.common.internal.c;
import com.google.android.gms.common.internal.m;
import com.google.android.gms.games.snapshot.SnapshotMetadata;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.snapshot.SnapshotMetadataEntity;
import java.util.ArrayList;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;

public final class ExtendedGameEntity extends GamesDowngradeableSafeParcel implements ExtendedGame
{
    public static final ExtendedGameEntityCreator CREATOR;
    private final int BR;
    private final GameEntity aan;
    private final int aao;
    private final boolean aap;
    private final int aaq;
    private final long aar;
    private final long aas;
    private final String aat;
    private final long aau;
    private final String aav;
    private final ArrayList<GameBadgeEntity> aaw;
    private final SnapshotMetadataEntity aax;
    
    static {
        CREATOR = new ExtendedGameEntityCreatorCompat();
    }
    
    ExtendedGameEntity(final int br, final GameEntity aan, final int aao, final boolean aap, final int aaq, final long aar, final long aas, final String aat, final long aau, final String aav, final ArrayList<GameBadgeEntity> aaw, final SnapshotMetadataEntity aax) {
        this.BR = br;
        this.aan = aan;
        this.aao = aao;
        this.aap = aap;
        this.aaq = aaq;
        this.aar = aar;
        this.aas = aas;
        this.aat = aat;
        this.aau = aau;
        this.aav = aav;
        this.aaw = aaw;
        this.aax = aax;
    }
    
    public ExtendedGameEntity(final ExtendedGame extendedGame) {
        final SnapshotMetadataEntity snapshotMetadataEntity = null;
        this.BR = 2;
        final Game game = extendedGame.getGame();
        GameEntity aan;
        if (game == null) {
            aan = null;
        }
        else {
            aan = new GameEntity(game);
        }
        this.aan = aan;
        this.aao = extendedGame.kP();
        this.aap = extendedGame.kQ();
        this.aaq = extendedGame.kR();
        this.aar = extendedGame.kS();
        this.aas = extendedGame.kT();
        this.aat = extendedGame.kU();
        this.aau = extendedGame.kV();
        this.aav = extendedGame.kW();
        final SnapshotMetadata kx = extendedGame.kX();
        SnapshotMetadataEntity aax;
        if (kx == null) {
            aax = snapshotMetadataEntity;
        }
        else {
            aax = new SnapshotMetadataEntity(kx);
        }
        this.aax = aax;
        final ArrayList<GameBadge> ko = extendedGame.kO();
        final int size = ko.size();
        this.aaw = new ArrayList<GameBadgeEntity>(size);
        for (int i = 0; i < size; ++i) {
            this.aaw.add((GameBadgeEntity)ko.get(i).freeze());
        }
    }
    
    static int a(final ExtendedGame extendedGame) {
        return m.hashCode(extendedGame.getGame(), extendedGame.kP(), extendedGame.kQ(), extendedGame.kR(), extendedGame.kS(), extendedGame.kT(), extendedGame.kU(), extendedGame.kV(), extendedGame.kW());
    }
    
    static boolean a(final ExtendedGame extendedGame, final Object o) {
        final boolean b = true;
        boolean b2;
        if (!(o instanceof ExtendedGame)) {
            b2 = false;
        }
        else {
            b2 = b;
            if (extendedGame != o) {
                final ExtendedGame extendedGame2 = (ExtendedGame)o;
                if (m.equal(extendedGame2.getGame(), extendedGame.getGame()) && m.equal(extendedGame2.kP(), extendedGame.kP()) && m.equal(extendedGame2.kQ(), extendedGame.kQ()) && m.equal(extendedGame2.kR(), extendedGame.kR()) && m.equal(extendedGame2.kS(), extendedGame.kS()) && m.equal(extendedGame2.kT(), extendedGame.kT()) && m.equal(extendedGame2.kU(), extendedGame.kU()) && m.equal(extendedGame2.kV(), extendedGame.kV())) {
                    b2 = b;
                    if (m.equal(extendedGame2.kW(), extendedGame.kW())) {
                        return b2;
                    }
                }
                return false;
            }
        }
        return b2;
    }
    
    static String b(final ExtendedGame extendedGame) {
        return m.h(extendedGame).a("Game", extendedGame.getGame()).a("Availability", extendedGame.kP()).a("Owned", extendedGame.kQ()).a("AchievementUnlockedCount", extendedGame.kR()).a("LastPlayedServerTimestamp", extendedGame.kS()).a("PriceMicros", extendedGame.kT()).a("FormattedPrice", extendedGame.kU()).a("FullPriceMicros", extendedGame.kV()).a("FormattedFullPrice", extendedGame.kW()).a("Snapshot", extendedGame.kX()).toString();
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return a(this, o);
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public int hashCode() {
        return a(this);
    }
    
    public boolean isDataValid() {
        return true;
    }
    
    @Override
    public ArrayList<GameBadge> kO() {
        return new ArrayList<GameBadge>(this.aaw);
    }
    
    @Override
    public int kP() {
        return this.aao;
    }
    
    @Override
    public boolean kQ() {
        return this.aap;
    }
    
    @Override
    public int kR() {
        return this.aaq;
    }
    
    @Override
    public long kS() {
        return this.aar;
    }
    
    @Override
    public long kT() {
        return this.aas;
    }
    
    @Override
    public String kU() {
        return this.aat;
    }
    
    @Override
    public long kV() {
        return this.aau;
    }
    
    @Override
    public String kW() {
        return this.aav;
    }
    
    @Override
    public SnapshotMetadata kX() {
        return this.aax;
    }
    
    public GameEntity kY() {
        return this.aan;
    }
    
    public ExtendedGame kZ() {
        return this;
    }
    
    @Override
    public String toString() {
        return b(this);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final int n2 = 0;
        if (!this.gQ()) {
            ExtendedGameEntityCreator.a(this, parcel, n);
        }
        else {
            this.aan.writeToParcel(parcel, n);
            parcel.writeInt(this.aao);
            int n3;
            if (this.aap) {
                n3 = 1;
            }
            else {
                n3 = 0;
            }
            parcel.writeInt(n3);
            parcel.writeInt(this.aaq);
            parcel.writeLong(this.aar);
            parcel.writeLong(this.aas);
            parcel.writeString(this.aat);
            parcel.writeLong(this.aau);
            parcel.writeString(this.aav);
            final int size = this.aaw.size();
            parcel.writeInt(size);
            for (int i = n2; i < size; ++i) {
                this.aaw.get(i).writeToParcel(parcel, n);
            }
        }
    }
    
    static final class ExtendedGameEntityCreatorCompat extends ExtendedGameEntityCreator
    {
        @Override
        public ExtendedGameEntity cg(final Parcel parcel) {
            if (GamesDowngradeableSafeParcel.c(c.gP()) || c.aV(ExtendedGameEntity.class.getCanonicalName())) {
                return super.cg(parcel);
            }
            final GameEntity gameEntity = (GameEntity)GameEntity.CREATOR.createFromParcel(parcel);
            final int int1 = parcel.readInt();
            final boolean b = parcel.readInt() == 1;
            final int int2 = parcel.readInt();
            final long long1 = parcel.readLong();
            final long long2 = parcel.readLong();
            final String string = parcel.readString();
            final long long3 = parcel.readLong();
            final String string2 = parcel.readString();
            final int int3 = parcel.readInt();
            final ArrayList list = new ArrayList<GameBadgeEntity>(int3);
            for (int i = 0; i < int3; ++i) {
                list.add(GameBadgeEntity.CREATOR.ch(parcel));
            }
            return new ExtendedGameEntity(2, gameEntity, int1, b, int2, long1, long2, string, long3, string2, (ArrayList<GameBadgeEntity>)list, null);
        }
    }
}
