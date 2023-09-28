package com.F5aes.controller;

import com.F5aes.model.ContentModel;
import com.F5aes.model.SkillModel;
import com.F5aes.model.StackModel;
import com.F5aes.service.PrincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class PrincipalController {

	@Autowired
	private PrincipalService  principalService;


//	----- Stack Model methods -----
	@PostMapping("/saveStack")
	public ResponseEntity <?> createStack(@RequestBody StackModel stackModel) {

		principalService.createStack(stackModel);
		return  ResponseEntity.ok("Successfully Saved!");
	}

	@GetMapping("/stacks")
	public List<StackModel> getAllStacks() {

		return principalService.getAllStack();
	}
	@PutMapping("/updateStack/{id}")
	public ResponseEntity<?> updateStack(@RequestBody StackModel stack, @PathVariable Long id) {
		principalService.editStack(stack,id);
		return ResponseEntity.ok("successfully updated!");
	}

	@DeleteMapping("/stacks/{id}")
	public void deleteStack(@PathVariable Long id) {

		principalService.deleteStack(id);

	}


	//	----- Skill Model methods -----
	@PostMapping("/saveSkill")
	public ResponseEntity <?> createSkill(@RequestBody SkillModel skill) {

		principalService.createSkill(skill);
		return  ResponseEntity.ok("Successfully Saved!");
	}

	@GetMapping("/skills")
	public List<SkillModel> getAllSkills() {

		return principalService.getAllSkill();
	}

	@PutMapping("/updateSkill/{id}")
	public ResponseEntity<?> updateSkill(@RequestBody SkillModel skill, @PathVariable Long id) {
		principalService.editSkill(skill,id);
		return ResponseEntity.ok("successfully updated!");
	}
	@DeleteMapping("/skill/{id}")
	public void deleteSkill(@PathVariable Long id) {

		principalService.deleteSkill(id);

	}

	//	----- Skill Model methods -----
	@PostMapping("/saveContent")
	public ResponseEntity <?> createContent(@RequestBody ContentModel contents) {

		principalService.createContent(contents);
		return  ResponseEntity.ok("Successfully Saved!");
	}

	@GetMapping("/contents")
	public List<ContentModel> getAllContents() {

		return principalService.getAllContent();
	}

	@PutMapping("/updateContent/{id}")
	public ResponseEntity<?> updateContent(@RequestBody ContentModel content, @PathVariable Long id) {
		principalService.editContent(content,id);
		return ResponseEntity.ok("successfully updated!");
	}
	@DeleteMapping("/content/{id}")
	public void deleteContent(@PathVariable Long id) {

		principalService.deleteContent(id);

	}
}
