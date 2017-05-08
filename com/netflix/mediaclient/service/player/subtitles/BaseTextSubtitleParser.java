// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles;

import java.util.Iterator;
import com.netflix.mediaclient.util.StringUtils;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.netflix.mediaclient.service.player.subtitles.text.TextSubtitleBlock;
import com.netflix.mediaclient.util.XmlDomUtils;
import org.w3c.dom.Element;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.SubtitleUtils;
import java.util.ArrayList;
import java.util.HashMap;
import com.netflix.mediaclient.media.SubtitleUrl;
import com.netflix.mediaclient.servicemgr.IPlayer;
import java.util.List;
import com.netflix.mediaclient.servicemgr.ISubtitleDef$SubtitleProfile;
import com.netflix.mediaclient.service.player.subtitles.text.Region;
import java.util.Map;
import com.netflix.mediaclient.service.player.subtitles.text.TextStyle;
import com.netflix.mediaclient.service.player.subtitles.text.CellResolution;

public abstract class BaseTextSubtitleParser extends BaseSubtitleParser implements TextSubtitleParser
{
    protected static final int MILLISECONDS_PER_SECOND = 1000;
    protected static final int START_TIME_TRASHOLD = 30000;
    protected String mAspectExtent;
    protected CellResolution mCellResolution;
    protected final TextStyle mDefault;
    protected final TextStyle mDeviceDefault;
    protected String mPixelAspectRatio;
    protected final TextStyle mRegionDefault;
    protected final Map<String, Region> mRegions;
    protected final Map<String, TextStyle> mStyles;
    protected ISubtitleDef$SubtitleProfile mSubtitleProfile;
    protected final List<SubtitleBlock> mTextBlocks;
    protected double mTickRate;
    protected String mTimeBase;
    protected final TextStyle mUserDefaults;
    protected final float mVideoAspectRatio;
    
    public BaseTextSubtitleParser(final IPlayer player, final SubtitleUrl subtitleUrl, final TextStyle mUserDefaults, final TextStyle mRegionDefault, final float mVideoAspectRatio, final SubtitleParser$DownloadFailedCallback subtitleParser$DownloadFailedCallback, final long n) {
        super(player, subtitleUrl, subtitleParser$DownloadFailedCallback, n);
        this.mStyles = new HashMap<String, TextStyle>();
        this.mRegions = new HashMap<String, Region>();
        this.mTextBlocks = new ArrayList<SubtitleBlock>();
        this.mDefault = new TextStyle();
        this.createDefaults();
        this.mVideoAspectRatio = mVideoAspectRatio;
        this.mUserDefaults = mUserDefaults;
        this.mRegionDefault = mRegionDefault;
        this.mDeviceDefault = SubtitleUtils.getDeviceDefaultTextStyle(mUserDefaults, mRegionDefault);
    }
    
    private int compareBlockTime(final long n, final int n2) {
        synchronized (this.mTextBlocks) {
            if (this.mTextBlocks.size() <= n2) {
                Log.e("nf_subtitles", "Index (" + n2 + ") is higher than numbet of blocks  " + this.mTextBlocks.size());
                return -1;
            }
            if (this.mTextBlocks.get(n2).isVisible(n)) {
                if (Log.isLoggable()) {
                    Log.d("nf_subtitles", "Block " + n2 + " is visible for pts " + n);
                }
                return 0;
            }
        }
        final SubtitleBlock subtitleBlock;
        if (subtitleBlock.getStart() < n) {
            if (Log.isLoggable()) {
                Log.d("nf_subtitles", "Block " + n2 + " is before pts " + n);
            }
            // monitorexit(list)
            return -1;
        }
        if (Log.isLoggable()) {
            Log.d("nf_subtitles", "Block " + n2 + " is after pts " + n);
        }
        // monitorexit(list)
        return 1;
    }
    
