// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import com.facebook.model.GraphObjectList;
import com.facebook.model.GraphObject;

interface TestSession$FqlResponse extends GraphObject
{
    GraphObjectList<TestSession$FqlResult> getData();
}
