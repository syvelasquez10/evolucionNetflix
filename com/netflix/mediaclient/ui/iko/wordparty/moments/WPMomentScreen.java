// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.wordparty.moments;

import android.content.res.Resources;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.ui.iko.wordparty.model.WPInteractiveMomentsModel$WPImage;
import com.netflix.mediaclient.util.ThreadUtils;
import android.animation.ValueAnimator$AnimatorUpdateListener;
import android.widget.ImageView$ScaleType;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import com.netflix.mediaclient.ui.iko.BaseInteractiveMomentsManager$PlaybackCompleteListener;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import com.netflix.mediaclient.util.ViewUtils;
import java.util.Collection;
import android.animation.AnimatorSet;
import android.animation.Animator;
import android.graphics.Paint;
import android.graphics.Canvas;
import android.graphics.Bitmap$Config;
import android.graphics.drawable.BitmapDrawable;
import java.util.Iterator;
import android.animation.Animator$AnimatorListener;
import android.animation.TimeInterpolator;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.iko.wordparty.WPConstants;
import com.netflix.mediaclient.util.DeviceUtils;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import android.view.View;
import com.netflix.mediaclient.ui.iko.wordparty.model.WPInteractiveMomentsModel$WPItem;
import com.netflix.mediaclient.ui.iko.wordparty.model.WPInteractiveMomentsModel$WPAudio;
import android.os.Handler;
import java.util.ArrayList;
import com.netflix.mediaclient.ui.iko.wordparty.model.WPInteractiveMomentsModel$WPMoment;
import java.util.List;
import android.view.View$OnClickListener;
import android.widget.ImageView;
import android.graphics.Bitmap;

public class WPMomentScreen implements CardListener, WPCardVOPlayer
{
    private static final String TAG = "WPMomentScreen";
    private Bitmap bgImageBitmap;
    private ImageView bgView;
    private WPCardLayout card1;
    private WPCardLayout card2;
    private WPCardLayout card3;
    private WPCardLayout card4;
    private View$OnClickListener cardClickListener;
    private List<Bitmap> cardClosedBitmapList;
    private List<Bitmap> cardOpenBitmapList;
    private List<Bitmap> cardVideoMaskBitmapList;
    private List<WPCardLayout> cardViewsList;
    private boolean cardsEnabled;
    private List<WPCardLayout> cardsList;
    private int colorBlue;
    private int colorGreen;
    private int colorRed;
    private int colorWhite;
    private int colorYellow;
    private WPCardLayout currentCard;
    private WPInteractiveMomentsModel$WPMoment currentMoment;
    private WPMomentScreen$WordPartyMomentState currentState;
    private ArrayList<String> currentlyPlayingAudioList;
    private final float deviceHeight;
    private float deviceWidth;
    private Bitmap fgImageBitmap;
    private ImageView fgView;
    private Handler handler;
    private List<WPInteractiveMomentsModel$WPAudio> instructionVOList;
    private List<WPInteractiveMomentsModel$WPAudio> introVOList;
    private boolean isPendingStart;
    private List<WPInteractiveMomentsModel$WPItem> itemList;
    private final WPInteractiveMomentsManager manager;
    private boolean momentClosed;
    private boolean openPanel;
    private View panel1;
    private View panel2;
    private View panel3;
    private View panel4;
    private LinearLayout panelContainer;
    private List<View> panelList;
    private List<WPInteractiveMomentsModel$WPAudio> passiveExitVOList;
    private List<WPInteractiveMomentsModel$WPAudio> positiveLineVOList;
    private final Interpolator quintOutInterpolator;
    private List<Bitmap> recapBitmapList;
    private int recapCounter;
    private List<WPInteractiveMomentsModel$WPItem> recapList;
    private List<WPInteractiveMomentsModel$WPAudio> recapVOList;
    private boolean resourcesLoaded;
    private boolean screenBackgrounded;
    private boolean screenPaused;
    private WPCardLayout standardCard1Reference;
    private WPCardLayout standardCard2Reference;
    private WPCardLayout standardCard3Reference;
    private WPCardLayout standardCard4Reference;
    private List<WPInteractiveMomentsModel$WPAudio> summaryVOList;
    private List<WPInteractiveMomentsModel$WPAudio> timeout2VOList;
    private int timeoutCounter;
    private Runnable timeoutRunnable;
    private List<WPInteractiveMomentsModel$WPAudio> timeoutVOList;
    private WPCardLayout wordWallyCard1Reference;
    private WPCardLayout wordWallyCard2Reference;
    private WPCardLayout wordWallyCard3Reference;
    private WPCardLayout wordWallyCard4Reference;
    private ViewGroup wpContainer;
    
    public WPMomentScreen(final WPInteractiveMomentsManager manager) {
        this.currentState = WPMomentScreen$WordPartyMomentState.INTRODUCTION;
        this.openPanel = true;
        this.currentlyPlayingAudioList = new ArrayList<String>();
        this.cardOpenBitmapList = new ArrayList<Bitmap>();
        this.recapBitmapList = new ArrayList<Bitmap>();
        this.cardClosedBitmapList = new ArrayList<Bitmap>();
        this.cardVideoMaskBitmapList = new ArrayList<Bitmap>();
        this.cardsList = new ArrayList<WPCardLayout>();
        this.cardViewsList = new ArrayList<WPCardLayout>();
        this.handler = new Handler();
        this.timeoutCounter = 0;
        this.timeoutRunnable = new WPMomentScreen$6(this);
        this.manager = manager;
        this.deviceWidth = DeviceUtils.getScreenWidthInPixels(manager.getContext());
        this.deviceHeight = DeviceUtils.getScreenHeightInPixels(manager.getContext());
        this.quintOutInterpolator = WPConstants.getQuintOutInterpolator();
    }
    
    private void animateCardReset(final WPCardLayout wpCardLayout) {
        if (Log.isLoggable()) {
            Log.d("WPMomentScreen", "animateCardReset: animation start");
        }
        wpCardLayout.animate().alpha(0.0f).setInterpolator((TimeInterpolator)this.quintOutInterpolator).setDuration(500L).setListener((Animator$AnimatorListener)new WPMomentScreen$5(this, wpCardLayout)).start();
    }
    
    private void animateContainerReset(final WPCardLayout wpCardLayout) {
        if (Log.isLoggable()) {
            Log.d("WPMomentScreen", "animateContainerReset: animation start");
        }
        this.wpContainer.animate().x(0.0f).y(0.0f).scaleX(1.0f).scaleY(1.0f).rotation(0.0f).setInterpolator((TimeInterpolator)this.quintOutInterpolator).setDuration(500L).setListener((Animator$AnimatorListener)new WPMomentScreen$4(this, wpCardLayout)).start();
    }
    
    private void animationRecapStartValues() {
        if (this.cardViewsList == null) {
            if (Log.isLoggable()) {
                Log.d("WPMomentScreen", "animationRecapStartValues: cardViewsList is null");
            }
            return;
        }
        for (final WPCardLayout wpCardLayout : this.cardViewsList) {
            wpCardLayout.setRotation(0.0f);
            wpCardLayout.setRotationY(0.0f);
            wpCardLayout.setTranslationX(-this.deviceWidth);
        }
        this.showHideCards(true);
    }
    
