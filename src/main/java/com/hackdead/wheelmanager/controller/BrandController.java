package com.hackdead.wheelmanager.controller;

import com.hackdead.wheelmanager.entities.Brand;
import com.hackdead.wheelmanager.service.IBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin
@RestController
@RequestMapping("/api/brands")
@Api(tags = "Brand", value = "Service Web RESTful of brands")
public class BrandController {
    @Autowired
    private IBrandService brandService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List Brands", notes = "Method to list all brands")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Brands found"),
            @ApiResponse(code = 404, message = "Brands not found")
    })
    public ResponseEntity<List<Brand>> findAll() {
        try {
            List<Brand> brands = brandService.getAll();
            if (brands.size() > 0)
                return new ResponseEntity<List<Brand>>(brands, HttpStatus.OK);
            else
                return new ResponseEntity<List<Brand>>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<List<Brand>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search Brand by Id", notes = "Method to find an brand by Id")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Brand found"),
            @ApiResponse(code = 404, message = "Brand not found")
    })
    public ResponseEntity<Brand> findById(@PathVariable("id") Long id) {
        try {
            Optional<Brand> brand = brandService.getById(id);
            if (!brand.isPresent())
                return new ResponseEntity<Brand>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<Brand>(brand.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Brand>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchByBrandName/{brandName}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search Brand by name", notes = "Method to find Brand by name")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Brands found"),
            @ApiResponse(code = 404, message = "Brands not found")
    })
    public ResponseEntity<List<Brand>> findByBrandName(@PathVariable("brandName") String brandName) {
        try {
            List<Brand> brands = brandService.findByBrandName(brandName);
            if (brands.size() > 0)
                return new ResponseEntity<List<Brand>>(brands, HttpStatus.OK);
            else
                return new ResponseEntity<List<Brand>>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<List<Brand>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registration of Brands", notes = "Method to register Brands in the BD")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Brand found"),
            @ApiResponse(code = 404, message = "Brand not found")
    })
    public ResponseEntity<Brand> insertBrand(@Valid @RequestBody Brand brand) {
        try {
            Brand brandNew = brandService.save(brand);
            return ResponseEntity.status(HttpStatus.CREATED).body(brandNew);
        } catch (Exception e) {
            return new ResponseEntity<Brand>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update Brands data", notes = "Method to update Brands")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Brand updated"),
            @ApiResponse(code = 404, message = "Brand not updated")
    })
    public ResponseEntity<Brand> updateBrand(
            @PathVariable("id") Long id, @Valid @RequestBody Brand brand) {
        try {
            Optional<Brand> brandUp = brandService.getById(id);
            if (!brandUp.isPresent())
                return new ResponseEntity<Brand>(HttpStatus.NOT_FOUND);
            brand.setId(id);
            brandService.save(brand);
            return new ResponseEntity<Brand>(brand, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Brand>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete Brand", notes = "Method to delete Brand")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Brand deleted"),
            @ApiResponse(code = 404, message = "Brand not deleted")
    })
    public ResponseEntity<Brand> deleteBrand(@PathVariable("id") Long id) {
        try {
            Optional<Brand> brandDelete = brandService.getById(id);
            if (!brandDelete.isPresent())
                return new ResponseEntity<Brand>(HttpStatus.NOT_FOUND);
            brandService.delete(id);
            return new ResponseEntity<Brand>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Brand>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
