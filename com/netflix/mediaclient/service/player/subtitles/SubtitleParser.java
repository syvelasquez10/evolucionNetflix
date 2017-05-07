// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles;

import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import com.netflix.mediaclient.event.nrdp.media.SubtitleData;
import com.netflix.mediaclient.util.StringUtils;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.netflix.mediaclient.util.XmlDomUtils;
import org.w3c.dom.Element;
import com.netflix.mediaclient.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.netflix.mediaclient.javabridge.ui.IMedia;
import java.util.Map;

public class SubtitleParser
{
    private static final int MILLISECONDS_PER_SECOND = 1000;
    private static final int START_TIME_TRASHOLD = 30000;
    private static final String TAG = "nf_subtitles";
    private String mAspectExtent;
    private CellResolution mCellResolution;
    private final TextStyle mDefaults;
    private int mIndexOfLastSearch;
    private String mPixelAspectRatio;
    private boolean mReady;
    private final Map<String, Region> mRegions;
    private final Map<String, TextStyle> mStyles;
    private IMedia.SubtitleProfile mSubtitleProfile;
    private final List<SubtitleBlock> mTextBlocks;
    private double mTickRate;
    private String mTimeBase;
    private final TextStyle mUserDefaults;
    private final float mVideoAspectRatio;
    
    public SubtitleParser(final float mVideoAspectRatio, final TextStyle mUserDefaults, final TextStyle textStyle) {
        this.mStyles = new HashMap<String, TextStyle>();
        this.mRegions = new HashMap<String, Region>();
        this.mTextBlocks = new ArrayList<SubtitleBlock>();
        this.mIndexOfLastSearch = -1;
        this.mVideoAspectRatio = mVideoAspectRatio;
        this.mUserDefaults = mUserDefaults;
        this.mDefaults = createDefaultStyle(mUserDefaults, textStyle);
    }
    
    private int compareBlockTime(final long n, final int n2) {
        if (this.mTextBlocks.size() <= n2) {
            Log.e("nf_subtitles", "Index (" + n2 + ") is higher than numbet of blocks  " + this.mTextBlocks.size());
        }
        else {
            final SubtitleBlock subtitleBlock = this.mTextBlocks.get(n2);
            if (subtitleBlock.isVisible(n)) {
                if (Log.isLoggable("nf_subtitles", 3)) {
                    Log.d("nf_subtitles", "Block " + n2 + " is visible for pts " + n);
                }
                return 0;
            }
            if (subtitleBlock.getStart() >= n) {
                if (Log.isLoggable("nf_subtitles", 3)) {
                    Log.d("nf_subtitles", "Block " + n2 + " is after pts " + n);
                }
                return 1;
            }
            if (Log.isLoggable("nf_subtitles", 3)) {
                Log.d("nf_subtitles", "Block " + n2 + " is before pts " + n);
                return -1;
            }
        }
        return -1;
    }
    
    private static TextStyle createDefaultStyle(final TextStyle textStyle, final TextStyle textStyle2) {
        final TextStyle textStyle3 = new TextStyle();
        final TextStyle default_DEVICE_TEXT_STYLE = TextStyle.DEFAULT_DEVICE_TEXT_STYLE;
        if (textStyle != null) {
            Log.d("nf_subtitles", "User overrides exist for style, apply it first!");
            textStyle3.merge(textStyle);
        }
        if (textStyle2 != null) {
            Log.d("nf_subtitles", "Region overrides exist, use region defaults");
            textStyle3.merge(textStyle2);
        }
        else {
            Log.d("nf_subtitles", "Region overrides does NOT exist for style, use just devices defaults");
        }
        textStyle3.merge(default_DEVICE_TEXT_STYLE);
        if (Log.isLoggable("nf_subtitles", 3)) {
            Log.d("nf_subtitles", "Style default: " + textStyle3);
        }
        return textStyle3;
    }
    
