// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

import java.util.LinkedHashSet;
import com.google.gson.JsonElement;
import com.google.gson.JsonArray;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.Arrays;
import com.netflix.mediaclient.NetflixApplication;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import java.util.Comparator;

final class PQL$1 implements Comparator<PQL>
{
    @Override
    public int compare(final PQL pql, final PQL pql2) {
        return comparePaths(pql, pql2);
    }
}
