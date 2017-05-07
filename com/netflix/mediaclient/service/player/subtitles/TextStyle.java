// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles;

import com.netflix.mediaclient.util.SubtitleUtils;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import org.w3c.dom.Element;
import com.netflix.mediaclient.service.webclient.model.leafs.SubtitlePreference;

public class TextStyle
{
    public static final String BACKGROUND_COLOR = "tts:backgroundColor";
    public static final String COLOR = "tts:color";
    public static final String DISPLAY_ALIGN = "tts:displayAlign";
    public static final String EXTENT = "tts:extent";
    public static final String FONT_DECORATION = "tts:textDecoration";
    public static final String FONT_FAMILY = "tts:fontFamily";
    public static final String FONT_SIZE = "tts:fontSize";
    public static final String FONT_STYLE = "tts:fontStyle";
    public static final String FONT_WEIGHT = "tts:fontWeight";
    public static final String ID = "xml:id";
    private static final int MAX_FONT_SIZE = 200;
    private static final int MIN_FONT_SIZE = 25;
    public static final String OPACITY = "tts:opacity";
    public static final String ORIGIN = "tts:origin";
    public static final String PARENT_STYLE_ID = "style";
    private static final String TAG = "nf_subtitles";
    public static final String TEXT_ALIGN = "tts:textAlign";
    public static final String TEXT_OUTLINE = "tts:textOutline";
    public static final String WINDOW_COLOR = "tts:windowColor";
    private String mBackgroundColor;
    private Float mBackgroundOpacity;
    private CellResolution mCellResolution;
    private String mColor;
    private DoubleLength mExtent;
    private FontFamilyMapping mFontFamily;
    private Integer mFontSize;
    private FontWeight mFontWeight;
    private HorizontalAlignment mHorizontalAlignment;
    private String mId;
    private Boolean mItalic;
    private Float mOpacity;
    private DoubleLength mOrigin;
    private Outline mOutline;
    private String mParentStyleId;
    private Boolean mUnderline;
    private VerticalAlignment mVerticalAlignment;
    private String mWindowColor;
    private Float mWindowOpacity;
    
    public TextStyle() {
    }
    
    public TextStyle(final TextStyle textStyle) {
        if (textStyle == null) {
            throw new IllegalArgumentException("Copy constructor must have object to work with, Null passed!");
        }
        this.mId = textStyle.mId;
        this.mParentStyleId = textStyle.mParentStyleId;
        this.mColor = textStyle.mColor;
        this.mWindowColor = textStyle.mWindowColor;
        this.mBackgroundColor = textStyle.mBackgroundColor;
        this.mFontSize = textStyle.mFontSize;
        this.mOutline = textStyle.mOutline;
        this.mFontFamily = textStyle.mFontFamily;
        this.mItalic = textStyle.mItalic;
        this.mUnderline = textStyle.mUnderline;
        this.mOpacity = textStyle.mOpacity;
        this.mWindowOpacity = textStyle.mWindowOpacity;
        this.mBackgroundOpacity = textStyle.mBackgroundOpacity;
        this.mFontWeight = textStyle.mFontWeight;
    }
    
    public TextStyle(final String mId, final String mColor, final String mWindowColor, final String mBackgroundColor, final Integer mFontSize, final Outline mOutline, final FontFamilyMapping mFontFamily, final Boolean mItalic, final Boolean mUnderline, final FontWeight mFontWeight, final Float mOpacity, final Float mWindowOpacity, final Float mBackgroundOpacity) {
        this.mId = mId;
        this.mColor = mColor;
        this.mWindowColor = mWindowColor;
        this.mBackgroundColor = mBackgroundColor;
        this.mFontSize = mFontSize;
        this.mOutline = mOutline;
        this.mFontFamily = mFontFamily;
        this.mItalic = mItalic;
        this.mFontWeight = mFontWeight;
        this.mUnderline = mUnderline;
        this.mOpacity = mOpacity;
        this.mWindowOpacity = mWindowOpacity;
        this.mBackgroundOpacity = mBackgroundOpacity;
    }
    
    public static TextStyle buildSubtitleDefaults(final SubtitlePreference subtitlePreference) {
        return buildSubtitleSettings(subtitlePreference);
    }
    
