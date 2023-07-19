package com.example.newproject.utility;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * @author Malikov Azizjon    newProject       19.07.2023       14:41
 */

@Getter
@Builder
public class PageWrapper {

    List list;
    long total;

}
