// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import org.json.JSONException;
import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.media.Subtitle;

public final class MdxSubtitle extends Subtitle
{
    protected static final String ATTR_LABEL = "label";
    public static final int IMPL_VALUE = 2;
    private final boolean mSelected;
    
    protected MdxSubtitle(final JSONObject jsonObject, final int nccpOrderNumber) throws JSONException {
        this.canDeviceRender = true;
        this.id = JsonUtils.getString(jsonObject, "id", null);
        this.languageDescription = JsonUtils.getString(jsonObject, "label", "English");
        this.nccpOrderNumber = nccpOrderNumber;
        this.mSelected = JsonUtils.getBoolean(jsonObject, "selected", false);
        this.trackType = 0;
    }
    
    public static final MdxSubtitle newInstance(final JSONObject jsonObject, final int n) throws JSONException {
        return new MdxSubtitle(jsonObject, n);
    }
    
    public boolean isSelected() {
        return this.mSelected;
    }
    
    @Override
    public JSONObject toJson() throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("impl", 2);
        jsonObject.put("id", (Object)this.id);
        jsonObject.put("label", (Object)this.languageDescription);
        jsonObject.put("order", this.nccpOrderNumber);
        jsonObject.put("selected", this.mSelected);
        return jsonObject;
    }
    
    @Override
    public String toString() {
        return "MdxSubtitle [mSelected=" + this.mSelected + ", id=" + this.id + ", languageCodeIso639_1=" + this.languageCodeIso639_1 + ", languageCodeIso639_2=" + this.languageCodeIso639_2 + ", languageDescription=" + this.languageDescription + ", trackType=" + this.trackType + ", canDeviceRender=" + this.canDeviceRender + ", nccpOrderNumber=" + this.nccpOrderNumber + "]";
    }
}
