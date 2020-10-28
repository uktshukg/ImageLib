package com.dexter.dunzo.ui.main

import android.util.Log
import kotlin.collections.ArrayList
import kotlin.math.min

object DiffFinder {
    fun compare(existing: ArrayList<LocalPhoto>, new: ArrayList<LocalPhoto>): DiffInfo {

        var i = 0
        var index = minOf(existing.size - 1, new.size - 1)
        val minList = ArrayList<LocalPhoto>(new)
        while (index >= 0) {
            if (existing[i] == (new[i])) {
                minList.removeAt(i)
                index--
                i++
            } else {
                break
            }
        }

        Log.e("utkarsh","inside min "+index +" minList "+minList.size+"  existing "+existing.size)
        return if (minList.isEmpty() && existing.isEmpty()) {
            DiffInfo(range = Range.Same)
        } else if (minList.isEmpty() && existing.isNotEmpty()) {
            DiffInfo(range = Range.Removed, index = index+1, itemCount = existing.size-i)
        } else if (minList.isNotEmpty() && existing.isEmpty()) {
            DiffInfo(range = Range.Added, index = index+1, itemCount = new.size-i)
        } else {
            DiffInfo(range = Range.Complex)
        }

    }

    data class DiffInfo(val range: Range, val index: Int = 0, val itemCount: Int =0)


    sealed class Range {
        object Same : Range()
        object Removed : Range()
        object Added : Range()
        object Complex : Range()
    }

}
