// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.metadata.internal;

import org.json.JSONException;
import com.google.android.gms.common.data.DataHolder;
import android.os.Bundle;
import org.json.JSONArray;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import com.google.android.gms.drive.metadata.b;

public class k extends b<String>
{
    public k(final String s, final int n) {
        super(s, Collections.singleton(s), Collections.emptySet(), n);
    }
    
    public static final Collection<String> bk(final String s) {
        if (s == null) {
            return null;
        }
        final ArrayList<String> list = new ArrayList<String>();
        final JSONArray jsonArray = new JSONArray(s);
        for (int i = 0; i < jsonArray.length(); ++i) {
            list.add(jsonArray.getString(i));
        }
        return (Collection<String>)Collections.unmodifiableCollection((Collection<?>)list);
    }
    
    @Override
    protected void a(final Bundle bundle, final Collection<String> collection) {
        bundle.putStringArrayList(this.getName(), new ArrayList((Collection<? extends E>)collection));
    }
    
    @Override
    protected Collection<String> d(final DataHolder dataHolder, final int n, final int n2) {
        try {
            return bk(dataHolder.c(this.getName(), n, n2));
        }
        catch (JSONException ex) {
            throw new IllegalStateException("DataHolder supplied invalid JSON", (Throwable)ex);
        }
    }
    
    protected Collection<String> l(final Bundle bundle) {
        return (Collection<String>)bundle.getStringArrayList(this.getName());
    }
}
