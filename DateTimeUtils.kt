import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtils {

    const val DATE_FORMAT_DISPLAY = "MM/dd/yyyy hh:mm aa" // 02/30/2021 05:21 PM
    const val DATE_FORMAT_FILE_STORE = "MM-dd-yyyy hh:mm:ss aa"
    const val DATE_FORMAT_DISPLAY1 = "EEEE MMMM, dd, yyyy hh:mm aa" // Tuesday March, 30, 2021 05:30 PM
    const val DATE_FORMAT_INPUT = "yyyy-MM-dd HH:mm:ss.SSS"//2021-03-19 15:02:53.328699
    const val DATE_FORMAT_UTC = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'" //2021-03-18T17:10:44.763Z

    fun getCurrentDate(outFormat: String): String?
    {
        val c = Calendar.getInstance()
        val df = SimpleDateFormat(outFormat, Locale.ENGLISH)
        return df.format(c.time)
    }
    fun getFormattedDate(cal: Calendar, outFormat: String): String?
    {
        val df = SimpleDateFormat(outFormat, Locale.ENGLISH)
        return df.format(cal.time)
    }
    fun getCurrentDateAmericaChicago(outFormat: String): String?
    {
        val c = Calendar.getInstance(TimeZone.getTimeZone("America/Chicago"))
        val df = SimpleDateFormat(outFormat, Locale.ENGLISH)
        return df.format(c.time)
    }
    fun getLocalDateFromUTC(strDateTime: String, inputFormat: String, outFormat: String): String?
    {
        var resultDateTime =""
        resultDateTime = try
        {
            val formatter = SimpleDateFormat(inputFormat, Locale.ENGLISH)
            formatter.timeZone = TimeZone.getTimeZone("UTC")
            val value: Date? = formatter.parse(strDateTime)

            val dateFormatter = SimpleDateFormat(outFormat, Locale.ENGLISH) //this format changeable
            dateFormatter.timeZone = TimeZone.getDefault()
            dateFormatter.format(value!!)
        }
        catch (e: Exception)
        {
            "00-00-0000 00:00"
        }
        return resultDateTime
    }
    fun parseDate(strDateTime: String, inputFormat: String, outFormat: String): String
    {
        var resultDateTime =""
        resultDateTime = try
        {
            val formatter = SimpleDateFormat(inputFormat, Locale.ENGLISH)
            val value: Date? = formatter.parse(strDateTime)

            val dateFormatter = SimpleDateFormat(outFormat, Locale.ENGLISH) //this format changeable
            dateFormatter.timeZone = TimeZone.getDefault()
            dateFormatter.format(value!!)
        }
        catch (e: Exception)
        {
            "00-00-0000 00:00"
        }
        return resultDateTime
    }
    fun convert12To24Hour(strTime: String?): String? {
        val f1: DateFormat = SimpleDateFormat("hh:mm a",Locale.ENGLISH)
        var date: Date? = null
        try
        {
            date = f1.parse(strTime)
        }
        catch (e: ParseException)
        {
            e.printStackTrace()
        }
        val f2: DateFormat = SimpleDateFormat("HH:mm",Locale.ENGLISH)
        return f2.format(date)
    }
    fun convert24To12Hour(strTime: String?): String? {
        val f1: DateFormat = SimpleDateFormat("HH:mm",Locale.ENGLISH)
        var date: Date? = null
        try
        {
            date = f1.parse(strTime)
        }
        catch (e: ParseException)
        {
            e.printStackTrace()
        }
        val f2: DateFormat = SimpleDateFormat("hh:mm a",Locale.ENGLISH)
        return f2.format(date)
    }
}
