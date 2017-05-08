// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.cxxbridge;

import android.content.Context;

final class JSBundleLoader$1 extends JSBundleLoader
{
    final /* synthetic */ String val$assetUrl;
    final /* synthetic */ Context val$context;
    
    JSBundleLoader$1(final Context val$context, final String val$assetUrl) {
        this.val$context = val$context;
        this.val$assetUrl = val$assetUrl;
    }
    
    @Override
    public String getSourceUrl() {
        return this.val$assetUrl;
    }
    
    @Override
    public void loadScript(final CatalystInstanceImpl catalystInstanceImpl) {
        catalystInstanceImpl.loadScriptFromAssets(this.val$context.getAssets(), this.val$assetUrl);
    }
}
