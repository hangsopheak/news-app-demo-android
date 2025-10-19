package com.rupp.news_app_demo_android

import com.rupp.news_app_demo_android.core.util.DateFormatUtil
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Date

class DateFormatUtilTest {
    @Test
    fun `format date to readable`() {
        val date = SimpleDateFormat("yyyy-MM-dd").parse("2025-01-01")!!
        val formatted = DateFormatUtil.run { date.toReadable() }
        assertEquals("Jan 01, 2025", formatted)
    }

    @Test
    fun `relative time for just now`() {
        val now = Date()
        val result = DateFormatUtil.run { now.toRelativeTime() }
        assertEquals("Just now", result)
    }

    @Test
    fun testRelativeTime_twoHoursAgo() {
        // Arrange: Create a Date 2 hours ago
        val now = Date()
        val twoHoursAgo = Date(now.time - 2 * 60 * 60 * 1000) // 2 hours in ms

        // Act
        val result = DateFormatUtil.run { twoHoursAgo.toRelativeTime() }

        // Assert
        assertEquals("2 hours ago", result)
    }


    @Test
    fun `format duration in hours and minutes`() {
        val start = SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2025-01-01 10:00")!!
        val end = SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2025-01-01 13:45")!!
        val result = DateFormatUtil.formatDuration(start, end)
        assertEquals("3h 45m", result)
    }

    @Test
    fun `parse valid date string`() {
        val result = DateFormatUtil.parseDate("2025-01-01", DateFormatUtil.Formats.ISO_DATE)
        assertNotNull(result)
    }

    @Test
    fun `parse invalid date string returns null`() {
        val result = DateFormatUtil.parseDate("invalid-date", DateFormatUtil.Formats.ISO_DATE)
        assertNull(result)
    }
}