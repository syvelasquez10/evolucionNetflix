// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.cstatssamurai;

import org.json.JSONException;
import java.util.Collection;
import org.json.JSONArray;
import java.util.ArrayList;
import org.json.JSONObject;
import java.net.URISyntaxException;
import java.net.URI;
import java.util.Iterator;
import java.util.Map;
import java.util.List;
import java.util.Arrays;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import com.netflix.cstats.ExponentialHistogram;
import java.util.HashMap;

public class ClientStats
{
    public static final int PERCENTAGE_OF_DEVICES_TO_ALLOCATE = 100;
    private static final String categoryHostnamesPackedConfig = "[{\"category\":\"drop\",\"description\":\"ignore / don\\u0027t bucket, unless something above recursively resolves here\",\"remark\":\"also include arbitrary netflix + ccTLDs\",\"hostnamepatterns\":[\"netflix\\\\.net\",\"hailmary\\\\.netflix\\\\.com\",\"www\\\\.netflix\\\\.com\",\"nflxso\\\\.net\"]},{\"category\":\"awsapi\",\"description\":\"AWS API Calls\",\"hostnamepatterns\":[\"api-global\\\\.netflix\\\\.com\",\"api-staging\\\\.netflix\\\\.com\",\"apis\\\\.netflix\\\\.com\",\"search\\\\.netflix\\\\.ca\",\"oca-api\\\\.netflix\\\\.com\"]},{\"category\":\"awslog\",\"description\":\"AWS logging requests\",\"hostnamepatterns\":[\"ichnaea\\\\.netflix\\\\.com\",\"customerevents\\\\.netflix\\\\.com\",\"presentationtracking\\\\.netflix\\\\.com\",\"beacon\\\\.netflix\\\\.com\"]},{\"category\":\"awsboot\",\"description\":\"AWS Boot Requests for client startup\",\"hostnamepatterns\":[\"uiboot\\\\.netflix\\\\.com\",\"appboot\\\\.netflix\\\\.com\"]},{\"category\":\"awslicense\",\"description\":\"AWS License Request traffic\",\"hostnamepatterns\":[\".*\\\\.nrd\\\\.netflix\\\\.com\",\"nrdp\\\\.nccp\\\\.netflix\\\\.com\",\"android\\\\.nccp\\\\.netflix\\\\.com\",\"ios\\\\.nccp\\\\.netflix\\\\.com\",\"cbp\\\\.nccp\\\\.netflix\\\\.com\",\".*\\\\.nccp\\\\.netflix\\\\.com\"]},{\"category\":\"aws\",\"description\":\"General AWS traffic\",\"hostnamepatterns\":[\"account\\\\.netflix\\\\.com\",\"signup\\\\.netflix\\\\.com\",\"fast\\\\.netflix\\\\.com\"]},{\"category\":\"akamai\",\"description\":\"Akamai CDN Resources\",\"hostnamepatterns\":[\"secure\\\\.netflix\\\\.com\",\".*\\\\.netflix\\\\.ca\",\".*\\\\.nflximg\\\\.net\",\".*\\\\.nflximg\\\\.com\",\".*\\\\.nflxext\\\\.com\"]},{\"category\":\"ocso\",\"description\":\"Open Connect small objects\",\"hostnamepatterns\":[\".*\\\\.nflxso\\\\.net\"]},{\"category\":\"ocftl\",\"description\":\"Open Connect FTL\",\"hostnamepatterns\":[\"ftl\\\\.netflix\\\\.com\",\"api-ftl\\\\.netflix\\\\.com\"]},{\"category\":\"oc\",\"description\":\"General Open Connect\",\"remark\":\"might catch too much, but don\\u0027t have a definitive list of OC hostnames\",\"hostnamepatterns\":[\".*\\\\.nflxvideo\\\\.net\",\".*\"]},{\"ignore\":true,\"date\":2.0170214E7,\"rev\":1.0}]";
    private static ClientStats instance;
    private final int MAX_LRU_CACHE_SIZE;
    private final String[] esnRandomizationWhitelist;
    private HashMap<String, Boolean> esnRandomizationWhitelistList;
    private HashMap<String, HashMap<String, ExponentialHistogram<Integer>>> histogramList;
    private String[] histogramTypes;
    private HashMap<String, Boolean> histogramTypesList;
    private HostnameCategoryResolver hostnameCategoryResolver;
    private boolean isEnabled;
    private LruCache<Long, String> journal;
    private int lastSnapshotHashCode;
    
