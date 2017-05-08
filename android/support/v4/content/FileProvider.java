// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.content;

import android.database.MatrixCursor;
import android.database.Cursor;
import android.os.ParcelFileDescriptor;
import android.content.ContentValues;
import android.webkit.MimeTypeMap;
import android.content.pm.ProviderInfo;
import android.content.res.XmlResourceParser;
import android.os.Environment;
import android.net.Uri;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import android.content.Context;
import java.util.HashMap;
import java.io.File;
import android.content.ContentProvider;

public class FileProvider extends ContentProvider
{
    private static final String ATTR_NAME = "name";
    private static final String ATTR_PATH = "path";
    private static final String[] COLUMNS;
    private static final File DEVICE_ROOT;
    private static final String META_DATA_FILE_PROVIDER_PATHS = "android.support.FILE_PROVIDER_PATHS";
    private static final String TAG_CACHE_PATH = "cache-path";
    private static final String TAG_EXTERNAL = "external-path";
    private static final String TAG_EXTERNAL_CACHE = "external-cache-path";
    private static final String TAG_EXTERNAL_FILES = "external-files-path";
    private static final String TAG_FILES_PATH = "files-path";
    private static final String TAG_ROOT_PATH = "root-path";
    private static HashMap<String, FileProvider$PathStrategy> sCache;
    private FileProvider$PathStrategy mStrategy;
    
    static {
        COLUMNS = new String[] { "_display_name", "_size" };
        DEVICE_ROOT = new File("/");
        FileProvider.sCache = new HashMap<String, FileProvider$PathStrategy>();
    }
    
    private static File buildPath(File file, final String... array) {
        for (int length = array.length, i = 0; i < length; ++i) {
            final String s = array[i];
            if (s != null) {
                file = new File(file, s);
            }
        }
        return file;
    }
    
    private static Object[] copyOf(final Object[] array, final int n) {
        final Object[] array2 = new Object[n];
        System.arraycopy(array, 0, array2, 0, n);
        return array2;
    }
    
    private static String[] copyOf(final String[] array, final int n) {
        final String[] array2 = new String[n];
        System.arraycopy(array, 0, array2, 0, n);
        return array2;
    }
    
    private static FileProvider$PathStrategy getPathStrategy(final Context context, final String s) {
        // monitorenter(FileProvider.sCache)
        try {
            FileProvider$PathStrategy pathStrategy;
            if ((pathStrategy = FileProvider.sCache.get(s)) != null) {
                return pathStrategy;
            }
            try {
                pathStrategy = parsePathStrategy(context, s);
                FileProvider.sCache.put(s, pathStrategy);
                return pathStrategy;
            }
            catch (IOException ex) {
                throw new IllegalArgumentException("Failed to parse android.support.FILE_PROVIDER_PATHS meta-data", ex);
            }
            catch (XmlPullParserException ex2) {
                throw new IllegalArgumentException("Failed to parse android.support.FILE_PROVIDER_PATHS meta-data", (Throwable)ex2);
            }
        }
        finally {}
    }
    
    public static Uri getUriForFile(final Context context, final String s, final File file) {
        return getPathStrategy(context, s).getUriForFile(file);
    }
    
    private static int modeToMode(final String s) {
        if ("r".equals(s)) {
            return 268435456;
        }
        if ("w".equals(s) || "wt".equals(s)) {
            return 738197504;
        }
        if ("wa".equals(s)) {
            return 704643072;
        }
        if ("rw".equals(s)) {
            return 939524096;
        }
        if ("rwt".equals(s)) {
            return 1006632960;
        }
        throw new IllegalArgumentException("Invalid mode: " + s);
    }
    
