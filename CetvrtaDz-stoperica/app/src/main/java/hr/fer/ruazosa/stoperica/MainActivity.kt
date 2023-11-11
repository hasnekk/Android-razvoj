package hr.fer.ruazosa.stoperica

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import hr.fer.ruazosa.stoperica.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding
    private val binding: ActivityMainBinding
        get() {
            return _binding
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        val stopWatchViewModel: StopWatchViewModel =
            ViewModelProvider(this)[StopWatchViewModel::class.java]

        stopWatchViewModel.secondsPast.observe(this) {
            binding.displaySec.text = getString(R.string.seconds_past_placeholder, it.toString())
        }

        binding.btnStart.setOnClickListener {
            stopWatchViewModel.startStopWatch()
        }

        binding.btnStop.setOnClickListener {
            stopWatchViewModel.stopStopWatch()
        }

        binding.btnReStart.setOnClickListener {
            stopWatchViewModel.restartStopWatch()
        }

    }
}