// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive;

import android.os.Parcel;
import android.util.Log;
import com.google.android.gms.internal.iz;
import android.util.Base64;
import com.google.android.gms.internal.iy;
import com.google.android.gms.drive.internal.q;
import com.google.android.gms.internal.eg;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class DriveId implements SafeParcelable
{
    public static final Parcelable$Creator<DriveId> CREATOR;
    final int kg;
    final String qO;
    final long qP;
    final long qQ;
    private volatile String qR;
    
    static {
        CREATOR = (Parcelable$Creator)new c();
    }
    
    DriveId(final int kg, final String qo, final long qp, final long qq) {
        final boolean b = false;
        this.qR = null;
        this.kg = kg;
        this.qO = qo;
        eg.r(!"".equals(qo));
        boolean b2 = false;
        Label_0058: {
            if (qo == null) {
                b2 = b;
                if (qp == -1L) {
                    break Label_0058;
                }
            }
            b2 = true;
        }
        eg.r(b2);
        this.qP = qp;
        this.qQ = qq;
    }
    
    public DriveId(final String s, final long n, final long n2) {
        this(1, s, n, n2);
    }
    
    public static DriveId ab(final String s) {
        eg.f(s);
        return new DriveId(s, -1L, -1L);
    }
    
    static DriveId d(final byte[] array) {
        while (true) {
            while (true) {
                q e;
                try {
                    e = q.e(array);
                    if ("".equals(e.rt)) {
                        final String rt = null;
                        return new DriveId(e.versionCode, rt, e.ru, e.rv);
                    }
                }
                catch (iy iy) {
                    throw new IllegalArgumentException();
                }
                final String rt = e.rt;
                continue;
            }
        }
    }
    
    public static DriveId decodeFromString(final String s) {
        eg.b(s.startsWith("DriveId:"), "Invalid DriveId: " + s);
        return d(Base64.decode(s.substring("DriveId:".length()), 10));
    }
    
    final byte[] cL() {
        final q q = new q();
        q.versionCode = this.kg;
        String qo;
        if (this.qO == null) {
            qo = "";
        }
        else {
            qo = this.qO;
        }
        q.rt = qo;
        q.ru = this.qP;
        q.rv = this.qQ;
        return iz.a(q);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public final String encodeToString() {
        if (this.qR == null) {
            this.qR = "DriveId:" + Base64.encodeToString(this.cL(), 10);
        }
        return this.qR;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof DriveId) {
            final DriveId driveId = (DriveId)o;
            if (driveId.qQ != this.qQ) {
                Log.w("DriveId", "Attempt to compare invalid DriveId detected. Has local storage been cleared?");
                return false;
            }
            if (driveId.qP == -1L && this.qP == -1L) {
                return driveId.qO.equals(this.qO);
            }
            if (driveId.qP == this.qP) {
                return true;
            }
        }
        return false;
    }
    
    public String getResourceId() {
        return this.qO;
    }
    
    @Override
    public int hashCode() {
        if (this.qP == -1L) {
            return this.qO.hashCode();
        }
        return (String.valueOf(this.qQ) + String.valueOf(this.qP)).hashCode();
    }
    
    @Override
    public String toString() {
        return this.encodeToString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        c.a(this, parcel, n);
    }
}