    private static FileProvider$PathStrategy parsePathStrategy(final Context context, String name) {
        final FileProvider$SimplePathStrategy fileProvider$SimplePathStrategy = new FileProvider$SimplePathStrategy(name);
        final XmlResourceParser loadXmlMetaData = context.getPackageManager().resolveContentProvider(name, 128).loadXmlMetaData(context.getPackageManager(), "android.support.FILE_PROVIDER_PATHS");
        if (loadXmlMetaData == null) {
            throw new IllegalArgumentException("Missing android.support.FILE_PROVIDER_PATHS meta-data");
        }
        while (true) {
            final int next = loadXmlMetaData.next();
            if (next == 1) {
                break;
            }
            if (next != 2) {
                continue;
            }
            name = loadXmlMetaData.getName();
            final String attributeValue = loadXmlMetaData.getAttributeValue((String)null, "name");
            final String attributeValue2 = loadXmlMetaData.getAttributeValue((String)null, "path");
            File file = null;
            Label_0109: {
                if ("root-path".equals(name)) {
                    file = FileProvider.DEVICE_ROOT;
                }
                else if ("files-path".equals(name)) {
                    file = context.getFilesDir();
                }
                else if ("cache-path".equals(name)) {
                    file = context.getCacheDir();
                }
                else if ("external-path".equals(name)) {
                    file = Environment.getExternalStorageDirectory();
                }
                else {
                    if ("external-files-path".equals(name)) {
                        final File[] externalFilesDirs = ContextCompat.getExternalFilesDirs(context, null);
                        if (externalFilesDirs.length > 0) {
                            file = externalFilesDirs[0];
                            break Label_0109;
                        }
                    }
                    else if ("external-cache-path".equals(name)) {
                        final File[] externalCacheDirs = ContextCompat.getExternalCacheDirs(context);
                        if (externalCacheDirs.length > 0) {
                            file = externalCacheDirs[0];
                            break Label_0109;
                        }
                    }
                    file = null;
                }
            }
            if (file == null) {
                continue;
            }
            fileProvider$SimplePathStrategy.addRoot(attributeValue, buildPath(file, attributeValue2));
        }
        return fileProvider$SimplePathStrategy;
    }
    
    public void attachInfo(final Context context, final ProviderInfo providerInfo) {
        super.attachInfo(context, providerInfo);
        if (providerInfo.exported) {
            throw new SecurityException("Provider must not be exported");
        }
        if (!providerInfo.grantUriPermissions) {
            throw new SecurityException("Provider must grant uri permissions");
        }
        this.mStrategy = getPathStrategy(context, providerInfo.authority);
    }
    
    public int delete(final Uri uri, final String s, final String[] array) {
        if (this.mStrategy.getFileForUri(uri).delete()) {
            return 1;
        }
        return 0;
    }
    
    public String getType(final Uri uri) {
        final File fileForUri = this.mStrategy.getFileForUri(uri);
        final int lastIndex = fileForUri.getName().lastIndexOf(46);
        if (lastIndex >= 0) {
            final String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileForUri.getName().substring(lastIndex + 1));
            if (mimeTypeFromExtension != null) {
                return mimeTypeFromExtension;
            }
        }
        return "application/octet-stream";
    }
    
    public Uri insert(final Uri uri, final ContentValues contentValues) {
        throw new UnsupportedOperationException("No external inserts");
    }
    
    public boolean onCreate() {
        return true;
    }
    
    public ParcelFileDescriptor openFile(final Uri uri, final String s) {
        return ParcelFileDescriptor.open(this.mStrategy.getFileForUri(uri), modeToMode(s));
    }
    
    public Cursor query(final Uri uri, final String[] array, final String s, String[] array2, String s2) {
        final File fileForUri = this.mStrategy.getFileForUri(uri);
        String[] columns = array;
        if (array == null) {
            columns = FileProvider.COLUMNS;
        }
        array2 = new String[columns.length];
        final Object[] array3 = new Object[columns.length];
        final int length = columns.length;
        int i = 0;
        int n = 0;
        while (i < length) {
            s2 = columns[i];
            if ("_display_name".equals(s2)) {
                array2[n] = "_display_name";
                final int n2 = n + 1;
                array3[n] = fileForUri.getName();
                n = n2;
            }
            else if ("_size".equals(s2)) {
                array2[n] = "_size";
                final int n3 = n + 1;
                array3[n] = fileForUri.length();
                n = n3;
            }
            ++i;
        }
        final String[] copy = copyOf(array2, n);
        final Object[] copy2 = copyOf(array3, n);
        final MatrixCursor matrixCursor = new MatrixCursor(copy, 1);
        matrixCursor.addRow(copy2);
        return (Cursor)matrixCursor;
    }
    
    public int update(final Uri uri, final ContentValues contentValues, final String s, final String[] array) {
        throw new UnsupportedOperationException("No external updates");
    }
}
