package com.bunheng.java.learn.phoneshop.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bunheng.java.learn.phoneshop.entity.Model;
import com.bunheng.java.learn.phoneshop.exception.ResourceNotFoundException;
import com.bunheng.java.learn.phoneshop.repository.ModelRepository;
import com.bunheng.java.learn.phoneshop.service.ModelService;
import com.bunheng.java.learn.phoneshop.service.util.PageUtil;
import com.bunheng.java.learn.phoneshop.specification.model.ModelFilter;
import com.bunheng.java.learn.phoneshop.specification.model.ModelSpecification;


@Service
public class ModelServiceImpl implements ModelService{

  @Autowired
  private ModelRepository modelRepository ; 

  @Override
  public Model create(Model model) {
    return modelRepository.save(model);
  }

  @Override
  public Model getById( Long id) {
    return modelRepository.findById(id)
                          .orElseThrow(() -> new ResourceNotFoundException("Model", id)) ;
  }

  @Override
  public Page<Model> getmodels(Map<String, String> params) {

    ModelFilter modelFilter = new ModelFilter();

    if(params.containsKey("name")){
      String name = params.get("name");
      modelFilter.setName(name);
    }

    if(params.containsKey("id")){
      String id = params.get("id");
      modelFilter.setId(Long.parseLong(id));
    }

    int pageLimit = 2;
    if (params.containsKey(PageUtil.PAGE_LIMIT)){
        pageLimit = Integer.parseInt(params.get(PageUtil.PAGE_LIMIT));
    }

    int pageNumber = 1 ;
    if (params.containsKey(PageUtil.PAGE_NUMBER)){
        pageNumber = Integer.parseInt(params.get(PageUtil.PAGE_NUMBER));
    }

    ModelSpecification modelSpecification = new ModelSpecification(modelFilter);

    PageRequest pagaable = PageUtil.getPageable(pageNumber, pageLimit);
     
    Page <Model> page = modelRepository.findAll(modelSpecification , pagaable); 

    return page ;

  }

  @Override
  public Model update(Model modelUpdate, Long id) {
    Model model = getById(id);
    model.setName(modelUpdate.getName());
    model.setBrand(modelUpdate.getBrand());
    return modelRepository.save(model);
  }

  @Override
  public void delete(Long id) {
   if(!modelRepository.existsById(id)){
     throw new ResourceNotFoundException("Brand", id) ;
   }
   modelRepository.deleteById(id);
  }

  @Override
    public List<Model> getModelsByBrand(Long brandId) {
        return modelRepository.findByBrandId(brandId);
    }


  // @Override
  // public Page<Model> getmodelsFilter(Map<String, String> params, Pageable pageable) {
  //   ModelFilter modelFilter = new ModelFilter();

  //   // Extract filter parameters from the request
  //   if (params.containsKey("name")) {
  //       modelFilter.setName(params.get("name"));
  //   }

  //   if (params.containsKey("brandId")) {
  //       modelFilter.setBrandId(Integer.parseInt(params.get("brandId")));
  //   }

  //   if(params.containsKey("id")){
  //     String id = params.get("id");
  //     modelFilter.setId(Integer.parseInt(id));
  //   }

  //   int pageLimit = 2;
  //   if (params.containsKey(PageUtil.PAGE_LIMIT)) {
  //       pageLimit = Integer.parseInt(params.get(PageUtil.PAGE_LIMIT));
  //   }

  //   int pageNumber = 0;
  //   if (params.containsKey(PageUtil.PAGE_NUMBER)) {
  //       pageNumber = Integer.parseInt(params.get(PageUtil.PAGE_NUMBER)) - 1; // Zero-based page index
  //   }

  //   // Use the provided pageable parameter
  //   PageRequest pageRequest = PageRequest.of(pageNumber, pageLimit);

  //   // Create Specification
  //   ModelSpecification modelSpecification = new ModelSpecification(modelFilter);

  //   // Return the paginated results
  //   return modelRepository.findAll(modelSpecification, pageRequest);
  //     }



  

}
