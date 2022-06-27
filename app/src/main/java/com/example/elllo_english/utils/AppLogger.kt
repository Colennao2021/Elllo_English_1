package com.example.elllo_english.utils

import java.text.SimpleDateFormat
import java.util.*


object AppLogger {
    enum class Level {
        DEBUG, INFO, WARNING, ERROR
    }

    private interface Config {
        interface Default {
            companion object {
                const val output = "%s/out/log"
            }
        }

        interface Format {
            companion object {
                /**
                 * 1. Log level <br></br>0
                 * 2. Time <br></br>
                 * 3. Package File<br></br>
                 * 4. Function <br></br>
                 * 5. Line of code <br></br>
                 * 6. Message
                 */
                const val messenger = "[%5s] [%s] [%s_%s(%s)] : %s"
                const val date = "yyyy-MM-dd HH:mm:ss"
            }
        }
    }

    class Setting {
        var isLog: Boolean
        var isLogFile: Boolean
        var output: String
        protected var level: Level?

        constructor(isLog: Boolean, isLogFile: Boolean, level: String, output: String) {
            this.isLog = isLog
            this.isLogFile = isLogFile
            this.level = Level.valueOf(level.toUpperCase(Locale.ROOT))
            if (this.level == null) this.level = Level.DEBUG
            this.output = output
        }

        constructor(isLog: Boolean, isLogFile: Boolean, level: Level?, output: String) {
            this.isLog = isLog
            this.isLogFile = isLogFile
            this.level = level
            this.output = output
        }
    }

    private var instance: AppLogger? = null

    fun getInstance(): AppLogger? {
        if (instance == null) instance = AppLogger
        return instance
    }

    private var setting: Setting? = null
    private val level: Level? = null
    private var directory: String? = null

    private fun AppLogger() {
        directory = System.getProperty("user.dir")
        setting = Setting(
            true,
            false,
            Level.DEBUG, String.format(Config.Default.output, directory)
        )
    }

    fun setting(setting: Setting?) {
        this.setting = setting
    }

    fun info(msg: String) {
        print(Level.INFO, msg)
    }

    fun debug(msg: String) {
        print(Level.DEBUG, msg)
    }

    fun warning(msg: String) {
        print(Level.WARNING, msg)
    }

    fun error(msg: String) {
        print(Level.ERROR, msg)
    }

    private fun print(level: Level, msg: String) {
        val now = System.currentTimeMillis()
        val time = millisecondToDate(now, Config.Format.date)
        val element = Throwable().stackTrace[2]
        val file = element.className
        val method = element.methodName
        val line = element.lineNumber
        val string =
            String.format(Config.Format.messenger, level, time, file, method, line, msg)
        println(string)
    }

    private fun millisecondToDate(time: Long, format: String): String {
        val now = Date(time)
        val sdf = SimpleDateFormat(format)
        return sdf.format(now)
    }
}