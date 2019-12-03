package com.ilp.rest.webservices.restfulwebservices;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins = "https://jeremtarenio.github.io")
@RestController
public class RecipeResource {

	@Autowired
	private RecipeHardCodedService recipeHardCodedService;

	// CREATE
	@PostMapping("/recipes")  // ok
	public ResponseEntity<Void> addRecipe(@RequestBody Recipe recipe) {
		Recipe newRecipe = recipeHardCodedService.save(-1, recipe);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newRecipe.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}
  
	// READ
	@GetMapping("/recipes") // ok
	public List<Recipe> getAllRecipes() {
		return recipeHardCodedService.findAll();
	}

	@GetMapping("/recipes/{id}") // ok
	public Recipe getRecipe(@PathVariable int id) {
		Recipe recipe = recipeHardCodedService.findById(id);

		if (recipe != null) {
			return recipe;
		}

		return null;
	}
	
	// UPDATE 
	@PutMapping("/recipes/{id}") // ok
	public ResponseEntity<Recipe> updateRecipe(@PathVariable int id, @RequestBody Recipe recipe) {
		Recipe updatedRecipe = recipeHardCodedService.save(id, recipe);

		return new ResponseEntity<Recipe>(updatedRecipe, HttpStatus.OK);
	}
  
	// DELETE
	@DeleteMapping("/recipes/{id}") // ok
	public ResponseEntity<Void> deleteRecipe(@PathVariable int id) {
		Recipe recipe = recipeHardCodedService.deleteById(id);

		if (recipe != null) {
			return ResponseEntity.noContent().build();
		}
 
		return ResponseEntity.notFound().build();
	}

}
