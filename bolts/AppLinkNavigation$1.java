// 
// Decompiled by Procyon v0.5.30
// 

package bolts;

import android.content.Context;

final class AppLinkNavigation$1 implements Continuation<AppLink, AppLinkNavigation$NavigationResult>
{
    final /* synthetic */ Context val$context;
    
    AppLinkNavigation$1(final Context val$context) {
        this.val$context = val$context;
    }
    
    @Override
    public AppLinkNavigation$NavigationResult then(final Task<AppLink> task) {
        return AppLinkNavigation.navigate(this.val$context, task.getResult());
    }
}
