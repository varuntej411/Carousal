package com.corpus.carousal.domain.model

import com.google.gson.annotations.SerializedName

data class CarousalResponse(

    @SerializedName("responseStatus")
    val responseStatus: ResponseStatus,

    @SerializedName("content")
    val content: List<ContentItem>
)

data class Content(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("stbCarouselImage")
    val stbCarouselImage: String,
    @SerializedName("otherDeviceCarouselImage")
    val otherDeviceCarouselImage: String,
    @SerializedName("mobileCarouselImage")
    val mobileCarouselImage: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("shareUrl")
    val shareUrl: String,
    @SerializedName("kind")
    val kind: String,
    @SerializedName("contentType")
    val contentType: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("onScreenAction")
    val onScreenAction: String,
    @SerializedName("externalLink")
    val externalLink: String,
    @SerializedName("franchiseId")
    val franchiseId: Int,
)

data class ContentItem(

    @SerializedName("isBigBannerAdEnabled")
    val isBigBannerAdEnabled: Boolean,

    @SerializedName("isSubscriberSpecific")
    val isSubscriberSpecific: Boolean,

    @SerializedName("bigBannerAdValue")
    val bigBannerAdValue: String,

    @SerializedName("iconType")
    val iconType: String,

    @SerializedName("displayOrder")
    val displayOrder: Int,

    @SerializedName("command")
    val command: String,

    @SerializedName("content")
    val contentItem: List<Content>,

    @SerializedName("displayNumberSeries")
    val displayNumberSeries: Boolean,

    @SerializedName("adImageType")
    val adImageType: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("contentType")
	val contentType: String
)

data class ResponseStatus(

    @SerializedName("statusMessage")
    val statusMessage: String,

    @SerializedName("statusCode")
    val statusCode: String
)
