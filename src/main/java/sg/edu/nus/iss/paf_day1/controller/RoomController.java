package sg.edu.nus.iss.paf_day1.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.paf_day1.exception.ResourceNotFoundException;
import sg.edu.nus.iss.paf_day1.model.Room;
import sg.edu.nus.iss.paf_day1.service.RoomService;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    
    @Autowired
    RoomService rmSvc;

    @GetMapping("/count")
    public ResponseEntity<Integer> getRoomCount(){
        Integer countResult = rmSvc.count();
        // return ResponseEntity.ok().body(countResult); can use either this or below
        return new ResponseEntity<Integer>(countResult, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms(){
        List<Room> rooms = new ArrayList<>();
        rooms = rmSvc.findAll();

        if (rooms.isEmpty()){
            throw new ResourceNotFoundException("No Room Record Exist");
            
        }else{
            return ResponseEntity.ok().body(rooms);
        }
    }

    //http://localhost:8080/api/rooms/{room-id}
    @GetMapping("/{room-id}")
    public ResponseEntity<Room> getRoomById(@PathVariable("room-id") int roomId){
        Room room = rmSvc.findById(roomId);

        if (room==null){
            throw new ResourceNotFoundException("Room not found");
        }else{
            return ResponseEntity.ok().body(room);
        }
    }

    @PostMapping
    public ResponseEntity<Boolean> createRoom(@RequestBody Room room){
        Boolean created = rmSvc.save(room);

        if (created){
            return ResponseEntity.internalServerError().build();
        }else{
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("{room-id}")
    public ResponseEntity<Integer> deleteRoomById ( @PathVariable("room-id") int roomId){
        int deleted = rmSvc.deleteById(roomId);
        if (deleted==1){
            return ResponseEntity.ok().build();
            }else{
                return new ResponseEntity<Integer>(deleted,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Integer> updatedRoom(@RequestBody Room room){
        int updated = rmSvc.update(room);
        if (updated==1){
            return ResponseEntity.ok().body(updated);
        }else{
            return ResponseEntity.internalServerError().build();
        }
    }

}
