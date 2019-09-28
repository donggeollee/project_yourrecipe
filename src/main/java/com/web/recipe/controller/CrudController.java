package com.web.recipe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.web.recipe.ifs.CrudInterface;
import com.web.recipe.model.network.Header;
import com.web.recipe.service.BaseService;

@Component
public class CrudController<Req,Res,Entity> implements CrudInterface<Req, Res>{
	
	@Autowired
	protected BaseService<Req, Res, Entity> baseService;
	
	@Override
	@PostMapping("")
	public Header<Res> create(@RequestBody Header<Req> request) {
		return baseService.create(request);
	}

	@Override 
	@GetMapping("{id}")	
	public Header<Res> read(@PathVariable Long id) {
		return baseService.read(id);
	}
	
	@Override
	@PutMapping("")
	public Header<Res> update(@RequestBody Header<Req> request) {
		return baseService.update(request);
	}

	@Override
	@DeleteMapping("{id}")
	public Header<Res> delete(@PathVariable Long id) {
		return baseService.delete(id);
	}

	

}
