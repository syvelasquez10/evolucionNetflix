// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Collections;
import com.google.android.gms.cast.internal.zzf;
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
    private final int mVersionCode;
    private String zzVd;
    String zzVe;
    private Inet4Address zzVf;
    private String zzVg;
    private String zzVh;
    private String zzVi;
    private int zzVj;
    private List<WebImage> zzVk;
    private int zzVl;
    private int zzys;
    
    static {
        CREATOR = (Parcelable$Creator)new zzb();
    }
    
    private CastDevice() {
        this(3, null, null, null, null, null, -1, new ArrayList<WebImage>(), 0, -1);
    }
    
    CastDevice(final int mVersionCode, final String zzVd, final String zzVe, final String zzVg, final String zzVh, final String zzVi, final int zzVj, final List<WebImage> zzVk, final int zzVl, final int zzys) {
        this.mVersionCode = mVersionCode;
        this.zzVd = zzVd;
        this.zzVe = zzVe;
        while (true) {
            if (this.zzVe == null) {
                break Label_0049;
            }
            try {
                final InetAddress byName = InetAddress.getByName(this.zzVe);
                if (byName instanceof Inet4Address) {
                    this.zzVf = (Inet4Address)byName;
                }
                this.zzVg = zzVg;
                this.zzVh = zzVh;
                this.zzVi = zzVi;
                this.zzVj = zzVj;
                this.zzVk = zzVk;
                this.zzVl = zzVl;
                this.zzys = zzys;
            }
            catch (UnknownHostException ex) {
                this.zzVf = null;
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
            else if (!zzf.zza(this.zzVd, castDevice.zzVd) || !zzf.zza(this.zzVf, castDevice.zzVf) || !zzf.zza(this.zzVh, castDevice.zzVh) || !zzf.zza(this.zzVg, castDevice.zzVg) || !zzf.zza(this.zzVi, castDevice.zzVi) || this.zzVj != castDevice.zzVj || !zzf.zza(this.zzVk, castDevice.zzVk) || this.zzVl != castDevice.zzVl || this.zzys != castDevice.zzys) {
                return false;
            }
        }
        return true;
    }
    
    public int getCapabilities() {
        return this.zzVl;
    }
    
    public String getDeviceId() {
        return this.zzVd;
    }
    
    public String getDeviceVersion() {
        return this.zzVi;
    }
    
    public String getFriendlyName() {
        return this.zzVg;
    }
    
    public List<WebImage> getIcons() {
        return Collections.unmodifiableList((List<? extends WebImage>)this.zzVk);
    }
    
    public Inet4Address getIpAddress() {
        return this.zzVf;
    }
    
    public String getModelName() {
        return this.zzVh;
    }
    
    public int getServicePort() {
        return this.zzVj;
    }
    
    public int getStatus() {
        return this.zzys;
    }
    
    int getVersionCode() {
        return this.mVersionCode;
    }
    
    @Override
    public int hashCode() {
        if (this.zzVd == null) {
            return 0;
        }
        return this.zzVd.hashCode();
    }
    
    public void putInBundle(final Bundle bundle) {
        if (bundle == null) {
            return;
        }
        bundle.putParcelable("com.google.android.gms.cast.EXTRA_CAST_DEVICE", (Parcelable)this);
    }
    
    @Override
    public String toString() {
        return String.format("\"%s\" (%s)", this.zzVg, this.zzVd);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzb.zza(this, parcel, n);
    }
}
