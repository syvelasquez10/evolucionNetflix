// 
// Decompiled by Procyon v0.5.30
// 

package com.visualon.OSMPUtils;

import java.util.List;

public class voOSOption
{
    public static final long OPTION_DECODE_DISABLE_DEBLOCK = 1L;
    public static final long OPTION_DECODE_DOLBY = 1L;
    public static final long OPTION_RENDERTYPE_NATIVE = 1L;
    public static final long OPTION_RENDERTYPE_OPENGL = 4L;
    public static final long OPTION_VIDEO_EFFECT_CLOSE_CAPTION = 1L;
    public static final long OPTION_VIDEO_EFFECT_CLOSE_CAPTION_ON = 1L;
    eVoOption mType;
    long mValue;
    
    public voOSOption(final eVoOption mType, final long mValue) {
        this.mType = eVoOption.eoNone;
        this.mValue = 0L;
        this.mType = mType;
        this.mValue = mValue;
    }
    
    public static voOSOption make(final eVoOption eVoOption, final long n) throws Exception {
        switch (eVoOption) {
            case eoVideoRender: {
                if (n < 1L || n > 4L) {
                    throw new Exception("invalid argument");
                }
                break;
            }
        }
        return new voOSOption(eVoOption, n);
    }
    
    public eVoOption getType() {
        return this.mType;
    }
    
    public long getValue() {
        return this.mValue;
    }
    
    public List<voOSOption> readConfig(final String s) {
        return null;
    }
    
    public enum eVoOption
    {
        eoAudioAnimation, 
        eoAudioDecode, 
        eoHLSBitrate, 
        eoLoop, 
        eoNone, 
        eoNotLoadLibrary, 
        eoSocketType, 
        eoThirdLibOp, 
        eoThreadCount, 
        eoVideoCloseCaption, 
        eoVideoColor, 
        eoVideoDecode, 
        eoVideoEffect, 
        eoVideoRender;
    }
}
