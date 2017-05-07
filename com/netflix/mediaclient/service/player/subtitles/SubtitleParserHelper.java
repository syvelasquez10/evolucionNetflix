// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles;

import org.w3c.dom.Element;

final class SubtitleParserHelper
{
    public static final String BR = "br";
    public static final String SPAN = "span";
    
    public static boolean isBreak(final Element element) {
        return element != null && "br".equalsIgnoreCase(element.getTagName());
    }
    
    public static boolean isSpan(final Element element) {
        return element != null && "span".equalsIgnoreCase(element.getTagName());
    }
}
