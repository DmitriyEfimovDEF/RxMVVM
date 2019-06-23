package ru.aisdev.rxjavamvvm.rx


import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.AsyncSubject
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.ReplaySubject
import ru.aisdev.rxjavamvvm.R
import java.util.*
import java.util.concurrent.TimeUnit

class BasicsRxJava : AppCompatActivity() {

    companion object {
        private const val TAG = "~~~~~~~~~~~~~~~"
        val justString = Observable.just("one", "two", "three", "four", "three", "two")
        val secondJustString = Observable.just("bitch","whore","pussy","cunt")
        private val just = Observable.just(2,5,1,5,2,11234,2,4,5,6)

        val professor = PublishSubject.create<String>()


    }

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //region startingTheObservables
        //        createFromIterable().subscribe { data ->
//            println("$TAG CREATE FROM ITERABLE")
//            println("$TAG received data is : $data}")
//        }
//        createTimer()
//            .doOnNext{
//                println("$TAG item is : $it")
//            }
//            .subscribe{ a->
//            println("$TAG CREATE TIMER")
//            println("$TAG  the food is ready after : $a")
//        }
//
//        println("$TAG CREATE FROM RANGE")
//        createRange()
//            .delay(1,TimeUnit.SECONDS)
//            .subscribe{data->
//            println("$TAG received data  is : $data}")
//        }
//        println("$TAG CREATE FROM INTERVAL")
//        createInterval()
//            .delay(1,TimeUnit.SECONDS)
//            .subscribe{data->
//                println("$TAG received data  is : $data}")
//            }
//        println("$TAG CREATE FROM JUST WITH FILTER")
//        createFilter()
//            .subscribe{data->
//                println("$TAG received data  is : $data}")
//            }
//        println("$TAG CREATE FROM JUST WITH TAKELAST")
//        createTakeLast()
//            .subscribe{data->
//                println("$TAG received data  is : $data}")
//            }
//        println("$TAG CREATE FROM JUST WITH TIMEOUT")
//        createTimeout("some")
//            .subscribe({ name ->
//                println("$TAG Hello,$name}")
//            }, { error ->
//                println("$TAG, error is: ${error.localizedMessage}")
//            }).dispose()
//        println("$TAG CREATE FROM JUST WITH DISTINCT")
//        createDistinct()
//            .subscribe({ value ->
//                println("$TAG value is $value}")
//            }, { error ->
//                println("$TAG, error is: ${error.localizedMessage}")
//            }).dispose()
//        println("$TAG CREATE FROM JUST WITH STARTWITH")
//        createStartWith()
//            .subscribe({ value ->
//                println("$TAG value is $value}")
//            }, { error ->
//                println("$TAG, error is: ${error.localizedMessage}")
//            }).dispose()

//
//        println("$TAG CREATE FROM JUST WITH MERGE")
//        createMerge()
//            .subscribe({ value ->
//                println("$TAG value is $value}")
//            }, { error ->
//                println("$TAG, error is: ${error.localizedMessage}")
//            }).dispose()

//        println("$TAG CREATE FROM JUST WITH CONCAT")
//        createConcat()
//            .subscribe({ value ->
//                println("$TAG value is $value}")
//            }, { error ->
//                println("$TAG, error is: ${error.localizedMessage}")
//            }).dispose()

//        println("$TAG CREATE FROM JUST WITH ZIP_OPERATOR")
//        createZipOperator()
//            .subscribe({ value ->
//                println("$TAG value is $value}")
//            }, { error ->
//                println("$TAG, error is: ${error.localizedMessage}")
//            }).dispose()
//        println("$TAG CREATE FROM JUST WITH MAP_OPERATOR")
//        createMap()
//            .subscribe({ value ->
//                println("$TAG value is $value}")
//            }, { error ->
//                println("$TAG, error is: ${error.localizedMessage}")
//            }).dispose()
//
//        println("$TAG CREATE FROM JUST WITH FLAT_MAP_OPERATOR")
//        createFlatMap()
//            .subscribe({ value ->
//                println("$TAG value is $value}")
//            }, { error ->
//                println("$TAG, error is: ${error.localizedMessage}")
//            }).dispose()
        //endregion
        //region PUBLISH_SUBJECT
        //        professor.subscribe(getFirstStudent())
//        professor.onNext("kotlin")
//        professor.onNext("java")
//        professor.onNext("reactive")
//
//        professor.subscribe(getLateStudent())
//        professor.onNext("scala")
//        professor.onComplete()
        //endregion
        //region REPLAY_SUBJECT
        //        val professor = ReplaySubject.create<String>()
//        professor.subscribe(getFirstStudent())
//        professor.onNext("kotlin")
//        professor.onNext("java")
//        professor.onNext("C++")
//
//        professor.subscribe(getLateStudent())
//        professor.onNext("scala")
//        professor.onComplete()
        //endregion
        val professor = AsyncSubject.create<String>()
        professor.subscribe(getFirstStudent())
        professor.onNext("kotlin")
        professor.onNext("java")
        professor.onNext("C++")

