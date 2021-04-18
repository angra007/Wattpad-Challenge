package com.angraankit.www.wattpad_challenge.cache

import androidx.room.*

@Entity(tableName = "Stories", indices = [Index(value = ["id"], unique = true)])
data class StoriesCacheEntity (

    @PrimaryKey (autoGenerate = false)
    @ColumnInfo(name = "id")
    var id : String,

    @ColumnInfo(name = "title")
    var title : String?,

    @Embedded
    var user : UserCacheEntity?,

    @ColumnInfo(name = "cover")
    var cover : String?

)

@Entity(tableName = "User")
data class UserCacheEntity (
    @PrimaryKey (autoGenerate = false)
    @ColumnInfo(name = "name")
    var name : String,

    @ColumnInfo(name = "avatar")
    var avatar : String?,

    @ColumnInfo(name = "fullname")
    var fullname : String?
)