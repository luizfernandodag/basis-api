package com.salsatechnology.service;

import com.salsatechnology.dto.ProductOrderDTO;
import com.salsatechnology.model.ProductOrder;
import com.salsatechnology.model.ProductType;
import com.salsatechnology.repository.ProductOrderRepository;
import com.salsatechnology.service.factory.ProductOrderFactoryIMP;
import com.salsatechnology.service.factory.ProductOrderFactory;
import com.salsatechnology.service.factory.SurfboardProductOrderFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductOrderService {
	private final Map<ProductType, ProductOrderFactory> factories;

	public ProductOrderService() {
		factories = new HashMap<>();
		factories.put(ProductType.SURFBOARD, new SurfboardProductOrderFactory());
		factories.put(ProductType.BEACH_CHAIR, new ProductOrderFactoryIMP());
		/*
	SAND_BOARD,
	BEACH_TABLE*/

	}


	@Autowired
	private final ProductOrderRepository productOrderRepository;
	private final static Double conversaoLong = 100.0;



	public void calculateOrderValues(ProductOrder productOrder)
	{
		ProductType productType = productOrder.getProductType();
		Integer timeHour = productOrder.getTimeHour();

		switch(productType)
		{
			case SURFBOARD:
				productOrder.setProductValue((long)(50.0 * conversaoLong));
				break;
			case BEACH_CHAIR:
				productOrder.setProductValue((long)(35.0 * conversaoLong));
				break;
			case SUNSHADE:
				productOrder.setProductValue((long)(40.0 * conversaoLong));
				break;
			case SAND_BOARD:
				productOrder.setProductValue((long)(25.0 * conversaoLong));
				break;
			case BEACH_TABLE:
				productOrder.setProductValue((long)(25.0 * conversaoLong));
				break;
		}

		// calcular valor toata

		Long productTotal = (long)(productOrder.getProductValue() * timeHour);
		productOrder.setProductTotal(productTotal);

		// calcular commissão

		Double comissionRate = getComissionRate(productType);
		Long useramount = (long) (productTotal * comissionRate);
		productOrder.setUserAmount(useramount);




	}
	public List<ProductOrder> filterOrdersByProduct(ProductType productType)
	{
		return productOrderRepository.findByProductType(productType);
	}
	private Double getComissionRate(ProductType productType)
	{
		switch(productType)
		{
			case SURFBOARD:
				return 0.156;
			case BEACH_CHAIR:
				return 0.05;
			case SUNSHADE:
				return 0.103;
			case SAND_BOARD:
				return 0.09;
			case BEACH_TABLE:
				return 0.081;
			default:
				return 0.0;
		}

	}

	public List<ProductOrder> getOrders(ProductOrderFilter filter)
	{
		Specification<ProductOrder> spec = null;

		if(filter.getUserName() != null)
		{
			spec = spec != null ? spec.and((root, query, cb) -> cb.like(root.get("userName"), "%" + filter.getUserName() + "%")) :
					(root, query, cb) -> cb.like(root.get("userName"), "%" + filter.getUserName() + "%");

		}

		return productOrderRepository.findAll(spec);

	}

	@Transactional
	public void createOrder(ProductOrderDTO productOrderDTO) {

		productOrderRepository.save(createProductOrder(productOrderDTO));
	}
	private Double calculateProductValue(ProductOrderDTO productOrderDTO) {
		// Implement logic to calculate product value based on product type
		/*Para SURFBOARD: Preço por hora: R$50,00 Porcentagem funcionário: 15.6%

				Para BEACH_CHAIR: Preço por hora: R$35,00 Porcentagem funcionário: 5%

				Para SUNSHADE: Preço por hora: R$40,00 Porcentagem funcionário: 10.3%

				Para SAND_BOARD: Preço por hora: R$25,00 Porcentagem funcionário: 9%

				Para BEACH_TABLE: Preço por hora: R$25,00 Porcentagem funcionário: 8.1%*/
		switch (productOrderDTO.getProductType()) {
			case SURFBOARD:
				return 500.0;
			case BEACH_CHAIR:
				return 25.0;
			case SUNSHADE:
				return 30.0;
			case SAND_BOARD:
				return 15.0;
			case BEACH_TABLE:
				return 40.0;
			default:
				return 0.0;
		}
	}

	private Integer calculateProductQuantity(ProductOrderDTO productOrderDTO) {
		return productOrderDTO.getTimeHour();
	}

	private Long calculateProductTotal(ProductOrderDTO productOrderDTO) {
		int hours = calculateProductQuantity(productOrderDTO);
		double price = calculateProductValue(productOrderDTO);
		Long out = (long) (hours*price);

		return out;
	}

	private Long calculateUserAmount(ProductOrderDTO productOrderDTO) {
		// Calculate user amount based on product value and quantity
		return (long)(calculateProductValue(productOrderDTO) * calculateProductQuantity(productOrderDTO));
	}

	private ProductOrder createProductOrder(ProductOrderDTO productOrderDTO) {
		ProductOrder productOrder = new ProductOrder();

		productOrder.setUserName(productOrderDTO.getUserName());
		productOrder.setProductType(productOrderDTO.getProductType());

		/*userName: Login do usuário;
productType: Tipo do produto;
timeHour: Tempo em horas que o produto ficou locado;
productValue: Valor do produto por hora;
productTotal: Valor do produto vezes tempo em horas locado;
userAmount: Comissão a ser recebida pelo usuário(Funcionário);*/
		productOrder.setTimeHour(productOrderDTO.getTimeHour());
		// Calculate productTotal based on logic (replace with your logic)
		productOrder.setProductValue((long)(1L*calculateProductValue(productOrderDTO)));
		//productOrder.setProductQuantity(calculateProductQuantity(productOrderDTO));




		calculateOrderValues( productOrder);
		return productOrder;
	}

	public List<ProductOrder> getOrdersByProductType(ProductType productType) {
		return productOrderRepository.findByProductType(productType);
	}

}