    private void animationStartValues(final boolean b) {
        this.resetCards(b);
        this.card1.setRotation(-90.0f);
        this.card2.setRotation(90.0f);
        this.card3.setRotation(-90.0f);
        this.card4.setRotation(-90.0f);
        this.card1.setTranslationX(-this.deviceWidth);
        this.card2.setTranslationX(this.deviceWidth);
        this.card3.setTranslationX(-this.deviceWidth);
        this.card4.setTranslationX(this.deviceWidth);
    }
    
    private void arrangeCardsForRecap() {
        if (Log.isLoggable()) {
            Log.d("WPMomentScreen", "arrangeCardsForRecap: started");
        }
        this.cardsList.add(this.cardsList.size() - 1, this.cardsList.remove(0));
    }
    
    private BitmapDrawable bitmapWithBorder(final Bitmap bitmap) {
        if (bitmap == null) {
            if (Log.isLoggable()) {
                Log.d("WPMomentScreen", "bitmapWithBorder: received a null drawable");
            }
            return null;
        }
        if (Log.isLoggable()) {
            Log.d("WPMomentScreen", "bitmapWithBorder: Adding border to bitmap");
        }
        final Bitmap bitmap2 = Bitmap.createBitmap(bitmap.getWidth() + 2, bitmap.getHeight() + 2, Bitmap$Config.ARGB_8888);
        new Canvas(bitmap2).drawBitmap(bitmap, (float)1, (float)1, (Paint)null);
        return new BitmapDrawable(this.manager.getContext().getResources(), bitmap2);
    }
    
    private BitmapDrawable bitmapWithoutBorder(final Bitmap bitmap) {
        if (bitmap == null) {
            if (Log.isLoggable()) {
                Log.d("WPMomentScreen", "bitmapWithoutBorder: received a null bitmap");
            }
            return null;
        }
        if (Log.isLoggable()) {
            Log.d("WPMomentScreen", "bitmapWithoutBorder: Creating bitmapDrawable");
        }
        return new BitmapDrawable(this.manager.getContext().getResources(), bitmap);
    }
    
    private void cancelCurrentAudioPlaybacks() {
        if (this.currentlyPlayingAudioList.isEmpty()) {
            if (Log.isLoggable()) {
                Log.d("WPMomentScreen", "cancelCurrentAudioPlaybacks: list is empty.");
            }
        }
        else {
            final Iterator<String> iterator = this.currentlyPlayingAudioList.iterator();
            while (iterator.hasNext()) {
                this.manager.stopAudioPlayback(iterator.next());
            }
        }
    }
    
    private void cardClickAnimationComplete(final WPCardLayout wpCardLayout) {
        if (wpCardLayout == null) {
            if (Log.isLoggable()) {
                Log.d("WPMomentScreen", "cardClickAnimationComplete: card is null");
            }
        }
        else {
            if (Log.isLoggable()) {
                Log.d("WPMomentScreen", "cardClickAnimationComplete: card = " + wpCardLayout);
            }
            if (!this.isMomentClosed()) {
                if (this.isLearnMoment()) {
                    if (this.cardsList == null) {
                        if (Log.isLoggable()) {
                            Log.d("WPMomentScreen", "animateCardReset: cardsList is null.");
                        }
                        this.discardAnimationComplete();
                        return;
                    }
                    if (this.cardsList.size() <= 1) {
                        if (Log.isLoggable()) {
                            Log.d("WPMomentScreen", "animateCardReset: cardsList size=" + this.cardsList.size());
                        }
                        this.cardsList.remove(wpCardLayout);
                        this.discardAnimationComplete();
                        return;
                    }
                    this.animateCardReset(wpCardLayout);
                }
                else {
                    if (this.currentState == WPMomentScreen$WordPartyMomentState.RECAP_ITEMS) {
                        this.playNextRecapItem();
                        return;
                    }
                    if (this.isRevealMoment()) {
                        this.discardAnimation(wpCardLayout);
                        return;
                    }
                    this.animateContainerReset(wpCardLayout);
                }
            }
        }
    }
    
    private void discardAnimation(final WPCardLayout wpCardLayout) {
        if (Log.isLoggable()) {
            Log.d("WPMomentScreen", "discardAnimation: started");
        }
        this.cardsList.remove(wpCardLayout);
        final AnimatorSet cardAnimation = wpCardLayout.getCardAnimation(this.deviceWidth, 0.0f, 90.0f, 1.0f);
        final List<Animator> revealCardAnimations = this.getRevealCardAnimations(this.cardsList, false);
        revealCardAnimations.add((Animator)cardAnimation);
        final AnimatorSet set = new AnimatorSet();
        set.playTogether((Collection)revealCardAnimations);
        set.setDuration(1000L);
        set.setInterpolator((TimeInterpolator)this.quintOutInterpolator);
        set.addListener((Animator$AnimatorListener)new WPMomentScreen$15(this));
        set.start();
    }
    
    private void enableCards() {
        if (Log.isLoggable()) {
            Log.d("WPMomentScreen", "enableCards: cardsEnabled = true");
        }
        this.cardsEnabled = true;
    }
    
    private void flipCard(final WPCardLayout wpCardLayout) {
        if (wpCardLayout == null) {
            if (Log.isLoggable()) {
                Log.d("WPMomentScreen", "flipCard: card is null");
            }
        }
        else {
            if (Log.isLoggable()) {
                Log.d("WPMomentScreen", "flipCard: cardsEnabled = " + this.cardsEnabled);
            }
            if (this.cardsEnabled) {
                this.timeoutCounter = 0;
                this.startStopTimeoutTimer(false);
                wpCardLayout.flip();
            }
        }
    }
    
    private int getLearnMomentPanelColor(final int n) {
        final int colorWhite = this.colorWhite;
        switch (n) {
            default: {
                return colorWhite;
            }
            case 0: {
                return this.colorYellow;
            }
            case 1: {
                return this.colorGreen;
            }
            case 2: {
                return this.colorBlue;
            }
            case 3: {
                return this.colorRed;
            }
        }
    }
    
    private List<Animator> getRecapAnimations(final List<WPCardLayout> list) {
        if (list == null) {
            Log.d("WPMomentScreen", "getRecapAnimations: cardsList is null");
            return null;
        }
        if (Log.isLoggable()) {
            Log.d("WPMomentScreen", "getRecapAnimations: started");
        }
        final int size = list.size();
        final ArrayList<AnimatorSet> list2 = (ArrayList<AnimatorSet>)new ArrayList<Animator>();
        for (int i = 0; i < size; ++i) {
            final WPCardLayout wpCardLayout = list.get(i);
            if (wpCardLayout == null) {
                Log.d("WPMomentScreen", "CardView is null. returning without animation");
                return null;
            }
            final AnimatorSet calculateRecapAnimation = wpCardLayout.calculateRecapAnimation(i, size);
            calculateRecapAnimation.setInterpolator((TimeInterpolator)this.quintOutInterpolator);
            list2.add((Animator)calculateRecapAnimation);
        }
        return (List<Animator>)list2;
    }
    
