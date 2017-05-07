// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.metadata.internal;

import com.google.android.gms.common.data.DataHolder;
import android.os.Bundle;
import org.json.JSONException;
import java.util.Collections;
import org.json.JSONArray;
import java.util.ArrayList;
import java.util.Collection;
import com.google.android.gms.drive.metadata.b;

public class i extends b<String>
{
    public i(final String s, final int n) {
        super(s, n);
    }
    
    public static final Collection<String> ay(final String s) throws JSONException {
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
    protected Collection<String> c(final DataHolder dataHolder, final int n, final int n2) {
        try {
            return ay(dataHolder.getString(this.getName(), n, n2));
        }
        catch (JSONException ex) {
            throw new IllegalStateException("DataHolder supplied invalid JSON", (Throwable)ex);
        }
    }
    
    protected Collection<String> j(final Bundle bundle) {
        return (Collection<String>)bundle.getStringArrayList(this.getName());
    }
}
