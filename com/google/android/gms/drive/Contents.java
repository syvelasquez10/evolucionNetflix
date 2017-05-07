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

public class Contents implements SafeParcelable
{
    public static final Parcelable$Creator<Contents> CREATOR;
    final ParcelFileDescriptor Cj;
    final int Eu;
    final int Ev;
    final DriveId Ew;
    private boolean Ex;
    private boolean Ey;
    private boolean mClosed;
    final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new a();
    }
    
    Contents(final int xh, final ParcelFileDescriptor cj, final int eu, final int ev, final DriveId ew) {
        this.mClosed = false;
        this.Ex = false;
        this.Ey = false;
        this.xH = xh;
        this.Cj = cj;
        this.Eu = eu;
        this.Ev = ev;
        this.Ew = ew;
    }
    
    public void close() {
        this.mClosed = true;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public int fA() {
        return this.Eu;
    }
    
    public DriveId getDriveId() {
        return this.Ew;
    }
    
    public InputStream getInputStream() {
        if (this.mClosed) {
            throw new IllegalStateException("Contents have been closed, cannot access the input stream.");
        }
        if (this.Ev != 268435456) {
            throw new IllegalStateException("getInputStream() can only be used with contents opened with MODE_READ_ONLY.");
        }
        if (this.Ex) {
            throw new IllegalStateException("getInputStream() can only be called once per Contents instance.");
        }
        this.Ex = true;
        return new FileInputStream(this.Cj.getFileDescriptor());
    }
    
    public int getMode() {
        return this.Ev;
    }
    
    public OutputStream getOutputStream() {
        if (this.mClosed) {
            throw new IllegalStateException("Contents have been closed, cannot access the output stream.");
        }
        if (this.Ev != 536870912) {
            throw new IllegalStateException("getOutputStream() can only be used with contents opened with MODE_WRITE_ONLY.");
        }
        if (this.Ey) {
            throw new IllegalStateException("getOutputStream() can only be called once per Contents instance.");
        }
        this.Ey = true;
        return new FileOutputStream(this.Cj.getFileDescriptor());
    }
    
    public ParcelFileDescriptor getParcelFileDescriptor() {
        if (this.mClosed) {
            throw new IllegalStateException("Contents have been closed, cannot access the output stream.");
        }
        return this.Cj;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        a.a(this, parcel, n);
    }
}
