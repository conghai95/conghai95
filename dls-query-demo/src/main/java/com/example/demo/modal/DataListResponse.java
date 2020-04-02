package com.example.demo.modal;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DataListResponse<D> {

	private int totalPage;

	private int totalElement;

	private List<D> data;
}
