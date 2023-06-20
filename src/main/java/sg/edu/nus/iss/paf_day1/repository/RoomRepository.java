package sg.edu.nus.iss.paf_day1.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import sg.edu.nus.iss.paf_day1.model.Room;

public class RoomRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private String countSQL = "select count(*) from room";
    private String findAllSQL="select * from room";
    private String findByIdSQL="select * from room where id = ?";
    private String deleteByIdSQL = "delete from room where id = ?";
    private String insertSQL="insert into room (room_type,price) values (?,?)";
    private String updateSQL = "update room set price = ? where id = ?";

    public int count(){
        Integer result = 0;
        result = jdbcTemplate.queryForObject(countSQL, Integer.class);
        return result;
    }

    public List<Room> getAllRooms(){
        List<Room> roomList = new ArrayList<>();

        roomList = jdbcTemplate.query(findAllSQL, BeanPropertyRowMapper.newInstance(Room.class));
        return roomList;
    }

    public Room getRoomById(int id){
        Room room = new Room();
        room = jdbcTemplate.queryForObject(findByIdSQL,BeanPropertyRowMapper.newInstance(Room.class), id);

        return room;
    }

}
    