    private int findIndex(final long n) {
        if (Log.isLoggable("nf_subtitles", 3)) {
            Log.d("nf_subtitles", "Index was not known before. Find it for position " + n);
        }
        int n2;
        if (n <= 30000L) {
            Log.d("nf_subtitles", "Less than treshold, return 0");
            n2 = 0;
        }
        else {
            Log.d("nf_subtitles", "Try to guess");
            int search = this.search(n);
            int n3;
            if ((n3 = search) > 0) {
                Log.d("nf_subtitles", "Lets see if index is first in array or just first found");
                int n4 = search - 1;
                while (true) {
                    n3 = search;
                    if (n4 <= 0) {
                        break;
                    }
                    n3 = search;
                    if (this.compareBlockTime(n, n4) != 0) {
                        break;
                    }
                    if (Log.isLoggable("nf_subtitles", 3)) {
                        Log.d("nf_subtitles", "New Index found " + n4);
                    }
                    search = n4;
                    --n4;
                }
            }
            n2 = n3;
            if (Log.isLoggable("nf_subtitles", 3)) {
                Log.d("nf_subtitles", "Index found " + n3);
                return n3;
            }
        }
        return n2;
    }
    
    private int getLastKnownPosition(final long n) {
        if (this.mIndexOfLastSearch < 0) {
            this.mIndexOfLastSearch = this.findIndex(n);
        }
        return this.mIndexOfLastSearch;
    }
    
    private void parseBody(final Element element) {
        if (element == null) {
            throw new IllegalArgumentException("Body element can not be null!");
        }
        Log.d("nf_subtitles", "Parsing body started");
        final TextStyle instanceFromContainer = TextStyle.createInstanceFromContainer(element, this, this.mDefaults);
        final Element firstFoundElementByTagName = XmlDomUtils.getFirstFoundElementByTagName(element, "div");
        if (firstFoundElementByTagName == null) {
            Log.e("nf_subtitles", "DIV element not found!");
            return;
        }
        final TextStyle instanceFromContainer2 = TextStyle.createInstanceFromContainer(firstFoundElementByTagName, this, instanceFromContainer);
        final NodeList elementsByTagName = firstFoundElementByTagName.getElementsByTagName("p");
        if (elementsByTagName == null || elementsByTagName.getLength() < 1) {
            Log.e("nf_subtitles", "P element(s) not found!");
            return;
        }
        for (int i = 0; i < elementsByTagName.getLength(); ++i) {
            final Node item = elementsByTagName.item(i);
            if (item instanceof Element) {
                this.mTextBlocks.add(new SubtitleBlock((Element)item, this, instanceFromContainer2, null));
            }
            else {
                Log.e("nf_subtitles", "Node is not instance of element for P!");
            }
        }
        Log.d("nf_subtitles", "Parsing body done");
    }
    
    private void parseHead(final Element element) {
        if (element == null) {
            throw new IllegalArgumentException("Head element can not be null!");
        }
        final String attribute = element.getAttribute("use");
        if (Log.isLoggable("nf_subtitles", 3)) {
            Log.d("nf_subtitles", "Subtitle profile: " + attribute);
        }
        this.mSubtitleProfile = IMedia.SubtitleProfile.fromNccpCode(attribute);
        this.parseStyling(element);
        this.parseRegions(element);
    }
    
    private void parseRegions(Element firstFoundElementByTagName) {
        Log.d("nf_subtitles", "Parsing regions started");
        firstFoundElementByTagName = XmlDomUtils.getFirstFoundElementByTagName(firstFoundElementByTagName, "layout");
        if (firstFoundElementByTagName == null) {
            Log.e("nf_subtitles", "Layout element not found!");
            return;
        }
        final NodeList elementsByTagName = firstFoundElementByTagName.getElementsByTagName("region");
        if (elementsByTagName == null || elementsByTagName.getLength() < 1) {
            Log.e("nf_subtitles", "Region element(s) not found!");
            return;
        }
        for (int i = 0; i < elementsByTagName.getLength(); ++i) {
            final Node item = elementsByTagName.item(i);
            if (item instanceof Element) {
                final Region region = Region.createRegion(this, (Element)item, this.mCellResolution, this.mDefaults);
                if (region == null) {
                    Log.w("nf_subtitles", "Region not found!");
                }
                else if (region.getId() == null) {
                    if (Log.isLoggable("nf_subtitles", 5)) {
                        Log.w("nf_subtitles", "Region exist, but its ID is null: " + region);
                    }
                }
                else {
                    if (Log.isLoggable("nf_subtitles", 3)) {
                        Log.d("nf_subtitles", "Region " + i + " found " + region);
                    }
                    this.mRegions.put(region.getId(), region);
                }
            }
            else {
                Log.e("nf_subtitles", "Node is not instance of element for region!");
            }
        }
        Log.d("nf_subtitles", "Parsing regions done");
    }
    
