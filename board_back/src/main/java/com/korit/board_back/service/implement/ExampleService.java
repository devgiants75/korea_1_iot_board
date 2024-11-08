package com.korit.board_back.service.implement;

import com.korit.board_back.entity.Example;
import com.korit.board_back.entity.ExampleCategory;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ExampleService {

    public Example saveExample(Set<ExampleCategory> categories) {

        categories = Set.of(
                ExampleCategory.EXAMPLE1,
                ExampleCategory.EXAMPLE2,
                ExampleCategory.EXAMPLE3
        );
        
        Example example = new Example();
        example.setCategories(categories);
        return null;
    }
}


