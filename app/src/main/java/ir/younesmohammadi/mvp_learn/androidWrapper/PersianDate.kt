package ir.younesmohammadi.mvp_learn.androidWrapper

import java.util.Calendar
import java.util.Date
import java.util.GregorianCalendar

object PersianDate {

    private var day = 0
    private var month = 0
    private var year = 0
    private var hour = 0
    private var min = 0

    private fun setDateCalendar() {

        val date = Date()
        val ld: Int

        val calendar = GregorianCalendar()
        calendar.time = date

        hour = calendar.get(Calendar.HOUR_OF_DAY)
        min = calendar.get(Calendar.MINUTE)

        //regionConvertCalendar

        val persianYear = calendar.get(Calendar.YEAR)
        val persianMonth = calendar.get(Calendar.MONTH) + 1
        val persianDate = calendar.get(Calendar.DATE)

        val buf1 = intArrayOf(0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334)
        val buf2 = intArrayOf(0, 31, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335)

        if (persianYear % 4 != 0) {

            this.day = buf1[persianMonth - 1] + persianDate

            if (this.day > 79) {
                this.day -= 79
                if (this.day <= 186) {
                    when (this.day % 31) {
                        0 -> {
                            month = this.day / 31
                            this.day = 31
                        }

                        else -> {
                            month = this.day / 31 + 1
                            this.day %= 31
                        }
                    }
                    year = persianYear - 621
                } else {
                    this.day -= 186
                    when (this.day % 30) {
                        0 -> {
                            month = this.day / 30 + 6
                            this.day = 30
                        }

                        else -> {
                            month = this.day / 30 + 7
                            this.day %= 30
                        }
                    }
                    year = persianYear - 621
                }
            } else {
                ld = if (persianYear > 1996 && persianYear % 4 == 1) {
                    11
                } else {
                    10
                }
                this.day += ld
                when (this.day % 30) {
                    0 -> {
                        month = this.day / 30 + 9
                        this.day = 30
                    }

                    else -> {
                        month = this.day / 30 + 10
                        this.day %= 30
                    }
                }
                year = persianYear - 622
            }
        } else {
            this.day = buf2[persianMonth - 1] + persianDate
            ld = if (persianYear >= 1996) {
                79
            } else {
                80
            }
            if (this.day > ld) {
                this.day -= ld
                if (this.day <= 186) {
                    when (this.day % 31) {
                        0 -> {
                            month = this.day / 31
                            this.day = 31
                        }

                        else -> {
                            month = this.day / 31 + 1
                            this.day %= 31
                        }
                    }
                    year = persianYear - 621
                } else {
                    this.day -= 186
                    when (this.day % 30) {
                        0 -> {
                            month = this.day / 30 + 6
                            this.day = 30
                        }

                        else -> {
                            month = this.day / 30 + 7
                            this.day %= 30
                        }
                    }
                    year = persianYear - 621
                }
            } else {
                this.day += 10
                when (this.day % 30) {
                    0 -> {
                        month = this.day / 30 + 9
                        this.day = 30
                    }

                    else -> {
                        month = this.day / 30 + 10
                        this.day %= 30
                    }
                }
                year = persianYear - 622
            }
        }

    }

    fun getDate(): String {

        setDateCalendar()

        val currentDate = "${year}/${month}/${day}"
        val currentTime = "${hour}:${min}"

        return "$currentDate | $currentTime"
    }

}