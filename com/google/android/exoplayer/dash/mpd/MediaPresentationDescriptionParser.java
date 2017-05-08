// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.dash.mpd;

import android.util.Pair;
import android.util.Log;
import com.google.android.exoplayer.extractor.mp4.PsshAtomUtil;
import android.util.Base64;
import com.google.android.exoplayer.util.ParserUtil;
import java.util.ArrayList;
import java.text.ParseException;
import com.google.android.exoplayer.ParserException;
import java.io.InputStream;
import com.google.android.exoplayer.util.MimeTypes;
import com.google.android.exoplayer.chunk.Format;
import com.google.android.exoplayer.drm.DrmInitData$SchemeInitData;
import java.util.UUID;
import java.util.List;
import java.util.regex.Matcher;
import android.text.TextUtils;
import com.google.android.exoplayer.util.Util;
import com.google.android.exoplayer.util.UriUtil;
import org.xmlpull.v1.XmlPullParser;
import com.google.android.exoplayer.util.Assertions;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import java.util.regex.Pattern;
import com.google.android.exoplayer.upstream.UriLoadable$Parser;
import org.xml.sax.helpers.DefaultHandler;

public class MediaPresentationDescriptionParser extends DefaultHandler implements UriLoadable$Parser<MediaPresentationDescription>
{
    private static final Pattern FRAME_RATE_PATTERN;
    private final String contentId;
    private final XmlPullParserFactory xmlParserFactory;
    
    static {
        FRAME_RATE_PATTERN = Pattern.compile("(\\d+)(?:/(\\d+))?");
    }
    
    public MediaPresentationDescriptionParser() {
        this(null);
    }
    
    public MediaPresentationDescriptionParser(final String contentId) {
        this.contentId = contentId;
        try {
            this.xmlParserFactory = XmlPullParserFactory.newInstance();
        }
        catch (XmlPullParserException ex) {
            throw new RuntimeException("Couldn't create XmlPullParserFactory instance", (Throwable)ex);
        }
    }
    
    private static int checkContentTypeConsistency(final int n, final int n2) {
        int n3;
        if (n == -1) {
            n3 = n2;
        }
        else {
            n3 = n;
            if (n2 != -1) {
                Assertions.checkState(n == n2);
                return n;
            }
        }
        return n3;
    }
    
    private static String checkLanguageConsistency(final String s, final String s2) {
        String s3;
        if (s == null) {
            s3 = s2;
        }
        else {
            s3 = s;
            if (s2 != null) {
                Assertions.checkState(s.equals(s2));
                return s;
            }
        }
        return s3;
    }
    
    protected static String parseBaseUrl(final XmlPullParser xmlPullParser, final String s) {
        xmlPullParser.next();
        return UriUtil.resolve(s, xmlPullParser.getText());
    }
    
    protected static long parseDateTime(final XmlPullParser xmlPullParser, final String s, final long n) {
        final String attributeValue = xmlPullParser.getAttributeValue((String)null, s);
        if (attributeValue == null) {
            return n;
        }
        return Util.parseXsDateTime(attributeValue);
    }
    
    protected static long parseDuration(final XmlPullParser xmlPullParser, final String s, final long n) {
        final String attributeValue = xmlPullParser.getAttributeValue((String)null, s);
        if (attributeValue == null) {
            return n;
        }
        return Util.parseXsDuration(attributeValue);
    }
    
    protected static float parseFrameRate(final XmlPullParser xmlPullParser, final float n) {
        final String attributeValue = xmlPullParser.getAttributeValue((String)null, "frameRate");
        float n2 = n;
        if (attributeValue != null) {
            final Matcher matcher = MediaPresentationDescriptionParser.FRAME_RATE_PATTERN.matcher(attributeValue);
            n2 = n;
            if (matcher.matches()) {
                final int int1 = Integer.parseInt(matcher.group(1));
                final String group = matcher.group(2);
                if (TextUtils.isEmpty((CharSequence)group)) {
                    return int1;
                }
                n2 = int1 / Integer.parseInt(group);
            }
        }
        return n2;
    }
    
    protected static int parseInt(final XmlPullParser xmlPullParser, final String s) {
        return parseInt(xmlPullParser, s, -1);
    }
    
    protected static int parseInt(final XmlPullParser xmlPullParser, final String s, final int n) {
        final String attributeValue = xmlPullParser.getAttributeValue((String)null, s);
        if (attributeValue == null) {
            return n;
        }
        return Integer.parseInt(attributeValue);
    }
    
    protected static long parseLong(final XmlPullParser xmlPullParser, final String s) {
        return parseLong(xmlPullParser, s, -1L);
    }
    
    protected static long parseLong(final XmlPullParser xmlPullParser, final String s, final long n) {
        final String attributeValue = xmlPullParser.getAttributeValue((String)null, s);
        if (attributeValue == null) {
            return n;
        }
        return Long.parseLong(attributeValue);
    }
    
