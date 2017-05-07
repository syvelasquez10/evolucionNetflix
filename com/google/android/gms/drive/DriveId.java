// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive;

import android.os.Parcel;
import com.google.android.gms.internal.kt;
import android.util.Log;
import com.google.android.gms.internal.ks;
import com.google.android.gms.drive.internal.y;
import android.util.Base64;
import com.google.android.gms.internal.fq;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class DriveId implements SafeParcelable
{
    public static final Parcelable$Creator<DriveId> CREATOR;
    final String EH;
    final long EI;
    final long EJ;
    private volatile String EK;
    final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new d();
    }
    
    DriveId(final int xh, final String eh, final long ei, final long ej) {
        final boolean b = false;
        this.EK = null;
        this.xH = xh;
        this.EH = eh;
        fq.z(!"".equals(eh));
        boolean b2 = false;
        Label_0058: {
            if (eh == null) {
                b2 = b;
                if (ei == -1L) {
                    break Label_0058;
                }
            }
            b2 = true;
        }
        fq.z(b2);
        this.EI = ei;
        this.EJ = ej;
    }
    
    public DriveId(final String s, final long n, final long n2) {
        this(1, s, n, n2);
    }
    
    public static DriveId aw(final String s) {
        fq.f(s);
        return new DriveId(s, -1L, -1L);
    }
    
    public static DriveId decodeFromString(final String s) {
        fq.b(s.startsWith("DriveId:"), "Invalid DriveId: " + s);
        return f(Base64.decode(s.substring("DriveId:".length()), 10));
    }
    
    static DriveId f(final byte[] array) {
        while (true) {
            while (true) {
                y g;
                try {
                    g = y.g(array);
                    if ("".equals(g.FC)) {
                        final String fc = null;
                        return new DriveId(g.versionCode, fc, g.FD, g.FE);
                    }
                }
                catch (ks ks) {
                    throw new IllegalArgumentException();
                }
                final String fc = g.FC;
                continue;
            }
        }
    }
    
    public int describeContents() {
        return 0;
    }
    
    public final String encodeToString() {
        if (this.EK == null) {
            this.EK = "DriveId:" + Base64.encodeToString(this.fC(), 10);
        }
        return this.EK;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof DriveId) {
            final DriveId driveId = (DriveId)o;
            if (driveId.EJ != this.EJ) {
                Log.w("DriveId", "Attempt to compare invalid DriveId detected. Has local storage been cleared?");
                return false;
            }
            if (driveId.EI == -1L && this.EI == -1L) {
                return driveId.EH.equals(this.EH);
            }
            if (driveId.EI == this.EI) {
                return true;
            }
        }
        return false;
    }
    
    final byte[] fC() {
        final y y = new y();
        y.versionCode = this.xH;
        String eh;
        if (this.EH == null) {
            eh = "";
        }
        else {
            eh = this.EH;
        }
        y.FC = eh;
        y.FD = this.EI;
        y.FE = this.EJ;
        return kt.d(y);
    }
    
    public String getResourceId() {
        return this.EH;
    }
    
    @Override
    public int hashCode() {
        if (this.EI == -1L) {
            return this.EH.hashCode();
        }
        return (String.valueOf(this.EJ) + String.valueOf(this.EI)).hashCode();
    }
    
    @Override
    public String toString() {
        return this.encodeToString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        d.a(this, parcel, n);
    }
}