    private int findIndex(final long n) {
        if (Log.isLoggable()) {
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
                    if (Log.isLoggable()) {
                        Log.d("nf_subtitles", "New Index found " + n4);
                    }
                    search = n4;
                    --n4;
                }
            }
            n2 = n3;
            if (Log.isLoggable()) {
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
        final TextStyle instanceFromContainer = TextStyle.createInstanceFromContainer(element, this, this.mDefault);
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
                final TextSubtitleBlock textSubtitleBlock = new TextSubtitleBlock((Element)item, this, instanceFromContainer2, null);
                synchronized (this.mTextBlocks) {
                    this.mTextBlocks.add(textSubtitleBlock);
                    continue;
                }
            }
            Log.e("nf_subtitles", "Node is not instance of element for P!");
        }
        Log.d("nf_subtitles", "Parsing body done");
    }
    
    private void parseHead(final Element element) {
        if (element == null) {
            throw new IllegalArgumentException("Head element can not be null!");
        }
        final String attribute = element.getAttribute("use");
        if (Log.isLoggable()) {
            Log.d("nf_subtitles", "Subtitle profile: " + attribute);
        }
        this.mSubtitleProfile = ISubtitleDef$SubtitleProfile.fromNccpCode(attribute);
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
                final Region region = Region.createRegion(this, (Element)item, this.mCellResolution, this.mDefault);
                if (region == null) {
                    Log.w("nf_subtitles", "Region not found!");
                }
                else if (region.getId() == null) {
                    if (Log.isLoggable()) {
                        Log.w("nf_subtitles", "Region exist, but its ID is null: " + region);
                    }
                }
                else {
                    if (Log.isLoggable()) {
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
                    if (Log.isLoggable()) {
                        Log.w("nf_subtitles", "Style exist, nut its ID is null: " + instanceFromContainer);
                    }
                }
                else {
                    this.mStyles.put(instanceFromContainer.getId(), instanceFromContainer);
                    if (Log.isLoggable()) {
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
        if (Log.isLoggable()) {
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
        int i = this.mTextBlocks.size() - 1;
        int n2 = 0;
        while (i >= n2) {
            final int n3 = (i - n2) / 2 + n2;
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
    
    protected void createDefaults() {
        this.mDefault.merge(this.mRegionDefault);
        this.mDefault.merge(this.mDeviceDefault);
    }
    
    @Override
    public CellResolution getCellResolution() {
        return this.mCellResolution;
    }
    
    @Override
    public TextStyle getDeviceDefault() {
        return this.mDeviceDefault;
    }
    
    @Override
    public Region getNamedRegion(final String s) {
        if (s == null) {
            return null;
        }
        return this.mRegions.get(s);
    }
    
    @Override
    public TextStyle getNamedStyle(final String s) {
        if (s == null) {
            return null;
        }
        return this.mStyles.get(s);
    }
    
    @Override
    public int getNumberOfDisplayedSubtitles() {
        synchronized (this) {
            synchronized (this.mTextBlocks) {
                final Iterator<SubtitleBlock> iterator = this.mTextBlocks.iterator();
                int n = 0;
                while (iterator.hasNext()) {
                    n += iterator.next().getNumberOfDisplays();
                }
                return n;
            }
        }
    }
    
    @Override
    public int getNumberOfSubtitlesExpectedToBeDisplayed() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: monitorenter   
        //     2: aload_0        
        //     3: getfield        com/netflix/mediaclient/service/player/subtitles/BaseTextSubtitleParser.mIndexOfLastSearch:I
        //     6: iconst_m1      
        //     7: if_icmpne       54
        //    10: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //    13: ifeq            45
        //    16: ldc             "nf_subtitles"
        //    18: new             Ljava/lang/StringBuilder;
        //    21: dup            
        //    22: invokespecial   java/lang/StringBuilder.<init>:()V
        //    25: ldc_w           "User just seeked, there was no pts update after that, just return already known value "
        //    28: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    31: aload_0        
        //    32: getfield        com/netflix/mediaclient/service/player/subtitles/BaseTextSubtitleParser.mNumberOfSubtitlesExpectedToBeDisplayed:I
        //    35: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    38: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    41: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    44: pop            
        //    45: aload_0        
        //    46: getfield        com/netflix/mediaclient/service/player/subtitles/BaseTextSubtitleParser.mNumberOfSubtitlesExpectedToBeDisplayed:I
        //    49: istore_2       
        //    50: aload_0        
        //    51: monitorexit    
        //    52: iload_2        
        //    53: ireturn        
        //    54: iconst_0       
        //    55: istore_1       
        //    56: aload_0        
        //    57: getfield        com/netflix/mediaclient/service/player/subtitles/BaseTextSubtitleParser.mTextBlocks:Ljava/util/List;
        //    60: astore          4
        //    62: aload           4
        //    64: monitorenter   
        //    65: aload_0        
        //    66: getfield        com/netflix/mediaclient/service/player/subtitles/BaseTextSubtitleParser.mTextBlocks:Ljava/util/List;
        //    69: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    74: astore          5
        //    76: aload           5
        //    78: invokeinterface java/util/Iterator.hasNext:()Z
        //    83: ifeq            119
        //    86: aload           5
        //    88: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    93: checkcast       Lcom/netflix/mediaclient/service/player/subtitles/SubtitleBlock;
        //    96: aload_0        
        //    97: getfield        com/netflix/mediaclient/service/player/subtitles/BaseTextSubtitleParser.mStartPlayPositionInTitleInMs:J
        //   100: aload_0        
        //   101: getfield        com/netflix/mediaclient/service/player/subtitles/BaseTextSubtitleParser.mLastRenderedPositionInTitleInMs:J
        //   104: invokeinterface com/netflix/mediaclient/service/player/subtitles/SubtitleBlock.isVisibleInGivenTimeRange:(JJ)Z
        //   109: ifeq            213
        //   112: iload_1        
        //   113: iconst_1       
        //   114: iadd           
        //   115: istore_1       
        //   116: goto            213
        //   119: aload           4
        //   121: monitorexit    
        //   122: aload_0        
        //   123: getfield        com/netflix/mediaclient/service/player/subtitles/BaseTextSubtitleParser.mNumberOfSubtitlesExpectedToBeDisplayed:I
        //   126: iload_1        
        //   127: iadd           
        //   128: istore_3       
        //   129: iload_3        
        //   130: istore_2       
        //   131: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   134: ifeq            50
        //   137: ldc             "nf_subtitles"
        //   139: new             Ljava/lang/StringBuilder;
        //   142: dup            
        //   143: invokespecial   java/lang/StringBuilder.<init>:()V
        //   146: iload_1        
        //   147: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   150: ldc_w           " where supposed to be visible between "
        //   153: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   156: aload_0        
        //   157: getfield        com/netflix/mediaclient/service/player/subtitles/BaseTextSubtitleParser.mStartPlayPositionInTitleInMs:J
        //   160: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   163: ldc_w           " and "
        //   166: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   169: aload_0        
        //   170: getfield        com/netflix/mediaclient/service/player/subtitles/BaseTextSubtitleParser.mLastRenderedPositionInTitleInMs:J
        //   173: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   176: ldc_w           " for total of "
        //   179: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   182: iload_3        
        //   183: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   186: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   189: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   192: pop            
        //   193: iload_3        
        //   194: istore_2       
        //   195: goto            50
        //   198: astore          4
        //   200: aload_0        
        //   201: monitorexit    
        //   202: aload           4
        //   204: athrow         
        //   205: astore          5
        //   207: aload           4
        //   209: monitorexit    
        //   210: aload           5
        //   212: athrow         
        //   213: goto            76
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  2      45     198    205    Any
        //  45     50     198    205    Any
        //  56     65     198    205    Any
        //  65     76     205    213    Any
        //  76     112    205    213    Any
        //  119    122    205    213    Any
        //  122    129    198    205    Any
        //  131    193    198    205    Any
        //  207    210    205    213    Any
        //  210    213    198    205    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0076:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Override
    public TextStyle getRegionDefault() {
        return this.mRegionDefault;
    }
    
    @Override
    public Region[] getRegions() {
        if (this.mRegions == null || this.mRegions.size() < 1) {
            return new Region[0];
        }
        return this.mRegions.values().toArray(new Region[this.mRegions.size()]);
    }
    
    @Override
    public ISubtitleDef$SubtitleProfile getSubtitleProfile() {
        return this.mSubtitleProfile;
    }
    
    @Override
    public SubtitleScreen getSubtitlesForPosition(final long mLastRenderedPositionInTitleInMs) {
        final long n = mLastRenderedPositionInTitleInMs + 2000L;
        final ArrayList<SubtitleBlock> list = new ArrayList<SubtitleBlock>();
        final ArrayList<SubtitleBlock> list2 = new ArrayList<SubtitleBlock>();
        if (Log.isLoggable()) {
            Log.d("nf_subtitles", "Subtitle blocks: " + this.mTextBlocks.size());
        }
        int lastKnownPosition = this.getLastKnownPosition(mLastRenderedPositionInTitleInMs);
        if (Log.isLoggable()) {
            Log.d("nf_subtitles", "==> Start search from index: " + lastKnownPosition);
        }
    Label_0307_Outer:
        while (true) {
            int n2 = 0;
        Label_0307:
            while (true) {
            Label_0316:
                while (true) {
                    synchronized (this.mTextBlocks) {
                        final int size = this.mTextBlocks.size();
                        if (lastKnownPosition < size) {
                            final SubtitleBlock subtitleBlock = this.mTextBlocks.get(lastKnownPosition);
                            if (subtitleBlock.getStart() > n) {
                                Log.d("nf_subtitles", "Subtitle block start is in future more than 2 sec, break search");
                            }
                            else {
                                if (subtitleBlock.isVisible(mLastRenderedPositionInTitleInMs)) {
                                    int n3;
                                    if ((n3 = n2) == 0) {
                                        if (Log.isLoggable()) {
                                            Log.d("nf_subtitles", "===> New index search found: " + lastKnownPosition);
                                        }
                                        n3 = 1;
                                        this.mIndexOfLastSearch = lastKnownPosition;
                                    }
                                    list.add(subtitleBlock);
                                    n2 = n3;
                                    break Label_0307;
                                }
                                if (subtitleBlock.isVisibleInGivenTimeRange(mLastRenderedPositionInTitleInMs, n)) {
                                    list2.add(subtitleBlock);
                                }
                                break Label_0316;
                            }
                        }
                        // monitorexit(this.mTextBlocks)
                        this.mLastRenderedPositionInTitleInMs = mLastRenderedPositionInTitleInMs;
                        return new SubtitleScreen(this, list, list2, 2000, mLastRenderedPositionInTitleInMs);
                    }
                    ++lastKnownPosition;
                    continue Label_0307_Outer;
                }
                continue Label_0307;
            }
        }
    }
    
    @Override
    public TextStyle getTextStyleDefault() {
        return this.mDefault;
    }
    
    @Override
    public double getTickRate() {
        return this.mTickRate;
    }
    
    @Override
    public String getTimeBase() {
        return this.mTimeBase;
    }
    
    @Override
    public TextStyle getUserDefaults() {
        return this.mUserDefaults;
    }
    
    protected void parse(final String s) {
        Log.d("nf_subtitles", "==> Subtitle parsing started...");
        if (StringUtils.isEmpty(s)) {
            throw new IllegalArgumentException("Subtitle data xml is empty!");
        }
        final Element documentElement = XmlDomUtils.createDocument(s).getDocumentElement();
        this.parseTt(documentElement);
        this.parseHead(XmlDomUtils.getChildElementByTagName(documentElement, "head"));
        Log.d("nf_subtitles", "Ready to serve subtitles...");
        this.mReady = true;
        this.parseBody(XmlDomUtils.getChildElementByTagName(documentElement, "body"));
        Log.d("nf_subtitles", "==> Subtitle parsing completed.");
    }
    
    @Override
    public void seeked(final int n) {
        int n2;
        synchronized (this) {
            super.seeked(n);
            n2 = 0;
            synchronized (this.mTextBlocks) {
                for (final SubtitleBlock subtitleBlock : this.mTextBlocks) {
                    int n3 = n2;
                    if (subtitleBlock.isVisibleInGivenTimeRange(this.mStartPlayPositionInTitleInMs, this.mLastRenderedPositionInTitleInMs)) {
                        n3 = n2 + 1;
                    }
                    subtitleBlock.seeked(n);
                    n2 = n3;
                }
            }
        }
        // monitorexit(t)
        this.mNumberOfSubtitlesExpectedToBeDisplayed += n2;
        this.mStartPlayPositionInTitleInMs = n;
        if (Log.isLoggable()) {
            Log.d("nf_subtitles", n2 + " where supposed to be visible between " + this.mStartPlayPositionInTitleInMs + " and " + this.mLastRenderedPositionInTitleInMs + " for total of " + this.mNumberOfSubtitlesExpectedToBeDisplayed);
        }
    }
    // monitorexit(this)
}
