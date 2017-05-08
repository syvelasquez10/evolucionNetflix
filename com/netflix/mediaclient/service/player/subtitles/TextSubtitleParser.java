// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles;

import com.netflix.mediaclient.service.player.subtitles.text.Region;
import com.netflix.mediaclient.service.player.subtitles.text.TextStyle;
import com.netflix.mediaclient.service.player.subtitles.text.CellResolution;

public interface TextSubtitleParser extends SubtitleParser
{
    CellResolution getCellResolution();
    
    TextStyle getDeviceDefault();
    
    Region getNamedRegion(final String p0);
    
    TextStyle getNamedStyle(final String p0);
    
    TextStyle getRegionDefault();
    
    Region[] getRegions();
    
    TextStyle getTextStyleDefault();
    
    double getTickRate();
    
    String getTimeBase();
    
    TextStyle getUserDefaults();
}
