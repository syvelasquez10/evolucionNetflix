// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.cxxbridge;

final class JSBundleLoader$2 extends JSBundleLoader
{
    final /* synthetic */ String val$fileName;
    
    JSBundleLoader$2(final String val$fileName) {
        this.val$fileName = val$fileName;
    }
    
    @Override
    public String loadScript(final CatalystInstanceImpl catalystInstanceImpl) {
        catalystInstanceImpl.loadScriptFromFile(this.val$fileName, this.val$fileName);
        return this.val$fileName;
    }
}
