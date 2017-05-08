// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media;

public abstract class BaseSubtitle implements Subtitle
{
    protected static final String ATTR_CAN_DEVICE_RENDER = "canDeviceRender";
    protected static final String ATTR_ID = "id";
    protected static final String ATTR_LANGUAGE = "language";
    protected static final String ATTR_LANGUAGE_DESCRIPTION = "languageDescription";
    public static final String ATTR_ORDER = "order";
    protected static final String ATTR_SELECTED = "selected";
    protected static final String ATTR_TRACK_TYPE = "trackType";
    public static final String IMPL = "impl";
    protected boolean canDeviceRender;
    protected String id;
    protected String languageCodeIso639_1;
    protected String languageCodeIso639_2;
    protected String languageDescription;
    protected int nccpOrderNumber;
    protected int trackType;
    
    @Override
    public boolean canDeviceRender() {
        return this.canDeviceRender;
    }
    
    @Override
    public int compareTo(final Subtitle subtitle) {
        int compare = -1;
        if (subtitle != null && this.languageDescription != null) {
            if (subtitle.getLanguageDescription() == null) {
                return 1;
            }
            if ((compare = String.CASE_INSENSITIVE_ORDER.compare(this.languageDescription, subtitle.getLanguageDescription())) == 0) {
                return this.languageDescription.compareTo(subtitle.getLanguageDescription());
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
            if (this.nccpOrderNumber != ((Subtitle)o).getNccpOrderNumber()) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public String getId() {
        return this.id;
    }
    
    @Override
    public String getLanguageCodeIso639_1() {
        return this.languageCodeIso639_1;
    }
    
    @Override
    public String getLanguageCodeIso639_2() {
        return this.languageCodeIso639_2;
    }
    
    @Override
    public String getLanguageDescription() {
        return this.languageDescription;
    }
    
    @Override
    public int getNccpOrderNumber() {
        return this.nccpOrderNumber;
    }
    
    @Override
    public int getTrackType() {
        return this.trackType;
    }
    
    @Override
    public int hashCode() {
        return this.nccpOrderNumber + 31;
    }
    
    @Override
    public boolean isCC() {
        return this.trackType == 2;
    }
    
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [id=" + this.id + ", languageCodeIso639_1=" + this.languageCodeIso639_1 + ", languageCodeIso639_2=" + this.languageCodeIso639_2 + ", languageDescription=" + this.languageDescription + ", trackType=" + this.trackType + ", canDeviceRender=" + this.canDeviceRender + ", nccpOrderNumber=" + this.nccpOrderNumber + "]";
    }
}
