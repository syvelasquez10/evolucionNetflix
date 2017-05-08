// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.audio;

import android.media.PlaybackParams;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.C;
import android.annotation.TargetApi;
import android.util.Log;
import com.google.android.exoplayer.util.Ac3Util;
import com.google.android.exoplayer.util.DtsUtil;
import com.google.android.exoplayer.util.Util;
import java.nio.ByteBuffer;
import android.os.ConditionVariable;
import java.lang.reflect.Method;

class AudioTrack$1 extends Thread
{
    final /* synthetic */ AudioTrack this$0;
    final /* synthetic */ android.media.AudioTrack val$toRelease;
    
    AudioTrack$1(final AudioTrack this$0, final android.media.AudioTrack val$toRelease) {
        this.this$0 = this$0;
        this.val$toRelease = val$toRelease;
    }
    
    @Override
    public void run() {
        try {
            this.val$toRelease.flush();
            this.val$toRelease.release();
        }
        finally {
            this.this$0.releasingConditionVariable.open();
        }
    }
}
