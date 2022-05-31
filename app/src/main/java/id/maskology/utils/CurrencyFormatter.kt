package id.maskology.utils

import java.text.NumberFormat
import java.util.*

object CurrencyFormatter {
    fun rupiahFormatter(number: Int) : String{
        val locale = Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(locale)
        return numberFormat.format(number).toString()
    }
}