    private List<Animator> getRecapEntryAnimations(final List<WPCardLayout> list) {
        if (list == null) {
            Log.d("WPMomentScreen", "getRecapEntryAnimations: cardsList is null");
            return null;
        }
        if (Log.isLoggable()) {
            Log.d("WPMomentScreen", "getRecapEntryAnimations: started");
        }
        final int size = list.size();
        final ArrayList<AnimatorSet> list2 = (ArrayList<AnimatorSet>)new ArrayList<Animator>();
        for (int i = 0; i < size; ++i) {
            final WPCardLayout wpCardLayout = list.get(i);
            if (wpCardLayout == null) {
                Log.d("TAG", "CardView is null. returning without animation");
                return null;
            }
            final AnimatorSet calculateRecapInitAnimation = wpCardLayout.calculateRecapInitAnimation(i, size);
            calculateRecapInitAnimation.setStartDelay((long)((size - i) * 200));
            calculateRecapInitAnimation.setInterpolator((TimeInterpolator)this.quintOutInterpolator);
            list2.add((Animator)calculateRecapInitAnimation);
        }
        return (List<Animator>)list2;
    }
    
    private List<Animator> getRecapExitAnimations(final List<WPCardLayout> list) {
        if (list == null) {
            Log.d("WPMomentScreen", "getRecapExitAnimations: cardsList is null");
            return null;
        }
        if (Log.isLoggable()) {
            Log.d("WPMomentScreen", "getRecapExitAnimations: started");
        }
        final int size = list.size();
        final ArrayList<AnimatorSet> list2 = (ArrayList<AnimatorSet>)new ArrayList<Animator>();
        for (int i = 0; i < size; ++i) {
            final WPCardLayout wpCardLayout = list.get(i);
            if (wpCardLayout == null) {
                Log.d("WPMomentScreen", "CardView is null. returning without animation");
                return null;
            }
            final AnimatorSet calculateRecapExitAnimation = wpCardLayout.calculateRecapExitAnimation(i, size);
            calculateRecapExitAnimation.setInterpolator((TimeInterpolator)this.quintOutInterpolator);
            list2.add((Animator)calculateRecapExitAnimation);
        }
        return (List<Animator>)list2;
    }
    
    private List<Animator> getRevealCardAnimations(final List<WPCardLayout> list, final boolean b) {
        if (list == null) {
            Log.d("WPMomentScreen", "getRevealCardAnimations: cardsList is null");
            return null;
        }
        if (Log.isLoggable()) {
            Log.d("WPMomentScreen", "getRevealCardAnimations: started");
        }
        final int size = list.size();
        final ArrayList<AnimatorSet> list2 = (ArrayList<AnimatorSet>)new ArrayList<Animator>();
        for (int i = 0; i < size; ++i) {
            final WPCardLayout wpCardLayout = list.get(i);
            if (wpCardLayout == null) {
                Log.d("WPMomentScreen", "CardView is null. returning without animation");
                return null;
            }
            final AnimatorSet calculateRevealAnimation = wpCardLayout.calculateRevealAnimation(i, size);
            if (b) {
                calculateRevealAnimation.setStartDelay((long)(i * 333));
            }
            calculateRevealAnimation.setInterpolator((TimeInterpolator)this.quintOutInterpolator);
            list2.add((Animator)calculateRevealAnimation);
        }
        return (List<Animator>)list2;
    }
    
    private int getStatusBarHeight() {
        return ViewUtils.getStatusBarHeight(this.manager.getContext());
    }
    
    private void handleCardClicked(final WPCardLayout currentCard) {
        if (currentCard == null) {
            if (Log.isLoggable()) {
                Log.d("WPMomentScreen", "handleCardClicked: card is null");
            }
        }
        else {
            if (Log.isLoggable()) {
                Log.d("WPMomentScreen", "handleCardClicked: card = " + currentCard);
            }
            this.manager.reportCommandEvent(UIViewLogging$UIViewCommandName.select);
            this.currentCard = currentCard;
            if (this.cardsEnabled) {
                this.manager.playItemSelectSound();
                this.cancelCurrentAudioPlaybacks();
                this.moveToState(WPMomentScreen$WordPartyMomentState.ITEM_SELECTION);
                if (this.isLearnMoment()) {
                    this.scaleUpCard(currentCard);
                    return;
                }
                if (this.isRevealMoment()) {
                    this.flipCard(currentCard);
                    return;
                }
                this.zoomInCard(currentCard);
            }
        }
    }
    
    private boolean isRevealMoment() {
        return "REVEAL".equalsIgnoreCase(this.currentMoment.getSceneType());
    }
    
    private void moveToState(WPMomentScreen$WordPartyMomentState currentState) {
        if (Log.isLoggable()) {
            Log.d("WPMomentScreen", "moveToState: state = " + currentState);
        }
        if (!this.isMomentClosed() && currentState != null) {
            this.currentState = currentState;
            switch (WPMomentScreen$16.$SwitchMap$com$netflix$mediaclient$ui$iko$wordparty$moments$WPMomentScreen$WordPartyMomentState[currentState.ordinal()]) {
                default: {}
                case 1: {
                    this.startEntryAnimation();
                    this.playVOList(this.introVOList, WPMomentScreen$WordPartyMomentState.INSTRUCTION);
                }
                case 2: {
                    this.enableCards();
                    if (this.isLearnMoment()) {
                        this.startPanelAnimation(true);
                    }
                    this.playVOList(this.instructionVOList, WPMomentScreen$WordPartyMomentState.ITEM_SELECTION);
                }
                case 3: {
                    this.startStopTimeoutTimer(true);
                }
                case 4: {
                    this.manager.playVictorySound();
                    if (this.isLearnMoment()) {
                        currentState = WPMomentScreen$WordPartyMomentState.SUMMARY;
                    }
                    else {
                        currentState = WPMomentScreen$WordPartyMomentState.RECAP;
                    }
                    this.playVOList(this.positiveLineVOList, currentState);
                    if (!this.isLearnMoment()) {
                        this.startRecapAnimation();
                        return;
                    }
                    break;
                }
                case 5: {
                    this.playVOList(this.recapVOList, WPMomentScreen$WordPartyMomentState.RECAP_ITEMS);
                }
                case 6: {
                    this.playRecapItems();
                }
                case 7: {
                    if (this.isLearnMoment()) {
                        this.manager.setActiveExit(true);
                    }
                    this.playVOList(this.summaryVOList, WPMomentScreen$WordPartyMomentState.OUTRO);
                }
                case 8: {
                    IClientLogging$CompletionReason clientLogging$CompletionReason;
                    if (this.timeoutCounter >= 2) {
                        clientLogging$CompletionReason = IClientLogging$CompletionReason.canceled;
                    }
                    else {
                        clientLogging$CompletionReason = IClientLogging$CompletionReason.success;
                    }
                    this.manager.reportMomentEnded(clientLogging$CompletionReason);
                    this.manager.hide();
                }
            }
        }
    }
    
    private void playAudioList(final List<WPInteractiveMomentsModel$WPAudio> list, final int n, final WPMomentScreen$WordPartyMomentState wpMomentScreen$WordPartyMomentState) {
        if (Log.isLoggable()) {
            Log.d("WPMomentScreen", "playVOList: currentState = " + this.currentState + " nextState = " + wpMomentScreen$WordPartyMomentState);
        }
        if (this.isMomentClosed()) {
            return;
        }
        if (list == null || list.isEmpty()) {
            Log.d("WPMomentScreen", "Unable to play audio for given empty or null VO list");
            this.moveToState(wpMomentScreen$WordPartyMomentState);
            return;
        }
        final int size = list.size();
        if (n >= size) {
            this.moveToState(wpMomentScreen$WordPartyMomentState);
            return;
        }
        this.playVO(list.get(n), new WPMomentScreen$12(this, wpMomentScreen$WordPartyMomentState, n, size, list));
    }
    
