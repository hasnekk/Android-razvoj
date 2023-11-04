package hr.fer.ruazosa.prvadz

import android.util.Log

class MostOccurancesClass {
    val arr: Array<Any>

    constructor(arr: Array<Any>) {
        this.arr = arr
    }

    fun mostOccured(): Any{
       var elementsInArray: MutableMap<Any, Int> = mutableMapOf()

        for (el in arr){
            var hashValue: Int = elementsInArray.getOrDefault(el, 0) // Get the number of occurrences or assign value to 0.
            hashValue++ // Increment occurrences.
            elementsInArray[el] = hashValue
        }

        val mostOccuredEntry: Map.Entry<Any, Int> = elementsInArray.maxWith(Comparator({a, b -> a.value.compareTo(b.value)}))
        return mostOccuredEntry.key
    }

}