// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.fresco;

import com.facebook.common.logging.FLog;
import com.facebook.common.soloader.SoLoaderShim$Handler;
import com.facebook.common.soloader.SoLoaderShim;
import com.facebook.drawee.backends.pipeline.Fresco;
import java.util.Set;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory;
import com.facebook.react.modules.network.OkHttpClientProvider;
import com.facebook.imagepipeline.listener.RequestListener;
import java.util.HashSet;
import com.facebook.imagepipeline.core.ImagePipelineConfig$Builder;
import android.content.Context;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

public class FrescoModule extends ReactContextBaseJavaModule
{
    private static boolean sHasBeenInitialized;
    private ImagePipelineConfig mConfig;
    
    static {
        FrescoModule.sHasBeenInitialized = false;
    }
    
    public FrescoModule(final ReactApplicationContext reactApplicationContext) {
        this(reactApplicationContext, null);
    }
    
    public FrescoModule(final ReactApplicationContext reactApplicationContext, final ImagePipelineConfig mConfig) {
        super(reactApplicationContext);
        this.mConfig = mConfig;
    }
    
    private static ImagePipelineConfig getDefaultConfig(final Context context) {
        return getDefaultConfigBuilder(context).build();
    }
    
    public static ImagePipelineConfig$Builder getDefaultConfigBuilder(final Context context) {
        final HashSet<RequestListener> requestListeners = new HashSet<RequestListener>();
        requestListeners.add(new SystraceRequestListener());
        return OkHttpImagePipelineConfigFactory.newBuilder(context.getApplicationContext(), OkHttpClientProvider.getOkHttpClient()).setDownsampleEnabled(false).setRequestListeners(requestListeners);
    }
    
    public static boolean hasBeenInitialized() {
        return FrescoModule.sHasBeenInitialized;
    }
    
    public void clearSensitiveData() {
        Fresco.getImagePipeline().clearCaches();
    }
    
    @Override
    public String getName() {
        return "FrescoModule";
    }
    
    @Override
    public void initialize() {
        super.initialize();
        if (!hasBeenInitialized()) {
            SoLoaderShim.setHandler(new FrescoModule$FrescoHandler(null));
            if (this.mConfig == null) {
                this.mConfig = getDefaultConfig((Context)this.getReactApplicationContext());
            }
            Fresco.initialize(this.getReactApplicationContext().getApplicationContext(), this.mConfig);
            FrescoModule.sHasBeenInitialized = true;
        }
        else if (this.mConfig != null) {
            FLog.w("React", "Fresco has already been initialized with a different config. The new Fresco configuration will be ignored!");
        }
        this.mConfig = null;
    }
}
