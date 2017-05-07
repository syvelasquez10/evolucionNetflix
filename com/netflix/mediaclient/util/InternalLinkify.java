// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import java.util.Comparator;
import java.util.List;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.netflix.mediaclient.android.widget.InternalURLSpan;
import android.text.SpannableString;
import java.util.Iterator;
import android.text.util.Linkify$TransformFilter;
import android.util.Patterns;
import java.util.ArrayList;
import android.text.style.URLSpan;
import android.text.Spannable;
import android.text.method.MovementMethod;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import android.text.util.Linkify$MatchFilter;

public class InternalLinkify
{
    public static final Linkify$MatchFilter sUrlMatchFilter;
    
    static {
        sUrlMatchFilter = (Linkify$MatchFilter)new InternalLinkify$1();
    }
    
    private static final void addLinkMovementMethod(final TextView textView) {
        final MovementMethod movementMethod = textView.getMovementMethod();
        if ((movementMethod == null || !(movementMethod instanceof LinkMovementMethod)) && textView.getLinksClickable()) {
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }
    
    public static final boolean addWebLinks(final Spannable spannable) {
        final URLSpan[] array = (URLSpan[])spannable.getSpans(0, spannable.length(), (Class)URLSpan.class);
        for (int i = array.length - 1; i >= 0; --i) {
            spannable.removeSpan((Object)array[i]);
        }
        final ArrayList list = new ArrayList<LinkSpec>();
        gatherLinks(list, spannable, Patterns.WEB_URL, new String[] { "http://", "https://" }, InternalLinkify.sUrlMatchFilter, null);
        pruneOverlaps(list);
        if (list.size() == 0) {
            return false;
        }
        for (final LinkSpec linkSpec : list) {
            applyLink(linkSpec.url, linkSpec.start, linkSpec.end, spannable);
        }
        return true;
    }
    
    public static final boolean addWebLinks(final TextView textView) {
        final CharSequence text = textView.getText();
        if (text instanceof Spannable) {
            if (addWebLinks((Spannable)text)) {
                addLinkMovementMethod(textView);
                return true;
            }
            return false;
        }
        else {
            final SpannableString value = SpannableString.valueOf(text);
            if (addWebLinks((Spannable)value)) {
                addLinkMovementMethod(textView);
                textView.setText((CharSequence)value);
                return true;
            }
            return false;
        }
    }
    
    private static final void applyLink(final String s, final int n, final int n2, final Spannable spannable) {
        spannable.setSpan((Object)new InternalURLSpan(s), n, n2, 33);
    }
    
    private static final void gatherLinks(final ArrayList<LinkSpec> list, final Spannable spannable, final Pattern pattern, final String[] array, final Linkify$MatchFilter linkify$MatchFilter, final Linkify$TransformFilter linkify$TransformFilter) {
        final Matcher matcher = pattern.matcher((CharSequence)spannable);
        while (matcher.find()) {
            final int start = matcher.start();
            final int end = matcher.end();
            if (linkify$MatchFilter == null || linkify$MatchFilter.acceptMatch((CharSequence)spannable, start, end)) {
                final LinkSpec linkSpec = new LinkSpec();
                linkSpec.url = makeUrl(matcher.group(0), array, matcher, linkify$TransformFilter);
                linkSpec.start = start;
                linkSpec.end = end;
                list.add(linkSpec);
            }
        }
    }
    
    private static final String makeUrl(String string, final String[] array, final Matcher matcher, final Linkify$TransformFilter linkify$TransformFilter) {
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
                        string2 = array[0] + string;
                    }
                    return string2;
                }
            }
            int n = 0;
            string = transformUrl;
            continue;
        }
    }
    
    private static final void pruneOverlaps(final ArrayList<LinkSpec> list) {
        Collections.sort((List<Object>)list, (Comparator<? super Object>)new InternalLinkify$2());
        int size = list.size();
        int i = 0;
        while (i < size - 1) {
            final LinkSpec linkSpec = list.get(i);
            final LinkSpec linkSpec2 = list.get(i + 1);
            if (linkSpec.start <= linkSpec2.start && linkSpec.end > linkSpec2.start) {
                int n;
                if (linkSpec2.end <= linkSpec.end) {
                    n = i + 1;
                }
                else if (linkSpec.end - linkSpec.start > linkSpec2.end - linkSpec2.start) {
                    n = i + 1;
                }
                else if (linkSpec.end - linkSpec.start < linkSpec2.end - linkSpec2.start) {
                    n = i;
                }
                else {
                    n = -1;
                }
                if (n != -1) {
                    list.remove(n);
                    --size;
                    continue;
                }
            }
            ++i;
        }
    }
}
