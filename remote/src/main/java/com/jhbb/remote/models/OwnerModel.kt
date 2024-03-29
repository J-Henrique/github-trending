package com.jhbb.remote.models

import com.google.gson.annotations.SerializedName

class OwnerModel(@SerializedName("login") val ownerName: String,
                 @SerializedName("avatar_url") val ownerAvatar: String)