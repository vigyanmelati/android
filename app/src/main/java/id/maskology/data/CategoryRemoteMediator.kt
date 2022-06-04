package id.maskology.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import id.maskology.data.local.database.CategoryRemoteKeys
import id.maskology.data.local.database.MaskologyDatabase
import id.maskology.data.model.Category
import id.maskology.data.remote.api.ApiService
import retrofit2.HttpException

@OptIn(ExperimentalPagingApi::class)
class CategoryRemoteMediator(
    private val categoryDatabase: MaskologyDatabase,
    private val apiService: ApiService
): RemoteMediator<Int, Category>()
{
    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Category>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: INITIAL_PAGE_INDEX
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey ?: return MediatorResult.Success(
                    endOfPaginationReached = remoteKeys != null
                )
                prevKey
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey ?: return MediatorResult.Success(
                    endOfPaginationReached = remoteKeys != null
                )
                nextKey
            }
        }
        return try {
            val categoryResponseData = apiService.getAllCategory(page, state.config.pageSize)
            val endOfPaginationReached = categoryResponseData.listCategory.isEmpty()

            categoryDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    categoryDatabase.categoryRemoteKeysDao().deleteCategoryRemoteKeys()
                    categoryDatabase.categoryDao().deleteAllCategory()
                }
                val prevKey = if (page == INITIAL_PAGE_INDEX) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                val keys = categoryResponseData.listCategory.map {
                    CategoryRemoteKeys(id = it.id, prevKey = prevKey, nextKey = nextKey)
                }

                categoryDatabase.categoryRemoteKeysDao().insertAllCategoryRemoteKeys(keys)
                categoryDatabase.categoryDao().insertCategory(categoryResponseData.listCategory)
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Category>) : CategoryRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()?.let { data ->
            categoryDatabase.categoryRemoteKeysDao().getCategoryRemoteKeysId(data.id)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Category>) : CategoryRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { data ->
            categoryDatabase.categoryRemoteKeysDao().getCategoryRemoteKeysId(data.id)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, Category>) : CategoryRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                categoryDatabase.categoryRemoteKeysDao().getCategoryRemoteKeysId(id)
            }
        }
    }

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

}