// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.search;

import android.content.Context;
import android.view.Menu;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public class SearchMenu
{
    public static void addSearchNavigation(final NetflixActivity netflixActivity, final Menu menu) {
        menu.add(0, 2131099662, 0, 2131493143).setIcon(2130837596).setIntent(SearchActivity.create((Context)netflixActivity)).setShowAsAction(1);
    }
}
