package pl.edu.pjatk.tau.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import pl.edu.pjatk.tau.domain.Computer;
import pl.edu.pjatk.tau.service.ComputerService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ComputerApi {

    @Autowired
    ComputerService computerService;

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
    public List<Computer> getAllComputers(@RequestParam(value = "mark", defaultValue = "computer") String name)throws SQLException {
        List<Computer> itemsList = new ArrayList<Computer>();
        itemsList = computerService.getAllComputers();
        System.out.println(itemsList);
        return itemsList;
    }
    
    @RequestMapping(
            value = "/cars/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ResponseEntity<Computer> Get(@PathVariable("id") int id) throws SQLException {
        Computer computer = computerService.selectComputer(id);
        if (computer == null) {
            return new ResponseEntity<Computer>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Computer>(computer, HttpStatus.OK);
    }
    
    @RequestMapping(
            value = "/cars/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<Computer> deleteNote(@PathVariable("id") int id) throws SQLException{
        Computer computer = computerService.selectComputer(id);
        if(computer == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        computerService.deleteComputer(computer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    
    @RequestMapping(
            value = "/cars/{id}",
            method = RequestMethod.PUT
    )
    @ResponseBody
    public ResponseEntity<Computer> editNote(@PathVariable("id") int id, @RequestBody Computer computer) throws SQLException{
        Computer computerToEdit = computerService.selectComputer(id);
        if(computer == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        computerToEdit.setMark(computer.getMark());
        computerToEdit.setPrice(computer.getPrice());
        computerToEdit.setDescription(computer.getDescription());
        computerService.editComputer(computerToEdit);
        return new ResponseEntity<>(HttpStatus.OK);

    }
    
    @SuppressWarnings("unused")
	@RequestMapping(
            value = "cars",
            method = RequestMethod.POST,
            produces = "application/json"

    )
    @ResponseBody
    public ResponseEntity<Void> addItem(@RequestBody Computer item,UriComponentsBuilder ucBuilder) throws SQLException{
    	computerService.addComputer(item);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
