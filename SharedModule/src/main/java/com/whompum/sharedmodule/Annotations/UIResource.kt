package com.whompum.sharedmodule.Annotations

import java.lang.annotation.Documented
import java.lang.annotation.Inherited

/**
 * Annotation to be placed over a resource
 * expressing which unique layer of a MVP framework it wishes to be used at.
 * This is used for [Lint] analysis to preserve relationships between a MVP layer
 * and its resource fit for its usecase.
 * E.g. a resource providing content, shouldn't be used by a non View layer.
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
@Documented
@Inherited
annotation class UIResource