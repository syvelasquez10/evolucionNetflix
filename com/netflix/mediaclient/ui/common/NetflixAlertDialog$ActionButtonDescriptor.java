// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

class NetflixAlertDialog$ActionButtonDescriptor
{
    private final String buttonId;
    private final String label;
    
    public NetflixAlertDialog$ActionButtonDescriptor(final String label, final String buttonId) {
        if (buttonId == null || label == null) {
            throw new IllegalArgumentException("Button ID and label can not be null!");
        }
        this.label = label;
        this.buttonId = buttonId;
    }
    
    public String getButtonId() {
        return this.buttonId;
    }
    
    public String getLabel() {
        return this.label;
    }
    
    @Override
    public String toString() {
        return "ActionButtonDescriptor [labelResourceId=" + this.label + ", buttonId=" + this.buttonId + "]";
    }
}
