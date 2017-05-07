// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles;

import com.netflix.mediaclient.util.StringUtils;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.netflix.mediaclient.Log;
import org.w3c.dom.Element;

public class Region
{
    public static final String DEFAULT_REGION_ID = "BOTTOM_CENTER";
    public static final String DISPLAY_ALIGN = "tts:displayAlign";
    public static final String EXTENT = "tts:extent";
    public static final String ORIGIN = "tts:origin";
    private static final String TAG = "nf_subtitles";
    public static final String TEXT_ALIGN = "tts:textAlign";
    private CellResolution mCellResolution;
    private DoubleLength mExtent;
    private HorizontalAlignment mHorizontalAlignment;
    private String mId;
    private DoubleLength mOrigin;
    private TextStyle mTextStyle;
    private VerticalAlignment mVerticalAlignment;
    
    private Region(final SubtitleParser subtitleParser, final Element element, final CellResolution mCellResolution, final TextStyle textStyle) {
        this.mId = element.getAttribute("xml:id");
        this.mCellResolution = mCellResolution;
        final TextStyle instanceFromContainer = TextStyle.createInstanceFromContainer(element, subtitleParser, textStyle);
        if (Log.isLoggable("nf_subtitles", 3)) {
            Log.d("nf_subtitles", "Style defined as attribute in region " + instanceFromContainer);
        }
        if (instanceFromContainer != null) {
            if (instanceFromContainer.getHorizontalAlignment() != null) {
                Log.d("nf_subtitles", "Hor alig found as attribute " + instanceFromContainer.getHorizontalAlignment());
                this.mHorizontalAlignment = instanceFromContainer.getHorizontalAlignment();
            }
            if (instanceFromContainer.getVerticalAlignment() != null) {
                Log.d("nf_subtitles", "Vert alig found as attribute " + instanceFromContainer.getVerticalAlignment());
                this.mVerticalAlignment = instanceFromContainer.getVerticalAlignment();
            }
            if (instanceFromContainer.getExtent() != null) {
                Log.d("nf_subtitles", "Extent as attribute " + instanceFromContainer.getExtent());
                this.mExtent = instanceFromContainer.getExtent();
            }
            if (instanceFromContainer.getOrigin() != null) {
                Log.d("nf_subtitles", "Origin as attribute " + instanceFromContainer.getOrigin());
                this.mOrigin = instanceFromContainer.getOrigin();
            }
        }
        final TextStyle styles = this.parseStyles(element);
        if (Log.isLoggable("nf_subtitles", 3)) {
            Log.d("nf_subtitles", "Style defined as elements in region " + styles);
        }
        this.mTextStyle = new TextStyle();
        if (styles != null) {
            Log.d("nf_subtitles", "Region parsed styles found use them first");
            this.mTextStyle.merge(styles);
        }
        if (instanceFromContainer != null) {
            Log.d("nf_subtitles", "Region Attribute styles found use them second");
            this.mTextStyle.merge(instanceFromContainer);
        }
    }
    
    private Region(final String mId, final DoubleLength mExtent, final DoubleLength mOrigin, final HorizontalAlignment mHorizontalAlignment, final VerticalAlignment mVerticalAlignment, final CellResolution mCellResolution, final TextStyle mTextStyle) {
        this.mId = mId;
        this.mExtent = mExtent;
        this.mOrigin = mOrigin;
        this.mHorizontalAlignment = mHorizontalAlignment;
        this.mVerticalAlignment = mVerticalAlignment;
        this.mCellResolution = mCellResolution;
        this.mTextStyle = mTextStyle;
    }
    
    public static Region createInstanceOfDefaultRegion(final TextStyle textStyle) {
        return new Region("DEFAULT_REGION", DoubleLength.ZERO, DoubleLength.ZERO, HorizontalAlignment.left, VerticalAlignment.top, null, textStyle);
    }
    