    private ClientStats() {
        this.esnRandomizationWhitelist = new String[] { "RANDOM-13184FEF-8ABF-4BBA-B995-E07DD7533917", "NFANDROIDD-PRV-P-LGE==NEXUS=5X-5911-C0A895E8B085608B2E11E44BB98763AB2395CE32EC6823C9CF3A08560119B593", "NFANDROID1-PRV-P-LGE==NEXUS=5X-5911-F1DFFFD908041FC15D630BBC3598EAF950856E85ADBCD155910CB9BD31EEAC51" };
        this.MAX_LRU_CACHE_SIZE = 10;
        this.isEnabled = true;
        this.initHistogramList("[{\"category\":\"drop\",\"description\":\"ignore / don\\u0027t bucket, unless something above recursively resolves here\",\"remark\":\"also include arbitrary netflix + ccTLDs\",\"hostnamepatterns\":[\"netflix\\\\.net\",\"hailmary\\\\.netflix\\\\.com\",\"www\\\\.netflix\\\\.com\",\"nflxso\\\\.net\"]},{\"category\":\"awsapi\",\"description\":\"AWS API Calls\",\"hostnamepatterns\":[\"api-global\\\\.netflix\\\\.com\",\"api-staging\\\\.netflix\\\\.com\",\"apis\\\\.netflix\\\\.com\",\"search\\\\.netflix\\\\.ca\",\"oca-api\\\\.netflix\\\\.com\"]},{\"category\":\"awslog\",\"description\":\"AWS logging requests\",\"hostnamepatterns\":[\"ichnaea\\\\.netflix\\\\.com\",\"customerevents\\\\.netflix\\\\.com\",\"presentationtracking\\\\.netflix\\\\.com\",\"beacon\\\\.netflix\\\\.com\"]},{\"category\":\"awsboot\",\"description\":\"AWS Boot Requests for client startup\",\"hostnamepatterns\":[\"uiboot\\\\.netflix\\\\.com\",\"appboot\\\\.netflix\\\\.com\"]},{\"category\":\"awslicense\",\"description\":\"AWS License Request traffic\",\"hostnamepatterns\":[\".*\\\\.nrd\\\\.netflix\\\\.com\",\"nrdp\\\\.nccp\\\\.netflix\\\\.com\",\"android\\\\.nccp\\\\.netflix\\\\.com\",\"ios\\\\.nccp\\\\.netflix\\\\.com\",\"cbp\\\\.nccp\\\\.netflix\\\\.com\",\".*\\\\.nccp\\\\.netflix\\\\.com\"]},{\"category\":\"aws\",\"description\":\"General AWS traffic\",\"hostnamepatterns\":[\"account\\\\.netflix\\\\.com\",\"signup\\\\.netflix\\\\.com\",\"fast\\\\.netflix\\\\.com\"]},{\"category\":\"akamai\",\"description\":\"Akamai CDN Resources\",\"hostnamepatterns\":[\"secure\\\\.netflix\\\\.com\",\".*\\\\.netflix\\\\.ca\",\".*\\\\.nflximg\\\\.net\",\".*\\\\.nflximg\\\\.com\",\".*\\\\.nflxext\\\\.com\"]},{\"category\":\"ocso\",\"description\":\"Open Connect small objects\",\"hostnamepatterns\":[\".*\\\\.nflxso\\\\.net\"]},{\"category\":\"ocftl\",\"description\":\"Open Connect FTL\",\"hostnamepatterns\":[\"ftl\\\\.netflix\\\\.com\",\"api-ftl\\\\.netflix\\\\.com\"]},{\"category\":\"oc\",\"description\":\"General Open Connect\",\"remark\":\"might catch too much, but don\\u0027t have a definitive list of OC hostnames\",\"hostnamepatterns\":[\".*\\\\.nflxvideo\\\\.net\",\".*\"]},{\"ignore\":true,\"date\":2.0170214E7,\"rev\":1.0}]");
        this.esnRandomizationWhitelistList = new HashMap<String, Boolean>(this.esnRandomizationWhitelist.length);
        final String[] esnRandomizationWhitelist = this.esnRandomizationWhitelist;
        for (int length = esnRandomizationWhitelist.length, i = 0; i < length; ++i) {
            this.esnRandomizationWhitelistList.put(esnRandomizationWhitelist[i], Boolean.TRUE);
        }
        this.lastSnapshotHashCode = 0;
        this.journal = new LruCache<Long, String>(10);
    }
    
