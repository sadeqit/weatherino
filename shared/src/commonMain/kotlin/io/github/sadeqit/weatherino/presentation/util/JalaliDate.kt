package io.github.sadeqit.weatherino.presentation.util

private val jalaliMonths = arrayOf(
    "فروردین",
    "اردیبهشت",
    "خرداد",
    "تیر",
    "مرداد",
    "شهریور",
    "مهر",
    "آبان",
    "آذر",
    "دی",
    "بهمن",
    "اسفند"
)

private val weekDays = arrayOf(
    "شنبه",
    "یکشنبه",
    "دوشنبه",
    "سه‌شنبه",
    "چهارشنبه",
    "پنجشنبه",
    "جمعه"
)

fun String.toPersianDate(): String {
    val parts = substringBefore(" ").split("-")

    if (parts.size != 3) return this

    val year = parts[0].toIntOrNull() ?: return this
    val month = parts[1].toIntOrNull() ?: return this
    val day = parts[2].toIntOrNull() ?: return this

    val (jalaliYear, jalaliMonth, jalaliDay) =
        gregorianToJalali(year, month, day)

    val dayOfWeek = gregorianDayOfWeek(year, month, day)

    return "${weekDays[dayOfWeek]}، $jalaliDay ${jalaliMonths[jalaliMonth - 1]}"
}

private fun gregorianDayOfWeek(
    year: Int,
    month: Int,
    day: Int
): Int {
    val adjustedYear = if (month < 3) year - 1 else year
    val adjustedMonth = if (month < 3) month + 12 else month

    val k = adjustedYear % 100
    val j = adjustedYear / 100

    val h = (
            day +
                    (13 * (adjustedMonth + 1)) / 5 +
                    k +
                    k / 4 +
                    j / 4 +
                    5 * j
            ) % 7

    return when (h) {
        0 -> 5 // پنجشنبه
        1 -> 6 // جمعه
        2 -> 0 // شنبه
        3 -> 1 // یکشنبه
        4 -> 2 // دوشنبه
        5 -> 3 // سه‌شنبه
        else -> 4 // چهارشنبه
    }
}

private fun gregorianToJalali(
    gy: Int,
    gm: Int,
    gd: Int
): Triple<Int, Int, Int> {

    val gDaysInMonth = intArrayOf(
        31, 28, 31, 30, 31, 30,
        31, 31, 30, 31, 30, 31
    )

    var gy2 = gy

    if (gm > 2) {
        gy2++
    }

    var days =
        355666 +
                (365 * gy) +
                ((gy2 + 3) / 4) -
                ((gy2 + 99) / 100) +
                ((gy2 + 399) / 400)

    for (i in 0 until gm - 1) {
        days += gDaysInMonth[i]
    }

    days += gd

    var jy = -1595 + (33 * (days / 12053))
    var dayOfCycle = days % 12053

    jy += 4 * (dayOfCycle / 1461)
    dayOfCycle %= 1461

    if (dayOfCycle > 365) {
        jy += (dayOfCycle - 1) / 365
        dayOfCycle = (dayOfCycle - 1) % 365
    }

    val jm: Int
    val jd: Int

    if (dayOfCycle < 186) {
        jm = 1 + (dayOfCycle / 31)
        jd = 1 + (dayOfCycle % 31)
    } else {
        jm = 7 + ((dayOfCycle - 186) / 30)
        jd = 1 + ((dayOfCycle - 186) % 30)
    }

    return Triple(jy, jm, jd)
}