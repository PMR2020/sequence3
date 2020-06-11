package fr.ec.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Post(
    @PrimaryKey
    val id: Long,
    val title: String,
    val subTitle: String,
    val imageUrl: String
)
