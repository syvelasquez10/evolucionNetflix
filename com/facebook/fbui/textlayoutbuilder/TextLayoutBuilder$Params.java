// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.fbui.textlayoutbuilder;

import android.graphics.Paint;
import android.support.v4.text.TextDirectionHeuristicsCompat;
import android.support.v4.text.TextDirectionHeuristicCompat;
import android.text.TextPaint;
import android.text.TextUtils$TruncateAt;
import android.text.Layout$Alignment;

class TextLayoutBuilder$Params
{
    Layout$Alignment alignment;
    TextUtils$TruncateAt ellipsize;
    boolean includePadding;
    boolean mForceNewPaint;
    int maxLines;
    int measureMode;
    TextPaint paint;
    boolean singleLine;
    float spacingAdd;
    float spacingMult;
    CharSequence text;
    TextDirectionHeuristicCompat textDirection;
    int width;
    
    TextLayoutBuilder$Params() {
        this.paint = new TextLayoutBuilder$ComparableTextPaint(1);
        this.spacingMult = 1.0f;
        this.spacingAdd = 0.0f;
        this.includePadding = true;
        this.ellipsize = null;
        this.singleLine = false;
        this.maxLines = Integer.MAX_VALUE;
        this.alignment = Layout$Alignment.ALIGN_NORMAL;
        this.textDirection = TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR;
        this.mForceNewPaint = false;
    }
    
    void createNewPaintIfNeeded() {
        if (this.mForceNewPaint) {
            this.paint = new TextLayoutBuilder$ComparableTextPaint((Paint)this.paint);
            this.mForceNewPaint = false;
        }
    }
    
    @Override
    public int hashCode() {
        int n = 1;
        int hashCode = 0;
        int hashCode2;
        if (this.paint != null) {
            hashCode2 = this.paint.hashCode();
        }
        else {
            hashCode2 = 0;
        }
        final int width = this.width;
        final int measureMode = this.measureMode;
        final int floatToIntBits = Float.floatToIntBits(this.spacingMult);
        final int floatToIntBits2 = Float.floatToIntBits(this.spacingAdd);
        int n2;
        if (this.includePadding) {
            n2 = 1;
        }
        else {
            n2 = 0;
        }
        int hashCode3;
        if (this.ellipsize != null) {
            hashCode3 = this.ellipsize.hashCode();
        }
        else {
            hashCode3 = 0;
        }
        if (!this.singleLine) {
            n = 0;
        }
        final int maxLines = this.maxLines;
        int hashCode4;
        if (this.alignment != null) {
            hashCode4 = this.alignment.hashCode();
        }
        else {
            hashCode4 = 0;
        }
        int hashCode5;
        if (this.textDirection != null) {
            hashCode5 = this.textDirection.hashCode();
        }
        else {
            hashCode5 = 0;
        }
        if (this.text != null) {
            hashCode = this.text.hashCode();
        }
        return (hashCode5 + (hashCode4 + (((hashCode3 + (n2 + (((((hashCode2 + 31) * 31 + width) * 31 + measureMode) * 31 + floatToIntBits) * 31 + floatToIntBits2) * 31) * 31) * 31 + n) * 31 + maxLines) * 31) * 31) * 31 + hashCode;
    }
}
