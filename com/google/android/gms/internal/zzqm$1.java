// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import org.json.JSONException;
import com.google.android.gms.tagmanager.zzbg;

final class zzqm$1 implements zzql
{
    @Override
    public Object zzt(final byte[] array) {
        if (array == null) {
            throw new zzqp$zzg("Cannot parse a null byte[]");
        }
        if (array.length == 0) {
            throw new zzqp$zzg("Cannot parse a 0 length byte[]");
        }
        try {
            final zzqp$zzc zzeN = zzqj.zzeN(new String(array));
            if (zzeN != null) {
                zzbg.v("The container was successfully parsed from the resource");
            }
            return zzeN;
        }
        catch (JSONException ex) {
            throw new zzqp$zzg("The resource data is corrupted. The container cannot be extracted from the binary data");
        }
        catch (zzqp$zzg zzqp$zzg) {
            throw new zzqp$zzg("The resource data is invalid. The container cannot be extracted from the binary data");
        }
    }
}
