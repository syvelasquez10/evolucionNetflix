// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media;

import com.netflix.mediaclient.service.player.subtitles.text.FontWeight;
import com.netflix.mediaclient.service.player.subtitles.text.FontFamilyMapping;
import com.netflix.mediaclient.service.player.subtitles.text.Outline;
import com.netflix.mediaclient.service.player.subtitles.text.TextStyle;
import android.content.Context;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;

public class Watermark
{
    private static final String ANCHOR = "anchor";
    public static final int DEFAULT_SIZE_DP = 10;
    private static final String ID = "identifier";
    private static final String OPACITY = "opacity";
    public static final float SAFE_DISPLAY_AREA_MARGIN = 5.0f;
    private static final String TAG = "nf_watermark";
    private Watermark$Anchor mAnchor;
    private String mIdentifier;
    private int mOpacity;
    
    private Watermark(final JSONObject jsonObject) {
        if (jsonObject == null) {
            throw new IllegalArgumentException("Watermark json is null!");
        }
        this.mIdentifier = JsonUtils.getString(jsonObject, "identifier", null);
        this.mOpacity = JsonUtils.getInt(jsonObject, "opacity", -1);
        final String string = JsonUtils.getString(jsonObject, "anchor", null);
        if (StringUtils.isEmpty(string)) {
            return;
        }
        try {
            this.mAnchor = Watermark$Anchor.valueOf(string);
        }
        catch (Throwable t) {
            Log.e("nf_watermark", "Failed, go to default", t);
            this.mAnchor = Watermark$Anchor.top_center;
        }
    }
    
    public static Watermark createWatermark(final JSONObject jsonObject) {
        if (jsonObject != null) {
            final Watermark watermark = new Watermark(jsonObject);
            if (watermark.isValid()) {
                return watermark;
            }
        }
        return null;
    }
    
    public Watermark$Anchor getAnchor() {
        return this.mAnchor;
    }
    
    public String getIdentifier() {
        return this.mIdentifier;
    }
    
    public int getOpacity() {
        return this.mOpacity;
    }
    
    public TextStyle getStyle(final Context context) {
        return new TextStyle("watermark", "white", null, null, null, Outline.getDefaultOutline(), FontFamilyMapping.defaultType, null, null, null, this.mOpacity / 100.0f, null, null);
    }
    
    public boolean isValid() {
        return this.mOpacity >= 0 && this.mOpacity <= 100 && StringUtils.isNotEmpty(this.mIdentifier) && this.mAnchor != null;
    }
    
    @Override
    public String toString() {
        return "Watermark{mIdentifier='" + this.mIdentifier + '\'' + ", mOpacity=" + this.mOpacity + ", mAnchor=" + this.mAnchor + '}';
    }
}
