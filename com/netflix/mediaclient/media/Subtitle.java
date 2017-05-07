// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media;

import org.json.JSONException;
import com.netflix.mediaclient.ui.player.NccpSubtitle;
import com.netflix.mediaclient.ui.mdx.MdxSubtitle;
import org.json.JSONObject;
import com.netflix.mediaclient.Log;

public abstract class Subtitle implements Comparable<Subtitle>
{
    protected static final String ATTR_CAN_DEVICE_RENDER = "canDeviceRender";
    protected static final String ATTR_ID = "id";
    protected static final String ATTR_LANGUAGE = "language";
    protected static final String ATTR_LANGUAGE_DESCRIPTION = "languageDescription";
    protected static final String ATTR_ORDER = "order";
    protected static final String ATTR_SELECTED = "selected";
    protected static final String ATTR_TRACK_TYPE = "trackType";
    public static final int CLOSED_CAPTION_SUBTITLE = 1;
    protected static final String IMPL = "impl";
    public static final int PRIMARY_SUBTITLE = 0;
    public static final int UNKNOWN_SUBTITLE = -1;
    protected boolean canDeviceRender;
    protected String id;
    protected String languageCodeIso639_1;
    protected String languageCodeIso639_2;
    protected String languageDescription;
    protected int nccpOrderNumber;
    protected int trackType;
    
    public static void dumpLog(final Subtitle[] array, final String s) {
        if (array != null) {
            if (Log.isLoggable()) {
                Log.d(s, "Subtitles: " + array.length);
                for (int i = 0; i < array.length; ++i) {
                    Log.d(s, i + " " + array[i]);
                }
            }
        }
        else {
            Log.e(s, "Subtitles are null!");
        }
    }
    
    static Subtitle restore(final JSONObject jsonObject) {
        if (jsonObject == null) {
            return null;
        }
        final int optInt = jsonObject.optInt("impl", -1);
        if (optInt == 2) {
            return MdxSubtitle.newInstance(jsonObject, jsonObject.getInt("order"));
        }
        if (optInt == 1) {
            return NccpSubtitle.newInstance(jsonObject, jsonObject.getInt("order"));
        }
        throw new JSONException("Implementation does not support restore " + optInt);
    }
    
    public boolean canDeviceRender() {
        return this.canDeviceRender;
    }
    
    @Override
    public int compareTo(final Subtitle subtitle) {
        int compare = -1;
        if (subtitle != null && this.languageDescription != null) {
            if (subtitle.languageDescription == null) {
                return 1;
            }
            if ((compare = String.CASE_INSENSITIVE_ORDER.compare(this.languageDescription, subtitle.languageDescription)) == 0) {
                return this.languageDescription.compareTo(subtitle.languageDescription);
            }
        }
        return compare;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null) {
                return false;
            }
            if (!(o instanceof Subtitle)) {
                return false;
            }
            if (this.nccpOrderNumber != ((Subtitle)o).nccpOrderNumber) {
                return false;
            }
        }
        return true;
    }
    
    public String getId() {
        return this.id;
    }
    
    public String getLanguageCodeIso639_1() {
        return this.languageCodeIso639_1;
    }
    
    public String getLanguageCodeIso639_2() {
        return this.languageCodeIso639_2;
    }
    
    public String getLanguageDescription() {
        return this.languageDescription;
    }
    
    public int getNccpOrderNumber() {
        return this.nccpOrderNumber;
    }
    
    public int getTrackType() {
        return this.trackType;
    }
    
    @Override
    public int hashCode() {
        return this.nccpOrderNumber + 31;
    }
    
    public boolean isCC() {
        return this.trackType == 1;
    }
    
    public abstract JSONObject toJson();
    
    @Override
    public String toString() {
        return "Subtitle [id=" + this.id + ", languageCodeIso639_1=" + this.languageCodeIso639_1 + ", languageCodeIso639_2=" + this.languageCodeIso639_2 + ", languageDescription=" + this.languageDescription + ", trackType=" + this.trackType + ", canDeviceRender=" + this.canDeviceRender + ", nccpOrderNumber=" + this.nccpOrderNumber + "]";
    }
}
