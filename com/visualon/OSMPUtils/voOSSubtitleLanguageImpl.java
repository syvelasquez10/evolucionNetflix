// 
// Decompiled by Procyon v0.5.30
// 

package com.visualon.OSMPUtils;

public class voOSSubtitleLanguageImpl implements voOSSubtitleLanguage
{
    int mFlag;
    String mLangName;
    int mLanguageType;
    int mReserved1;
    int mReserved2;
    
    public voOSSubtitleLanguageImpl(final String mLangName, final int mLanguageType, final int mFlag, final int mReserved1, final int mReserved2) {
        this.mLangName = mLangName;
        this.mLanguageType = mLanguageType;
        this.mFlag = mFlag;
        this.mReserved1 = mReserved1;
        this.mReserved2 = mReserved2;
    }
    
    @Override
    public int Flag() {
        return this.mFlag;
    }
    
    @Override
    public String LangName() {
        return this.mLangName;
    }
    
    @Override
    public int LanguageType() {
        return this.mLanguageType;
    }
    
    @Override
    public int Reserved1() {
        return this.mReserved1;
    }
    
    @Override
    public int Reserved2() {
        return this.mReserved2;
    }
}
