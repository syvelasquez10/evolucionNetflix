// 
// Decompiled by Procyon v0.5.30
// 

package android.support.graphics.drawable;

import android.content.res.Resources$Theme;
import android.util.AttributeSet;
import android.content.res.Resources;
import org.xmlpull.v1.XmlPullParser;
import android.content.res.TypedArray;
import android.graphics.Paint$Join;
import android.graphics.Paint$Cap;

class VectorDrawableCompat$VFullPath extends VectorDrawableCompat$VPath
{
    float mFillAlpha;
    int mFillColor;
    int mFillRule;
    float mStrokeAlpha;
    int mStrokeColor;
    Paint$Cap mStrokeLineCap;
    Paint$Join mStrokeLineJoin;
    float mStrokeMiterlimit;
    float mStrokeWidth;
    private int[] mThemeAttrs;
    float mTrimPathEnd;
    float mTrimPathOffset;
    float mTrimPathStart;
    
    public VectorDrawableCompat$VFullPath() {
        this.mStrokeColor = 0;
        this.mStrokeWidth = 0.0f;
        this.mFillColor = 0;
        this.mStrokeAlpha = 1.0f;
        this.mFillAlpha = 1.0f;
        this.mTrimPathStart = 0.0f;
        this.mTrimPathEnd = 1.0f;
        this.mTrimPathOffset = 0.0f;
        this.mStrokeLineCap = Paint$Cap.BUTT;
        this.mStrokeLineJoin = Paint$Join.MITER;
        this.mStrokeMiterlimit = 4.0f;
    }
    
    public VectorDrawableCompat$VFullPath(final VectorDrawableCompat$VFullPath vectorDrawableCompat$VFullPath) {
        super(vectorDrawableCompat$VFullPath);
        this.mStrokeColor = 0;
        this.mStrokeWidth = 0.0f;
        this.mFillColor = 0;
        this.mStrokeAlpha = 1.0f;
        this.mFillAlpha = 1.0f;
        this.mTrimPathStart = 0.0f;
        this.mTrimPathEnd = 1.0f;
        this.mTrimPathOffset = 0.0f;
        this.mStrokeLineCap = Paint$Cap.BUTT;
        this.mStrokeLineJoin = Paint$Join.MITER;
        this.mStrokeMiterlimit = 4.0f;
        this.mThemeAttrs = vectorDrawableCompat$VFullPath.mThemeAttrs;
        this.mStrokeColor = vectorDrawableCompat$VFullPath.mStrokeColor;
        this.mStrokeWidth = vectorDrawableCompat$VFullPath.mStrokeWidth;
        this.mStrokeAlpha = vectorDrawableCompat$VFullPath.mStrokeAlpha;
        this.mFillColor = vectorDrawableCompat$VFullPath.mFillColor;
        this.mFillRule = vectorDrawableCompat$VFullPath.mFillRule;
        this.mFillAlpha = vectorDrawableCompat$VFullPath.mFillAlpha;
        this.mTrimPathStart = vectorDrawableCompat$VFullPath.mTrimPathStart;
        this.mTrimPathEnd = vectorDrawableCompat$VFullPath.mTrimPathEnd;
        this.mTrimPathOffset = vectorDrawableCompat$VFullPath.mTrimPathOffset;
        this.mStrokeLineCap = vectorDrawableCompat$VFullPath.mStrokeLineCap;
        this.mStrokeLineJoin = vectorDrawableCompat$VFullPath.mStrokeLineJoin;
        this.mStrokeMiterlimit = vectorDrawableCompat$VFullPath.mStrokeMiterlimit;
    }
    
    private Paint$Cap getStrokeLineCap(final int n, final Paint$Cap paint$Cap) {
        switch (n) {
            default: {
                return paint$Cap;
            }
            case 0: {
                return Paint$Cap.BUTT;
            }
            case 1: {
                return Paint$Cap.ROUND;
            }
            case 2: {
                return Paint$Cap.SQUARE;
            }
        }
    }
    
    private Paint$Join getStrokeLineJoin(final int n, final Paint$Join paint$Join) {
        switch (n) {
            default: {
                return paint$Join;
            }
            case 0: {
                return Paint$Join.MITER;
            }
            case 1: {
                return Paint$Join.ROUND;
            }
            case 2: {
                return Paint$Join.BEVEL;
            }
        }
    }
    
    private void updateStateFromTypedArray(final TypedArray typedArray, final XmlPullParser xmlPullParser) {
        this.mThemeAttrs = null;
        if (!TypedArrayUtils.hasAttribute(xmlPullParser, "pathData")) {
            return;
        }
        final String string = typedArray.getString(0);
        if (string != null) {
            this.mPathName = string;
        }
        final String string2 = typedArray.getString(2);
        if (string2 != null) {
            this.mNodes = PathParser.createNodesFromPathData(string2);
        }
        this.mFillColor = TypedArrayUtils.getNamedColor(typedArray, xmlPullParser, "fillColor", 1, this.mFillColor);
        this.mFillAlpha = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "fillAlpha", 12, this.mFillAlpha);
        this.mStrokeLineCap = this.getStrokeLineCap(TypedArrayUtils.getNamedInt(typedArray, xmlPullParser, "strokeLineCap", 8, -1), this.mStrokeLineCap);
        this.mStrokeLineJoin = this.getStrokeLineJoin(TypedArrayUtils.getNamedInt(typedArray, xmlPullParser, "strokeLineJoin", 9, -1), this.mStrokeLineJoin);
        this.mStrokeMiterlimit = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "strokeMiterLimit", 10, this.mStrokeMiterlimit);
        this.mStrokeColor = TypedArrayUtils.getNamedColor(typedArray, xmlPullParser, "strokeColor", 3, this.mStrokeColor);
        this.mStrokeAlpha = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "strokeAlpha", 11, this.mStrokeAlpha);
        this.mStrokeWidth = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "strokeWidth", 4, this.mStrokeWidth);
        this.mTrimPathEnd = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "trimPathEnd", 6, this.mTrimPathEnd);
        this.mTrimPathOffset = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "trimPathOffset", 7, this.mTrimPathOffset);
        this.mTrimPathStart = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "trimPathStart", 5, this.mTrimPathStart);
    }
    
    public void inflate(final Resources resources, final AttributeSet set, final Resources$Theme resources$Theme, final XmlPullParser xmlPullParser) {
        final TypedArray obtainAttributes = VectorDrawableCommon.obtainAttributes(resources, resources$Theme, set, AndroidResources.styleable_VectorDrawablePath);
        this.updateStateFromTypedArray(obtainAttributes, xmlPullParser);
        obtainAttributes.recycle();
    }
}
