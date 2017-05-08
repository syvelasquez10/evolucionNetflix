// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.chunk;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import com.google.android.exoplayer.MediaCodecUtil;
import com.google.android.exoplayer.util.MimeTypes;
import com.google.android.exoplayer.util.Util;
import android.graphics.Point;

public final class VideoFormatSelectorUtil
{
    private static Point getMaxVideoSizeInViewport(final boolean b, final int n, final int n2, final int n3, final int n4) {
        int n5 = 1;
        int n6 = n;
        int n7 = n2;
        if (b) {
            int n8;
            if (n3 > n4) {
                n8 = 1;
            }
            else {
                n8 = 0;
            }
            if (n <= n2) {
                n5 = 0;
            }
            n6 = n;
            n7 = n2;
            if (n8 != n5) {
                n7 = n;
                n6 = n2;
            }
        }
        if (n3 * n7 >= n4 * n6) {
            return new Point(n6, Util.ceilDivide(n6 * n4, n3));
        }
        return new Point(Util.ceilDivide(n7 * n3, n4), n7);
    }
    
    private static boolean isFormatPlayable(final Format format, final String[] array, final boolean b, final boolean b2) {
        if ((array == null || Util.contains(array, format.mimeType)) && (!b || (format.width < 1280 && format.height < 720))) {
            if (format.width > 0 && format.height > 0) {
                if (Util.SDK_INT >= 21) {
                    String videoMediaMimeType;
                    if ("video/x-unknown".equals(videoMediaMimeType = MimeTypes.getVideoMediaMimeType(format.codecs))) {
                        videoMediaMimeType = "video/avc";
                    }
                    if (format.frameRate > 0.0f) {
                        return MediaCodecUtil.isSizeAndRateSupportedV21(videoMediaMimeType, b2, format.width, format.height, format.frameRate);
                    }
                    return MediaCodecUtil.isSizeSupportedV21(videoMediaMimeType, b2, format.width, format.height);
                }
                else if (format.width * format.height > MediaCodecUtil.maxH264DecodableFrameSize()) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    public static int[] selectVideoFormats(final List<? extends FormatWrapper> list, final String[] array, final boolean b, final boolean b2, final boolean b3, int i, int width) {
        int n = Integer.MAX_VALUE;
        final ArrayList<Integer> list2 = new ArrayList<Integer>();
        final int size = list.size();
        int j = 0;
    Label_0178_Outer:
        while (j < size) {
            final Format format = ((FormatWrapper)list.get(j)).getFormat();
            if (isFormatPlayable(format, array, b, b3)) {
                list2.add(j);
                if (format.width > 0 && format.height > 0 && i > 0 && width > 0) {
                    final Point maxVideoSizeInViewport = getMaxVideoSizeInViewport(b2, i, width, format.width, format.height);
                    final int n2 = format.width * format.height;
                    if (format.width >= (int)(maxVideoSizeInViewport.x * 0.98f) && format.height >= (int)(maxVideoSizeInViewport.y * 0.98f) && n2 < n) {
                        n = n2;
                    }
                }
            }
            while (true) {
                ++j;
                continue Label_0178_Outer;
                continue;
            }
        }
        if (n != Integer.MAX_VALUE) {
            Format format2;
            for (i = list2.size() - 1; i >= 0; --i) {
                format2 = ((FormatWrapper)list.get(list2.get(i))).getFormat();
                if (format2.width > 0 && format2.height > 0) {
                    width = format2.width;
                    if (format2.height * width > n) {
                        list2.remove(i);
                    }
                }
            }
        }
        return Util.toArray(list2);
    }
    
    public static int[] selectVideoFormatsForDefaultDisplay(final Context context, final List<? extends FormatWrapper> list, final String[] array, final boolean b) {
        final Point physicalDisplaySize = Util.getPhysicalDisplaySize(context);
        return selectVideoFormats(list, array, b, true, false, physicalDisplaySize.x, physicalDisplaySize.y);
    }
}