        professor.subscribe(getLateStudent())
        professor.onNext("scala")
        professor.onComplete()
    }

    private fun getFirstStudent(): Observer<String> {
        return object : Observer<String> {
            override fun onComplete() {
                println("$TAG the lecture is over")
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: String) {
                println("$TAG our professor teach us a $t")
            }

            override fun onError(e: Throwable) {
                println("$TAG error is ${e.localizedMessage}")
            }

        }
    }
    private fun getLateStudent(): Observer<String> {
        return object : Observer<String> {
            override fun onComplete() {
                println("$TAG the lecture is over for the latest student")
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: String) {
                println("$TAG our professor teach us a $t for the latest student")

            }

            override fun onError(e: Throwable) {
                println("$TAG error is ${e.localizedMessage} for the latest student")

            }

        }
    }

    //region creating different types of observables
    private fun createMap() = just
        .map {value -> value*5}

    private fun createFlatMap() = justString
        .flatMap{
            getName(it)
        }
    private fun getName(id: String): Observable<String> {
        val names = arrayListOf<String>("kostya", "petya", "vasya")
        val rand = Random().nextInt(3)
        return Observable.just(names[rand])
    }


//    private fun createZipOperator() = justString.zipWith(secondJustString, BiFunction{first,second ->
//            "$first $second"
//        })


    private fun createMerge(): Observable<Int> {
        val firstStream = Observable.just(1, 2, 3)
        val secondStream = Observable.just(4,5,6)

        return firstStream.mergeWith(secondStream)
    }
    private fun createConcat(): Observable<Int> {
        val firstStream = Observable.just(1, 2, 3)
        val secondStream = Observable.just(4,5,6)

        return firstStream.concatWith(secondStream)
    }

    private fun createStartWith() = justString.startWith("ten")

    //       create a simple observer on data stream
    private fun showHowJustWorks() = just
        .subscribe(object : Observer<Int> {
            override fun onComplete()               {println("$TAG all data is received, completed") }
            override fun onSubscribe(d: Disposable) {println("$TAG al data is received: $")}
            override fun onNext(t: Int)             {println("$TAG new item of data stream is received: $t")}
            override fun onError(e: Throwable)      {println("$TAG an error is received ${e.localizedMessage}")
            }
        })

    private fun createTimer() = Observable.timer(5,TimeUnit.SECONDS)

    private fun createInterval() =Observable.interval(1, TimeUnit.SECONDS).takeWhile{value -> value<21}

    private fun createRange() = Observable.range(1, 3)//repeat(3)

    private fun createFromIterable() = Observable.fromIterable(listOf(2, 5, 7))

    private fun createFromArray() = Observable.fromArray(arrayOf(1,5,8,47,1))

    private fun createFilter() = just
        .filter { x-> x > 10 }


    private fun createTakeLast() = Observable.just(2, 40, 5, 30)
        .takeLast(3)

    private fun createTimeout(name:String) = Observable.fromCallable {
        if (name == "somename")
            Thread.sleep(1500)
            name
        }.timeout(1L,TimeUnit.SECONDS)

    private fun createDistinct() = just
        .distinct()
    //endregion

}