    private void playNextRecapItem() {
        final int n = this.recapCounter++;
        if (Log.isLoggable()) {
            Log.d("WPMomentScreen", "playNextRecapItem: Counter = " + n);
        }
        if (!this.isMomentClosed()) {
            if (this.cardsList == null || this.cardsList.isEmpty()) {
                if (Log.isLoggable()) {
                    Log.d("WPMomentScreen", "playNextRecapItem: cardsList is null or empty");
                }
            }
            else {
                if (n > 0) {
                    this.arrangeCardsForRecap();
                }
                if (n < this.cardsList.size()) {
                    this.startRecapAnimation(n);
                    return;
                }
                this.startRecapExitAnimation();
                this.moveToState(WPMomentScreen$WordPartyMomentState.SUMMARY);
            }
        }
    }
    
    private void playRecapItems() {
        this.recapCounter = 0;
        this.playNextRecapItem();
    }
    
    private void playVOList(final List<WPInteractiveMomentsModel$WPAudio> list, final WPMomentScreen$WordPartyMomentState wpMomentScreen$WordPartyMomentState) {
        this.playAudioList(list, 0, wpMomentScreen$WordPartyMomentState);
    }
    
    private void releaseBitmapList(final List<Bitmap> list) {
        if (Log.isLoggable()) {
            Log.d("WPMomentScreen", "releaseBitmapList: start");
        }
        if (list == null || list.isEmpty()) {
            if (Log.isLoggable()) {
                Log.d("WPMomentScreen", "releaseBitmapList: input list is either null or empty");
            }
        }
        else {
            final Iterator<Bitmap> iterator = list.iterator();
            while (iterator.hasNext()) {
                this.manager.releaseBitmaps(iterator.next());
            }
        }
    }
    
    private void releaseBitmaps() {
        if (Log.isLoggable()) {
            Log.d("WPMomentScreen", "releaseBitmaps for card open, closed and recap list bitmaps");
        }
        this.releaseBitmapList(this.cardOpenBitmapList);
        this.releaseBitmapList(this.cardClosedBitmapList);
        this.releaseBitmapList(this.cardVideoMaskBitmapList);
        this.releaseBitmapList(this.recapBitmapList);
    }
    
    private void resetCards(final boolean b) {
        if (Log.isLoggable()) {
            Log.d("WPMomentScreen", "resetCards: closeCard = " + b);
        }
        final Iterator<WPCardLayout> iterator = this.cardViewsList.iterator();
        while (iterator.hasNext()) {
            iterator.next().reset(b);
        }
    }
    
    private void scaleUpCard(final WPCardLayout wpCardLayout) {
        if (wpCardLayout == null) {
            if (Log.isLoggable()) {
                Log.d("WPMomentScreen", "scaleUpCard: card is null");
            }
        }
        else {
            if (Log.isLoggable()) {
                Log.d("WPMomentScreen", "scaleUpCard: cardsEnabled = " + this.cardsEnabled);
            }
            if (this.cardsEnabled) {
                this.timeoutCounter = 0;
                this.startStopTimeoutTimer(false);
                final ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder((Object)wpCardLayout, new PropertyValuesHolder[] { PropertyValuesHolder.ofFloat(View.SCALE_X, new float[] { wpCardLayout.getScaleX() * 1.1f }), PropertyValuesHolder.ofFloat(View.SCALE_Y, new float[] { wpCardLayout.getScaleY() * 1.1f }) });
                ofPropertyValuesHolder.addListener((Animator$AnimatorListener)new WPMomentScreen$2(this, wpCardLayout));
                ofPropertyValuesHolder.setDuration(500L);
                ofPropertyValuesHolder.setInterpolator((TimeInterpolator)this.quintOutInterpolator);
                ofPropertyValuesHolder.start();
                this.onCardClickStart(wpCardLayout);
            }
        }
    }
    
    private void showCurrentLearnMomentCard() {
        if (this.cardsList == null || this.cardsList.isEmpty()) {
            if (Log.isLoggable()) {
                Log.d("WPMomentScreen", "showCurrentLearnMomentCard: cardsList is null or empty.");
            }
        }
        else {
            this.currentCard = this.cardsList.get(0);
            if (this.currentCard != null) {
                if (Log.isLoggable()) {
                    Log.d("WPMomentScreen", "showCurrentLearnMomentCard: showing currentCard=" + this.currentCard);
                }
                this.currentCard.setTranslationX(0.0f);
                this.currentCard.setTranslationY(0.0f);
                this.currentCard.setRotation(0.0f);
                final int cardHeight = ((WPWordWallyCardLayout)this.currentCard).getCardHeight();
                final float n = (this.deviceHeight - this.getStatusBarHeight()) * 0.62f / cardHeight;
                final float translationY = cardHeight * n / 10.0f;
                this.currentCard.setScaleX(n);
                this.currentCard.setScaleY(n);
                this.currentCard.setTranslationY(translationY);
                AnimationUtils.startViewAppearanceAnimation((View)this.currentCard, true, 500);
                if (Log.isLoggable()) {
                    Log.d("WPMomentScreen", "showCurrentLearnMomentCard: cardsEnabled = true ");
                }
                this.cardsEnabled = true;
            }
        }
    }
    
    private void showHideCards(final boolean b) {
        if (Log.isLoggable()) {
            Log.d("WPMomentScreen", "showHideCards: show = " + b);
        }
        final Iterator<WPCardLayout> iterator = this.cardsList.iterator();
        while (iterator.hasNext()) {
            ViewUtils.setVisibleOrGone((View)iterator.next(), b);
        }
    }
    
    private void startEntryAnimation() {
        if (Log.isLoggable()) {
            Log.d("WPMomentScreen", "startEntryAnimation: Start check if resources are already loaded.");
        }
        if (this.isMomentClosed() || this.currentMoment == null) {
            return;
        }
        if (Log.isLoggable()) {
            Log.d("WPMomentScreen", "startEntryAnimation: start. cardsEnabled = false");
        }
        if (this.currentMoment.getBackgroundImage() == null) {
            ViewUtils.setVisibleOrGone((View)this.bgView, false);
        }
        else {
            this.bgView.setImageBitmap(this.bgImageBitmap);
            ViewUtils.setVisibleOrGone((View)this.bgView, true);
        }
        if (this.currentMoment.getForegroundImage() == null) {
            ViewUtils.setVisibleOrGone((View)this.fgView, false);
        }
        else {
            this.fgView.setImageBitmap(this.fgImageBitmap);
            ViewUtils.setVisibleOrGone((View)this.fgView, true);
        }
        this.cardsEnabled = false;
        if (this.isLearnMoment()) {
            final int statusBarHeight = this.getStatusBarHeight();
            final float deviceHeight = this.deviceHeight;
            int navigationBarHeight;
            if (this.manager.isNavigationBarBelowContent()) {
                navigationBarHeight = ViewUtils.getNavigationBarHeight(this.manager.getContext(), false);
            }
            else {
                navigationBarHeight = 0;
            }
            final float n = navigationBarHeight + deviceHeight - statusBarHeight;
            final int height = (int)Math.ceil(n * 0.56f);
            final int width = (int)Math.ceil(n * 0.56f * 1.778f);
            this.panelContainer.getLayoutParams().height = height;
            this.panelContainer.getLayoutParams().width = width;
            ViewUtils.setVisibleOrGone((View)this.panelContainer, true);
            this.fgView.setPadding(0, statusBarHeight, 0, 0);
            this.panelContainer.setTranslationY((float)(statusBarHeight / 2));
            this.fgView.setImageBitmap(this.fgImageBitmap);
            this.fgView.setScaleType(ImageView$ScaleType.CENTER_CROP);
            ViewUtils.setVisibleOrGone((View)this.fgView, true);
            return;
        }
        this.showHideCards(true);
        this.animationStartValues(this.isRevealMoment());
        final AnimatorSet set = new AnimatorSet();
        set.playTogether((Collection)this.getRevealCardAnimations(this.cardsList, true));
        set.setInterpolator((TimeInterpolator)this.quintOutInterpolator);
        set.setDuration(1000L);
        set.addListener((Animator$AnimatorListener)new WPMomentScreen$9(this));
        set.start();
    }
    
