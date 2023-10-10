package com.F5aes.service;

import com.F5aes.Exceptions.BootcampNotFoundExceptions;
import com.F5aes.model.Bootcamp;
import com.F5aes.model.Content;
import com.F5aes.model.Skill;
import com.F5aes.model.Stack;
import com.F5aes.repository.BootcampRepository;
import com.F5aes.repository.ContentRepository;
import com.F5aes.repository.SkillRepository;
import com.F5aes.repository.StackRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class PrincipalService {

	// ----- Stack model Methods -----
	@Autowired
	private StackRepository stackRepository;

	public Stack createStack(Stack stack) {

	return 	stackRepository.save(stack);

	}

	public List<Stack> getAllStack() {

		return stackRepository.findAll();

	}

	public Stack getStackById(long id) {
		Optional<Stack> optionalStack = stackRepository.findById(id);
		return optionalStack.orElse(null);
	}

	public Stack editStack(Long id,Stack stack) {

		Stack existingStack = getStackById(id);

			existingStack.setId(stack.getId());
			existingStack.setName(stack.getName());
		return  createStack(existingStack);


	}

	public void deleteStack(Long id) {

		stackRepository.deleteById(id);
		ResponseEntity.ok("Stack deleted successfully!");
	}

	// ----- Skill model methods -----
	@Autowired
	private SkillRepository skillRepository;

	public Skill createSkill(Skill skill) {

	return 	skillRepository.save(skill);

	}

	public List<Skill> getAllSkill() {
		try {
			return skillRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	public Skill getSkillById(long id) {

		Optional<Skill> optionalSkillModel = skillRepository.findById(id);
		return optionalSkillModel.orElse(null);
	}

	public Skill editSkill(Long id, Skill skill) {

				Skill updateSkill = getSkillById(id);
				updateSkill.setId(skill.getId());
				updateSkill.setName(skill.getName());

			return 	skillRepository.save(updateSkill);

	}

	public void deleteSkill(Long id) {

		skillRepository.deleteById(id);
		ResponseEntity.ok("Content deleted!");
	}

	// ----- Content model Methods -----
	@Autowired
	private ContentRepository contentRepository;

	public void createContent(Content contents) {

		contentRepository.save(contents);
		ResponseEntity.ok("Saved Successfully");
	}

	public List<Content> getAllContent() {

		return contentRepository.findAll();

	}

	public Optional<Content> getContentById(long id) {
		return contentRepository.findById(id);
	}

	public void editContent(@RequestBody Content contents, @PathVariable Long id) {
		try {
			Optional<Content> existingContent = contentRepository.findById(id);

			if (existingContent.isPresent()) {
				Content updateContent = existingContent.get();
				updateContent.setName(contents.getName());

				contentRepository.save(updateContent);
				ResponseEntity.ok("Content updated!");
			} else {
				ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not updated");
		}
	}

	public void deleteContent(Long id) {

		contentRepository.deleteById(id);
		ResponseEntity.ok("Content deleted!");
	}

	// ---------Bootcamp Model Content--------
	@Autowired
	private BootcampRepository bootcampRepository;

	public List<Bootcamp> findAll() {
		return bootcampRepository.findAll();
	}


	public Bootcamp getBootcampById (Long id) {
	Optional<Bootcamp> optionalBootcampModel = bootcampRepository.findById(id);
	return optionalBootcampModel.orElse(null);
	}



	public Bootcamp findByName(String name) {
		return bootcampRepository.findByName(name)
				.orElseThrow(() -> new BootcampNotFoundExceptions("Bootcamp not found whit name " + name));
	}

	public Bootcamp saveBootcamp(Bootcamp bootcamp) {
		return bootcampRepository.save(bootcamp);
	}

	@Transactional
	public Bootcamp updateBootcamp(Long id, Bootcamp bootcampDetails) {
		Bootcamp bootcamp = getBootcampById(id);
		bootcamp.setName(bootcampDetails.getName());
		bootcamp.setDuration(bootcampDetails.getDuration());
		bootcamp.setStartDate(bootcampDetails.getStartDate());
		bootcamp.setEndDate(bootcampDetails.getEndDate());
		return saveBootcamp(bootcamp);
	}

	public void deleteById(Long Id) {
		bootcampRepository.deleteById(Id);
	}

}
