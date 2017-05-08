// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.upstream;

import com.google.android.exoplayer.util.Util;
import com.google.android.exoplayer.util.Predicate;
import com.google.android.exoplayer.util.Assertions;
import android.content.Context;

public final class DefaultUriDataSource implements UriDataSource
{
    private final UriDataSource assetDataSource;
    private final UriDataSource contentDataSource;
    private UriDataSource dataSource;
    private final UriDataSource fileDataSource;
    private final UriDataSource httpDataSource;
    
    public DefaultUriDataSource(final Context context, final TransferListener transferListener, final UriDataSource uriDataSource) {
        this.httpDataSource = Assertions.checkNotNull(uriDataSource);
        this.fileDataSource = new FileDataSource(transferListener);
        this.assetDataSource = new AssetDataSource(context, transferListener);
        this.contentDataSource = new ContentDataSource(context, transferListener);
    }
    
    public DefaultUriDataSource(final Context context, final TransferListener transferListener, final String s) {
        this(context, transferListener, s, false);
    }
    
    public DefaultUriDataSource(final Context context, final TransferListener transferListener, final String s, final boolean b) {
        this(context, transferListener, new DefaultHttpDataSource(s, null, transferListener, 8000, 8000, b));
    }
    
    public DefaultUriDataSource(final Context context, final String s) {
        this(context, null, s, false);
    }
    
    @Override
    public void close() {
        if (this.dataSource == null) {
            return;
        }
        try {
            this.dataSource.close();
        }
        finally {
            this.dataSource = null;
        }
    }
    
    @Override
    public String getUri() {
        if (this.dataSource == null) {
            return null;
        }
        return this.dataSource.getUri();
    }
    
    @Override
    public long open(final DataSpec dataSpec) {
        Assertions.checkState(this.dataSource == null);
        final String scheme = dataSpec.uri.getScheme();
        if (Util.isLocalFileUri(dataSpec.uri)) {
            if (dataSpec.uri.getPath().startsWith("/android_asset/")) {
                this.dataSource = this.assetDataSource;
            }
            else {
                this.dataSource = this.fileDataSource;
            }
        }
        else if ("asset".equals(scheme)) {
            this.dataSource = this.assetDataSource;
        }
        else if ("content".equals(scheme)) {
            this.dataSource = this.contentDataSource;
        }
        else {
            this.dataSource = this.httpDataSource;
        }
        return this.dataSource.open(dataSpec);
    }
    
    @Override
    public int read(final byte[] array, final int n, final int n2) {
        return this.dataSource.read(array, n, n2);
    }
}
