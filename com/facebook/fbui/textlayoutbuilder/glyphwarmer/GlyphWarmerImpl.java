// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.fbui.textlayoutbuilder.glyphwarmer;

import android.text.Layout;
import android.annotation.SuppressLint;
import android.os.HandlerThread;
import com.facebook.fbui.textlayoutbuilder.GlyphWarmer;

public class GlyphWarmerImpl implements GlyphWarmer
{
    private static GlyphWarmerImpl$WarmHandler sWarmHandler;
    
    @SuppressLint({ "BadMethodUse-android.os.HandlerThread._Constructor", "BadMethodUse-java.lang.Thread.start" })
    private GlyphWarmerImpl$WarmHandler getWarmHandler() {
        if (GlyphWarmerImpl.sWarmHandler == null) {
            final HandlerThread handlerThread = new HandlerThread("GlyphWarmer");
            handlerThread.start();
            GlyphWarmerImpl.sWarmHandler = new GlyphWarmerImpl$WarmHandler(handlerThread.getLooper());
        }
        return GlyphWarmerImpl.sWarmHandler;
    }
    
    @Override
    public void warmLayout(final Layout layout) {
        final GlyphWarmerImpl$WarmHandler warmHandler = this.getWarmHandler();
        warmHandler.sendMessage(warmHandler.obtainMessage(1, (Object)layout));
    }
}
