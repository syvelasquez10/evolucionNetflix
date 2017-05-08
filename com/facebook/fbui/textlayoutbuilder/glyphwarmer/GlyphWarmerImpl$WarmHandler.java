// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.fbui.textlayoutbuilder.glyphwarmer;

import com.facebook.fbui.textlayoutbuilder.util.LayoutMeasureUtil;
import android.text.Layout;
import android.os.Message;
import android.os.Looper;
import android.graphics.Picture;
import android.os.Handler;

class GlyphWarmerImpl$WarmHandler extends Handler
{
    private final Picture mPicture;
    
    public GlyphWarmerImpl$WarmHandler(final Looper looper) {
        super(looper);
        this.mPicture = new Picture();
    }
    
    public void handleMessage(final Message message) {
        final Layout layout = (Layout)message.obj;
        try {
            layout.draw(this.mPicture.beginRecording(LayoutMeasureUtil.getWidth(layout), LayoutMeasureUtil.getHeight(layout)));
            this.mPicture.endRecording();
        }
        catch (Exception ex) {}
    }
}
