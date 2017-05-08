// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.network;

import com.facebook.common.logging.FLog;
import android.net.Uri;
import android.content.Context;
import okio.ByteString;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;
import java.io.ByteArrayOutputStream;
import okhttp3.RequestBody;
import java.io.InputStream;
import okhttp3.MediaType;

class RequestBodyUtil
{
    public static RequestBody create(final MediaType mediaType, final InputStream inputStream) {
        return new RequestBodyUtil$1(mediaType, inputStream);
    }
    
    public static RequestBody createGzip(final MediaType mediaType, final String s) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            final GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gzipOutputStream.write(s.getBytes());
            gzipOutputStream.close();
            return RequestBody.create(mediaType, byteArrayOutputStream.toByteArray());
        }
        catch (IOException ex) {
            return null;
        }
    }
    
    public static ProgressRequestBody createProgressRequest(final RequestBody requestBody, final ProgressListener progressListener) {
        return new ProgressRequestBody(requestBody, progressListener);
    }
    
    public static RequestBody getEmptyBody(final String s) {
        RequestBody create = null;
        if (s.equals("POST") || s.equals("PUT") || s.equals("PATCH")) {
            create = RequestBody.create((MediaType)null, ByteString.EMPTY);
        }
        return create;
    }
    
    public static InputStream getFileInputStream(final Context context, final String s) {
        try {
            return context.getContentResolver().openInputStream(Uri.parse(s));
        }
        catch (Exception ex) {
            FLog.e("React", "Could not retrieve file for contentUri " + s, ex);
            return null;
        }
    }
    
    public static boolean isGzipEncoding(final String s) {
        return "gzip".equalsIgnoreCase(s);
    }
}
