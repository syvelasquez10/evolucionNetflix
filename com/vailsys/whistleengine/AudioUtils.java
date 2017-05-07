// 
// Decompiled by Procyon v0.5.30
// 

package com.vailsys.whistleengine;

import android.media.AudioRecord;

class AudioUtils
{
    private static final int[] sampleRates;
    
    static {
        sampleRates = new int[] { 48000, 32000, 24000, 16000, 8000 };
    }
    
    public static int getSampleRate() {
        final int[] sampleRates = AudioUtils.sampleRates;
        for (int length = sampleRates.length, i = 0; i < length; ++i) {
            final int n = sampleRates[i];
            if (isSampleRateSupported(n)) {
                return n;
            }
        }
        return 0;
    }
    
    public static boolean isSampleRateSupported(int minBufferSize) {
        minBufferSize = AudioRecord.getMinBufferSize(minBufferSize, 16, 2);
        return minBufferSize != -2 && minBufferSize != -1;
    }
}
