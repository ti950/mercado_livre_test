package com.br.mltest.utils

import java.text.DecimalFormat

object Utils {

    fun currencyFormat(price: String): String {
        val formatter = DecimalFormat("###,###,##0.00")
        return "R$ " + formatter.format(price.toDouble())
    }

    fun toPortuguese(value: String): String {
        if (value == "new") return "novo"
        return value
    }

}