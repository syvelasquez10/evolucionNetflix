// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.camera;

import android.database.Cursor;
import java.util.Collections;
import java.util.Map;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableMap;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import android.text.TextUtils;
import android.graphics.Bitmap$CompressFormat;
import java.io.IOException;
import android.media.ExifInterface;
import com.facebook.common.logging.FLog;
import android.net.Uri;
import android.graphics.Bitmap;
import java.io.File;
import android.content.Context;
import android.os.AsyncTask;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactApplicationContext;
import java.util.Arrays;
import java.util.List;
import android.annotation.SuppressLint;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

public class ImageEditingManager extends ReactContextBaseJavaModule
{
    private static final int COMPRESS_QUALITY = 90;
    @SuppressLint({ "InlinedApi" })
    private static final String[] EXIF_ATTRIBUTES;
    private static final List<String> LOCAL_URI_PREFIXES;
    protected static final String NAME = "ImageEditingManager";
    private static final String TEMP_FILE_PREFIX = "ReactNative_cropped_image_";
    
    static {
        LOCAL_URI_PREFIXES = Arrays.asList("file://", "content://");
        EXIF_ATTRIBUTES = new String[] { "FNumber", "DateTime", "DateTimeDigitized", "ExposureTime", "Flash", "FocalLength", "GPSAltitude", "GPSAltitudeRef", "GPSDateStamp", "GPSLatitude", "GPSLatitudeRef", "GPSLongitude", "GPSLongitudeRef", "GPSProcessingMethod", "GPSTimeStamp", "ImageLength", "ImageWidth", "ISOSpeedRatings", "Make", "Model", "Orientation", "SubSecTime", "SubSecTimeDigitized", "SubSecTimeOriginal", "WhiteBalance" };
    }
    
    public ImageEditingManager(final ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        new ImageEditingManager$CleanTask(this.getReactApplicationContext(), null).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
    }
    
    private static void copyExif(final Context context, final Uri uri, final File file) {
        final File fileFromUri = getFileFromUri(context, uri);
        if (fileFromUri == null) {
            FLog.w("React", "Couldn't get real path for uri: " + uri);
            return;
        }
        final ExifInterface exifInterface = new ExifInterface(fileFromUri.getAbsolutePath());
        final ExifInterface exifInterface2 = new ExifInterface(file.getAbsolutePath());
        final String[] exif_ATTRIBUTES = ImageEditingManager.EXIF_ATTRIBUTES;
        for (int length = exif_ATTRIBUTES.length, i = 0; i < length; ++i) {
            final String s = exif_ATTRIBUTES[i];
            final String attribute = exifInterface.getAttribute(s);
            if (attribute != null) {
                exifInterface2.setAttribute(s, attribute);
            }
        }
        exifInterface2.saveAttributes();
    }
    
    private static File createTempFile(final Context context, final String s) {
        final File externalCacheDir = context.getExternalCacheDir();
        final File cacheDir = context.getCacheDir();
        if (externalCacheDir == null && cacheDir == null) {
            throw new IOException("No cache directory available");
        }
        File file;
        if (externalCacheDir == null) {
            file = cacheDir;
        }
        else {
            file = externalCacheDir;
            if (cacheDir != null) {
                file = externalCacheDir;
                if (externalCacheDir.getFreeSpace() <= cacheDir.getFreeSpace()) {
                    file = cacheDir;
                }
            }
        }
        return File.createTempFile("ReactNative_cropped_image_", getFileExtensionForType(s), file);
    }
    
    private static Bitmap$CompressFormat getCompressFormatForType(final String s) {
        if ("image/png".equals(s)) {
            return Bitmap$CompressFormat.PNG;
        }
        if ("image/webp".equals(s)) {
            return Bitmap$CompressFormat.WEBP;
        }
        return Bitmap$CompressFormat.JPEG;
    }
    
    private static int getDecodeSampleSize(int n, int n2, final int n3, final int n4) {
        int n5 = 1;
        final int n6 = 1;
        if (n2 > n3 || n > n4) {
            n2 /= 2;
            final int n7 = n / 2;
            n = n6;
            while (true) {
                n5 = n;
                if (n7 / n < n3) {
                    break;
                }
                n5 = n;
                if (n2 / n < n4) {
                    break;
                }
                n *= 2;
            }
        }
        return n5;
    }
    
    private static String getFileExtensionForType(final String s) {
        if ("image/png".equals(s)) {
            return ".png";
        }
        if ("image/webp".equals(s)) {
            return ".webp";
        }
        return ".jpg";
    }
    
    private static File getFileFromUri(Context query, final Uri uri) {
        final File file = null;
        File file2;
        if (uri.getScheme().equals("file")) {
            file2 = new File(uri.getPath());
        }
        else {
            file2 = file;
            if (uri.getScheme().equals("content")) {
                query = (Context)query.getContentResolver().query(uri, new String[] { "_data" }, (String)null, (String[])null, (String)null);
                file2 = file;
                if (query != null) {
                    try {
                        if (((Cursor)query).moveToFirst()) {
                            final String string = ((Cursor)query).getString(0);
                            if (!TextUtils.isEmpty((CharSequence)string)) {
                                return new File(string);
                            }
                        }
                        return null;
                    }
                    finally {
                        ((Cursor)query).close();
                    }
                }
            }
        }
        return file2;
    }
    
    private static boolean isLocalUri(final String s) {
        final Iterator<String> iterator = ImageEditingManager.LOCAL_URI_PREFIXES.iterator();
        while (iterator.hasNext()) {
            if (s.startsWith(iterator.next())) {
                return true;
            }
        }
        return false;
    }
    
    private static void writeCompressedBitmapToFile(final Bitmap bitmap, final String s, File file) {
        file = (File)new FileOutputStream(file);
        try {
            bitmap.compress(getCompressFormatForType(s), 90, (OutputStream)file);
        }
        finally {
            if (file != null) {
                ((OutputStream)file).close();
            }
        }
    }
    
    @ReactMethod
    public void cropImage(final String s, ReadableMap map, final Callback callback, final Callback callback2) {
        ReadableMap map2;
        if (map.hasKey("offset")) {
            map2 = map.getMap("offset");
        }
        else {
            map2 = null;
        }
        ReadableMap map3;
        if (map.hasKey("size")) {
            map3 = map.getMap("size");
        }
        else {
            map3 = null;
        }
        if (map2 == null || map3 == null || !map2.hasKey("x") || !map2.hasKey("y") || !map3.hasKey("width") || !map3.hasKey("height")) {
            throw new JSApplicationIllegalArgumentException("Please specify offset and size");
        }
        if (s == null || s.isEmpty()) {
            throw new JSApplicationIllegalArgumentException("Please specify a URI");
        }
        final ImageEditingManager$CropTask imageEditingManager$CropTask = new ImageEditingManager$CropTask(this.getReactApplicationContext(), s, (int)map2.getDouble("x"), (int)map2.getDouble("y"), (int)map3.getDouble("width"), (int)map3.getDouble("height"), callback, callback2, null);
        if (map.hasKey("displaySize")) {
            map = map.getMap("displaySize");
            imageEditingManager$CropTask.setTargetSize(map.getInt("width"), map.getInt("height"));
        }
        imageEditingManager$CropTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
    }
    
    @Override
    public Map<String, Object> getConstants() {
        return Collections.emptyMap();
    }
    
    @Override
    public String getName() {
        return "ImageEditingManager";
    }
    
    @Override
    public void onCatalystInstanceDestroy() {
        new ImageEditingManager$CleanTask(this.getReactApplicationContext(), null).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
    }
}
