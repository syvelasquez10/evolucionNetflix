// 
// Decompiled by Procyon v0.5.30
// 

package bolts;

import java.util.concurrent.Callable;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.net.HttpURLConnection;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.net.URLConnection;
import android.net.Uri;
import java.util.Map;
import org.json.JSONArray;
import android.content.Context;

public class WebViewAppLinkResolver implements AppLinkResolver
{
    private static final String KEY_AL_VALUE = "value";
    private static final String KEY_ANDROID = "android";
    private static final String KEY_APP_NAME = "app_name";
    private static final String KEY_CLASS = "class";
    private static final String KEY_PACKAGE = "package";
    private static final String KEY_SHOULD_FALLBACK = "should_fallback";
    private static final String KEY_URL = "url";
    private static final String KEY_WEB = "web";
    private static final String KEY_WEB_URL = "url";
    private static final String META_TAG_PREFIX = "al";
    private static final String PREFER_HEADER = "Prefer-Html-Meta-Tags";
    private static final String TAG_EXTRACTION_JAVASCRIPT = "javascript:boltsWebViewAppLinkResolverResult.setValue((function() {  var metaTags = document.getElementsByTagName('meta');  var results = [];  for (var i = 0; i < metaTags.length; i++) {    var property = metaTags[i].getAttribute('property');    if (property && property.substring(0, 'al:'.length) === 'al:') {      var tag = { \"property\": metaTags[i].getAttribute('property') };      if (metaTags[i].hasAttribute('content')) {        tag['content'] = metaTags[i].getAttribute('content');      }      results.push(tag);    }  }  return JSON.stringify(results);})())";
    private final Context context;
    
    public WebViewAppLinkResolver(final Context context) {
        this.context = context;
    }
    
    private static List<Map<String, Object>> getAlList(final Map<String, Object> map, final String s) {
        List<Map<String, Object>> emptyList;
        if ((emptyList = map.get(s)) == null) {
            emptyList = Collections.emptyList();
        }
        return emptyList;
    }
    
    private static AppLink makeAppLinkFromAlData(final Map<String, Object> map, final Uri uri) {
        final ArrayList<AppLink$Target> list = new ArrayList<AppLink$Target>();
        Object emptyList;
        if ((emptyList = map.get("android")) == null) {
            emptyList = Collections.emptyList();
        }
        for (final Map<String, Object> map2 : emptyList) {
            final List<Map<String, Object>> alList = getAlList(map2, "url");
            final List<Map<String, Object>> alList2 = getAlList(map2, "package");
            final List<Map<String, Object>> alList3 = getAlList(map2, "class");
            final List<Map<String, Object>> alList4 = getAlList(map2, "app_name");
            for (int max = Math.max(alList.size(), Math.max(alList2.size(), Math.max(alList3.size(), alList4.size()))), i = 0; i < max; ++i) {
                Object value;
                if (alList.size() > i) {
                    value = alList.get(i).get("value");
                }
                else {
                    value = null;
                }
                final Uri tryCreateUrl = tryCreateUrl((String)value);
                Object value2;
                if (alList2.size() > i) {
                    value2 = alList2.get(i).get("value");
                }
                else {
                    value2 = null;
                }
                final String s = (String)value2;
                Object value3;
                if (alList3.size() > i) {
                    value3 = alList3.get(i).get("value");
                }
                else {
                    value3 = null;
                }
                final String s2 = (String)value3;
                Object value4;
                if (alList4.size() > i) {
                    value4 = alList4.get(i).get("value");
                }
                else {
                    value4 = null;
                }
                list.add(new AppLink$Target(s, s2, tryCreateUrl, (String)value4));
            }
        }
        final List<Map> list2 = map.get("web");
        Uri tryCreateUrl2;
        if (list2 != null && list2.size() > 0) {
            final Map<K, List> map3 = list2.get(0);
            final List<Map> list3 = map3.get("url");
            final List<Map> list4 = map3.get("should_fallback");
            Uri uri2;
            if (list4 != null && list4.size() > 0 && Arrays.asList("no", "false", "0").contains(((Map<K, String>)list4.get(0)).get("value").toLowerCase())) {
                uri2 = null;
            }
            else {
                uri2 = uri;
            }
            tryCreateUrl2 = uri2;
            if (uri2 != null) {
                tryCreateUrl2 = uri2;
                if (list3 != null) {
                    tryCreateUrl2 = uri2;
                    if (list3.size() > 0) {
                        tryCreateUrl2 = tryCreateUrl(list3.get(0).get("value"));
                    }
                }
            }
        }
        else {
            tryCreateUrl2 = uri;
        }
        return new AppLink(uri, list, tryCreateUrl2);
    }
    
