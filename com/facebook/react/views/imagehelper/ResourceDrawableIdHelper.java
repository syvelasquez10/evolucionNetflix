// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.imagehelper;

import android.net.Uri$Builder;
import android.net.Uri;
import android.graphics.drawable.Drawable;
import android.content.Context;
import java.util.HashMap;
import java.util.Map;

public class ResourceDrawableIdHelper
{
    private static ResourceDrawableIdHelper sResourceDrawableIdHelper;
    private Map<String, Integer> mResourceDrawableIdMap;
    
    private ResourceDrawableIdHelper() {
        this.mResourceDrawableIdMap = new HashMap<String, Integer>();
    }
    
    public static ResourceDrawableIdHelper getInstance() {
        if (ResourceDrawableIdHelper.sResourceDrawableIdHelper == null) {
            ResourceDrawableIdHelper.sResourceDrawableIdHelper = new ResourceDrawableIdHelper();
        }
        return ResourceDrawableIdHelper.sResourceDrawableIdHelper;
    }
    
    public Drawable getResourceDrawable(final Context context, final String s) {
        final int resourceDrawableId = this.getResourceDrawableId(context, s);
        if (resourceDrawableId > 0) {
            return context.getResources().getDrawable(resourceDrawableId);
        }
        return null;
    }
    
    public int getResourceDrawableId(final Context context, String replace) {
        if (replace == null || replace.isEmpty()) {
            return 0;
        }
        replace = replace.toLowerCase().replace("-", "_");
        if (this.mResourceDrawableIdMap.containsKey(replace)) {
            return this.mResourceDrawableIdMap.get(replace);
        }
        final int identifier = context.getResources().getIdentifier(replace, "drawable", context.getPackageName());
        this.mResourceDrawableIdMap.put(replace, identifier);
        return identifier;
    }
    
    public Uri getResourceDrawableUri(final Context context, final String s) {
        final int resourceDrawableId = this.getResourceDrawableId(context, s);
        if (resourceDrawableId > 0) {
            return new Uri$Builder().scheme("res").path(String.valueOf(resourceDrawableId)).build();
        }
        return Uri.EMPTY;
    }
}