    protected static String parseString(final XmlPullParser xmlPullParser, final String s, final String s2) {
        final String attributeValue = xmlPullParser.getAttributeValue((String)null, s);
        if (attributeValue == null) {
            return s2;
        }
        return attributeValue;
    }
    
    protected AdaptationSet buildAdaptationSet(final int n, final int n2, final List<Representation> list, final List<ContentProtection> list2) {
        return new AdaptationSet(n, n2, list, list2);
    }
    
    protected ContentProtection buildContentProtection(final String s, final UUID uuid, final DrmInitData$SchemeInitData drmInitData$SchemeInitData) {
        return new ContentProtection(s, uuid, drmInitData$SchemeInitData);
    }
    
    protected Format buildFormat(final String s, final String s2, final int n, final int n2, final float n3, final int n4, final int n5, final int n6, final String s3, final String s4) {
        return new Format(s, s2, n, n2, n3, n4, n5, n6, s3, s4);
    }
    
    protected MediaPresentationDescription buildMediaPresentationDescription(final long n, final long n2, final long n3, final boolean b, final long n4, final long n5, final UtcTimingElement utcTimingElement, final String s, final List<Period> list) {
        return new MediaPresentationDescription(n, n2, n3, b, n4, n5, utcTimingElement, s, list);
    }
    
    protected Period buildPeriod(final String s, final long n, final List<AdaptationSet> list) {
        return new Period(s, n, list);
    }
    
    protected RangedUri buildRangedUri(final String s, final String s2, final long n, final long n2) {
        return new RangedUri(s, s2, n, n2);
    }
    
    protected Representation buildRepresentation(final String s, final int n, final Format format, final SegmentBase segmentBase) {
        return Representation.newInstance(s, n, format, segmentBase);
    }
    
    protected SegmentBase$SegmentList buildSegmentList(final RangedUri rangedUri, final long n, final long n2, final int n3, final long n4, final List<SegmentBase$SegmentTimelineElement> list, final List<RangedUri> list2) {
        return new SegmentBase$SegmentList(rangedUri, n, n2, n3, n4, list, list2);
    }
    
    protected SegmentBase$SegmentTemplate buildSegmentTemplate(final RangedUri rangedUri, final long n, final long n2, final int n3, final long n4, final List<SegmentBase$SegmentTimelineElement> list, final UrlTemplate urlTemplate, final UrlTemplate urlTemplate2, final String s) {
        return new SegmentBase$SegmentTemplate(rangedUri, n, n2, n3, n4, list, urlTemplate, urlTemplate2, s);
    }
    
    protected SegmentBase$SegmentTimelineElement buildSegmentTimelineElement(final long n, final long n2) {
        return new SegmentBase$SegmentTimelineElement(n, n2);
    }
    
    protected SegmentBase$SingleSegmentBase buildSingleSegmentBase(final RangedUri rangedUri, final long n, final long n2, final String s, final long n3, final long n4) {
        return new SegmentBase$SingleSegmentBase(rangedUri, n, n2, s, n3, n4);
    }
    
    protected UtcTimingElement buildUtcTimingElement(final String s, final String s2) {
        return new UtcTimingElement(s, s2);
    }
    
    protected int getContentType(final Representation representation) {
        final String mimeType = representation.format.mimeType;
        if (!TextUtils.isEmpty((CharSequence)mimeType)) {
            if (MimeTypes.isVideo(mimeType)) {
                return 0;
            }
            if (MimeTypes.isAudio(mimeType)) {
                return 1;
            }
            if (MimeTypes.isText(mimeType) || "application/ttml+xml".equals(mimeType)) {
                return 2;
            }
            if ("application/mp4".equals(mimeType)) {
                final String codecs = representation.format.codecs;
                if ("stpp".equals(codecs) || "wvtt".equals(codecs)) {
                    return 2;
                }
            }
        }
        return -1;
    }
    
    @Override
    public MediaPresentationDescription parse(final String s, final InputStream inputStream) {
        try {
            final XmlPullParser pullParser = this.xmlParserFactory.newPullParser();
            pullParser.setInput(inputStream, (String)null);
            if (pullParser.next() != 2 || !"MPD".equals(pullParser.getName())) {
                throw new ParserException("inputStream does not contain a valid media presentation description");
            }
            goto Label_0062;
        }
        catch (XmlPullParserException ex) {
            throw new ParserException((Throwable)ex);
        }
        catch (ParseException ex2) {
            throw new ParserException(ex2);
        }
    }
    
