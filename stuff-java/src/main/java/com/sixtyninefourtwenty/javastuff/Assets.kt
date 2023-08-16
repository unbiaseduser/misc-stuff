@file:JvmName("Assets")
@file:Suppress("unused")

package com.sixtyninefourtwenty.javastuff

import android.content.res.AssetManager
import android.graphics.drawable.Drawable

fun AssetManager.openQuietly(path: String) = open(path)

fun AssetManager.openBufferedReader(path: String) = open(path).bufferedReader()

fun AssetManager.readEntireTextFile(path: String) = openBufferedReader(path).use { it.readText() }

fun AssetManager.openBufferedStream(path: String) = open(path).buffered()

fun AssetManager.createDrawableFromAsset(path: String) = open(path).use { Drawable.createFromStream(it, path) }
