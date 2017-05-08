// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.android;

import org.json.JSONException;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import com.netflix.mediaclient.service.webclient.model.leafs.PreviewContentConfigData;
import com.netflix.mediaclient.javabridge.invoke.BaseInvoke;

public class SetPreviewContentConfiguration extends BaseInvoke
{
    private static final String METHOD = "setPreviewContentConfiguration";
    private static final String PROPERTY_ENCRYPTED_DOLBY_DIGITAL_PLUS_20_ENABLED = "encDDP20";
    private static final String PROPERTY_ENCRYPTED_DOLBY_DIGITAL_PLUS_51_ENABLED = "encDDP51";
    private static final String PROPERTY_ENCRYPTED_HEAAC_2_ENABLED = "encHEAAC2";
    private static final String PROPERTY_ENCRYPTED_IMAGE_SUBTITLES_ENABLED = "encImageSubtitles";
    private static final String PROPERTY_ENCRYPTED_TEXT_SUBTITLES_ENABLED = "encTextSubtitles";
    private static final String PROPERTY_PREVIEW_CONTENT_ENABLED = "enabled";
    private static final String TARGET = "android";
    
    public SetPreviewContentConfiguration(final PreviewContentConfigData arguments) {
        super("android", "setPreviewContentConfiguration");
        this.setArguments(arguments);
    }
    
    private void setArguments(final PreviewContentConfigData previewContentConfigData) {
        try {
            final JSONObject jsonObject = new JSONObject();
            if (previewContentConfigData != null) {
                if (Log.isLoggable()) {
                    Log.d("nf_invoke", "Preview content: " + previewContentConfigData);
                }
                jsonObject.put("enabled", previewContentConfigData.isPreviewContentEnabled());
                jsonObject.put("encTextSubtitles", previewContentConfigData.isEncryptedTextSubtitlesEnabled());
                jsonObject.put("encImageSubtitles", previewContentConfigData.isEncryptedImageSubtitlesEnabled());
                jsonObject.put("encDDP20", previewContentConfigData.isEncryptedDolbyDigitalPlus20Enabled());
                jsonObject.put("encDDP51", previewContentConfigData.isEncryptedDolbyDigitalPlus51Enabled());
                jsonObject.put("encHEAAC2", previewContentConfigData.isEncryptedHEAAC2Enabled());
            }
            else {
                Log.d("nf_invoke", "No preview content override, default to enabled");
                jsonObject.put("enabled", true);
                jsonObject.put("encTextSubtitles", true);
                jsonObject.put("encImageSubtitles", true);
                jsonObject.put("encDDP20", true);
                jsonObject.put("encDDP51", true);
                jsonObject.put("encHEAAC2", true);
            }
            this.arguments = jsonObject.toString();
        }
        catch (JSONException ex) {
            Log.e("nf_invoke", "Failed to create JSON object", (Throwable)ex);
        }
    }
}