    protected AdaptationSet parseAdaptationSet(final XmlPullParser xmlPullParser, final String s, final SegmentBase segmentBase) {
        final int int1 = parseInt(xmlPullParser, "id", -1);
        int n = this.parseContentType(xmlPullParser);
        final String attributeValue = xmlPullParser.getAttributeValue((String)null, "mimeType");
        final String attributeValue2 = xmlPullParser.getAttributeValue((String)null, "codecs");
        final int int2 = parseInt(xmlPullParser, "width", -1);
        final int int3 = parseInt(xmlPullParser, "height", -1);
        final float frameRate = parseFrameRate(xmlPullParser, -1.0f);
        int audioChannelConfiguration = -1;
        final int int4 = parseInt(xmlPullParser, "audioSamplingRate", -1);
        final String attributeValue3 = xmlPullParser.getAttributeValue((String)null, "lang");
        final MediaPresentationDescriptionParser$ContentProtectionsBuilder mediaPresentationDescriptionParser$ContentProtectionsBuilder = new MediaPresentationDescriptionParser$ContentProtectionsBuilder();
        final ArrayList<Representation> list = new ArrayList<Representation>();
        int n2 = 0;
        String baseUrl = s;
        SegmentBase segmentBase2 = segmentBase;
        String checkLanguageConsistency = attributeValue3;
    Label_0157_Outer:
        while (true) {
            xmlPullParser.next();
            if (ParserUtil.isStartTag(xmlPullParser, "BaseURL")) {
                if (n2 == 0) {
                    baseUrl = parseBaseUrl(xmlPullParser, baseUrl);
                    n2 = 1;
                }
            }
            else if (ParserUtil.isStartTag(xmlPullParser, "ContentProtection")) {
                final ContentProtection contentProtection = this.parseContentProtection(xmlPullParser);
                if (contentProtection != null) {
                    mediaPresentationDescriptionParser$ContentProtectionsBuilder.addAdaptationSetProtection(contentProtection);
                }
            }
            else if (ParserUtil.isStartTag(xmlPullParser, "ContentComponent")) {
                checkLanguageConsistency = checkLanguageConsistency(checkLanguageConsistency, xmlPullParser.getAttributeValue((String)null, "lang"));
                n = checkContentTypeConsistency(n, this.parseContentType(xmlPullParser));
            }
            else if (ParserUtil.isStartTag(xmlPullParser, "Representation")) {
                final Representation representation = this.parseRepresentation(xmlPullParser, baseUrl, attributeValue, attributeValue2, int2, int3, frameRate, audioChannelConfiguration, int4, checkLanguageConsistency, segmentBase2, mediaPresentationDescriptionParser$ContentProtectionsBuilder);
                mediaPresentationDescriptionParser$ContentProtectionsBuilder.endRepresentation();
                n = checkContentTypeConsistency(n, this.getContentType(representation));
                list.add(representation);
            }
            else if (ParserUtil.isStartTag(xmlPullParser, "AudioChannelConfiguration")) {
                audioChannelConfiguration = this.parseAudioChannelConfiguration(xmlPullParser);
            }
            else if (ParserUtil.isStartTag(xmlPullParser, "SegmentBase")) {
                segmentBase2 = this.parseSegmentBase(xmlPullParser, baseUrl, (SegmentBase$SingleSegmentBase)segmentBase2);
            }
            else if (ParserUtil.isStartTag(xmlPullParser, "SegmentList")) {
                segmentBase2 = this.parseSegmentList(xmlPullParser, baseUrl, (SegmentBase$SegmentList)segmentBase2);
            }
            else if (ParserUtil.isStartTag(xmlPullParser, "SegmentTemplate")) {
                segmentBase2 = this.parseSegmentTemplate(xmlPullParser, baseUrl, (SegmentBase$SegmentTemplate)segmentBase2);
            }
            else if (ParserUtil.isStartTag(xmlPullParser)) {
                this.parseAdaptationSetChild(xmlPullParser);
            }
            while (true) {
                if (ParserUtil.isEndTag(xmlPullParser, "AdaptationSet")) {
                    break;
                }
                continue Label_0157_Outer;
                continue;
            }
        }
        return this.buildAdaptationSet(int1, n, list, mediaPresentationDescriptionParser$ContentProtectionsBuilder.build());
    }
    
    protected void parseAdaptationSetChild(final XmlPullParser xmlPullParser) {
    }
    
    protected int parseAudioChannelConfiguration(final XmlPullParser xmlPullParser) {
        int int1;
        if ("urn:mpeg:dash:23003:3:audio_channel_configuration:2011".equals(parseString(xmlPullParser, "schemeIdUri", null))) {
            int1 = parseInt(xmlPullParser, "value");
        }
        else {
            int1 = -1;
        }
        do {
            xmlPullParser.next();
        } while (!ParserUtil.isEndTag(xmlPullParser, "AudioChannelConfiguration"));
        return int1;
    }
    