    public static TextStyle buildSubtitleSettings(final SubtitlePreference subtitlePreference) {
        TextStyle textStyle = null;
        if (subtitlePreference != null && (subtitlePreference.getCharEdgeAttrs() != null || subtitlePreference.getCharEdgeColor() != null || subtitlePreference.getCharColor() != null || subtitlePreference.getWindowColor() != null || subtitlePreference.getBackgroundColor() != null || subtitlePreference.getCharStyle() != null || subtitlePreference.getCharSize() != null || subtitlePreference.getCharOpacity() != null || subtitlePreference.getWindowOpacity() != null || subtitlePreference.getBackgroundOpacity() != null)) {
            final TextStyle textStyle2 = new TextStyle();
            if (subtitlePreference.getCharEdgeAttrs() != null || subtitlePreference.getCharEdgeColor() != null) {
                final Outline defaultOutline = Outline.getDefaultOutline();
                if (subtitlePreference.getCharEdgeAttrs() != null) {
                    defaultOutline.setEdgeType(CharacterEdgeTypeMapping.valueOf(subtitlePreference.getCharEdgeAttrs()));
                }
                if (subtitlePreference.getCharEdgeColor() != null) {
                    final ColorMapping lookup = ColorMapping.lookup(subtitlePreference.getCharEdgeColor());
                    if (lookup != null) {
                        defaultOutline.setEdgeColor(lookup.getColorStringValue());
                    }
                }
                textStyle2.mOutline = defaultOutline;
            }
            if (subtitlePreference.getCharColor() != null) {
                final ColorMapping lookup2 = ColorMapping.lookup(subtitlePreference.getCharColor());
                if (lookup2 != null) {
                    textStyle2.mColor = lookup2.getColorStringValue();
                }
            }
            if (subtitlePreference.getWindowColor() != null) {
                final ColorMapping lookup3 = ColorMapping.lookup(subtitlePreference.getWindowColor());
                if (lookup3 != null) {
                    textStyle2.mWindowColor = lookup3.getColorStringValue();
                }
            }
            if (subtitlePreference.getBackgroundColor() != null) {
                final ColorMapping lookup4 = ColorMapping.lookup(subtitlePreference.getBackgroundColor());
                if (lookup4 != null) {
                    textStyle2.mBackgroundColor = lookup4.getColorStringValue();
                }
            }
            if (subtitlePreference.getCharStyle() != null) {
                textStyle2.mFontFamily = FontFamilyMapping.lookup(subtitlePreference.getCharStyle());
            }
            if (subtitlePreference.getCharSize() != null) {
                textStyle2.mFontSize = SizeMapping.lookup(subtitlePreference.getCharSize());
            }
            if (subtitlePreference.getCharOpacity() != null) {
                textStyle2.mOpacity = OpacityMapping.lookup(subtitlePreference.getCharOpacity());
            }
            if (subtitlePreference.getWindowOpacity() != null) {
                textStyle2.mWindowOpacity = OpacityMapping.lookup(subtitlePreference.getWindowOpacity());
            }
            textStyle = textStyle2;
            if (subtitlePreference.getBackgroundOpacity() != null) {
                textStyle2.mBackgroundOpacity = OpacityMapping.lookup(subtitlePreference.getBackgroundOpacity());
                return textStyle2;
            }
        }
        return textStyle;
    }
    
    public static TextStyle createInstanceFromContainer(final Element element, final SubtitleParser subtitleParser, final TextStyle textStyle) {
        if (element == null) {
            throw new IllegalArgumentException("Element can not be null!");
        }
        if (subtitleParser == null) {
            throw new IllegalArgumentException("Parser can not be null!");
        }
        final TextStyle textStyle2 = new TextStyle();
        textStyle2.populate(element);
        textStyle2.mCellResolution = subtitleParser.getCellResolution();
        if (textStyle2.mParentStyleId != null) {
            if (Log.isLoggable()) {
                Log.d("nf_subtitles", "Parent style found. ID: " + textStyle2.mParentStyleId);
            }
            final TextStyle namedStyle = subtitleParser.getNamedStyle(textStyle2.mParentStyleId);
            if (namedStyle != null) {
                if (Log.isLoggable()) {
                    Log.d("nf_subtitles", "Parent style found, merge: " + textStyle2.mParentStyleId);
                }
                textStyle2.merge(namedStyle);
            }
            else {
                Log.w("nf_subtitles", "Parent style NOT found!");
            }
        }
        if (textStyle != null) {
            Log.v("nf_subtitles", "Apply default style");
            textStyle2.merge(textStyle);
        }
        if (Log.isLoggable()) {
            Log.v("nf_subtitles", "Style created: " + textStyle2);
        }
        return textStyle2;
    }
    
    static FontWeight getFontWeight(final String s) {
        return FontWeight.createFontWeight(s);
    }
    
