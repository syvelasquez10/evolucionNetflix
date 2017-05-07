// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util.gfx;

import android.graphics.Bitmap;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.servicemgr.IClientLogging;

public interface ImageLoader
{
    public static final int ONFAIL_DRAWABLE_RESOURCE = 2130837567;
    public static final int PLACEHOLDER_DRAWABLE_RESOURCE = 2130837567;
    public static final int PRIORITY_LOW = 0;
    public static final int PRIORITY_NORMAL = 1;
    
    void cancelAllRequests();
    
    void getImg(final String p0, final IClientLogging.AssetType p1, final int p2, final int p3, final ImageLoaderListener p4);
    
    void refreshImgIfNecessary(final AdvancedImageView p0, final IClientLogging.AssetType p1);
    
    void showImg(final AdvancedImageView p0, final String p1, final IClientLogging.AssetType p2, final String p3, final boolean p4, final boolean p5);
    
    void showImg(final AdvancedImageView p0, final String p1, final IClientLogging.AssetType p2, final String p3, final boolean p4, final boolean p5, final int p6);
    
    public interface ImageLoaderListener
    {
        void onErrorResponse(final String p0);
        
        void onResponse(final Bitmap p0, final String p1);
    }
}
