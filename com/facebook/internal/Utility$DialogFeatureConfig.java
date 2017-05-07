// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import org.json.JSONArray;
import org.json.JSONObject;
import android.net.Uri;

public class Utility$DialogFeatureConfig
{
    private String dialogName;
    private Uri fallbackUrl;
    private String featureName;
    private int[] featureVersionSpec;
    
    private Utility$DialogFeatureConfig(final String dialogName, final String featureName, final Uri fallbackUrl, final int[] featureVersionSpec) {
        this.dialogName = dialogName;
        this.featureName = featureName;
        this.fallbackUrl = fallbackUrl;
        this.featureVersionSpec = featureVersionSpec;
    }
    
    private static Utility$DialogFeatureConfig parseDialogConfig(final JSONObject jsonObject) {
        Uri parse = null;
        final String optString = jsonObject.optString("name");
        if (!Utility.isNullOrEmpty(optString)) {
            final String[] split = optString.split("\\|");
            if (split.length == 2) {
                final String s = split[0];
                final String s2 = split[1];
                if (!Utility.isNullOrEmpty(s) && !Utility.isNullOrEmpty(s2)) {
                    final String optString2 = jsonObject.optString("url");
                    if (!Utility.isNullOrEmpty(optString2)) {
                        parse = Uri.parse(optString2);
                    }
                    return new Utility$DialogFeatureConfig(s, s2, parse, parseVersionSpec(jsonObject.optJSONArray("versions")));
                }
            }
        }
        return null;
    }
    
    private static int[] parseVersionSpec(final JSONArray jsonArray) {
        int[] array = null;
        if (jsonArray != null) {
            final int length = jsonArray.length();
            array = new int[length];
            int i = 0;
        Label_0064_Outer:
            while (i < length) {
                final int optInt = jsonArray.optInt(i, -1);
                while (true) {
                    int int1;
                    if ((int1 = optInt) != -1) {
                        break Label_0064;
                    }
                    final String optString = jsonArray.optString(i);
                    int1 = optInt;
                    if (Utility.isNullOrEmpty(optString)) {
                        break Label_0064;
                    }
                    try {
                        int1 = Integer.parseInt(optString);
                        array[i] = int1;
                        ++i;
                        continue Label_0064_Outer;
                    }
                    catch (NumberFormatException ex) {
                        Utility.logd("FacebookSDK", ex);
                        int1 = -1;
                        continue;
                    }
                    break;
                }
                break;
            }
        }
        return array;
    }
    
    public String getDialogName() {
        return this.dialogName;
    }
    
    public Uri getFallbackUrl() {
        return this.fallbackUrl;
    }
    
    public String getFeatureName() {
        return this.featureName;
    }
    
    public int[] getVersionSpec() {
        return this.featureVersionSpec;
    }
}
