// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player;

enum PlayerAgent$AudioSinkType
{
    AUDIOSINK_BT("bluetooth"), 
    AUDIOSINK_BUILTIN("builtin"), 
    AUDIOSINK_DEAULT("default"), 
    AUDIOSINK_DOCK("dock"), 
    AUDIOSINK_HEADPHONE("headphone"), 
    AUDIOSINK_OTHERS("others"), 
    AUDIOSINK_USB("usb");
    
    private String mDecriptionString;
    
    private PlayerAgent$AudioSinkType(final String mDecriptionString) {
        this.mDecriptionString = mDecriptionString;
    }
    
    String getDecriptionString() {
        return this.mDecriptionString;
    }
}
