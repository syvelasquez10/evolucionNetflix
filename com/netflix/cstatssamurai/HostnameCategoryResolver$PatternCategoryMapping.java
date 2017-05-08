// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.cstatssamurai;

import java.util.regex.Pattern;

class HostnameCategoryResolver$PatternCategoryMapping
{
    String category;
    Pattern pattern;
    final /* synthetic */ HostnameCategoryResolver this$0;
    
    public HostnameCategoryResolver$PatternCategoryMapping(final HostnameCategoryResolver this$0, final String s, final String category) {
        this.this$0 = this$0;
        this.pattern = Pattern.compile(s);
        this.category = category;
    }
}
