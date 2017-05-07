// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import java.util.Map;

public class Utility$FetchedAppSettings
{
    private Map<String, Map<String, Utility$DialogFeatureConfig>> dialogConfigMap;
    private String nuxContent;
    private boolean nuxEnabled;
    private boolean supportsAttribution;
    private boolean supportsImplicitLogging;
    
    private Utility$FetchedAppSettings(final boolean supportsAttribution, final boolean supportsImplicitLogging, final String nuxContent, final boolean nuxEnabled, final Map<String, Map<String, Utility$DialogFeatureConfig>> dialogConfigMap) {
        this.supportsAttribution = supportsAttribution;
        this.supportsImplicitLogging = supportsImplicitLogging;
        this.nuxContent = nuxContent;
        this.nuxEnabled = nuxEnabled;
        this.dialogConfigMap = dialogConfigMap;
    }
    
    public Map<String, Map<String, Utility$DialogFeatureConfig>> getDialogConfigurations() {
        return this.dialogConfigMap;
    }
    
    public String getNuxContent() {
        return this.nuxContent;
    }
    
    public boolean getNuxEnabled() {
        return this.nuxEnabled;
    }
    
    public boolean supportsAttribution() {
        return this.supportsAttribution;
    }
    
    public boolean supportsImplicitLogging() {
        return this.supportsImplicitLogging;
    }
}