    private void startPanelAnimation(final boolean b) {
        if (this.panelList == null) {
            if (Log.isLoggable()) {
                Log.d("WPMomentScreen", "showPanelAnimation: PanelList is null.");
            }
            return;
        }
        final ArrayList<ObjectAnimator> list = new ArrayList<ObjectAnimator>();
        for (int size = this.panelList.size(), i = 0; i < size; ++i) {
            final int learnMomentPanelColor = this.getLearnMomentPanelColor(i);
            final View view = this.panelList.get(i);
            final ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder((Object)view, new PropertyValuesHolder[] { PropertyValuesHolder.ofFloat(View.ROTATION_Y, new float[] { view.getRotationY() + 180.0f }), PropertyValuesHolder.ofFloat(View.ALPHA, WPConstants.CARD_FLIP_ALPHA_VALUE_LIST) });
            ofPropertyValuesHolder.addUpdateListener((ValueAnimator$AnimatorUpdateListener)new WPMomentScreen$10(this, b, view, learnMomentPanelColor, i, size, ofPropertyValuesHolder));
            ofPropertyValuesHolder.setDuration(500L);
            ofPropertyValuesHolder.setStartDelay((i + 1) * 166L);
            list.add(ofPropertyValuesHolder);
        }
        final AnimatorSet set = new AnimatorSet();
        set.playTogether((Collection)list);
        set.setInterpolator((TimeInterpolator)this.quintOutInterpolator);
        set.addListener((Animator$AnimatorListener)new WPMomentScreen$11(this, b));
        set.start();
        this.manager.playPanelShuffleSound();
    }
    
    private void startRecapAnimation() {
        if (Log.isLoggable()) {
            Log.d("WPMomentScreen", "startRecapAnimation: started cardsEnabled = false");
        }
        this.cardsEnabled = false;
        this.showHideCards(true);
        this.prepareRecapScreen();
        this.showHideCards(false);
        this.animationRecapStartValues();
        this.startRecapEntryAnimation();
    }
    
    private void startRecapAnimation(final int n) {
        if (Log.isLoggable()) {
            Log.d("WPMomentScreen", "startRecapAnimation: counter = " + n);
        }
        if (this.isMomentClosed()) {
            return;
        }
        if (Log.isLoggable()) {
            Log.d("WPMomentScreen", "startRecapAnimation: animation started");
        }
        final AnimatorSet set = new AnimatorSet();
        set.playTogether((Collection)this.getRecapAnimations(this.cardsList));
        set.setInterpolator((TimeInterpolator)this.quintOutInterpolator);
        set.addListener((Animator$AnimatorListener)new WPMomentScreen$14(this));
        set.start();
    }
    
    private void startRecapEntryAnimation() {
        if (Log.isLoggable()) {
            Log.d("WPMomentScreen", "startRecapEntryAnimation: started");
        }
        if (this.isMomentClosed()) {
            return;
        }
        final AnimatorSet set = new AnimatorSet();
        set.playTogether((Collection)this.getRecapEntryAnimations(this.cardsList));
        set.setInterpolator((TimeInterpolator)this.quintOutInterpolator);
        set.setDuration(1500L);
        set.start();
    }
    
    private void startRecapExitAnimation() {
        if (Log.isLoggable()) {
            Log.d("WPMomentScreen", "startRecapExitAnimation: started");
        }
        if (this.isMomentClosed()) {
            return;
        }
        final AnimatorSet set = new AnimatorSet();
        set.playTogether((Collection)this.getRecapExitAnimations(this.cardsList));
        set.setInterpolator((TimeInterpolator)this.quintOutInterpolator);
        set.start();
    }
    
    private void startStopTimeoutTimer(final boolean b) {
        if (Log.isLoggable()) {
            Log.d("WPMomentScreen", "startStopTimeoutTimer: start = " + b);
        }
        this.handler.removeCallbacks(this.timeoutRunnable);
        if (b && this.currentState == WPMomentScreen$WordPartyMomentState.ITEM_SELECTION) {
            this.handler.postDelayed(this.timeoutRunnable, 15000L);
        }
    }
    
    private void startWiggleAnimation() {
        if (!this.isMomentClosed()) {
            if (this.cardsList == null) {
                Log.d("WPMomentScreen", "cardsList is null");
                return;
            }
            final int size = this.cardsList.size();
            final ArrayList<ObjectAnimator> list = new ArrayList<ObjectAnimator>();
            for (int i = 0; i < size; ++i) {
                final WPCardLayout wpCardLayout = this.cardsList.get(i);
                if (wpCardLayout == null) {
                    Log.d("TAG", "CardView is null. returning without animation");
                    return;
                }
                list.add(wpCardLayout.getWiggleAnimation(i));
            }
            final AnimatorSet set = new AnimatorSet();
            set.setDuration(500L);
            set.playTogether((Collection)list);
            set.addListener((Animator$AnimatorListener)new WPMomentScreen$7(this));
            set.setInterpolator((TimeInterpolator)this.quintOutInterpolator);
            set.start();
            this.cardsEnabled = false;
            this.manager.playWiggleSound();
            if (Log.isLoggable()) {
                Log.d("WPMomentScreen", "startWiggleAnimation: started cardsEnabled = false");
            }
        }
    }
    
    private void zoomInCard(final WPCardLayout wpCardLayout) {
        if (wpCardLayout == null && Log.isLoggable()) {
            Log.d("WPMomentScreen", "zoomInCard: card is null");
        }
        if (!this.isMomentClosed()) {
            if (Log.isLoggable()) {
                Log.d("WPMomentScreen", "zoomInCard: cardsEnabled = " + this.cardsEnabled);
            }
            if (this.cardsEnabled) {
                this.timeoutCounter = 0;
                this.startStopTimeoutTimer(false);
                this.wpContainer.setPivotX(wpCardLayout.getX() + wpCardLayout.getPivotX());
                this.wpContainer.setPivotY(wpCardLayout.getY() + wpCardLayout.getPivotY());
                final ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder((Object)this.wpContainer, new PropertyValuesHolder[] { PropertyValuesHolder.ofFloat(View.ROTATION, new float[] { this.wpContainer.getRotation(), -1.0f * wpCardLayout.getRotation() }), PropertyValuesHolder.ofFloat(View.SCALE_X, new float[] { this.wpContainer.getScaleX(), 2.0f }), PropertyValuesHolder.ofFloat(View.SCALE_Y, new float[] { this.wpContainer.getScaleY(), 2.0f }), PropertyValuesHolder.ofFloat(View.X, new float[] { this.wpContainer.getX(), this.wpContainer.getWidth() / 2 - (wpCardLayout.getX() + wpCardLayout.getPivotX()) }), PropertyValuesHolder.ofFloat(View.Y, new float[] { this.wpContainer.getY(), this.wpContainer.getHeight() / 2 - (wpCardLayout.getY() + wpCardLayout.getPivotY()) }) });
                ofPropertyValuesHolder.addListener((Animator$AnimatorListener)new WPMomentScreen$3(this, wpCardLayout));
                ofPropertyValuesHolder.setDuration(500L);
                ofPropertyValuesHolder.setInterpolator((TimeInterpolator)this.quintOutInterpolator);
                ofPropertyValuesHolder.start();
                this.onCardClickStart(wpCardLayout);
            }
        }
    }
    
