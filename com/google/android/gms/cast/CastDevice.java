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
    private final int zzCY;
    private String zzQK;
    String zzQL;
    private Inet4Address zzQM;
    private String zzQN;
    private String zzQO;
    private String zzQP;
    private int zzQQ;
    private List<WebImage> zzQR;
    private int zzQS;
    private int zzwS;
    
    static {
        CREATOR = (Parcelable$Creator)new zzb();
    }
    
    private CastDevice() {
        this(3, null, null, null, null, null, -1, new ArrayList<WebImage>(), 0, -1);
    }
    
    CastDevice(final int zzCY, final String zzQK, final String zzQL, final String zzQN, final String zzQO, final String zzQP, final int zzQQ, final List<WebImage> zzQR, final int zzQS, final int zzwS) {
        this.zzCY = zzCY;
        this.zzQK = zzQK;
        this.zzQL = zzQL;
        while (true) {
            if (this.zzQL == null) {
                break Label_0049;
            }
            try {
                final InetAddress byName = InetAddress.getByName(this.zzQL);
                if (byName instanceof Inet4Address) {
                    this.zzQM = (Inet4Address)byName;
                }
                this.zzQN = zzQN;
                this.zzQO = zzQO;
                this.zzQP = zzQP;
                this.zzQQ = zzQQ;
                this.zzQR = zzQR;
                this.zzQS = zzQS;
                this.zzwS = zzwS;
            }
            catch (UnknownHostException ex) {
                this.zzQM = null;
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
            else if (!zzf.zza(this.zzQK, castDevice.zzQK) || !zzf.zza(this.zzQM, castDevice.zzQM) || !zzf.zza(this.zzQO, castDevice.zzQO) || !zzf.zza(this.zzQN, castDevice.zzQN) || !zzf.zza(this.zzQP, castDevice.zzQP) || this.zzQQ != castDevice.zzQQ || !zzf.zza(this.zzQR, castDevice.zzQR) || this.zzQS != castDevice.zzQS || this.zzwS != castDevice.zzwS) {
                return false;
            }
        }
        return true;
    }
    
    public int getCapabilities() {
        return this.zzQS;
    }
    
    public String getDeviceId() {
        return this.zzQK;
    }
    
    public String getDeviceVersion() {
        return this.zzQP;
    }
    
    public String getFriendlyName() {
        return this.zzQN;
    }
    
    public List<WebImage> getIcons() {
        return Collections.unmodifiableList((List<? extends WebImage>)this.zzQR);
    }
    
    public Inet4Address getIpAddress() {
        return this.zzQM;
    }
    
    public String getModelName() {
        return this.zzQO;
    }
    
    public int getServicePort() {
        return this.zzQQ;
    }
    
    public int getStatus() {
        return this.zzwS;
    }
    
    int getVersionCode() {
        return this.zzCY;
    }
    
    @Override
    public int hashCode() {
        if (this.zzQK == null) {
            return 0;
        }
        return this.zzQK.hashCode();
    }
    
    public void putInBundle(final Bundle bundle) {
        if (bundle == null) {
            return;
        }
        bundle.putParcelable("com.google.android.gms.cast.EXTRA_CAST_DEVICE", (Parcelable)this);
    }
    
    @Override
    public String toString() {
        return String.format("\"%s\" (%s)", this.zzQN, this.zzQK);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzb.zza(this, parcel, n);
    }
}
