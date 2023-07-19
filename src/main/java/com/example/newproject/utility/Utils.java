package com.example.newproject.utility;

import com.example.newproject.entity.Pagination;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Malikov Azizjon    newProject       19.07.2023       14:46
 */

@Slf4j
public class Utils {

    public static Pageable getPageableFromPagination(Pagination pagination) {
        Pageable paging;
        if (pagination.     getSortBy() == null || pagination.getSortBy().size() == 0)
            paging = PageRequest.of(pagination.getPage(), pagination.getItemsPerPage(), Sort.by("id").descending());
        else {
            List<Sort.Order> orders = new ArrayList<Sort.Order>();
            List<String> sortBy = pagination.getSortBy();
            List<Boolean> sortDesc = pagination.getSortDesc();
            for (int i = 0; i < sortBy.size(); i++) {
                Sort.Direction direction;
                if (sortDesc.get(i))
                    direction = Sort.Direction.DESC;
                else direction = Sort.Direction.ASC;
                Sort.Order order = new Sort.Order(direction, sortBy.get(i));
                orders.add(order);
            }
            paging = PageRequest.of(pagination.getPage(), pagination.getItemsPerPage(), Sort.by(orders));
        }

        return paging;
    }

}
