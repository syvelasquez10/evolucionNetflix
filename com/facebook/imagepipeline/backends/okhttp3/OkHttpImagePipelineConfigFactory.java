// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.backends.okhttp3;

import com.facebook.imagepipeline.producers.NetworkFetcher;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.core.ImagePipelineConfig$Builder;
import okhttp3.OkHttpClient;
import android.content.Context;

public class OkHttpImagePipelineConfigFactory
{
    public static ImagePipelineConfig$Builder newBuilder(final Context context, final OkHttpClient okHttpClient) {
        return ImagePipelineConfig.newBuilder(context).setNetworkFetcher(new OkHttpNetworkFetcher(okHttpClient));
    }
}
