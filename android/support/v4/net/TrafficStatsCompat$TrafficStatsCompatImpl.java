// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.net;

import java.net.Socket;

interface TrafficStatsCompat$TrafficStatsCompatImpl
{
    void clearThreadStatsTag();
    
    int getThreadStatsTag();
    
    void incrementOperationCount(final int p0);
    
    void incrementOperationCount(final int p0, final int p1);
    
    void setThreadStatsTag(final int p0);
    
    void tagSocket(final Socket p0);
    
    void untagSocket(final Socket p0);
}
