// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.nativecode;

public class WebpTranscoderFactory
{
    private static WebpTranscoder sWebpTranscoder;
    public static boolean sWebpTranscoderPresent;
    
    static {
        WebpTranscoderFactory.sWebpTranscoderPresent = false;
        try {
            WebpTranscoderFactory.sWebpTranscoder = (WebpTranscoder)Class.forName("com.facebook.imagepipeline.nativecode.WebpTranscoderImpl").newInstance();
            WebpTranscoderFactory.sWebpTranscoderPresent = true;
        }
        catch (Throwable t) {
            WebpTranscoderFactory.sWebpTranscoderPresent = false;
        }
    }
    
    public static WebpTranscoder getWebpTranscoder() {
        return WebpTranscoderFactory.sWebpTranscoder;
    }
}