    protected ContentProtection parseContentProtection(final XmlPullParser xmlPullParser) {
        final String attributeValue = xmlPullParser.getAttributeValue((String)null, "schemeIdUri");
        int n = 0;
        DrmInitData$SchemeInitData drmInitData$SchemeInitData = null;
        UUID uuid = null;
        int n2;
        DrmInitData$SchemeInitData drmInitData$SchemeInitData2;
        UUID uuid2;
        do {
            xmlPullParser.next();
            n2 = n;
            drmInitData$SchemeInitData2 = drmInitData$SchemeInitData;
            uuid2 = uuid;
            if (ParserUtil.isStartTag(xmlPullParser, "cenc:pssh")) {
                n2 = n;
                drmInitData$SchemeInitData2 = drmInitData$SchemeInitData;
                uuid2 = uuid;
                if (xmlPullParser.next() == 4) {
                    n2 = 1;
                    drmInitData$SchemeInitData2 = new DrmInitData$SchemeInitData("video/mp4", Base64.decode(xmlPullParser.getText(), 0));
                    uuid2 = PsshAtomUtil.parseUuid(drmInitData$SchemeInitData2.data);
                }
            }
            n = n2;
            drmInitData$SchemeInitData = drmInitData$SchemeInitData2;
            uuid = uuid2;
        } while (!ParserUtil.isEndTag(xmlPullParser, "ContentProtection"));
        if (n2 != 0 && uuid2 == null) {
            Log.w("MediaPresentationDescriptionParser", "Skipped unsupported ContentProtection element");
            return null;
        }
        return this.buildContentProtection(attributeValue, uuid2, drmInitData$SchemeInitData2);
    }
    
    protected int parseContentType(final XmlPullParser xmlPullParser) {
        final String attributeValue = xmlPullParser.getAttributeValue((String)null, "contentType");
        if (!TextUtils.isEmpty((CharSequence)attributeValue)) {
            if ("audio".equals(attributeValue)) {
                return 1;
            }
            if ("video".equals(attributeValue)) {
                return 0;
            }
            if ("text".equals(attributeValue)) {
                return 2;
            }
        }
        return -1;
    }
    
    protected RangedUri parseInitialization(final XmlPullParser xmlPullParser, final String s) {
        return this.parseRangedUrl(xmlPullParser, s, "sourceURL", "range");
    }
    
    protected MediaPresentationDescription parseMediaPresentationDescription(final XmlPullParser xmlPullParser, String baseUrl) {
        final long dateTime = parseDateTime(xmlPullParser, "availabilityStartTime", -1L);
        final long duration = parseDuration(xmlPullParser, "mediaPresentationDuration", -1L);
        final long duration2 = parseDuration(xmlPullParser, "minBufferTime", -1L);
        final String attributeValue = xmlPullParser.getAttributeValue((String)null, "type");
        final boolean b = attributeValue != null && attributeValue.equals("dynamic");
        long duration3;
        if (b) {
            duration3 = parseDuration(xmlPullParser, "minimumUpdatePeriod", -1L);
        }
        else {
            duration3 = -1L;
        }
        long duration4;
        if (b) {
            duration4 = parseDuration(xmlPullParser, "timeShiftBufferDepth", -1L);
        }
        else {
            duration4 = -1L;
        }
        UtcTimingElement utcTiming = null;
        String nextText = null;
        final ArrayList<Period> list = new ArrayList<Period>();
        long n;
        if (b) {
            n = -1L;
        }
        else {
            n = 0L;
        }
        int n2 = 0;
        int n3 = 0;
    Label_0168_Outer:
        while (true) {
            xmlPullParser.next();
            while (true) {
                long n4 = 0L;
                Label_0449: {
                    int n5;
                    int n6;
                    if (ParserUtil.isStartTag(xmlPullParser, "BaseURL")) {
                        n4 = n;
                        if (n3 != 0) {
                            break Label_0449;
                        }
                        baseUrl = parseBaseUrl(xmlPullParser, baseUrl);
                        final boolean b2 = true;
                        n5 = n2;
                        n6 = (b2 ? 1 : 0);
                    }
                    else if (ParserUtil.isStartTag(xmlPullParser, "UTCTiming")) {
                        utcTiming = this.parseUtcTiming(xmlPullParser);
                        final int n7 = n2;
                        n6 = n3;
                        n5 = n7;
                    }
                    else if (ParserUtil.isStartTag(xmlPullParser, "Location")) {
                        nextText = xmlPullParser.nextText();
                        final int n8 = n2;
                        n6 = n3;
                        n5 = n8;
                    }
                    else {
                        n4 = n;
                        if (!ParserUtil.isStartTag(xmlPullParser, "Period")) {
                            break Label_0449;
                        }
                        n4 = n;
                        if (n2 != 0) {
                            break Label_0449;
                        }
                        final Pair<Period, Long> period = this.parsePeriod(xmlPullParser, baseUrl, n);
                        final Period period2 = (Period)period.first;
                        if (period2.startMs != -1L) {
                            final long longValue = (long)period.second;
                            if (longValue == -1L) {
                                n4 = -1L;
                            }
                            else {
                                n4 = longValue + period2.startMs;
                            }
                            list.add(period2);
                            break Label_0449;
                        }
                        if (!b) {
                            throw new ParserException("Unable to determine start of period " + list.size());
                        }
                        final boolean b3 = true;
                        n6 = n3;
                        n5 = (b3 ? 1 : 0);
                    }
                    if (ParserUtil.isEndTag(xmlPullParser, "MPD")) {
                        while (true) {
                            Label_0518: {
                                if (duration != -1L) {
                                    break Label_0518;
                                }
                                if (n == -1L) {
                                    if (!b) {
                                        throw new ParserException("Unable to determine duration of static manifest.");
                                    }
                                    break Label_0518;
                                }
                                if (list.isEmpty()) {
                                    throw new ParserException("No periods found.");
                                }
                                return this.buildMediaPresentationDescription(dateTime, n, duration2, b, duration3, duration4, utcTiming, nextText, list);
                            }
                            n = duration;
                            continue;
                        }
                    }
                    final int n9 = n5;
                    n3 = n6;
                    n2 = n9;
                    continue Label_0168_Outer;
                }
                final int n10 = n2;
                n = n4;
                int n6 = n3;
                int n5 = n10;
                continue;
            }
        }
    }
    
