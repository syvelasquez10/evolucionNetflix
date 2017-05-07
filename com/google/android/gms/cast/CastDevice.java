// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Collections;
import java.util.Iterator;
import com.google.android.gms.internal.ik;
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
    private final int BR;
    private String ER;
    String ES;
    private Inet4Address ET;
    private String EU;
    private String EV;
    private String EW;
    private int EX;
    private List<WebImage> EY;
    private int EZ;
    private int Fa;
    
    static {
        CREATOR = (Parcelable$Creator)new b();
    }
    
    private CastDevice() {
        this(3, null, null, null, null, null, -1, new ArrayList<WebImage>(), 0, -1);
    }
    
    CastDevice(final int br, final String er, final String es, final String eu, final String ev, final String ew, final int ex, final List<WebImage> ey, final int ez, final int fa) {
        this.BR = br;
        this.ER = er;
        this.ES = es;
        while (true) {
            if (this.ES == null) {
                break Label_0049;
            }
            try {
                final InetAddress byName = InetAddress.getByName(this.ES);
                if (byName instanceof Inet4Address) {
                    this.ET = (Inet4Address)byName;
                }
                this.EU = eu;
                this.EV = ev;
                this.EW = ew;
                this.EX = ex;
                this.EY = ey;
                this.EZ = ez;
                this.Fa = fa;
            }
            catch (UnknownHostException ex2) {
                this.ET = null;
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
            else if (!ik.a(this.ER, castDevice.ER) || !ik.a(this.ET, castDevice.ET) || !ik.a(this.EV, castDevice.EV) || !ik.a(this.EU, castDevice.EU) || !ik.a(this.EW, castDevice.EW) || this.EX != castDevice.EX || !ik.a(this.EY, castDevice.EY) || this.EZ != castDevice.EZ || this.Fa != castDevice.Fa) {
                return false;
            }
        }
        return true;
    }
    
    public int getCapabilities() {
        return this.EZ;
    }
    
    public String getDeviceId() {
        return this.ER;
    }
    
    public String getDeviceVersion() {
        return this.EW;
    }
    
    public String getFriendlyName() {
        return this.EU;
    }
    
    public WebImage getIcon(final int n, final int n2) {
        WebImage webImage = null;
        if (this.EY.isEmpty()) {
            return null;
        }
        if (n <= 0 || n2 <= 0) {
            return this.EY.get(0);
        }
        final Iterator<WebImage> iterator = this.EY.iterator();
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
                webImage2 = this.EY.get(0);
            }
        }
        return webImage2;
    }
    
    public List<WebImage> getIcons() {
        return Collections.unmodifiableList((List<? extends WebImage>)this.EY);
    }
    
    public Inet4Address getIpAddress() {
        return this.ET;
    }
    
    public String getModelName() {
        return this.EV;
    }
    
    public int getServicePort() {
        return this.EX;
    }
    
    public int getStatus() {
        return this.Fa;
    }
    
    int getVersionCode() {
        return this.BR;
    }
    
    public boolean hasIcons() {
        return !this.EY.isEmpty();
    }
    
    @Override
    public int hashCode() {
        if (this.ER == null) {
            return 0;
        }
        return this.ER.hashCode();
    }
    
    public boolean isSameDevice(final CastDevice castDevice) {
        if (castDevice != null) {
            if (this.getDeviceId() != null) {
                return ik.a(this.getDeviceId(), castDevice.getDeviceId());
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
        return String.format("\"%s\" (%s)", this.EU, this.ER);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        b.a(this, parcel, n);
    }
}
