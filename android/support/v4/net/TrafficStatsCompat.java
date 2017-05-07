// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.net;

import java.net.Socket;
import android.os.Build$VERSION;

public class TrafficStatsCompat
{
    private static final TrafficStatsCompat$TrafficStatsCompatImpl IMPL;
    
    static {
        if (Build$VERSION.SDK_INT >= 14) {
            IMPL = new TrafficStatsCompat$IcsTrafficStatsCompatImpl();
            return;
        }
        IMPL = new TrafficStatsCompat$BaseTrafficStatsCompatImpl();
    }
    
    public static void clearThreadStatsTag() {
        TrafficStatsCompat.IMPL.clearThreadStatsTag();
    }
    
    public static int getThreadStatsTag() {
        return TrafficStatsCompat.IMPL.getThreadStatsTag();
    }
    
    public static void incrementOperationCount(final int n) {
        TrafficStatsCompat.IMPL.incrementOperationCount(n);
    }
    
    public static void incrementOperationCount(final int n, final int n2) {
        TrafficStatsCompat.IMPL.incrementOperationCount(n, n2);
    }
    
    public static void setThreadStatsTag(final int threadStatsTag) {
        TrafficStatsCompat.IMPL.setThreadStatsTag(threadStatsTag);
    }
    
    public static void tagSocket(final Socket socket) {
        TrafficStatsCompat.IMPL.tagSocket(socket);
    }
    
    public static void untagSocket(final Socket socket) {
        TrafficStatsCompat.IMPL.untagSocket(socket);
    }
}