    public void configureCards(final WPInteractiveMomentsModel$WPMoment wpInteractiveMomentsModel$WPMoment) {
        ThreadUtils.assertNotOnMain();
        if (wpInteractiveMomentsModel$WPMoment == null) {
            if (Log.isLoggable()) {
                Log.d("WPMomentScreen", "configureCards: moment is null");
            }
        }
        else {
            this.itemList = wpInteractiveMomentsModel$WPMoment.getItems();
            this.recapList = wpInteractiveMomentsModel$WPMoment.getRecapItems();
            if (this.itemList != null) {
                if (Log.isLoggable()) {
                    Log.d("WPMomentScreen", "configureCards: Clearing bitmaps list");
                }
                final int size = this.itemList.size();
                this.cardOpenBitmapList.clear();
                this.cardClosedBitmapList.clear();
                this.cardVideoMaskBitmapList.clear();
                this.recapBitmapList.clear();
                if (Log.isLoggable()) {
                    Log.d("WPMomentScreen", "configureCards: itemsList size = " + size);
                }
                for (final WPInteractiveMomentsModel$WPItem wpInteractiveMomentsModel$WPItem : this.itemList) {
                    if (wpInteractiveMomentsModel$WPItem == null) {
                        if (!Log.isLoggable()) {
                            continue;
                        }
                        Log.d("WPMomentScreen", "configureCards: card is null");
                    }
                    else {
                        Bitmap bitmap = this.manager.getBitmapFromCache(wpInteractiveMomentsModel$WPItem.getCardClosedImage(), false);
                        this.cardClosedBitmapList.add(bitmap);
                        final WPInteractiveMomentsModel$WPImage cardOpenImage = wpInteractiveMomentsModel$WPItem.getCardOpenImage();
                        if (cardOpenImage != null) {
                            bitmap = this.manager.getBitmapFromCache(cardOpenImage, false);
                        }
                        this.cardOpenBitmapList.add(bitmap);
                        final WPInteractiveMomentsModel$WPImage cardOpenVideoMask = wpInteractiveMomentsModel$WPItem.getCardOpenVideoMask();
                        Bitmap bitmapFromCache = null;
                        if (cardOpenVideoMask != null) {
                            bitmapFromCache = this.manager.getBitmapFromCache(cardOpenVideoMask, false);
                        }
                        this.cardVideoMaskBitmapList.add(bitmapFromCache);
                    }
                }
                if (this.recapList != null) {
                    for (final WPInteractiveMomentsModel$WPItem wpInteractiveMomentsModel$WPItem2 : this.recapList) {
                        if (wpInteractiveMomentsModel$WPItem2 != null) {
                            this.recapBitmapList.add(this.manager.getBitmapFromCache(wpInteractiveMomentsModel$WPItem2.getCardClosedImage(), false));
                        }
                    }
                }
                final WPInteractiveMomentsModel$WPImage backgroundImage = wpInteractiveMomentsModel$WPMoment.getBackgroundImage();
                if (backgroundImage != null) {
                    this.bgImageBitmap = this.manager.getBitmapFromCache(backgroundImage, true);
                }
                final WPInteractiveMomentsModel$WPImage foregroundImage = wpInteractiveMomentsModel$WPMoment.getForegroundImage();
                if (foregroundImage != null) {
                    this.fgImageBitmap = this.manager.getBitmapFromCache(foregroundImage, true);
                }
                if (Log.isLoggable()) {
                    Log.d("WPMomentScreen", "configureCards: resourcesLoaded = true");
                }
                this.resourcesLoaded = true;
                this.prepareAndStartIfPending();
                return;
            }
            if (Log.isLoggable()) {
                Log.d("WPMomentScreen", "configureCards: itemList is null");
            }
        }
    }
    
    public void discardAnimationComplete() {
        if (this.cardsList != null && this.cardsList.isEmpty()) {
            this.moveToState(WPMomentScreen$WordPartyMomentState.POSITIVE_LINE);
            this.startStopTimeoutTimer(false);
            return;
        }
        this.startStopTimeoutTimer(true);
    }
    
    public String getCurrentStateNameForLogging() {
        String s = null;
        if (this.currentState == WPMomentScreen$WordPartyMomentState.OUTRO) {
            if (this.timeoutCounter < 2) {
                return "ACTIVE_EXIT";
            }
            s = "PASSIVE_EXIT";
        }
        else if (this.currentState != null) {
            return this.currentState.name();
        }
        return s;
    }
    
    public void hideScreen() {
        if (Log.isLoggable()) {
            Log.d("WPMomentScreen", "hideScreen: stopping timeout timer and hiding cards");
        }
        this.timeoutCounter = 0;
        this.momentClosed = true;
        this.startStopTimeoutTimer(this.isPendingStart = false);
        for (final WPCardLayout wpCardLayout : this.cardViewsList) {
            ViewUtils.setVisibleOrGone((View)wpCardLayout, false);
            wpCardLayout.releaseResources();
        }
        ViewUtils.setVisibleOrGone((View)this.bgView, false);
        ViewUtils.setVisibleOrGone((View)this.fgView, false);
        ViewUtils.setVisibleOrGone((View)this.panelContainer, false);
        this.card1 = null;
        this.card2 = null;
        this.card3 = null;
        this.card4 = null;
    }
    
    public boolean isLearnMoment() {
        return this.currentMoment != null && "LEARN".equalsIgnoreCase(this.currentMoment.getSceneType());
    }
    
    public boolean isMomentClosed() {
        final boolean b = this.screenBackgrounded || this.momentClosed;
        if (Log.isLoggable()) {
            Log.d("WPMomentScreen", "isMomentClosed - " + b);
        }
        return b;
    }
    
    @Override
    public void onCardClickStart(final WPCardLayout wpCardLayout) {
        if (Log.isLoggable()) {
            Log.d("WPMomentScreen", "onCardClickStart: cardsEnabled = false");
        }
        this.cardsEnabled = false;
    }
    
    @Override
    public void onCardRevealComplete(final WPCardLayout wpCardLayout) {
        if (wpCardLayout == null) {
            if (Log.isLoggable()) {
                Log.d("WPMomentScreen", "onCardRevealComplete: card is null");
            }
            return;
        }
        if (Log.isLoggable()) {
            Log.d("WPMomentScreen", "onCardRevealComplete: calling cardClickAnimationComplete");
        }
        this.cardClickAnimationComplete(wpCardLayout);
    }
    
