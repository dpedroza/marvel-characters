package com.marvel.domain.core

import io.reactivex.Completable
import io.reactivex.Single


abstract class UseCase<Input, Output> {

    object FromSingle {

        interface WithInput<in Input, Output> {
            fun execute(params: Input): Single<Output>
        }

        interface WithoutInput<Output> {
            fun execute(): Single<Output>
        }
    }

    object FromCompletable {

        interface WithInput<in Input> {
            fun execute(params: Input): Completable
        }

        interface WithoutInput {
            fun execute(): Completable
        }
    }
}