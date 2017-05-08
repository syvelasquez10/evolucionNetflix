// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.utils;

import java.io.File;
import com.netflix.mediaclient.service.offline.download.DownloadableType;

public class OfflinePathUtils
{
    public static String getDirectoryPathForViewable(final String s, final String s2) {
        return s + "/" + s2;
    }
    
    public static File getFileObjectForDownloadable(final String s, final String s2, final DownloadableType downloadableType) {
        return new File(getFilePathForDownloadable(s, s2, downloadableType));
    }
    
    public static String getFilePathForDownloadable(final String s, final String s2, final DownloadableType downloadableType) {
        return s + "/" + s2 + "." + downloadableType.getFileExtension();
    }
    
    public static String getFilePathForMetaRegistry(final File file) {
        return file.getAbsolutePath() + "/of_meta_registry.json";
    }
    
    public static String getFilePathForRegistry(final String s) {
        return s + "/registry.json";
    }
    
    public static String getFilePathOfflineManifest(final String s, final String s2) {
        return s + "/" + s2 + ".manifest";
    }
}
