package com.corpus.carousal.model

data class CarousalResponse(
    val responseStatus: ResponseStatus,
    val content: List<ContentItem>
)

data class ResponseStatus(
    val statusMessage: String,
    val statusCode: String
)

data class ContentItem(
    val isBigBannerAdEnabled: Boolean,
    val isSubscriberSpecific: Boolean,
    val bigBannerAdValue: String,
    val iconType: String,
    val displayOrder: Int,
    val title: String,
    val contentType: String,
    val command: String,
    val content: String,
    val displayNumberSeries: Boolean,
    val adImageType: String,
    val externalLink: String,
    val kind: String,
    val description: String,
    val onScreenAction: String,
    val stbCarouselImage: String,
    val mobileCarouselImage: String,
    val otherDeviceCarouselImage: String,
    val franchiseId: Int,
    val shareUrl: String,
    val id: Int
)

