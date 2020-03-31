package com.komsia.kom.service;

import java.util.Map;

import com.komsia.kom.domain.SinmungoVO;

public interface SinmungoService {

	Map<String, Object> getSinmungoList(SinmungoVO sinmungoVO);

	Map<String, Object> getSinmungo(SinmungoVO sinmungoVO);

	Map<String, Object> sinmungoRegister(SinmungoVO sinmungoVO);
}
