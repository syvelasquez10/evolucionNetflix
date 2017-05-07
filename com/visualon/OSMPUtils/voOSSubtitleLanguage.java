// 
// Decompiled by Procyon v0.5.30
// 

package com.visualon.OSMPUtils;

public interface voOSSubtitleLanguage
{
    public static final int VOOSMP_LANG_TYPE_ENGLISH = 3;
    public static final int VOOSMP_LANG_TYPE_FRENCH = 4;
    public static final int VOOSMP_LANG_TYPE_GERMANY = 7;
    public static final int VOOSMP_LANG_TYPE_ITALIAN = 8;
    public static final int VOOSMP_LANG_TYPE_JAPANESE = 6;
    public static final int VOOSMP_LANG_TYPE_KOREAN = 5;
    public static final int VOOSMP_LANG_TYPE_SIMPLE_CHINESE = 1;
    public static final int VOOSMP_LANG_TYPE_SPANISH = 9;
    public static final int VOOSMP_LANG_TYPE_TRADITIONAL_CHINESE = 2;
    public static final int VOOSMP_LANG_TYPE_UNKNOWN = 0;
    
    int Flag();
    
    String LangName();
    
    int LanguageType();
    
    int Reserved1();
    
    int Reserved2();
}
