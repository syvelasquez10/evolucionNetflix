// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.game;

import android.os.Parcel;
import com.google.android.gms.internal.fe;
import com.google.android.gms.internal.fo;
import android.net.Uri;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;

public final class GameBadgeEntity extends GamesDowngradeableSafeParcel implements GameBadge
{
    public static final GameBadgeEntityCreator CREATOR;
    private String EB;
    private String HD;
    private Uri HF;
    private int LF;
    private final int xH;
    
    static {
        CREATOR = new GameBadgeEntityCreatorCompat();
    }
    
    GameBadgeEntity(final int xh, final int lf, final String eb, final String hd, final Uri hf) {
        this.xH = xh;
        this.LF = lf;
        this.EB = eb;
        this.HD = hd;
        this.HF = hf;
    }
    
    public GameBadgeEntity(final GameBadge gameBadge) {
        this.xH = 1;
        this.LF = gameBadge.getType();
        this.EB = gameBadge.getTitle();
        this.HD = gameBadge.getDescription();
        this.HF = gameBadge.getIconImageUri();
    }
    
    static int a(final GameBadge gameBadge) {
        return fo.hashCode(gameBadge.getType(), gameBadge.getTitle(), gameBadge.getDescription(), gameBadge.getIconImageUri());
    }
    
    static boolean a(final GameBadge gameBadge, final Object o) {
        final boolean b = true;
        boolean b2;
        if (!(o instanceof GameBadge)) {
            b2 = false;
        }
        else {
            b2 = b;
            if (gameBadge != o) {
                final GameBadge gameBadge2 = (GameBadge)o;
                if (fo.equal(gameBadge2.getType(), gameBadge.getTitle())) {
                    b2 = b;
                    if (fo.equal(gameBadge2.getDescription(), gameBadge.getIconImageUri())) {
                        return b2;
                    }
                }
                return false;
            }
        }
        return b2;
    }
    
    static String b(final GameBadge gameBadge) {
        return fo.e(gameBadge).a("Type", gameBadge.getType()).a("Title", gameBadge.getTitle()).a("Description", gameBadge.getDescription()).a("IconImageUri", gameBadge.getIconImageUri()).toString();
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return a(this, o);
    }
    
    @Override
    public String getDescription() {
        return this.HD;
    }
    
    @Override
    public Uri getIconImageUri() {
        return this.HF;
    }
    
    @Override
    public String getTitle() {
        return this.EB;
    }
    
    @Override
    public int getType() {
        return this.LF;
    }
    
    public int getVersionCode() {
        return this.xH;
    }
    
    @Override
    public int hashCode() {
        return a(this);
    }
    
    public GameBadge hh() {
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
        if (!this.eK()) {
            GameBadgeEntityCreator.a(this, parcel, n);
            return;
        }
        parcel.writeInt(this.LF);
        parcel.writeString(this.EB);
        parcel.writeString(this.HD);
        String string;
        if (this.HF == null) {
            string = null;
        }
        else {
            string = this.HF.toString();
        }
        parcel.writeString(string);
    }
    
    static final class GameBadgeEntityCreatorCompat extends GameBadgeEntityCreator
    {
        @Override
        public GameBadgeEntity ar(final Parcel parcel) {
            if (GamesDowngradeableSafeParcel.c(fe.eJ()) || fe.al(GameBadgeEntity.class.getCanonicalName())) {
                return super.ar(parcel);
            }
            final int int1 = parcel.readInt();
            final String string = parcel.readString();
            final String string2 = parcel.readString();
            final String string3 = parcel.readString();
            Uri parse;
            if (string3 == null) {
                parse = null;
            }
            else {
                parse = Uri.parse(string3);
            }
            return new GameBadgeEntity(1, int1, string, string2, parse);
        }
    }
}
