package com.b2chat.orden.compra;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.time.LocalDate;

import com.b2chat.orden.compra.api.OrdenCompraApi;
import com.b2chat.orden.compra.dto.OrdenCompraDto;
import com.b2chat.orden.compra.service.OrdenCompraApiServiceImp;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Clase encargada de realizar los diferentes Test de la aplicacion
 * @author jvelandia
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class TestOrdenCompraApi {
	
	private static final Logger logger = LoggerFactory.getLogger(TestOrdenCompraApi.class);

	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	private OrdenCompraApi orden;

	@InjectMocks
	private OrdenCompraApiServiceImp ordenCompraService;
	

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		MockMvcBuilders.standaloneSetup(orden).setControllerAdvice(new Exception()).build();

	}


	@Test
	@Order(1)
	public void GenerarOrdenCompra() throws Exception {
		logger.info("Ingresando al metodo: GenerarOrdenCompra");
		// Se verifica que se inyecte correctamente el EJB
		Assert.assertNotNull(ordenCompraService);
		try {
			OrdenCompraDto ordenCompraDto = new OrdenCompraDto();
			ordenCompraDto.setIdCliente(182L);
			ordenCompraDto.setNumeroOrdenCompra(78978L);
			ordenCompraDto.setFechaEntrega(LocalDate.now());
			ordenCompraDto.setDescripcion("Realizando compras de productos varios");
			
			logger.info("Consultando Servicio /b2chat/generarOrdenCompra");
			MvcResult mvcResult = mockMvc
					.perform(post("/b2chat/generarOrdenCompra").content(asJsonString(ordenCompraDto))
							.contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().is(201)).andExpect(jsonPath("$.code").value(200)).andReturn();
			mvcResult.getResponse().getContentAsString();

			logger.info("Resultado Test Final:" + mvcResult.getResponse().getContentAsString());
		} catch (Exception e) {
			Assert.fail("Se ha presentado un error funcional el cual no debe presentarse");
			logger.error("Error al consultar servicio /b2chat/generarOrdenCompra: ",e.getMessage());
		}

	}
	
	@DisplayName("Test Consultar orden Compra Por Fecha")
	@Test
	@Order(2)
	public void ConsultaOrdenCompra() throws Exception {
		logger.info("Ingresando al método: ConsultaOrdenCompra");
		MvcResult mvcResult = mockMvc
				.perform(get("/b2chat/consultaOrdenCompra/2022-06-02").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().is(200)).andExpect(jsonPath("$.code").value(200)).andReturn();

		mvcResult.getResponse().getContentAsString();
		logger.info("Resultado Test consultar orden compra:" + mvcResult.getResponse().getContentAsString());
	}



	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