    static Boolean getStyleItalic(final String s) {
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        return "italic".equalsIgnoreCase(s);
    }
    
    static Boolean isStyleUnderline(final String s) {
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        return "underline".equalsIgnoreCase(s);
    }
    
    private boolean populate(final Element element) {
        final boolean b = true;
        boolean b2 = false;
        final String attribute = element.getAttribute("xml:id");
        if (!StringUtils.isEmpty(attribute)) {
            this.mId = attribute;
            b2 = true;
        }
        final String attribute2 = element.getAttribute("style");
        if (!StringUtils.isEmpty(attribute2)) {
            this.mParentStyleId = attribute2;
            b2 = true;
        }
        this.mColor = ColorMapping.findColor(element.getAttribute("tts:color"));
        if (this.mColor != null) {
            b2 = true;
        }
        this.mBackgroundColor = ColorMapping.findColor(element.getAttribute("tts:backgroundColor"));
        if (this.mBackgroundColor != null) {
            b2 = true;
        }
        this.mWindowColor = ColorMapping.findColor(element.getAttribute("tts:windowColor"));
        if (this.mWindowColor != null) {
            b2 = true;
        }
        this.mFontSize = StringUtils.safeParsePercentage(element.getAttribute("tts:fontSize"), 25, 200, true);
        if (this.mFontSize != null) {
            b2 = true;
        }
        this.mOutline = Outline.createInstance(element.getAttribute("tts:textOutline"));
        if (this.mOutline != null) {
            b2 = true;
        }
        this.mFontFamily = FontFamilyMapping.lookup(element.getAttribute("tts:fontFamily"));
        if (this.mFontFamily != null) {
            b2 = true;
        }
        this.mItalic = getStyleItalic(element.getAttribute("tts:fontStyle"));
        if (this.mItalic != null) {
            b2 = true;
        }
        this.mFontWeight = getFontWeight(element.getAttribute("tts:fontWeight"));
        boolean b3;
        if (this.mFontWeight != null) {
            b3 = true;
        }
        else {
            b3 = b2;
            if (this.mFontFamily != null) {
                this.mFontWeight = SubtitleUtils.getDefaultFontWeight(this.mFontFamily);
                b3 = b2;
            }
        }
        this.mOpacity = OpacityMapping.lookup(element.getAttribute("tts:opacity"));
        boolean b4;
        if (this.mOpacity != null) {
            b4 = b;
        }
        else {
            b4 = b3;
        }
        this.mWindowOpacity = this.mOpacity;
        this.mBackgroundOpacity = this.mOpacity;
        final String attribute3 = element.getAttribute("tts:textAlign");
        if (!StringUtils.isEmpty(attribute3)) {
            this.mHorizontalAlignment = HorizontalAlignment.from(attribute3);
        }
        final String attribute4 = element.getAttribute("tts:displayAlign");
        if (!StringUtils.isEmpty(attribute4)) {
            this.mVerticalAlignment = VerticalAlignment.from(attribute4);
        }
        final String attribute5 = element.getAttribute("tts:extent");
        if (!StringUtils.isEmpty(attribute5)) {
            this.mExtent = DoubleLength.createInstance(attribute5, this.mCellResolution);
        }
        final String attribute6 = element.getAttribute("tts:origin");
        if (!StringUtils.isEmpty(attribute6)) {
            this.mOrigin = DoubleLength.createInstance(attribute6, this.mCellResolution);
        }
        return b4;
    }
    
    boolean addStyle(final Element element) {
        return this.populate(element);
    }
    
    public String getBackgroundColor() {
        return this.mBackgroundColor;
    }
    
    public Float getBackgroundOpacity() {
        return this.mBackgroundOpacity;
    }
    
    public String getColor() {
        return this.mColor;
    }
    
    public DoubleLength getExtent() {
        return this.mExtent;
    }
    
    public FontFamilyMapping getFontFamily() {
        return this.mFontFamily;
    }
    
    public Integer getFontSize() {
        return this.mFontSize;
    }
    
    public FontWeight getFontWeight() {
        return this.mFontWeight;
    }
    
    public HorizontalAlignment getHorizontalAlignment() {
        return this.mHorizontalAlignment;
    }
    
    public String getId() {
        return this.mId;
    }
    
    public Boolean getItalic() {
        return this.mItalic;
    }
    
    public Float getOpacity() {
        return this.mOpacity;
    }
    
    public DoubleLength getOrigin() {
        return this.mOrigin;
    }
    
    public Outline getOutline() {
        return this.mOutline;
    }
    
