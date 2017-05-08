// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.apm.model;

import org.json.JSONObject;
import com.netflix.mediaclient.util.DeviceUtils;
import com.facebook.device.yearclass.YearClass;
import android.content.Context;

public class UIBrowseStartupSessionCustomData
{
    public static final String HEAP_SIZE = "heapSize";
    public static final String SCREEN_CATEGORY = "screenCat";
    public static final String YEAR_CLASS = "yearClass";
    private final int heapSizeMb;
    private final String screenCategory;
    private final int yearClass;
    
    private UIBrowseStartupSessionCustomData(final int heapSizeMb, final int yearClass, final String screenCategory) {
        this.heapSizeMb = heapSizeMb;
        this.yearClass = yearClass;
        this.screenCategory = screenCategory;
    }
    
    public static UIBrowseStartupSessionCustomData create(final Context context) {
        return new UIBrowseStartupSessionCustomData((int)(Runtime.getRuntime().maxMemory() / 1048576L), YearClass.get(context), DeviceUtils.getScreenSizeCategoryString(context));
    }
    
    public JSONObject toJson() {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("heapSize", this.heapSizeMb);
        jsonObject.put("yearClass", this.yearClass);
        jsonObject.put("screenCat", (Object)this.screenCategory);
        return jsonObject;
    }
}
