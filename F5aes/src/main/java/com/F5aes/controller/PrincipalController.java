package com.F5aes.controller;

import com.F5aes.model.*;
import com.F5aes.repository.SkillRepository;
import com.F5aes.repository.StackRepository;
import com.F5aes.service.PrincipalService;
//import com.F5aes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api")
@CrossOrigin("*")
public class PrincipalController {
	@Autowired
	private PrincipalService principalService;
//	@Autowired
//	private UserService userService;

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
	//public  static String imagesPaths = System.getProperty("user.dir")+"/src/main/resources/Images";

	@PostMapping("/saveStack")
	public ResponseEntity<String> createStack( @RequestBody Stack stackModel) {

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
	public ResponseEntity<String> deleteStack(@PathVariable Long id) {

		principalService.deleteStack(id);
		return ResponseEntity.ok("Stack deleted successfully");

	}

	// ----- Skill Model methods -----
	@Autowired
	StackRepository stackRepository;
	@PostMapping("/saveSkill")
	public ResponseEntity<Map<String, Object>> createSkill(@RequestBody Skill skillRequest) {

		// Retrieve the Stack object by its ID
		Stack stack = null;
		if(skillRequest.getStack() !=null) {
			stack = stackRepository.findById(skillRequest.getStack().getId()).orElse(null);
		}
		if (stack == null) {
			Skill skill = new Skill();
			skill.setName(skillRequest.getName());
			Skill result=	principalService.createSkill(skill);
			Map<String, Object> response = new HashMap<>();
			response.put("message", "Stack not found or not specified");
			response.put("data", result);
			return ResponseEntity.badRequest().body(response);
		}

		Skill skill = new Skill();
		skill.setName(skillRequest.getName());
		skill.setStack(stack);
		Skill result=	principalService.createSkill(skill);
		Map<String, Object> response = new HashMap<>();
		response.put("message", "Saved Successfully");
		response.put("data", result);
		return ResponseEntity.ok(response);

	}

	@GetMapping("/skills")
	public List<Skill> getAllSkills() {

		return principalService.getAllSkill();
	}

	@GetMapping("/skill/{id}")
	public Skill getSkillById(@PathVariable long id) {
		return principalService.getSkillById(id);
	}

	@PutMapping("/updateSkill/{id}")
	public ResponseEntity<Map<String,Object>> updateSkill( @PathVariable Long id ,@RequestBody Skill skill) {
		// Retrieve the existing Skill object by its ID
		Skill existingSkill = principalService.getSkillById(id);


		if (existingSkill == null) {
			Map<String, Object> response = new HashMap<>();
			response.put("message", "Skill not found");
			return ResponseEntity.badRequest().body(response);
		}

		// Update the skill name and/or stack if provided
		if (skill.getName() != null || skill.getStack() != null) {
			if (skill.getName() != null) {
				existingSkill.setName(skill.getName());
			}
			// Update the stack in skill if provided
			if (skill.getStack() != null) {
				Long stackId = skill.getStack().getId();
				Stack stack = stackRepository.findById(stackId).orElse(null);
				if (stack == null) {
					Map<String, Object> response = new HashMap<>();
					response.put("message", "Skill not found");
					return ResponseEntity.badRequest().body(response);
				}
				existingSkill.setStack(stack);
			}

			// Save the updated skill
			Skill updatedSkill = principalService.editSkill(id, existingSkill);

			Map<String, Object> response = new HashMap<>();
			response.put("message", "Updated Successfully");
			response.put("data", updatedSkill);
			return ResponseEntity.ok(response);
		}
		// If neither name nor skill is provided
		Map<String, Object> response = new HashMap<>();
		response.put("message", "No data provided for updated");
		return ResponseEntity.badRequest().body(response);

	}

	@DeleteMapping("/deleteSkill/{id}")
	public ResponseEntity<String> deleteSkill(@PathVariable Long id) {

		principalService.deleteSkill(id);
		return ResponseEntity.ok("Skill deleted successfully!");
	}

	// ----- Content Model methods -----
	@Autowired
	private SkillRepository skillRepository;
	@PostMapping("/saveContent")
	public ResponseEntity<Map<String, Object>> createContent(@RequestBody Content contentRequest, Long id) {
		// Retrieve the Skill object by its ID
		Skill skill= null;
		if(contentRequest.getSkill()!=null) {
			 skill = skillRepository.findById(contentRequest.getSkill().getId()).orElse(null);
		}
		if (skill == null) {
			Content content = new Content();
			content.setName(contentRequest.getName());
			Content result=	principalService.createContent(content);
			Map<String, Object> response = new HashMap<>();
			response.put("message", "Skill not found or not specified");
			response.put("data", result);
			return ResponseEntity.badRequest().body(response);

		}
		Content content = new Content();
		content.setName(contentRequest.getName());
		content.setSkill(skill);

		Content result=	principalService.createContent(content);
		Map<String, Object> response = new HashMap<>();
		response.put("message", "Saved Successfully");
		response.put("data", result);
		return ResponseEntity.ok(response);

	}
	@GetMapping("/contents")
	public List<Content> getAllContents() {

		return principalService.getAllContent();
	}
	@GetMapping("/content/{id}")
	public Content getContentById(@PathVariable long id) {
		return principalService.getContentById(id);
	}

	@PutMapping("/updateContent/{id}")
	public ResponseEntity<Map<String, Object>> updateContent(@RequestBody Content content, @PathVariable Long id) {
		// Retrieve the existing Content object by its ID
		Content existingContent = principalService.getContentById(id);

		if (existingContent == null) {
			Map<String, Object> response = new HashMap<>();
			response.put("message", "Content not found");
			return ResponseEntity.badRequest().body(response);
		}

		// Update the content name if provided
		if (content.getName() != null || content.getSkill() != null) {
			if (content.getName() != null) {
				existingContent.setName(content.getName());
			}
			// Update the skill in content if provided
			if (content.getSkill() != null) {
				Skill skill = skillRepository.findById(content.getSkill().getId()).orElse(null);
				if (skill == null) {
					Map<String, Object> response = new HashMap<>();
					response.put("message", "Skill not found");
					return ResponseEntity.badRequest().body(response);
				}
				existingContent.setSkill(skill);
			}

			// Save the updated content
			Content updatedContent = principalService.editContent(id, existingContent);

			Map<String, Object> response = new HashMap<>();
			response.put("message", "Updated Successfully");
			response.put("data", updatedContent);
			return ResponseEntity.ok(response);
		}
		// If neither name nor skill is provided
		Map<String, Object> response = new HashMap<>();
		response.put("message", "No data provided for updated");
		return ResponseEntity.badRequest().body(response);
	}
	@DeleteMapping("/deleteContent/{id}")
	public ResponseEntity<String> deleteContent(@PathVariable Long id) {
		principalService.deleteContent(id);
		return ResponseEntity.ok("Content deleted successfully!");

	}
}
