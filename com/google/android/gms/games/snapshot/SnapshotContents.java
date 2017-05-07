// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.snapshot;

import android.os.Parcel;
import com.google.android.gms.internal.jy;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import android.os.ParcelFileDescriptor;
import java.nio.channels.FileChannel;
import java.io.IOException;
import com.google.android.gms.games.internal.GamesLog;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class SnapshotContents implements SafeParcelable
{
    public static final SnapshotContentsCreator CREATOR;
    private static final Object acV;
    private final int BR;
    private Contents Op;
    
    static {
        acV = new Object();
        CREATOR = new SnapshotContentsCreator();
    }
    
    SnapshotContents(final int br, final Contents op) {
        this.BR = br;
        this.Op = op;
    }
    
    public SnapshotContents(final Contents contents) {
        this(1, contents);
    }
    
    private boolean a(final int n, final byte[] array, final int n2, final int n3, final boolean b) {
        while (true) {
            Label_0123: {
                if (this.isClosed()) {
                    break Label_0123;
                }
                final boolean b2 = true;
                n.a(b2, (Object)"Must provide a previously opened SnapshotContents");
                synchronized (SnapshotContents.acV) {
                    final FileOutputStream fileOutputStream = new FileOutputStream(this.Op.getParcelFileDescriptor().getFileDescriptor());
                    final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                    try {
                        final FileChannel channel = fileOutputStream.getChannel();
                        channel.position(n);
                        bufferedOutputStream.write(array, n2, n3);
                        if (b) {
                            channel.truncate(array.length);
                        }
                        bufferedOutputStream.flush();
                        return true;
                    }
                    catch (IOException ex) {
                        GamesLog.a("SnapshotContents", "Failed to write snapshot data", ex);
                        return false;
                    }
                }
            }
            final boolean b2 = false;
            continue;
        }
    }
    
    public void close() {
        this.Op.hJ();
        this.Op = null;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public Contents getContents() {
        return this.Op;
    }
    
    public ParcelFileDescriptor getParcelFileDescriptor() {
        n.a(!this.isClosed(), (Object)"Cannot mutate closed contents!");
        return this.Op.getParcelFileDescriptor();
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    public boolean isClosed() {
        return this.Op == null;
    }
    
    public boolean modifyBytes(final int n, final byte[] array, final int n2, final int n3) {
        return this.a(n, array, n2, array.length, false);
    }
    
    public byte[] readFully() throws IOException {
        boolean b = false;
        if (!this.isClosed()) {
            b = true;
        }
        n.a(b, (Object)"Must provide a previously opened Snapshot");
        synchronized (SnapshotContents.acV) {
            final FileInputStream fileInputStream = new FileInputStream(this.Op.getParcelFileDescriptor().getFileDescriptor());
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            try {
                fileInputStream.getChannel().position(0L);
                final byte[] a = jy.a(bufferedInputStream, false);
                fileInputStream.getChannel().position(0L);
                return a;
            }
            catch (IOException ex) {
                GamesLog.b("SnapshotContents", "Failed to read snapshot data", ex);
                throw ex;
            }
        }
    }
    
    public boolean writeBytes(final byte[] array) {
        return this.a(0, array, 0, array.length, true);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        SnapshotContentsCreator.a(this, parcel, n);
    }
}
