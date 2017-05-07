// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import org.json.JSONException;
import org.json.JSONArray;
import java.io.File;

public final class bz extends bq
{
    private bz(final File file) {
        super(file);
    }
    
    @Override
    public final Object a() {
        try {
            return new JSONArray((String)super.a());
        }
        catch (JSONException ex) {
            return null;
        }
    }
}
