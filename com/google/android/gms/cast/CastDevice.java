// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Collections;
import java.util.Iterator;
import com.google.android.gms.internal.dh;
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
    String kA;
    private Inet4Address kB;
    private String kC;
    private String kD;
    private String kE;
    private int kF;
    private List<WebImage> kG;
    private final int kg;
    private String kz;
    
    static {
        CREATOR = (Parcelable$Creator)new b();
    }
    
    private CastDevice() {
        this(1, null, null, null, null, null, -1, new ArrayList<WebImage>());
    }
    
    CastDevice(final int kg, final String kz, final String ka, final String kc, final String kd, final String ke, final int kf, final List<WebImage> kg2) {
        this.kg = kg;
        this.kz = kz;
        this.kA = ka;
        while (true) {
            if (this.kA == null) {
                break Label_0049;
            }
            try {
                final InetAddress byName = InetAddress.getByName(this.kA);
                if (byName instanceof Inet4Address) {
                    this.kB = (Inet4Address)byName;
                }
                this.kC = kc;
                this.kD = kd;
                this.kE = ke;
                this.kF = kf;
                this.kG = kg2;
            }
            catch (UnknownHostException ex) {
                this.kB = null;
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
            else if (!dh.a(this.kz, castDevice.kz) || !dh.a(this.kB, castDevice.kB) || !dh.a(this.kD, castDevice.kD) || !dh.a(this.kC, castDevice.kC) || !dh.a(this.kE, castDevice.kE) || this.kF != castDevice.kF || !dh.a(this.kG, castDevice.kG)) {
                return false;
            }
        }
        return true;
    }
    
    public String getDeviceId() {
        return this.kz;
    }
    
    public String getDeviceVersion() {
        return this.kE;
    }
    
    public String getFriendlyName() {
        return this.kC;
    }
    
    public WebImage getIcon(final int n, final int n2) {
        WebImage webImage = null;
        if (this.kG.isEmpty()) {
            return null;
        }
        if (n <= 0 || n2 <= 0) {
            return this.kG.get(0);
        }
        final Iterator<WebImage> iterator = this.kG.iterator();
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
                webImage2 = this.kG.get(0);
            }
        }
        return webImage2;
    }
    
    public List<WebImage> getIcons() {
        return Collections.unmodifiableList((List<? extends WebImage>)this.kG);
    }
    
    public Inet4Address getIpAddress() {
        return this.kB;
    }
    
    public String getModelName() {
        return this.kD;
    }
    
    public int getServicePort() {
        return this.kF;
    }
    
    int getVersionCode() {
        return this.kg;
    }
    
    public boolean hasIcons() {
        return !this.kG.isEmpty();
    }
    
    @Override
    public int hashCode() {
        if (this.kz == null) {
            return 0;
        }
        return this.kz.hashCode();
    }
    
    public boolean isSameDevice(final CastDevice castDevice) {
        if (castDevice != null) {
            if (this.getDeviceId() != null) {
                return dh.a(this.getDeviceId(), castDevice.getDeviceId());
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
        return String.format("\"%s\" (%s)", this.kC, this.kz);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        b.a(this, parcel, n);
    }
}
