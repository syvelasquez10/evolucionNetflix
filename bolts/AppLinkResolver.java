// 
// Decompiled by Procyon v0.5.30
// 

package bolts;

import android.net.Uri;

public interface AppLinkResolver
{
    Task<AppLink> getAppLinkFromUrlInBackground(final Uri p0);
}
