// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import android.os.Parcel;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.IOException;
import android.net.LocalSocket;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.games.multiplayer.realtime.RealTimeSocket;

final class RealTimeSocketImpl implements RealTimeSocket
{
    private ParcelFileDescriptor Cj;
    private final LocalSocket JP;
    private final String Jg;
    
    RealTimeSocketImpl(final LocalSocket jp, final String jg) {
        this.JP = jp;
        this.Jg = jg;
    }
    
    @Override
    public void close() throws IOException {
        this.JP.close();
    }
    
    @Override
    public InputStream getInputStream() throws IOException {
        return this.JP.getInputStream();
    }
    
    @Override
    public OutputStream getOutputStream() throws IOException {
        return this.JP.getOutputStream();
    }
    
    @Override
    public ParcelFileDescriptor getParcelFileDescriptor() throws IOException {
        if (this.Cj == null && !this.isClosed()) {
            final Parcel obtain = Parcel.obtain();
            obtain.writeFileDescriptor(this.JP.getFileDescriptor());
            obtain.setDataPosition(0);
            this.Cj = obtain.readFileDescriptor();
        }
        return this.Cj;
    }
    
    @Override
    public boolean isClosed() {
        return !this.JP.isConnected() && !this.JP.isBound();
    }
}
