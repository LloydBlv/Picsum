package com.example.data

import assertk.all
import assertk.assertThat
import assertk.assertions.hasSize
import assertk.assertions.isEqualTo
import assertk.assertions.prop
import com.example.testing.TestData.provideTestPhotoDtos
import com.squareup.moshi.Moshi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class PhotoDtoTest {

    private lateinit var moshi: Moshi

    @Before
    fun setup() {
        moshi = Moshi.Builder().build()
    }

    @Test
    fun `test photo list json is parsed correctly`() {
        val photosList = moshi.provideTestPhotoDtos()
        assertThat(photosList).all {
            hasSize(993)
            transform { it.first() }.all {
                prop(PhotoDto::id).isEqualTo(0)
                prop(PhotoDto::author).isEqualTo("Alejandro Escamilla")
                prop(PhotoDto::authorUrl).isEqualTo("https://unsplash.com/photos/yC-Yzbqy7PY")
                prop(PhotoDto::width).isEqualTo(5000)
                prop(PhotoDto::height).isEqualTo(3333)
                prop(PhotoDto::fileName).isEqualTo("0.jpeg")
            }
            transform { it.last() }.all {
                prop(PhotoDto::id).isEqualTo(1084)
                prop(PhotoDto::author).isEqualTo("Jay Ruzesky")
                prop(PhotoDto::authorUrl).isEqualTo("https://unsplash.com/photos/h13Y8vyIXNU")
                prop(PhotoDto::width).isEqualTo(4579)
                prop(PhotoDto::height).isEqualTo(3271)
                prop(PhotoDto::fileName).isEqualTo("1084.jpeg")
            }
        }
        assertEquals(photosList.size, 993)
    }
}
