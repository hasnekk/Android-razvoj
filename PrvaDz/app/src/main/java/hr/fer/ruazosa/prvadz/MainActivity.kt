package hr.fer.ruazosa.prvadz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var arr: Array<Any> = arrayOf("kristijan", 1, 1, "kristijan", "kristijan", true)
        val someObject: MostOccurancesClass = MostOccurancesClass(arr)
        val mostOccured: Any = someObject.mostOccured()
        println("Element: \"$mostOccured\" has occured the most in arr.")

    }
}