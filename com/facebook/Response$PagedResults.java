// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import com.facebook.model.GraphObjectList;
import com.facebook.model.GraphObject;

interface Response$PagedResults extends GraphObject
{
    GraphObjectList<GraphObject> getData();
    
    Response$PagingInfo getPaging();
}
