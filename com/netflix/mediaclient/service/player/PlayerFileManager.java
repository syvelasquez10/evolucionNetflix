// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player;

import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.FileUtils;
import com.netflix.mediaclient.Log;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.IPlayerFileCache;

public class PlayerFileManager implements IPlayerFileCache
{
    private static final String FILE_PATH = "player_cache";
    protected static final String TAG = "nf_subtitles";
    private Context mContext;
    private File mRoot;
    private Map<String, File> mSubtitles;
    
    public PlayerFileManager(final Context mContext) {
        this.mSubtitles = new HashMap<String, File>();
        this.mContext = mContext;
        this.mRoot = this.mContext.getDir("player_cache", 0);
        Log.d("nf_subtitles", "Remove cached data for player started... ");
        final boolean deleteRecursive = FileUtils.deleteRecursive(this.mRoot);
        if (Log.isLoggable()) {
            Log.d("nf_subtitles", "Removed cached entries: " + deleteRecursive);
        }
        this.mRoot = this.mContext.getDir("player_cache", 0);
    }
    
    private String getKey(final String s, final String s2) {
        if (StringUtils.isEmpty(s) || StringUtils.isEmpty(s2)) {
            Log.e("nf_subtitles", "Bad playable id and or language.");
            return null;
        }
        return s + "_" + s2;
    }
    
    public String existSubtitleCache(String key, final String s) {
        key = this.getKey(key, s);
        if (key != null && this.mSubtitles.get(key) != null) {
            return key;
        }
        return null;
    }
    
    @Override
    public File getFile(final String s, final String s2) {
        if (Log.isLoggable()) {
            Log.d("nf_subtitles", "Get file from cache " + s + " with name " + s2);
        }
        if (StringUtils.isEmpty(s) || StringUtils.isEmpty(s2)) {
            Log.w("nf_subtitles", "Bad arguments: key or name not found");
            return null;
        }
        final File file = this.mSubtitles.get(s);
        if (file == null) {
            if (Log.isLoggable()) {
                Log.e("nf_subtitles", "Subtitle cache for " + s + " does not exist!");
            }
            return null;
        }
        final File file2 = new File(file, s2);
        if (file2.exists()) {
            Log.d("nf_subtitles", "File exist");
            return file2;
        }
        Log.e("nf_subtitles", "File does NOT exist!");
        return null;
    }
    
    @Override
    public String getSubtitleCache(final String s, String s2) {
        s2 = this.getKey(s, s2);
        if (StringUtils.isEmpty(s)) {
            Log.e("nf_subtitles", "Playable ID is empty, use temp directory!");
            s2 = String.valueOf(System.currentTimeMillis());
        }
        int n;
        if (this.mSubtitles.get(s2) != null) {
            n = 1;
        }
        else {
            n = 0;
        }
        if (n == 0) {
            final File file = new File(this.mRoot, s2);
            if (!file.exists()) {
                final boolean mkdir = file.mkdir();
                if (Log.isLoggable()) {
                    Log.d("nf_subtitles", "Cache " + file.getAbsolutePath() + " created: " + mkdir);
                }
            }
            else if (Log.isLoggable()) {
                Log.d("nf_subtitles", "Cache " + file.getAbsolutePath() + " already existed!");
            }
            this.mSubtitles.put(s2, file);
        }
        return s2;
    }
    
    @Override
    public boolean moveFile(String string, final String s, final String s2) {
        if (Log.isLoggable()) {
            Log.d("nf_subtitles", "Move file  " + s + "to cache " + string + " with name " + s2);
        }
        boolean moveFile;
        if (StringUtils.isEmpty(string) || StringUtils.isEmpty(s) || StringUtils.isEmpty(s2)) {
            Log.w("nf_subtitles", "Bad arguments: key or source or destination not found!");
            moveFile = false;
        }
        else {
            final File file = this.mSubtitles.get(string);
            if (file == null) {
                Log.e("nf_subtitles", "Subtitle cache for " + string + " does not exist!");
                return false;
            }
            string = file.getAbsolutePath() + "/" + s2;
            final boolean b = moveFile = FileUtils.moveFile(s, string);
            if (Log.isLoggable()) {
                Log.d("nf_subtitles", "Move file  " + s + " to " + string + " was success " + b);
                return b;
            }
        }
        return moveFile;
    }
    
    @Override
    public String saveFile(final String s, final String s2, final byte[] array) {
        if (Log.isLoggable()) {
            Log.d("nf_subtitles", "Save data as file to cache " + s + " with name " + s2);
        }
        final boolean empty = StringUtils.isEmpty(s);
        final boolean empty2 = StringUtils.isEmpty(s2);
        final boolean b = array == null;
        if (empty || empty2 || b) {
            Log.e("nf_subtitles", "Bad arguments! Key empty %b or name empty %b or bytes are null %b!", empty, empty2, b);
            return null;
        }
        final File file = this.mSubtitles.get(s);
        if (file == null) {
            Log.e("nf_subtitles", "Subtitle cache for " + s + " does not exist!");
            return null;
        }
        File file2 = new File(file, s2);
        while (true) {
            try {
                final boolean newFile = file2.createNewFile();
                if (Log.isLoggable()) {
                    Log.d("nf_subtitles", "File " + file2.getAbsolutePath() + " created: " + newFile);
                }
                final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file2));
                bufferedOutputStream.write(array);
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
                if (file2 != null) {
                    return file2.getAbsolutePath();
                }
            }
            catch (Throwable t) {
                Log.e("nf_subtitles", "Failed to save file ", t);
                file2 = null;
                continue;
            }
            break;
        }
        return null;
    }
}