    public static Region createInstanceOfSimpleSdhRegion(final TextStyle textStyle) {
        return new Region("SIMPLE_SDH", DoubleLength.SIMPLE_SDH_EXTENT, DoubleLength.ZERO, HorizontalAlignment.left, VerticalAlignment.top, null, textStyle);
    }
    
    public static Region createRegion(final SubtitleParser subtitleParser, final Element element, final CellResolution cellResolution, final TextStyle textStyle) {
        return new Region(subtitleParser, element, cellResolution, textStyle);
    }
    
    private TextStyle parseStyles(final Element element) {
        final NodeList elementsByTagName = element.getElementsByTagName("style");
        if (elementsByTagName == null || elementsByTagName.getLength() < 1) {
            Log.e("nf_subtitles", "Styles element(s) not found in region");
            return null;
        }
        if (Log.isLoggable("nf_subtitles", 3)) {
            Log.d("nf_subtitles", "Found " + elementsByTagName.getLength() + " styles for region");
        }
        final TextStyle textStyle = new TextStyle();
        for (int i = 0; i < elementsByTagName.getLength(); ++i) {
            final Node item = elementsByTagName.item(i);
            if (item instanceof Element) {
                final Element element2 = (Element)item;
                if (textStyle.addStyle(element2)) {
                    Log.d("nf_subtitles", "Style added");
                }
                else {
                    Log.d("nf_subtitles", "Style not added, check to see if it they are region attributes.");
                    this.processRegionAttributes(element2);
                }
            }
        }
        return textStyle;
    }
    
    private void processRegionAttributes(final Element element) {
        final String attribute = element.getAttribute("tts:textAlign");
        if (!StringUtils.isEmpty(attribute)) {
            if (Log.isLoggable("nf_subtitles", 3)) {
                Log.d("nf_subtitles", "Text alignment found " + attribute);
            }
            this.mHorizontalAlignment = HorizontalAlignment.from(attribute);
        }
        final String attribute2 = element.getAttribute("tts:displayAlign");
        if (!StringUtils.isEmpty(attribute2)) {
            if (Log.isLoggable("nf_subtitles", 3)) {
                Log.d("nf_subtitles", "Display alignment found " + attribute2);
            }
            this.mVerticalAlignment = VerticalAlignment.from(attribute2);
        }
        final String attribute3 = element.getAttribute("tts:extent");
        if (!StringUtils.isEmpty(attribute3)) {
            if (Log.isLoggable("nf_subtitles", 3)) {
                Log.d("nf_subtitles", "Extent found " + attribute3);
            }
            this.mExtent = DoubleLength.createInstance(attribute3, this.mCellResolution);
        }
        final String attribute4 = element.getAttribute("tts:origin");
        if (!StringUtils.isEmpty(attribute4)) {
            if (Log.isLoggable("nf_subtitles", 3)) {
                Log.d("nf_subtitles", "Origin found " + attribute4);
            }
            this.mOrigin = DoubleLength.createInstance(attribute4, this.mCellResolution);
        }
    }
    
    public DoubleLength getExtent() {
        return this.mExtent;
    }
    
    public HorizontalAlignment getHorizontalAlignment() {
        return this.mHorizontalAlignment;
    }
    
    public String getId() {
        return this.mId;
    }
    
    public DoubleLength getOrigin() {
        return this.mOrigin;
    }
    
    public TextStyle getTextStyle() {
        return this.mTextStyle;
    }
    
    public VerticalAlignment getVerticalAlignment() {
        return this.mVerticalAlignment;
    }
    
    @Override
    public String toString() {
        return "Region [mId=" + this.mId + ", mExtent=" + this.mExtent + ", mOrigin=" + this.mOrigin + ", mHorizontalAlignment=" + this.mHorizontalAlignment + ", mVerticalAlignment=" + this.mVerticalAlignment + ", mCellResolution=" + this.mCellResolution + ", mTextStyle=" + this.mTextStyle + "]";
    }
}
