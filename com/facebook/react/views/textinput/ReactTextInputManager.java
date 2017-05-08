// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.textinput;

import android.widget.TextView;
import com.facebook.react.views.text.TextInlineImageSpan;
import com.facebook.react.views.text.ReactTextUpdate;
import android.graphics.PorterDuff$Mode;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableMap;
import android.text.InputFilter$LengthFilter;
import java.util.LinkedList;
import com.facebook.react.views.imagehelper.ResourceDrawableIdHelper;
import android.graphics.Typeface;
import com.facebook.react.views.text.DefaultStyleValuesUtil;
import com.facebook.yoga.YogaConstants;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import java.util.Map;
import com.facebook.react.uimanager.PixelUtil;
import android.content.Context;
import com.facebook.react.uimanager.ReactShadowNode;
import android.widget.TextView$OnEditorActionListener;
import android.view.View$OnFocusChangeListener;
import android.text.TextWatcher;
import com.facebook.react.bridge.ReactContext;
import android.view.View;
import com.facebook.react.uimanager.ThemedReactContext;
import android.text.InputFilter;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.BaseViewManager;

public class ReactTextInputManager extends BaseViewManager<ReactEditText, LayoutShadowNode>
{
    private static final int BLUR_TEXT_INPUT = 2;
    private static final InputFilter[] EMPTY_FILTERS;
    private static final int FOCUS_TEXT_INPUT = 1;
    private static final int IME_ACTION_ID = 1648;
    private static final int INPUT_TYPE_KEYBOARD_NUMBERED = 12290;
    private static final String KEYBOARD_TYPE_EMAIL_ADDRESS = "email-address";
    private static final String KEYBOARD_TYPE_NUMERIC = "numeric";
    private static final String KEYBOARD_TYPE_PHONE_PAD = "phone-pad";
    protected static final String REACT_CLASS = "AndroidTextInput";
    private static final int[] SPACING_TYPES;
    private static final int UNSET = -1;
    
    static {
        SPACING_TYPES = new int[] { 8, 0, 2, 1, 3 };
        EMPTY_FILTERS = new InputFilter[0];
    }
    
    private static void checkPasswordType(final ReactEditText reactEditText) {
        if ((reactEditText.getStagedInputType() & 0x3002) != 0x0 && (reactEditText.getStagedInputType() & 0x80) != 0x0) {
            updateStagedInputTypeFlag(reactEditText, 128, 16);
        }
    }
    
    private static int parseNumericFontWeight(final String s) {
        if (s.length() == 3 && s.endsWith("00") && s.charAt(0) <= '9' && s.charAt(0) >= '1') {
            return (s.charAt(0) - '0') * 'd';
        }
        return -1;
    }
    
    private static void updateStagedInputTypeFlag(final ReactEditText reactEditText, final int n, final int n2) {
        reactEditText.setStagedInputType((reactEditText.getStagedInputType() & ~n) | n2);
    }
    
    @Override
    protected void addEventEmitters(final ThemedReactContext themedReactContext, final ReactEditText reactEditText) {
        reactEditText.addTextChangedListener((TextWatcher)new ReactTextInputManager$ReactTextInputTextWatcher(this, themedReactContext, reactEditText));
        reactEditText.setOnFocusChangeListener((View$OnFocusChangeListener)new ReactTextInputManager$1(this, themedReactContext, reactEditText));
        reactEditText.setOnEditorActionListener((TextView$OnEditorActionListener)new ReactTextInputManager$2(this, themedReactContext, reactEditText));
    }
    
    @Override
    public LayoutShadowNode createShadowNodeInstance() {
        return new ReactTextInputShadowNode();
    }
    
    public ReactEditText createViewInstance(final ThemedReactContext themedReactContext) {
        final ReactEditText reactEditText = new ReactEditText((Context)themedReactContext);
        reactEditText.setInputType(reactEditText.getInputType() & 0xFFFDFFFF);
        reactEditText.setReturnKeyType("done");
        reactEditText.setTextSize(0, (float)(int)Math.ceil(PixelUtil.toPixelFromSP(14.0f)));
        return reactEditText;
    }
    
