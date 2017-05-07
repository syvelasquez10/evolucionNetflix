// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.speech.tts;

import android.speech.tts.TextToSpeech$OnUtteranceCompletedListener;

final class TextToSpeechICSMR1$2 implements TextToSpeech$OnUtteranceCompletedListener
{
    final /* synthetic */ TextToSpeechICSMR1$UtteranceProgressListenerICSMR1 val$listener;
    
    TextToSpeechICSMR1$2(final TextToSpeechICSMR1$UtteranceProgressListenerICSMR1 val$listener) {
        this.val$listener = val$listener;
    }
    
    public void onUtteranceCompleted(final String s) {
        this.val$listener.onStart(s);
        this.val$listener.onDone(s);
    }
}
