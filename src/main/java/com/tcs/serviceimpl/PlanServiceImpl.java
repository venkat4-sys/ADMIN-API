package com.tcs.serviceimpl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.config.AppProperties;
import com.tcs.constants.AppConstants;
import com.tcs.dto.PlanCreateRequest;
import com.tcs.dto.PlanResponse;
import com.tcs.entity.PlanEntity;
import com.tcs.repo.PlanRepository;
import com.tcs.service.PlanService;

@Service
public class PlanServiceImpl implements PlanService {

	@Autowired
	private PlanRepository planRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AppProperties appProperties;

	@Override
	public String createPlan(PlanCreateRequest planCreateRequest) {
	
		PlanEntity entity = new PlanEntity();
		entity.setPlanName(planCreateRequest.getPlanName());
		entity.setPlanStartDate(planCreateRequest.getPlanStartDate());
		entity.setPlanEndDate(planCreateRequest.getPlanEndDate());
		planRepository.save(entity);
		Map<String, String> messages = appProperties.getMessages();
		String planCreationMsg = messages.get(AppConstants.PLAN_SUCCESS_MSG);
		return planCreationMsg;
	}

	@Override
	public List<PlanResponse> getAllPlans() {
		List<PlanEntity> all = planRepository.findAll();
		List<PlanResponse> listOfPlans = all.stream().map(e -> mapToDto(e)).collect(Collectors.toList());
		return listOfPlans;
	}

	@Override
	public PlanResponse getPlan(Integer planId) {
		Optional<PlanEntity> byId = planRepository.findById(planId);
		if (byId.isPresent()) {
			PlanEntity planEntity = byId.get();
			PlanResponse response = mapToDto(planEntity);

			return response;
		}
		return null;
	}

	@Override
	public String editPlan(Integer planId, PlanCreateRequest PlanCreateRequest) {
		Optional<PlanEntity> byId = planRepository.findById(planId);
		if (byId.isPresent()) {
			PlanEntity planEntity = byId.get();
			planEntity.setPlanCategory(PlanCreateRequest.getPlanCategory());
			planEntity.setPlanStartDate(PlanCreateRequest.getPlanStartDate());
			planEntity.setPlanEndDate(PlanCreateRequest.getPlanEndDate());
			planEntity.setPlanName(PlanCreateRequest.getPlanName());
			PlanEntity entity = planRepository.save(planEntity);
			if (entity != null) {
				Map<String, String> messages = appProperties.getMessages();
				String planUpdatedMsg = messages.get(AppConstants.PLAN_UPDATE_MSG);
				return planUpdatedMsg;
			}
		}

		return null;
	}

	@Override
	public void AccSwitch(Integer planId) {
		Optional<PlanEntity> byId = planRepository.findById(planId);
		if (byId.isPresent()) {
			PlanEntity planEntity = byId.get();
			boolean status = !(planEntity.isAccSwitch());
			planEntity.setAccSwitch(status);

			planRepository.save(planEntity);

		}

	}

	private PlanResponse mapToDto(PlanEntity entity) {
		PlanResponse response = new PlanResponse();

		modelMapper.map(entity, response);

		return response;

	}
}
