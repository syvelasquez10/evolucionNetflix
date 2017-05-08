// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.imagehelper;

import java.util.Iterator;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import java.util.List;

public class MultiSourceHelper
{
    public static MultiSourceHelper$MultiSourceResult getBestSourceForSize(final int n, final int n2, final List<ImageSource> list) {
        return getBestSourceForSize(n, n2, list, 1.0);
    }
    
    public static MultiSourceHelper$MultiSourceResult getBestSourceForSize(final int n, final int n2, final List<ImageSource> list, final double n3) {
        if (list.isEmpty()) {
            return new MultiSourceHelper$MultiSourceResult(null, null, null);
        }
        if (list.size() == 1) {
            return new MultiSourceHelper$MultiSourceResult(list.get(0), null, null);
        }
        if (n <= 0 || n2 <= 0) {
            return new MultiSourceHelper$MultiSourceResult(null, null, null);
        }
        final ImagePipeline imagePipeline = ImagePipelineFactory.getInstance().getImagePipeline();
        final double n4 = n * n2;
        double n5 = Double.MAX_VALUE;
        final Iterator<ImageSource> iterator = list.iterator();
        double n6 = Double.MAX_VALUE;
        ImageSource imageSource = null;
        ImageSource imageSource2 = null;
        while (iterator.hasNext()) {
            final ImageSource imageSource3 = iterator.next();
            final double abs = Math.abs(1.0 - imageSource3.getSize() / (n4 * n3));
            double n7 = n6;
            if (abs < n6) {
                n7 = abs;
                imageSource = imageSource3;
            }
            if (abs < n5 && (imagePipeline.isInBitmapMemoryCache(imageSource3.getUri()) || imagePipeline.isInDiskCacheSync(imageSource3.getUri()))) {
                imageSource2 = imageSource3;
                n5 = abs;
            }
            n6 = n7;
        }
        ImageSource imageSource4;
        if ((imageSource4 = imageSource2) != null) {
            imageSource4 = imageSource2;
            if (imageSource != null) {
                imageSource4 = imageSource2;
                if (imageSource2.getSource().equals(imageSource.getSource())) {
                    imageSource4 = null;
                }
            }
        }
        return new MultiSourceHelper$MultiSourceResult(imageSource, imageSource4, null);
    }
}
