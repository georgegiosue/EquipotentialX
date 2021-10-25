package com.nmrc.core

import java.io.File

private const val PATH = "src/main/resources/static/curves/"

fun readAllPath(path: String = PATH): List<List<Int>> =
    run {
        File(path).list()?.map {
            it.run {
                this.substring(1, it.length).split(".")[0]
                    .split("$")
            }
        }?.map { it -> it.map { it.toInt() } } ?: emptyList()
    }
