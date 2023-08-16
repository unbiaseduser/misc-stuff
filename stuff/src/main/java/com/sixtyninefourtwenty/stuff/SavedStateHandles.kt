@file:JvmName("SavedStateHandles")
@file:Suppress("unused")

package com.sixtyninefourtwenty.stuff

import android.os.Bundle
import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import java.io.Serializable

fun SavedStateHandle.getBoolean(key: String) = get<Boolean>(key)
fun SavedStateHandle.getBooleanOrDefault(key: String, defValue: Boolean) = getBoolean(key) ?: defValue
fun SavedStateHandle.setBoolean(key: String, value: Boolean) = set(key, value)

fun SavedStateHandle.getByte(key: String) = get<Byte>(key)
fun SavedStateHandle.getByteOrDefault(key: String, defValue: Byte) = getByte(key) ?: defValue
fun SavedStateHandle.setByte(key: String, value: Byte) = set(key, value)

fun SavedStateHandle.getChar(key: String) = get<Char>(key)
fun SavedStateHandle.getCharOrDefault(key: String, defValue: Char) = getChar(key) ?: defValue
fun SavedStateHandle.setChar(key: String, value: Char) = set(key, value)

fun SavedStateHandle.getDouble(key: String) = get<Double>(key)
fun SavedStateHandle.getDoubleOrDefault(key: String, defValue: Double) = getDouble(key) ?: defValue
fun SavedStateHandle.setDouble(key: String, value: Double) = set(key, value)

fun SavedStateHandle.getFloat(key: String) = get<Float>(key)
fun SavedStateHandle.getFloatOrDefault(key: String, defValue: Float) = getFloat(key) ?: defValue
fun SavedStateHandle.setFloat(key: String, value: Float) = set(key, value)

fun SavedStateHandle.getInt(key: String) = get<Int>(key)
fun SavedStateHandle.getIntOrDefault(key: String, defValue: Int) = getInt(key) ?: defValue
fun SavedStateHandle.setInt(key: String, value: Int) = set(key, value)

fun SavedStateHandle.getLong(key: String) = get<Long>(key)
fun SavedStateHandle.getLongOrDefault(key: String, defValue: Long) = getLong(key) ?: defValue
fun SavedStateHandle.setLong(key: String, value: Long) = set(key, value)

fun SavedStateHandle.getShort(key: String) = get<Short>(key)
fun SavedStateHandle.getShortOrDefault(key: String, defValue: Short) = getShort(key) ?: defValue
fun SavedStateHandle.setShort(key: String, value: Short) = set(key, value)

fun SavedStateHandle.getBundle(key: String) = get<Bundle>(key)
fun SavedStateHandle.setBundle(key: String, value: Bundle) = set(key, value)

fun SavedStateHandle.getCharSequence(key: String) = get<CharSequence>(key)
fun SavedStateHandle.setCharSequence(key: String, value: CharSequence) = set(key, value)

fun <T : Parcelable> SavedStateHandle.getParcelable(key: String) = get<T>(key)
fun SavedStateHandle.setParcelable(key: String, value: Parcelable) = set(key, value)

fun SavedStateHandle.getBooleanArray(key: String) = get<BooleanArray>(key)
fun SavedStateHandle.setBooleanArray(key: String, value: BooleanArray) = set(key, value)

fun SavedStateHandle.getByteArray(key: String) = get<ByteArray>(key)
fun SavedStateHandle.setByteArray(key: String, value: ByteArray) = set(key, value)

fun SavedStateHandle.getCharArray(key: String) = get<CharArray>(key)
fun SavedStateHandle.setCharArray(key: String, value: CharArray) = set(key, value)

fun SavedStateHandle.getDoubleArray(key: String) = get<DoubleArray>(key)
fun SavedStateHandle.setDoubleArray(key: String, value: DoubleArray) = set(key, value)

fun SavedStateHandle.getFloatArray(key: String) = get<FloatArray>(key)
fun SavedStateHandle.setFloatArray(key: String, value: FloatArray) = set(key, value)

fun SavedStateHandle.getIntArray(key: String) = get<IntArray>(key)
fun SavedStateHandle.setIntArray(key: String, value: IntArray) = set(key, value)

fun SavedStateHandle.getLongArray(key: String) = get<LongArray>(key)
fun SavedStateHandle.setLongArray(key: String, value: LongArray) = set(key, value)

fun SavedStateHandle.getShortArray(key: String) = get<ShortArray>(key)
fun SavedStateHandle.setShortArray(key: String, value: ShortArray) = set(key, value)

fun SavedStateHandle.getParcelableArray(key: String) = get<Array<Parcelable>>(key)
fun SavedStateHandle.setParcelableArray(key: String, value: Array<Parcelable>) = set(key, value)

fun SavedStateHandle.getStringArray(key: String) = get<Array<String>>(key)
fun SavedStateHandle.setStringArray(key: String, value: Array<String>) = set(key, value)

fun SavedStateHandle.getCharSequenceArray(key: String) = get<Array<CharSequence>>(key)
fun SavedStateHandle.setCharSequenceArray(key: String, value: Array<CharSequence>) = set(key, value)

fun <T : Serializable> SavedStateHandle.getSerializable(key: String) = get<T>(key)
fun SavedStateHandle.setSerializable(key: String, value: Serializable) = set(key, value)
