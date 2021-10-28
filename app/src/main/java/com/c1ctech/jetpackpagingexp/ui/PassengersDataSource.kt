package com.c1ctech.jetpackpagingexp.ui

import androidx.paging.PagingSource
import com.c1ctech.jetpackpagingexp.data.MyApi
import com.c1ctech.jetpackpagingexp.data.models.Passenger
import com.c1ctech.jetpackpagingexp.data.models.PassengersResponse

class PassengersDataSource(private val api: MyApi) : PagingSource<Int, Passenger>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Passenger> {
        return try {
            val nextPageNumber = params.key ?: 0
            val response: PassengersResponse = api.getPassengersData(nextPageNumber)

            LoadResult.Page(
                data = response.data,
                prevKey = if (nextPageNumber > 0) nextPageNumber - 1 else null,
                nextKey = if (nextPageNumber < response.totalPages) nextPageNumber + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}