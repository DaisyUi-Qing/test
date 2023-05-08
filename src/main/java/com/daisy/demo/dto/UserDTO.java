package com.daisy.demo.dto;

import com.daisy.demo.entity.User;
import lombok.Data;

/**
 * @author dxm
 */
@Data
public class UserDTO extends User {

    private int pageNum;

    private int pageSize;

    private int start;

    private Boolean all;
}
