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
    private String zzTn;
    String zzTo;
    private Inet4Address zzTp;
    private String zzTq;
    private String zzTr;
    private String zzTs;
    private int zzTt;
    private List<WebImage> zzTu;
    private int zzTv;
    private int zzxJ;
    
    static {
        CREATOR = (Parcelable$Creator)new zzb();
    }
    
    private CastDevice() {
        this(3, null, null, null, null, null, -1, new ArrayList<WebImage>(), 0, -1);
    }
    
    CastDevice(final int mVersionCode, final String zzTn, final String zzTo, final String zzTq, final String zzTr, final String zzTs, final int zzTt, final List<WebImage> zzTu, final int zzTv, final int zzxJ) {
        this.mVersionCode = mVersionCode;
        this.zzTn = zzTn;
        this.zzTo = zzTo;
        while (true) {
            if (this.zzTo == null) {
                break Label_0049;
            }
            try {
                final InetAddress byName = InetAddress.getByName(this.zzTo);
                if (byName instanceof Inet4Address) {
                    this.zzTp = (Inet4Address)byName;
                }
                this.zzTq = zzTq;
                this.zzTr = zzTr;
                this.zzTs = zzTs;
                this.zzTt = zzTt;
                this.zzTu = zzTu;
                this.zzTv = zzTv;
                this.zzxJ = zzxJ;
            }
            catch (UnknownHostException ex) {
                this.zzTp = null;
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
            else if (!zzf.zza(this.zzTn, castDevice.zzTn) || !zzf.zza(this.zzTp, castDevice.zzTp) || !zzf.zza(this.zzTr, castDevice.zzTr) || !zzf.zza(this.zzTq, castDevice.zzTq) || !zzf.zza(this.zzTs, castDevice.zzTs) || this.zzTt != castDevice.zzTt || !zzf.zza(this.zzTu, castDevice.zzTu) || this.zzTv != castDevice.zzTv || this.zzxJ != castDevice.zzxJ) {
                return false;
            }
        }
        return true;
    }
    
    public int getCapabilities() {
        return this.zzTv;
    }
    
    public String getDeviceId() {
        return this.zzTn;
    }
    
    public String getDeviceVersion() {
        return this.zzTs;
    }
    
    public String getFriendlyName() {
        return this.zzTq;
    }
    
    public List<WebImage> getIcons() {
        return Collections.unmodifiableList((List<? extends WebImage>)this.zzTu);
    }
    
    public Inet4Address getIpAddress() {
        return this.zzTp;
    }
    
    public String getModelName() {
        return this.zzTr;
    }
    
    public int getServicePort() {
        return this.zzTt;
    }
    
    public int getStatus() {
        return this.zzxJ;
    }
    
    int getVersionCode() {
        return this.mVersionCode;
    }
    
    @Override
    public int hashCode() {
        if (this.zzTn == null) {
            return 0;
        }
        return this.zzTn.hashCode();
    }
    
    public void putInBundle(final Bundle bundle) {
        if (bundle == null) {
            return;
        }
        bundle.putParcelable("com.google.android.gms.cast.EXTRA_CAST_DEVICE", (Parcelable)this);
    }
    
    @Override
    public String toString() {
        return String.format("\"%s\" (%s)", this.zzTq, this.zzTn);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzb.zza(this, parcel, n);
    }
}
