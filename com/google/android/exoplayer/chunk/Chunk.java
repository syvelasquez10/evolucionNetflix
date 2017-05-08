// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.chunk;

import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.upstream.DataSpec;
import com.google.android.exoplayer.upstream.DataSource;
import com.google.android.exoplayer.upstream.Loader$Loadable;

public abstract class Chunk implements Loader$Loadable
{
    protected final DataSource dataSource;
    public final DataSpec dataSpec;
    public final Format format;
    public final int parentId;
    public final int trigger;
    public final int type;
    
    public Chunk(final DataSource dataSource, final DataSpec dataSpec, final int type, final int trigger, final Format format, final int parentId) {
        this.dataSource = Assertions.checkNotNull(dataSource);
        this.dataSpec = Assertions.checkNotNull(dataSpec);
        this.type = type;
        this.trigger = trigger;
        this.format = format;
        this.parentId = parentId;
    }
    
    public abstract long bytesLoaded();
}
