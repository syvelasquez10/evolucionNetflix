// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import java.io.File;

public interface IPlayerFileCache
{
    public static final String MASTER_INDEX_NAME = "master.idx";
    public static final String SEGMENT_INDEX_NAME = "segment.idx";
    public static final String TEXT_SUBTITLE_METADATA = "manifest_ttml.xml";
    
    File getFile(final String p0, final String p1);
    
    String getSubtitleCache(final String p0, final String p1);
    
    boolean moveFile(final String p0, final String p1, final String p2);
    
    String saveFile(final String p0, final String p1, final byte[] p2);
}
