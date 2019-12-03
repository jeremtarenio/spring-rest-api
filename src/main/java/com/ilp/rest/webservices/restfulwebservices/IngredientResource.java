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

@CrossOrigin(origins = "https://jeremtarenio.github.io/recipe-book")
@RestController
public class IngredientResource {
	@Autowired
	private IngredientHardcodedService ingredientHardCodedService;

	// CREATE
	@PostMapping("/ingredients")
	public ResponseEntity<Void> updateIngredient(@RequestBody Ingredient ingredient) {
		Ingredient ing = ingredientHardCodedService.save(-1, ingredient);
		int indexOfIngredient = IngredientHardcodedService.getIngredients().indexOf(ing);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(indexOfIngredient)
				.toUri();

		return ResponseEntity.created(uri).build();
	}
 
	// READ
	@GetMapping("/ingredients")
	public List<Ingredient> getAllIngredients() {
		return ingredientHardCodedService.findAll();
	} 

	@GetMapping("/ingredients/{index}")
	public Ingredient getIngredient(@PathVariable String index) {
		Ingredient ingredient = ingredientHardCodedService.findByIndex(Integer.parseInt(index));

		if (ingredient != null) {
			return ingredient;
		}

		return null;
	}

	// UPDATE
	@PutMapping("/ingredients/{index}")
	public ResponseEntity<Ingredient> updateIngredient(@PathVariable String index, @RequestBody Ingredient ingredient) {
		Ingredient ing = ingredientHardCodedService.save(Integer.parseInt(index), ingredient);

		return new ResponseEntity<Ingredient>(ing, HttpStatus.OK);
	}
 
	// DELETE
	@DeleteMapping("/ingredients/{index}")
	public ResponseEntity<Void> deleteIngredient(@PathVariable String index) {
		Ingredient ingredient = ingredientHardCodedService.deleteByIndex(Integer.parseInt(index));

		if (ingredient != null) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.notFound().build();
	}

}
