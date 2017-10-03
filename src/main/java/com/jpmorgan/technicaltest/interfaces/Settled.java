package com.jpmorgan.technicaltest.interfaces;

import java.util.List;

import com.jpmorgan.technicaltest.bo.EntityDetails;

public interface Settled {
	
	public void settle(List<EntityDetails> entityDetailsList);
}
