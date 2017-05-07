// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles;

import com.netflix.mediaclient.util.SubtitleUtils;
import com.netflix.mediaclient.util.StringUtils;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.netflix.mediaclient.Log;
import java.util.ArrayList;
import org.w3c.dom.Element;
import java.util.List;

public class SubtitleBlock
{
    public static final String BEGIN = "begin";
    public static final String END = "end";
    public static final String ID = "xml:id";
    public static final String REGION = "region";
    private static final String TAG = "nf_subtitles";
    private long mEnd;
    private String mId;
    private float mMaxFontSizeMultiplier;
    private Region mRegion;
    private long mStart;
    private TextStyle mStyle;
    private List<SubtitleTextNode> mTextNodes;
    private int mTotalNumberOfLines;
    
    SubtitleBlock(final Element element, final SubtitleParser subtitleParser, final TextStyle textStyle, final Region region) {
        this.mTextNodes = new ArrayList<SubtitleTextNode>();
        if (element == null) {
            throw new IllegalArgumentException("P can not be null!");
        }
        if (subtitleParser == null) {
            throw new IllegalArgumentException("Parser can not be null!");
        }
        this.mId = element.getAttribute("xml:id");
        if (this.mId == null) {
            Log.w("nf_subtitles", "Block id is not specified, it will be faked.");
        }
        this.parseRegion(subtitleParser, region, element);
        this.mStyle = TextStyle.createInstanceFromContainer(element, subtitleParser, textStyle);
        this.mStart = this.parseTime(element.getAttribute("begin"), subtitleParser.getTickRate());
        this.mEnd = this.parseTime(element.getAttribute("end"), subtitleParser.getTickRate());
        if (Log.isLoggable()) {
            Log.d("nf_subtitles", "Use subtitle from " + this.mStart + " to " + this.mEnd);
        }
        this.parseTextNodes(subtitleParser, element);
        if (Log.isLoggable()) {
            Log.d("nf_subtitles", "Total number of lines needed: " + this.mTotalNumberOfLines);
            Log.d("nf_subtitles", "Max font size multplier from content: " + this.mMaxFontSizeMultiplier);
        }
        final TextStyle userDefaults = subtitleParser.getUserDefaults();
        if (userDefaults != null) {
            Log.d("nf_subtitles", "Use user overide for mMaxFontSizeMultiplier");
            float mMaxFontSizeMultiplier;
            if (userDefaults.getFontSize() != null) {
                mMaxFontSizeMultiplier = userDefaults.getFontSize();
            }
            else {
                mMaxFontSizeMultiplier = 100.0f;
            }
            this.mMaxFontSizeMultiplier = mMaxFontSizeMultiplier;
        }
        if (this.mMaxFontSizeMultiplier < 0.0f) {
            Log.d("nf_subtitles", "Max font size multplier set to 100! ");
            this.mMaxFontSizeMultiplier = 100.0f;
        }
    }
    
    private int handleSpanNode(final SubtitleParser subtitleParser, final Element element, int handleXmlElement, TextStyle instanceFromContainer) {
        Log.d("nf_subtitles", "SPAN node, create subtitle block");
        instanceFromContainer = TextStyle.createInstanceFromContainer(element, subtitleParser, instanceFromContainer);
        final NodeList childNodes = element.getChildNodes();
        int i = 0;
        int n = handleXmlElement;
        while (i < childNodes.getLength()) {
            final Node item = childNodes.item(i);
            if (item.getNodeType() == 1) {
                handleXmlElement = this.handleXmlElement(subtitleParser, item, n, instanceFromContainer);
            }
            else if (item.getNodeType() == 3) {
                this.handleTextNode(subtitleParser, item, n, instanceFromContainer);
                handleXmlElement = 0;
            }
            else {
                handleXmlElement = n;
                if (Log.isLoggable()) {
                    Log.d("nf_subtitles", "Ignore node of type: " + item.getNodeType());
                    handleXmlElement = n;
                }
            }
            ++i;
            n = handleXmlElement;
        }
        return n;
    }
    
    private void handleTextNode(final SubtitleParser subtitleParser, final Node node, final int n, final TextStyle textStyle) {
        Log.d("nf_subtitles", "Text node, create subtitle block");
        TextStyle textStyle2 = null;
        if (textStyle != null) {
            textStyle2 = new TextStyle(textStyle);
            textStyle2.merge(this.mStyle);
        }
        else if (this.mStyle != null) {
            textStyle2 = new TextStyle(this.mStyle);
        }
        final SubtitleTextNode subtitleTextNode = new SubtitleTextNode(textStyle2, node.getTextContent(), n);
        this.mTextNodes.add(subtitleTextNode);
        this.updateBlockContainerDimensions(subtitleTextNode);
    }
    
