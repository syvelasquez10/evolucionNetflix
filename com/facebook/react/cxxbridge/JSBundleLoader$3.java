// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.cxxbridge;

import com.facebook.react.devsupport.DebugServerException;

final class JSBundleLoader$3 extends JSBundleLoader
{
    final /* synthetic */ String val$cachedFileLocation;
    final /* synthetic */ String val$sourceURL;
    
    JSBundleLoader$3(final String val$cachedFileLocation, final String val$sourceURL) {
        this.val$cachedFileLocation = val$cachedFileLocation;
        this.val$sourceURL = val$sourceURL;
    }
    
    @Override
    public String loadScript(final CatalystInstanceImpl catalystInstanceImpl) {
        try {
            catalystInstanceImpl.loadScriptFromFile(this.val$cachedFileLocation, this.val$sourceURL);
            return this.val$sourceURL;
        }
        catch (Exception ex) {
            throw DebugServerException.makeGeneric(ex.getMessage(), ex);
        }
    }
}
