package com.sixtyninefourtwenty.stuff

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate

/**
 * Tests for type-safe savedstatehandle extensions to make sure the underlying savedstatehandle actually accepts those types
 */
class SavedStateHandleExtensionsTest {

    companion object {
        const val KEY = "foo"
    }

    private lateinit var savedStateHandle: SavedStateHandle

    @BeforeEach
    fun init() {
        savedStateHandle = SavedStateHandle()
    }

    @Test
    fun booleanTest() {
        assertNull(savedStateHandle.getBoolean(KEY))
        assertFalse(savedStateHandle.getBooleanOrDefault(KEY, false))
        savedStateHandle.setBoolean(KEY, true)
        assertTrue(savedStateHandle.getBooleanOrDefault(KEY, false))
    }

    @Test
    fun byteTest() {
        assertNull(savedStateHandle.getByte(KEY))
        assertEquals(-1, savedStateHandle.getByteOrDefault(KEY, -1))
        savedStateHandle.setByte(KEY, 1)
        assertEquals(1, savedStateHandle.getByteOrDefault(KEY, -1))
    }

    @Test
    fun charTest() {
        assertNull(savedStateHandle.getChar(KEY))
        assertEquals('a', savedStateHandle.getCharOrDefault(KEY, 'a'))
        savedStateHandle.setChar(KEY, 'b')
        assertEquals('b', savedStateHandle.getCharOrDefault(KEY, 'a'))
    }

    @Test
    fun doubleTest() {
        assertNull(savedStateHandle.getDouble(KEY))
        assertEquals(Double.MIN_VALUE, savedStateHandle.getDoubleOrDefault(KEY, Double.MIN_VALUE))
        savedStateHandle.setDouble(KEY, 1.0)
        assertEquals(1.0, savedStateHandle.getDoubleOrDefault(KEY, Double.MIN_VALUE))
    }

    @Test
    fun floatTest() {
        assertNull(savedStateHandle.getFloat(KEY))
        assertEquals(Float.MIN_VALUE, savedStateHandle.getFloatOrDefault(KEY, Float.MIN_VALUE))
        savedStateHandle.setFloat(KEY, 1.0F)
        assertEquals(1.0F, savedStateHandle.getFloatOrDefault(KEY, Float.MIN_VALUE))
    }

    @Test
    fun intTest() {
        assertNull(savedStateHandle.getInt(KEY))
        assertEquals(Int.MIN_VALUE, savedStateHandle.getIntOrDefault(KEY, Int.MIN_VALUE))
        savedStateHandle.setInt(KEY, 1)
        assertEquals(1, savedStateHandle.getIntOrDefault(KEY, Int.MIN_VALUE))
    }

    @Test
    fun longTest() {
        assertNull(savedStateHandle.getLong(KEY))
        assertEquals(Long.MIN_VALUE, savedStateHandle.getLongOrDefault(KEY, Long.MIN_VALUE))
        savedStateHandle.setLong(KEY, 1)
        assertEquals(1, savedStateHandle.getLongOrDefault(KEY, Long.MIN_VALUE))
    }

    @Test
    fun shortTest() {
        assertNull(savedStateHandle.getShort(KEY))
        assertEquals(Short.MIN_VALUE, savedStateHandle.getShortOrDefault(KEY, Short.MIN_VALUE))
        savedStateHandle.setShort(KEY, 1)
        assertEquals(1, savedStateHandle.getShortOrDefault(KEY, Short.MIN_VALUE))
    }

    @Test
    fun bundleTest() {
        assertNull(savedStateHandle.getBundle(KEY))
        val bundle = Bundle()
        savedStateHandle.setBundle(KEY, bundle)
        assertEquals(bundle, savedStateHandle.getBundle(KEY))
    }

    @Test
    fun charSequenceTest() {
        assertNull(savedStateHandle.getCharSequence(KEY))
        val stringBuilder = StringBuilder()
        savedStateHandle.setCharSequence(KEY, stringBuilder)
        assertEquals(stringBuilder, savedStateHandle.getCharSequence(KEY))
    }

