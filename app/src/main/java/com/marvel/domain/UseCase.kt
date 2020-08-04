package com.marvel.domain

abstract class UseCase {

    object Single {

        interface WithInput<in Input, Output> {
            fun execute(params: Input): io.reactivex.Single<Output>
        }

        interface WithoutInput<Output> {
            fun execute(): io.reactivex.Single<Output>
        }
    }

    object Completable {

        interface WithInput<in Input> {
            fun execute(params: Input): io.reactivex.Completable
        }
    }
}
