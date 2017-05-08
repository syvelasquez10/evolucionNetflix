// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.util;

import org.xmlpull.v1.XmlPullParser;

public final class ParserUtil
{
    public static boolean isEndTag(final XmlPullParser xmlPullParser) {
        return xmlPullParser.getEventType() == 3;
    }
    
    public static boolean isEndTag(final XmlPullParser xmlPullParser, final String s) {
        return isEndTag(xmlPullParser) && xmlPullParser.getName().equals(s);
    }
    
    public static boolean isStartTag(final XmlPullParser xmlPullParser) {
        return xmlPullParser.getEventType() == 2;
    }
    
    public static boolean isStartTag(final XmlPullParser xmlPullParser, final String s) {
        return isStartTag(xmlPullParser) && xmlPullParser.getName().equals(s);
    }
}
