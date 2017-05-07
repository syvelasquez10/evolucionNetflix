// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer.realtime;

import android.os.ParcelFileDescriptor;
import java.io.OutputStream;
import java.io.InputStream;

public interface RealTimeSocket
{
    void close();
    
    InputStream getInputStream();
    
    OutputStream getOutputStream();
    
    ParcelFileDescriptor getParcelFileDescriptor();
    
    boolean isClosed();
}
