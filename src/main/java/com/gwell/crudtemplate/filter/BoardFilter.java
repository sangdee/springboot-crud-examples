package com.gwell.crudtemplate.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author : Sangji Lee
 * @see : https://github.com/sangdee
 * @since : 2021-03-07
 */

/**
 * Add a filter to apply to the search
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardFilter {
    private String fTitle;
}
