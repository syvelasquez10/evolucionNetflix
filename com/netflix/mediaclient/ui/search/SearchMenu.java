// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.search;

import android.view.Menu;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public class SearchMenu
{
    public static void addSearchNavigation(final NetflixActivity netflixActivity, final Menu menu) {
        menu.add(0, 2131165243, 0, 2131493212).setIcon(2130837689).setIntent(SearchActivity.create(netflixActivity)).setShowAsAction(1);
    }
}
