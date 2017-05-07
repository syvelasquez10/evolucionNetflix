// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.ads.search.SearchAdRequest;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class ai implements SafeParcelable
{
    public static final aj CREATOR;
    public final int backgroundColor;
    public final int eZ;
    public final int fa;
    public final int fb;
    public final int fc;
    public final int fd;
    public final int fe;
    public final int ff;
    public final String fg;
    public final int fh;
    public final String fi;
    public final int fj;
    public final int fk;
    public final String fl;
    public final int versionCode;
    
    static {
        CREATOR = new aj();
    }
    
    ai(final int versionCode, final int ez, final int backgroundColor, final int fa, final int fb, final int fc, final int fd, final int fe, final int ff, final String fg, final int fh, final String fi, final int fj, final int fk, final String fl) {
        this.versionCode = versionCode;
        this.eZ = ez;
        this.backgroundColor = backgroundColor;
        this.fa = fa;
        this.fb = fb;
        this.fc = fc;
        this.fd = fd;
        this.fe = fe;
        this.ff = ff;
        this.fg = fg;
        this.fh = fh;
        this.fi = fi;
        this.fj = fj;
        this.fk = fk;
        this.fl = fl;
    }
    
    public ai(final SearchAdRequest searchAdRequest) {
        this.versionCode = 1;
        this.eZ = searchAdRequest.getAnchorTextColor();
        this.backgroundColor = searchAdRequest.getBackgroundColor();
        this.fa = searchAdRequest.getBackgroundGradientBottom();
        this.fb = searchAdRequest.getBackgroundGradientTop();
        this.fc = searchAdRequest.getBorderColor();
        this.fd = searchAdRequest.getBorderThickness();
        this.fe = searchAdRequest.getBorderType();
        this.ff = searchAdRequest.getCallButtonColor();
        this.fg = searchAdRequest.getCustomChannels();
        this.fh = searchAdRequest.getDescriptionTextColor();
        this.fi = searchAdRequest.getFontFace();
        this.fj = searchAdRequest.getHeaderTextColor();
        this.fk = searchAdRequest.getHeaderTextSize();
        this.fl = searchAdRequest.getQuery();
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        aj.a(this, parcel, n);
    }
}
