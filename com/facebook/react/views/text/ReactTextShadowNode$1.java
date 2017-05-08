// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.text;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.UIViewOperationQueue;
import com.facebook.yoga.YogaDirection;
import com.facebook.react.uimanager.PixelUtil;
import java.util.ArrayList;
import com.facebook.react.uimanager.ReactShadowNode;
import android.text.style.StrikethroughSpan;
import android.text.style.UnderlineSpan;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import com.facebook.react.uimanager.IllegalViewOperationException;
import java.util.List;
import android.text.SpannableStringBuilder;
import android.text.Spannable;
import com.facebook.react.uimanager.LayoutShadowNode;
import android.text.BoringLayout$Metrics;
import android.text.TextPaint;
import com.facebook.yoga.YogaMeasureOutput;
import android.text.StaticLayout$Builder;
import android.text.StaticLayout;
import android.text.Layout$Alignment;
import android.os.Build$VERSION;
import com.facebook.yoga.YogaConstants;
import android.text.Layout;
import android.text.BoringLayout;
import com.facebook.infer.annotation.Assertions;
import android.text.Spanned;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaNodeAPI;
import com.facebook.yoga.YogaMeasureFunction;

class ReactTextShadowNode$1 implements YogaMeasureFunction
{
    final /* synthetic */ ReactTextShadowNode this$0;
    
    ReactTextShadowNode$1(final ReactTextShadowNode this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public long measure(final YogaNodeAPI yogaNodeAPI, final float n, final YogaMeasureMode yogaMeasureMode, float desiredWidth, final YogaMeasureMode yogaMeasureMode2) {
        final TextPaint access$000 = ReactTextShadowNode.sTextPaintInstance;
        final Spanned spanned = Assertions.assertNotNull((Spanned)this.this$0.mPreparedSpannableText, "Spannable element has not been prepared in onBeforeLayout");
        final BoringLayout$Metrics boring = BoringLayout.isBoring((CharSequence)spanned, access$000);
        if (boring == null) {
            desiredWidth = Layout.getDesiredWidth((CharSequence)spanned, access$000);
        }
        else {
            desiredWidth = Float.NaN;
        }
        boolean b;
        if (yogaMeasureMode == YogaMeasureMode.UNDEFINED || n < 0.0f) {
            b = true;
        }
        else {
            b = false;
        }
        Object o;
        if (boring == null && (b || (!YogaConstants.isUndefined(desiredWidth) && desiredWidth <= n))) {
            final int n2 = (int)Math.ceil(desiredWidth);
            if (Build$VERSION.SDK_INT < 23) {
                o = new StaticLayout((CharSequence)spanned, access$000, n2, Layout$Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
            }
            else {
                o = StaticLayout$Builder.obtain((CharSequence)spanned, 0, spanned.length(), access$000, n2).setAlignment(Layout$Alignment.ALIGN_NORMAL).setLineSpacing(0.0f, 1.0f).setIncludePad(true).setBreakStrategy(this.this$0.mTextBreakStrategy).setHyphenationFrequency(1).build();
            }
        }
        else if (boring != null && (b || boring.width <= n)) {
            o = BoringLayout.make((CharSequence)spanned, access$000, boring.width, Layout$Alignment.ALIGN_NORMAL, 1.0f, 0.0f, boring, true);
        }
        else if (Build$VERSION.SDK_INT < 23) {
            o = new StaticLayout((CharSequence)spanned, access$000, (int)n, Layout$Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
        }
        else {
            o = StaticLayout$Builder.obtain((CharSequence)spanned, 0, spanned.length(), access$000, (int)n).setAlignment(Layout$Alignment.ALIGN_NORMAL).setLineSpacing(0.0f, 1.0f).setIncludePad(true).setBreakStrategy(this.this$0.mTextBreakStrategy).setHyphenationFrequency(1).build();
        }
        if (this.this$0.mNumberOfLines != -1 && this.this$0.mNumberOfLines < ((Layout)o).getLineCount()) {
            return YogaMeasureOutput.make(((Layout)o).getWidth(), ((Layout)o).getLineBottom(this.this$0.mNumberOfLines - 1));
        }
        return YogaMeasureOutput.make(((Layout)o).getWidth(), ((Layout)o).getHeight());
    }
}
