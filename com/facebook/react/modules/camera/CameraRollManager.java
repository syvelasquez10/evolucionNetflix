// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.camera;

import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import android.os.AsyncTask;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReadableMap;
import android.content.res.AssetFileDescriptor;
import java.io.IOException;
import com.facebook.common.logging.FLog;
import android.graphics.Rect;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory$Options;
import android.net.Uri;
import android.provider.MediaStore$Images$Media;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableMap;
import android.database.Cursor;
import android.content.ContentResolver;
import com.facebook.react.bridge.ReactApplicationContext;
import android.os.Build$VERSION;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

public class CameraRollManager extends ReactContextBaseJavaModule
{
    private static final String ERROR_UNABLE_TO_LOAD = "E_UNABLE_TO_LOAD";
    private static final String ERROR_UNABLE_TO_LOAD_PERMISSION = "E_UNABLE_TO_LOAD_PERMISSION";
    private static final String ERROR_UNABLE_TO_SAVE = "E_UNABLE_TO_SAVE";
    public static final boolean IS_JELLY_BEAN_OR_LATER;
    private static final String[] PROJECTION;
    private static final String SELECTION_BUCKET = "bucket_display_name = ?";
    private static final String SELECTION_DATE_TAKEN = "datetaken < ?";
    
    static {
        IS_JELLY_BEAN_OR_LATER = (Build$VERSION.SDK_INT >= 16);
        if (CameraRollManager.IS_JELLY_BEAN_OR_LATER) {
            PROJECTION = new String[] { "_id", "mime_type", "bucket_display_name", "datetaken", "width", "height", "longitude", "latitude" };
            return;
        }
        PROJECTION = new String[] { "_id", "mime_type", "bucket_display_name", "datetaken", "longitude", "latitude" };
    }
    
    public CameraRollManager(final ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }
    
    private static void putBasicNodeInfo(final Cursor cursor, final WritableMap writableMap, final int n, final int n2, final int n3) {
        writableMap.putString("type", cursor.getString(n));
        writableMap.putString("group_name", cursor.getString(n2));
        writableMap.putDouble("timestamp", cursor.getLong(n3) / 1000.0);
    }
    
    private static void putEdges(final ContentResolver contentResolver, final Cursor cursor, final WritableMap writableMap, final int n) {
        final WritableNativeArray writableNativeArray = new WritableNativeArray();
        cursor.moveToFirst();
        final int columnIndex = cursor.getColumnIndex("_id");
        final int columnIndex2 = cursor.getColumnIndex("mime_type");
        final int columnIndex3 = cursor.getColumnIndex("bucket_display_name");
        final int columnIndex4 = cursor.getColumnIndex("datetaken");
        int columnIndex5;
        if (CameraRollManager.IS_JELLY_BEAN_OR_LATER) {
            columnIndex5 = cursor.getColumnIndex("width");
        }
        else {
            columnIndex5 = -1;
        }
        int columnIndex6;
        if (CameraRollManager.IS_JELLY_BEAN_OR_LATER) {
            columnIndex6 = cursor.getColumnIndex("height");
        }
        else {
            columnIndex6 = -1;
        }
        final int columnIndex7 = cursor.getColumnIndex("longitude");
        final int columnIndex8 = cursor.getColumnIndex("latitude");
        for (int n2 = 0; n2 < n && !cursor.isAfterLast(); ++n2) {
            final WritableNativeMap writableNativeMap = new WritableNativeMap();
            final WritableNativeMap writableNativeMap2 = new WritableNativeMap();
            if (putImageInfo(contentResolver, cursor, writableNativeMap2, columnIndex, columnIndex5, columnIndex6)) {
                putBasicNodeInfo(cursor, writableNativeMap2, columnIndex2, columnIndex3, columnIndex4);
                putLocationInfo(cursor, writableNativeMap2, columnIndex7, columnIndex8);
                writableNativeMap.putMap("node", writableNativeMap2);
                writableNativeArray.pushMap(writableNativeMap);
            }
            else {
                --n2;
            }
            cursor.moveToNext();
        }
        writableMap.putArray("edges", writableNativeArray);
    }
    
