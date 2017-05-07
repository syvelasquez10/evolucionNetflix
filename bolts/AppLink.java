// 
// Decompiled by Procyon v0.5.30
// 

package bolts;

import java.util.Collections;
import java.util.List;
import android.net.Uri;

public class AppLink
{
    private Uri sourceUrl;
    private List<AppLink$Target> targets;
    private Uri webUrl;
    
    public AppLink(final Uri sourceUrl, final List<AppLink$Target> list, final Uri webUrl) {
        this.sourceUrl = sourceUrl;
        List<AppLink$Target> emptyList = list;
        if (list == null) {
            emptyList = Collections.emptyList();
        }
        this.targets = emptyList;
        this.webUrl = webUrl;
    }
    
    public Uri getSourceUrl() {
        return this.sourceUrl;
    }
    
    public List<AppLink$Target> getTargets() {
        return Collections.unmodifiableList((List<? extends AppLink$Target>)this.targets);
    }
    
    public Uri getWebUrl() {
        return this.webUrl;
    }
}
