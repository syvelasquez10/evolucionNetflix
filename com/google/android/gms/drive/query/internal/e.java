// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query.internal;

import java.util.Set;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

class e
{
    static MetadataField<?> b(final MetadataBundle metadataBundle) {
        final Set<MetadataField<?>> ip = metadataBundle.ip();
        if (ip.size() != 1) {
            throw new IllegalArgumentException("bundle should have exactly 1 populated field");
        }
        return ip.iterator().next();
    }
}
