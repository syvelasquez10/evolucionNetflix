// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import java.io.InputStream;
import java.io.ByteArrayInputStream;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import android.net.Uri;
import android.util.Base64;
import com.facebook.common.internal.Preconditions;
import java.util.concurrent.Executor;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.imagepipeline.memory.PooledByteBufferFactory;

public class DataFetchProducer extends LocalFetchProducer
{
    public DataFetchProducer(final PooledByteBufferFactory pooledByteBufferFactory, final boolean b) {
        super(CallerThreadExecutor.getInstance(), pooledByteBufferFactory, b);
    }
    
    static byte[] getData(final String s) {
        Preconditions.checkArgument(s.substring(0, 5).equals("data:"));
        final int index = s.indexOf(44);
        final String substring = s.substring(index + 1, s.length());
        if (isBase64(s.substring(0, index))) {
            return Base64.decode(substring, 0);
        }
        return Uri.decode(substring).getBytes();
    }
    
    static boolean isBase64(final String s) {
        if (!s.contains(";")) {
            return false;
        }
        final String[] split = s.split(";");
        return split[split.length - 1].equals("base64");
    }
    
    @Override
    protected EncodedImage getEncodedImage(final ImageRequest imageRequest) {
        final byte[] data = getData(imageRequest.getSourceUri().toString());
        return this.getByteBufferBackedEncodedImage(new ByteArrayInputStream(data), data.length);
    }
    
    @Override
    protected String getProducerName() {
        return "DataFetchProducer";
    }
}
