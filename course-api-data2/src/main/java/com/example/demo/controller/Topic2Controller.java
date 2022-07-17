package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entity.Topic;

@RestController
public class Topic2Controller {

	RestTemplate restTemplate = new RestTemplate();
	HttpHeaders headers = new HttpHeaders();

	@GetMapping(path="/topicsfortopic2")
	public @ResponseBody List<Topic> getTopic() {

		List<Topic> topicList = restTemplate.getForObject("http://localhost:9000/topics", List.class);
		return topicList;
	}

	@GetMapping("/topic2/{id}")
	public Topic getTopic(@PathVariable long id) {
		Topic topic = restTemplate.getForObject("http://localhost:9000/topics/" + id, Topic.class);
		return topic;
	}

	@PostMapping("/topic2add")
	public void addTopic(@RequestBody Topic topic) {

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Topic> entity = new HttpEntity<>(topic, headers);
		restTemplate.postForObject("http://localhost:9000/topicsadd", entity, Topic.class);
	}

	@PutMapping("/topic2update")
	public void upadateTopic(@RequestBody Topic topic) {
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Topic> entity = new HttpEntity<Topic>(topic, headers);
		restTemplate.put("http://localhost:9000/topicsupdate", entity, Topic.class);

	}

	/*
	 * @PatchMapping("/topic2patchupdate/{id}") public void
	 * updatePatchTopic(@RequestBody Topic topic, @PathVariable long id) {
	 * 
	 * headers.setContentType(MediaType.APPLICATION_JSON); HttpEntity<Topic> entity
	 * = new HttpEntity<Topic>(topic,headers);
	 * restTemplate.put("http://localhost:9000/topicspatchupdate/"+id, entity,
	 * Topic.class);
	 * 
	 * }
	 */

	@DeleteMapping("/topic2delete/{id}")
	public void deleteTopic(@PathVariable long id) {
		restTemplate.delete("http://localhost:9000/topicsdelete/" + id);

	}

}
