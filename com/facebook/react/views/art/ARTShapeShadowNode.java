// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.art;

import android.graphics.PathEffect;
import android.graphics.DashPathEffect;
import android.graphics.Paint$Join;
import android.graphics.Paint$Cap;
import com.facebook.common.logging.FLog;
import android.graphics.Paint$Style;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.bridge.ReadableArray;
import android.graphics.Paint;
import android.graphics.Canvas;
import android.graphics.RectF;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import android.graphics.Path;

public class ARTShapeShadowNode extends ARTVirtualNode
{
    private float[] mFillColor;
    protected Path mPath;
    private int mStrokeCap;
    private float[] mStrokeColor;
    private float[] mStrokeDash;
    private int mStrokeJoin;
    private float mStrokeWidth;
    
    public ARTShapeShadowNode() {
        this.mStrokeWidth = 1.0f;
        this.mStrokeCap = 1;
        this.mStrokeJoin = 1;
    }
    
    private Path createPath(final float[] array) {
        final Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        int i = 0;
        while (i < array.length) {
            final int n = i + 1;
            final int n2 = (int)array[i];
            switch (n2) {
                default: {
                    throw new JSApplicationIllegalArgumentException("Unrecognized drawing instruction " + n2);
                }
                case 0: {
                    final int n3 = n + 1;
                    final float n4 = array[n];
                    final float mScale = this.mScale;
                    i = n3 + 1;
                    path.moveTo(mScale * n4, array[n3] * this.mScale);
                    continue;
                }
                case 1: {
                    path.close();
                    i = n;
                    continue;
                }
                case 2: {
                    final int n5 = n + 1;
                    final float n6 = array[n];
                    final float mScale2 = this.mScale;
                    i = n5 + 1;
                    path.lineTo(mScale2 * n6, array[n5] * this.mScale);
                    continue;
                }
                case 3: {
                    final int n7 = n + 1;
                    final float n8 = array[n];
                    final float mScale3 = this.mScale;
                    final int n9 = n7 + 1;
                    final float n10 = array[n7];
                    final float mScale4 = this.mScale;
                    final int n11 = n9 + 1;
                    final float n12 = array[n9];
                    final float mScale5 = this.mScale;
                    final int n13 = n11 + 1;
                    final float n14 = array[n11];
                    final float mScale6 = this.mScale;
                    final int n15 = n13 + 1;
                    path.cubicTo(n8 * mScale3, n10 * mScale4, n12 * mScale5, n14 * mScale6, array[n13] * this.mScale, array[n15] * this.mScale);
                    i = n15 + 1;
                    continue;
                }
                case 4: {
                    final int n16 = n + 1;
                    final float n17 = array[n] * this.mScale;
                    final int n18 = n16 + 1;
                    final float n19 = array[n16] * this.mScale;
                    final int n20 = n18 + 1;
                    final float n21 = array[n18] * this.mScale;
                    final int n22 = n20 + 1;
                    final float n23 = (float)Math.toDegrees(array[n20]);
                    final int n24 = n22 + 1;
                    final float n25 = (float)Math.toDegrees(array[n22]);
                    int n26;
                    if (array[n24] == 0.0f) {
                        n26 = 1;
                    }
                    else {
                        n26 = 0;
                    }
                    float n27 = n25;
                    if (n26 == 0) {
                        n27 = 360.0f - n25;
                    }
                    path.addArc(new RectF(n17 - n21, n19 - n21, n17 + n21, n19 + n21), n23, n23 - n27);
                    i = n24 + 1;
                    continue;
                }
            }
        }
        return path;
    }
    
    @Override
    public void draw(final Canvas canvas, final Paint paint, float n) {
        n *= this.mOpacity;
        if (n > 0.01f) {
            this.saveAndSetupCanvas(canvas);
            if (this.mPath == null) {
                throw new JSApplicationIllegalArgumentException("Shapes should have a valid path (d) prop");
            }
            if (this.setupFillPaint(paint, n)) {
                canvas.drawPath(this.mPath, paint);
            }
            if (this.setupStrokePaint(paint, n)) {
                canvas.drawPath(this.mPath, paint);
            }
            this.restoreCanvas(canvas);
        }
        this.markUpdateSeen();
    }
    
    @ReactProp(name = "fill")
    public void setFill(final ReadableArray readableArray) {
        this.mFillColor = PropHelper.toFloatArray(readableArray);
        this.markUpdated();
    }
    