    public String getParentStyleId() {
        return this.mParentStyleId;
    }
    
    public Boolean getUnderline() {
        return this.mUnderline;
    }
    
    public VerticalAlignment getVerticalAlignment() {
        return this.mVerticalAlignment;
    }
    
    public String getWindowColor() {
        return this.mWindowColor;
    }
    
    public Float getWindowOpacity() {
        return this.mWindowOpacity;
    }
    
    public void merge(final TextStyle textStyle) {
        if (textStyle != null) {
            if (this.mColor == null && textStyle.mColor != null) {
                if (Log.isLoggable()) {
                    Log.d("nf_subtitles", "Use parent color " + textStyle.mColor);
                }
                this.mColor = textStyle.mColor;
            }
            if (this.mWindowColor == null && textStyle.mWindowColor != null) {
                this.mWindowColor = textStyle.mWindowColor;
            }
            if (this.mBackgroundColor == null && textStyle.mBackgroundColor != null) {
                this.mBackgroundColor = textStyle.mBackgroundColor;
            }
            if (this.mFontSize == null && textStyle.mFontSize != null) {
                if (Log.isLoggable()) {
                    Log.d("nf_subtitles", "Use parent mFontSize " + textStyle.mFontSize);
                }
                this.mFontSize = textStyle.mFontSize;
            }
            if (this.mOutline == null && textStyle.mOutline != null) {
                this.mOutline = textStyle.mOutline;
            }
            if (this.mFontFamily == null && textStyle.mFontFamily != null) {
                this.mFontFamily = textStyle.mFontFamily;
            }
            if (this.mItalic == null && textStyle.mItalic != null) {
                this.mItalic = textStyle.mItalic;
            }
            if (this.mUnderline == null && textStyle.mUnderline != null) {
                this.mUnderline = textStyle.mUnderline;
            }
            if (this.mFontWeight == null && textStyle.mFontWeight != null) {
                this.mFontWeight = textStyle.mFontWeight;
            }
            if (this.mOpacity == null && textStyle.mOpacity != null) {
                this.mOpacity = textStyle.mOpacity;
            }
            if (this.mWindowOpacity == null && textStyle.mWindowOpacity != null) {
                this.mWindowOpacity = textStyle.mWindowOpacity;
            }
            if (this.mBackgroundOpacity == null && textStyle.mBackgroundOpacity != null) {
                this.mBackgroundOpacity = textStyle.mBackgroundOpacity;
            }
        }
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TextStyle [");
        if (this.mId != null) {
            sb.append(" id=").append(this.mId);
        }
        if (this.mParentStyleId != null) {
            sb.append(", ParentStyleId=").append(this.mParentStyleId);
        }
        if (this.mColor != null) {
            sb.append(", Color=").append(this.mColor);
        }
        if (this.mWindowColor != null) {
            sb.append(", WindowColor=").append(this.mWindowColor);
        }
        if (this.mBackgroundColor != null) {
            sb.append(", BackgroundColor=").append(this.mBackgroundColor);
        }
        if (this.mFontSize != null) {
            sb.append(", FontSize=").append(this.mFontSize);
        }
        if (this.mFontFamily != null) {
            sb.append(", FontFamily=").append(this.mFontFamily);
        }
        if (this.mOutline != null) {
            sb.append(", Outline=").append(this.mOutline);
        }
        if (this.mItalic != null) {
            sb.append(", Italic=").append(this.mItalic);
        }
        if (this.mUnderline != null) {
            sb.append(", Underline=").append(this.mUnderline);
        }
        if (this.mFontWeight != null) {
            sb.append(", Font weight=").append(this.mFontWeight);
        }
        if (this.mOpacity != null) {
            sb.append(", Opacity=").append(this.mOpacity);
        }
        if (this.mWindowOpacity != null) {
            sb.append(", WindowOpacity=").append(this.mWindowOpacity);
        }
        if (this.mBackgroundOpacity != null) {
            sb.append(", BackgroundOpacity=").append(this.mBackgroundOpacity);
        }
        if (this.mOrigin != null) {
            sb.append(", Origin=").append(this.mOrigin);
        }
        if (this.mExtent != null) {
            sb.append(", Extent=").append(this.mExtent);
        }
        if (this.mHorizontalAlignment != null) {
            sb.append(", HorizontalAlignment=").append(this.mHorizontalAlignment);
        }
        if (this.mVerticalAlignment != null) {
            sb.append(", VerticalAlignment=").append(this.mVerticalAlignment);
        }
        sb.append("]");
        return sb.toString();
    }
}
