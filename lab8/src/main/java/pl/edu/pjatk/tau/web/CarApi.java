package pl.edu.pjatk.tau.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import pl.edu.pjatk.tau.domain.Car;
import pl.edu.pjatk.tau.service.CarService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CarApi {

    @Autowired
    CarService carService;

    @RequestMapping("/")
    public String index() {
        return "empty bla-bla";
    }

    @RequestMapping(
            value = "/cars",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    
    @ResponseBody
    public List<Car> getAllCars(@RequestParam(value = "mark", defaultValue = "car") String name)throws SQLException {
        List<Car> itemsList = new ArrayList<Car>();
        itemsList = carService.getAllCars();
        System.out.println(itemsList);
        return itemsList;
    }
    
    @RequestMapping(
            value = "/cars/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ResponseEntity<Car> Get(@PathVariable("id") int id) throws SQLException {
        Car car = carService.selectCar(id);
        if (car == null) {
            return new ResponseEntity<Car>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Car>(car, HttpStatus.OK);
    }
    
    @RequestMapping(
            value = "/cars/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<Car> deleteNote(@PathVariable("id") int id) throws SQLException{
        Car car = carService.selectCar(id);
        if(car == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        carService.deleteCar(car);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    
    @RequestMapping(
            value = "/cars/{id}",
            method = RequestMethod.PUT
    )
    @ResponseBody
    public ResponseEntity<Car> editNote(@PathVariable("id") int id, @RequestBody Car car) throws SQLException{
        Car carToEdit = carService.selectCar(id);
        if(car == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        carToEdit.setMark(car.getMark());
        carToEdit.setPrice(car.getPrice());
        carToEdit.setDescription(car.getDescription());
        carService.editCar(carToEdit);
        return new ResponseEntity<>(HttpStatus.OK);

    }
    
    @SuppressWarnings("unused")
	@RequestMapping(
            value = "cars",
            method = RequestMethod.POST,
            produces = "application/json"

    )
    @ResponseBody
    public ResponseEntity<Void> addItem(@RequestBody Car item,UriComponentsBuilder ucBuilder) throws SQLException{
    	carService.addCar(item);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
