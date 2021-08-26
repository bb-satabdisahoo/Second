package com.example.rxjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName(); //returns the simple name of the underlying class
    //private Disposable disposable;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observable<String> animalsObservable = getAnimalsObservable();
        //Observer<String> animalsObserver = getAnimalsObserver();
        DisposableObserver<String> animalsObserver = getAnimalsObserver();
        DisposableObserver<String> animalsObserverAllCaps = getanimalsObserverAllCaps();

        compositeDisposable.add(
                animalsObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(@NonNull String s) throws Exception {
                        return s.toLowerCase().startsWith("b");
                    }
                })
                .subscribeWith(animalsObserver));

        compositeDisposable.add(
                animalsObservable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .filter(new Predicate<String>() {
                            @Override
                            public boolean test(@NonNull String s) throws Exception {
                                return s.toLowerCase().startsWith("c");
                            }
                        })
                        .map(new Function<String,String>(){
                            @Override
                            public String apply(String s)throws Exception{
                                return s.toUpperCase();
                            }
                             })
                        .subscribeWith(animalsObserverAllCaps));
        
        compositeDisposable.add(getNoteObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<Note, Note>() {
                    @Override
                    public Note apply(Note note) throws Exception{
                        note.setNote(note.getNote().toUpperCase());
                        return note;
                    }
                })
                .subscribeWith(getNoteObserver())
        );

        Observable.range(1,20)
                .repeat(2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(@NonNull Integer integer) throws Exception {
                        return integer%2==0;
                    }
                })
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer)throws Exception{
                        return integer + " is even";
                    }
                })
                /*.subscribe(new DisposableObserver<Integer>() {
                    @Override
                    public void onNext(@NonNull Integer integer) {
                        Log.d(TAG, "Number: " + integer);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "All numbers emitted!");
                    }
                });*/
        .subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d(TAG, "Subscribed");
            }

            @Override
            public void onNext(@NonNull String s) {
                Log.d(TAG, s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "All numbers emitted!");
            }
        });


    }

    private DisposableObserver<Note> getNoteObserver() {
        return new DisposableObserver<Note>() {
            @Override
            public void onNext(@NonNull Note note) {
                Log.d(TAG, note.getId() + ". " +note.getNote());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "All notes are emitted!");
            }
        };
    }

    private Observable<Note> getNoteObservable() {
        final List<Note> notes = prepareNote();
        return Observable.create(new ObservableOnSubscribe<Note>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Note> emitter) throws Exception {
                for(Note note: notes){
                    if(!emitter.isDisposed())
                        emitter.onNext(note);
                }
                if(!emitter.isDisposed())
                    emitter.onComplete();
            }
        });
    }

    private List<Note> prepareNote() {
        List<Note> notes = new ArrayList<>();
        notes.add(new Note(1,"One"));
        notes.add(new Note(2,"Two"));
        notes.add(new Note(3,"Three"));
        notes.add(new Note(4,"Four"));
        notes.add(new Note(5,"Five"));
        return notes;
    }

    private DisposableObserver<String> getanimalsObserverAllCaps() {
        return new DisposableObserver<String>() {
            @Override
            public void onNext(@NonNull String s) {
                Log.d(TAG, "Name: " + s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "All items are emitted!");
            }
        };
    }



    private DisposableObserver<String> getAnimalsObserver() {
        return new DisposableObserver<String>(){

            @Override
            public void onNext(@NonNull String s) {
                Log.d(TAG, s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "All items are emitted!");
            }
        };

    }

    private Observable<String> getAnimalsObservable() {
        return Observable.fromArray("Ant", "Ape",
                "Bat", "Bee", "Bear", "Butterfly",
                "Cat", "Crab", "Cod",
                "Dog", "Dove",
                "Fox", "Frog"); //or just
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        //disposable.dispose(); // don't send events once the activity is destroyed
        compositeDisposable.clear();
    }
}