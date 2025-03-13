package com.bunheng.java.learn.phoneshop.service.Impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bunheng.java.learn.phoneshop.entity.Color;
import com.bunheng.java.learn.phoneshop.exception.ResourceNotFoundException;
import com.bunheng.java.learn.phoneshop.repository.ColorRepository;
import com.bunheng.java.learn.phoneshop.service.ColorService;
import com.bunheng.java.learn.phoneshop.service.util.PageUtil;
import com.bunheng.java.learn.phoneshop.specification.color.ColorFilter;
import com.bunheng.java.learn.phoneshop.specification.color.ColorSpecification;

@Service
public class ColorServiceImpl implements ColorService{
    @Autowired
    private ColorRepository colorRepository ; 

    @Override
    public Color create(Color color) {
       return colorRepository.save(color);
    }

    @Override
    public Page<Color> getColors(Map<String, String> params) {
        ColorFilter  colorFilter  = new ColorFilter(); 

        if(params.containsKey("name")){
            String name = params.get("name");
            colorFilter.setName(name);
        }

        if (params.containsKey("id")){
            String id = params.get("id");
            colorFilter.setId(Long.parseLong(id));
        }

         int pageLimit = 2 ;
        if (params.containsKey(PageUtil.PAGE_LIMIT)){
            pageLimit = Integer.parseInt(params.get(PageUtil.PAGE_LIMIT));
        }

        int pageNumber = 1 ;
        if (params.containsKey(PageUtil.PAGE_NUMBER)){
            pageNumber = Integer.parseInt(params.get(PageUtil.PAGE_NUMBER));
        }

        ColorSpecification colorSpecification = new ColorSpecification(colorFilter); 
        PageRequest pageable = PageUtil.getPageable(pageNumber, pageLimit);
        Page<Color> page = colorRepository.findAll(colorSpecification, pageable) ;

        return page ;

    }
    


    @Override
    public Color getColorById(Long id) {
       return colorRepository.findById(id)
                            .orElseThrow(() -> new ResourceNotFoundException("Color", id)) ;

    }

    @Override
    public Color update(Long id, Color colorUpdate) {
       Color color = getColorById(id); 
       color.setName(colorUpdate.getName());
       return colorRepository.save(color); 
    }

    @Override
    public void delete(Long id) {
        if (!colorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Color", id) ;
        }
        colorRepository.deleteById(id); // Deletes the brand with the given ID
    }
    
}
