// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.game;

import android.os.Parcel;
import com.google.android.gms.common.internal.c;
import com.google.android.gms.common.internal.m;
import android.net.Uri;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;

public final class GameBadgeEntity extends GamesDowngradeableSafeParcel implements GameBadge
{
    public static final GameBadgeEntityCreator CREATOR;
    private final int BR;
    private int FD;
    private String No;
    private String Tg;
    private Uri UW;
    
    static {
        CREATOR = new GameBadgeEntity$GameBadgeEntityCreatorCompat();
    }
    
    GameBadgeEntity(final int br, final int fd, final String no, final String tg, final Uri uw) {
        this.BR = br;
        this.FD = fd;
        this.No = no;
        this.Tg = tg;
        this.UW = uw;
    }
    
    public GameBadgeEntity(final GameBadge gameBadge) {
        this.BR = 1;
        this.FD = gameBadge.getType();
        this.No = gameBadge.getTitle();
        this.Tg = gameBadge.getDescription();
        this.UW = gameBadge.getIconImageUri();
    }
    
    static int a(final GameBadge gameBadge) {
        return m.hashCode(gameBadge.getType(), gameBadge.getTitle(), gameBadge.getDescription(), gameBadge.getIconImageUri());
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
                if (m.equal(gameBadge2.getType(), gameBadge.getTitle())) {
                    b2 = b;
                    if (m.equal(gameBadge2.getDescription(), gameBadge.getIconImageUri())) {
                        return b2;
                    }
                }
                return false;
            }
        }
        return b2;
    }
    
    static String b(final GameBadge gameBadge) {
        return m.h(gameBadge).a("Type", gameBadge.getType()).a("Title", gameBadge.getTitle()).a("Description", gameBadge.getDescription()).a("IconImageUri", gameBadge.getIconImageUri()).toString();
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
        return this.Tg;
    }
    
    @Override
    public Uri getIconImageUri() {
        return this.UW;
    }
    
    @Override
    public String getTitle() {
        return this.No;
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
    
    public boolean isDataValid() {
        return true;
    }
    
    public GameBadge la() {
        return this;
    }
    
    @Override
    public String toString() {
        return b(this);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        if (!this.gQ()) {
            GameBadgeEntityCreator.a(this, parcel, n);
            return;
        }
        parcel.writeInt(this.FD);
        parcel.writeString(this.No);
        parcel.writeString(this.Tg);
        String string;
        if (this.UW == null) {
            string = null;
        }
        else {
            string = this.UW.toString();
        }
        parcel.writeString(string);
    }
}
