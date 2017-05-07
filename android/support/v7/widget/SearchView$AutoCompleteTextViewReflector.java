// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.os.ResultReceiver;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import java.lang.reflect.Method;

class SearchView$AutoCompleteTextViewReflector
{
    private Method doAfterTextChanged;
    private Method doBeforeTextChanged;
    private Method ensureImeVisible;
    private Method showSoftInputUnchecked;
    
    SearchView$AutoCompleteTextViewReflector() {
        while (true) {
            try {
                (this.doBeforeTextChanged = AutoCompleteTextView.class.getDeclaredMethod("doBeforeTextChanged", (Class<?>[])new Class[0])).setAccessible(true);
                try {
                    (this.doAfterTextChanged = AutoCompleteTextView.class.getDeclaredMethod("doAfterTextChanged", (Class<?>[])new Class[0])).setAccessible(true);
                    try {
                        (this.ensureImeVisible = AutoCompleteTextView.class.getMethod("ensureImeVisible", Boolean.TYPE)).setAccessible(true);
                        try {
                            (this.showSoftInputUnchecked = InputMethodManager.class.getMethod("showSoftInputUnchecked", Integer.TYPE, ResultReceiver.class)).setAccessible(true);
                        }
                        catch (NoSuchMethodException ex) {}
                    }
                    catch (NoSuchMethodException ex2) {}
                }
                catch (NoSuchMethodException ex3) {}
            }
            catch (NoSuchMethodException ex4) {
                continue;
            }
            break;
        }
    }
    
    void doAfterTextChanged(final AutoCompleteTextView autoCompleteTextView) {
        if (this.doAfterTextChanged == null) {
            return;
        }
        try {
            this.doAfterTextChanged.invoke(autoCompleteTextView, new Object[0]);
        }
        catch (Exception ex) {}
    }
    
    void doBeforeTextChanged(final AutoCompleteTextView autoCompleteTextView) {
        if (this.doBeforeTextChanged == null) {
            return;
        }
        try {
            this.doBeforeTextChanged.invoke(autoCompleteTextView, new Object[0]);
        }
        catch (Exception ex) {}
    }
    
    void ensureImeVisible(final AutoCompleteTextView autoCompleteTextView, final boolean b) {
        if (this.ensureImeVisible == null) {
            return;
        }
        try {
            this.ensureImeVisible.invoke(autoCompleteTextView, b);
        }
        catch (Exception ex) {}
    }
}
