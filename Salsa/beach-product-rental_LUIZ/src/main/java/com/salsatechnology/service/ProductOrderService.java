package com.salsatechnology.service;

import com.salsatechnology.dto.ProductOrderDTO;
import com.salsatechnology.model.ProductOrder;
import com.salsatechnology.model.ProductType;
import com.salsatechnology.repository.ProductOrderRepository;
import com.salsatechnology.service.factory.Factory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductOrderService {
	private final static Double conversaoLong = 100.0;

	static public Factory factory = new Factory();

	@Autowired
	private final ProductOrderRepository productOrderRepository;


	public List<ProductOrder> filterOrdersByProduct(ProductType productType) {
		return productOrderRepository.findByProductType(productType);
	}


	public List<ProductOrder> getOrders(ProductOrderFilter filter) {
		Specification<ProductOrder> spec = null;

		if (filter.getUserName() != null) {
			spec = spec != null ? spec.and((root, query, cb) -> cb.like(root.get("userName"), "%" + filter.getUserName() + "%")) :
					(root, query, cb) -> cb.like(root.get("userName"), "%" + filter.getUserName() + "%");

		}

		return productOrderRepository.findAll(spec);

	}

	@Transactional
	public void createOrder(ProductOrderDTO productOrderDTO) {

		productOrderRepository.save(createProductOrder(productOrderDTO));
	}


	private ProductOrder createProductOrder(ProductOrderDTO productOrderDTO) {

		return factory.createProductOrder(productOrderDTO);
	}

	public List<ProductOrder> getOrdersByProductType(ProductType productType) {
		return productOrderRepository.findByProductType(productType);
	}

	public void calculateOrderValues(ProductOrder productOrder) {
		ProductType productType = productOrder.getProductType();
		Integer timeHour = productOrder.getTimeHour();

		switch (productType) {
			case SURFBOARD:
				productOrder.setProductValue((long) (50.0 * conversaoLong));
				break;
			case BEACH_CHAIR:
				productOrder.setProductValue((long) (35.0 * conversaoLong));
				break;
			case SUNSHADE:
				productOrder.setProductValue((long) (40.0 * conversaoLong));
				break;
			case SAND_BOARD:
				productOrder.setProductValue((long) (25.0 * conversaoLong));
				break;
			case BEACH_TABLE:
				productOrder.setProductValue((long) (25.0 * conversaoLong));
				break;
		}


	}
}
