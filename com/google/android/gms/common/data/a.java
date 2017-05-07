// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.data;

import java.io.OutputStream;
import java.io.DataOutputStream;
import android.os.Parcel;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import android.graphics.Bitmap$Config;
import java.io.InputStream;
import java.io.DataInputStream;
import android.os.ParcelFileDescriptor$AutoCloseInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import android.util.Log;
import java.io.Closeable;
import java.io.File;
import android.graphics.Bitmap;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class a implements SafeParcelable
{
    public static final Parcelable$Creator<a> CREATOR;
    final int BR;
    final int FD;
    ParcelFileDescriptor JK;
    private Bitmap JL;
    private boolean JM;
    private File JN;
    
    static {
        CREATOR = (Parcelable$Creator)new b();
    }
    
    a(final int br, final ParcelFileDescriptor jk, final int fd) {
        this.BR = br;
        this.JK = jk;
        this.FD = fd;
        this.JL = null;
        this.JM = false;
    }
    
    public a(final Bitmap jl) {
        this.BR = 1;
        this.JK = null;
        this.FD = 0;
        this.JL = jl;
        this.JM = true;
    }
    
    private void a(final Closeable closeable) {
        try {
            closeable.close();
        }
        catch (IOException ex) {
            Log.w("BitmapTeleporter", "Could not close stream", (Throwable)ex);
        }
    }
    
    private FileOutputStream gy() {
        if (this.JN == null) {
            throw new IllegalStateException("setTempDir() must be called before writing this object to a parcel");
        }
        File file;
        try {
            final File tempFile;
            file = (tempFile = File.createTempFile("teleporter", ".tmp", this.JN));
            final FileOutputStream fileOutputStream = new FileOutputStream(tempFile);
            final a a = this;
            final File file2 = file;
            final int n = 268435456;
            final ParcelFileDescriptor parcelFileDescriptor = ParcelFileDescriptor.open(file2, n);
            a.JK = parcelFileDescriptor;
            final File file3 = file;
            file3.delete();
            return fileOutputStream;
        }
        catch (IOException ex) {
            throw new IllegalStateException("Could not create temporary file", ex);
        }
        try {
            final File tempFile = file;
            final FileOutputStream fileOutputStream = new FileOutputStream(tempFile);
            final a a = this;
            final File file2 = file;
            final int n = 268435456;
            final ParcelFileDescriptor parcelFileDescriptor = ParcelFileDescriptor.open(file2, n);
            a.JK = parcelFileDescriptor;
            final File file3 = file;
            file3.delete();
            return fileOutputStream;
        }
        catch (FileNotFoundException ex2) {
            throw new IllegalStateException("Temporary file is somehow already deleted");
        }
    }
    
    public void a(final File jn) {
        if (jn == null) {
            throw new NullPointerException("Cannot set null temp directory");
        }
        this.JN = jn;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public Bitmap gx() {
        Label_0097: {
            if (this.JM) {
                break Label_0097;
            }
            Object wrap = new DataInputStream((InputStream)new ParcelFileDescriptor$AutoCloseInputStream(this.JK));
            try {
                final byte[] array = new byte[((DataInputStream)wrap).readInt()];
                final int int1 = ((DataInputStream)wrap).readInt();
                final int int2 = ((DataInputStream)wrap).readInt();
                final Bitmap$Config value = Bitmap$Config.valueOf(((DataInputStream)wrap).readUTF());
                ((DataInputStream)wrap).read(array);
                this.a((Closeable)wrap);
                wrap = ByteBuffer.wrap(array);
                final Bitmap bitmap = Bitmap.createBitmap(int1, int2, value);
                bitmap.copyPixelsFromBuffer((Buffer)wrap);
                this.JL = bitmap;
                this.JM = true;
                return this.JL;
            }
            catch (IOException ex) {
                throw new IllegalStateException("Could not read from parcel file descriptor", ex);
            }
            finally {
                this.a((Closeable)wrap);
            }
        }
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        Label_0100: {
            if (this.JK != null) {
                break Label_0100;
            }
            final Bitmap jl = this.JL;
            final ByteBuffer allocate = ByteBuffer.allocate(jl.getRowBytes() * jl.getHeight());
            jl.copyPixelsToBuffer((Buffer)allocate);
            final byte[] array = allocate.array();
            final DataOutputStream dataOutputStream = new DataOutputStream(this.gy());
            try {
                dataOutputStream.writeInt(array.length);
                dataOutputStream.writeInt(jl.getWidth());
                dataOutputStream.writeInt(jl.getHeight());
                dataOutputStream.writeUTF(jl.getConfig().toString());
                dataOutputStream.write(array);
                this.a(dataOutputStream);
                b.a(this, parcel, n);
            }
            catch (IOException ex) {
                throw new IllegalStateException("Could not write into unlinked file", ex);
            }
            finally {
                this.a(dataOutputStream);
            }
        }
    }
}