    @Override
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of("focusTextInput", 1, "blurTextInput", 2);
    }
    
    @Override
    public Map<String, Object> getExportedCustomBubblingEventTypeConstants() {
        return (Map<String, Object>)MapBuilder.builder().put("topSubmitEditing", MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onSubmitEditing", "captured", "onSubmitEditingCapture"))).put("topEndEditing", MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onEndEditing", "captured", "onEndEditingCapture"))).put("topTextInput", MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onTextInput", "captured", "onTextInputCapture"))).put("topFocus", MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onFocus", "captured", "onFocusCapture"))).put("topBlur", MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onBlur", "captured", "onBlurCapture"))).build();
    }
    
    @Override
    public Map getExportedViewConstants() {
        return MapBuilder.of("AutoCapitalizationType", MapBuilder.of("none", 0, "characters", 4096, "words", 8192, "sentences", 16384));
    }
    
    @Override
    public String getName() {
        return "AndroidTextInput";
    }
    
    @Override
    public Class<? extends LayoutShadowNode> getShadowNodeClass() {
        return ReactTextInputShadowNode.class;
    }
    
    @Override
    protected void onAfterUpdateTransaction(final ReactEditText reactEditText) {
        super.onAfterUpdateTransaction(reactEditText);
        reactEditText.commitStagedInputType();
    }
    
    @Override
    public void receiveCommand(final ReactEditText reactEditText, final int n, final ReadableArray readableArray) {
        switch (n) {
            default: {}
            case 1: {
                reactEditText.requestFocusFromJS();
            }
            case 2: {
                reactEditText.clearFocusFromJS();
            }
        }
    }
    
    @ReactProp(name = "autoCapitalize")
    public void setAutoCapitalize(final ReactEditText reactEditText, final int n) {
        updateStagedInputTypeFlag(reactEditText, 28672, n);
    }
    
    @ReactProp(name = "autoCorrect")
    public void setAutoCorrect(final ReactEditText reactEditText, final Boolean b) {
        int n;
        if (b != null) {
            if (b) {
                n = 32768;
            }
            else {
                n = 524288;
            }
        }
        else {
            n = 0;
        }
        updateStagedInputTypeFlag(reactEditText, 557056, n);
    }
    
    @ReactProp(defaultBoolean = true, name = "blurOnSubmit")
    public void setBlurOnSubmit(final ReactEditText reactEditText, final boolean blurOnSubmit) {
        reactEditText.setBlurOnSubmit(blurOnSubmit);
    }
    
    @ReactPropGroup(customType = "Color", names = { "borderColor", "borderLeftColor", "borderRightColor", "borderTopColor", "borderBottomColor" })
    public void setBorderColor(final ReactEditText reactEditText, final int n, final Integer n2) {
        float n3 = Float.NaN;
        float n4;
        if (n2 == null) {
            n4 = Float.NaN;
        }
        else {
            n4 = (n2 & 0xFFFFFF);
        }
        if (n2 != null) {
            n3 = n2 >>> 24;
        }
        reactEditText.setBorderColor(ReactTextInputManager.SPACING_TYPES[n], n4, n3);
    }
    
    @ReactPropGroup(defaultFloat = Float.NaN, names = { "borderRadius", "borderTopLeftRadius", "borderTopRightRadius", "borderBottomRightRadius", "borderBottomLeftRadius" })
    public void setBorderRadius(final ReactEditText reactEditText, final int n, final float n2) {
        float pixelFromDIP = n2;
        if (!YogaConstants.isUndefined(n2)) {
            pixelFromDIP = PixelUtil.toPixelFromDIP(n2);
        }
        if (n == 0) {
            reactEditText.setBorderRadius(pixelFromDIP);
            return;
        }
        reactEditText.setBorderRadius(pixelFromDIP, n - 1);
    }
    
    @ReactProp(name = "borderStyle")
    public void setBorderStyle(final ReactEditText reactEditText, final String borderStyle) {
        reactEditText.setBorderStyle(borderStyle);
    }
    
    @ReactPropGroup(defaultFloat = Float.NaN, names = { "borderWidth", "borderLeftWidth", "borderRightWidth", "borderTopWidth", "borderBottomWidth" })
    public void setBorderWidth(final ReactEditText reactEditText, final int n, final float n2) {
        float pixelFromDIP = n2;
        if (!YogaConstants.isUndefined(n2)) {
            pixelFromDIP = PixelUtil.toPixelFromDIP(n2);
        }
        reactEditText.setBorderWidth(ReactTextInputManager.SPACING_TYPES[n], pixelFromDIP);
    }
    
    @ReactProp(customType = "Color", name = "color")
    public void setColor(final ReactEditText reactEditText, final Integer n) {
        if (n == null) {
            reactEditText.setTextColor(DefaultStyleValuesUtil.getDefaultTextColor(reactEditText.getContext()));
            return;
        }
        reactEditText.setTextColor((int)n);
    }
    
    @ReactProp(defaultBoolean = false, name = "disableFullscreenUI")
    public void setDisableFullscreenUI(final ReactEditText reactEditText, final boolean disableFullscreenUI) {
        reactEditText.setDisableFullscreenUI(disableFullscreenUI);
    }
    
    @ReactProp(defaultBoolean = true, name = "editable")
    public void setEditable(final ReactEditText reactEditText, final boolean enabled) {
        reactEditText.setEnabled(enabled);
    }
    
    @ReactProp(name = "fontFamily")
    public void setFontFamily(final ReactEditText reactEditText, final String s) {
        int style = 0;
        if (reactEditText.getTypeface() != null) {
            style = reactEditText.getTypeface().getStyle();
        }
        reactEditText.setTypeface(Typeface.create(s, style));
    }
    
    @ReactProp(defaultFloat = 14.0f, name = "fontSize")
    public void setFontSize(final ReactEditText reactEditText, final float n) {
        reactEditText.setTextSize(0, (float)(int)Math.ceil(PixelUtil.toPixelFromSP(n)));
    }
    
    @ReactProp(name = "fontStyle")
    public void setFontStyle(final ReactEditText reactEditText, final String s) {
        int n = -1;
        if ("italic".equals(s)) {
            n = 2;
        }
        else if ("normal".equals(s)) {
            n = 0;
        }
        Typeface typeface;
        if ((typeface = reactEditText.getTypeface()) == null) {
            typeface = Typeface.DEFAULT;
        }
        if (n != typeface.getStyle()) {
            reactEditText.setTypeface(typeface, n);
        }
    }
    
    @ReactProp(name = "fontWeight")
    public void setFontWeight(final ReactEditText reactEditText, final String s) {
        final int n = -1;
        int numericFontWeight;
        if (s != null) {
            numericFontWeight = parseNumericFontWeight(s);
        }
        else {
            numericFontWeight = -1;
        }
        int n2 = 0;
        Label_0033: {
            if (numericFontWeight >= 500 || "bold".equals(s)) {
                n2 = 1;
            }
            else {
                if (!"normal".equals(s)) {
                    n2 = n;
                    if (numericFontWeight == -1) {
                        break Label_0033;
                    }
                    n2 = n;
                    if (numericFontWeight >= 500) {
                        break Label_0033;
                    }
                }
                n2 = 0;
            }
        }
        Typeface typeface;
        if ((typeface = reactEditText.getTypeface()) == null) {
            typeface = Typeface.DEFAULT;
        }
        if (n2 != typeface.getStyle()) {
            reactEditText.setTypeface(typeface, n2);
        }
    }
    
    @ReactProp(name = "inlineImageLeft")
    public void setInlineImageLeft(final ReactEditText reactEditText, final String s) {
        reactEditText.setCompoundDrawablesWithIntrinsicBounds(ResourceDrawableIdHelper.getInstance().getResourceDrawableId(reactEditText.getContext(), s), 0, 0, 0);
    }
    
    @ReactProp(name = "inlineImagePadding")
    public void setInlineImagePadding(final ReactEditText reactEditText, final int compoundDrawablePadding) {
        reactEditText.setCompoundDrawablePadding(compoundDrawablePadding);
    }
    
    @ReactProp(name = "keyboardType")
    public void setKeyboardType(final ReactEditText reactEditText, final String s) {
        int n = 1;
        if ("numeric".equalsIgnoreCase(s)) {
            n = 12290;
        }
        else if ("email-address".equalsIgnoreCase(s)) {
            n = 33;
        }
        else if ("phone-pad".equalsIgnoreCase(s)) {
            n = 3;
        }
        updateStagedInputTypeFlag(reactEditText, 12323, n);
        checkPasswordType(reactEditText);
    }
    
    @ReactProp(name = "maxLength")
    public void setMaxLength(final ReactEditText reactEditText, final Integer n) {
        int i = 0;
        final InputFilter[] filters = reactEditText.getFilters();
        final InputFilter[] empty_FILTERS = ReactTextInputManager.EMPTY_FILTERS;
        InputFilter[] filters2;
        if (n == null) {
            filters2 = empty_FILTERS;
            if (filters.length > 0) {
                final LinkedList<InputFilter> list = new LinkedList<InputFilter>();
                while (i < filters.length) {
                    if (!(filters[i] instanceof InputFilter$LengthFilter)) {
                        list.add(filters[i]);
                    }
                    ++i;
                }
                filters2 = empty_FILTERS;
                if (!list.isEmpty()) {
                    filters2 = list.toArray(new InputFilter[list.size()]);
                }
            }
        }
        else if (filters.length > 0) {
            int j = 0;
            boolean b = false;
            while (j < filters.length) {
                if (filters[j] instanceof InputFilter$LengthFilter) {
                    filters[j] = (InputFilter)new InputFilter$LengthFilter((int)n);
                    b = true;
                }
                ++j;
            }
            if (!b) {
                final InputFilter[] array = new InputFilter[filters.length + 1];
                System.arraycopy(filters, 0, array, 0, filters.length);
                filters[filters.length] = (InputFilter)new InputFilter$LengthFilter((int)n);
                filters2 = array;
            }
            else {
                filters2 = filters;
            }
        }
        else {
            filters2 = new InputFilter[] { new InputFilter$LengthFilter((int)n) };
        }
        while (true) {
            reactEditText.setFilters(filters2);
            return;
            continue;
        }
    }
    
    @ReactProp(defaultBoolean = false, name = "multiline")
    public void setMultiline(final ReactEditText reactEditText, final boolean b) {
        int n = 131072;
        int n2;
        if (b) {
            n2 = 0;
        }
        else {
            n2 = 131072;
        }
        if (!b) {
            n = 0;
        }
        updateStagedInputTypeFlag(reactEditText, n2, n);
    }
    
    @ReactProp(defaultInt = 1, name = "numberOfLines")
    public void setNumLines(final ReactEditText reactEditText, final int lines) {
        reactEditText.setLines(lines);
    }
    
    @ReactProp(defaultBoolean = false, name = "onContentSizeChange")
    public void setOnContentSizeChange(final ReactEditText reactEditText, final boolean b) {
        if (b) {
            reactEditText.setContentSizeWatcher(new ReactTextInputManager$ReactContentSizeWatcher(this, reactEditText));
            return;
        }
        reactEditText.setContentSizeWatcher(null);
    }
    
    @ReactProp(defaultBoolean = false, name = "onSelectionChange")
    public void setOnSelectionChange(final ReactEditText reactEditText, final boolean b) {
        if (b) {
            reactEditText.setSelectionWatcher(new ReactTextInputManager$ReactSelectionWatcher(this, reactEditText));
            return;
        }
        reactEditText.setSelectionWatcher(null);
    }
    
    @ReactProp(name = "placeholder")
    public void setPlaceholder(final ReactEditText reactEditText, final String hint) {
        reactEditText.setHint((CharSequence)hint);
    }
    
    @ReactProp(customType = "Color", name = "placeholderTextColor")
    public void setPlaceholderTextColor(final ReactEditText reactEditText, final Integer n) {
        if (n == null) {
            reactEditText.setHintTextColor(DefaultStyleValuesUtil.getDefaultTextColorHint(reactEditText.getContext()));
            return;
        }
        reactEditText.setHintTextColor((int)n);
    }
    
    @ReactProp(name = "returnKeyLabel")
    public void setReturnKeyLabel(final ReactEditText reactEditText, final String s) {
        reactEditText.setImeActionLabel((CharSequence)s, 1648);
    }
    
    @ReactProp(name = "returnKeyType")
    public void setReturnKeyType(final ReactEditText reactEditText, final String returnKeyType) {
        reactEditText.setReturnKeyType(returnKeyType);
    }
    
    @ReactProp(defaultBoolean = false, name = "secureTextEntry")
    public void setSecureTextEntry(final ReactEditText reactEditText, final boolean b) {
        int n = 0;
        int n2;
        if (b) {
            n2 = 0;
        }
        else {
            n2 = 144;
        }
        if (b) {
            n = 128;
        }
        updateStagedInputTypeFlag(reactEditText, n2, n);
        checkPasswordType(reactEditText);
    }
    
    @ReactProp(defaultBoolean = false, name = "selectTextOnFocus")
    public void setSelectTextOnFocus(final ReactEditText reactEditText, final boolean selectAllOnFocus) {
        reactEditText.setSelectAllOnFocus(selectAllOnFocus);
    }
    
    @ReactProp(name = "selection")
    public void setSelection(final ReactEditText reactEditText, final ReadableMap readableMap) {
        if (readableMap != null && readableMap.hasKey("start") && readableMap.hasKey("end")) {
            reactEditText.setSelection(readableMap.getInt("start"), readableMap.getInt("end"));
        }
    }
    
    @ReactProp(customType = "Color", name = "selectionColor")
    public void setSelectionColor(final ReactEditText reactEditText, final Integer n) {
        if (n == null) {
            reactEditText.setHighlightColor(DefaultStyleValuesUtil.getDefaultTextColorHighlight(reactEditText.getContext()));
            return;
        }
        reactEditText.setHighlightColor((int)n);
    }
    
    @ReactProp(name = "textAlign")
    public void setTextAlign(final ReactEditText reactEditText, final String s) {
        if (s == null || "auto".equals(s)) {
            reactEditText.setGravityHorizontal(0);
            return;
        }
        if ("left".equals(s)) {
            reactEditText.setGravityHorizontal(3);
            return;
        }
        if ("right".equals(s)) {
            reactEditText.setGravityHorizontal(5);
            return;
        }
        if ("center".equals(s)) {
            reactEditText.setGravityHorizontal(1);
            return;
        }
        if ("justify".equals(s)) {
            reactEditText.setGravityHorizontal(3);
            return;
        }
        throw new JSApplicationIllegalArgumentException("Invalid textAlign: " + s);
    }
    
    @ReactProp(name = "textAlignVertical")
    public void setTextAlignVertical(final ReactEditText reactEditText, final String s) {
        if (s == null || "auto".equals(s)) {
            reactEditText.setGravityVertical(0);
            return;
        }
        if ("top".equals(s)) {
            reactEditText.setGravityVertical(48);
            return;
        }
        if ("bottom".equals(s)) {
            reactEditText.setGravityVertical(80);
            return;
        }
        if ("center".equals(s)) {
            reactEditText.setGravityVertical(16);
            return;
        }
        throw new JSApplicationIllegalArgumentException("Invalid textAlignVertical: " + s);
    }
    
    @ReactProp(customType = "Color", name = "underlineColorAndroid")
    public void setUnderlineColor(final ReactEditText reactEditText, final Integer n) {
        if (n == null) {
            reactEditText.getBackground().clearColorFilter();
            return;
        }
        reactEditText.getBackground().setColorFilter((int)n, PorterDuff$Mode.SRC_IN);
    }
    
    @Override
    public void updateExtraData(final ReactEditText reactEditText, final Object o) {
        if (o instanceof float[]) {
            final float[] array = (float[])o;
            reactEditText.setPadding((int)Math.floor(array[0]), (int)Math.floor(array[1]), (int)Math.floor(array[2]), (int)Math.floor(array[3]));
        }
        else if (o instanceof ReactTextUpdate) {
            final ReactTextUpdate reactTextUpdate = (ReactTextUpdate)o;
            if (reactTextUpdate.containsImages()) {
                TextInlineImageSpan.possiblyUpdateInlineImageSpans(reactTextUpdate.getText(), (TextView)reactEditText);
            }
            reactEditText.maybeSetText(reactTextUpdate);
        }
    }
}
