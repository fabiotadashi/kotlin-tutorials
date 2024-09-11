package com.baeldung.dates.kotlinxDatetime

import kotlinx.datetime.*
import kotlinx.datetime.format.DateTimeComponents
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.char
import org.slf4j.LoggerFactory
import kotlin.time.Duration

class KotlinxDateTimeOperations {

    val logger = LoggerFactory.getLogger(KotlinxDateTimeOperations::class.java)

    fun getInstant(): Instant {
        val instant = Clock.System.now()
        logger.info(instant.toString())
        return instant
    }

    fun getLocalDateTimeBrazilTimeZoneFromInstant(instant: Instant): LocalDateTime {
        val timeZoneFromString = TimeZone.of("Brazil/East")
        val localDateTimeBrazil = instant.toLocalDateTime(timeZoneFromString)
        logger.info(localDateTimeBrazil.toString())
        return localDateTimeBrazil
    }

    fun getLocalDateTimeUtcMinus3TimeZoneFromInstant(instant: Instant): LocalDateTime {
        val timeZoneFromUTCString = TimeZone.of("UTC-3")
        val localDateUtcMinus3 = instant.toLocalDateTime(timeZoneFromUTCString)
        logger.info(localDateUtcMinus3.toString())
        return localDateUtcMinus3
    }

    fun createLocalDateTime(): LocalDateTime {
        val localDateTime = LocalDateTime(year = 2024, month = Month.JULY, dayOfMonth = 31, hour = 11, minute = 10, second = 0, nanosecond = 0)
        return localDateTime
    }

    fun getLocalDate(): LocalDate {
        val localDate = LocalDate(year = 2024, month = Month.JULY, dayOfMonth = 31)
        return localDate
    }

    fun convertInstantToLocalDate(instant: Instant): LocalDate {
        val localDate = instant.toLocalDateTime(TimeZone.currentSystemDefault()).date
        return localDate
    }

    fun getLocalTime(): LocalTime {
        val hourMinute = LocalTime(hour = 10, minute = 30)
        val localTimeSeconds = LocalTime(hour = 10, minute = 30, second = 15)
        val localTimeNanoseconds = LocalTime(hour = 10, minute = 30, second = 15, nanosecond = 50)
        return hourMinute
    }

    fun convertInstantToLocalTime(instant: Instant): LocalTime {
        val localTime = instant.toLocalDateTime(TimeZone.currentSystemDefault()).time
        return localTime
    }

    fun compareInstantAndEpochMilliseconds(): Long {
        val instant = Instant.fromEpochMilliseconds(epochMilliseconds = 1722475458286)
        val epochMilliseconds = instant.toEpochMilliseconds()
        return epochMilliseconds
    }

    fun parseStringToInstant(): Instant {
        val instant = Instant.parse("2024-07-31T22:19:44.475Z")
        return instant
    }

    fun parseStringToLocalDateTime(): LocalDateTime {
        val localDateTime = LocalDateTime.parse("2024-07-31T22:19")
        return localDateTime
    }

    fun parseStringToLocalDate(): LocalDate {
        val localDate = LocalDate.parse("2024-07-31")
        return localDate
    }

    fun parseStringToLocalTime(): LocalTime {
        val localTime = LocalTime.parse("10:19:22.111")
        return localTime
    }

    fun parseLocalDateFromCustom(): LocalDate {
        val dateFormat = LocalDate.Format {
            dayOfMonth()
            char('/')
            monthNumber()
            char('/')
            year()
        }
        val localDate = LocalDate.parse(input = "31/07/2024", format = dateFormat)
        logger.info(localDate.format(dateFormat))
        return localDate
    }

    fun getDateTimeComponents(): DateTimeComponents {
        val monthDay = DateTimeComponents.Format {
            dayOfMonth()
            char('-')
            monthName(MonthNames.ENGLISH_FULL)
        }.parse("31-July")
        logger.info(monthDay.monthNumber.toString())
        logger.info(monthDay.month.toString())
        logger.info(monthDay.dayOfMonth.toString())
        return monthDay
    }

    fun getMonthsBetween(): Int {
        val instant = Instant.parse("2024-07-31T22:00:00.000Z")
        val olderInstant = Instant.parse("2024-03-15T22:00:00.000Z")
        val monthsUntil = olderInstant.monthsUntil(instant, TimeZone.UTC)
        val months = olderInstant.until(instant, DateTimeUnit.MONTH, TimeZone.UTC)
        assert(monthsUntil.toLong() == months)
        return monthsUntil
    }

    fun addAndSubtract() {
        val instant = Clock.System.now()
        val tenDaysFromNow: Instant = instant.plus(10, DateTimeUnit.DAY, TimeZone.UTC)
        val tenDaysBeforeNow: Instant = instant.minus(10, DateTimeUnit.DAY, TimeZone.UTC)
        val localDate = LocalDate(year = 2024, month = Month.JULY, dayOfMonth = 31)
        val oneYearFromLocalDate = localDate.plus(1, DateTimeUnit.YEAR)
        val oneYearBeforeLocalDate = localDate.minus(1, DateTimeUnit.YEAR)
    }

    fun addMinutesToLocalDateTime() {
        val localDateTime = LocalDateTime(year = 2024, month = Month.JULY, dayOfMonth = 31, hour = 11, minute = 10, second = 0, nanosecond = 0)
        val timeZone = TimeZone.of("Brazil/West")
        val instant = localDateTime.toInstant(timeZone)
        val fiveMinutesAfter = instant.plus(5, DateTimeUnit.MINUTE)
        val localDateTimeFiveMinutesAfter = fiveMinutesAfter.toLocalDateTime(timeZone)
    }

}