    private void parseStyling(Element firstFoundElementByTagName) {
        Log.d("nf_subtitles", "Parsing styling started");
        firstFoundElementByTagName = XmlDomUtils.getFirstFoundElementByTagName(firstFoundElementByTagName, "styling");
        if (firstFoundElementByTagName == null) {
            Log.d("nf_subtitles", "Styling element not found!");
            return;
        }
        final NodeList elementsByTagName = firstFoundElementByTagName.getElementsByTagName("style");
        if (elementsByTagName == null || elementsByTagName.getLength() < 1) {
            Log.d("nf_subtitles", "Style element(s) not found!");
            return;
        }
        for (int i = 0; i < elementsByTagName.getLength(); ++i) {
            final Node item = elementsByTagName.item(i);
            if (item instanceof Element) {
                final TextStyle instanceFromContainer = TextStyle.createInstanceFromContainer((Element)item, this, null);
                if (instanceFromContainer == null) {
                    Log.w("nf_subtitles", "Style not found!");
                }
                else if (instanceFromContainer.getId() == null) {
                    if (Log.isLoggable("nf_subtitles", 5)) {
                        Log.w("nf_subtitles", "Style exist, nut its ID is null: " + instanceFromContainer);
                    }
                }
                else {
                    this.mStyles.put(instanceFromContainer.getId(), instanceFromContainer);
                    if (Log.isLoggable("nf_subtitles", 3)) {
                        Log.d("nf_subtitles", "Style found: " + instanceFromContainer);
                    }
                }
            }
            else {
                Log.e("nf_subtitles", "Node is not instance of element for style!");
            }
        }
        Log.d("nf_subtitles", "Parsing styling done");
    }
    
    private void parseTt(final Element element) {
        if (element == null) {
            throw new IllegalArgumentException("Root element can not be null!");
        }
        final String attribute = element.getAttribute("ttp:tickRate");
        if (!StringUtils.isEmpty(attribute)) {
            final double double1 = Double.parseDouble(attribute);
            if (double1 <= 0.0) {
                Log.d("nf_subtitles", "Tickrate defaults to 1000");
                this.mTickRate = 1000.0;
            }
            else {
                Log.d("nf_subtitles", "Tickrate calculate");
                this.mTickRate = 1000.0 / double1;
            }
        }
        else {
            Log.d("nf_subtitles", "Tickrate defaults to 1000 on empty tag");
            this.mTickRate = 1000.0;
        }
        if (Log.isLoggable("nf_subtitles", 3)) {
            Log.d("nf_subtitles", "Tickrate final: " + this.mTickRate);
        }
        this.mPixelAspectRatio = element.getAttribute("ttp:pixelAspectRatio");
        this.mAspectExtent = element.getAttribute("tts:extent");
        final String attribute2 = element.getAttribute("ttp:cellResolution");
        if (!StringUtils.isEmpty(attribute2)) {
            this.mCellResolution = CellResolution.createInstance(attribute2, this.mAspectExtent, this.mPixelAspectRatio, this.mVideoAspectRatio);
        }
    }
    
