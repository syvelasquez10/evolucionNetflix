// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.graphics.Canvas;
import android.annotation.SuppressLint;
import java.util.Iterator;
import android.text.StaticLayout;
import android.text.Layout$Alignment;
import android.text.Layout;
import android.content.res.TypedArray;
import android.text.TextUtils$TruncateAt;
import java.util.ArrayList;
import android.util.AttributeSet;
import android.content.Context;
import java.util.List;
import java.util.regex.Pattern;
import android.widget.TextView;

public class EllipsizingTextView extends TextView
{
    private static final Pattern DEFAULT_END_PUNCTUATION;
    private static final String ELLIPSIS = "\u2026";
    private final List<EllipsizingTextView$EllipsizeListener> ellipsizeListeners;
    private Pattern endPunctuationPattern;
    private String fullText;
    private boolean isEllipsized;
    private boolean isStale;
    private float lineAdditionalVerticalPadding;
    private float lineSpacingMultiplier;
    private int maxLines;
    private boolean programmaticChange;
    
    static {
        DEFAULT_END_PUNCTUATION = Pattern.compile("[\\.,\u2026;\\:\\s]*$", 32);
    }
    
    public EllipsizingTextView(final Context context) {
        this(context, null);
    }
    
    public EllipsizingTextView(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public EllipsizingTextView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.ellipsizeListeners = new ArrayList<EllipsizingTextView$EllipsizeListener>();
        this.lineSpacingMultiplier = 1.0f;
        this.lineAdditionalVerticalPadding = 0.0f;
        super.setEllipsize((TextUtils$TruncateAt)null);
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, new int[] { 16843091 });
        this.setMaxLines(obtainStyledAttributes.getInt(0, Integer.MAX_VALUE));
        obtainStyledAttributes.recycle();
        this.setEndPunctuationPattern(EllipsizingTextView.DEFAULT_END_PUNCTUATION);
    }
    
    private Layout createWorkingLayout(final String s) {
        return (Layout)new StaticLayout((CharSequence)s, this.getPaint(), this.getWidth() - this.getPaddingLeft() - this.getPaddingRight(), Layout$Alignment.ALIGN_NORMAL, this.lineSpacingMultiplier, this.lineAdditionalVerticalPadding, false);
    }
    
    private int getFullyVisibleLinesCount() {
        return (this.getHeight() - this.getPaddingTop() - this.getPaddingBottom()) / this.createWorkingLayout("").getLineBottom(0);
    }
    
    private int getLinesCount() {
        if (this.ellipsizingLastFullyVisibleLine()) {
            int fullyVisibleLinesCount;
            if ((fullyVisibleLinesCount = this.getFullyVisibleLinesCount()) == -1) {
                fullyVisibleLinesCount = 1;
            }
            return fullyVisibleLinesCount;
        }
        return this.maxLines;
    }
    
    private void resetText() {
        String text = this.fullText;
        final Layout workingLayout = this.createWorkingLayout(text);
        final int linesCount = this.getLinesCount();
        while (true) {
            Label_0219: {
                boolean isEllipsized = false;
                Label_0133: {
                    if (workingLayout.getLineCount() <= linesCount) {
                        isEllipsized = false;
                        break Label_0133;
                    }
                    final String trim = this.fullText.substring(0, workingLayout.getLineEnd(linesCount - 1)).trim();
                    if (this.createWorkingLayout(trim + "\u2026").getLineCount() > linesCount) {
                        final int lastIndex = trim.lastIndexOf(32);
                        if (lastIndex != -1) {
                            break Label_0219;
                        }
                    }
                    text = this.endPunctuationPattern.matcher(trim).replaceFirst("") + "\u2026";
                    isEllipsized = true;
                }
                Label_0161: {
                    if (text.equals(this.getText())) {
                        break Label_0161;
                    }
                    this.programmaticChange = true;
                    try {
                        this.setText((CharSequence)text);
                        this.programmaticChange = false;
                        this.isStale = false;
                        if (isEllipsized != this.isEllipsized) {
                            this.isEllipsized = isEllipsized;
                            final Iterator<EllipsizingTextView$EllipsizeListener> iterator = this.ellipsizeListeners.iterator();
                            while (iterator.hasNext()) {
                                iterator.next().ellipsizeStateChanged(isEllipsized);
                            }
                            return;
                        }
                        return;
                        final String trim;
                        final int lastIndex;
                        trim.substring(0, lastIndex);
                        continue;
                    }
                    finally {
                        this.programmaticChange = false;
                    }
                }
            }
            break;
        }
    }
    
    public void addEllipsizeListener(final EllipsizingTextView$EllipsizeListener ellipsizingTextView$EllipsizeListener) {
        if (ellipsizingTextView$EllipsizeListener == null) {
            throw new NullPointerException();
        }
        this.ellipsizeListeners.add(ellipsizingTextView$EllipsizeListener);
    }
    
    public boolean ellipsizingLastFullyVisibleLine() {
        return this.maxLines == Integer.MAX_VALUE;
    }
    
    @SuppressLint({ "Override" })
    public int getMaxLines() {
        return this.maxLines;
    }
    
    public boolean isEllipsized() {
        return this.isEllipsized;
    }
    
    protected void onDraw(final Canvas canvas) {
        if (this.isStale) {
            this.resetText();
        }
        super.onDraw(canvas);
    }
    
    protected void onSizeChanged(final int n, final int n2, final int n3, final int n4) {
        super.onSizeChanged(n, n2, n3, n4);
        if (this.ellipsizingLastFullyVisibleLine()) {
            this.isStale = true;
        }
    }
    
    protected void onTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
        super.onTextChanged(charSequence, n, n2, n3);
        if (!this.programmaticChange) {
            this.fullText = charSequence.toString();
            this.isStale = true;
        }
    }
    
    public void removeEllipsizeListener(final EllipsizingTextView$EllipsizeListener ellipsizingTextView$EllipsizeListener) {
        this.ellipsizeListeners.remove(ellipsizingTextView$EllipsizeListener);
    }
    
    public void setEllipsize(final TextUtils$TruncateAt textUtils$TruncateAt) {
    }
    
    public void setEndPunctuationPattern(final Pattern endPunctuationPattern) {
        this.endPunctuationPattern = endPunctuationPattern;
    }
    
    public void setLineSpacing(final float lineAdditionalVerticalPadding, final float lineSpacingMultiplier) {
        super.setLineSpacing(this.lineAdditionalVerticalPadding = lineAdditionalVerticalPadding, this.lineSpacingMultiplier = lineSpacingMultiplier);
    }
    
    public void setMaxLines(final int n) {
        super.setMaxLines(n);
        this.maxLines = n;
        this.isStale = true;
    }
    
    public void setPadding(final int n, final int n2, final int n3, final int n4) {
        super.setPadding(n, n2, n3, n4);
        if (this.ellipsizingLastFullyVisibleLine()) {
            this.isStale = true;
        }
    }
}
