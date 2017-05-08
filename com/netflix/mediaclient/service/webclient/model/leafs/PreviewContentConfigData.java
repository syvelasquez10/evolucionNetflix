// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import com.google.gson.annotations.SerializedName;

public class PreviewContentConfigData
{
    private static final String TAG = "nf_previewcontent";
    @SerializedName("encryptedDolbyDigitalPlus20Enabled")
    private boolean encryptedDolbyDigitalPlus20Enabled;
    @SerializedName("encryptedDolbyDigitalPlus51Enabled")
    private boolean encryptedDolbyDigitalPlus51Enabled;
    @SerializedName("encryptedHEAAC2Enabled")
    private boolean encryptedHEAAC2Enabled;
    @SerializedName("encryptedImageSubtitlesEnabled")
    private boolean encryptedImageSubtitlesEnabled;
    @SerializedName("encryptedTextSubtitlesEnabled")
    private boolean encryptedTextSubtitlesEnabled;
    @SerializedName("previewContentEnabled")
    private boolean previewContentEnabled;
    
    public PreviewContentConfigData() {
        this.previewContentEnabled = true;
        this.encryptedTextSubtitlesEnabled = true;
        this.encryptedImageSubtitlesEnabled = true;
        this.encryptedDolbyDigitalPlus51Enabled = true;
        this.encryptedDolbyDigitalPlus20Enabled = true;
        this.encryptedHEAAC2Enabled = true;
    }
    
    public static PreviewContentConfigData getDefault() {
        return new PreviewContentConfigData();
    }
    
    public static PreviewContentConfigData getDisabledConfig() {
        final PreviewContentConfigData previewContentConfigData = new PreviewContentConfigData();
        previewContentConfigData.previewContentEnabled = false;
        previewContentConfigData.encryptedTextSubtitlesEnabled = false;
        previewContentConfigData.encryptedImageSubtitlesEnabled = false;
        previewContentConfigData.encryptedDolbyDigitalPlus51Enabled = false;
        previewContentConfigData.encryptedDolbyDigitalPlus20Enabled = false;
        previewContentConfigData.encryptedHEAAC2Enabled = false;
        return previewContentConfigData;
    }
    
    @Override
    public boolean equals(final Object o) {
        final boolean b = true;
        final boolean b2 = false;
        boolean b3;
        if (this == o) {
            b3 = true;
        }
        else {
            b3 = b2;
            if (o != null) {
                b3 = b2;
                if (this.getClass() == o.getClass()) {
                    final PreviewContentConfigData previewContentConfigData = (PreviewContentConfigData)o;
                    b3 = b2;
                    if (this.previewContentEnabled == previewContentConfigData.previewContentEnabled) {
                        b3 = b2;
                        if (this.encryptedTextSubtitlesEnabled == previewContentConfigData.encryptedTextSubtitlesEnabled) {
                            b3 = b2;
                            if (this.encryptedImageSubtitlesEnabled == previewContentConfigData.encryptedImageSubtitlesEnabled) {
                                b3 = b2;
                                if (this.encryptedDolbyDigitalPlus51Enabled == previewContentConfigData.encryptedDolbyDigitalPlus51Enabled) {
                                    b3 = b2;
                                    if (this.encryptedDolbyDigitalPlus20Enabled == previewContentConfigData.encryptedDolbyDigitalPlus20Enabled) {
                                        return this.encryptedHEAAC2Enabled == previewContentConfigData.encryptedHEAAC2Enabled && b;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return b3;
    }
    
    @Override
    public int hashCode() {
        int n = 1;
        int n2;
        if (this.previewContentEnabled) {
            n2 = 1;
        }
        else {
            n2 = 0;
        }
        int n3;
        if (this.encryptedTextSubtitlesEnabled) {
            n3 = 1;
        }
        else {
            n3 = 0;
        }
        int n4;
        if (this.encryptedImageSubtitlesEnabled) {
            n4 = 1;
        }
        else {
            n4 = 0;
        }
        int n5;
        if (this.encryptedDolbyDigitalPlus51Enabled) {
            n5 = 1;
        }
        else {
            n5 = 0;
        }
        int n6;
        if (this.encryptedDolbyDigitalPlus20Enabled) {
            n6 = 1;
        }
        else {
            n6 = 0;
        }
        if (!this.encryptedHEAAC2Enabled) {
            n = 0;
        }
        return (n6 + (n5 + (n4 + (n3 + n2 * 31) * 31) * 31) * 31) * 31 + n;
    }
    
    public boolean isEncryptedDolbyDigitalPlus20Enabled() {
        return this.encryptedDolbyDigitalPlus20Enabled;
    }
    
    public boolean isEncryptedDolbyDigitalPlus51Enabled() {
        return this.encryptedDolbyDigitalPlus51Enabled;
    }
    
    public boolean isEncryptedHEAAC2Enabled() {
        return this.encryptedHEAAC2Enabled;
    }
    
    public boolean isEncryptedImageSubtitlesEnabled() {
        return this.encryptedImageSubtitlesEnabled;
    }
    
    public boolean isEncryptedTextSubtitlesEnabled() {
        return this.encryptedTextSubtitlesEnabled;
    }
    
    public boolean isPreviewContentEnabled() {
        return this.previewContentEnabled;
    }
    
    @Override
    public String toString() {
        return "PreviewContentConfigData{previewContentEnabled=" + this.previewContentEnabled + ", encryptedTextSubtitlesEnabled=" + this.encryptedTextSubtitlesEnabled + ", encryptedImageSubtitlesEnabled=" + this.encryptedImageSubtitlesEnabled + ", encryptedDolbyDigitalPlus51Enabled=" + this.encryptedDolbyDigitalPlus51Enabled + ", encryptedDolbyDigitalPlus20Enabled=" + this.encryptedDolbyDigitalPlus20Enabled + ", encryptedHEAAC2Enabled=" + this.encryptedHEAAC2Enabled + '}';
    }
}
