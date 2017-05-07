// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive;

import android.os.Parcel;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@Deprecated
public class Contents implements SafeParcelable
{
    public static final Parcelable$Creator<Contents> CREATOR;
    final int BR;
    final ParcelFileDescriptor Kx;
    final int MN;
    final DriveId MO;
    final boolean MP;
    private boolean MQ;
    private boolean MR;
    private boolean mClosed;
    final int uQ;
    
    static {
        CREATOR = (Parcelable$Creator)new a();
    }
    
    Contents(final int br, final ParcelFileDescriptor kx, final int uq, final int mn, final DriveId mo, final boolean mp) {
        this.mClosed = false;
        this.MQ = false;
        this.MR = false;
        this.BR = br;
        this.Kx = kx;
        this.uQ = uq;
        this.MN = mn;
        this.MO = mo;
        this.MP = mp;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public DriveId getDriveId() {
        return this.MO;
    }
    
    public InputStream getInputStream() {
        if (this.mClosed) {
            throw new IllegalStateException("Contents have been closed, cannot access the input stream.");
        }
        if (this.MN != 268435456) {
            throw new IllegalStateException("getInputStream() can only be used with contents opened with MODE_READ_ONLY.");
        }
        if (this.MQ) {
            throw new IllegalStateException("getInputStream() can only be called once per Contents instance.");
        }
        this.MQ = true;
        return new FileInputStream(this.Kx.getFileDescriptor());
    }
    
    public int getMode() {
        return this.MN;
    }
    
    public OutputStream getOutputStream() {
        if (this.mClosed) {
            throw new IllegalStateException("Contents have been closed, cannot access the output stream.");
        }
        if (this.MN != 536870912) {
            throw new IllegalStateException("getOutputStream() can only be used with contents opened with MODE_WRITE_ONLY.");
        }
        if (this.MR) {
            throw new IllegalStateException("getOutputStream() can only be called once per Contents instance.");
        }
        this.MR = true;
        return new FileOutputStream(this.Kx.getFileDescriptor());
    }
    
    public ParcelFileDescriptor getParcelFileDescriptor() {
        if (this.mClosed) {
            throw new IllegalStateException("Contents have been closed, cannot access the output stream.");
        }
        return this.Kx;
    }
    
    public int getRequestId() {
        return this.uQ;
    }
    
    public void hJ() {
        this.mClosed = true;
    }
    
    public boolean hK() {
        return this.mClosed;
    }
    
    public boolean hL() {
        return this.MP;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        a.a(this, parcel, n);
    }
}
