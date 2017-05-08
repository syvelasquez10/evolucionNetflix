// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import com.netflix.mediaclient.util.api.Api19Util;
import com.netflix.mediaclient.util.api.Api21Util;
import android.media.SoundPool;

public final class AudioUtils
{
    public static SoundPool createSoundPool(final int n) {
        if (AndroidUtils.getAndroidVersion() >= 21) {
            return Api21Util.createSoundPool();
        }
        return Api19Util.createSoundPoolPreLollipop(n);
    }
}
