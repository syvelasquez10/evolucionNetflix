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
    final int kg;
    private boolean mClosed;
    final ParcelFileDescriptor om;
    final int qE;
    final int qF;
    final DriveId qG;
    private boolean qH;
    private boolean qI;
    
    static {
        CREATOR = (Parcelable$Creator)new a();
    }
    
    Contents(final int kg, final ParcelFileDescriptor om, final int qe, final int qf, final DriveId qg) {
        this.mClosed = false;
        this.qH = false;
        this.qI = false;
        this.kg = kg;
        this.om = om;
        this.qE = qe;
        this.qF = qf;
        this.qG = qg;
    }
    
    public int cJ() {
        return this.qE;
    }
    
    public void close() {
        this.mClosed = true;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public DriveId getDriveId() {
        return this.qG;
    }
    
    public InputStream getInputStream() {
        if (this.mClosed) {
            throw new IllegalStateException("Contents have been closed, cannot access the input stream.");
        }
        if (this.qF != 268435456) {
            throw new IllegalStateException("getInputStream() can only be used with contents opened with MODE_READ_ONLY.");
        }
        if (this.qH) {
            throw new IllegalStateException("getInputStream() can only be called once per Contents instance.");
        }
        this.qH = true;
        return new FileInputStream(this.om.getFileDescriptor());
    }
    
    public int getMode() {
        return this.qF;
    }
    
    public OutputStream getOutputStream() {
        if (this.mClosed) {
            throw new IllegalStateException("Contents have been closed, cannot access the output stream.");
        }
        if (this.qF != 536870912) {
            throw new IllegalStateException("getOutputStream() can only be used with contents opened with MODE_WRITE_ONLY.");
        }
        if (this.qI) {
            throw new IllegalStateException("getOutputStream() can only be called once per Contents instance.");
        }
        this.qI = true;
        return new FileOutputStream(this.om.getFileDescriptor());
    }
    
    public ParcelFileDescriptor getParcelFileDescriptor() {
        if (this.mClosed) {
            throw new IllegalStateException("Contents have been closed, cannot access the output stream.");
        }
        return this.om;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        a.a(this, parcel, n);
    }
}
