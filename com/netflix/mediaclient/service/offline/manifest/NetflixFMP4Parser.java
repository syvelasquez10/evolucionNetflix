// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.manifest;

import java.io.IOException;
import com.netflix.mediaclient.Log;
import java.io.RandomAccessFile;
import com.google.android.exoplayer.util.Util;

public class NetflixFMP4Parser
{
    private static final String TAG = "NetflixFMP4Parser";
    private static final int TYPE_sidx;
    
    static {
        TYPE_sidx = Util.getIntegerCodeForString("sidx");
    }
    
    static NetflixFMP4Parser$SidxInfo parseSidxInfo(final String s) {
        try {
            final RandomAccessFile randomAccessFile = new RandomAccessFile(s, "r");
            long n = 0L;
            int int1;
            while (true) {
                int1 = randomAccessFile.readInt();
                if (randomAccessFile.readInt() == NetflixFMP4Parser.TYPE_sidx) {
                    break;
                }
                randomAccessFile.skipBytes(int1 - 8);
                n += int1;
            }
            randomAccessFile.close();
            Log.d("NetflixFMP4Parser", "sidx @" + n + ", size " + int1);
            return new NetflixFMP4Parser$SidxInfo(int1, n);
        }
        catch (IOException ex) {
            Log.w("NetflixFMP4Parser", "ParseSidxInfo " + s + ", " + ex);
            return null;
        }
    }
}
