// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.model.search;

import com.netflix.model.branches.FalkorValidator;
import com.netflix.model.branches.FalkorObject;

public interface SearchSuggestion extends FalkorObject, FalkorValidator
{
    String getTitle();
}