    private static Map<String, Object> parseAlData(final JSONArray jsonArray) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        for (int i = 0; i < jsonArray.length(); ++i) {
            final JSONObject jsonObject = jsonArray.getJSONObject(i);
            final String[] split = jsonObject.getString("property").split(":");
            if (split[0].equals("al")) {
                int j = 1;
                Object o = hashMap;
                while (j < split.length) {
                    List<?> list = ((Map<String, List<?>>)o).get(split[j]);
                    if (list == null) {
                        list = new ArrayList<Object>();
                        ((Map<String, List<Map<String, Object>>>)o).put(split[j], (List<Map<String, Object>>)list);
                    }
                    if (list.size() > 0) {
                        o = list.get(list.size() - 1);
                    }
                    else {
                        o = null;
                    }
                    if (o == null || j == split.length - 1) {
                        o = new HashMap<String, List<Map<String, Object>>>();
                        list.add(o);
                    }
                    ++j;
                }
                if (jsonObject.has("content")) {
                    if (jsonObject.isNull("content")) {
                        ((Map<String, String>)o).put("value", null);
                    }
                    else {
                        ((Map<String, String>)o).put("value", jsonObject.getString("content"));
                    }
                }
            }
        }
        return (Map<String, Object>)hashMap;
    }
    
    private static String readFromConnection(final URLConnection urlConnection) {
        int n = 0;
        while (true) {
            Label_0079: {
                if (!(urlConnection instanceof HttpURLConnection)) {
                    break Label_0079;
                }
                Object inputStream = urlConnection;
                ByteArrayOutputStream byteArrayOutputStream = null;
                Label_0088: {
                    while (true) {
                        try {
                            final InputStream inputStream2 = urlConnection.getInputStream();
                            inputStream = inputStream2;
                            try {
                                byteArrayOutputStream = new ByteArrayOutputStream();
                                final byte[] array = new byte[1024];
                                while (true) {
                                    final int read = ((InputStream)inputStream).read(array);
                                    if (read == -1) {
                                        break Label_0088;
                                    }
                                    byteArrayOutputStream.write(array, 0, read);
                                }
                            }
                            finally {
                                ((InputStream)inputStream).close();
                            }
                        }
                        catch (Exception ex) {
                            final InputStream inputStream2 = ((HttpURLConnection)inputStream).getErrorStream();
                            continue;
                        }
                        break;
                    }
                    break Label_0079;
                }
                final URLConnection urlConnection2;
                String contentEncoding;
                final String s = contentEncoding = urlConnection2.getContentEncoding();
                if (s == null) {
                    final String[] split = urlConnection2.getContentType().split(";");
                    final int length = split.length;
                    String substring;
                    while (true) {
                        substring = s;
                        if (n >= length) {
                            break;
                        }
                        final String trim = split[n].trim();
                        if (trim.startsWith("charset=")) {
                            substring = trim.substring("charset=".length());
                            break;
                        }
                        ++n;
                    }
                    if ((contentEncoding = substring) == null) {
                        contentEncoding = "UTF-8";
                    }
                }
                final String s2 = new String(byteArrayOutputStream.toByteArray(), contentEncoding);
                ((InputStream)inputStream).close();
                return s2;
            }
            Object inputStream = urlConnection.getInputStream();
            continue;
        }
    }
    
    private static Uri tryCreateUrl(final String s) {
        if (s == null) {
            return null;
        }
        return Uri.parse(s);
    }
    
    @Override
    public Task<AppLink> getAppLinkFromUrlInBackground(final Uri uri) {
        final Capture capture = new Capture();
        final Capture capture2 = new Capture();
        return Task.callInBackground((Callable<Object>)new WebViewAppLinkResolver$3(this, uri, capture, capture2)).onSuccessTask((Continuation<Object, Task<Object>>)new WebViewAppLinkResolver$2(this, capture2, uri, capture), Task.UI_THREAD_EXECUTOR).onSuccess((Continuation<Object, AppLink>)new WebViewAppLinkResolver$1(this, uri));
    }
}
