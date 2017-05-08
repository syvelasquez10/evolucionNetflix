// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import android.database.Cursor;
import java.io.IOException;
import android.provider.ContactsContract$Contacts;
import com.facebook.common.util.UriUtil;
import com.facebook.imagepipeline.request.ImageRequest;
import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import com.facebook.imagepipeline.image.EncodedImage;
import android.net.Uri;
import com.facebook.imagepipeline.memory.PooledByteBufferFactory;
import java.util.concurrent.Executor;
import android.content.ContentResolver;

public class LocalContentUriFetchProducer extends LocalFetchProducer
{
    private static final String[] PROJECTION;
    private final ContentResolver mContentResolver;
    
    static {
        PROJECTION = new String[] { "_id", "_data" };
    }
    
    public LocalContentUriFetchProducer(final Executor executor, final PooledByteBufferFactory pooledByteBufferFactory, final ContentResolver mContentResolver, final boolean b) {
        super(executor, pooledByteBufferFactory, b);
        this.mContentResolver = mContentResolver;
    }
    
    private EncodedImage getCameraImage(Uri query) {
        query = (Uri)this.mContentResolver.query(query, LocalContentUriFetchProducer.PROJECTION, (String)null, (String[])null, (String)null);
        if (query == null) {
            return null;
        }
        try {
            if (((Cursor)query).getCount() == 0) {
                return null;
            }
            ((Cursor)query).moveToFirst();
            final String string = ((Cursor)query).getString(((Cursor)query).getColumnIndex("_data"));
            if (string != null) {
                return this.getEncodedImage(new FileInputStream(string), getLength(string));
            }
            return null;
        }
        finally {
            ((Cursor)query).close();
        }
    }
    
    private static int getLength(final String s) {
        if (s == null) {
            return -1;
        }
        return (int)new File(s).length();
    }
    
    @Override
    protected EncodedImage getEncodedImage(final ImageRequest imageRequest) {
        final Uri sourceUri = imageRequest.getSourceUri();
        EncodedImage encodedImage;
        if (UriUtil.isLocalContactUri(sourceUri)) {
            InputStream inputStream;
            if (sourceUri.toString().endsWith("/photo")) {
                inputStream = this.mContentResolver.openInputStream(sourceUri);
            }
            else if ((inputStream = ContactsContract$Contacts.openContactPhotoInputStream(this.mContentResolver, sourceUri)) == null) {
                throw new IOException("Contact photo does not exist: " + sourceUri);
            }
            encodedImage = this.getEncodedImage(inputStream, -1);
        }
        else if (!UriUtil.isLocalCameraUri(sourceUri) || (encodedImage = this.getCameraImage(sourceUri)) == null) {
            return this.getEncodedImage(this.mContentResolver.openInputStream(sourceUri), -1);
        }
        return encodedImage;
    }
    
    @Override
    protected String getProducerName() {
        return "LocalContentUriFetchProducer";
    }
}
