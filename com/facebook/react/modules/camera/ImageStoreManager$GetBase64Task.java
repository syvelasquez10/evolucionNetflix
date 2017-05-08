// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.camera;

import java.io.InputStream;
import com.facebook.react.bridge.ReactMethod;
import android.os.AsyncTask;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import java.io.FileNotFoundException;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import android.util.Base64OutputStream;
import java.io.ByteArrayOutputStream;
import android.net.Uri;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.GuardedAsyncTask;

class ImageStoreManager$GetBase64Task extends GuardedAsyncTask<Void, Void>
{
    private final Callback mError;
    private final Callback mSuccess;
    private final String mUri;
    final /* synthetic */ ImageStoreManager this$0;
    
    private ImageStoreManager$GetBase64Task(final ImageStoreManager this$0, final ReactContext reactContext, final String mUri, final Callback mSuccess, final Callback mError) {
        this.this$0 = this$0;
        super(reactContext);
        this.mUri = mUri;
        this.mSuccess = mSuccess;
        this.mError = mError;
    }
    
    @Override
    protected void doInBackgroundGuarded(Void... openInputStream) {
        try {
            openInputStream = (Void[])(Object)this.this$0.getReactApplicationContext().getContentResolver().openInputStream(Uri.parse(this.mUri));
            final Base64OutputStream base64OutputStream = new Base64OutputStream((OutputStream)new ByteArrayOutputStream(), 0);
            final byte[] array = new byte[8192];
            Label_0103: {
                try {
                    while (true) {
                        final int read = ((InputStream)(Object)openInputStream).read(array);
                        if (read <= -1) {
                            break Label_0103;
                        }
                        base64OutputStream.write(array, 0, read);
                    }
                }
                catch (IOException ex) {
                    this.mError.invoke(ex.getMessage());
                    return;
                    final ByteArrayOutputStream byteArrayOutputStream;
                    this.mSuccess.invoke(byteArrayOutputStream.toString());
                }
                finally {
                    closeQuietly((Closeable)(Object)openInputStream);
                    closeQuietly((Closeable)base64OutputStream);
                }
            }
        }
        catch (FileNotFoundException ex2) {}
    }
}
