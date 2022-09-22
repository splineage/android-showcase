package com.igorwojda.showcase.feature.album.data.datasource.api.service

import com.igorwojda.showcase.base.data.retrofit.ApiResult
import com.igorwojda.showcase.feature.album.data.datasource.api.response.GetAlbumInfoResponse
import com.igorwojda.showcase.feature.album.data.datasource.api.response.SearchAlbumResponse
import retrofit2.http.POST
import retrofit2.http.Query

internal interface AlbumRetrofitService {

    @POST("./?method=album.search")
    suspend fun searchAlbumAsync(
        @Query("album") phrase: String,
        @Query("limit") limit: Int = 60,
    ): ApiResult<SearchAlbumResponse>

    @POST("./?method=album.getInfo")
    suspend fun getAlbumInfoAsync(
        @Query("artist") artistName: String,
        @Query("album") albumName: String,
        @Query("mbid") mbId: String?,
    ): ApiResult<GetAlbumInfoResponse>
}