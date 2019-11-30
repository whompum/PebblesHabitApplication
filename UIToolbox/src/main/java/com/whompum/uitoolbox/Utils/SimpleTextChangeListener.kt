package com.whompum.uitoolbox.Utils

import android.text.Editable
import android.text.TextWatcher

/**
 * Super simple class that acts as a fix around Androids
 * poor Text-listening implementation. This class simply provides
 * stubs around [TextWatcher] callbacks that can be overriden as needed.
 */
open class SimpleTextChangeListener: TextWatcher {
    override fun afterTextChanged(p0: Editable?) {}
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
}