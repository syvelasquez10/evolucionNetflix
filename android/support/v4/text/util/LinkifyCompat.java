// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.text.util;

import java.util.List;
import java.util.Collections;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import android.webkit.WebView;
import java.util.regex.Matcher;
import java.util.Locale;
import java.util.Iterator;
import android.support.v4.util.PatternsCompat;
import java.util.ArrayList;
import android.text.util.Linkify;
import android.text.style.URLSpan;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.util.Linkify$TransformFilter;
import android.text.util.Linkify$MatchFilter;
import java.util.regex.Pattern;
import android.text.method.MovementMethod;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import java.util.Comparator;

public final class LinkifyCompat
{
    private static final Comparator<LinkifyCompat$LinkSpec> COMPARATOR;
    private static final String[] EMPTY_STRING;
    
    static {
        EMPTY_STRING = new String[0];
        COMPARATOR = new LinkifyCompat$1();
    }
    
    private static void addLinkMovementMethod(final TextView textView) {
        final MovementMethod movementMethod = textView.getMovementMethod();
        if ((movementMethod == null || !(movementMethod instanceof LinkMovementMethod)) && textView.getLinksClickable()) {
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }
    
    public static final void addLinks(final TextView textView, final Pattern pattern, final String s) {
        addLinks(textView, pattern, s, null, null, null);
    }
    
    public static final void addLinks(final TextView textView, final Pattern pattern, final String s, final Linkify$MatchFilter linkify$MatchFilter, final Linkify$TransformFilter linkify$TransformFilter) {
        addLinks(textView, pattern, s, null, linkify$MatchFilter, linkify$TransformFilter);
    }
    
    public static final void addLinks(final TextView textView, final Pattern pattern, final String s, final String[] array, final Linkify$MatchFilter linkify$MatchFilter, final Linkify$TransformFilter linkify$TransformFilter) {
        final SpannableString value = SpannableString.valueOf(textView.getText());
        if (addLinks((Spannable)value, pattern, s, array, linkify$MatchFilter, linkify$TransformFilter)) {
            textView.setText((CharSequence)value);
            addLinkMovementMethod(textView);
        }
    }
    
    public static final boolean addLinks(final Spannable spannable, final int n) {
        if (n == 0) {
            return false;
        }
        final URLSpan[] array = (URLSpan[])spannable.getSpans(0, spannable.length(), (Class)URLSpan.class);
        for (int i = array.length - 1; i >= 0; --i) {
            spannable.removeSpan((Object)array[i]);
        }
        if ((n & 0x4) != 0x0) {
            Linkify.addLinks(spannable, 4);
        }
        final ArrayList list = new ArrayList<LinkifyCompat$LinkSpec>();
        if ((n & 0x1) != 0x0) {
            gatherLinks(list, spannable, PatternsCompat.AUTOLINK_WEB_URL, new String[] { "http://", "https://", "rtsp://" }, Linkify.sUrlMatchFilter, null);
        }
        if ((n & 0x2) != 0x0) {
            gatherLinks(list, spannable, PatternsCompat.AUTOLINK_EMAIL_ADDRESS, new String[] { "mailto:" }, null, null);
        }
        if ((n & 0x8) != 0x0) {
            gatherMapLinks(list, spannable);
        }
        pruneOverlaps(list, spannable);
        if (list.size() == 0) {
            return false;
        }
        for (final LinkifyCompat$LinkSpec linkifyCompat$LinkSpec : list) {
            if (linkifyCompat$LinkSpec.frameworkAddedSpan == null) {
                applyLink(linkifyCompat$LinkSpec.url, linkifyCompat$LinkSpec.start, linkifyCompat$LinkSpec.end, spannable);
            }
        }
        return true;
    }
    
    public static final boolean addLinks(final Spannable spannable, final Pattern pattern, final String s) {
        return addLinks(spannable, pattern, s, null, null, null);
    }
    
    public static final boolean addLinks(final Spannable spannable, final Pattern pattern, final String s, final Linkify$MatchFilter linkify$MatchFilter, final Linkify$TransformFilter linkify$TransformFilter) {
        return addLinks(spannable, pattern, s, null, linkify$MatchFilter, linkify$TransformFilter);
    }
    
    public static final boolean addLinks(final Spannable spannable, final Pattern pattern, final String s, final String[] array, final Linkify$MatchFilter linkify$MatchFilter, final Linkify$TransformFilter linkify$TransformFilter) {
        String s2 = s;
        if (s == null) {
            s2 = "";
        }
        String[] empty_STRING = null;
        Label_0027: {
            if (array != null) {
                empty_STRING = array;
                if (array.length >= 1) {
                    break Label_0027;
                }
            }
            empty_STRING = LinkifyCompat.EMPTY_STRING;
        }
        final String[] array2 = new String[empty_STRING.length + 1];
        array2[0] = s2.toLowerCase(Locale.ROOT);
        for (int i = 0; i < empty_STRING.length; ++i) {
            final String s3 = empty_STRING[i];
            String lowerCase;
            if (s3 == null) {
                lowerCase = "";
            }
            else {
                lowerCase = s3.toLowerCase(Locale.ROOT);
            }
            array2[i + 1] = lowerCase;
        }
        final Matcher matcher = pattern.matcher((CharSequence)spannable);
        boolean b = false;
        while (matcher.find()) {
            final int start = matcher.start();
            final int end = matcher.end();
            if (linkify$MatchFilter == null || linkify$MatchFilter.acceptMatch((CharSequence)spannable, start, end)) {
                applyLink(makeUrl(matcher.group(0), array2, matcher, linkify$TransformFilter), start, end, spannable);
                b = true;
            }
        }
        return b;
    }
    
    public static final boolean addLinks(final TextView textView, final int n) {
        if (n == 0) {
            return false;
        }
        final CharSequence text = textView.getText();
        if (text instanceof Spannable) {
            if (addLinks((Spannable)text, n)) {
                addLinkMovementMethod(textView);
                return true;
            }
            return false;
        }
        else {
            final SpannableString value = SpannableString.valueOf(text);
            if (addLinks((Spannable)value, n)) {
                addLinkMovementMethod(textView);
                textView.setText((CharSequence)value);
                return true;
            }
            return false;
        }
    }
    
    private static void applyLink(final String s, final int n, final int n2, final Spannable spannable) {
        spannable.setSpan((Object)new URLSpan(s), n, n2, 33);
    }
    
    private static void gatherLinks(final ArrayList<LinkifyCompat$LinkSpec> list, final Spannable spannable, final Pattern pattern, final String[] array, final Linkify$MatchFilter linkify$MatchFilter, final Linkify$TransformFilter linkify$TransformFilter) {
        final Matcher matcher = pattern.matcher((CharSequence)spannable);
        while (matcher.find()) {
            final int start = matcher.start();
            final int end = matcher.end();
            if (linkify$MatchFilter == null || linkify$MatchFilter.acceptMatch((CharSequence)spannable, start, end)) {
                final LinkifyCompat$LinkSpec linkifyCompat$LinkSpec = new LinkifyCompat$LinkSpec();
                linkifyCompat$LinkSpec.url = makeUrl(matcher.group(0), array, matcher, linkify$TransformFilter);
                linkifyCompat$LinkSpec.start = start;
                linkifyCompat$LinkSpec.end = end;
                list.add(linkifyCompat$LinkSpec);
            }
        }
    }
    
    private static final void gatherMapLinks(final ArrayList<LinkifyCompat$LinkSpec> ex, Spannable spannable) {
        spannable = (Spannable)spannable.toString();
        int n = 0;
        while (true) {
            String address;
            LinkifyCompat$LinkSpec linkifyCompat$LinkSpec;
            try {
                while (true) {
                    address = WebView.findAddress((String)spannable);
                    if (address == null) {
                        return;
                    }
                    final int index = ((String)spannable).indexOf(address);
                    if (index < 0) {
                        break;
                    }
                    linkifyCompat$LinkSpec = new LinkifyCompat$LinkSpec();
                    final int n2 = address.length() + index;
                    linkifyCompat$LinkSpec.start = index + n;
                    linkifyCompat$LinkSpec.end = n + n2;
                    spannable = (Spannable)((String)spannable).substring(n2);
                    n += n2;
                    final String s = address;
                    final String s2 = "UTF-8";
                    final String s3 = URLEncoder.encode(s, s2);
                    final LinkifyCompat$LinkSpec linkifyCompat$LinkSpec2 = linkifyCompat$LinkSpec;
                    final StringBuilder sb = new StringBuilder();
                    final String s4 = "geo:0,0?q=";
                    final StringBuilder sb2 = sb.append(s4);
                    final String s5 = s3;
                    final StringBuilder sb3 = sb2.append(s5);
                    final String s6 = sb3.toString();
                    linkifyCompat$LinkSpec2.url = s6;
                    final ArrayList<LinkifyCompat$LinkSpec> list = (ArrayList<LinkifyCompat$LinkSpec>)ex;
                    final LinkifyCompat$LinkSpec linkifyCompat$LinkSpec3 = linkifyCompat$LinkSpec;
                    list.add(linkifyCompat$LinkSpec3);
                }
                return;
            }
            catch (UnsupportedOperationException ex) {
                return;
            }
            try {
                final String s = address;
                final String s2 = "UTF-8";
                final String s3 = URLEncoder.encode(s, s2);
                final LinkifyCompat$LinkSpec linkifyCompat$LinkSpec2 = linkifyCompat$LinkSpec;
                final StringBuilder sb = new StringBuilder();
                final String s4 = "geo:0,0?q=";
                final StringBuilder sb2 = sb.append(s4);
                final String s5 = s3;
                final StringBuilder sb3 = sb2.append(s5);
                final String s6 = sb3.toString();
                linkifyCompat$LinkSpec2.url = s6;
                final ArrayList<LinkifyCompat$LinkSpec> list = (ArrayList<LinkifyCompat$LinkSpec>)ex;
                final LinkifyCompat$LinkSpec linkifyCompat$LinkSpec3 = linkifyCompat$LinkSpec;
                list.add(linkifyCompat$LinkSpec3);
                continue;
            }
            catch (UnsupportedEncodingException ex2) {
                continue;
            }
            break;
        }
    }
    
    private static String makeUrl(String string, final String[] array, final Matcher matcher, final Linkify$TransformFilter linkify$TransformFilter) {
        final boolean b = true;
        String transformUrl;
        if (linkify$TransformFilter != null) {
            transformUrl = linkify$TransformFilter.transformUrl(matcher, string);
        }
        else {
            transformUrl = string;
        }
        while (true) {
            for (int i = 0; i < array.length; ++i) {
                if (transformUrl.regionMatches(true, 0, array[i], 0, array[i].length())) {
                    string = transformUrl;
                    int n = b ? 1 : 0;
                    if (!transformUrl.regionMatches(false, 0, array[i], 0, array[i].length())) {
                        string = array[i] + transformUrl.substring(array[i].length());
                        n = (b ? 1 : 0);
                    }
                    String string2 = string;
                    if (n == 0) {
                        string2 = string;
                        if (array.length > 0) {
                            string2 = array[0] + string;
                        }
                    }
                    return string2;
                }
            }
            int n = 0;
            string = transformUrl;
            continue;
        }
    }
    
    private static final void pruneOverlaps(final ArrayList<LinkifyCompat$LinkSpec> list, final Spannable spannable) {
        int i = 0;
        final URLSpan[] array = (URLSpan[])spannable.getSpans(0, spannable.length(), (Class)URLSpan.class);
        for (int j = 0; j < array.length; ++j) {
            final LinkifyCompat$LinkSpec linkifyCompat$LinkSpec = new LinkifyCompat$LinkSpec();
            linkifyCompat$LinkSpec.frameworkAddedSpan = array[j];
            linkifyCompat$LinkSpec.start = spannable.getSpanStart((Object)array[j]);
            linkifyCompat$LinkSpec.end = spannable.getSpanEnd((Object)array[j]);
            list.add(linkifyCompat$LinkSpec);
        }
        Collections.sort((List<Object>)list, (Comparator<? super Object>)LinkifyCompat.COMPARATOR);
        int size = list.size();
        while (i < size - 1) {
            final LinkifyCompat$LinkSpec linkifyCompat$LinkSpec2 = list.get(i);
            final LinkifyCompat$LinkSpec linkifyCompat$LinkSpec3 = list.get(i + 1);
            if (linkifyCompat$LinkSpec2.start <= linkifyCompat$LinkSpec3.start && linkifyCompat$LinkSpec2.end > linkifyCompat$LinkSpec3.start) {
                int n;
                if (linkifyCompat$LinkSpec3.end <= linkifyCompat$LinkSpec2.end) {
                    n = i + 1;
                }
                else if (linkifyCompat$LinkSpec2.end - linkifyCompat$LinkSpec2.start > linkifyCompat$LinkSpec3.end - linkifyCompat$LinkSpec3.start) {
                    n = i + 1;
                }
                else if (linkifyCompat$LinkSpec2.end - linkifyCompat$LinkSpec2.start < linkifyCompat$LinkSpec3.end - linkifyCompat$LinkSpec3.start) {
                    n = i;
                }
                else {
                    n = -1;
                }
                if (n != -1) {
                    final URLSpan frameworkAddedSpan = list.get(n).frameworkAddedSpan;
                    if (frameworkAddedSpan != null) {
                        spannable.removeSpan((Object)frameworkAddedSpan);
                    }
                    list.remove(n);
                    --size;
                    continue;
                }
            }
            ++i;
        }
    }
}
