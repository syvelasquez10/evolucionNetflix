// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import com.netflix.mediaclient.Log;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.SortedMap;
import android.content.Context;

public class BifManager
{
    private static final int CHUNK_SIZE = 1024;
    private static final int HEADER_SIZE = 64;
    private static final int INDEX_ENTRY_SIZE = 8;
    private static final int MAX_INDEX_SIZE = 28800;
    private static final String SAVED_FILE_NAME = "bif.tmp";
    private static final String TAG = "BifManager";
    private final Context mAppContext;
    private int mBifCount;
    private final SortedMap<Integer, Integer> mBifMap;
    private volatile boolean mBifReady;
    private Thread mDownloadThread;
    private int mHeaderIndexSize;
    private int mInterval;
    private String mSavedFileName;
    private volatile boolean mStopBif;
    private final TrickplayUrl[] mTrickplayUrls;
    private final ArrayList<String> mUrlList;
    private int mVersion;
    
    public BifManager(final Context mAppContext, final String s) {
        this.mBifMap = new TreeMap<Integer, Integer>();
        this.mInterval = 1000;
        this.mUrlList = new ArrayList<String>(4);
        this.mSavedFileName = "bif.tmp";
        this.mTrickplayUrls = null;
        this.mSavedFileName = "mdxbif.tmp";
        this.mAppContext = mAppContext;
        this.mUrlList.add(s);
        if (Log.isLoggable()) {
            Log.d("BifManager", "load bif from  " + s);
        }
        this.loadBif();
        Log.d("BifManager", "BifManager end");
    }
    
    public BifManager(final Context mAppContext, final TrickplayUrl[] mTrickplayUrls, final int n) {
        this.mBifMap = new TreeMap<Integer, Integer>();
        this.mInterval = 1000;
        this.mUrlList = new ArrayList<String>(4);
        this.mSavedFileName = "bif.tmp";
        this.mAppContext = mAppContext;
        this.createUrlList(this.mTrickplayUrls = mTrickplayUrls);
        this.loadBif();
        Log.d("BifManager", "BifManager end");
    }
    
    private void createUrlList(final TrickplayUrl[] array) {
        if (Log.isLoggable()) {
            Log.d("BifManager", "BifManager start, pick urls..." + array);
        }
        while (true) {
            for (int i = 0; i < this.mTrickplayUrls.length; ++i) {
                final String[] url = this.mTrickplayUrls[i].getUrl();
                if (this.mTrickplayUrls[i].getAspect() == 1.0f) {
                    int n = 0;
                    int n2;
                    while (true) {
                        n2 = i;
                        if (n >= url.length) {
                            break;
                        }
                        if (Log.isLoggable()) {
                            Log.d("BifManager", "preferred url" + n + " == " + url[n]);
                        }
                        this.mUrlList.add(url[n]);
                        ++n;
                    }
                    for (int j = 0; j < this.mTrickplayUrls.length; ++j) {
                        if (n2 != j) {
                            final String[] url2 = this.mTrickplayUrls[j].getUrl();
                            for (int k = 0; k < url2.length; ++k) {
                                this.mUrlList.add(url2[k]);
                            }
                        }
                    }
                    if (Log.isLoggable()) {
                        Log.d("BifManager", this.mTrickplayUrls.length + " TrickplayUrls entities with " + this.mUrlList.size() + " urls");
                    }
                    return;
                }
            }
            int n2 = -1;
            continue;
        }
    }
    
    private void dumpBifBuffer(final byte[] array, final int n) {
        Log.d("BifManager", String.format("first foure %02x %02x %02x %2x", array[0], array[1], array[2], array[3]));
        Log.d("BifManager", String.format("last foure %02x %02x %02x %2x", array[n - 4], array[n - 3], array[n - 2], array[n - 1]));
    }
    
    private static void dumpHeader(final byte[] array) {
        for (int i = 0; i < 64; i += 4) {
            Log.d("BifManager", String.format("%02x %02x %02x %2x", array[i], array[i + 1], array[i + 2], array[i + 3]));
        }
    }
    
    private void loadBif() {
        this.mStopBif = false;
        this.mBifReady = false;
        this.mDownloadThread = new Thread(new BifManager$1(this), "BifManagerThread");
        if (this.mDownloadThread != null) {
            this.mDownloadThread.start();
        }
    }
    
    public ByteBuffer getIndexFrame(int intValue) {
        if (Log.isLoggable()) {
            Log.d("BifManager", "get " + intValue);
        }
        if (!this.mBifReady) {
            return null;
        }
        intValue = (this.mInterval + intValue - 1) / this.mInterval;
        final SortedMap<Integer, Integer> headMap = this.mBifMap.headMap(intValue);
        final SortedMap<Integer, Integer> tailMap = this.mBifMap.tailMap(intValue);
        if (!headMap.isEmpty() && !tailMap.isEmpty()) {
            intValue = (int)headMap.get(headMap.lastKey());
            final int n = tailMap.get(tailMap.firstKey()) - intValue;
            final int mHeaderIndexSize = this.mHeaderIndexSize;
            final byte[] array = new byte[n];
            try {
                final FileInputStream openFileInput = this.mAppContext.openFileInput(this.mSavedFileName);
                openFileInput.skip(intValue - mHeaderIndexSize);
                final int read = openFileInput.read(array, 0, n);
                if (Log.isLoggable()) {
                    Log.d("BifManager", "return @" + intValue + ", with size " + n + ", read " + read);
                }
                final ByteBuffer wrap = ByteBuffer.wrap(array, 0, n);
                wrap.position(0);
                wrap.limit(n);
                openFileInput.close();
                return wrap;
            }
            catch (Exception ex) {
                Log.e("BifManager", "Failed reading bif ", ex);
            }
        }
        return null;
    }
    
    public boolean isBifReady() {
        return this.mBifReady;
    }
    
    public void release() {
        this.mStopBif = true;
        this.mAppContext.deleteFile(this.mSavedFileName);
    }
}
