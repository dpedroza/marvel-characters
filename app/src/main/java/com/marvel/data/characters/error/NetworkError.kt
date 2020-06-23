package com.marvel.data.characters.error

sealed class NetworkError : Throwable() {
    class NotConnected : NetworkError()
    class SlowConnection : NetworkError()
    class Canceled : NetworkError()
}