    public static int djb2Hash(final byte[] array) {
        byte[] bytes = array;
        if (array == null) {
            bytes = "".getBytes();
        }
        int n = 5381;
        for (int i = 0; i < bytes.length; ++i) {
            n = (n + (n << 5) ^ bytes[i]);
        }
        return n;
    }
    
    public static String getFileAsString(final String s) {
        final StringBuilder sb = new StringBuilder("");
        final BufferedReader bufferedReader = new BufferedReader(new FileReader(s));
        while (true) {
            final String line = bufferedReader.readLine();
            if (line == null) {
                break;
            }
            sb.append(line);
        }
        return sb.toString();
    }
    
    public static ClientStats getInstance() {
        if (ClientStats.instance == null) {
            ClientStats.instance = new ClientStats();
        }
        return ClientStats.instance;
    }
    
    private void initHistogramList(final String s) {
        final int n = 0;
        this.hostnameCategoryResolver = new HostnameCategoryResolver(s);
        final List<String> list = Arrays.asList(this.hostnameCategoryResolver.getCategories());
        if (list.size() < 1) {
            list.add("drop");
        }
        this.histogramTypesList = new HashMap<String, Boolean>(list.size());
        final String[] categories = this.hostnameCategoryResolver.getCategories();
        for (int length = categories.length, i = 0; i < length; ++i) {
            this.histogramTypesList.put(categories[i], Boolean.TRUE);
        }
        this.histogramList = new HashMap<String, HashMap<String, ExponentialHistogram<Integer>>>(list.size());
        final String[] categories2 = this.hostnameCategoryResolver.getCategories();
        for (int length2 = categories2.length, j = n; j < length2; ++j) {
            final String s2 = categories2[j];
            final HashMap<String, ExponentialHistogram<Integer>> hashMap = new HashMap<String, ExponentialHistogram<Integer>>();
            final ExponentialHistogram<Integer> exponentialHistogram = new ExponentialHistogram<Integer>(Integer.class);
            exponentialHistogram.initializeBucketRanges(1, 30000, 48);
            hashMap.put("duration", exponentialHistogram);
            final ExponentialHistogram<Integer> exponentialHistogram2 = new ExponentialHistogram<Integer>(Integer.class);
            exponentialHistogram2.initializeBucketRanges(1, 131072, 32);
            hashMap.put("size", exponentialHistogram2);
            this.histogramList.put(s2, hashMap);
        }
    }
    
    public void addCount(final String s, final String s2, final Integer n, final int n2) {
        if (this.isEnabled && this.histogramTypesList.containsKey(s)) {
            this.histogramList.get(s).get(s2).addCount(n, n2);
        }
    }
    
    public String getJournal() {
        final StringBuilder sb = new StringBuilder();
        sb.append('{');
        final Iterator<Map.Entry<Object, Object>> iterator = this.journal.entrySet().iterator();
        int n = 1;
        while (iterator.hasNext()) {
            final Map.Entry<Object, Object> entry = iterator.next();
            if (n != 0) {
                n = 0;
            }
            else {
                sb.append(", ");
            }
            sb.append("\"" + entry.getKey() + "\":" + entry.getValue());
        }
        sb.append('}');
        return sb.toString();
    }
    
    public String getNetworkHistogramType(String resolveCategory) {
        final String s = null;
        try {
            final URI uri = new URI(resolveCategory);
            resolveCategory = s;
            if (uri.getHost() != null) {
                resolveCategory = this.hostnameCategoryResolver.resolveCategory(uri.getHost());
            }
            return resolveCategory;
        }
        catch (NullPointerException ex) {
            return null;
        }
        catch (URISyntaxException ex2) {
            return null;
        }
    }
    
    public boolean isDeviceInRandomization(final String s) {
        if (!this.esnRandomizationWhitelistList.containsKey(s)) {}
        return true;
    }
    
