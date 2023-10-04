package com.F5aes.controller;

import com.F5aes.model.BootcampModel;
import com.F5aes.model.ContentModel;
import com.F5aes.model.SkillModel;
import com.F5aes.model.StackModel;
import com.F5aes.service.PrincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
@CrossOrigin("*")
public class PrincipalController {

	@Autowired
	private PrincipalService principalService;

	// ----- Stack Model methods -----

	// create image directory
	public  static String imagesPaths = System.clearProperty("user.dir")+"/src/main/resources/Images";

	@PostMapping("/saveStack")
	public ResponseEntity<?> createStack(@RequestBody StackModel stackModel,
	                                     @RequestParam("image") MultipartFile file) {

		String originalFileName = file.getOriginalFilename();
		Path fileNamePath = Paths.get(imagesPaths,originalFileName);
		try{
			Files.write(fileNamePath,file.getBytes());

		}catch (IOException e){

			throw new RuntimeException(e);
		}
		principalService.createStack(stackModel);
		return ResponseEntity.ok("Successfully Saved!");
	}

	@GetMapping("/stacks")
	public List<StackModel> getAllStacks() {

		return principalService.getAllStack();
	}

	@GetMapping("/stacks/{id}")
	public StackModel getStackById(@PathVariable long id) {
		return principalService.getStackById(id);
	}

	@PutMapping("/updateStack/{id}")
	public ResponseEntity<?> updateStack(@RequestBody StackModel stack, @PathVariable Long id) {
		principalService.editStack(stack, id);
		return ResponseEntity.ok("successfully updated!");
	}

	@DeleteMapping("/stacks/{id}")
	public void deleteStack(@PathVariable Long id) {

		principalService.deleteStack(id);

	}

	// ----- Skill Model methods -----
	@PostMapping("/saveSkill")
	public ResponseEntity<String> createSkill(@RequestBody SkillModel skill) {

		StackModel selectedStack = principalService.getStackById(skill.getStackModel().getId());

		if (selectedStack == null) {
			return ResponseEntity.badRequest().body("Selected stack not found");
		}

		// Set the selected stack in the skill
		skill.setStackModel(selectedStack);

		principalService.createSkill(skill);

		return ResponseEntity.ok("Saved Successfully");
	}



	@GetMapping("/skills")
	public List<SkillModel> getAllSkills() {

		return principalService.getAllSkill();
	}

	@GetMapping("/skills/{id}")
	public SkillModel getSkillById(@PathVariable long id) {
		return principalService.getSkillById(id);
	}

	@PutMapping("/updateSkill/{id}")
	public ResponseEntity<?> updateSkill(@RequestBody SkillModel skill, @PathVariable Long id) {
		principalService.editSkill(skill, id);
		return ResponseEntity.ok("successfully updated!");
	}

	@DeleteMapping("/skill/{id}")
	public void deleteSkill(@PathVariable Long id) {

		principalService.deleteSkill(id);

	}

	// ----- Content Model methods -----
	@PostMapping("/saveContent")
	public ResponseEntity<?> createContent(@RequestBody ContentModel contents) {

		SkillModel selectedSkill = principalService.getSkillById(contents.getSkillModel().getId());

		if (selectedSkill == null) {
			return ResponseEntity.badRequest().body("Selected skill not found");
		}

		// Set the selected stack in the skill
		contents.setSkillModel(selectedSkill);

		principalService.createContent(contents);

		return ResponseEntity.ok("Saved Successfully");

}

	@GetMapping("/contents")
	public List<ContentModel> getAllContents() {

		return principalService.getAllContent();
	}

	@GetMapping("/content/{id}")
	public Optional<ContentModel> getContentById(@PathVariable long id) {
		return principalService.getContentById(id);
	}

	@PutMapping("/updateContent/{id}")
	public ResponseEntity<?> updateContent(@RequestBody ContentModel content, @PathVariable Long id) {
		principalService.editContent(content, id);
		return ResponseEntity.ok("successfully updated!");
	}

	@DeleteMapping("/content/{id}")
	public void deleteContent(@PathVariable Long id) {

		principalService.deleteContent(id);

	}

	// ------ Bootcamp Model methods -------

	@GetMapping("/bootcamps")
	public List<BootcampModel> getAllBootcamp() {
		return principalService.findAll();
	}

	@GetMapping({ "/{id}" })
	public BootcampModel getBootcampById(@PathVariable Long id) {
		return principalService.findById(id);
	}

	@GetMapping("/name/{name}")
	public BootcampModel getBootcampByName(@PathVariable String name) {
		return principalService.findByName(name);
	}

	@PostMapping(value = "add", consumes = "application/json")
	public ResponseEntity<BootcampModel> createBootcamp(@RequestBody BootcampModel bootcampModel) {
		BootcampModel savedBootcampModel = principalService.save(bootcampModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedBootcampModel);
	}

	@PutMapping("/update/{id}")
	public BootcampModel updateBootcamp(@PathVariable Long id, @RequestBody BootcampModel bootcampDetails) {
		return principalService.updateBootcamp(id, bootcampDetails);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteBootcamp(@PathVariable Long id) {
		principalService.deleteById(id);
		return ResponseEntity.ok("Bootcamp deleted successfully");
	}
}