    protected Pair<Period, Long> parsePeriod(final XmlPullParser xmlPullParser, final String s, long duration) {
        final String attributeValue = xmlPullParser.getAttributeValue((String)null, "id");
        duration = parseDuration(xmlPullParser, "start", duration);
        final long duration2 = parseDuration(xmlPullParser, "duration", -1L);
        final ArrayList<AdaptationSet> list = new ArrayList<AdaptationSet>();
        int n = 0;
        SegmentBase segmentBase = null;
        String s2 = s;
        do {
            xmlPullParser.next();
            int n2;
            SegmentBase segmentBase2;
            String baseUrl;
            if (ParserUtil.isStartTag(xmlPullParser, "BaseURL")) {
                n2 = n;
                segmentBase2 = segmentBase;
                baseUrl = s2;
                if (n == 0) {
                    baseUrl = parseBaseUrl(xmlPullParser, s2);
                    n2 = 1;
                    segmentBase2 = segmentBase;
                }
            }
            else if (ParserUtil.isStartTag(xmlPullParser, "AdaptationSet")) {
                list.add(this.parseAdaptationSet(xmlPullParser, s2, segmentBase));
                n2 = n;
                segmentBase2 = segmentBase;
                baseUrl = s2;
            }
            else if (ParserUtil.isStartTag(xmlPullParser, "SegmentBase")) {
                segmentBase2 = this.parseSegmentBase(xmlPullParser, s2, null);
                n2 = n;
                baseUrl = s2;
            }
            else if (ParserUtil.isStartTag(xmlPullParser, "SegmentList")) {
                segmentBase2 = this.parseSegmentList(xmlPullParser, s2, null);
                n2 = n;
                baseUrl = s2;
            }
            else {
                n2 = n;
                segmentBase2 = segmentBase;
                baseUrl = s2;
                if (ParserUtil.isStartTag(xmlPullParser, "SegmentTemplate")) {
                    segmentBase2 = this.parseSegmentTemplate(xmlPullParser, s2, null);
                    n2 = n;
                    baseUrl = s2;
                }
            }
            n = n2;
            segmentBase = segmentBase2;
            s2 = baseUrl;
        } while (!ParserUtil.isEndTag(xmlPullParser, "Period"));
        return (Pair<Period, Long>)Pair.create((Object)this.buildPeriod(attributeValue, duration, list), (Object)duration2);
    }
    
    protected RangedUri parseRangedUrl(final XmlPullParser xmlPullParser, final String s, String attributeValue, final String s2) {
        attributeValue = xmlPullParser.getAttributeValue((String)null, attributeValue);
        long long1 = 0L;
        final long n = -1L;
        final String attributeValue2 = xmlPullParser.getAttributeValue((String)null, s2);
        long n2 = n;
        if (attributeValue2 != null) {
            final String[] split = attributeValue2.split("-");
            final long n3 = long1 = Long.parseLong(split[0]);
            n2 = n;
            if (split.length == 2) {
                n2 = 1L + (Long.parseLong(split[1]) - n3);
                long1 = n3;
            }
        }
        return this.buildRangedUri(s, attributeValue, long1, n2);
    }
    