    @ReactProp(name = "d")
    public void setShapePath(final ReadableArray readableArray) {
        this.mPath = this.createPath(PropHelper.toFloatArray(readableArray));
        this.markUpdated();
    }
    
    @ReactProp(name = "stroke")
    public void setStroke(final ReadableArray readableArray) {
        this.mStrokeColor = PropHelper.toFloatArray(readableArray);
        this.markUpdated();
    }
    
    @ReactProp(defaultInt = 1, name = "strokeCap")
    public void setStrokeCap(final int mStrokeCap) {
        this.mStrokeCap = mStrokeCap;
        this.markUpdated();
    }
    
    @ReactProp(name = "strokeDash")
    public void setStrokeDash(final ReadableArray readableArray) {
        this.mStrokeDash = PropHelper.toFloatArray(readableArray);
        this.markUpdated();
    }
    
    @ReactProp(defaultInt = 1, name = "strokeJoin")
    public void setStrokeJoin(final int mStrokeJoin) {
        this.mStrokeJoin = mStrokeJoin;
        this.markUpdated();
    }
    
    @ReactProp(defaultFloat = 1.0f, name = "strokeWidth")
    public void setStrokeWidth(final float mStrokeWidth) {
        this.mStrokeWidth = mStrokeWidth;
        this.markUpdated();
    }
    
    protected boolean setupFillPaint(final Paint paint, float n) {
        boolean b = false;
        if (this.mFillColor != null) {
            b = b;
            if (this.mFillColor.length > 0) {
                paint.reset();
                paint.setFlags(1);
                paint.setStyle(Paint$Style.FILL);
                final int n2 = (int)this.mFillColor[0];
                switch (n2) {
                    default: {
                        FLog.w("React", "ART: Color type " + n2 + " not supported!");
                        break;
                    }
                    case 0: {
                        if (this.mFillColor.length > 4) {
                            n = this.mFillColor[4] * n * 255.0f;
                        }
                        else {
                            n *= 255.0f;
                        }
                        paint.setARGB((int)n, (int)(this.mFillColor[1] * 255.0f), (int)(this.mFillColor[2] * 255.0f), (int)(this.mFillColor[3] * 255.0f));
                        break;
                    }
                }
                b = true;
            }
        }
        return b;
    }
    
    protected boolean setupStrokePaint(final Paint paint, float n) {
        if (this.mStrokeWidth == 0.0f || this.mStrokeColor == null || this.mStrokeColor.length == 0) {
            return false;
        }
        paint.reset();
        paint.setFlags(1);
        paint.setStyle(Paint$Style.STROKE);
        switch (this.mStrokeCap) {
            default: {
                throw new JSApplicationIllegalArgumentException("strokeCap " + this.mStrokeCap + " unrecognized");
            }
            case 0: {
                paint.setStrokeCap(Paint$Cap.BUTT);
                break;
            }
            case 2: {
                paint.setStrokeCap(Paint$Cap.SQUARE);
                break;
            }
            case 1: {
                paint.setStrokeCap(Paint$Cap.ROUND);
                break;
            }
        }
        switch (this.mStrokeJoin) {
            default: {
                throw new JSApplicationIllegalArgumentException("strokeJoin " + this.mStrokeJoin + " unrecognized");
            }
            case 0: {
                paint.setStrokeJoin(Paint$Join.MITER);
                break;
            }
            case 2: {
                paint.setStrokeJoin(Paint$Join.BEVEL);
                break;
            }
            case 1: {
                paint.setStrokeJoin(Paint$Join.ROUND);
                break;
            }
        }
        paint.setStrokeWidth(this.mStrokeWidth * this.mScale);
        if (this.mStrokeColor.length > 3) {
            n = this.mStrokeColor[3] * n * 255.0f;
        }
        else {
            n *= 255.0f;
        }
        paint.setARGB((int)n, (int)(this.mStrokeColor[0] * 255.0f), (int)(this.mStrokeColor[1] * 255.0f), (int)(this.mStrokeColor[2] * 255.0f));
        if (this.mStrokeDash != null && this.mStrokeDash.length > 0) {
            paint.setPathEffect((PathEffect)new DashPathEffect(this.mStrokeDash, 0.0f));
        }
        return true;
    }
}
