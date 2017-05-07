// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.game;

import android.os.Parcel;
import java.util.Collection;
import com.google.android.gms.internal.fe;
import com.google.android.gms.internal.fo;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import java.util.ArrayList;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;

public final class ExtendedGameEntity extends GamesDowngradeableSafeParcel implements ExtendedGame
{
    public static final ExtendedGameEntityCreator CREATOR;
    private final long LA;
    private final String LB;
    private final ArrayList<GameBadgeEntity> LC;
    private final GameEntity Lt;
    private final int Lu;
    private final boolean Lv;
    private final int Lw;
    private final long Lx;
    private final long Ly;
    private final String Lz;
    private final int xH;
    
    static {
        CREATOR = new ExtendedGameEntityCreatorCompat();
    }
    
    ExtendedGameEntity(final int xh, final GameEntity lt, final int lu, final boolean lv, final int lw, final long lx, final long ly, final String lz, final long la, final String lb, final ArrayList<GameBadgeEntity> lc) {
        this.xH = xh;
        this.Lt = lt;
        this.Lu = lu;
        this.Lv = lv;
        this.Lw = lw;
        this.Lx = lx;
        this.Ly = ly;
        this.Lz = lz;
        this.LA = la;
        this.LB = lb;
        this.LC = lc;
    }
    
    public ExtendedGameEntity(final ExtendedGame extendedGame) {
        this.xH = 1;
        final Game game = extendedGame.getGame();
        GameEntity lt;
        if (game == null) {
            lt = null;
        }
        else {
            lt = new GameEntity(game);
        }
        this.Lt = lt;
        this.Lu = extendedGame.gX();
        this.Lv = extendedGame.gY();
        this.Lw = extendedGame.gZ();
        this.Lx = extendedGame.ha();
        this.Ly = extendedGame.hb();
        this.Lz = extendedGame.hc();
        this.LA = extendedGame.hd();
        this.LB = extendedGame.he();
        final ArrayList<GameBadge> gw = extendedGame.gW();
        final int size = gw.size();
        this.LC = new ArrayList<GameBadgeEntity>(size);
        for (int i = 0; i < size; ++i) {
            this.LC.add((GameBadgeEntity)gw.get(i).freeze());
        }
    }
    
    static int a(final ExtendedGame extendedGame) {
        return fo.hashCode(extendedGame.getGame(), extendedGame.gX(), extendedGame.gY(), extendedGame.gZ(), extendedGame.ha(), extendedGame.hb(), extendedGame.hc(), extendedGame.hd(), extendedGame.he());
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
                if (fo.equal(extendedGame2.getGame(), extendedGame.getGame()) && fo.equal(extendedGame2.gX(), extendedGame.gX()) && fo.equal(extendedGame2.gY(), extendedGame.gY()) && fo.equal(extendedGame2.gZ(), extendedGame.gZ()) && fo.equal(extendedGame2.ha(), extendedGame.ha()) && fo.equal(extendedGame2.hb(), extendedGame.hb()) && fo.equal(extendedGame2.hc(), extendedGame.hc()) && fo.equal(extendedGame2.hd(), extendedGame.hd())) {
                    b2 = b;
                    if (fo.equal(extendedGame2.he(), extendedGame.he())) {
                        return b2;
                    }
                }
                return false;
            }
        }
        return b2;
    }
    
    static String b(final ExtendedGame extendedGame) {
        return fo.e(extendedGame).a("Game", extendedGame.getGame()).a("Availability", extendedGame.gX()).a("Owned", extendedGame.gY()).a("AchievementUnlockedCount", extendedGame.gZ()).a("LastPlayedServerTimestamp", extendedGame.ha()).a("PriceMicros", extendedGame.hb()).a("FormattedPrice", extendedGame.hc()).a("FullPriceMicros", extendedGame.hd()).a("FormattedFullPrice", extendedGame.he()).toString();
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return a(this, o);
    }
    
    @Override
    public ArrayList<GameBadge> gW() {
        return new ArrayList<GameBadge>(this.LC);
    }
    
    @Override
    public int gX() {
        return this.Lu;
    }
    
    @Override
    public boolean gY() {
        return this.Lv;
    }
    
    @Override
    public int gZ() {
        return this.Lw;
    }
    
    public int getVersionCode() {
        return this.xH;
    }
    
    @Override
    public long ha() {
        return this.Lx;
    }
    
    @Override
    public int hashCode() {
        return a(this);
    }
    
    @Override
    public long hb() {
        return this.Ly;
    }
    
    @Override
    public String hc() {
        return this.Lz;
    }
    
    @Override
    public long hd() {
        return this.LA;
    }
    
    @Override
    public String he() {
        return this.LB;
    }
    
    public GameEntity hf() {
        return this.Lt;
    }
    
    public ExtendedGame hg() {
        return this;
    }
    
    public boolean isDataValid() {
        return true;
    }
    
    @Override
    public String toString() {
        return b(this);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final int n2 = 0;
        if (!this.eK()) {
            ExtendedGameEntityCreator.a(this, parcel, n);
        }
        else {
            this.Lt.writeToParcel(parcel, n);
            parcel.writeInt(this.Lu);
            int n3;
            if (this.Lv) {
                n3 = 1;
            }
            else {
                n3 = 0;
            }
            parcel.writeInt(n3);
            parcel.writeInt(this.Lw);
            parcel.writeLong(this.Lx);
            parcel.writeLong(this.Ly);
            parcel.writeString(this.Lz);
            parcel.writeLong(this.LA);
            parcel.writeString(this.LB);
            final int size = this.LC.size();
            parcel.writeInt(size);
            for (int i = n2; i < size; ++i) {
                this.LC.get(i).writeToParcel(parcel, n);
            }
        }
    }
    
    static final class ExtendedGameEntityCreatorCompat extends ExtendedGameEntityCreator
    {
        @Override
        public ExtendedGameEntity aq(final Parcel parcel) {
            if (GamesDowngradeableSafeParcel.c(fe.eJ()) || fe.al(ExtendedGameEntity.class.getCanonicalName())) {
                return super.aq(parcel);
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
                list.add(GameBadgeEntity.CREATOR.ar(parcel));
            }
            return new ExtendedGameEntity(1, gameEntity, int1, b, int2, long1, long2, string, long3, string2, (ArrayList<GameBadgeEntity>)list);
        }
    }
}
