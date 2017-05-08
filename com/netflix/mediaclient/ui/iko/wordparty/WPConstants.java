// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.wordparty;

import android.support.v4.view.animation.PathInterpolatorCompat;
import android.view.animation.Interpolator;

public class WPConstants
{
    public static final long ANIMATION_DURATION_MS = 1000L;
    public static final float ASPECT_RATIO_4_3_QUALIFIER_VALUE = 1.5f;
    public static final int CARDS_MAX_COUNT = 4;
    public static final int CARD_ANIMATION_ROTATION_START_DEGREE = 90;
    public static final float[] CARD_FLIP_ALPHA_VALUE_LIST;
    public static final long CARD_FLIP_ANIMATION_DURATION_MS = 500L;
    public static final float CARD_HEIGHT_SPACING_MULTIPLIER = 3.0f;
    public static final long CARD_HIDE_ANIMATION_DURATION_MS = 500L;
    public static final float CARD_LEARN_MOMENT_SCALE_VALUE = 1.1f;
    public static final long CARD_RECAP_ANIMATION_DURATION_MS = 800L;
    public static final float CARD_ROTATION_DEGREE = 3.0f;
    public static final long CARD_SCALE_UP_ANIMATION_DURATION_MS = 500L;
    public static final int CARD_SHADOW_PERCENTAGE = 10;
    public static final float CARD_SPACING_MULTIPLIER = 1.2f;
    public static final float CARD_WIDTH_PERCENTAGE_FOR_4_3_RATIO_DEVICE = 0.4f;
    public static final float CARD_WIDTH_PERCENTAGE_OF_DEVICE_WIDTH = 0.36f;
    public static final long CARD_ZOOM_ANIMATION_DURATION_MS = 500L;
    public static final String CONTEXTUAL_MOMENT = "CONTEXTUAL";
    public static final int FLIP_ROTATION_DEGREE_VALUE = -180;
    public static final int HIDE_PLAYER_CONTROLS_DELAY_MS = 300;
    public static final int IGNORE_CLICK_TIMEOUT_MS = 300;
    public static final String LEARN_MOMENT = "LEARN";
    public static final String LOGGING_EXPECTED_VIDEO_OFFSET_KEY = "expectedVideoOffset";
    public static final String LOGGING_NOTIFICATION_ID_KEY = "notificationId";
    public static final String LOGGING_STATE_ACTIVE_EXIT = "ACTIVE_EXIT";
    public static final String LOGGING_STATE_PASSIVE_EXIT = "PASSIVE_EXIT";
    public static final int MINIMUM_PUG_ON_SCREEN_DISPLAY_TIME_MS = 2000;
    public static final int ONE_SECOND_DURATION_MS = 1000;
    public static final long PANEL_FLIP_ANIMATION_DELAY_MS = 166L;
    public static final long PANEL_FLIP_ANIMATION_DURATION_MS = 500L;
    public static final int PROGRESS_DIFFERENCE_ACCEPTABLE_RANGE_MS = 500;
    public static final String PROGRESS_PROPERTY = "progress";
    public static final int PUG_MINIMUM_DURATION_MS = 5000;
    public static final int PUG_PROGRESS_FOREGROUND_COLOR = -1275068417;
    public static final int PUG_PROGRESS_VALUE = 500;
    public static final int PUG_PULSATING_ANIMATION_DURATION_MS = 1332;
    public static final float PUG_REVEAL_SCALE_MULTIPLIER = 2.5f;
    public static final float PUG_SCALE_PULSATE_VALUE = 1.1f;
    public static final long RECAP_ANIMATION_DURATION_MS = 1500L;
    public static final float RECAP_BIG_SCALE_VALUE = 1.5f;
    public static final int RECAP_INTRO_DELAY_DURATION_MS = 200;
    public static final float RECAP_SMALL_SCALE_VALUE = 0.5f;
    public static final float RESET_VALUE = 0.0f;
    public static final int REVEAL_ANIMATION_DELAY_DURATION_MS = 333;
    public static final int REVEAL_ANIMATION_DURATION_MS = 666;
    public static final float REVEAL_BIG_SCALE_VALUE = 1.5f;
    public static final String REVEAL_MOMENT = "REVEAL";
    public static final float SCALE_RESET_VALUE = 1.0f;
    public static final float SFX_VOLUME_VALUE = 1.0f;
    private static final String TAG = "WPConstants";
    public static final int TIMEOUT_DURATION_MS = 15000;
    public static final int WIGGLE_ANIMATION_DELAY_MS = 100;
    public static final int WIGGLE_ANIMATION_DURATION_MS = 500;
    public static final float WORD_WALLY_CARD_HEIGHT_RATIO = 0.62f;
    public static final float WORD_WALLY_PANEL_HEIGHT_RATIO = 0.56f;
    
    static {
        CARD_FLIP_ALPHA_VALUE_LIST = new float[] { 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f };
    }
    
    public static Interpolator getQuintOutInterpolator() {
        return PathInterpolatorCompat.create(0.23f, 0.32f, 1.0f, 1.0f);
    }
}
