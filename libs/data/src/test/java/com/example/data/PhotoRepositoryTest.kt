package com.example.data

import app.cash.turbine.test
import assertk.all
import assertk.assertThat
import assertk.assertions.hasSize
import assertk.assertions.isEqualTo
import assertk.assertions.prop
import com.example.domain.models.models.Author
import com.example.domain.models.models.FileName
import com.example.domain.models.models.Id
import com.example.domain.models.models.Photo
import com.example.domain.models.models.Size
import kotlinx.coroutines.test.runTest
import org.junit.Test

class PhotoRepositoryTest {
    @Test
    fun `repository maps to domain objects correctly`() = runTest {
        val photoRepository = com.example.testing.PhotoRepositoryFake()
        photoRepository.getPhotos().test {
            val photos = awaitItem().getOrNull()!!
            assertThat(photos).all {
                hasSize(993)
                transform { it.first() }.all {
                    prop(Photo::id).isEqualTo(Id(0))
                    prop(
                        Photo::author
                    ).isEqualTo(Author("Alejandro Escamilla", "https://unsplash.com/photos/yC-Yzbqy7PY"))
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
            awaitComplete()
        }
    }
}
