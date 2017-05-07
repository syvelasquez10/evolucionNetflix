// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.ads.search.SearchAdRequest;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class av implements SafeParcelable
{
    public static final aw CREATOR;
    public final int backgroundColor;
    public final int mA;
    public final int mB;
    public final String mC;
    public final int mq;
    public final int mr;
    public final int ms;
    public final int mt;
    public final int mu;
    public final int mv;
    public final int mw;
    public final String mx;
    public final int my;
    public final String mz;
    public final int versionCode;
    
    static {
        CREATOR = new aw();
    }
    
    av(final int versionCode, final int mq, final int backgroundColor, final int mr, final int ms, final int mt, final int mu, final int mv, final int mw, final String mx, final int my, final String mz, final int ma, final int mb, final String mc) {
        this.versionCode = versionCode;
        this.mq = mq;
        this.backgroundColor = backgroundColor;
        this.mr = mr;
        this.ms = ms;
        this.mt = mt;
        this.mu = mu;
        this.mv = mv;
        this.mw = mw;
        this.mx = mx;
        this.my = my;
        this.mz = mz;
        this.mA = ma;
        this.mB = mb;
        this.mC = mc;
    }
    
    public av(final SearchAdRequest searchAdRequest) {
        this.versionCode = 1;
        this.mq = searchAdRequest.getAnchorTextColor();
        this.backgroundColor = searchAdRequest.getBackgroundColor();
        this.mr = searchAdRequest.getBackgroundGradientBottom();
        this.ms = searchAdRequest.getBackgroundGradientTop();
        this.mt = searchAdRequest.getBorderColor();
        this.mu = searchAdRequest.getBorderThickness();
        this.mv = searchAdRequest.getBorderType();
        this.mw = searchAdRequest.getCallButtonColor();
        this.mx = searchAdRequest.getCustomChannels();
        this.my = searchAdRequest.getDescriptionTextColor();
        this.mz = searchAdRequest.getFontFace();
        this.mA = searchAdRequest.getHeaderTextColor();
        this.mB = searchAdRequest.getHeaderTextSize();
        this.mC = searchAdRequest.getQuery();
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        aw.a(this, parcel, n);
    }
}
