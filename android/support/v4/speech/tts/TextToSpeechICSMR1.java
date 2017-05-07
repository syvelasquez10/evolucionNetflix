// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.speech.tts;

import android.speech.tts.TextToSpeech$OnUtteranceCompletedListener;
import android.speech.tts.UtteranceProgressListener;
import android.os.Build$VERSION;
import java.util.Set;
import java.util.Locale;
import android.speech.tts.TextToSpeech;

class TextToSpeechICSMR1
{
    public static final String KEY_FEATURE_EMBEDDED_SYNTHESIS = "embeddedTts";
    public static final String KEY_FEATURE_NETWORK_SYNTHESIS = "networkTts";
    
    static Set<String> getFeatures(final TextToSpeech textToSpeech, final Locale locale) {
        if (Build$VERSION.SDK_INT >= 15) {
            return (Set<String>)textToSpeech.getFeatures(locale);
        }
        return null;
    }
    
    static void setUtteranceProgressListener(final TextToSpeech textToSpeech, final TextToSpeechICSMR1$UtteranceProgressListenerICSMR1 textToSpeechICSMR1$UtteranceProgressListenerICSMR1) {
        if (Build$VERSION.SDK_INT >= 15) {
            textToSpeech.setOnUtteranceProgressListener((UtteranceProgressListener)new TextToSpeechICSMR1$1(textToSpeechICSMR1$UtteranceProgressListenerICSMR1));
            return;
        }
        textToSpeech.setOnUtteranceCompletedListener((TextToSpeech$OnUtteranceCompletedListener)new TextToSpeechICSMR1$2(textToSpeechICSMR1$UtteranceProgressListenerICSMR1));
    }
}
