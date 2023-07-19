package com.example.newproject.entity;

import lombok.Data;

import java.util.List;

/**
 * @author Malikov Azizjon    newProject       19.07.2023       14:43
 */

@Data
public class Pagination <T>{

    private Integer page;
    private Integer itemsPerPage;  // limit
    private List<String> sortBy;
    private List<Boolean> sortDesc;
    private T search;


    public Pagination convertToPagination(){
        Pagination pagination = new Pagination<>();
        pagination.setPage(page);
        pagination.setItemsPerPage(itemsPerPage);
        pagination.setSortBy(sortBy);
        pagination.setSortDesc(sortDesc);
        return pagination;
    }

}