    protected Representation parseRepresentation(final XmlPullParser xmlPullParser, String s, String s2, String s3, int n, int n2, float frameRate, int audioChannelConfiguration, int int1, final String s4, final SegmentBase segmentBase, final MediaPresentationDescriptionParser$ContentProtectionsBuilder mediaPresentationDescriptionParser$ContentProtectionsBuilder) {
        final String attributeValue = xmlPullParser.getAttributeValue((String)null, "id");
        final int int2 = parseInt(xmlPullParser, "bandwidth");
        final String string = parseString(xmlPullParser, "mimeType", s2);
        final String string2 = parseString(xmlPullParser, "codecs", s3);
        final int int3 = parseInt(xmlPullParser, "width", n);
        final int int4 = parseInt(xmlPullParser, "height", n2);
        frameRate = parseFrameRate(xmlPullParser, frameRate);
        int1 = parseInt(xmlPullParser, "audioSamplingRate", int1);
        n2 = 0;
        n = audioChannelConfiguration;
        SegmentBase segmentBase2 = segmentBase;
        SegmentBase segmentBase3 = null;
    Label_0138_Outer:
        while (true) {
            xmlPullParser.next();
            while (true) {
                Label_0384: {
                    if (ParserUtil.isStartTag(xmlPullParser, "BaseURL")) {
                        if (n2 != 0) {
                            break Label_0384;
                        }
                        s3 = parseBaseUrl(xmlPullParser, s);
                        segmentBase3 = segmentBase2;
                        audioChannelConfiguration = 1;
                        s2 = s3;
                        n2 = n;
                        n = audioChannelConfiguration;
                    }
                    else if (ParserUtil.isStartTag(xmlPullParser, "AudioChannelConfiguration")) {
                        audioChannelConfiguration = this.parseAudioChannelConfiguration(xmlPullParser);
                        s3 = s;
                        segmentBase3 = segmentBase2;
                        n = n2;
                        n2 = audioChannelConfiguration;
                        s2 = s3;
                    }
                    else if (ParserUtil.isStartTag(xmlPullParser, "SegmentBase")) {
                        final SegmentBase$SingleSegmentBase segmentBase4 = this.parseSegmentBase(xmlPullParser, s, (SegmentBase$SingleSegmentBase)segmentBase2);
                        s2 = s;
                        segmentBase3 = segmentBase4;
                        audioChannelConfiguration = n;
                        n = n2;
                        n2 = audioChannelConfiguration;
                    }
                    else if (ParserUtil.isStartTag(xmlPullParser, "SegmentList")) {
                        final SegmentBase$SegmentList segmentList = this.parseSegmentList(xmlPullParser, s, (SegmentBase$SegmentList)segmentBase2);
                        s2 = s;
                        segmentBase3 = segmentList;
                        audioChannelConfiguration = n;
                        n = n2;
                        n2 = audioChannelConfiguration;
                    }
                    else if (ParserUtil.isStartTag(xmlPullParser, "SegmentTemplate")) {
                        final SegmentBase$SegmentTemplate segmentTemplate = this.parseSegmentTemplate(xmlPullParser, s, (SegmentBase$SegmentTemplate)segmentBase2);
                        s2 = s;
                        segmentBase3 = segmentTemplate;
                        audioChannelConfiguration = n;
                        n = n2;
                        n2 = audioChannelConfiguration;
                    }
                    else {
                        if (!ParserUtil.isStartTag(xmlPullParser, "ContentProtection")) {
                            break Label_0384;
                        }
                        final ContentProtection contentProtection = this.parseContentProtection(xmlPullParser);
                        if (contentProtection != null) {
                            mediaPresentationDescriptionParser$ContentProtectionsBuilder.addAdaptationSetProtection(contentProtection);
                        }
                        break Label_0384;
                    }
                    if (ParserUtil.isEndTag(xmlPullParser, "Representation")) {
                        break;
                    }
                    audioChannelConfiguration = n2;
                    s3 = s2;
                    segmentBase2 = segmentBase3;
                    n2 = n;
                    n = audioChannelConfiguration;
                    s = s3;
                    continue Label_0138_Outer;
                }
                s3 = s;
                segmentBase3 = segmentBase2;
                audioChannelConfiguration = n;
                n = n2;
                n2 = audioChannelConfiguration;
                s2 = s3;
                continue;
            }
        }
        final Format buildFormat = this.buildFormat(attributeValue, string, int3, int4, frameRate, n2, int1, int2, s4, string2);
        s3 = this.contentId;
        if (segmentBase3 == null) {
            segmentBase3 = new SegmentBase$SingleSegmentBase(s2);
        }
        return this.buildRepresentation(s3, -1, buildFormat, segmentBase3);
    }
    