    public void onDestroy() {
        if (Log.isLoggable()) {
            Log.d("WPMomentScreen", "onDestroy: invoked on PlayerFragment");
        }
        if (this.cardViewsList != null && !this.cardViewsList.isEmpty()) {
            final Iterator<WPCardLayout> iterator = this.cardViewsList.iterator();
            while (iterator.hasNext()) {
                iterator.next().releaseResources();
            }
        }
        this.releaseBitmaps();
    }
    
    public void onPause() {
        if (Log.isLoggable()) {
            Log.d("WPMomentScreen", "onPause: invoked on PlayerFragment");
        }
        this.screenPaused = true;
    }
    
    public void onResume() {
        if (Log.isLoggable()) {
            Log.d("WPMomentScreen", "onResume: invoked on PlayerFragment");
        }
        this.screenPaused = false;
    }
    
    public void onStart() {
        if (Log.isLoggable()) {
            Log.d("WPMomentScreen", "onStart: invoked on PlayerFragment");
        }
        final boolean screenBackgrounded = this.screenBackgrounded;
        this.screenBackgrounded = false;
        if (Log.isLoggable()) {
            Log.d("WPMomentScreen", "onStop: screenBackgrounded = false");
        }
        if (!this.isMomentClosed() && screenBackgrounded && this.resourcesLoaded) {
            this.manager.playBgAudio();
            this.moveToState(this.currentState);
            if (this.currentState == WPMomentScreen$WordPartyMomentState.ITEM_SELECTION) {
                final boolean cardsEnabled = this.cardsEnabled;
                if (Log.isLoggable()) {
                    Log.d("WPMomentScreen", "onStart: in Item selection state. Current card animation complete = " + cardsEnabled);
                }
                if (cardsEnabled) {
                    this.cardClickAnimationComplete(this.currentCard);
                }
                else {
                    if (this.cardsList == null || this.cardsList.isEmpty()) {
                        this.moveToState(WPMomentScreen$WordPartyMomentState.POSITIVE_LINE);
                        return;
                    }
                    if (Log.isLoggable()) {
                        Log.d("WPMomentScreen", "onStart: calling currentCard.revealCard");
                    }
                    if (this.currentCard != null) {
                        this.currentCard.revealCard();
                    }
                }
            }
        }
    }
    
    public void onStop() {
        if (Log.isLoggable()) {
            Log.d("WPMomentScreen", "onStop: invoked on PlayerFragment");
        }
        this.startStopTimeoutTimer(false);
        this.screenBackgrounded = true;
        if (Log.isLoggable()) {
            Log.d("WPMomentScreen", "onStop: screenBackgrounded = true");
        }
        if (this.currentState == WPMomentScreen$WordPartyMomentState.ITEM_SELECTION) {
            final boolean cardsEnabled = this.cardsEnabled;
            if (Log.isLoggable()) {
                Log.d("WPMomentScreen", "onStop: in Item selection state. Current card animation complete = " + cardsEnabled);
            }
        }
    }
    
    @Override
    public void playVO(final WPInteractiveMomentsModel$WPAudio wpInteractiveMomentsModel$WPAudio, final BaseInteractiveMomentsManager$PlaybackCompleteListener baseInteractiveMomentsManager$PlaybackCompleteListener) {
        if (Log.isLoggable()) {
            Log.d("WPMomentScreen", "playVO: invoked");
        }
        if (this.isMomentClosed()) {
            return;
        }
        if (wpInteractiveMomentsModel$WPAudio == null || StringUtils.isEmpty(wpInteractiveMomentsModel$WPAudio.getUrl())) {
            if (Log.isLoggable()) {
                Log.d("WPMomentScreen", "playVO: audio is null or url is empty");
            }
            baseInteractiveMomentsManager$PlaybackCompleteListener.onComplete(null);
            return;
        }
        this.manager.playAudio(wpInteractiveMomentsModel$WPAudio.getUrl(), wpInteractiveMomentsModel$WPAudio.getVolume(), false, new WPMomentScreen$13(this, baseInteractiveMomentsManager$PlaybackCompleteListener));
        this.currentlyPlayingAudioList.add(wpInteractiveMomentsModel$WPAudio.getUrl());
    }
    
    public boolean prepareAndStart() {
        final WPInteractiveMomentsModel$WPMoment currentMoment = this.currentMoment;
        if (currentMoment == null) {
            if (Log.isLoggable()) {
                Log.d("WPMomentScreen", "prepareAndStart: moment is null");
            }
            return false;
        }
        if (Log.isLoggable()) {
            Log.d("WPMomentScreen", "prepareAndStart: moment = " + currentMoment.getSceneType());
        }
        this.momentClosed = false;
        final List<WPInteractiveMomentsModel$WPItem> items = currentMoment.getItems();
        if (Log.isLoggable()) {
            Log.d("WPMomentScreen", "prepareAndStart: are resources loaded = " + this.resourcesLoaded);
        }
        if (items == null || !this.resourcesLoaded) {
            if (Log.isLoggable()) {
                Log.d("WPMomentScreen", "prepareAndStart: resources not loaded");
            }
            this.isPendingStart = true;
            return false;
        }
        this.isPendingStart = false;
        this.cardsList.clear();
        this.currentlyPlayingAudioList.clear();
        for (int size = items.size(), i = 0; i < size; ++i) {
            final WPCardLayout wpCardLayout = this.cardViewsList.get(i);
            final WPInteractiveMomentsModel$WPItem wpInteractiveMomentsModel$WPItem = items.get(i);
            if (wpInteractiveMomentsModel$WPItem != null) {
                wpCardLayout.setAudio(wpInteractiveMomentsModel$WPItem.getItemAudio());
                wpCardLayout.setVideo(wpInteractiveMomentsModel$WPItem.getCardVideo());
            }
            this.cardsList.add(wpCardLayout);
            if (this.cardOpenBitmapList.size() > i && this.cardClosedBitmapList.size() > i) {
                final Bitmap bitmap = this.cardClosedBitmapList.get(i);
                final Bitmap bitmap2 = this.cardOpenBitmapList.get(i);
                final Bitmap bitmap3 = this.cardVideoMaskBitmapList.get(i);
                final BitmapDrawable bitmapWithBorder = this.bitmapWithBorder(bitmap2);
                final BitmapDrawable bitmapWithoutBorder = this.bitmapWithoutBorder(bitmap3);
                BitmapDrawable bitmapWithBorder2;
                if ("REVEAL".equalsIgnoreCase(currentMoment.getSceneType())) {
                    bitmapWithBorder2 = this.bitmapWithBorder(bitmap);
                }
                else {
                    bitmapWithBorder2 = bitmapWithBorder;
                }
                wpCardLayout.setDrawables(bitmapWithBorder2, bitmapWithBorder, bitmapWithoutBorder);
            }
        }
        this.showHideCards(false);
        if (this.isLearnMoment() && this.panelList != null) {
            for (int j = 0; j < this.panelList.size(); ++j) {
                this.panelList.get(j).setBackgroundColor(this.getLearnMomentPanelColor(j));
            }
        }
        this.introVOList = currentMoment.getIntroductionAudioList();
        this.instructionVOList = currentMoment.getInstructionAudioList();
        this.timeoutVOList = currentMoment.getTimeoutAudioList();
        this.timeout2VOList = currentMoment.getTimeout2AudioList();
        this.passiveExitVOList = currentMoment.getPassiveExitAudioList();
        this.positiveLineVOList = currentMoment.getPositiveLineAudioList();
        this.recapVOList = currentMoment.getRecapAudioList();
        this.summaryVOList = currentMoment.getSummaryAudioList();
        this.start();
        return true;
    }
    
