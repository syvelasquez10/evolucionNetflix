// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.content;

import java.util.Iterator;
import android.net.Uri$Builder;
import java.util.Map;
import android.net.Uri;
import java.io.IOException;
import android.text.TextUtils;
import java.io.File;
import java.util.HashMap;

class FileProvider$SimplePathStrategy implements FileProvider$PathStrategy
{
    private final String mAuthority;
    private final HashMap<String, File> mRoots;
    
    public FileProvider$SimplePathStrategy(final String mAuthority) {
        this.mRoots = new HashMap<String, File>();
        this.mAuthority = mAuthority;
    }
    
    public void addRoot(final String s, final File file) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new IllegalArgumentException("Name must not be empty");
        }
        try {
            this.mRoots.put(s, file.getCanonicalFile());
        }
        catch (IOException ex) {
            throw new IllegalArgumentException("Failed to resolve canonical path for " + file, ex);
        }
    }
    
    @Override
    public File getFileForUri(Uri uri) {
        final String encodedPath = uri.getEncodedPath();
        final int index = encodedPath.indexOf(47, 1);
        final String decode = Uri.decode(encodedPath.substring(1, index));
        final String decode2 = Uri.decode(encodedPath.substring(index + 1));
        final File file = this.mRoots.get(decode);
        if (file == null) {
            throw new IllegalArgumentException("Unable to find configured root for " + uri);
        }
        uri = (Uri)new File(file, decode2);
        File canonicalFile;
        try {
            canonicalFile = ((File)uri).getCanonicalFile();
            if (!canonicalFile.getPath().startsWith(file.getPath())) {
                throw new SecurityException("Resolved path jumped beyond configured root");
            }
        }
        catch (IOException ex) {
            throw new IllegalArgumentException("Failed to resolve canonical path for " + uri);
        }
        return canonicalFile;
    }
    
    @Override
    public Uri getUriForFile(File o) {
        String canonicalPath;
        while (true) {
            while (true) {
                Label_0277: {
                    try {
                        canonicalPath = ((File)o).getCanonicalPath();
                        o = null;
                        for (final Map.Entry<String, File> entry : this.mRoots.entrySet()) {
                            final String path = entry.getValue().getPath();
                            if (!canonicalPath.startsWith(path)) {
                                break Label_0277;
                            }
                            Object o2 = entry;
                            if (o != null) {
                                if (path.length() <= ((Map.Entry<K, File>)o).getValue().getPath().length()) {
                                    break Label_0277;
                                }
                                o2 = entry;
                            }
                            o = o2;
                        }
                    }
                    catch (IOException ex) {
                        throw new IllegalArgumentException("Failed to resolve canonical path for " + o);
                    }
                    break;
                }
                Object o2 = o;
                continue;
            }
        }
        if (o == null) {
            throw new IllegalArgumentException("Failed to find configured root that contains " + canonicalPath);
        }
        final String path2 = ((Map.Entry<K, File>)o).getValue().getPath();
        String s;
        if (path2.endsWith("/")) {
            s = canonicalPath.substring(path2.length());
        }
        else {
            s = canonicalPath.substring(path2.length() + 1);
        }
        return new Uri$Builder().scheme("content").authority(this.mAuthority).encodedPath(Uri.encode((String)((Map.Entry<String, V>)o).getKey()) + '/' + Uri.encode(s, "/")).build();
    }
}
