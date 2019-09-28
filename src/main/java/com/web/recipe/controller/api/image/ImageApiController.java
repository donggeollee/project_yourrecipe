package com.web.recipe.controller.api.image;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.recipe.controller.CrudController;
import com.web.recipe.model.entity.Image;
import com.web.recipe.model.network.request.ImageApiRequest;
import com.web.recipe.model.network.response.ImageApiResponse;


@RestController
@RequestMapping("/api/image")
public class ImageApiController extends CrudController<ImageApiRequest, ImageApiResponse, Image>{

}
