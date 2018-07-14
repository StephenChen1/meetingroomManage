package dao;

import org.apache.ibatis.annotations.Param;

public interface TimeBookedDao {
	
	/**
	 * 插入一条新数据
	 * @param timeBookedId
	 * @param startDate
	 * @param endDate
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	boolean addTimeBooked(@Param("timeBookedId")int timeBookedId,@Param("startDate")String startDate,@Param("endDate")String endDate,
			@Param("startTime")String startTime,@Param("endTime")String endTime);
}
