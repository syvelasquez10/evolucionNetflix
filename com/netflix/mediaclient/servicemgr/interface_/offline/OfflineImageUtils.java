// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_.offline;

import java.io.File;
import com.netflix.mediaclient.service.resfetcher.ResourceFetcherCallback;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.FileUtils;
import android.content.Context;
import com.netflix.mediaclient.util.gfx.ImageLoader$ImageLoaderListener;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.service.NetflixService;

public class OfflineImageUtils
{
    private static final String TAG = "offlineImageUtils";
    
    private static void cacheImageLocally(final NetflixService netflixService, final String s, final String s2) {
        netflixService.getImageLoader().getImg(s, IClientLogging$AssetType.boxArt, 0, 0, (ImageLoader$ImageLoaderListener)new OfflineImageUtils$1(netflixService, s, s2));
    }
    
    public static void cacheProfileImage(final NetflixService netflixService, final String s, final String s2) {
        FileUtils.createDirectoryIfRequired(getLocalDirectoryForProfile((Context)netflixService));
        cacheImageLocally(netflixService, s, getLocalFileForProfileImage((Context)netflixService, s2));
    }
    
    public static void cacheVideoDetailsImage(final NetflixService netflixService, final String s, final String s2) {
        FileUtils.createDirectoryIfRequired(getLocalDirectoryForVideoDetails((Context)netflixService));
        cacheImageLocally(netflixService, s, getLocalFileForVideoDetailsImage((Context)netflixService, s2));
    }
    
    private static void copyResourceToFilesDir(final NetflixService netflixService, final String s, final String s2) {
        if (Log.isLoggable()) {
            Log.i("offlineImageUtils", "copyResourceToFilesDir url=" + s);
        }
        netflixService.getResourceFetcher().fetchResource(s, IClientLogging$AssetType.boxArt, new OfflineImageUtils$2(s2));
    }
    
    public static void deleteAllOfflineImages(final Context context) {
        FileUtils.deleteRecursive(new File(context.getFilesDir() + "/img/of/"));
    }
    
    public static void deleteVideoDetailsImage(final Context context, final String s) {
        final File file = new File(getLocalFileForVideoDetailsImage(context, s));
        if (file.exists()) {
            Log.i("offlineImageUtils", "deleteVideoDetailsImage result=%b", file.delete());
        }
    }
    
    private static String getLocalDirectoryForProfile(final Context context) {
        return context.getFilesDir() + "/img/of/profiles/";
    }
    
    private static String getLocalDirectoryForVideoDetails(final Context context) {
        return context.getFilesDir() + "/img/of/videos/";
    }
    
    public static String getLocalFileForProfileImage(final Context context, final String s) {
        return getLocalDirectoryForProfile(context) + s + ".img";
    }
    
    public static String getLocalFileForVideoDetailsImage(final Context context, final String s) {
        return getLocalDirectoryForVideoDetails(context) + s + ".img";
    }
}