    private int handleXmlElement(final SubtitleParser subtitleParser, final Node node, final int n, final TextStyle textStyle) {
        final Element element = (Element)node;
        int n2;
        if (SubtitleParserHelper.isBreak(element)) {
            Log.d("nf_subtitles", "Break line, increase br counter");
            n2 = n + 1;
        }
        else {
            if (SubtitleParserHelper.isSpan(element)) {
                return this.handleSpanNode(subtitleParser, element, n, textStyle);
            }
            n2 = n;
            if (Log.isLoggable()) {
                Log.d("nf_subtitles", "Ignore node of type: " + node.getNodeType());
                return n;
            }
        }
        return n2;
    }
    
    private void parseRegion(final SubtitleParser subtitleParser, final Region mRegion, final Element element) {
        final String attribute = element.getAttribute("region");
        if (StringUtils.isEmpty(attribute)) {
            if (mRegion == null) {
                Log.w("nf_subtitles", "Region is not specified and body region is not provided. Use default region.");
                return;
            }
            Log.d("nf_subtitles", "Region is not specified. Use body region.");
            this.mRegion = mRegion;
        }
        else {
            if (Log.isLoggable()) {
                Log.d("nf_subtitles", "Belongs to region " + attribute);
            }
            this.mRegion = subtitleParser.getNamedRegion(attribute);
            if (this.mRegion == null && Log.isLoggable()) {
                Log.d("nf_subtitles", "Region NOT found for id " + attribute + " and body region not used, use default region!");
            }
        }
    }
    
    private void parseTextNodes(final SubtitleParser subtitleParser, final Element element) {
        final NodeList childNodes = element.getChildNodes();
        int i = 0;
        int n = 0;
        while (i < childNodes.getLength()) {
            final Node item = childNodes.item(i);
            int handleXmlElement;
            if (item.getNodeType() == 1) {
                handleXmlElement = this.handleXmlElement(subtitleParser, item, n, this.mStyle);
            }
            else if (item.getNodeType() == 3) {
                this.handleTextNode(subtitleParser, item, n, null);
                handleXmlElement = 0;
            }
            else {
                handleXmlElement = n;
                if (Log.isLoggable()) {
                    Log.d("nf_subtitles", "Ignore node of type: " + item.getNodeType());
                    handleXmlElement = n;
                }
            }
            ++i;
            n = handleXmlElement;
        }
    }
    
    private long parseTime(final String s, final double n) {
        return SubtitleUtils.parseTime(s, n);
    }
    
    private void updateBlockContainerDimensions(final SubtitleTextNode subtitleTextNode) {
        this.mTotalNumberOfLines = this.mTotalNumberOfLines + subtitleTextNode.getLineBreaks() + 1;
        if (subtitleTextNode.getStyle() != null) {
            Log.d("nf_subtitles", "Font size: " + subtitleTextNode.getStyle().getFontSize());
        }
        if (subtitleTextNode.getStyle() != null && subtitleTextNode.getStyle().getFontSize() != null && this.mMaxFontSizeMultiplier < subtitleTextNode.getStyle().getFontSize()) {
            this.mMaxFontSizeMultiplier = subtitleTextNode.getStyle().getFontSize();
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null) {
                return false;
            }
            if (!(o instanceof SubtitleBlock)) {
                return false;
            }
            final SubtitleBlock subtitleBlock = (SubtitleBlock)o;
            if (this.mId == null) {
                if (subtitleBlock.mId != null) {
                    return false;
                }
            }
            else if (!this.mId.equals(subtitleBlock.mId)) {
                return false;
            }
        }
        return true;
    }
    
    public long getEnd() {
        return this.mEnd;
    }
    
    public String getId() {
        return this.mId;
    }
    
    public Region getRegion() {
        return this.mRegion;
    }
    
    public long getStart() {
        return this.mStart;
    }
    
    public TextStyle getStyle() {
        return this.mStyle;
    }
    
    public List<SubtitleTextNode> getTextNodes() {
        return this.mTextNodes;
    }
    
    public int getTotalNumberOfLines() {
        return this.mTotalNumberOfLines;
    }
    
    @Override
    public int hashCode() {
        int hashCode;
        if (this.mId == null) {
            hashCode = 0;
        }
        else {
            hashCode = this.mId.hashCode();
        }
        return hashCode + 31;
    }
    
    public boolean isVisible(final long n) {
        return n >= this.mStart && n <= this.mEnd;
    }
    
    public boolean isVisibleInGivenTimeRange(final long n, final long n2) {
        if (n > n2) {
            Log.e("nf_subtitles", "From can not be later than to!");
        }
        else if (n2 > this.mStart && n <= this.mEnd) {
            return true;
        }
        return false;
    }
    
    @Override
    public String toString() {
        return "SubtitleBlock [mId=" + this.mId + ", mRegion=" + this.mRegion + ", mTextNodes=" + this.mTextNodes + ", mStart=" + this.mStart + ", mEnd=" + this.mEnd + ", mStyle=" + this.mStyle + ", mTotalNumberOfLines=" + this.mTotalNumberOfLines + ", mMaxFontSizeMultiplier=" + this.mMaxFontSizeMultiplier + "]";
    }
}
