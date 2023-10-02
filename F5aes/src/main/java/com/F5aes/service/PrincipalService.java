package com.F5aes.service;

import com.F5aes.Exceptions.BootcampNotFoundExceptions;
import com.F5aes.model.BootcampModel;
import com.F5aes.model.ContentModel;
import com.F5aes.model.SkillModel;
import com.F5aes.model.StackModel;
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

	public void createStack(StackModel stack) {

		stackRepository.save(stack);
		ResponseEntity.ok("Saved Successfully");
	}

	public List<StackModel> getAllStack() {

		return stackRepository.findAll();

	}

	public Optional<StackModel> getStackById(long id) {
		return stackRepository.findById(id);
	}

	public void editStack(@RequestBody StackModel stack, @PathVariable Long id) {
		try {
			Optional<StackModel> existingStack = stackRepository.findById(id);

			if (existingStack.isPresent()) {
				StackModel updateStack = existingStack.get();
				updateStack.setName(stack.getName());

				stackRepository.save(updateStack);
				ResponseEntity.ok("Stack updated!");
			} else {
				ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not updated");
		}
	}

	public void deleteStack(Long id) {

		stackRepository.deleteById(id);
		ResponseEntity.ok("Content deleted!");
	}

	// ----- Skill model methods -----
	@Autowired
	private SkillRepository skillRepository;

	public void createSkill(SkillModel skill) {

		skillRepository.save(skill);
		ResponseEntity.ok("Saved Successfully");
	}

	public List<SkillModel> getAllSkill() {

		return skillRepository.findAll();

	}

	public void editSkill(@RequestBody SkillModel skill, @PathVariable Long id) {
		try {
			Optional<SkillModel> existingSkill = skillRepository.findById(id);

			if (existingSkill.isPresent()) {
				SkillModel updateSkill = existingSkill.get();
				updateSkill.setName(skill.getName());

				skillRepository.save(updateSkill);
				ResponseEntity.ok("Skill updated!");
			} else {
				ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not updated");
		}
	}

	public void deleteSkill(Long id) {

		skillRepository.deleteById(id);
		ResponseEntity.ok("Content deleted!");
	}

	// ----- Content model Methods -----
	@Autowired
	private ContentRepository contentRepository;

	public void createContent(ContentModel contents) {

		contentRepository.save(contents);
		ResponseEntity.ok("Saved Successfully");
	}

	public List<ContentModel> getAllContent() {

		return contentRepository.findAll();

	}

	public void editContent(@RequestBody ContentModel contents, @PathVariable Long id) {
		try {
			Optional<ContentModel> existingContent = contentRepository.findById(id);

			if (existingContent.isPresent()) {
				ContentModel updateContent = existingContent.get();
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

	public List<BootcampModel> findAll() {
		return bootcampRepository.findAll();
	}

	public BootcampModel findById(Long id) {
		return bootcampRepository.findById(id)
				.orElseThrow(() -> new BootcampNotFoundExceptions("Bootcamp not found whit id:" + id));
	}

	public BootcampModel findByName(String name) {
		return bootcampRepository.findByName(name)
				.orElseThrow(() -> new BootcampNotFoundExceptions("Bootcamp not found whit name " + name));
	}

	public BootcampModel save(BootcampModel bootcampModel) {
		return bootcampRepository.save(bootcampModel);
	}

	@Transactional
	public BootcampModel updateBootcamp(Long id, BootcampModel bootcampDetails) {
		BootcampModel bootcampModel = findById(id);
		bootcampModel.setName(bootcampDetails.getName());
		bootcampModel.setDuration(bootcampDetails.getDuration());
		bootcampModel.setStartDate(bootcampDetails.getStartDate());
		bootcampModel.setEndDate(bootcampDetails.getEndDate());
		return save(bootcampModel);
	}

	public void deleteById(Long Id) {
		bootcampRepository.deleteById(Id);
	}

}
