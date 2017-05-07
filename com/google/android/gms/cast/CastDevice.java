// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Collections;
import java.util.Iterator;
import com.google.android.gms.internal.eo;
import android.os.Bundle;
import java.net.UnknownHostException;
import java.net.InetAddress;
import java.util.ArrayList;
import com.google.android.gms.common.images.WebImage;
import java.util.List;
import java.net.Inet4Address;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class CastDevice implements SafeParcelable
{
    public static final Parcelable$Creator<CastDevice> CREATOR;
    private final int xH;
    private String ya;
    String yb;
    private Inet4Address yc;
    private String yd;
    private String ye;
    private String yf;
    private int yg;
    private List<WebImage> yh;
    
    static {
        CREATOR = (Parcelable$Creator)new b();
    }
    
    private CastDevice() {
        this(1, null, null, null, null, null, -1, new ArrayList<WebImage>());
    }
    
    CastDevice(final int xh, final String ya, final String yb, final String yd, final String ye, final String yf, final int yg, final List<WebImage> yh) {
        this.xH = xh;
        this.ya = ya;
        this.yb = yb;
        while (true) {
            if (this.yb == null) {
                break Label_0049;
            }
            try {
                final InetAddress byName = InetAddress.getByName(this.yb);
                if (byName instanceof Inet4Address) {
                    this.yc = (Inet4Address)byName;
                }
                this.yd = yd;
                this.ye = ye;
                this.yf = yf;
                this.yg = yg;
                this.yh = yh;
            }
            catch (UnknownHostException ex) {
                this.yc = null;
                continue;
            }
            break;
        }
    }
    
    public static CastDevice getFromBundle(final Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        bundle.setClassLoader(CastDevice.class.getClassLoader());
        return (CastDevice)bundle.getParcelable("com.google.android.gms.cast.EXTRA_CAST_DEVICE");
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof CastDevice)) {
                return false;
            }
            final CastDevice castDevice = (CastDevice)o;
            if (this.getDeviceId() == null) {
                if (castDevice.getDeviceId() != null) {
                    return false;
                }
            }
            else if (!eo.a(this.ya, castDevice.ya) || !eo.a(this.yc, castDevice.yc) || !eo.a(this.ye, castDevice.ye) || !eo.a(this.yd, castDevice.yd) || !eo.a(this.yf, castDevice.yf) || this.yg != castDevice.yg || !eo.a(this.yh, castDevice.yh)) {
                return false;
            }
        }
        return true;
    }
    
    public String getDeviceId() {
        return this.ya;
    }
    
    public String getDeviceVersion() {
        return this.yf;
    }
    
    public String getFriendlyName() {
        return this.yd;
    }
    
    public WebImage getIcon(final int n, final int n2) {
        WebImage webImage = null;
        if (this.yh.isEmpty()) {
            return null;
        }
        if (n <= 0 || n2 <= 0) {
            return this.yh.get(0);
        }
        final Iterator<WebImage> iterator = this.yh.iterator();
        WebImage webImage2 = null;
    Label_0127_Outer:
        while (iterator.hasNext()) {
            final WebImage webImage3 = iterator.next();
            final int width = webImage3.getWidth();
            final int height = webImage3.getHeight();
            if (width >= n && height >= n2) {
                if (webImage2 != null && (webImage2.getWidth() <= width || webImage2.getHeight() <= height)) {
                    continue Label_0127_Outer;
                }
                webImage2 = webImage3;
            }
            else {
                if (width >= n || height >= n2 || (webImage != null && (webImage.getWidth() >= width || webImage.getHeight() >= height))) {
                    continue Label_0127_Outer;
                }
                webImage = webImage3;
            }
            while (true) {
                continue Label_0127_Outer;
                continue;
            }
        }
        if (webImage2 == null) {
            if (webImage != null) {
                webImage2 = webImage;
            }
            else {
                webImage2 = this.yh.get(0);
            }
        }
        return webImage2;
    }
    
    public List<WebImage> getIcons() {
        return Collections.unmodifiableList((List<? extends WebImage>)this.yh);
    }
    
    public Inet4Address getIpAddress() {
        return this.yc;
    }
    
    public String getModelName() {
        return this.ye;
    }
    
    public int getServicePort() {
        return this.yg;
    }
    
    int getVersionCode() {
        return this.xH;
    }
    
    public boolean hasIcons() {
        return !this.yh.isEmpty();
    }
    
    @Override
    public int hashCode() {
        if (this.ya == null) {
            return 0;
        }
        return this.ya.hashCode();
    }
    
    public boolean isSameDevice(final CastDevice castDevice) {
        if (castDevice != null) {
            if (this.getDeviceId() != null) {
                return eo.a(this.getDeviceId(), castDevice.getDeviceId());
            }
            if (castDevice.getDeviceId() == null) {
                return true;
            }
        }
        return false;
    }
    
    public void putInBundle(final Bundle bundle) {
        if (bundle == null) {
            return;
        }
        bundle.putParcelable("com.google.android.gms.cast.EXTRA_CAST_DEVICE", (Parcelable)this);
    }
    
    @Override
    public String toString() {
        return String.format("\"%s\" (%s)", this.yd, this.ya);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        b.a(this, parcel, n);
    }
}
