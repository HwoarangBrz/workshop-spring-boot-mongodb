package com.hwoarang.workshopmongo.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hwoarang.workshopmongo.domain.Post;
import com.hwoarang.workshopmongo.resources.util.URL;
import com.hwoarang.workshopmongo.services.PostService;

@RestController
@RequestMapping(value="/posts")
public class PostResource {
	
	@Autowired
	private PostService service;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Post> findById(@PathVariable String id) {
		return ResponseEntity.ok().body(service.findById(id));
	}
	
	@RequestMapping(value="/titlesearch", method=RequestMethod.GET)
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue="") String text) {
		return ResponseEntity.ok().body(service.findByTitle(URL.decodeParam(text)));
	}

	@RequestMapping(value="/fullsearch", method=RequestMethod.GET)
	public ResponseEntity<List<Post>> fullSearch(
			@RequestParam(value="text", defaultValue="") String text,
			@RequestParam(value="minDate", defaultValue="") String minDate,
			@RequestParam(value="maxDate ", defaultValue="") String maxDate) {
		return ResponseEntity.ok().body(service.fullSearch(
				URL.decodeParam(text), 
				URL.convertDate(minDate, new Date(0L)), 
				URL.convertDate(maxDate, new Date())));
	}
}
