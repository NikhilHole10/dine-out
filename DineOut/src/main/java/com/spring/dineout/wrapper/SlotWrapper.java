package com.spring.dineout.wrapper;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.dineout.dto.SlotsRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SlotWrapper {

	List<SlotsRequest> slotList;
}
