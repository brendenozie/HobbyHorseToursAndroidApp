package ke.co.tulivuapps.hobbyhorsetours.data.remote.utils

import ke.co.tulivuapps.hobbyhorsetours.data.model.APIError

/**
 * Created by brendenozie on 12.03.2023
 */

sealed class DataState<T> {
    class Success<T>(val data: T) : DataState<T>()
    class Loading<T> : DataState<T>()
    class Error<T>(val apiError: APIError?) : DataState<T>()
}