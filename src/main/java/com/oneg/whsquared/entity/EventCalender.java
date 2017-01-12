package com.oneg.whsquared.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "event_calender")
@XmlRootElement
public class EventCalender extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "allDayEvent")
	private boolean allDayEvent;

	@Basic(optional = false)
	@Column(name = "start_date")
	@Temporal(TemporalType.DATE)
	private Date startDate;

	@Basic(optional = false)
	@Column(name = "end_date")
	@Temporal(TemporalType.DATE)
	private Date endDate;

	@Basic(optional = false)
	@Column(name = "start_time")
	@Temporal(TemporalType.TIME)
	private Date startTime;

	@Basic(optional = false)
	@Column(name = "end_time")
	@Temporal(TemporalType.TIME)
	private Date endTime;

	@Column(name = "recurrenceRule")
	private String recurrenceRule;

	@Column(name = "recurrenceRulesEnd")
	private String recurrenceRulesEnd;

	@Column(name = "isMonday")
	private boolean isMonday;

	@Column(name = "isTuesday")
	private boolean isTuesday;

	@Column(name = "isWednesday")
	private boolean isWednesday;

	@Column(name = "isThursday")
	private boolean isThursday;

	@Column(name = "isFriday")
	private boolean isFriday;

	@Column(name = "isSaturday")
	private boolean isSaturday;

	@Column(name = "isSunday")
	private boolean isSunday;

	@Column(name = "weeks")
	private int weeks;

	@Column(name = "months")
	private int months;

	@Column(name = "monthlyDate")
	private int monthlyDate;

	@Column(name = "recurrenceEndDate")
	@Temporal(TemporalType.DATE)
	private Date recurrenceEndDate;

	@Column(name = "isExMonday")
	private boolean isExMonday;

	@Column(name = "isExTuesday")
	private boolean isExTuesday;

	@Column(name = "isExWednesday")
	private boolean isExWednesday;

	@Column(name = "isExThursday")
	private boolean isExThursday;

	@Column(name = "isExFriday")
	private boolean isExFriday;

	@Column(name = "isExSaturday")
	private boolean isExSaturday;

	@Column(name = "isExSunday")
	private boolean isExSunday;

	@Column(name = "exWeeks")
	private int exWeeks;

	@Column(name = "exMonths")
	private int exMonths;

	@Column(name = "exMonthlyDate")
	private int exMonthlyDate;

	@Column(name = "exclusion")
	private String exclusion;

	@Column(name = "exclusionDate")
	@Temporal(TemporalType.DATE)
	private Date exclusionDate;

	@Column(name = "description")
	private String description;

	@JsonIgnore
	@Basic(optional = false)
	@JoinColumn(name = "eventId", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Event eventId;

	@Basic(optional = false)
	@JoinColumn(name = "timeZone", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private TimeZone timeZone;

	public EventCalender() {
	}

	/**
	 * @return the allDayEvent
	 */
	public boolean isAllDayEvent() {
		return allDayEvent;
	}

	/**
	 * @return the isExMonday
	 */
	public boolean getIsExMonday() {
		return isExMonday;
	}

	/**
	 * @param isExMonday
	 *            the isExMonday to set
	 */
	public void setIsExMonday(boolean isExMonday) {
		this.isExMonday = isExMonday;
	}

	/**
	 * @return the isExTuesday
	 */
	public boolean getIsExTuesday() {
		return isExTuesday;
	}

	/**
	 * @param isExTuesday
	 *            the isExTuesday to set
	 */
	public void setIsExTuesday(boolean isExTuesday) {
		this.isExTuesday = isExTuesday;
	}

	/**
	 * @return the isExWednesday
	 */
	public boolean getIsExWednesday() {
		return isExWednesday;
	}

	/**
	 * @param isExWednesday
	 *            the isExWednesday to set
	 */
	public void setIsExWednesday(boolean isExWednesday) {
		this.isExWednesday = isExWednesday;
	}

	/**
	 * @return the isExThursday
	 */
	public boolean getIsExThursday() {
		return isExThursday;
	}

	/**
	 * @param isExThursday
	 *            the isExThursday to set
	 */
	public void setIsExThursday(boolean isExThursday) {
		this.isExThursday = isExThursday;
	}

	/**
	 * @return the isExFriday
	 */
	public boolean getIsExFriday() {
		return isExFriday;
	}

	/**
	 * @param isExFriday
	 *            the isExFriday to set
	 */
	public void setIsExFriday(boolean isExFriday) {
		this.isExFriday = isExFriday;
	}

	/**
	 * @return the isExSaturday
	 */
	public boolean getIsExSaturday() {
		return isExSaturday;
	}

	/**
	 * @param isExSaturday
	 *            the isExSaturday to set
	 */
	public void setIsExSaturday(boolean isExSaturday) {
		this.isExSaturday = isExSaturday;
	}

	/**
	 * @return the isExSunday
	 */
	public boolean getIsExSunday() {
		return isExSunday;
	}

	/**
	 * @param isExSunday
	 *            the isExSunday to set
	 */
	public void setIsExSunday(boolean isExSunday) {
		this.isExSunday = isExSunday;
	}

	/**
	 * @return the exWeeks
	 */
	public int getExWeeks() {
		return exWeeks;
	}

	/**
	 * @param exWeeks
	 *            the exWeeks to set
	 */
	public void setExWeeks(int exWeeks) {
		this.exWeeks = exWeeks;
	}

	/**
	 * @return the exMonths
	 */
	public int getExMonths() {
		return exMonths;
	}

	/**
	 * @param exMonths
	 *            the exMonths to set
	 */
	public void setExMonths(int exMonths) {
		this.exMonths = exMonths;
	}

	/**
	 * @return the exMonthlyDate
	 */
	public int getExMonthlyDate() {
		return exMonthlyDate;
	}

	/**
	 * @param exMonthlyDate
	 *            the exMonthlyDate to set
	 */
	public void setExMonthlyDate(int exMonthlyDate) {
		this.exMonthlyDate = exMonthlyDate;
	}

	/**
	 * @param allDayEvent
	 *            the allDayEvent to set
	 */
	public void setAllDayEvent(boolean allDayEvent) {
		this.allDayEvent = allDayEvent;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		if (startTime == null) {

		}
		String[] time = startTime.split(":");
		this.startTime = new Date(2016, 0, 1, Integer.parseInt(time[0]), Integer.parseInt(time[1]));
	}

	/**
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(String endTime) {
		if (endTime == null) {

		}
		String[] time = endTime.split(":");
		String type = "";
		if (time[1] != null) {
			String[] types = time[1].split(" ");
			time[1] = types[0];
			type = types[0];
			if (type != null && type.equalsIgnoreCase("PM")) {
				time[1] = (Integer.parseInt(time[1]) + 12) + "";
			}
		}
		this.endTime = new Date(2016, 0, 1, Integer.parseInt(time[0]), Integer.parseInt(time[1]));
	}

	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the timeZone
	 */
	public TimeZone getTimeZone() {
		return timeZone;
	}

	/**
	 * @param timeZone
	 *            the timeZone to set
	 */
	public void setTimeZone(TimeZone timeZone) {
		this.timeZone = timeZone;
	}

	/**
	 * @return the recurrenceRule
	 */
	public String getRecurrenceRule() {
		return recurrenceRule;
	}

	/**
	 * @param recurrenceRule
	 *            the recurrenceRule to set
	 */
	public void setRecurrenceRule(String recurrenceRule) {
		this.recurrenceRule = recurrenceRule;
	}

	/**
	 * @return the recurrenceRulesEnd
	 */
	public String getRecurrenceRulesEnd() {
		return recurrenceRulesEnd;
	}

	/**
	 * @param recurrenceRulesEnd
	 *            the recurrenceRulesEnd to set
	 */
	public void setRecurrenceRulesEnd(String recurrenceRulesEnd) {
		this.recurrenceRulesEnd = recurrenceRulesEnd;
	}

	/**
	 * @return the recurrenceEndDate
	 */
	public Date getRecurrenceEndDate() {
		return recurrenceEndDate;
	}

	/**
	 * @param recurrenceEndDate
	 *            the recurrenceEndDate to set
	 */
	public void setRecurrenceEndDate(Date recurrenceEndDate) {
		this.recurrenceEndDate = recurrenceEndDate;
	}

	/**
	 * @return the exclusion
	 */
	public String getExclusion() {
		return exclusion;
	}

	/**
	 * @param exclusion
	 *            the exclusion to set
	 */
	public void setExclusion(String exclusion) {
		this.exclusion = exclusion;
	}

	/**
	 * @return the exclusionDate
	 */
	public Date getExclusionDate() {
		return exclusionDate;
	}

	/**
	 * @param exclusionDate
	 *            the exclusionDate to set
	 */
	public void setExclusionDate(Date exclusionDate) {
		this.exclusionDate = exclusionDate;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the eventId
	 */
	public Event getEventId() {
		return eventId;
	}

	/**
	 * @param eventId
	 *            the eventId to set
	 */
	public void setEventId(Event eventId) {
		this.eventId = eventId;
	}

	/**
	 * @return the isMonday
	 */
	public boolean getIsMonday() {
		return isMonday;
	}

	/**
	 * @param isMonday
	 *            the isMonday to set
	 */
	public void setIsMonday(boolean isMonday) {
		this.isMonday = isMonday;
	}

	/**
	 * @return the isTuesday
	 */
	public boolean getIsTuesday() {
		return isTuesday;
	}

	/**
	 * @param isTuesday
	 *            the isTuesday to set
	 */
	public void setIsTuesday(boolean isTuesday) {
		this.isTuesday = isTuesday;
	}

	/**
	 * @return the isWednesday
	 */
	public boolean getIsWednesday() {
		return isWednesday;
	}

	/**
	 * @param isWednesday
	 *            the isWednesday to set
	 */
	public void setIsWednesday(boolean isWednesday) {
		this.isWednesday = isWednesday;
	}

	/**
	 * @return the isThursday
	 */
	public boolean getIsThursday() {
		return isThursday;
	}

	/**
	 * @param isThursday
	 *            the isThursday to set
	 */
	public void setIsThursday(boolean isThursday) {
		this.isThursday = isThursday;
	}

	/**
	 * @return the isFriday
	 */
	public boolean getIsFriday() {
		return isFriday;
	}

	/**
	 * @param isFriday
	 *            the isFriday to set
	 */
	public void setIsFriday(boolean isFriday) {
		this.isFriday = isFriday;
	}

	/**
	 * @return the isSaturday
	 */
	public boolean getIsSaturday() {
		return isSaturday;
	}

	/**
	 * @param isSaturday
	 *            the isSaturday to set
	 */
	public void setIsSaturday(boolean isSaturday) {
		this.isSaturday = isSaturday;
	}

	/**
	 * @return the isSunday
	 */
	public boolean getIsSunday() {
		return isSunday;
	}

	/**
	 * @param isSunday
	 *            the isSunday to set
	 */
	public void setIsSunday(boolean isSunday) {
		this.isSunday = isSunday;
	}

	/**
	 * @return the weeks
	 */
	public int getWeeks() {
		return weeks;
	}

	/**
	 * @param weeks
	 *            the weeks to set
	 */
	public void setWeeks(int weeks) {
		this.weeks = weeks;
	}

	/**
	 * @return the months
	 */
	public int getMonths() {
		return months;
	}

	/**
	 * @param months
	 *            the months to set
	 */
	public void setMonths(int months) {
		this.months = months;
	}

	/**
	 * @return the monthlyDate
	 */
	public int getMonthlyDate() {
		return monthlyDate;
	}

	/**
	 * @param monthlyDate
	 *            the monthlyDate to set
	 */
	public void setMonthlyDate(int monthlyDate) {
		this.monthlyDate = monthlyDate;
	}

}
