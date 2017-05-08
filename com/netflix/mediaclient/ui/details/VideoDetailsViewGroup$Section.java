// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

public enum VideoDetailsViewGroup$Section
{
    ACTIONS(false), 
    ACTION_LABEL(false), 
    CREDITS(true), 
    INFO(false), 
    MATCH(false), 
    SPINNER(false), 
    SYNOPSIS(true), 
    TITLE(false);
    
    boolean isSecondary;
    
    private VideoDetailsViewGroup$Section(final boolean isSecondary) {
        this.isSecondary = isSecondary;
    }
    
    public boolean isSecondaryText() {
        return this.isSecondary;
    }
}
