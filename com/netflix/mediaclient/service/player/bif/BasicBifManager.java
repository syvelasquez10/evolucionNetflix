// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.bif;

import java.io.IOException;
import java.nio.ByteOrder;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.RandomAccessFile;
import com.netflix.mediaclient.Log;
import java.nio.ByteBuffer;
import java.util.TreeMap;
import java.util.SortedMap;

abstract class BasicBifManager implements IBifManager
{
    private static final int CHUNK_SIZE = 1024;
    private static final int HEADER_SIZE = 64;
    private static final int INDEX_ENTRY_SIZE = 8;
    private static final int MAX_INDEX_SIZE = 28800;
    private static final String TAG = "BasicBifManager";
    private final SortedMap<Integer, Integer> mBifMap;
    private int mInterval;
    
    BasicBifManager() {
        this.mBifMap = new TreeMap<Integer, Integer>();
        this.mInterval = 1000;
    }
    
    @Override
    public ByteBuffer getIndexFrame(int intValue) {
        if (Log.isLoggable()) {
            Log.d("BasicBifManager", "get " + intValue);
        }
        if (!this.isBifLoaded()) {
            return null;
        }
        intValue = (this.mInterval + intValue - 1) / this.mInterval;
        final SortedMap<Integer, Integer> headMap = this.mBifMap.headMap(intValue);
        final SortedMap<Integer, Integer> tailMap = this.mBifMap.tailMap(intValue);
        if (!headMap.isEmpty() && !tailMap.isEmpty()) {
            intValue = (int)headMap.get(headMap.lastKey());
            final int n = tailMap.get(tailMap.firstKey()) - intValue;
            final byte[] array = new byte[n];
            try {
                final RandomAccessFile randomAccessFile = this.getRandomAccessFile();
                randomAccessFile.seek(intValue);
                final int read = randomAccessFile.read(array, 0, n);
                if (Log.isLoggable()) {
                    Log.d("BasicBifManager", "return @" + intValue + ", with size " + n + ", read " + read);
                }
                final ByteBuffer wrap = ByteBuffer.wrap(array, 0, n);
                wrap.position(0);
                wrap.limit(n);
                return wrap;
            }
            catch (Exception ex) {
                Log.e("BasicBifManager", ex, "Failed reading bif ", new Object[0]);
            }
        }
        return null;
    }
    
    protected abstract RandomAccessFile getRandomAccessFile();
    
    protected abstract boolean isBifLoaded();
    
    protected boolean parseIndexFromInputStream(final InputStream inputStream) {
        while (true) {
            while (true) {
                int n2;
                int read2;
                try {
                    final BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                    final byte[] array = new byte[64];
                    final int read = bufferedInputStream.read(array, 0, 64);
                    if (Log.isLoggable()) {
                        Log.d("BasicBifManager", "read " + read + " bytes");
                    }
                    if (read < 64) {
                        bufferedInputStream.close();
                        return false;
                    }
                    final ByteBuffer wrap = ByteBuffer.wrap(array);
                    wrap.order(ByteOrder.nativeOrder());
                    wrap.order(ByteOrder.nativeOrder());
                    final int int1 = wrap.getInt(8);
                    final int int2 = wrap.getInt(12);
                    this.mInterval = wrap.getInt(16);
                    if (Log.isLoggable()) {
                        Log.d("BasicBifManager", "version= " + int1 + ", bifCount= " + int2 + ",mInterval= " + this.mInterval);
                    }
                    if (int2 <= 0 || int2 > 28800) {
                        if (Log.isLoggable()) {
                            Log.d("BasicBifManager", "bif count may not be correct" + int2);
                        }
                        bufferedInputStream.close();
                        return false;
                    }
                    final int n = (int2 + 1) * 8;
                    final byte[] array2 = new byte[n];
                    if (Log.isLoggable()) {
                        Log.d("BasicBifManager", "try to read index " + n);
                    }
                    n2 = 0;
                    if (n2 >= n) {
                        final ByteBuffer wrap2 = ByteBuffer.wrap(array2);
                        wrap2.order(ByteOrder.nativeOrder());
                        for (int i = 0; i < n2; i += 8) {
                            final int int3 = wrap2.getInt();
                            final int int4 = wrap2.getInt();
                            int n3;
                            if ((n3 = int3) == -1) {
                                n3 = Integer.MAX_VALUE;
                            }
                            this.mBifMap.put(n3, int4);
                        }
                        bufferedInputStream.close();
                        return true;
                    }
                    int n4;
                    if (n - n2 < 1024) {
                        n4 = n - n2;
                    }
                    else {
                        n4 = 1024;
                    }
                    read2 = bufferedInputStream.read(array2, n2, n4);
                    if (read2 != n4 && Log.isLoggable()) {
                        Log.d("BasicBifManager", "attempt to read " + n4 + ", actual " + read2 + " bytes");
                    }
                    if (read2 <= 0) {
                        bufferedInputStream.close();
                        return false;
                    }
                }
                catch (IOException ex) {
                    return false;
                }
                n2 += read2;
                continue;
            }
        }
    }
}
