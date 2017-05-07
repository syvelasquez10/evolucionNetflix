// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import com.facebook.model.GraphObjectList;
import com.facebook.model.GraphObject;

interface TestSession$FqlResult extends GraphObject
{
    GraphObjectList<GraphObject> getFqlResultSet();
}