    private static boolean putImageInfo(final ContentResolver contentResolver, final Cursor cursor, final WritableMap writableMap, int outHeight, final int n, final int n2) {
        float n3 = -1.0f;
        final WritableNativeMap writableNativeMap = new WritableNativeMap();
        final Uri withAppendedPath = Uri.withAppendedPath(MediaStore$Images$Media.EXTERNAL_CONTENT_URI, cursor.getString(outHeight));
        writableNativeMap.putString("uri", withAppendedPath.toString());
        while (true) {
            Label_0208: {
                if (!CameraRollManager.IS_JELLY_BEAN_OR_LATER) {
                    break Label_0208;
                }
                float n4 = cursor.getInt(n);
                n3 = cursor.getInt(n2);
                Label_0140: {
                    if (n4 > 0.0f) {
                        final float n5 = n3;
                        if (n3 > 0.0f) {
                            break Label_0140;
                        }
                    }
                    try {
                        final AssetFileDescriptor openAssetFileDescriptor = contentResolver.openAssetFileDescriptor(withAppendedPath, "r");
                        final BitmapFactory$Options bitmapFactory$Options = new BitmapFactory$Options();
                        bitmapFactory$Options.inJustDecodeBounds = true;
                        BitmapFactory.decodeFileDescriptor(openAssetFileDescriptor.getFileDescriptor(), (Rect)null, bitmapFactory$Options);
                        openAssetFileDescriptor.close();
                        n4 = bitmapFactory$Options.outWidth;
                        outHeight = bitmapFactory$Options.outHeight;
                        final float n5 = outHeight;
                        writableNativeMap.putDouble("width", n4);
                        writableNativeMap.putDouble("height", n5);
                        writableMap.putMap("image", writableNativeMap);
                        return true;
                    }
                    catch (IOException ex) {
                        FLog.e("React", "Could not get width/height for " + withAppendedPath.toString(), ex);
                        return false;
                    }
                }
            }
            float n4 = -1.0f;
            continue;
        }
    }
    
    private static void putLocationInfo(final Cursor cursor, final WritableMap writableMap, final int n, final int n2) {
        final double double1 = cursor.getDouble(n);
        final double double2 = cursor.getDouble(n2);
        if (double1 > 0.0 || double2 > 0.0) {
            final WritableNativeMap writableNativeMap = new WritableNativeMap();
            writableNativeMap.putDouble("longitude", double1);
            writableNativeMap.putDouble("latitude", double2);
            writableMap.putMap("location", writableNativeMap);
        }
    }
    
    private static void putPageInfo(final Cursor cursor, final WritableMap writableMap, final int n) {
        final WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putBoolean("has_next_page", n < cursor.getCount());
        if (n < cursor.getCount()) {
            cursor.moveToPosition(n - 1);
            writableNativeMap.putString("end_cursor", cursor.getString(cursor.getColumnIndex("datetaken")));
        }
        writableMap.putMap("page_info", writableNativeMap);
    }
    
    @Override
    public String getName() {
        return "RKCameraRollManager";
    }
    
    @ReactMethod
    public void getPhotos(final ReadableMap readableMap, final Promise promise) {
        final int int1 = readableMap.getInt("first");
        String string;
        if (readableMap.hasKey("after")) {
            string = readableMap.getString("after");
        }
        else {
            string = null;
        }
        String string2;
        if (readableMap.hasKey("groupName")) {
            string2 = readableMap.getString("groupName");
        }
        else {
            string2 = null;
        }
        ReadableArray array;
        if (readableMap.hasKey("mimeTypes")) {
            array = readableMap.getArray("mimeTypes");
        }
        else {
            array = null;
        }
        if (readableMap.hasKey("groupTypes")) {
            throw new JSApplicationIllegalArgumentException("groupTypes is not supported on Android");
        }
        new CameraRollManager$GetPhotosTask(this.getReactApplicationContext(), int1, string, string2, array, promise, null).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
    }
    
    @ReactMethod
    public void saveToCameraRoll(final String s, final String s2, final Promise promise) {
        CameraRollManager$MediaType cameraRollManager$MediaType;
        if (s2.equals("video")) {
            cameraRollManager$MediaType = CameraRollManager$MediaType.VIDEO;
        }
        else {
            cameraRollManager$MediaType = CameraRollManager$MediaType.PHOTO;
        }
        new CameraRollManager$SaveToCameraRoll(this.getReactApplicationContext(), Uri.parse(s), cameraRollManager$MediaType, promise).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
    }
}
