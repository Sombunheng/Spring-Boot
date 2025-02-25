package com.bunheng.java.learn.phoneshop.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaginationDTO {
    private int pageNumber ;
    private int pageSize ; 
    private int totalPages ;
    private long totalElements ;
    private long numberOfElements;


    private boolean first ;
    private boolean last ;
    private boolean empty ;


}
