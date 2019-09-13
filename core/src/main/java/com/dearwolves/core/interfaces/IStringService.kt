package com.dearwolves.core.interfaces

interface IStringService {
    operator fun get(key: String): String
    operator fun get(key: Int): String
}