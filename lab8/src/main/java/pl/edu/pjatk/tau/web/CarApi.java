package pl.edu.pjatk.tau.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pl.edu.pjatk.tau.domain.Car;
import pl.edu.pjatk.tau.service.CarService;

import javax.print.attribute.standard.Media;
import javax.servlet.annotation.WebServlet;

import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class CarApi {
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    CarService carService;


    @RequestMapping("/")
    public String index() {
        return "empty bla-bla";
    }

    @RequestMapping(
            value = "/person",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public Car getPerson(@RequestParam(value="mark", defaultValue="bla-bla") String mark) {
        Car p = new Car();
        p.setId(counter.incrementAndGet());
        p.setMark(mark);
        p.setPrice(12000);
        p.setDescription("No Desription");
        return p;
    }
    
    @RequestMapping(
            value = "/car/{id}",
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

}
