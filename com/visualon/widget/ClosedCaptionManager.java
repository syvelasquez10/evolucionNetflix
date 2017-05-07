// 
// Decompiled by Procyon v0.5.30
// 

package com.visualon.widget;

import com.visualon.OSMPSubTitle.voSubTitleManager;

public class ClosedCaptionManager extends voSubTitleManager
{
    @Override
    public voSubTitleFormatSettingImpl getSettings() {
        if (this.settings == null) {
            this.settings = (voSubTitleFormatSettingImpl)new CCSettings();
        }
        return this.settings;
    }
    
    public class CCSettings extends voSubTitleFormatSettingImpl
    {
    }
}
