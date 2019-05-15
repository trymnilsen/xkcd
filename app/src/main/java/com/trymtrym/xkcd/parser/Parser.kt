package com.trymtrym.xkcd.parser

interface Parser<T, U> {
    fun parse(data: T): U
}