    public void prepareAndStartIfPending() {
        if (Log.isLoggable()) {
            Log.d("WPMomentScreen", "prepareAndStartIfPending: is pending start? = " + this.isPendingStart);
        }
        if (this.isPendingStart) {
            this.handler.post((Runnable)new WPMomentScreen$1(this));
        }
    }
    
    public boolean prepareRecapScreen() {
        if (Log.isLoggable()) {
            Log.d("WPMomentScreen", "prepareRecapScreen: start");
        }
        if (this.recapList != null && !this.recapList.isEmpty()) {
            this.cardsList.clear();
            for (int i = 0; i < this.recapList.size(); ++i) {
                final WPCardLayout wpCardLayout = this.cardViewsList.get(i);
                final WPInteractiveMomentsModel$WPItem wpInteractiveMomentsModel$WPItem = this.recapList.get(i);
                if (wpInteractiveMomentsModel$WPItem != null) {
                    wpCardLayout.setAudio(wpInteractiveMomentsModel$WPItem.getRecapAudio());
                }
                this.cardsList.add(wpCardLayout);
                if (this.recapBitmapList.size() <= i || this.cardClosedBitmapList.size() <= i) {
                    return false;
                }
                final BitmapDrawable bitmapWithBorder = this.bitmapWithBorder(this.recapBitmapList.get(i));
                wpCardLayout.setDrawables(bitmapWithBorder, bitmapWithBorder, this.bitmapWithoutBorder(this.cardVideoMaskBitmapList.get(i)));
            }
            return true;
        }
        return false;
    }
    
    public void setInteractiveMomentAndFindViewsForMoment(final WPInteractiveMomentsModel$WPMoment currentMoment, final View view) {
        this.currentMoment = currentMoment;
        if (this.wpContainer == null) {
            this.wpContainer = (ViewGroup)view.findViewById(2131690311);
        }
        if (this.isLearnMoment()) {
            WPCardLayout wordWallyCard1Reference;
            if ((wordWallyCard1Reference = this.wordWallyCard1Reference) == null) {
                wordWallyCard1Reference = (WPWordWallyCardLayout)view.findViewById(2131690326);
                this.wordWallyCard1Reference = wordWallyCard1Reference;
            }
            this.card1 = wordWallyCard1Reference;
            WPCardLayout wordWallyCard2Reference;
            if ((wordWallyCard2Reference = this.wordWallyCard2Reference) == null) {
                wordWallyCard2Reference = (WPWordWallyCardLayout)view.findViewById(2131690327);
                this.wordWallyCard2Reference = wordWallyCard2Reference;
            }
            this.card2 = wordWallyCard2Reference;
            WPCardLayout wordWallyCard3Reference;
            if ((wordWallyCard3Reference = this.wordWallyCard3Reference) == null) {
                wordWallyCard3Reference = (WPWordWallyCardLayout)view.findViewById(2131690328);
                this.wordWallyCard3Reference = wordWallyCard3Reference;
            }
            this.card3 = wordWallyCard3Reference;
            WPCardLayout wordWallyCard4Reference;
            if ((wordWallyCard4Reference = this.wordWallyCard4Reference) == null) {
                wordWallyCard4Reference = (WPWordWallyCardLayout)view.findViewById(2131690329);
                this.wordWallyCard4Reference = wordWallyCard4Reference;
            }
            this.card4 = wordWallyCard4Reference;
        }
        else {
            WPCardLayout standardCard1Reference;
            if ((standardCard1Reference = this.standardCard1Reference) == null) {
                standardCard1Reference = (WPStandardCardLayout)view.findViewById(2131690322);
                this.standardCard1Reference = standardCard1Reference;
            }
            this.card1 = standardCard1Reference;
            WPCardLayout standardCard2Reference;
            if ((standardCard2Reference = this.standardCard2Reference) == null) {
                standardCard2Reference = (WPStandardCardLayout)view.findViewById(2131690323);
                this.standardCard2Reference = standardCard2Reference;
            }
            this.card2 = standardCard2Reference;
            WPCardLayout standardCard3Reference;
            if ((standardCard3Reference = this.standardCard3Reference) == null) {
                standardCard3Reference = (WPStandardCardLayout)view.findViewById(2131690324);
                this.standardCard3Reference = standardCard3Reference;
            }
            this.card3 = standardCard3Reference;
            WPCardLayout standardCard4Reference;
            if ((standardCard4Reference = this.standardCard4Reference) == null) {
                standardCard4Reference = (WPStandardCardLayout)view.findViewById(2131690325);
                this.standardCard4Reference = standardCard4Reference;
            }
            this.card4 = standardCard4Reference;
        }
        if (this.panelContainer == null) {
            this.panelContainer = (LinearLayout)view.findViewById(2131690317);
        }
        ViewUtils.setVisibleOrGone((View)this.panelContainer, false);
        if (this.panel1 == null) {
            this.panel1 = view.findViewById(2131690318);
        }
        if (this.panel2 == null) {
            this.panel2 = view.findViewById(2131690319);
        }
        if (this.panel3 == null) {
            this.panel3 = view.findViewById(2131690320);
        }
        if (this.panel4 == null) {
            this.panel4 = view.findViewById(2131690321);
        }
        if (this.bgView == null) {
            this.bgView = (ImageView)view.findViewById(2131690316);
        }
        if (this.fgView == null) {
            this.fgView = (ImageView)view.findViewById(2131690330);
        }
        this.cardClickListener = (View$OnClickListener)new WPMomentScreen$8(this);
        this.cardViewsList.clear();
        this.cardViewsList.add(this.card1);
        this.cardViewsList.add(this.card2);
        this.cardViewsList.add(this.card3);
        this.cardViewsList.add(this.card4);
        if (this.panelList == null) {
            (this.panelList = new ArrayList<View>()).add(this.panel1);
            this.panelList.add(this.panel2);
            this.panelList.add(this.panel3);
            this.panelList.add(this.panel4);
        }
        final Resources resources = this.manager.getContext().getResources();
        this.colorYellow = resources.getColor(2131624166);
        this.colorGreen = resources.getColor(2131624164);
        this.colorRed = resources.getColor(2131624165);
        this.colorBlue = resources.getColor(2131624163);
        this.colorWhite = resources.getColor(2131624161);
        for (final WPCardLayout wpCardLayout : this.cardViewsList) {
            wpCardLayout.setOnClickListener(this.cardClickListener);
            wpCardLayout.setCardListener(this);
            wpCardLayout.setVOPlayer(this);
        }
    }
    
    public void start() {
        if (Log.isLoggable()) {
            Log.d("WPMomentScreen", "starting moment intro animation");
        }
        if (this.screenBackgrounded) {
            return;
        }
        if (Log.isLoggable()) {
            Log.d("WPMomentScreen", "start: Playing bg audio in loop");
        }
        this.moveToState(WPMomentScreen$WordPartyMomentState.INTRODUCTION);
        this.manager.playBgAudio();
    }
}
