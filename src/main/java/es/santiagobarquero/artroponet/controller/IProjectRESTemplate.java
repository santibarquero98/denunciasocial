package es.santiagobarquero.artroponet.controller;
/**
 * ProjectRESTemplate interface defines the basic REST services for the app controller
 * 
 * @author santi
 *
 */
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface IProjectRESTemplate<DVO> {

	ResponseEntity<List<DVO>> getAlls();
	
	HttpStatus add(@RequestBody DVO reqDvo);
	
	HttpStatus remove(@RequestBody DVO reqDvo);
	
	ResponseEntity<DVO> update(@RequestBody DVO reqDvo);
}
