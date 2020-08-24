package com.w.twitterfeed.supporting

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): Resource<T> = Resource(status = Status.Success, data = data, message = null)

        fun <T> error(data: T?, message: String): Resource<T> =
            Resource(status = Status.Error, data = data, message = message)

        fun <T> loading(data: T?): Resource<T> = Resource(status = Status.Loading, data = data, message = null)
    }
}