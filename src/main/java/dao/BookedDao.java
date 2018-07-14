package dao;

import org.apache.ibatis.annotations.Param;

import entity.Booked;

public interface BookedDao {
	/**
	 * 插入booked表的记录，并返回time_booked_id主键，插入时stauts默认为1。插入后，timeBookedId注入到Booked对象中。
	 * 
	 * @param staffNumber
	 * @param roomNumber
	 * @return
	 */
	int addBooked(Booked booked);
}
