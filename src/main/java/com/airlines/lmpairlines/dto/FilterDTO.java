package com.airlines.lmpairlines.dto;

import lombok.Data;

@Data
public class FilterDTO {
    private String filter;
    private Integer pageNumber;
    private Integer pageSize;
}
