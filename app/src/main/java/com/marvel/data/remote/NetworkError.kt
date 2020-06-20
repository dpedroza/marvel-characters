package com.marvel.data.remote

sealed class NetworkError : Throwable() {
    class NotConnected : NetworkError()
    class SlowConnection : NetworkError()
    class Canceled : NetworkError()
}