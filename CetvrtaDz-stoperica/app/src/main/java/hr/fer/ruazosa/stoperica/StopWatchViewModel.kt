package hr.fer.ruazosa.stoperica

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StopWatchViewModel : ViewModel() {
    var secondsPast: MutableLiveData<Int> = MutableLiveData<Int>(0)
    private var job: Job? = null

    fun startStopWatch() {
       if(job == null || job?.isActive == false){
           job = viewModelScope.launch(Dispatchers.IO) {
               while (true) {
                   delay(1000)
                   val currentSecondsPast = secondsPast.value ?: 0
                   secondsPast.postValue(currentSecondsPast + 1)
               }
           }
       }
    }

    fun stopStopWatch() {
        job?.cancel()
    }

    fun restartStopWatch() {
        stopStopWatch()
        secondsPast.value = 0
    }

    // If there are any cleanup tasks you need to perform when the ViewModel is cleared (e.g., stopping the coroutine), you can override the onCleared method in your ViewModel.
    override fun onCleared() {
        super.onCleared()
        stopStopWatch() // Make sure to stop the coroutine and perform any necessary cleanup
    }

}