    @Test
    fun parcelableTest() {
        assertNull(savedStateHandle.getParcelable(KEY))
        val obj = MyParcelable()
        savedStateHandle.setParcelable(KEY, obj)
        assertEquals(obj, savedStateHandle.getParcelable(KEY))
    }

    @Test
    fun serializableTest() {
        assertNull(savedStateHandle.getSerializable(KEY))
        val today = LocalDate.now()
        savedStateHandle.setSerializable(KEY, today)
        assertEquals(today, savedStateHandle.getSerializable(KEY))
    }

    @Test
    fun booleanArrayTest() {
        assertNull(savedStateHandle.getBooleanArray(KEY))
        val array = BooleanArray(1)
        savedStateHandle.setBooleanArray(KEY, array)
        assertEquals(array, savedStateHandle.getBooleanArray(KEY))
    }

    @Test
    fun byteArrayTest() {
        assertNull(savedStateHandle.getByteArray(KEY))
        val array = ByteArray(1)
        savedStateHandle.setByteArray(KEY, array)
        assertEquals(array, savedStateHandle.getByteArray(KEY))
    }

    @Test
    fun charArrayTest() {
        assertNull(savedStateHandle.getCharArray(KEY))
        val array = CharArray(1)
        savedStateHandle.setCharArray(KEY, array)
        assertEquals(array, savedStateHandle.getCharArray(KEY))
    }

    @Test
    fun doubleArrayTest() {
        assertNull(savedStateHandle.getDoubleArray(KEY))
        val array = DoubleArray(1)
        savedStateHandle.setDoubleArray(KEY, array)
        assertEquals(array, savedStateHandle.getDoubleArray(KEY))
    }

    @Test
    fun floatArrayTest() {
        assertNull(savedStateHandle.getFloatArray(KEY))
        val array = FloatArray(1)
        savedStateHandle.setFloatArray(KEY, array)
        assertEquals(array, savedStateHandle.getFloatArray(KEY))
    }

    @Test
    fun intArrayTest() {
        assertNull(savedStateHandle.getIntArray(KEY))
        val array = IntArray(1)
        savedStateHandle.setIntArray(KEY, array)
        assertEquals(array, savedStateHandle.getIntArray(KEY))
    }

    @Test
    fun longArrayTest() {
        assertNull(savedStateHandle.getLongArray(KEY))
        val array = LongArray(1)
        savedStateHandle.setLongArray(KEY, array)
        assertEquals(array, savedStateHandle.getLongArray(KEY))
    }

    @Test
    fun shortArrayTest() {
        assertNull(savedStateHandle.getShortArray(KEY))
        val array = ShortArray(1)
        savedStateHandle.setShortArray(KEY, array)
        assertEquals(array, savedStateHandle.getShortArray(KEY))
    }

    @Test
    fun parcelableArrayTest() {
        assertNull(savedStateHandle.getParcelableArray(KEY))
        val array = arrayOf<Parcelable>()
        savedStateHandle.setParcelableArray(KEY, array)
        assertEquals(array, savedStateHandle.getParcelableArray(KEY))
    }

    @Test
    fun stringArrayTest() {
        assertNull(savedStateHandle.getStringArray(KEY))
        val array = arrayOf<String>()
        savedStateHandle.setStringArray(KEY, array)
        assertEquals(array, savedStateHandle.getStringArray(KEY))
    }

    @Test
    fun charSequenceArrayTest() {
        assertNull(savedStateHandle.getCharSequenceArray(KEY))
        val array = arrayOf<CharSequence>()
        savedStateHandle.setCharSequenceArray(KEY, array)
        assertEquals(array, savedStateHandle.getCharSequenceArray(KEY))
    }

    private class MyParcelable : Parcelable {
        override fun describeContents(): Int = 0

        override fun writeToParcel(dest: Parcel, flags: Int) {

        }
    }

}