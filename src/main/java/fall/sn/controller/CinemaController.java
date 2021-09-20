package fall.sn.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fall.sn.model.Film;
import fall.sn.repository.FilmRepository;

@RestController
public class CinemaController {
@Autowired
private FilmRepository filmRepository;
	@GetMapping(path="/imageFimls/{id}",produces=MediaType.IMAGE_JPEG_VALUE)
	public byte[] images(@PathVariable (name = "id")Long id)throws Exception {
		Film film=filmRepository.findById(id).get();
		String photoName=film.getPhoto();
		File file=new File(System.getProperty("user.home")+"/cinema/images"+photoName);
		Path path=Paths.get(file.toURI());
		return Files.readAllBytes(path);
	}

}
