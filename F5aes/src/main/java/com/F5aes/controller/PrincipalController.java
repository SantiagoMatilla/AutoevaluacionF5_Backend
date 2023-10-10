package com.F5aes.controller;

import com.F5aes.model.*;
import com.F5aes.service.PrincipalService;
import com.F5aes.service.UserService;
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
	@Autowired
	private UserService userService;

	// ------ Bootcamp Model methods -------
	@GetMapping("/bootcamp")
	public List<Bootcamp> getAllBootcamp() {
		return principalService.findAll();
	}

	@GetMapping({ "bootcamp/{id}" })
	public Bootcamp getBootcampById(@PathVariable Long id) {
		return principalService.getBootcampById(id);
	}

	@GetMapping("/{name}")
	public Bootcamp getBootcampByName(@PathVariable String name) {
		return principalService.findByName(name);
	}

	@PostMapping("/saveBootcamp")
	public ResponseEntity<Bootcamp> createBootcamp(@RequestBody Bootcamp bootcamp) {


		Bootcamp savedBootcamp = principalService.saveBootcamp(bootcamp);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedBootcamp);
	}

	@PutMapping("/updateBootcamp/{id}")
	public Bootcamp updateBootcamp(@PathVariable Long id, @RequestBody Bootcamp bootcampDetails) {
		return principalService.updateBootcamp(id, bootcampDetails);
	}

	@DeleteMapping("/deleteBootcamp/{id}")
	public ResponseEntity<String> deleteBootcamp(@PathVariable Long id) {
		principalService.deleteById(id);
		return ResponseEntity.ok("Bootcamp deleted successfully");
	}

	// ----- Stack Model methods -----

	// create image directory
	public  static String imagesPaths = System.getProperty("user.dir")+"/src/main/resources/Images";

	@PostMapping("/saveStack")
	public ResponseEntity<String> createStack( @ModelAttribute Stack stackModel,
	                                     @RequestParam("image") MultipartFile file) {

		String originalFilename = file.getOriginalFilename();
		Path fileNamePath = Paths.get(imagesPaths,originalFilename);
		try{
			Files.write(fileNamePath,file.getBytes());
			Stack stack = new Stack();
			stack.setImage(originalFilename);
			stack.setName(stackModel.getName());

		}catch (IOException e){

			throw new RuntimeException(e);
		}
		principalService.createStack(stackModel);
		return ResponseEntity.ok("Successfully Saved!");
	}

	@GetMapping("/stacks")
	public List<Stack> getAllStacks() {

		return principalService.getAllStack();
	}

	@GetMapping("/stack/{id}")
	public Stack getStackById(@PathVariable long id) {
		return principalService.getStackById(id);
	}

	@PutMapping("/updateStack/{id}")
	public ResponseEntity<?> updateStack(@PathVariable Long id ,@RequestBody Stack stack) {
		principalService.editStack(id,stack);
		return ResponseEntity.ok("successfully updated!");
	}

	@DeleteMapping("/deleteStack/{id}")
	public void deleteStack(@PathVariable Long id) {

		principalService.deleteStack(id);

	}

	// ----- Skill Model methods -----
	@PostMapping("/saveSkill")
	public ResponseEntity<String> createSkill(@RequestBody Skill skill) {

		Stack selectedStack = principalService.getStackById(skill.getStack().getId());

		if (selectedStack == null) {
			return ResponseEntity.badRequest().body("Selected stack not found");
		}

		// Set the selected stack in the skill
		skill.setStack(selectedStack);

		principalService.createSkill(skill);

		return ResponseEntity.ok("Saved Successfully");
	}



	@GetMapping("/skills")
	public List<Skill> getAllSkills() {

		return principalService.getAllSkill();
	}

	@GetMapping("/skills/{id}")
	public Skill getSkillById(@PathVariable long id) {
		return principalService.getSkillById(id);
	}

	@PutMapping("/updateSkill/{id}")
	public ResponseEntity<?> updateSkill(@RequestBody Skill skill, @PathVariable Long id) {
		principalService.editSkill(skill, id);
		return ResponseEntity.ok("successfully updated!");
	}

	@DeleteMapping("/skill/{id}")
	public void deleteSkill(@PathVariable Long id) {

		principalService.deleteSkill(id);

	}

	// ----- Content Model methods -----
	@PostMapping("/saveContent")
	public ResponseEntity<?> createContent(@RequestBody Content contents) {

		Skill selectedSkill = principalService.getSkillById(contents.getSkill().getId());

		if (selectedSkill == null) {
			return ResponseEntity.badRequest().body("Selected skill not found");
		}

		// Set the selected stack in the skill
		contents.setSkill(selectedSkill);

		principalService.createContent(contents);

		return ResponseEntity.ok("Saved Successfully");

}
	@GetMapping("/contents")
	public List<Content> getAllContents() {

		return principalService.getAllContent();
	}
	@GetMapping("/content/{id}")
	public Optional<Content> getContentById(@PathVariable long id) {
		return principalService.getContentById(id);
	}

	@PutMapping("/updateContent/{id}")
	public ResponseEntity<?> updateContent(@RequestBody Content content, @PathVariable Long id) {
		principalService.editContent(content, id);
		return ResponseEntity.ok("successfully updated!");
	}

	@DeleteMapping("/content/{id}")
	public void deleteContent(@PathVariable Long id) {

		principalService.deleteContent(id);

	}
}