    private int search(final long n) {
        int n2 = 0;
        int i = this.mTextBlocks.size() - 1;
        while (i >= n2) {
            final int n3 = n2 + (i - n2) / 2;
            final int compareBlockTime = this.compareBlockTime(n, n3);
            if (compareBlockTime == 0) {
                return n3;
            }
            if (compareBlockTime < 0) {
                n2 = n3 + 1;
            }
            else {
                i = n3 - 1;
            }
        }
        return 0;
    }
    
    public CellResolution getCellResolution() {
        return this.mCellResolution;
    }
    
    public Region getNamedRegion(final String s) {
        if (s == null) {
            return null;
        }
        return this.mRegions.get(s);
    }
    
    public TextStyle getNamedStyle(final String s) {
        if (s == null) {
            return null;
        }
        return this.mStyles.get(s);
    }
    
    public Region[] getRegions() {
        if (this.mRegions == null || this.mRegions.size() < 1) {
            return new Region[0];
        }
        return this.mRegions.values().toArray(new Region[this.mRegions.size()]);
    }
    
    public IMedia.SubtitleProfile getSubtitleProfile() {
        return this.mSubtitleProfile;
    }
    
    public SubtitleScreen getSubtitlesForPosition(final long n) {
        final long n2 = n + 2000L;
        final ArrayList<SubtitleBlock> list = new ArrayList<SubtitleBlock>();
        final ArrayList<SubtitleBlock> list2 = new ArrayList<SubtitleBlock>();
        if (Log.isLoggable("nf_subtitles", 3)) {
            Log.d("nf_subtitles", "Subtitle blocks: " + this.mTextBlocks.size());
        }
        int i = this.getLastKnownPosition(n);
        if (Log.isLoggable("nf_subtitles", 3)) {
            Log.d("nf_subtitles", "==> Start search from index: " + i);
        }
        int n3 = 0;
        while (i < this.mTextBlocks.size()) {
            final SubtitleBlock subtitleBlock = this.mTextBlocks.get(i);
            if (subtitleBlock.getStart() > n2) {
                Log.d("nf_subtitles", "Subtitle block start is in future more than 2 sec, break search");
                break;
            }
            int n4;
            if (subtitleBlock.isVisible(n)) {
                if ((n4 = n3) == 0) {
                    if (Log.isLoggable("nf_subtitles", 3)) {
                        Log.d("nf_subtitles", "===> New index search found: " + i);
                    }
                    n4 = 1;
                    this.mIndexOfLastSearch = i;
                }
                list.add(subtitleBlock);
            }
            else {
                n4 = n3;
                if (subtitleBlock.isVisibleInGivenTimeRange(n, n2)) {
                    list2.add(subtitleBlock);
                    n4 = n3;
                }
            }
            ++i;
            n3 = n4;
        }
        return new SubtitleScreen(this, list, list2, 2000);
    }
    
    public TextStyle getTextStyleDefault() {
        return this.mDefaults;
    }
    
    public double getTickRate() {
        return this.mTickRate;
    }
    
    public String getTimeBase() {
        return this.mTimeBase;
    }
    
    public TextStyle getUserDefaults() {
        return this.mUserDefaults;
    }
    
    public boolean isReady() {
        return this.mReady;
    }
    
    public void parse(final SubtitleData subtitleData) throws IOException, ParserConfigurationException, SAXException {
        Log.d("nf_subtitles", "==> Subtitle parsing started...");
        if (subtitleData == null) {
            throw new IllegalArgumentException("Subtitle data event can not be null!");
        }
        final Element documentElement = XmlDomUtils.createDocument(subtitleData.getXml()).getDocumentElement();
        this.parseTt(documentElement);
        this.parseHead(XmlDomUtils.getChildElementByTagName(documentElement, "head"));
        Log.d("nf_subtitles", "Ready to serve subtitles...");
        this.mReady = true;
        this.parseBody(XmlDomUtils.getChildElementByTagName(documentElement, "body"));
        Log.d("nf_subtitles", "==> Subtitle parsing completed.");
    }
    
    public void seeked() {
        this.mIndexOfLastSearch = -1;
    }
}