    public void reset() {
        if (ClientStats.instance != null) {
            this.initHistogramList("[{\"category\":\"drop\",\"description\":\"ignore / don\\u0027t bucket, unless something above recursively resolves here\",\"remark\":\"also include arbitrary netflix + ccTLDs\",\"hostnamepatterns\":[\"netflix\\\\.net\",\"hailmary\\\\.netflix\\\\.com\",\"www\\\\.netflix\\\\.com\",\"nflxso\\\\.net\"]},{\"category\":\"awsapi\",\"description\":\"AWS API Calls\",\"hostnamepatterns\":[\"api-global\\\\.netflix\\\\.com\",\"api-staging\\\\.netflix\\\\.com\",\"apis\\\\.netflix\\\\.com\",\"search\\\\.netflix\\\\.ca\",\"oca-api\\\\.netflix\\\\.com\"]},{\"category\":\"awslog\",\"description\":\"AWS logging requests\",\"hostnamepatterns\":[\"ichnaea\\\\.netflix\\\\.com\",\"customerevents\\\\.netflix\\\\.com\",\"presentationtracking\\\\.netflix\\\\.com\",\"beacon\\\\.netflix\\\\.com\"]},{\"category\":\"awsboot\",\"description\":\"AWS Boot Requests for client startup\",\"hostnamepatterns\":[\"uiboot\\\\.netflix\\\\.com\",\"appboot\\\\.netflix\\\\.com\"]},{\"category\":\"awslicense\",\"description\":\"AWS License Request traffic\",\"hostnamepatterns\":[\".*\\\\.nrd\\\\.netflix\\\\.com\",\"nrdp\\\\.nccp\\\\.netflix\\\\.com\",\"android\\\\.nccp\\\\.netflix\\\\.com\",\"ios\\\\.nccp\\\\.netflix\\\\.com\",\"cbp\\\\.nccp\\\\.netflix\\\\.com\",\".*\\\\.nccp\\\\.netflix\\\\.com\"]},{\"category\":\"aws\",\"description\":\"General AWS traffic\",\"hostnamepatterns\":[\"account\\\\.netflix\\\\.com\",\"signup\\\\.netflix\\\\.com\",\"fast\\\\.netflix\\\\.com\"]},{\"category\":\"akamai\",\"description\":\"Akamai CDN Resources\",\"hostnamepatterns\":[\"secure\\\\.netflix\\\\.com\",\".*\\\\.netflix\\\\.ca\",\".*\\\\.nflximg\\\\.net\",\".*\\\\.nflximg\\\\.com\",\".*\\\\.nflxext\\\\.com\"]},{\"category\":\"ocso\",\"description\":\"Open Connect small objects\",\"hostnamepatterns\":[\".*\\\\.nflxso\\\\.net\"]},{\"category\":\"ocftl\",\"description\":\"Open Connect FTL\",\"hostnamepatterns\":[\"ftl\\\\.netflix\\\\.com\",\"api-ftl\\\\.netflix\\\\.com\"]},{\"category\":\"oc\",\"description\":\"General Open Connect\",\"remark\":\"might catch too much, but don\\u0027t have a definitive list of OC hostnames\",\"hostnamepatterns\":[\".*\\\\.nflxvideo\\\\.net\",\".*\"]},{\"ignore\":true,\"date\":2.0170214E7,\"rev\":1.0}]");
            this.lastSnapshotHashCode = 0;
            this.journal = new LruCache<Long, String>(10);
        }
    }
    
    public void setEnabled(final boolean isEnabled) {
        this.isEnabled = isEnabled;
    }
    
    public void takeSnapshot() {
        final String string = this.toString();
        if (this.lastSnapshotHashCode != string.hashCode()) {
            this.journal.put(System.nanoTime(), string);
            this.lastSnapshotHashCode = string.hashCode();
        }
    }
    
    @Override
    public String toString() {
        final String string = "" + "CurrentHistTypes: " + this.histogramList.size();
        try {
            final JSONObject jsonObject = new JSONObject();
            final String[] categories = this.hostnameCategoryResolver.getCategories();
            for (int length = categories.length, i = 0; i < length; ++i) {
                final String s = categories[i];
                final ArrayList<JSONObject> list = new ArrayList<JSONObject>();
                for (final Map.Entry<String, ExponentialHistogram<Integer>> entry : this.histogramList.get(s).entrySet()) {
                    final JSONObject jsonObject2 = new JSONObject();
                    jsonObject2.put("name", (Object)entry.getKey());
                    jsonObject2.put("layout", (Object)entry.getValue().getLayoutForJson());
                    jsonObject2.put("data", (Object)new JSONObject((Map)entry.getValue().getHistogramMapForJson()));
                    list.add(jsonObject2);
                }
                jsonObject.put(s, (Object)new JSONArray((Collection)list));
            }
            return jsonObject.toString();
        }
        catch (ClassCastException ex) {
            return string;
        }
        catch (JSONException ex2) {
            return string;
        }
    }
}
