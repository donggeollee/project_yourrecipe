package com.web.recipe.service.image;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.web.recipe.model.entity.Image;
import com.web.recipe.model.network.Header;
import com.web.recipe.model.network.request.ImageApiRequest;
import com.web.recipe.model.network.response.ImageApiResponse;
import com.web.recipe.service.BaseService;

@Service
public class ImageApiLogicService extends BaseService<ImageApiRequest, ImageApiResponse, Image>{

	@Override
	public Header<ImageApiResponse> create(Header<ImageApiRequest> request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Header<ImageApiResponse> read(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Header<ImageApiResponse> update(Header<ImageApiRequest> request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Header delete(Long id) {
		Optional<Image> optional =baseRepository.findById(id);
		return optional.map(image->{
			baseRepository.delete(image);
			
			
			return Header.OK();
		}).orElseGet(()->Header.ERROR("ERROR"));
	}

}
