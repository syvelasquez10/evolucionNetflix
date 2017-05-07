// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer.realtime;

import android.os.ParcelFileDescriptor;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.IOException;

public interface RealTimeSocket
{
    void close() throws IOException;
    
    InputStream getInputStream() throws IOException;
    
    OutputStream getOutputStream() throws IOException;
    
    ParcelFileDescriptor getParcelFileDescriptor() throws IOException;
    
    boolean isClosed();
}
