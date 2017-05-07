// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.lolomo;

import android.graphics.Typeface;
import android.widget.TextView;
import com.netflix.mediaclient.ui.home.StandardSlidingMenu$GenreRowHolder;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import com.netflix.mediaclient.util.ViewUtils;
import android.support.v4.widget.DrawerLayout;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.home.StandardSlidingMenu;

public class KidsSlidingMenu extends StandardSlidingMenu
{
    private final int kidsItemTextColor;
    
    public KidsSlidingMenu(final NetflixActivity netflixActivity, final DrawerLayout drawerLayout) {
        super(netflixActivity, drawerLayout, false);
        final View viewById = drawerLayout.findViewById(2131624512);
        this.kidsItemTextColor = netflixActivity.getResources().getColor(2131558484);
        viewById.setBackgroundColor(netflixActivity.getResources().getColor(2131558517));
        this.profileName.setTextColor(this.kidsItemTextColor);
        this.profileName.setTextSize(netflixActivity.getResources().getDimension(2131296481));
        ViewUtils.setTextViewToBold(this.profileName);
        this.homeText.setTextColor(this.kidsItemTextColor);
        ViewUtils.setTextViewToBold(this.homeText);
        ((FrameLayout$LayoutParams)this.genresList.getLayoutParams()).bottomMargin = (int)netflixActivity.getResources().getDimension(2131296465);
        ViewUtils.removeShadow(this.homeText);
        this.homeText.setLayoutParams((ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-1, netflixActivity.getResources().getDimensionPixelSize(2131296482)));
        drawerLayout.findViewById(2131624515).setVisibility(8);
        drawerLayout.findViewById(2131624507).setBackgroundResource(2131558483);
    }
    
    @Override
    protected void updateAdapterViews(final StandardSlidingMenu$GenreRowHolder standardSlidingMenu$GenreRowHolder, final boolean b) {
        super.updateAdapterViews(standardSlidingMenu$GenreRowHolder, b);
        standardSlidingMenu$GenreRowHolder.tv.setTextColor(this.kidsItemTextColor);
        final TextView tv = standardSlidingMenu$GenreRowHolder.tv;
        final Typeface typeface = standardSlidingMenu$GenreRowHolder.tv.getTypeface();
        int n;
        if (b) {
            n = 1;
        }
        else {
            n = 0;
        }
        tv.setTypeface(typeface, n);
        standardSlidingMenu$GenreRowHolder.tv.setLayoutParams((ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-1, this.activity.getResources().getDimensionPixelSize(2131296482)));
        ViewUtils.removeShadow(standardSlidingMenu$GenreRowHolder.tv);
    }
    
    @Override
    protected void updateSwitchProfileButton() {
        super.updateSwitchProfileButton();
        this.switchProfilesIcon.setImageResource(2130837668);
    }
}
