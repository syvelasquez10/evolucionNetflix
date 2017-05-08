// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import java.util.HashMap;

final class MediaUtils$1 extends HashMap<String, MediaUtils$VideoDecoderClassfier>
{
    MediaUtils$1() {
        this.put("video/avc", MediaUtils$VideoDecoderClassfier.AVC);
        this.put("video/x-vnd.on2.vp9", MediaUtils$VideoDecoderClassfier.VP9);
        this.put("video/hevc", MediaUtils$VideoDecoderClassfier.HEVC);
    }
}
