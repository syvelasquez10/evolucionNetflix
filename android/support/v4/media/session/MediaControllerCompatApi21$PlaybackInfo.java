// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import android.media.session.MediaController$PlaybackInfo;
import android.media.AudioAttributes;

public class MediaControllerCompatApi21$PlaybackInfo
{
    private static final int FLAG_SCO = 4;
    private static final int STREAM_BLUETOOTH_SCO = 6;
    private static final int STREAM_SYSTEM_ENFORCED = 7;
    
    public static AudioAttributes getAudioAttributes(final Object o) {
        return ((MediaController$PlaybackInfo)o).getAudioAttributes();
    }
    
    public static int getCurrentVolume(final Object o) {
        return ((MediaController$PlaybackInfo)o).getCurrentVolume();
    }
    
    public static int getLegacyAudioStream(final Object o) {
        return toLegacyStreamType(getAudioAttributes(o));
    }
    
    public static int getMaxVolume(final Object o) {
        return ((MediaController$PlaybackInfo)o).getMaxVolume();
    }
    
    public static int getPlaybackType(final Object o) {
        return ((MediaController$PlaybackInfo)o).getPlaybackType();
    }
    
    public static int getVolumeControl(final Object o) {
        return ((MediaController$PlaybackInfo)o).getVolumeControl();
    }
    
    private static int toLegacyStreamType(final AudioAttributes audioAttributes) {
        int n = 3;
        if ((audioAttributes.getFlags() & 0x1) == 0x1) {
            n = 7;
        }
        else {
            if ((audioAttributes.getFlags() & 0x4) == 0x4) {
                return 6;
            }
            switch (audioAttributes.getUsage()) {
                case 1:
                case 11:
                case 12:
                case 14: {
                    break;
                }
                default: {
                    return 3;
                }
                case 2: {
                    return 0;
                }
                case 13: {
                    return 1;
                }
                case 3: {
                    return 8;
                }
                case 4: {
                    return 4;
                }
                case 6: {
                    return 2;
                }
                case 5:
                case 7:
                case 8:
                case 9:
                case 10: {
                    return 5;
                }
            }
        }
        return n;
    }
}
