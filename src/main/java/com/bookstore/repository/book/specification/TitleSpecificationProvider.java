package com.bookstore.repository.book.specification;

import com.bookstore.model.Book;
import com.bookstore.repository.SpecificationProvider;
import java.util.Arrays;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class TitleSpecificationProvider implements SpecificationProvider<Book> {

    @Override
    public String getKey() {
        return "title";
    }

    @Override
    public Specification<Book> getSpecification(String[] params) {
        return (root, query, criteriaBuilder)
                -> root.get("title").in(Arrays.stream(params).toArray());
    }

}