    protected SegmentBase$SingleSegmentBase parseSegmentBase(final XmlPullParser xmlPullParser, final String s, final SegmentBase$SingleSegmentBase segmentBase$SingleSegmentBase) {
        long timescale;
        if (segmentBase$SingleSegmentBase != null) {
            timescale = segmentBase$SingleSegmentBase.timescale;
        }
        else {
            timescale = 1L;
        }
        final long long1 = parseLong(xmlPullParser, "timescale", timescale);
        long presentationTimeOffset;
        if (segmentBase$SingleSegmentBase != null) {
            presentationTimeOffset = segmentBase$SingleSegmentBase.presentationTimeOffset;
        }
        else {
            presentationTimeOffset = 0L;
        }
        final long long2 = parseLong(xmlPullParser, "presentationTimeOffset", presentationTimeOffset);
        long n;
        if (segmentBase$SingleSegmentBase != null) {
            n = segmentBase$SingleSegmentBase.indexStart;
        }
        else {
            n = 0L;
        }
        long indexLength;
        if (segmentBase$SingleSegmentBase != null) {
            indexLength = segmentBase$SingleSegmentBase.indexLength;
        }
        else {
            indexLength = -1L;
        }
        final String attributeValue = xmlPullParser.getAttributeValue((String)null, "indexRange");
        if (attributeValue != null) {
            final String[] split = attributeValue.split("-");
            n = Long.parseLong(split[0]);
            indexLength = Long.parseLong(split[1]) - n + 1L;
        }
        RangedUri rangedUri;
        if (segmentBase$SingleSegmentBase != null) {
            rangedUri = segmentBase$SingleSegmentBase.initialization;
        }
        else {
            rangedUri = null;
        }
        do {
            xmlPullParser.next();
            if (ParserUtil.isStartTag(xmlPullParser, "Initialization")) {
                rangedUri = this.parseInitialization(xmlPullParser, s);
            }
        } while (!ParserUtil.isEndTag(xmlPullParser, "SegmentBase"));
        return this.buildSingleSegmentBase(rangedUri, long1, long2, s, n, indexLength);
    }
    
    protected SegmentBase$SegmentList parseSegmentList(final XmlPullParser xmlPullParser, final String s, final SegmentBase$SegmentList list) {
        RangedUri rangedUri = null;
        long timescale;
        if (list != null) {
            timescale = list.timescale;
        }
        else {
            timescale = 1L;
        }
        final long long1 = parseLong(xmlPullParser, "timescale", timescale);
        long presentationTimeOffset;
        if (list != null) {
            presentationTimeOffset = list.presentationTimeOffset;
        }
        else {
            presentationTimeOffset = 0L;
        }
        final long long2 = parseLong(xmlPullParser, "presentationTimeOffset", presentationTimeOffset);
        long duration;
        if (list != null) {
            duration = list.duration;
        }
        else {
            duration = -1L;
        }
        final long long3 = parseLong(xmlPullParser, "duration", duration);
        int startNumber;
        if (list != null) {
            startNumber = list.startNumber;
        }
        else {
            startNumber = 1;
        }
        final int int1 = parseInt(xmlPullParser, "startNumber", startNumber);
        List<RangedUri> list2 = null;
        List<SegmentBase$SegmentTimelineElement> list3 = null;
        RangedUri initialization;
        List<SegmentBase$SegmentTimelineElement> segmentTimeline;
        List<RangedUri> mediaSegments;
        do {
            xmlPullParser.next();
            if (ParserUtil.isStartTag(xmlPullParser, "Initialization")) {
                initialization = this.parseInitialization(xmlPullParser, s);
                segmentTimeline = list3;
                mediaSegments = list2;
            }
            else if (ParserUtil.isStartTag(xmlPullParser, "SegmentTimeline")) {
                segmentTimeline = this.parseSegmentTimeline(xmlPullParser);
                mediaSegments = list2;
                initialization = rangedUri;
            }
            else {
                mediaSegments = list2;
                segmentTimeline = list3;
                initialization = rangedUri;
                if (ParserUtil.isStartTag(xmlPullParser, "SegmentURL")) {
                    if ((mediaSegments = list2) == null) {
                        mediaSegments = new ArrayList<RangedUri>();
                    }
                    mediaSegments.add(this.parseSegmentUrl(xmlPullParser, s));
                    segmentTimeline = list3;
                    initialization = rangedUri;
                }
            }
            list2 = mediaSegments;
            list3 = segmentTimeline;
            rangedUri = initialization;
        } while (!ParserUtil.isEndTag(xmlPullParser, "SegmentList"));
        RangedUri initialization2;
        List<SegmentBase$SegmentTimelineElement> segmentTimeline2;
        if (list != null) {
            if (initialization != null) {
                initialization2 = initialization;
            }
            else {
                initialization2 = list.initialization;
            }
            if (segmentTimeline != null) {
                segmentTimeline2 = segmentTimeline;
            }
            else {
                segmentTimeline2 = list.segmentTimeline;
            }
            if (mediaSegments == null) {
                mediaSegments = list.mediaSegments;
            }
        }
        else {
            initialization2 = initialization;
            segmentTimeline2 = segmentTimeline;
        }
        return this.buildSegmentList(initialization2, long1, long2, int1, long3, segmentTimeline2, mediaSegments);
    }
    
