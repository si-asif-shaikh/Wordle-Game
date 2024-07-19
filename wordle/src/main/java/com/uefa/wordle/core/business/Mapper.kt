package com.uefa.wordle.core.business

// Interface mapper
// since this interface will only have 1 abstract function
// we can use fun interface
fun interface Mapper<in From,out To>{
    fun map(from: From): To
}

// First map from List is called
// then map from Mapper is called

fun <F, T> Mapper<F, T>.mapAll(list: List<F>) = list.map { map(it) }
// You could add more collection mapping operations if needed
