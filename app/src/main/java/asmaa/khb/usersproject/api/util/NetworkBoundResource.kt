package asmaa.khb.usersproject.api.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (ResultType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true },
    crossinline map: (RequestType) -> ResultType

) = flow {
    val data = query().first()
    emit(Resource.Loading(null))
    val flow = if (shouldFetch(data)) {
        try {
            saveFetchResult(map(fetch()))
            query().map { Resource.Success(it) }
        } catch (throwable: Throwable) {
            query().map { Resource.Error(throwable, it) }
        }
    } else {
        query().map { Resource.Success(it) }
    }
    emit(Resource.Loading(data))
    emitAll(flow)
}