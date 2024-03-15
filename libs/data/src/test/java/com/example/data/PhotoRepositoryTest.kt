package com.example.data

import assertk.all
import assertk.assertThat
import assertk.assertions.hasSize
import assertk.assertions.isEqualTo
import assertk.assertions.prop
import com.example.domain.models.Author
import com.example.domain.models.FileName
import com.example.domain.models.Id
import com.example.domain.models.Photo
import com.example.domain.models.Size
import kotlinx.coroutines.test.runTest
import org.junit.Test

class PhotoRepositoryTest {
    @Test
    fun `repository maps to domain objects correctly`() = runTest {
        val photoRepository = PhotoRepositoryFake()
        val photos = photoRepository.getPhotos().getResultOrNull()!!
        assertThat(photos).all {
            hasSize(993)
            transform { it.first() }.all {
                prop(Photo::id).isEqualTo(Id(0))
                prop(Photo::author).isEqualTo(Author("Alejandro Escamilla", "https://unsplash.com/photos/yC-Yzbqy7PY"))
                prop(Photo::size).all {
                    prop(Size::width).isEqualTo(5000)
                    prop(Size::height).isEqualTo(3333)
                }
                prop(Photo::fileName).isEqualTo(FileName("0.jpeg"))
            }
            transform { it.last() }.all {
                prop(Photo::id).isEqualTo(Id(1084))
                prop(Photo::author).isEqualTo(Author("Jay Ruzesky", "https://unsplash.com/photos/h13Y8vyIXNU"))
                prop(Photo::size).all {
                    prop(Size::width).isEqualTo(4579)
                    prop(Size::height).isEqualTo(3271)
                }
                prop(Photo::fileName).isEqualTo(FileName("1084.jpeg"))
            }
        }
    }
}