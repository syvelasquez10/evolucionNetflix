// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.support.v4.util.SimpleArrayMap;
import android.app.Activity;

public class SupportActivity extends Activity
{
    private SimpleArrayMap<Class<? extends SupportActivity$ExtraData>, SupportActivity$ExtraData> mExtraDataMap;
    
    public SupportActivity() {
        this.mExtraDataMap = new SimpleArrayMap<Class<? extends SupportActivity$ExtraData>, SupportActivity$ExtraData>();
    }
    
    public <T extends SupportActivity$ExtraData> T getExtraData(final Class<T> clazz) {
        return (T)this.mExtraDataMap.get(clazz);
    }
    
    public void putExtraData(final SupportActivity$ExtraData supportActivity$ExtraData) {
        this.mExtraDataMap.put(supportActivity$ExtraData.getClass(), supportActivity$ExtraData);
    }
}
