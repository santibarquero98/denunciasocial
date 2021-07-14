package es.santiagobarquero.artroponet.controller;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import es.santiagobarquero.artroponet.auxiliary.Utilities;
import es.santiagobarquero.artroponet.auxiliary.exceptions.NotImplementedException;
import es.santiagobarquero.artroponet.resources.dvo.GalleryDvo;
import es.santiagobarquero.artroponet.resources.dvo.PictureDvo;
import es.santiagobarquero.artroponet.service.IAuditService;
import es.santiagobarquero.artroponet.service.IGalleryService;
import es.santiagobarquero.artroponet.service.IPictureService;
import es.santiagobarquero.artroponet.service.IUserService;

@RestController
@RequestMapping("/rest/gallery")
public class GalleryRestController implements IProjectRESTemplate<GalleryDvo> {

	@Autowired
	private IGalleryService galleryService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IAuditService auditService;
	
	@Autowired
	private IPictureService pictureService;
	
	@GetMapping("/getbyuser/{id}")
	public EntityResponse<GalleryDvo> getGalleryByUser(@RequestParam(required = true) String pkUser) {
		Long pkUserLong = Long.valueOf(pkUser);
		userService.findIt(pkUserLong);
		return null;
	}
	
	@GetMapping("/")
	public HttpStatus throwResponse(@RequestHeader HttpHeaders reqHeader) {
		InetSocketAddress inetSocket = reqHeader.getHost();
		String hostname = inetSocket.getHostName();
		if(!Utilities.isNullOrBlank(hostname)) {
			try {
				auditService.auditGetRequest(hostname, "/rest/gallery/", null, HttpStatus.OK, null, true);
			} catch (NotImplementedException e) {
				return HttpStatus.INTERNAL_SERVER_ERROR;
			}
		}
		
		File file = new File("C:\\Users\\santi\\Desktop\\FORMAL1.jpg");
		try {
			byte[] image = FileUtils.readFileToByteArray(file);
			PictureDvo pictureDvo = new PictureDvo();
			Blob blob = new SerialBlob(image);
			pictureDvo.setImage(blob);
			pictureService.create(pictureDvo);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SerialException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return HttpStatus.OK;
	}
	
	@Override
	public HttpStatus add(GalleryDvo arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<List<GalleryDvo>> getAlls() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HttpStatus remove(GalleryDvo arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<GalleryDvo> update(GalleryDvo arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
