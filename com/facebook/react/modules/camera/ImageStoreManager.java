// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.camera;

import com.facebook.react.bridge.ReactMethod;
import android.os.AsyncTask;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.Callback;
import java.io.IOException;
import java.io.Closeable;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

public class ImageStoreManager extends ReactContextBaseJavaModule
{
    private static final int BUFFER_SIZE = 8192;
    
    public ImageStoreManager(final ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }
    
    private static void closeQuietly(final Closeable closeable) {
        try {
            closeable.close();
        }
        catch (IOException ex) {}
    }
    
    @ReactMethod
    public void getBase64ForTag(final String s, final Callback callback, final Callback callback2) {
        new ImageStoreManager$GetBase64Task(this, this.getReactApplicationContext(), s, callback, callback2, null).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
    }
    
    @Override
    public String getName() {
        return "ImageStoreManager";
    }
}
