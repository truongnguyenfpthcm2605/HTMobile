package com.poly.app.util;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;


public class SortAnPage {
	public static Sort getSort(String keySort) {
		return Sort.by(Direction.DESC, keySort);
	}
}
