// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.player;

import android.text.TextUtils;

public final class PlayerColumnNames
{
    public final String aaH;
    public final String aaI;
    public final String aaJ;
    public final String aaK;
    public final String aaL;
    public final String aaM;
    public final String aaN;
    public final String aaO;
    public final String aaP;
    public final String aaQ;
    public final String aaR;
    public final String aaS;
    public final String aaT;
    public final String aaU;
    public final String aaV;
    public final String aaW;
    public final String aaX;
    public final String aaY;
    public final String aaZ;
    public final String aba;
    public final String abb;
    public final String abc;
    public final String abd;
    public final String abe;
    public final String abf;
    
    public PlayerColumnNames(final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            this.aaH = "external_player_id";
            this.aaI = "profile_name";
            this.aaJ = "profile_icon_image_uri";
            this.aaK = "profile_icon_image_url";
            this.aaL = "profile_hi_res_image_uri";
            this.aaM = "profile_hi_res_image_url";
            this.aaN = "last_updated";
            this.aaO = "is_in_circles";
            this.aaP = "played_with_timestamp";
            this.aaQ = "current_xp_total";
            this.aaR = "current_level";
            this.aaS = "current_level_min_xp";
            this.aaT = "current_level_max_xp";
            this.aaU = "next_level";
            this.aaV = "next_level_max_xp";
            this.aaW = "last_level_up_timestamp";
            this.aaX = "player_title";
            this.aaY = "has_all_public_acls";
            this.aaZ = "is_profile_visible";
            this.aba = "most_recent_external_game_id";
            this.abb = "most_recent_game_name";
            this.abc = "most_recent_activity_timestamp";
            this.abd = "most_recent_game_icon_uri";
            this.abe = "most_recent_game_hi_res_uri";
            this.abf = "most_recent_game_featured_uri";
            return;
        }
        this.aaH = s + "external_player_id";
        this.aaI = s + "profile_name";
        this.aaJ = s + "profile_icon_image_uri";
        this.aaK = s + "profile_icon_image_url";
        this.aaL = s + "profile_hi_res_image_uri";
        this.aaM = s + "profile_hi_res_image_url";
        this.aaN = s + "last_updated";
        this.aaO = s + "is_in_circles";
        this.aaP = s + "played_with_timestamp";
        this.aaQ = s + "current_xp_total";
        this.aaR = s + "current_level";
        this.aaS = s + "current_level_min_xp";
        this.aaT = s + "current_level_max_xp";
        this.aaU = s + "next_level";
        this.aaV = s + "next_level_max_xp";
        this.aaW = s + "last_level_up_timestamp";
        this.aaX = s + "player_title";
        this.aaY = s + "has_all_public_acls";
        this.aaZ = s + "is_profile_visible";
        this.aba = s + "most_recent_external_game_id";
        this.abb = s + "most_recent_game_name";
        this.abc = s + "most_recent_activity_timestamp";
        this.abd = s + "most_recent_game_icon_uri";
        this.abe = s + "most_recent_game_hi_res_uri";
        this.abf = s + "most_recent_game_featured_uri";
    }
}