    protected SegmentBase$SegmentTemplate parseSegmentTemplate(final XmlPullParser xmlPullParser, final String s, final SegmentBase$SegmentTemplate segmentBase$SegmentTemplate) {
        long timescale;
        if (segmentBase$SegmentTemplate != null) {
            timescale = segmentBase$SegmentTemplate.timescale;
        }
        else {
            timescale = 1L;
        }
        final long long1 = parseLong(xmlPullParser, "timescale", timescale);
        long presentationTimeOffset;
        if (segmentBase$SegmentTemplate != null) {
            presentationTimeOffset = segmentBase$SegmentTemplate.presentationTimeOffset;
        }
        else {
            presentationTimeOffset = 0L;
        }
        final long long2 = parseLong(xmlPullParser, "presentationTimeOffset", presentationTimeOffset);
        long duration;
        if (segmentBase$SegmentTemplate != null) {
            duration = segmentBase$SegmentTemplate.duration;
        }
        else {
            duration = -1L;
        }
        final long long3 = parseLong(xmlPullParser, "duration", duration);
        int startNumber;
        if (segmentBase$SegmentTemplate != null) {
            startNumber = segmentBase$SegmentTemplate.startNumber;
        }
        else {
            startNumber = 1;
        }
        final int int1 = parseInt(xmlPullParser, "startNumber", startNumber);
        UrlTemplate mediaTemplate;
        if (segmentBase$SegmentTemplate != null) {
            mediaTemplate = segmentBase$SegmentTemplate.mediaTemplate;
        }
        else {
            mediaTemplate = null;
        }
        final UrlTemplate urlTemplate = this.parseUrlTemplate(xmlPullParser, "media", mediaTemplate);
        UrlTemplate initializationTemplate;
        if (segmentBase$SegmentTemplate != null) {
            initializationTemplate = segmentBase$SegmentTemplate.initializationTemplate;
        }
        else {
            initializationTemplate = null;
        }
        final UrlTemplate urlTemplate2 = this.parseUrlTemplate(xmlPullParser, "initialization", initializationTemplate);
        RangedUri rangedUri = null;
        List<SegmentBase$SegmentTimelineElement> list = null;
        RangedUri rangedUri2;
        List<SegmentBase$SegmentTimelineElement> list2;
        do {
            xmlPullParser.next();
            if (ParserUtil.isStartTag(xmlPullParser, "Initialization")) {
                rangedUri2 = this.parseInitialization(xmlPullParser, s);
                list2 = list;
            }
            else {
                list2 = list;
                rangedUri2 = rangedUri;
                if (ParserUtil.isStartTag(xmlPullParser, "SegmentTimeline")) {
                    list2 = this.parseSegmentTimeline(xmlPullParser);
                    rangedUri2 = rangedUri;
                }
            }
            list = list2;
            rangedUri = rangedUri2;
        } while (!ParserUtil.isEndTag(xmlPullParser, "SegmentTemplate"));
        List<SegmentBase$SegmentTimelineElement> list3;
        if (segmentBase$SegmentTemplate != null) {
            if (rangedUri2 == null) {
                rangedUri2 = segmentBase$SegmentTemplate.initialization;
            }
            if (list2 == null) {
                list2 = segmentBase$SegmentTemplate.segmentTimeline;
            }
            list3 = list2;
        }
        else {
            list3 = list2;
        }
        return this.buildSegmentTemplate(rangedUri2, long1, long2, int1, long3, list3, urlTemplate2, urlTemplate, s);
    }
    
    protected List<SegmentBase$SegmentTimelineElement> parseSegmentTimeline(final XmlPullParser xmlPullParser) {
        final ArrayList<SegmentBase$SegmentTimelineElement> list = new ArrayList<SegmentBase$SegmentTimelineElement>();
        long n = 0L;
        do {
            xmlPullParser.next();
            long n2 = n;
            if (ParserUtil.isStartTag(xmlPullParser, "S")) {
                long long1 = parseLong(xmlPullParser, "t", n);
                final long long2 = parseLong(xmlPullParser, "d");
                final int int1 = parseInt(xmlPullParser, "r", 0);
                int n3 = 0;
                while (true) {
                    n2 = long1;
                    if (n3 >= int1 + 1) {
                        break;
                    }
                    list.add(this.buildSegmentTimelineElement(long1, long2));
                    ++n3;
                    long1 += long2;
                }
            }
            n = n2;
        } while (!ParserUtil.isEndTag(xmlPullParser, "SegmentTimeline"));
        return list;
    }
    
    protected RangedUri parseSegmentUrl(final XmlPullParser xmlPullParser, final String s) {
        return this.parseRangedUrl(xmlPullParser, s, "media", "mediaRange");
    }
    
    protected UrlTemplate parseUrlTemplate(final XmlPullParser xmlPullParser, final String s, UrlTemplate compile) {
        final String attributeValue = xmlPullParser.getAttributeValue((String)null, s);
        if (attributeValue != null) {
            compile = UrlTemplate.compile(attributeValue);
        }
        return compile;
    }
    
    protected UtcTimingElement parseUtcTiming(final XmlPullParser xmlPullParser) {
        return this.buildUtcTimingElement(xmlPullParser.getAttributeValue((String)null, "schemeIdUri"), xmlPullParser.getAttributeValue((String)null, "value"));
    }
}
