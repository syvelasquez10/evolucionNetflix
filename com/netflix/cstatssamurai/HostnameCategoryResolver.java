// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.cstatssamurai;

import java.util.Iterator;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashSet;

public class HostnameCategoryResolver
{
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private HashSet<String> allCategories;
    private ArrayList<HostnameCategoryResolver$PatternCategoryMapping> patternCategoryMap;
    
    public HostnameCategoryResolver(final String s) {
        this.patternCategoryMap = new ArrayList<HostnameCategoryResolver$PatternCategoryMapping>();
        final CategoryHostnamesData[] array = new Gson().fromJson(s, CategoryHostnamesData[].class);
        int length;
        if (array.length > 0) {
            length = array.length;
        }
        else {
            length = 16;
        }
        this.allCategories = new HashSet<String>(length);
        for (int length2 = array.length, i = 0; i < length2; ++i) {
            final CategoryHostnamesData categoryHostnamesData = array[i];
            if (categoryHostnamesData != null && categoryHostnamesData.hostnamepatterns != null) {
                this.allCategories.add(categoryHostnamesData.category);
                final String[] hostnamepatterns = categoryHostnamesData.hostnamepatterns;
                for (int length3 = hostnamepatterns.length, j = 0; j < length3; ++j) {
                    this.patternCategoryMap.add(new HostnameCategoryResolver$PatternCategoryMapping(this, hostnamepatterns[j], categoryHostnamesData.category));
                }
            }
        }
    }
    
    public String[] getCategories() {
        return this.allCategories.toArray(new String[this.allCategories.size()]);
    }
    
    public String resolveCategory(final String s) {
        for (final HostnameCategoryResolver$PatternCategoryMapping hostnameCategoryResolver$PatternCategoryMapping : this.patternCategoryMap) {
            if (hostnameCategoryResolver$PatternCategoryMapping.pattern.matcher(s).matches()) {
                return hostnameCategoryResolver$PatternCategoryMapping.category;
            }
        }
        return "NOMATCH";
    }
}
