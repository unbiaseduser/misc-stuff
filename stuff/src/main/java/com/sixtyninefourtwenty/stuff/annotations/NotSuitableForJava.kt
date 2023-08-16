package com.sixtyninefourtwenty.stuff.annotations

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.FILE, AnnotationTarget.CLASS)
@MustBeDocumented
@Retention(AnnotationRetention.SOURCE)
internal annotation class NotSuitableForJava(val reason: String)
