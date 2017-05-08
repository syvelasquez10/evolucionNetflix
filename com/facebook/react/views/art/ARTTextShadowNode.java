// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.art;

import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.bridge.ReadableArray;
import android.text.TextUtils;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.graphics.Paint$Align;
import android.graphics.Paint;
import com.facebook.react.bridge.ReadableMap;

public class ARTTextShadowNode extends ARTShapeShadowNode
{
    private ReadableMap mFrame;
    private int mTextAlignment;
    
    public ARTTextShadowNode() {
        this.mTextAlignment = 0;
    }
    
    private void applyTextPropertiesToPaint(final Paint paint) {
        int n = 1;
        switch (this.mTextAlignment) {
            case 0: {
                paint.setTextAlign(Paint$Align.LEFT);
                break;
            }
            case 1: {
                paint.setTextAlign(Paint$Align.RIGHT);
                break;
            }
            case 2: {
                paint.setTextAlign(Paint$Align.CENTER);
                break;
            }
        }
        if (this.mFrame != null && this.mFrame.hasKey("font")) {
            final ReadableMap map = this.mFrame.getMap("font");
            if (map != null) {
                float n2 = 12.0f;
                if (map.hasKey("fontSize")) {
                    n2 = (float)map.getDouble("fontSize");
                }
                paint.setTextSize(n2 * this.mScale);
                boolean b;
                if (map.hasKey("fontWeight") && "bold".equals(map.getString("fontWeight"))) {
                    b = true;
                }
                else {
                    b = false;
                }
                boolean b2;
                if (map.hasKey("fontStyle") && "italic".equals(map.getString("fontStyle"))) {
                    b2 = true;
                }
                else {
                    b2 = false;
                }
                if (b && b2) {
                    n = 3;
                }
                else if (!b) {
                    if (b2) {
                        n = 2;
                    }
                    else {
                        n = 0;
                    }
                }
                paint.setTypeface(Typeface.create(map.getString("fontFamily"), n));
            }
        }
    }
    
    @Override
    public void draw(final Canvas canvas, final Paint paint, float n) {
        if (this.mFrame != null) {
            n *= this.mOpacity;
            if (n > 0.01f && this.mFrame.hasKey("lines")) {
                final ReadableArray array = this.mFrame.getArray("lines");
                if (array != null && array.size() != 0) {
                    this.saveAndSetupCanvas(canvas);
                    final String[] array2 = new String[array.size()];
                    for (int i = 0; i < array2.length; ++i) {
                        array2[i] = array.getString(i);
                    }
                    final String join = TextUtils.join((CharSequence)"\n", (Object[])array2);
                    if (this.setupStrokePaint(paint, n)) {
                        this.applyTextPropertiesToPaint(paint);
                        if (this.mPath == null) {
                            canvas.drawText(join, 0.0f, -paint.ascent(), paint);
                        }
                        else {
                            canvas.drawTextOnPath(join, this.mPath, 0.0f, 0.0f, paint);
                        }
                    }
                    if (this.setupFillPaint(paint, n)) {
                        this.applyTextPropertiesToPaint(paint);
                        if (this.mPath == null) {
                            canvas.drawText(join, 0.0f, -paint.ascent(), paint);
                        }
                        else {
                            canvas.drawTextOnPath(join, this.mPath, 0.0f, 0.0f, paint);
                        }
                    }
                    this.restoreCanvas(canvas);
                    this.markUpdateSeen();
                }
            }
        }
    }
    
    @ReactProp(defaultInt = 0, name = "alignment")
    public void setAlignment(final int mTextAlignment) {
        this.mTextAlignment = mTextAlignment;
    }
    
    @ReactProp(name = "frame")
    public void setFrame(final ReadableMap mFrame) {
        this.mFrame = mFrame;
    }
}
